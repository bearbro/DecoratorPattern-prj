import java.io.IOException;
import java.io.Writer;

public  abstract class IMyWriter extends Writer{
    private Writer out;
    IMyWriter(Writer out){
        this.out=out;
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        out.write(str, off, len);
    }

    @Override
    public void write(String str) throws IOException {
        out.write(str);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        out.write(cbuf,off, len);
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
