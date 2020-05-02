package org.eclipse.php.core.ast.error;

import java.util.LinkedList;
import java.util.List;

/**
 * @author abertschi
 */
public class ErrorEvent {

    public enum Type {
        SYNTAX,
        GENERAL,
        FATAL
    }

    public static final int POSITION_UNKNOWN = -1;

    private Type type;

    private String message;

    private int left = POSITION_UNKNOWN;
    private int right = POSITION_UNKNOWN;

    /** Parser Context object, ie token **/
    private Object parseObject;

    /** is set if type is SYNTAX **/
    private List<String> expectedTokens = new LinkedList<>();

    public ErrorEvent(Type type, String message) {
        this.type = type;
        this.message = message;
    }

    public ErrorEvent(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public Object getParseObject() {
        return parseObject;
    }

    public void setParseObject(Object parseObject) {
        this.parseObject = parseObject;
    }

    public List<String> getExpectedTokens() {
        return expectedTokens;
    }

    public void setExpectedTokens(List<String> expectedTokens) {
        this.expectedTokens = expectedTokens;
    }

    @Override
    public String toString() {
        return "ErrorEvent{" +
                "type=" + type +
                ", message='" + message + '\'' +
                ", left=" + left +
                ", right=" + right +
                ", parseObject=" + parseObject +
                ", expectedTokens=" + expectedTokens +
                '}';
    }
}