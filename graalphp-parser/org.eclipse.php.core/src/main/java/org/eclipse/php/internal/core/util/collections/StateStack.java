/*******************************************************************************
 * Copyright (c) 2009, 2017 IBM Corporation and others.
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
/*
 * StateStack.java
 *
 * Created on May 2, 2000, 12:36 PM
 */

package org.eclipse.php.internal.core.util.collections;

import java.util.Arrays;

/**
 * @author erez
 * @version A last in first out (LIFO) stack of integers that contains states
 *          pushed by the lexer.
 */
public class StateStack implements Cloneable {

    private byte[] stack;
    private int lastIn = -1;

    /**
     * Creates new StateStack
     */
    public StateStack() {
        this(5);
    }

    public StateStack(int stackSize) {
        stack = new byte[stackSize];
        lastIn = -1;
    }

    public boolean isEmpty() {
        return lastIn == -1;
    }

    public int popStack() {
        int result = stack[lastIn];
        lastIn--;
        return result;
    }

    public void pushStack(int state) {
        lastIn++;
        if (lastIn == stack.length) {
            multiplySize();
        }
        stack[lastIn] = (byte) state;
    }

    private void multiplySize() {
        int length = stack.length;
        byte[] temp = new byte[length * 2];
        System.arraycopy(stack, 0, temp, 0, length);
        stack = temp;
    }

    public int clear() {
        return lastIn = -1;
    }

    public int size() {
        return lastIn + 1;
    }

    public StateStack createClone() {
        StateStack rv = new StateStack(this.size());
        rv.copyFrom(this);
        return rv;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return createClone();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + lastIn;
        result = prime * result + Arrays.hashCode(stack);
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        StateStack other = (StateStack) obj;
        if (lastIn != other.lastIn) {
            return false;
        }
        if (!Arrays.equals(stack, other.stack)) {
            return false;
        }
        return true;
    }

    public void copyFrom(StateStack s) {
        while (s.lastIn >= this.stack.length) {
            this.multiplySize();
        }
        this.lastIn = s.lastIn;
        for (int i = 0; i <= s.lastIn; i++) {
            this.stack[i] = s.stack[i];
        }
    }

    public boolean contains(int state) {
        for (int i = 0; i <= lastIn; i++) {
            if (stack[i] == state) {
                return true;
            }
        }
        return false;
    }

    public int get(int index) {
        return stack[index];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(50);
        for (int i = 0; i <= lastIn; i++) {
            sb.append(" stack[" + i + "]= " + stack[i]); //$NON-NLS-1$ //$NON-NLS-2$
        }
        return sb.toString();
    }

}