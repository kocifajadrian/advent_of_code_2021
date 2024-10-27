// problem: https://adventofcode.com/2021/day/1


package day01;

import java.io.*;
import java.util.ArrayList;


public class Solution {

    String fileName = "src/day01/input.txt";
    ArrayList<Integer> numbers = new ArrayList<>();

    public void readFile() {
        try {
            File file = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                numbers.add(Integer.valueOf(line));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int star1() {
        int counter = 0;
        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i - 1) < numbers.get(i)) counter++;
        }
        return counter;
    }

    public int star2() {
        int counter = 0;
        for (int i = 0; i < numbers.size() - 3; i++) {
            if (numbers.get(i) < numbers.get(i + 3)) counter++;
        }
        return counter;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.readFile();
        System.out.println(solution.star1());
        System.out.println(solution.star2());
    }
}
