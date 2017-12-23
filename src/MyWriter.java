import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MyWriter extends Writer {

    private ArrayList<String> illegalWords;//非法词汇
    private Writer out;


    public MyWriter(Writer out) {
        super(out);
        this.out = out;
        illegalWords=readIllegalWords("badwords.txt");//载入非法词汇
    }

    public  void addIllegalWords(ArrayList<String> illegalWords) {
        illegalWords.addAll(illegalWords);
    }

    @Override
    public void write(char cbuf[], int off, int len) throws IOException {
        out.write(pb(new StringBuffer(String.valueOf(cbuf, off, len))));
    }
    public void write(String str) throws IOException {
        out.write(pb(new StringBuffer(str)));
    }
    public void write(String str, int off, int len) throws IOException {
        out.write(pb(new StringBuffer(str.substring(off, len))));
    }

    @Override
    public void flush() throws IOException {
       out.flush();
    }

    @Override
    public void close() throws IOException {
        out.close();
    }


    //屏蔽函数
    public static String pb(StringBuffer sb) {
        char ch = '*';

        for (String oldStr :readIllegalWords("badwords.txt") ) {
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
    public static   ArrayList<String> readIllegalWords(String illegalWordsFile) {
        ArrayList nillegalWords = new ArrayList<String>();
        try {
            Scanner in = new Scanner(new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(illegalWordsFile))));
            while (in.hasNext()) {
                nillegalWords.add(in.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return nillegalWords;
    }
}
