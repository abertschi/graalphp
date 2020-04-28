package java_cup;

import java.io.PrintWriter;
import java.util.Stack;
import java.util.Enumeration;

/** 
 * This class handles emitting generated code for the resulting parser.
 * The various parse tables must be constructed, etc. before calling any 
 * routines in this class.<p>  
 *
 * Three classes are produced by this code:
 *   <dl>
 *   <dt> symbol constant class
 *   <dd>   this contains constant declarations for each terminal (and 
 *          optionally each non-terminal).
 *   <dt> action class
 *   <dd>   this non-public class contains code to invoke all the user actions 
 *          that were embedded in the parser specification.
 *   <dt> parser class
 *   <dd>   the specialized parser class consisting primarily of some user 
 *          supplied general and initialization code, and the parse tables.
 *   </dl><p>
 *
 *  Three parse tables are created as part of the parser class:
 *    <dl>
 *    <dt> production table
 *    <dd>   lists the LHS non terminal number, and the length of the RHS of 
 *           each production.
 *    <dt> action table
 *    <dd>   for each state of the parse machine, gives the action to be taken
 *           (shift, reduce, or error) under each lookahead symbol.<br>
 *    <dt> reduce-goto table
 *    <dd>   when a reduce on a given production is taken, the parse stack is 
 *           popped back a number of elements corresponding to the RHS of the 
 *           production.  This reveals a prior state, which we transition out 
 *           of under the LHS non terminal symbol for the production (as if we
 *           had seen the LHS symbol rather than all the symbols matching the 
 *           RHS).  This table is indexed by non terminal numbers and indicates 
 *           how to make these transitions. 
 *    </dl><p>
 * 
 * In addition to the method interface, this class maintains a series of 
 * public global variables and flags indicating how misc. parts of the code 
 * and other output is to be produced, and counting things such as number of 
 * conflicts detected (see the source code and public variables below for
 * more details).<p> 
 *
 * This class is "static" (contains only static data and methods).<p> 
 *
 * @see java_cup.main
 * @version last update: 11/25/95
 * @author Scott Hudson
 */

/* Major externally callable routines here include:
     symbols               - emit the symbol constant class 
     parser                - emit the parser class

   In addition the following major internal routines are provided:
     emit_package          - emit a package declaration
     emit_action_code      - emit the class containing the user's actions 
     emit_production_table - emit declaration and init for the production table
     do_action_table       - emit declaration and init for the action table
     do_reduce_table       - emit declaration and init for the reduce-goto table

   Finally, this class uses a number of public instance variables to communicate
   optional parameters and flags used to control how code is generated,
   as well as to report counts of various things (such as number of conflicts
   detected).  These include:

   prefix                  - a prefix string used to prefix names that would 
			     otherwise "pollute" someone else's name space.
   package_name            - name of the package emitted code is placed in 
			     (or null for an unnamed package.
   symbol_const_class_name - name of the class containing symbol constants.
   parser_class_name       - name of the class for the resulting parser.
   action_code             - user supplied declarations and other code to be 
			     placed in action class.
   parser_code             - user supplied declarations and other code to be 
			     placed in parser class.
   init_code               - user supplied code to be executed as the parser 
			     is being initialized.
   scan_code               - user supplied code to get the next Symbol.
   start_production        - the start production for the grammar.
   import_list             - list of imports for use with action class.
   num_conflicts           - number of conflicts detected. 
   nowarn                  - true if we are not to issue warning messages.
   not_reduced             - count of number of productions that never reduce.
   unused_term             - count of unused terminal symbols.
   unused_non_term         - count of unused non terminal symbols.
   *_time                  - a series of symbols indicating how long various
			     sub-parts of code generation took (used to produce
			     optional time reports in main).
*/

public class emit {

  /*-----------------------------------------------------------*/
  /*--- Constructor(s) ----------------------------------------*/
  /*-----------------------------------------------------------*/

  /** Only constructor is private so no instances can be created. */
  private emit() { }

  /*-----------------------------------------------------------*/
  /*--- Static (Class) Variables ------------------------------*/
  /*-----------------------------------------------------------*/

  /** The prefix placed on names that pollute someone else's name space. */
  public static String prefix = "CUP$";

  /*. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .*/

  /** Package that the resulting code goes into (null is used for unnamed). */
  public static String package_name = null;

  /*. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .*/

  /** Name of the generated class for symbol constants. */
  public static String symbol_const_class_name = "sym";

  /*. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .*/

  /** Name of the generated parser class. */
  public static String parser_class_name = "parser";

  /*. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .*/

 /** TUM changes; proposed by Henning Niss 20050628: Type arguments for class declaration */
  public static String class_type_argument = null;

  /*. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .*/

  /** User declarations for direct inclusion in user action class. */
  public static String action_code = null;

  /*. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .*/

  /** User declarations for direct inclusion in parser class. */
  public static String parser_code = null;

  /*. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .*/

  /** User code for user_init() which is called during parser initialization. */
  public static String init_code = null;

  /*. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .*/

  /** User code for scan() which is called to get the next Symbol. */
  public static String scan_code = null;

  /*. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .*/

  /** The start production of the grammar. */
  public static production start_production = null;

  /*. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .*/

  /** List of imports (Strings containing class names) to go with actions. */
  public static Stack import_list = new Stack();

  /*. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .*/

  /** Number of conflict found while building tables. */
  public static int num_conflicts = 0;

  /*. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .*/

  /** Do we skip warnings? */
  public static boolean nowarn = false;

