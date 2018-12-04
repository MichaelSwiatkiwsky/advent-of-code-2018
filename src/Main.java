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
        System.out.println("Challenge 3A solution: " + solveChallenge3A("input/input3A.txt"));
        System.out.println("Challenge 3B solution: " + solveChallenge3B("input/input3A.txt"));
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

                int mismatchCount = 0;
                for (int a = 0; a < strA.length(); a++) {
                    if (strA.charAt(a) == strB.charAt(a)) {
                        similarChars += strA.charAt(a);
                    }
                    else {
                        mismatchCount++;
                        if (mismatchCount > 1) {
                            break; // No need to check rest of string if already impossible to satisfy conditions
                        }
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

    public static int solveChallenge3A(String inputFileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(inputFileName));

        Map<Integer, Map<Integer, Integer>> claimed = new HashMap<>();

        for (String line : lines) {
            String[] parts = line.split(" ");

            String[] coords = parts[2].split(",");
            int xPos = Integer.parseInt(coords[0]);
            int yPos = Integer.parseInt(coords[1].substring(0, coords[1].length() - 1));

            String[] dimensions = parts[3].split("x");
            int width = Integer.parseInt(dimensions[0]);
            int height = Integer.parseInt(dimensions[1]);

            for (int j = 0; j < height; j++) {
                for (int i = 0; i < width; i++) {
                    int x = xPos + i;
                    int y = yPos + j;
                    if (claimed.get(x) == null) {
                        claimed.put(x, new HashMap<>());
                    }

                    claimed.get(x).put(y, claimed.get(x).getOrDefault(y, 0) + 1);
                }
            }
        }

        int numOverlappingSquares = 0;

        for (Map<Integer, Integer> innerMap : claimed.values()) {
            for (int coordCount : innerMap.values()) {
                if (coordCount > 1) {
                    numOverlappingSquares++;
                }
            }
        }

        return numOverlappingSquares;
    }

    /** This works, but it is a "dumb" solution - essentially just brute force. There is surely a smarter way
     * to calculate overlaps mathematically without relying on a hashmap of "seen" coordinates.
     *
     * An alternate approach could be to compare each rectangle against each other and check mathematically if they
     * overlap (based on xPos, yPos, width and height) but that would also require multiple passes of list. I feel like
     * there is probably a way to do it with just one pass - maybe by keeping track of perimeters.**/
    public static int solveChallenge3B(String inputFileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(inputFileName));

        Map<Integer, Map<Integer, Integer>> claimed = new HashMap<>();

        for (String line : lines) {
            String[] parts = line.split(" ");

            String[] coords = parts[2].split(",");
            int xPos = Integer.parseInt(coords[0]);
            int yPos = Integer.parseInt(coords[1].substring(0, coords[1].length() - 1));

            String[] dimensions = parts[3].split("x");
            int width = Integer.parseInt(dimensions[0]);
            int height = Integer.parseInt(dimensions[1]);

            for (int j = 0; j < height; j++) {
                for (int i = 0; i < width; i++) {
                    int x = xPos + i;
                    int y = yPos + j;
                    if (claimed.get(x) == null) {
                        claimed.put(x, new HashMap<>());
                    }

                    claimed.get(x).put(y, claimed.get(x).getOrDefault(y, 0) + 1);
                }
            }
        }

        for (int a = 0; a < lines.size(); a++) {
            String[] parts = lines.get(a).split(" ");

            String[] coords = parts[2].split(",");
            int xPos = Integer.parseInt(coords[0]);
            int yPos = Integer.parseInt(coords[1].substring(0, coords[1].length() - 1));

            String[] dimensions = parts[3].split("x");
            int width = Integer.parseInt(dimensions[0]);
            int height = Integer.parseInt(dimensions[1]);

            boolean noOverlaps = true;
            // By checking for noOverlaps we can break out of outer loop too once overlap found
            for (int j = 0; j < height && noOverlaps; j++) {
                for (int i = 0; i < width; i++) {
                    int x = xPos + i;
                    int y = yPos + j;
                    if (claimed.get(x).get(y) > 1) {
                        noOverlaps = false;
                        break; // Leave early when we know there is overlap
                    }
                }
            }
            if (noOverlaps) {
                return a + 1; // +1 because a starts at 0 but IDs start at 1
            }
        }

        // Should never reach here if input valid
        return -1;
    }
}

