package day02;

import java.io.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;


public class Solution {

    String fileName = "src/day02/input.txt";
    ArrayList<SimpleEntry<String, Integer>> commands = new ArrayList<>();

    public void readFile() {
        try {
            File file = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                commands.add(new SimpleEntry<>(parts[0], Integer.valueOf(parts[1])));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int star1() {
        int horizontal = 0, depth = 0;
        for (SimpleEntry<String, Integer> element : commands) {
            String command = element.getKey();
            int value = element.getValue();

            if (command.equals("forward")) horizontal += value;
            else if (command.equals("down")) depth += value;
            else depth -= value;
        }
        return horizontal * depth;
    }

    public int star2() {
        int horizontal = 0, depth = 0, aim = 0;
        for (SimpleEntry<String, Integer> element : commands) {
            String command = element.getKey();
            int value = element.getValue();

            if (command.equals("forward")) {
                horizontal += value;
                depth += aim * value;
            } else if (command.equals("down")) aim += value;
            else aim -= value;
        }
        return horizontal * depth;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.readFile();
        System.out.println(solution.star1());
        System.out.println(solution.star2());
    }
}
