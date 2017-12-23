import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        new MyFrame("词汇屏蔽");
        String path = "orign";
        Scanner cin = new Scanner(System.in);
//        path = cin.next();
        path = "a.txt";
        showFile(path);
        String path2 = "out.txt";
        //屏蔽
        try {
            PrintWriter out = new PrintWriter(
                    new BufferedWriter(
                            new MyWriter(
                                    new OutputStreamWriter(
                                            new FileOutputStream(path2)
                                    ))
                    )
            );
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(path)
                    )
            );
            String a = in.readLine();
            while (a != null) {
                out.println(a);
                a = in.readLine();
            }
            out.flush();
            out.close();
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        showFile(path2);
//        System.out.println("Hello World!");
    }

    static void showFile(String path) {
        String s = "--------------" + path + "------------";
        System.out.println(s);
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(path)
                    )
            );
            String a = in.readLine();
            while (a != null) {
                System.out.println(a);
                a = in.readLine();
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        int sl = s.length();
        while (sl-- > 0)
            System.out.print("-");
        System.out.print('\n');
    }
}
