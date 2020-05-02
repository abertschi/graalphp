/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Zend Technologies
 *******************************************************************************/
/**
 * 
 */
package org.eclipse.php.core.ast.nodes;

import org.eclipse.dltk.ast.Modifiers;
import org.eclipse.dltk.core.*;

/**
 * A variable binding represents either a field of a class or interface, or a
 * local variable declaration (including formal parameters, local variables, and
 * exception variables) or a global variable.
 * <p>
 * This interface is not intended to be implemented by clients.
 * </p>
 * FIXME - need implementation
 * 
 * @author shalom
 */
public class VariableBinding implements IVariableBinding {

	private static final int VALID_MODIFIERS = Modifiers.AccPublic | Modifiers.AccProtected | Modifiers.AccPrivate
			| Modifiers.AccDefault | Modifiers.AccConstant | Modifiers.AccStatic | Modifiers.AccGlobal;

	private final BindingResolver resolver;
	private final IMember modelElement;
	private boolean isFakeField;

	private ITypeBinding declaringClassTypeBinding;
	private int id;

	private Variable varialbe;

	public Variable getVarialbe() {
		return varialbe;
	}

	/**
	 * 
	 */
	public VariableBinding(BindingResolver resolver, IMember modelElement) {
		this.resolver = resolver;
		this.modelElement = modelElement;

	}

	public VariableBinding(DefaultBindingResolver resolver, IMember modelElement, Variable variable, int id) {
		this.resolver = resolver;
		this.modelElement = modelElement;
		this.varialbe = variable;
		this.id = id;
	}

    @Override
    public boolean isField() {
        return false;
    }

    @Override
    public boolean isParameter() {
        return false;
    }

    @Override
    public boolean isGlobal() {
        return false;
    }

    @Override
    public boolean isLocal() {
        return false;
    }

    @Override
    public int getKind() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getModifiers() {
        return 0;
    }

    @Override
    public boolean isDeprecated() {
        return false;
    }

    @Override
    public IModelElement getPHPElement() {
        return null;
    }

    @Override
    public String getKey() {
        return null;
    }

    @Override
    public ITypeBinding getType() {
        return null;
    }

    @Override
    public int getVariableId() {
        return 0;
    }

    @Override
    public Object getConstantValue() {
        return null;
    }

    @Override
    public IFunctionBinding getDeclaringFunction() {
        return null;
    }

    @Override
    public ITypeBinding getDeclaringClass() {
        return null;
    }
}
