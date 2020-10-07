package org.graalphp.nodes.literal;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import org.graalphp.nodes.PhpExprNode;

/**
 * Represents string in graalphp
 *
 * @author Avinash D'Silva <avinash.roshan.dsilva@gmail.com>
 */
@NodeInfo(shortName = "string")
public final class PhpStringNode extends PhpExprNode {

    private final String val;

    public PhpStringNode(String v) {
        if(v.startsWith("'") && v.endsWith("'")){
            this.val = v.substring(1,v.length()-1);
        }else if(v.startsWith("\"") && v.endsWith("\"")){
            String str = v.substring(1,v.length()-1);
            str = str.replace("\\n","\n");
            str = str.replace("\\t","\t");
            str = str.replace("\\r","\r");
            this.val = str;
        }else {
            this.val = v;
        }
    }

    public PhpStringNode(Long v) {
        this.val = String.valueOf(v);
    }

    @Override
    public boolean executeBoolean(VirtualFrame f) {
        if(val.equals("")){
            return false;
        }else if(val.equals("false")){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public Object executeGeneric(VirtualFrame frame) {
        return val;
    }

    @Override
    public String toString() {
        return "PhpStringNode{" +
                "val=" + val +
                '}';
    }
}
