package day08;

import java.io.*;
import java.util.*;


public class Solution {

    String fileName = "src/day08/input.txt";
    ArrayList<ArrayList<ArrayList<String>>> information = new ArrayList<>();

    public void readFile() {
        try {
            File file = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] strings = line.split(" \\| ");
                information.add(new ArrayList<>());
                information.getLast().add(new ArrayList<>());
                for (String word : strings[0].split(" ")) {
                    information.getLast().getLast().add(word);
                }
                information.getLast().add(new ArrayList<>());
                for (String word : strings[1].split(" ")) {
                    information.getLast().getLast().add(word);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int star1() {
        int counter = 0;
        String lengths = "2347";
        for (ArrayList<ArrayList<String>> element : information) {
            for (String word1 : element.getFirst()) {
                if (!lengths.contains(String.valueOf(word1.length()))) continue;
                for (String word2 : element.getLast()) {
                    if (word1.length() != word2.length()) continue;
                    boolean condition = true;
                    for (char character : word2.toCharArray()) {
                        if (!word1.contains(String.valueOf(character))) {
                            condition = false;
                            break;
                        }
                    }
                    if (condition) counter++;
                }
            }
        }
        return counter;
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
