package org.graalphp.parser;

import com.oracle.truffle.api.TruffleException;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.source.Source;
import com.oracle.truffle.api.source.SourceSection;

/**
 * Parsing error occurred during parsing of PHP source code.
 *
 * @author abertschi
 */
public class PhpParseException extends RuntimeException implements TruffleException {
    private final Source source;
    private final int line;
    private final int column;
    private final int length;

    public PhpParseException(Source source, int line, int column, int length, String message) {
        super(message);
        this.source = source;
        this.line = line;
        this.column = column;
        this.length = length;
    }

    @Override
    public SourceSection getSourceLocation() {
        return source.createSection(line, column, length);
    }

    @Override
    public Node getLocation() {
        return null;
    }

    @Override
    public boolean isSyntaxError() {
        return true;
    }

    @SuppressWarnings("sync-override")
    @Override
    public final Throwable fillInStackTrace() {
        return this;
    }
}