  /*. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .*/

  /** Count of the number on non-reduced productions found. */
  public static int not_reduced = 0;

  /*. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .*/

  /** Count of unused terminals. */
  public static int unused_term = 0;

  /*. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .*/

  /** Count of unused non terminals. */
  public static int unused_non_term = 0;

  /*. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .*/

  /* Timing values used to produce timing report in main.*/

  /** Time to produce symbol constant class. */
  public static long symbols_time          = 0;

  /** Time to produce parser class. */
  public static long parser_time           = 0;

  /** Time to produce action code class. */
  public static long action_code_time      = 0;

  /** Time to produce the production table. */
  public static long production_table_time = 0;

  /** Time to produce the action table. */
  public static long action_table_time     = 0;

  /** Time to produce the reduce-goto table. */
  public static long goto_table_time       = 0;

  /* frankf 6/18/96 */
  protected static boolean _lr_values;
  protected static boolean _locations;
  protected static boolean _xmlactions;
  protected static boolean _genericlabels;

  /** whether or not to emit code for left and right values */
  public static boolean lr_values() {return _lr_values;}
  public static boolean locations() { return _locations; }
  protected static void set_lr_values(boolean b) { _lr_values = b;}
  protected static void set_locations(boolean b) { _locations = b; }
  protected static void set_genericlabels(boolean b) { _genericlabels = b; }
  protected static void set_xmlactions(boolean b) { _xmlactions = b; 
 	if (!b) return;
 	_locations=true; 
 	_lr_values=true;
  }
  //Hm Added clear  to clear all static fields
  public static void clear () {
	  _genericlabels = false;
	  _xmlactions = false;
      _locations = false; 
      _lr_values = true;
      action_code = null;
      import_list = new Stack();
      init_code = null;
      not_reduced = 0;
      num_conflicts = 0;
      package_name = null;
      parser_class_name = "parser";
      parser_code = null;
      scan_code = null;
      start_production = null;
      symbol_const_class_name = "sym";
      unused_non_term = 0;
      unused_term = 0;
  }

  /*-----------------------------------------------------------*/
  /*--- General Methods ---------------------------------------*/
  /*-----------------------------------------------------------*/

  /** Build a string with the standard prefix. 
   * @param str string to prefix.
   */
  protected static String pre(String str) {
    return prefix + parser_class_name + "$" + str;
  }

   /**
    * TUM changes; proposed by Henning Niss 20050628 
    * Build a string with the specified type arguments,
    * if present, otherwise an empty string.
    */
   protected static String typeArgument() {
     return class_type_argument == null ? "" : "<" + class_type_argument + ">";
   }

  /*. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .*/

  /** Emit a package spec if the user wants one. 
   * @param out stream to produce output on.
   */
  protected static void emit_package(PrintWriter out)
    {
      /* generate a package spec if we have a name for one */
      if (package_name != null) {
	out.println("package " + package_name + ";"); out.println();
      }
    }

  /*. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .*/

  /** Emit code for the symbol constant class, optionally including non terms,
   *  if they have been requested.  
   * @param out            stream to produce output on.
   * @param emit_non_terms do we emit constants for non terminals?
   * @param sym_interface  should we emit an interface, rather than a class?
   */
  public static void symbols(PrintWriter out, 
			     boolean emit_non_terms, boolean sym_interface)
    {
      terminal term;
      non_terminal nt;
      String class_or_interface = (sym_interface)?"interface":"class";

      long start_time = System.currentTimeMillis();

      /* top of file */
      out.println();
      out.println("//----------------------------------------------------"); 
      out.println("// The following code was generated by " + version.title_str);
      out.println("//----------------------------------------------------"); 
      out.println();
      emit_package(out);

      /* class header */
      out.println("/** CUP generated " + class_or_interface + 
		  " containing symbol constants. */");
      out.println("public " + class_or_interface + " " + 
		  symbol_const_class_name + " {");

      out.println("  /* terminals */");

      /* walk over the terminals */              /* later might sort these */
      for (Enumeration e = terminal.all(); e.hasMoreElements(); )
	{
	  term = (terminal)e.nextElement();

	  /* output a constant decl for the terminal */
	  out.println("  public static final int " + term.name() + " = " + 
		      term.index() + ";");
	}

      /* Emit names of terminals */
      out.println("  public static final String[] terminalNames = new String[] {");
      for (int i = 0; i < terminal.number(); i++) {
	  out.print("  \"");
	  out.print(terminal.find(i).name());
	  out.print("\"");
	  if (i < terminal.number() - 1) {
	      out.print(",");
	  }
	  out.println();
      }
      out.println("  };");

      /* do the non terminals if they want them (parser doesn't need them) */
      if (emit_non_terms)
	{
          out.println();
          out.println("  /* non terminals */");

          /* walk over the non terminals */       /* later might sort these */
          for (Enumeration e = non_terminal.all(); e.hasMoreElements(); )
	    {
	      nt = (non_terminal)e.nextElement();

          // ****
          // TUM Comment: here we could add a typesafe enumeration
          // ****

	      /* output a constant decl for the terminal */
	      out.println("  static final int " + nt.name() + " = " + 
		          nt.index() + ";");
	    }
	}

      /* end of class */
      out.println("}");
      out.println();

      symbols_time = System.currentTimeMillis() - start_time;
    }

  /*. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .*/

