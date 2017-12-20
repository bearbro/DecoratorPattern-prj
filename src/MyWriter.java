import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MyWriter extends FilterWriter {
    /**
     * Create a new filtered writer.
     *
     * @param out a Writer object to provide the underlying stream.
     * @throws NullPointerException if <code>out</code> is <code>null</code>
     */
    static ArrayList<String> illegalWords;//非法词汇

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

    }

    public void write(char cbuf[], int off, int len) throws IOException {
        out.write(pb(new StringBuffer(String.valueOf(cbuf, off, len))));

    }

    public void write(String str, int off, int len) throws IOException {
        out.write(str, off, len);
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
