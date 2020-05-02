package org.eclipse.core.runtime;

import org.eclipse.php.core.ast.nodes.IBinding;

public class Assert {
    public static boolean isNotNull(IBinding binding) {
        return binding != null;
    }

    public static boolean isTrue(boolean length) {
        return length;
    }
}