  static final int UPPERLIMIT = 300;
  /** Emit code for the non-public class holding the actual action code. 
   * @param out        stream to produce output on.
   * @param start_prod the start production of the grammar.
   */
  protected static void emit_action_code(PrintWriter out, production start_prod)
    throws internal_error
    {
      production prod;

      long start_time = System.currentTimeMillis();

      /* class header */
      out.println();
      out.println(
       "/** Cup generated class to encapsulate user supplied action code.*/"
      );  
      out.println("@SuppressWarnings({\"rawtypes\", \"unchecked\", \"unused\"})");
      /* TUM changes; proposed by Henning Niss 20050628: added type arguement */
      out.println("class " +  pre("actions") + typeArgument() + " {");
      /* user supplied code */
      if (action_code != null)
	{
	  out.println();
          out.println(action_code);
	}

      /* field for parser object */
      /* TUM changes; proposed by Henning Niss 20050628: added typeArgument */
      out.println("  private final "+parser_class_name + typeArgument() + " parser;");

      /* constructor */
      out.println();
      out.println("  /** Constructor */");
      /* TUM changes; proposed by Henning Niss 20050628: added typeArgument */
      out.println("  " + pre("actions") + "("+parser_class_name+typeArgument()+" parser) {");
      out.println("    this.parser = parser;");
      out.println("  }");

      out.println();
      for (int instancecounter = 0; instancecounter <= production.number()/UPPERLIMIT; instancecounter++) {
      out.println("  /** Method "+instancecounter+" with the actual generated action code for actions "+(instancecounter*UPPERLIMIT)+" to "+((instancecounter+1)*UPPERLIMIT) +". */");
      out.println("  public final java_cup.runtime.Symbol " + 
		     pre("do_action_part")+ String.format("%08d",new Integer(instancecounter)) +"(");
      out.println("    int                        " + pre("act_num,"));
      out.println("    java_cup.runtime.lr_parser " + pre("parser,"));
      out.println("    java.util.Stack            " + pre("stack,"));
      out.println("    int                        " + pre("top)"));
      out.println("    throws java.lang.Exception");
      out.println("    {");
      out.println("      /* Symbol object for return from actions */");
      out.println("      java_cup.runtime.Symbol " + pre("result") + ";");
      out.println();
      out.println("      /* select the action based on the action number */");
      out.println("      switch (" + pre("act_num") + ")");
      out.println("        {");
      // START Switch
      /* emit action code for each production as a separate case */
      int proditeration = instancecounter*UPPERLIMIT;
      prod=production.find(proditeration);
      for ( ;proditeration<Math.min((instancecounter+1)*UPPERLIMIT,production.number());prod=(production)production.find(++proditeration) )
	{
	  /* case label */
          out.println("          /*. . . . . . . . . . . . . . . . . . . .*/");
          out.println("          case " + prod.index() + ": // " + 
					  prod.to_simple_string());

	  /* give them their own block to work in */
	  out.println("            {");


          /**
           * TUM 20060608 intermediate result patch
           */
          String result = "null";
          if (prod instanceof action_production) {
              int lastResult = ((action_production)prod).getIndexOfIntermediateResult();
              if (lastResult!=-1) {
                  result =  "(" + prod.lhs().the_symbol().stack_type() + ") " +
                      "((java_cup.runtime.Symbol) " + emit.pre("stack") + 
                      // TUM 20050917
                      ((lastResult==1)?".peek()":(".elementAt(" + emit.pre("top") + "-" + (lastResult-1) + ")"))+
                      ").value";
              }
          }

	  /* create the result symbol */
	  /*make the variable RESULT which will point to the new Symbol (see below)
	    and be changed by action code
	    6/13/96 frankf */
	  out.println("              " +  prod.lhs().the_symbol().stack_type() +
		      " RESULT ="+result+";");

	  /* Add code to propagate RESULT assignments that occur in
	   * action code embedded in a production (ie, non-rightmost
	   * action code). 24-Mar-1998 CSA
	   */
	  for (int i=prod.rhs_length()-1; i>=0; i--) {
	    // only interested in non-terminal symbols.
	    if (!(prod.rhs(i) instanceof symbol_part)) continue;
	    symbol s = ((symbol_part)prod.rhs(i)).the_symbol();
	    if (!(s instanceof non_terminal)) continue;
	    // skip this non-terminal unless it corresponds to
	    // an embedded action production.
	    if (((non_terminal)s).is_embedded_action == false) continue;
	    // OK, it fits.  Make a conditional assignment to RESULT.
	    int index = prod.rhs_length() - i - 1; // last rhs is on top.
            // set comment to inform about where the intermediate result came from
	    out.println("              " + "// propagate RESULT from " +s.name());
//            // look out, whether the intermediate result is null or not
//	    out.println("              " + "if ( " +
//	      "((java_cup.runtime.Symbol) " + emit.pre("stack") + 
//			// TUM 20050917
//			((index==0)?".peek()":(".elementAt(" + emit.pre("top") + "-" + index + ")"))+
//			").value != null )");

// TUM 20060608: even when its null: who cares?

	    // store the intermediate result into RESULT
            out.println("                " + "RESULT = " +
	      "(" + prod.lhs().the_symbol().stack_type() + ") " +
	      "((java_cup.runtime.Symbol) " + emit.pre("stack") + 
			// TUM 20050917
			((index==0)?".peek()":(".elementAt(" + emit.pre("top") + "-" + index + ")"))+
			").value;");
            break;
	  }

        /* if there is an action string, emit it */
          if (prod.action() != null && prod.action().code_string() != null &&
              !prod.action().equals(""))
            out.println(prod.action().code_string());

	  /* here we have the left and right values being propagated.  
		must make this a command line option.
	     frankf 6/18/96 */

         /* Create the code that assigns the left and right values of
            the new Symbol that the production is reducing to */
	  if (emit.lr_values()) {	    
	    int loffset;
	    String leftstring, rightstring;
	    // TUM 20050917
            //int roffset = 0;
	    rightstring = "((java_cup.runtime.Symbol)" + emit.pre("stack") + 
		// TUM 20050917
		//".elementAt(" + emit.pre("top") + "-" + roffset + "))"+
		".peek()"+
                // TUM 20060327 removed .right
		")"; 	  
	    if (prod.rhs_length() == 0) 
	      leftstring = rightstring;
	    else {
	      loffset = prod.rhs_length() - 1;
	      leftstring = "((java_cup.runtime.Symbol)" + emit.pre("stack") + 
		  // TUM 20050917
		  ((loffset==0)?(".peek()"):(".elementAt(" + emit.pre("top") + "-" + loffset + ")")) +
                  // TUM 20060327 removed .left
		  ")";
	    }
	    out.println("              " + pre("result") + " = parser.getSymbolFactory().newSymbol(" + 
                        "\""+ 	prod.lhs().the_symbol().name() +"\","+ 
			prod.lhs().the_symbol().index()  +
			", " + leftstring + ((prod.rhs_length()==0)?(""):(", " + rightstring)) + ", RESULT);");
	  } else {
	    out.println("              " + pre("result") + " = parser.getSymbolFactory().newSymbol(" + 
		"\""+ 	prod.lhs().the_symbol().name() +  "\","+ 
			prod.lhs().the_symbol().index() + 
			", RESULT);");
	  }
	  
	  /* end of their block */
	  out.println("            }");

	  /* if this was the start production, do action for accept */
	  if (prod == start_prod)
	    {
	      out.println("          /* ACCEPT */");
	      out.println("          " + pre("parser") + ".done_parsing();");
	    }

	  /* code to return lhs symbol */
	  out.println("          return " + pre("result") + ";");
	  out.println();
	}

      // END Switch
      out.println("          /* . . . . . .*/");
      out.println("          default:");
      out.println("            throw new Exception(");
      out.println("               \"Invalid action number \"+"+pre("act_num")+"+\"found in " +
				  "internal parse table\");");
      out.println();
      out.println("        }");
      out.println("    } /* end of method */");
      }

      /* action method head */
      out.println();
      out.println("  /** Method splitting the generated action code into several parts. */");
      out.println("  public final java_cup.runtime.Symbol " + 
		     pre("do_action") + "(");
      out.println("    int                        " + pre("act_num,"));
      out.println("    java_cup.runtime.lr_parser " + pre("parser,"));
      out.println("    java.util.Stack            " + pre("stack,"));
      out.println("    int                        " + pre("top)"));
      out.println("    throws java.lang.Exception");
      out.println("    {");

      if (production.number()<UPPERLIMIT) { // Make it simple for the optimizer to inline!
	  out.println("              return " + pre("do_action_part")+ String.format("%08d",new Integer(0))+"(");
	  out.println("                               " + pre("act_num,"));
	  out.println("                               " + pre("parser,"));
	  out.println("                               " + pre("stack,"));
	  out.println("                               " + pre("top);"));
	  out.println("    }");

	  /* end of class */
	  out.println("}");
	  out.println();

	  action_code_time = System.currentTimeMillis() - start_time;
	  return;
      }

      /* switch top */
      out.println("      /* select the action handler based on the action number */");
      out.println("      switch (" + pre("act_num") + "/"+UPPERLIMIT+ ")");
      out.println("        {");

      /* emit action code for each production as a separate case */
      for (int instancecounter = 0; instancecounter <= production.number()/UPPERLIMIT; instancecounter++) {
	  /* case label */
          out.println("          /*. . . . . . . . "+(instancecounter*UPPERLIMIT)+" < #action < "+((instancecounter+1)*UPPERLIMIT)+". . . . . . . . . . . .*/");
          out.println("          case " + instancecounter + ": ");
	  out.println("              return " + pre("do_action_part")+ String.format("%08d",new Integer(instancecounter))+"(");
	  out.println("                               " + pre("act_num,"));
	  out.println("                               " + pre("parser,"));
	  out.println("                               " + pre("stack,"));
	  out.println("                               " + pre("top);"));
      }

      out.println("          /* . . . no valid action number: . . .*/");
      out.println("          default:");
      out.println("            throw new Exception(\"Invalid action number found in internal parse table\");");
      out.println();
      out.println("        }      /* end of switch */");

      /* end of method */
      out.println("    }");

      /* end of class */
      out.println("}");
      out.println();

      action_code_time = System.currentTimeMillis() - start_time;
    }

