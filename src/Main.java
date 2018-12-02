import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Challenge 1A solution: " + solveChallenge1A("input/input1A.txt"));
        System.out.println("Challenge 1B solution: " + solveChallenge1B("input/input1A.txt"));
        System.out.println("Challenge 2A solution: " + solveChallenge2A("input/input2A.txt"));
        System.out.println("Challenge 2B solution: " + solveChallenge2B("input/input2A.txt"));
    }

    public static int solveChallenge1A(String inputFileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(inputFileName));

        int sum = 0;

        for (String line : lines) {
            sum += Integer.parseInt(line);
        }

        return sum;
    }

    public static int solveChallenge1B(String inputFileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(inputFileName));

        Map<Integer,Integer> seen = new HashMap<Integer,Integer>();

        int sum = 0;
        seen.put(sum, seen.getOrDefault(sum, 0) + 1);

        // Is an infinite loop actually possible?
        while (true) {
            for (String line : lines) {
                sum += Integer.parseInt(line);
                if (seen.get(sum) != null) {
                    return sum;
                }
                seen.put(sum, seen.getOrDefault(sum, 0) + 1);
            }
        }
    }

    public static int solveChallenge2A(String inputFileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(inputFileName));
        int doubleCount = 0;
        int tripleCount = 0;

        for (String line : lines) {
            Map<Character,Integer> seen = new HashMap<Character, Integer>();
            for (int i = 0; i < line.length(); i++) {
                Character c = line.charAt(i);
                seen.put(c, seen.getOrDefault(c, 0) + 1);
            }
            boolean foundDouble = false;
            boolean foundTriple = false;

            for (Map.Entry<Character, Integer> entry : seen.entrySet()) {
                if (entry.getValue() == 2 && !foundDouble) {
                    foundDouble = true;
                    doubleCount++;
                }
                if (entry.getValue() == 3 && !foundTriple) {
                    foundTriple = true;
                    tripleCount++;
                }
            }
        }

        return doubleCount * tripleCount;
    }

    public static String solveChallenge2B(String inputFileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(inputFileName));

        int ID_SIZE = lines.get(0).length();

        // Is this N^2 or NlogN complexity? Can this be made more efficient?
        for (int i = 0; i < lines.size(); i++) {
            for (int j = i + 1; j < lines.size(); j++) {
                String strA = lines.get(i);
                String strB = lines.get(j);

                String similarChars = "";

                for (int a = 0; a < strA.length(); a++) {
                    if (strA.charAt(a) == strB.charAt(a)) {
                        similarChars += strA.charAt(a);
                    }
                }
                if (similarChars.length() == ID_SIZE - 1) {
                    return similarChars;
                }
            }
        }

        // Should never be reached if input was valid
        return "invalid";
    }
}

