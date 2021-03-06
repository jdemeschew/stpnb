package de.tinysite.stpnb.antlr;
import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.misc.Interval;
import org.netbeans.spi.lexer.LexerInput;

/**
 * adapated from http://wiki.netbeans.org/New_Language_Support_Tutorial_Antlr
 */
public class AntlrCharStream implements CharStream {

    private int line = 1;
    private int charPositionInLine = 0;
    private final LexerInput input;
    private final String name;
    private int index = 0;
    private List<CharStreamState> markers;
    private int markDepth = 0;

    public AntlrCharStream(LexerInput input, String name) {
        this.input = input;
        this.name = name;
    }

    @Override
    public String getText(Interval intrvl) {
        return input.readText().toString();
    }


    @Override
    public void consume() {
        int c = input.read();
        index++;
        charPositionInLine++;

        if (c == '\n') {
            line++;
            charPositionInLine = 0;
        }
    }

    @Override
    public int LA(int i) {
        if (i == 0) {
            return 0; // undefined
        }

        int c = 0;
        for (int j = 0; j < i; j++) {
            c = read();
        }

        backup(i);

        return c;
    }

    @Override
    public int mark() {
        if (markers == null) {
            markers = new ArrayList<>();
            markers.add(null); // depth 0 means no backtracking, leave blank
        }

        markDepth++;

        CharStreamState state;
        if (markDepth >= markers.size()) {
            state = new CharStreamState();
            markers.add(state);
        } else {
            state = markers.get(markDepth);
        }

        state.index = index;
        state.line = line;
        state.charPositionInLine = charPositionInLine;

        return markDepth;
    }
            
    @Override
    public void release(int marker) {
        // unwind any other markers made after m and release m
        markDepth = --marker;
    }

    @Override
    public void seek(int index) {
        if (index < this.index) {
            backup(this.index - index);
            this.index = index; // just jump; don't update stream state (line, ...)
            return;
        }

        // seek forward, consume until p hits index
        while (this.index < index) {
            consume();
        }
    }

    @Override
    public int index() {
        return index;
    }

    @Override
    public int size() {
        return -1; //unknown...
    }

    @Override
    public String getSourceName() {
        return name;
    }

    private int read() {
        int result = input.read();
        if (result == LexerInput.EOF) {
            result = CharStream.EOF;
        }
        return result;
    }

    private void backup(int count) {
        input.backup(count);
    }

    private class CharStreamState {

        int index;
        int line;
        int charPositionInLine;
    }
}