  /*. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .*/

  /** Emit the production table. 
   * @param out stream to produce output on.
   */
  protected static void emit_production_table(PrintWriter out)
    {
      production all_prods[];
      production prod;

      long start_time = System.currentTimeMillis();

      /* collect up the productions in order */
      all_prods = new production[production.number()];
      for (Enumeration p = production.all(); p.hasMoreElements(); )
	{
	  prod = (production)p.nextElement();
	  all_prods[prod.index()] = prod;
	}

      // make short[][]
      short[][] prod_table = new short[production.number()][2];
      for (int i = 0; i<production.number(); i++)
	{
	  prod = all_prods[i];
	  // { lhs symbol , rhs size }
	  prod_table[i][0] = (short) prod.lhs().the_symbol().index();
	  prod_table[i][1] = (short) prod.rhs_length();
	}
      /* do the top of the table */
      out.println();
      out.println("  /** Production table. */");
      out.println("  protected static final short _production_table[][] = ");
      out.print  ("    unpackFromStrings(");
      do_table_as_string(out, prod_table);
      out.println(");");

      /* do the public accessor method */
      out.println();
      out.println("  /** Access to production table. */");
      out.println("  public short[][] production_table() " + 
						 "{return _production_table;}");

      production_table_time = System.currentTimeMillis() - start_time;
    }

