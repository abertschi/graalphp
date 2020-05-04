package org.eclipse.php.core.ast.error;

import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.Symbol;

import java.util.LinkedList;
import java.util.List;

/**
 * Manages errors in PHP Parser.
 * An error may be fatal or non-fatal. Fatal errors will throw exception
 * if parsing is continued. Non-Fatal errors are recoverable and parsing continues.
 *
 * @author abertschi
 */
public class ParseErrorManager {

    private List<ParseErrorListener> listeners = new LinkedList<>();

    public void addListener(ParseErrorListener l) {
        this.listeners.add(l);
    }

    public void removeListener(ParseErrorListener l) {
        this.listeners.remove(l);
    }

    protected void notifyListeners(ErrorEvent e) {
        listeners.forEach(l -> l.onError(e));
    }

    public void signalError(String message, Object info, boolean isFatal) {
        StringBuilder msg = new StringBuilder(message);

        ErrorEvent err = new ErrorEvent(isFatal ? ErrorEvent.Type.FATAL
                : ErrorEvent.Type.GENERAL);

        extractLocation(msg, info, err);
        err.setMessage(msg.toString());
        err.setParseObject(info);
        err.setExpectedTokens(null);
        notifyListeners(err);

    }

    public void signalSyntaxError(Symbol token, List<String> expectedTokenList) {
        StringBuilder msg = new StringBuilder("Syntax error");

        ErrorEvent e = new ErrorEvent(ErrorEvent.Type.SYNTAX);
        extractLocation(msg, token, e);
        e.setMessage(msg.toString());
        e.setParseObject(token);
        e.setExpectedTokens(expectedTokenList != null ? expectedTokenList : new LinkedList<>());

        notifyListeners(e);
    }

    private void extractLocation(StringBuilder msg, Object info, ErrorEvent err) {
        // XXX: index start at 0
        if (info instanceof ComplexSymbolFactory.ComplexSymbol) {
            ComplexSymbolFactory.ComplexSymbol cs = (ComplexSymbolFactory.ComplexSymbol) info;
            err.setLeft(cs.getLeft().getOffset());
            err.setRight(cs.getRight().getOffset());
            msg.append(" for input symbol \"")
                    .append(cs.getName())
                    .append("\" spanning from ")
                    .append(cs.getLeft())
                    .append(" to ")
                    .append(cs.getRight());
        }

        // XXX: index we get here are 0 based. +1 may be better for readability
        if (info instanceof Symbol) {
            int left = ((Symbol) info).left;
            int right = ((Symbol) info).right;
            err.setLeft(left != -1 ? left : ErrorEvent.POSITION_UNKNOWN);
            err.setRight(right != -1 ? right : ErrorEvent.POSITION_UNKNOWN);

            if (left != -1) {
                msg.append(" at character ")
                        .append(((Symbol) info).left)
                        .append(" of input");
            }
        }
    }
}
