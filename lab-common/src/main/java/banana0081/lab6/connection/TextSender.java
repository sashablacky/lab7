package banana0081.lab6.connection;

import banana0081.lab6.abstraction.AbstractMessage;

import java.io.PrintStream;

public class TextSender {

    private static PrintStream printStream;

    public TextSender(PrintStream printStream) {
        TextSender.printStream = printStream;
    }

    public static void printText(String message) {
        printStream.println(message);
    }

    public static void printError(String message) {
        printStream.println(message);
    }

    public static void printMessage(AbstractMessage message) {
        printStream.println(message.getMessage());
    }

    public void changePrintStream(PrintStream newPrintStream) {
        printStream = newPrintStream;
    }
}