  /*. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .*/

  /** Emit the action table. 
   * @param out             stream to produce output on.
   * @param act_tab         the internal representation of the action table.
   * @param compact_reduces do we use the most frequent reduce as default?
   */
  protected static void do_action_table(
    PrintWriter        out, 
    parse_action_table act_tab,
    boolean            compact_reduces)
    throws internal_error
    {
      parse_action_row row;
      parse_action     act;
      int              red;

      long start_time = System.currentTimeMillis();

      /* collect values for the action table */
      short[][] action_table = new short[act_tab.num_states()][];
      /* do each state (row) of the action table */
      for (int i = 0; i < act_tab.num_states(); i++)
	{
	  /* get the row */
	  row = act_tab.under_state[i];

	  /* determine the default for the row */
	  if (compact_reduces)
	    row.compute_default();
	  else
	    row.default_reduce = -1;

	  /* make temporary table for the row. */
	  short[] temp_table = new short[2*parse_action_row.size()];
	  int nentries = 0;

	  /* do each column */
	  for (int j = 0; j < parse_action_row.size(); j++)
	    {
	      /* extract the action from the table */
	      act = row.under_term[j];

	      /* skip error entries these are all defaulted out */
	      if (act.kind() != parse_action.ERROR)
		{
		  /* first put in the symbol index, then the actual entry */

		  /* shifts get positive entries of state number + 1 */
		  if (act.kind() == parse_action.SHIFT)
		    {
		      /* make entry */
		      temp_table[nentries++] = (short) j;
		      temp_table[nentries++] = (short)
			(((shift_action)act).shift_to().index() + 1);
		    }

		  /* reduce actions get negated entries of production# + 1 */
		  else if (act.kind() == parse_action.REDUCE)
		    {
		      /* if its the default entry let it get defaulted out */
		      red = ((reduce_action)act).reduce_with().index();
		      if (red != row.default_reduce) {
			/* make entry */
			temp_table[nentries++] = (short) j;
			temp_table[nentries++] = (short) (-(red+1));
		      }
		    } else if (act.kind() == parse_action.NONASSOC)
		      {
			/* do nothing, since we just want a syntax error */
		      }
		  /* shouldn't be anything else */
		  else
		    throw new internal_error("Unrecognized action code " + 
					     act.kind() + " found in parse table");
		}
	    }

	  /* now we know how big to make the row */
	  action_table[i] = new short[nentries + 2];
	  System.arraycopy(temp_table, 0, action_table[i], 0, nentries);

	  /* finish off the row with a default entry */
	  action_table[i][nentries++] = -1;
	  if (row.default_reduce != -1)
	    action_table[i][nentries++] = (short) (-(row.default_reduce+1));
	  else
	    action_table[i][nentries++] = 0;
	}

      /* finish off the init of the table */
      out.println();
      out.println("  /** Parse-action table. */");
      out.println("  protected static final short[][] _action_table = "); 
      out.print  ("    unpackFromStrings(");
      do_table_as_string(out, action_table);
      out.println(");");

      /* do the public accessor method */
      out.println();
      out.println("  /** Access to parse-action table. */");
      out.println("  public short[][] action_table() {return _action_table;}");

      action_table_time = System.currentTimeMillis() - start_time;
    }

  /*. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .*/

  /** Emit the reduce-goto table. 
   * @param out     stream to produce output on.
   * @param red_tab the internal representation of the reduce-goto table.
   */
  protected static void do_reduce_table(
    PrintWriter out, 
    parse_reduce_table red_tab)
    {
      lalr_state       goto_st;
      parse_action     act;

      long start_time = System.currentTimeMillis();

      /* collect values for reduce-goto table */
      short[][] reduce_goto_table = new short[red_tab.num_states()][];
      /* do each row of the reduce-goto table */
      for (int i=0; i<red_tab.num_states(); i++)
	{
	  /* make temporary table for the row. */
	  short[] temp_table = new short[2*parse_reduce_row.size()];
	  int nentries = 0;
	  /* do each entry in the row */
	  for (int j=0; j<parse_reduce_row.size(); j++)
	    {
	      /* get the entry */
	      goto_st = red_tab.under_state[i].under_non_term[j];

	      /* if we have none, skip it */
	      if (goto_st != null)
		{
		  /* make entries for the index and the value */
		  temp_table[nentries++] = (short) j;
		  temp_table[nentries++] = (short) goto_st.index();
		}
	    }
	  /* now we know how big to make the row. */
	  reduce_goto_table[i] = new short[nentries+2];
	  System.arraycopy(temp_table, 0, reduce_goto_table[i], 0, nentries);

	  /* end row with default value */
	  reduce_goto_table[i][nentries++] = -1;
	  reduce_goto_table[i][nentries++] = -1;
	}

      /* emit the table. */
      out.println();
      out.println("  /** <code>reduce_goto</code> table. */");
      out.println("  protected static final short[][] _reduce_table = "); 
      out.print  ("    unpackFromStrings(");
      do_table_as_string(out, reduce_goto_table);
      out.println(");");

      /* do the public accessor method */
      out.println();
      out.println("  /** Access to <code>reduce_goto</code> table. */");
      out.println("  public short[][] reduce_table() {return _reduce_table;}");
      out.println();

      goto_table_time = System.currentTimeMillis() - start_time;
    }

