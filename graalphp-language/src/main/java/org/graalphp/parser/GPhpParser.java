package org.graalphp.parser;

import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.api.source.Source;
import org.eclipse.php.core.PHPVersion;
import org.eclipse.php.core.ast.error.ConsoleErrorListener;
import org.eclipse.php.core.ast.error.ErrorEvent;
import org.eclipse.php.core.ast.nodes.ASTParser;
import org.graalphp.PhpLanguage;
import org.graalphp.util.GPhpLogger;
import org.graalphp.util.Logger;

import java.util.Map;

/**
 * Main class to parse and translate PHP to graal php AST.
 *
 * @author abertschi
 */
public class GPhpParser {
    private PhpLanguage lang;
    private final static Logger LOG = GPhpLogger.getLogger(PhpLanguage.class.getCanonicalName());

    public GPhpParser(PhpLanguage lang) {
        this.lang = lang;
    }

    public Map<String, RootCallTarget> parseSource(Source source) {
        GPhpParseVisitor v = new GPhpParseVisitor();
        ASTParser parser = null;
        try {
            parser = ASTParser.newParser(PHPVersion.PHP7_4);
            parser.setSource(source.getReader());
            parser.addErrorListener(new ConsoleErrorListener());
            parser.addErrorListener((event) -> handleParseError(source, event));
            Object pgm = null;
            pgm = parser.parsePhpProgram();
            LOG.finest(pgm.toString());
        }catch (Exception e) {
        }

        return null;
    }

    void handleParseError(Source source, ErrorEvent event) {
        System.out.println("handleParseError");
        StringBuilder msg = new StringBuilder();
        if (event.getType() == ErrorEvent.Type.SYNTAX) {
            msg.append("Syntax Error(s)");
        } else {
            msg.append("Error(s)");
        }
        int col = 0;
        int line = 0;
        int len = 0;
        String location = " unknown location";
        if (event.getLeft() != ErrorEvent.POSITION_UNKNOWN) {
            col = source.getColumnNumber(event.getLeft());
            line = source.getLineNumber(event.getLeft());
            location = "-- line " + line + " col " + col + ": ";
            len = Math.max(event.getRight() - event.getLeft(), 0);
        }

        msg.append(" parsing script: ").append(location);

        // XXX: we dont need detailed msg if we deal with syntax error
        if (event.getType() != ErrorEvent.Type.SYNTAX) {
            if (event.getMessage() != null && !event.getMessage().isEmpty()) {
                msg.append("(").append(event.getMessage()).append(")");
            }
        }
        LOG.info(msg.toString());
        throw new GPhpParseException(source, line, col, len, msg.toString());
    }
}
