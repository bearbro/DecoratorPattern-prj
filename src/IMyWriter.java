import java.io.IOException;
import java.io.Writer;

public  abstract class IMyWriter extends Writer{
    protected Writer out;
    IMyWriter(Writer out){
        this.out=out;
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        out.write(cbuf);
    }

    @Override
    public void flush() throws IOException {
        out.flush();
    }

    @Override
    public void close() throws IOException {
        out.close();
    }
}
