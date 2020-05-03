package readability;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String inputPath = args[0];
        ReadabilityScore readabilityScore = new ReadabilityScore();
        readabilityScore.countScore(inputPath);
     }
}
