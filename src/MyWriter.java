import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MyWriter extends Writer {

    static ArrayList<String> illegalWords;//非法词汇
    protected Writer out;
    static {
        illegalWords = new ArrayList<String>();
        try {
            Scanner in = new Scanner(new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream("badwords.txt"))));
            while (in.hasNext()) {
                illegalWords.add(in.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected MyWriter(Writer out) {
        super(out);
        this.out = out;

    }

    public void write(char cbuf[], int off, int len) throws IOException {
        out.write(pb(new StringBuffer(String.valueOf(cbuf, off, len))));
    }

    public void write(String str, int off, int len) throws IOException {
        out.write(str, off, len);
    }

    @Override
    public void flush() throws IOException {
       out.flush();
    }

    @Override
    public void close() throws IOException {
        out.close();
    }

    public void write(int c) throws IOException {
        out.write(c);
    }

    //屏蔽函数
    static String pb(StringBuffer sb) {
        char ch = '*';
        for (String oldStr : illegalWords) {
            int i = sb.indexOf(oldStr);
            int oldLen = oldStr.length();
            while (i > -1) {
                for (int j = 0; j < oldLen; j++) {
                    sb.setCharAt(i + j, ch);
                }
                i = sb.indexOf(oldStr, i + oldLen);
            }
        }
        return sb.toString();
    }
}
