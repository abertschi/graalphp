package org.graalphp.nodes;

import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.RootNode;
import org.graalphp.PhpLanguage;

/**
 * Function which is not initialized but used somewhere will cause a fail
 *
 * @author abertschi
 */
public class UndefFunctionRootNode extends RootNode {

    private final String name;

    public UndefFunctionRootNode(PhpLanguage lang, String name) {
        super(lang);
        this.name = name;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        return null;
    }
}
