package readability;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        String[] inputString = scanner.nextLine().replaceAll(",","").split("[.!?]".trim());
        int countWordsInSentence = 0;
        for (String str: inputString) {
            String[] temp = str.split("\\b\\s|\\b\u00a0");
            countWordsInSentence += temp.length;
        }
        if ((double)countWordsInSentence / inputString.length > 10.0) {
            System.out.println("HARD");
        }
        else {
            System.out.println("EASY");
        }
     }
}
