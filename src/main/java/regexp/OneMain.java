package regexp;

public class OneMain {
    public static void main(String[] args) {
        /*
            \\d - одно цифра
            + - 1 или более
            * - 0 или более
            ? - 0 или 1 символ до
            (x|y|z) - одна строка из множества строк
         */

        String a = "12123";
        String b = "-#dsd12123";
        String c = "+12123";
        String d = "#12123";
        String e = "$12123";
        System.out.println(a.matches("(-|\\+|#|\\$)?\\d+"));
        System.out.println(b.matches("(-#dsd|\\+|#|\\$)?\\d+"));
        System.out.println(c.matches("(-|\\+|#|\\$)?\\d+"));
        System.out.println(d.matches("(-|\\+|#|\\$)?\\d+"));
        System.out.println(e.matches("(-|\\+|#|\\$)?\\d+"));

        
    }
}
