package banana0081.lab6.io;
import java.util.Scanner;
public class IOUtils {


        public static Scanner in = new Scanner(System.in);

        public static void setScanner(Scanner scanner) {
            in = scanner;
        }

        public static void print(String str) {
            System.out.print(str);
        }

        public static void println(String str) {
            System.out.println(str);
        }

        public static void printErr(String str) {
            System.out.println("\u001B[31m" + str + "\u001B[0m");
        }

        public static void printList(String str) {
            System.out.print("\u001B[33m" + str + "\u001B[0m");
        }

        public static String input() {
            return in.nextLine().trim();
        }
    }
