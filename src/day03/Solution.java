package day03;

import java.io.*;
import java.util.ArrayList;


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

    public int star1() {
        StringBuilder gamma = new StringBuilder();
        StringBuilder epsilon = new StringBuilder();
        for (int i = 0; i < binaryNumbers.getFirst().length(); i++) {
            int counter = 0;
            for (String binary : binaryNumbers) {
                if (binary.charAt(i) == '1') counter++;
                else counter--;
            }
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
        return 0;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.readFile();
        System.out.println(solution.star1());
        System.out.println(solution.star2());
    }
}
