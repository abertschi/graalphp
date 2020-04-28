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
package org.eclipse.php.internal.core.util;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * A simple XML writer.
 */
public class XMLWriter extends PrintWriter {
    protected int tab;

    /* constants */
    protected static final String XML_VERSION = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"; //$NON-NLS-1$

    public XMLWriter(OutputStream output) throws UnsupportedEncodingException {
        this(output, true);
    }

    public XMLWriter(OutputStream output, boolean printHeader) throws UnsupportedEncodingException {
        super(new OutputStreamWriter(output, "UTF8")); //$NON-NLS-1$
        tab = 0;
        if (printHeader) {
            println(XML_VERSION);
        }
    }

    public void endTag(String name) {
        tab--;
        printTag('/' + name, null);
    }

    public void printSimpleTag(String name, Object value) {
        if (value != null) {
            printTag(name, null, true, false);
            print(getEscaped(String.valueOf(value)));
            printTag('/' + name, null, false, true);
        }
    }

    public void printTabulation() {
        for (int i = 0; i < tab; i++) {
            super.print('\t');
        }
    }

    public void printTag(String name, Map<String, ?> parameters) {
        printTag(name, parameters, true, true);
    }

    public void printTag(String name, Map<String, ?> parameters, boolean shouldTab, boolean newLine) {
        StringBuilder sb = new StringBuilder();
        sb.append("<"); //$NON-NLS-1$
        sb.append(name);
        if (parameters != null) {
            for (Object element : parameters.keySet()) {
                sb.append(" "); //$NON-NLS-1$
                String key = (String) element;
                sb.append(key);
                sb.append("=\""); //$NON-NLS-1$
                sb.append(getEscaped(String.valueOf(parameters.get(key))));
                sb.append("\""); //$NON-NLS-1$
            }
        }
        sb.append(">"); //$NON-NLS-1$
        if (shouldTab) {
            printTabulation();
        }
        if (newLine) {
            println(sb.toString());
        } else {
            print(sb.toString());
        }
    }

    public void startTag(String name, Map<String, ?> parameters) {
        startTag(name, parameters, true);
    }

    public void startTag(String name, Map<String, ?> parameters, boolean newLine) {
        printTag(name, parameters, true, newLine);
        tab++;
    }

    private static void appendEscapedChar(StringBuilder buffer, char c) {
        String replacement = getReplacement(c);
        if (replacement != null) {
            buffer.append('&');
            buffer.append(replacement);
            buffer.append(';');
        } else {
            buffer.append(c);
        }
    }

    public static String getEscaped(String s) {
        StringBuilder result = new StringBuilder(s.length() + 10);
        for (int i = 0; i < s.length(); ++i) {
            appendEscapedChar(result, s.charAt(i));
        }
        return result.toString();
    }

    private static String getReplacement(char c) {
        // Encode special XML characters into the equivalent character
        // references.
        // These five are defined by default for all XML documents.
        switch (c) {
            case '<':
                return "lt"; //$NON-NLS-1$
            case '>':
                return "gt"; //$NON-NLS-1$
            case '"':
                return "quot"; //$NON-NLS-1$
            case '\'':
                return "apos"; //$NON-NLS-1$
            case '&':
                return "amp"; //$NON-NLS-1$
        }
        return null;
    }
}