  // print a string array encoding the given short[][] array.
  protected static void do_table_as_string(PrintWriter out, short[][] sa) {
    out.println("new String[] {");
    out.print("    \"");
    int nchar=0, nbytes=0;
    nbytes+=do_escaped(out, (char)(sa.length>>16));
    nchar  =do_newline(out, nchar, nbytes);
    nbytes+=do_escaped(out, (char)(sa.length&0xFFFF));
    nchar  =do_newline(out, nchar, nbytes);
    for (int i=0; i<sa.length; i++) {
	nbytes+=do_escaped(out, (char)(sa[i].length>>16));
	nchar  =do_newline(out, nchar, nbytes);
	nbytes+=do_escaped(out, (char)(sa[i].length&0xFFFF));
	nchar  =do_newline(out, nchar, nbytes);
	for (int j=0; j<sa[i].length; j++) {
	  // contents of string are (value+2) to allow for common -1, 0 cases
	  // (UTF-8 encoding is most efficient for 0<c<0x80)
	  nbytes+=do_escaped(out, (char)(2+sa[i][j]));
	  nchar  =do_newline(out, nchar, nbytes);
	}
    }
    out.print("\" }");
  }
  // split string if it is very long; start new line occasionally for neatness
  protected static int do_newline(PrintWriter out, int nchar, int nbytes) {
    if (nbytes > 65500)  { out.println("\", "); out.print("    \""); }
    else if (nchar > 11) { out.println("\" +"); out.print("    \""); }
    else return nchar+1;
    return 0;
  }
  // output an escape sequence for the given character code.
  protected static int do_escaped(PrintWriter out, char c) {
    StringBuffer escape = new StringBuffer();
    if (c <= 0xFF) {
      escape.append(Integer.toOctalString(c));
      while(escape.length() < 3) escape.insert(0, '0');
    } else {
      escape.append(Integer.toHexString(c));
      while(escape.length() < 4) escape.insert(0, '0');
      escape.insert(0, 'u');
    }
    escape.insert(0, '\\');
    out.print(escape.toString());

    // return number of bytes this takes up in UTF-8 encoding.
    if (c == 0) return 2;
    if (c >= 0x01 && c <= 0x7F) return 1;
    if (c >= 0x80 && c <= 0x7FF) return 2;
    return 3;
  }

  /*. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .*/

