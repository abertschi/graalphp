package org.graalphp.nodes.function;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.RootNode;
import org.graalphp.PhpLanguage;
import org.graalphp.exception.PhpUndefFunctionException;

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
        throw new PhpUndefFunctionException("function not backed by implementation " + name,
                this);
    }
}
