package readability;

import java.io.File;
import java.io.FileNotFoundException;
import eu.crydee.syllablecounter.SyllableCounter;
import java.util.Scanner;

public class Main {
    public static String load(String importPath) {
        StringBuilder result = new StringBuilder();
        try (final Scanner scan = new Scanner(new File(importPath))) {
            while (scan.hasNext()) {
               result.append(scan.nextLine());
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
        }
        return result.toString();
    }
    public static void printResult(int choose) {
        switch (choose) {
            case 1:
                System.out.println("This text should be understood by 6 year olds.");
                break;
            case 2:
                System.out.println("This text should be understood by 7 year olds.");
                break;
            case 3:
                System.out.println("This text should be understood by 9 year olds.");
                break;
            case 4:
                System.out.println("This text should be understood by 10 year olds.");
                break;
            case 5:
                System.out.println("This text should be understood by 11 year olds.");
                break;
            case 6:
                System.out.println("This text should be understood by 12 year olds.");
                break;
            case 7:
                System.out.println("This text should be understood by 13 year olds.");
                break;
            case 8:
                System.out.println("This text should be understood by 14 year olds.");
                break;
            case 9:
                System.out.println("This text should be understood by 15 year olds.");
                break;
            case 10:
                System.out.println("This text should be understood by 16 year olds.");
                break;
            case 11:
                System.out.println("This text should be understood by 17 year olds.");
                break;
            case 12:
                System.out.println("This text should be understood by 18 year olds.");
                break;
            case 13:
                System.out.println("This text should be understood by 24 year olds.");
                break;
            case 14:
                System.out.println("This text should be understood by 24+ year olds.");
                break;
            default:
                System.out.println("You're too old person or you don't exist:)");
                break;
        }
    }
    public static void main(String[] args) {
        SyllableCounter syllableCounter = new SyllableCounter();
        /*String inputPath = "";
        for (int i = 0; i < args.length;  i++) {
            inputPath = args[0];
        }*/
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();//load(inputPath);
        int countSyllable = syllableCounter.count(inputString);
        char[] charInputString = inputString.toCharArray();
        int countChar = 0;
        for (char c : charInputString) {
            if (c != ' ') {
                ++countChar;
            }
        }
        String[] arrayInputString = inputString.replaceAll("[,()]","").split("[.!?]".trim());
        int countWordsInSentence = 0;
        for (String str: arrayInputString) {
            String[] temp = str.split("\\b\\s|\\b\u00a0");
            countWordsInSentence += temp.length;
        }
        double score = 4.71 * ((double) countChar/countWordsInSentence) + 0.5 * ((double)countWordsInSentence/arrayInputString.length) - 21.43;
        System.out.println("The text is: " + inputString);
        System.out.println();
        System.out.println("Words: " + countWordsInSentence);
        System.out.println("Sentences: " + arrayInputString.length);
        System.out.println("Characters: " + countChar);
        System.out.println("Syllables: " + countSyllable);
        System.out.printf("The score is: %.2f\n", score);
        int choose = (int) Math.ceil(score);
        printResult(choose);
     }
}
