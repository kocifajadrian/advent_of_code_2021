package day03;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class Solution {

    String fileName = "src/day03/input.txt";
    ArrayList<String> binaryNumbers = new ArrayList<>();

    public void readFile() {
        try {
            File file = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                binaryNumbers.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int binaryToDecimal(String binary) {
        int result = 0;
        for (int i = 0; i < binary.length(); i++) {
            if (binary.charAt(i) == '1') {
                result += (int) Math.pow(2, binary.length() - i - 1);
            }
        }
        return result;
    }

    public int countAtIndex(Set<String> elements, int index) {
        int counter = 0;
        for (String element : elements) {
            if (element.charAt(index) == '1') counter++;
            else counter--;
        }
        return counter;
    }

    public Set<String> filter(Set<String> elements, int index, char character) {
        Set<String> filteredElements = new HashSet<>(elements);
        for (String element : elements) {
            if (element.charAt(index) != character) filteredElements.remove(element);
        }
        return filteredElements;
    }

    public int star1() {
        StringBuilder gamma = new StringBuilder();
        StringBuilder epsilon = new StringBuilder();
        for (int i = 0; i < binaryNumbers.getFirst().length(); i++) {
            int counter = countAtIndex(new HashSet<>(binaryNumbers), i);
            if (counter >= 0) {
                gamma.append("1");
                epsilon.append("0");
            } else {
                gamma.append("0");
                epsilon.append("1");
            }
        }
        return binaryToDecimal(String.valueOf(gamma)) * binaryToDecimal(String.valueOf(epsilon));
    }

    public int star2() {
        Set<String> oNumbers = new HashSet<>(binaryNumbers);
        Set<String> co2Numbers = new HashSet<>(binaryNumbers);

        for (int i = 0; i < binaryNumbers.getFirst().length(); i++) {
            int oCounter = countAtIndex(oNumbers, i);
            char oCharacter = oCounter < 0 ? '0' : '1';
            oNumbers = filter(oNumbers, i, oCharacter);
            if (oNumbers.size() == 1) break;
        }

        for (int i = 0; i < binaryNumbers.getFirst().length(); i++) {
            int co2Counter = countAtIndex(co2Numbers, i);
            char co2Character = co2Counter < 0 ? '1' : '0';
            co2Numbers = filter(co2Numbers, i, co2Character);
            if (co2Numbers.size() == 1) break;
        }
        
        return binaryToDecimal(oNumbers.iterator().next()) * binaryToDecimal(co2Numbers.iterator().next());
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.readFile();
        System.out.println(solution.star1());
        System.out.println(solution.star2());
    }
}