  /** Emit the parser subclass with embedded tables. 
   * @param out             stream to produce output on.
   * @param action_table    internal representation of the action table.
   * @param reduce_table    internal representation of the reduce-goto table.
   * @param start_st        start state of the parse machine.
   * @param start_prod      start production of the grammar.
   * @param compact_reduces do we use most frequent reduce as default?
   * @param suppress_scanner should scanner be suppressed for compatibility?
   */
  public static void parser(
    PrintWriter        out, 
    parse_action_table action_table,
    parse_reduce_table reduce_table,
    int                start_st,
    production         start_prod,
    boolean            compact_reduces,
    boolean            suppress_scanner)
    throws internal_error
    {
      long start_time = System.currentTimeMillis();

      /* top of file */
      out.println();
      out.println("//----------------------------------------------------"); 
      out.println("// The following code was generated by " + version.title_str);
      out.println("//----------------------------------------------------"); 
      out.println();
      emit_package(out);

      /* user supplied imports */
      for (int i = 0; i < import_list.size(); i++)
	out.println("import " + import_list.elementAt(i) + ";");
      if (locations())
	out.println("import java_cup.runtime.ComplexSymbolFactory.Location;");
  	out.println("import java_cup.runtime.XMLElement;");

      /* class header */
      out.println();
      out.println("/** "+version.title_str+" generated parser.");
      out.println("  */");
      out.println("@SuppressWarnings({\"rawtypes\"})");
      /* TUM changes; proposed by Henning Niss 20050628: added typeArgument */
      out.println("public class " + parser_class_name + typeArgument() +
		  " extends java_cup.runtime.lr_parser {");

      out.println();
      out.println(" public final Class getSymbolContainer() {");
      out.println("    return "+ symbol_const_class_name +".class;");
      out.println("}");
      
      /* constructors [CSA/davidm, 24-jul-99] */
      out.println();
      out.println("  /** Default constructor. */");
      out.println("  @Deprecated");
      out.println("  public " + parser_class_name + "() {super();}");
      if (!suppress_scanner) {
	  out.println();
	  out.println("  /** Constructor which sets the default scanner. */");
	  out.println("  @Deprecated");
	  out.println("  public " + parser_class_name + 
		      "(java_cup.runtime.Scanner s) {super(s);}");
          // TUM 20060327 added SymbolFactory aware constructor
	  out.println();
	  out.println("  /** Constructor which sets the default scanner. */");
	  out.println("  public " + parser_class_name + 
		      "(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}");
      }

      /* emit the various tables */
      emit_production_table(out);
      do_action_table(out, action_table, compact_reduces);
      do_reduce_table(out, reduce_table);

      /* instance of the action encapsulation class */
      out.println("  /** Instance of action encapsulation class. */");
      out.println("  protected " + pre("actions") + " action_obj;");
      out.println();

      /* action object initializer */
      out.println("  /** Action encapsulation object initializer. */");
      out.println("  protected void init_actions()");
      out.println("    {");
      /* TUM changes; proposed by Henning Niss 20050628: added typeArgument */
      out.println("      action_obj = new " + pre("actions") + typeArgument() +"(this);");
      out.println("    }");
      out.println();

      /* access to action code */
      out.println("  /** Invoke a user supplied parse action. */");
      out.println("  public java_cup.runtime.Symbol do_action(");
      out.println("    int                        act_num,");
      out.println("    java_cup.runtime.lr_parser parser,");
      out.println("    java.util.Stack            stack,");
      out.println("    int                        top)");
      out.println("    throws java.lang.Exception");
      out.println("  {");
      out.println("    /* call code in generated class */");
      out.println("    return action_obj." + pre("do_action(") +
                  "act_num, parser, stack, top);");
      out.println("  }");
      out.println("");


      /* method to tell the parser about the start state */
      out.println("  /** Indicates start state. */");
      out.println("  public int start_state() {return " + start_st + ";}");

      /* method to indicate start production */
      out.println("  /** Indicates start production. */");
      out.println("  public int start_production() {return " + 
		     start_production.index() + ";}");
      out.println();

      /* methods to indicate EOF and error symbol indexes */
      out.println("  /** <code>EOF</code> Symbol index. */");
      out.println("  public int EOF_sym() {return " + terminal.EOF.index() + 
					  ";}");
      out.println();
      out.println("  /** <code>error</code> Symbol index. */");
      out.println("  public int error_sym() {return " + terminal.error.index() +
					  ";}");
      out.println();

      /* user supplied code for user_init() */
      if (init_code != null)
	{
          out.println();
	  out.println("  /** User initialization code. */");
	  out.println("  public void user_init() throws java.lang.Exception");
	  out.println("    {");
	  out.println(init_code);
	  out.println("    }");
	}

      /* user supplied code for scan */
      if (scan_code != null)
	{
          out.println();
	  out.println("  /** Scan to get the next Symbol. */");
	  out.println("  public java_cup.runtime.Symbol scan()");
	  out.println("    throws java.lang.Exception");
	  out.println("    {");
	  out.println(scan_code);
	  out.println("    }");
	}

      /* user supplied code */
      if (parser_code != null)
	{
	  out.println();
          out.println(parser_code);
	}


      /* put out the action code class */
      if (!_xmlactions)
    	  emit_action_code(out, start_prod);
      else
    	  emit_xmlaction_code(out, start_prod);

      /* end of class */
      out.println("}");

      parser_time = System.currentTimeMillis() - start_time;
    }
/** Emit code for generic XML parsetree output. 
   * @param out        stream to produce output on.
   * @param start_prod the start production of the grammar.
   */
  protected static void emit_xmlaction_code(PrintWriter out, production start_prod)
    throws internal_error
    {
      production prod;

      long start_time = System.currentTimeMillis();

      /* class header */
      out.println();
      out.println(
       "/** Cup generated class to encapsulate user supplied action code.*/"
      );  
      out.println("@SuppressWarnings({\"rawtypes\", \"unchecked\", \"unused\"})");
      out.println("class " +  pre("actions") + typeArgument() + " {");
      /* user supplied code */
      if (action_code != null)
	{
	  out.println();
          out.println(action_code);
	}

      /* field for parser object */
      out.println("  private final "+parser_class_name + typeArgument() + " parser;");

      /* constructor */
      out.println();
      out.println("  /** Constructor */");
      out.println("  " + pre("actions") + "("+parser_class_name+typeArgument()+" parser) {");
      out.println("    this.parser = parser;");
      out.println("  }");

      out.println();
      for (int instancecounter = 0; instancecounter <= production.number()/UPPERLIMIT; instancecounter++) {
      out.println("  /** Method "+instancecounter+" with the actual generated action code for actions "+(instancecounter*UPPERLIMIT)+" to "+((instancecounter+1)*UPPERLIMIT) +". */");
      out.println("  public final java_cup.runtime.Symbol " + 
		     pre("do_action_part")+ String.format("%08d",new Integer(instancecounter)) +"(");
      out.println("    int                        " + pre("act_num,"));
      out.println("    java_cup.runtime.lr_parser " + pre("parser,"));
      out.println("    java.util.Stack            " + pre("stack,"));
      out.println("    int                        " + pre("top)"));
      out.println("    throws java.lang.Exception");
      out.println("    {");
      out.println("      /* Symbol object for return from actions */");
      out.println("      java_cup.runtime.Symbol " + pre("result") + ";");
      out.println();
      out.println("      /* select the action based on the action number */");
      out.println("      switch (" + pre("act_num") + ")");
      out.println("        {");
      // START Switch
      /* emit action code for each production as a separate case */
      int proditeration = instancecounter*UPPERLIMIT;
      prod=production.find(proditeration);
      for ( ;proditeration<Math.min((instancecounter+1)*UPPERLIMIT,production.number());prod=(production)production.find(++proditeration) )
	{
	  /* case label */
          out.println("          /*. . . . . . . . . . . . . . . . . . . .*/");
          out.println("          case " + prod.index() + ": // " + 
					  prod.to_simple_string());

	  /* give them their own block to work in */
	  out.println("            {");


      out.println("                XMLElement RESULT;");
      

          // Generate the XML Output
          String nested="";
          for (int rhsi=0;rhsi<prod.rhs_length();rhsi++){
        	  if (!(prod.rhs(rhsi) instanceof symbol_part)) continue;
        	  String label = prod.rhs(rhsi).label();
        	  symbol_part sym  = (symbol_part)prod.rhs(rhsi);
        	  if (label==null) {
        		  if (!_genericlabels) continue;
        		  label = sym.the_symbol().name()+rhsi;
        	  }
        	  if (sym.the_symbol().is_non_term())
        		  nested+=",(XMLElement)"+label;
        	  else 
        		  nested+=",new XMLElement.Terminal("+label+"xleft,\""+label+"\","+label+","+label+"xright)";
          }
          
          if (prod.action() != null && prod.action().code_string() != null &&
                  !prod.action().equals(""))
                out.println(prod.action().code_string());
          
          // determine the variant:
          int variant=0;
          for (int i=0;i<proditeration;i++)
        	  if (production.find(i).lhs().equals(prod.lhs())) variant++;
          
          String lhsname =prod.lhs().the_symbol().name().replace('$','_'); 
          out.println("                RESULT = new XMLElement.NonTerminal(\""+
        		  lhsname+"\","+
        		  variant+nested+");"); 
          
          
         /* Create the code that assigns the left and right values of
            the new Symbol that the production is reducing to */
	  if (emit.lr_values()) {	    
	    int loffset;
	    String leftstring, rightstring;
	    rightstring = "((java_cup.runtime.Symbol)" + emit.pre("stack") + ".peek()"+")"; 	  
	    if (prod.rhs_length() == 0) 
	      leftstring = rightstring;
	    else {
	      loffset = prod.rhs_length() - 1;
	      leftstring = "((java_cup.runtime.Symbol)" + emit.pre("stack") + 
		  ((loffset==0)?(".peek()"):(".elementAt(" + emit.pre("top") + "-" + loffset + ")")) +")";
	    }
	    out.println("              " + pre("result") + " = parser.getSymbolFactory().newSymbol(" + 
                        "\""+ 	prod.lhs().the_symbol().name() +"\","+ 
			prod.lhs().the_symbol().index() + ", " + leftstring + ", " + rightstring + ", RESULT);");
	  } else {
	    out.println("              " + pre("result") + " = parser.getSymbolFactory().newSymbol(" + 
		"\""+ 	prod.lhs().the_symbol().name() +  "\"," + prod.lhs().the_symbol().index() + ", RESULT);");
	  }
	  
	  /* end of their block */
	  out.println("            }");

	  /* if this was the start production, do action for accept */
	  if (prod == start_prod)
	    {
	      out.println("          /* ACCEPT */");
	      out.println("          " + pre("parser") + ".done_parsing();");
	    }

	  /* code to return lhs symbol */
	  out.println("          return " + pre("result") + ";");
	  out.println();
	}

      // END Switch
      out.println("          /* . . . . . .*/");
      out.println("          default:");
      out.println("            throw new Exception(");
      out.println("               \"Invalid action number \"+"+pre("act_num")+"+\"found in " +
				  "internal parse table\");");
      out.println();
      out.println("        }");
      out.println("    } /* end of method */");
      }

      /* action method head */
      out.println();
      out.println("  /** Method splitting the generated action code into several parts. */");
      out.println("  public final java_cup.runtime.Symbol " + 
		     pre("do_action") + "(");
      out.println("    int                        " + pre("act_num,"));
      out.println("    java_cup.runtime.lr_parser " + pre("parser,"));
      out.println("    java.util.Stack            " + pre("stack,"));
      out.println("    int                        " + pre("top)"));
      out.println("    throws java.lang.Exception");
      out.println("    {");

      if (production.number()<UPPERLIMIT) { // Make it simple for the optimizer to inline!
	  out.println("              return " + pre("do_action_part")+ String.format("%08d",new Integer(0))+"(");
	  out.println("                               " + pre("act_num,"));
	  out.println("                               " + pre("parser,"));
	  out.println("                               " + pre("stack,"));
	  out.println("                               " + pre("top);"));
	  out.println("    }");

	  /* end of class */
	  out.println("}");
	  out.println();

	  action_code_time = System.currentTimeMillis() - start_time;
	  return;
      }

      /* switch top */
      out.println("      /* select the action handler based on the action number */");
      out.println("      switch (" + pre("act_num") + "/"+UPPERLIMIT+ ")");
      out.println("        {");

      /* emit action code for each production as a separate case */
      for (int instancecounter = 0; instancecounter <= production.number()/UPPERLIMIT; instancecounter++) {
	  /* case label */
          out.println("          /*. . . . . . . . "+(instancecounter*UPPERLIMIT)+" < #action < "+((instancecounter+1)*UPPERLIMIT)+". . . . . . . . . . . .*/");
          out.println("          case " + instancecounter + ": ");
	  out.println("              return " + pre("do_action_part")+ String.format("%08d",new Integer(instancecounter))+"(");
	  out.println("                               " + pre("act_num,"));
	  out.println("                               " + pre("parser,"));
	  out.println("                               " + pre("stack,"));
	  out.println("                               " + pre("top);"));
      }

      out.println("          /* . . . no valid action number: . . .*/");
      out.println("          default:");
      out.println("            throw new Exception(\"Invalid action number found in internal parse table\");");
      out.println();
      out.println("        }      /* end of switch */");

      /* end of method */
      out.println("    }");

      /* end of class */
      out.println("}");
      out.println();

      action_code_time = System.currentTimeMillis() - start_time;
    }


    /*-----------------------------------------------------------*/
}
