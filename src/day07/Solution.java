package day07;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    String fileName = "src/day07/input.txt";
    ArrayList<Integer> numbers = new ArrayList<>();

    public void readFile() {
        try {
            File file = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            for (String number : line.split(",")) {
                numbers.add(Integer.valueOf(number));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int star1() {
        int minimum = Integer.MAX_VALUE;
        Set<Integer> visited = new HashSet<>();
        for (int finalPosition : numbers) {
            int cost = 0;
            if (visited.contains(finalPosition)) continue;
            visited.add(finalPosition);
            for (int crabPosition : numbers) {
                cost += Math.abs(finalPosition - crabPosition);
            }
            if (cost < minimum) minimum = cost;
        }
        return minimum;
    }

    public int star2() {
        int minimum = Integer.MAX_VALUE;
        Set<Integer> visited = new HashSet<>();
        for (int finalPosition : numbers) {
            int cost = 0;
            if (visited.contains(finalPosition)) continue;
            visited.add(finalPosition);
            for (int crabPosition : numbers) {
                int steps = Math.abs(finalPosition - crabPosition);
                cost += (steps * (steps + 1)) / 2;
            }
            if (cost < minimum) minimum = cost;
        }
        return minimum;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.readFile();
        System.out.println(solution.star1());
        System.out.println(solution.star2());
    }
}
