package readability;
import java.io.*;
import java.util.Scanner;

public class ReadabilityScore {
    private String load(String importPath) {
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
    private boolean isVowel(char ch) { return ch == 'a' || ch == 'i' || ch == 'o' || ch == 'u' || ch == 'y'; }
    private int countCharInText(String input) {
        char[] charInputString = input.toCharArray();
        int countChar = 0;
        for (char c : charInputString) {
            if (c != ' ') {
                ++countChar;
            }
        }
        return countChar;
    }
    private int countWordInText(String[] input) {
        int countWordsInSentence = 0;
        for (String str: input) {
            String[] temp = str.split("\\b\\s|\\b\u00a0");
            countWordsInSentence += temp.length;
        }
        return countWordsInSentence;
    }
    private int countSyllableInText(String[] input) {
        int countSyllable = 0;
        for (String str: input) {
            String[] temp = str.split("\\b\\s|\\b\u00a0");
            for (String word : temp) {
                int wordsylls = 0;
                countSyllable -= word.equalsIgnoreCase("you") ? 1 : 0;
                for (int i = 0; i < word.length(); i++) {
                    char a = Character.toLowerCase(word.charAt(i));
                    if ((a == 'e' && i <= word.length() - 2) || (isVowel(a))) {
                        countSyllable++;
                        wordsylls++;
                        i++;
                    }
                }
                countSyllable += wordsylls == 0 ? 1 : 0;
            }
        }
        return countSyllable;
    }
    private int countPolysyllables(String[] input) {
        int polysyllables = 0;
        for (String str: input) {
            String[] temp = str.split("\\b\\s|\\b\u00a0");
            for (String word : temp) {
                int wordsylls = 0;
                for (int i = 0; i < word.length(); i++) {
                    char a = Character.toLowerCase(word.charAt(i));
                    if ((a == 'e' && i <= word.length() - 2) || (isVowel(a))) {
                        wordsylls++;
                        i++;
                    }
                }
                polysyllables += wordsylls > 2 ? 1 : 0;
            }
        }
        return polysyllables;
    }
    public void countScore(String namePathFile) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String inputString = load(namePathFile);
        int countChar = countCharInText(inputString);
        String[] arrayInputString = inputString.replaceAll("[,()]","").split("[.!?]".trim());
        int countWordsInSentence = countWordInText(arrayInputString);
        int countSyllable = countSyllableInText(arrayInputString);
        int polysyllables = countPolysyllables(arrayInputString);
        double automatedReadabilityIndex = 4.71 * ((double) countChar/countWordsInSentence) + 0.5 * ((double)countWordsInSentence/arrayInputString.length) - 21.43;
        double fleschKincaid = 0.39 * ((double)countWordsInSentence/arrayInputString.length) + 11.8 * ((double)countSyllable/countWordsInSentence) - 15.59;
        double simpleMeasureGobbledygook = 1.043 * Math.sqrt(polysyllables * ((double)30/arrayInputString.length)) + 3.1291;
        double colemanLiau = 0.0588 * countChar / countWordsInSentence * 100 - 0.296 * arrayInputString.length / countWordsInSentence * 100 - 15.8;
        double resultAge = 0;
        System.out.println("The text is: " + inputString);
        System.out.println();
        System.out.println("Words: " + countWordsInSentence);
        System.out.println("Sentences: " + arrayInputString.length);
        System.out.println("Characters: " + countChar);
        System.out.println("Syllables: " + countSyllable);
        System.out.println("Polysyllables: : " + polysyllables);
        System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all):");
        String[] grades = {"6","7","9","10","11","12","13","14","15","16","17","18","24","25"};
        //String choose = scanner.next();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        switch (br.readLine()) {
            case "ARI":
                System.out.printf("Automated Readability Index: %.02f (about %s  year olds).", automatedReadabilityIndex, (grades[(int)Math.round(automatedReadabilityIndex-1)]));
                resultAge += Integer.parseInt(grades[(int)Math.round(automatedReadabilityIndex-1)]);
                break;
            case "FK":
                System.out.printf("Flesch–Kincaid readability tests: %.02f (about %s  year olds).",fleschKincaid,(grades[(int)Math.round(fleschKincaid-1)]));
                resultAge += Integer.parseInt(grades[(int)Math.round(fleschKincaid-1)]);
                break;
            case "SMOG":
                System.out.printf("Simple Measure of Gobbledygook: %.02f (about %s  year olds).",simpleMeasureGobbledygook,(grades[(int)Math.round(simpleMeasureGobbledygook-1)]));
                resultAge += Integer.parseInt(grades[(int)Math.round(simpleMeasureGobbledygook-1)]);
                break;
            case "CL":
                System.out.printf("Coleman–Liau index: %.02f (about %s  year olds).",colemanLiau,(grades[(int)Math.round(colemanLiau-1)]));
                resultAge += Integer.parseInt(grades[(int)Math.round(colemanLiau-1)]);
                break;
            case "all":
                System.out.printf("Automated Readability Index: %.02f (about %s year olds).\n", automatedReadabilityIndex, (grades[(int)Math.round(automatedReadabilityIndex-1)]));
                System.out.printf("Flesch–Kincaid readability tests: %.02f (about %s year olds).\n",fleschKincaid,(grades[(int)Math.round(fleschKincaid-1)]));
                System.out.printf("Simple Measure of Gobbledygook: %.02f (about %s year olds).\n", simpleMeasureGobbledygook,(grades[(int)Math.round(simpleMeasureGobbledygook-1)]));
                System.out.printf("Coleman–Liau index: %.02f (about %s year olds).\n",colemanLiau,(grades[(int)Math.round(colemanLiau-1)]));
                resultAge += Integer.parseInt(grades[(int)Math.round(automatedReadabilityIndex-1)]);
                resultAge += Integer.parseInt(grades[(int)Math.round(fleschKincaid-1)]);
                resultAge += Integer.parseInt(grades[(int)Math.round(simpleMeasureGobbledygook-1)]);
                resultAge += Integer.parseInt(grades[(int)Math.round(colemanLiau-1)]);
                break;
        }
        resultAge /= 4;
        System.out.printf("This text should be understood in average by %.02f year olds",resultAge);
    }
}
