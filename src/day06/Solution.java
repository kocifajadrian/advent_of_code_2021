// problem: https://adventofcode.com/2021/day/6


package day06;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Solution {

    public static int born = 9, reset = 7;
    String fileName = "src/day06/input.txt";
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

    public long computeOne(int n, int days) {
        long counter = 0;
        Map<Integer, Long> state = new HashMap<>();
        state.put(n, 1L);

        for (int i = 0; i < days; i++) {
            Map<Integer, Long> newState = new HashMap<>();
            for (Map.Entry<Integer, Long> element : state.entrySet()) {
                int key = element.getKey();
                long value = element.getValue();
                int newKey = key - 1 < 0 ? reset - 1 : key - 1;
                newState.computeIfPresent(newKey, (_, v) -> v + value);
                newState.putIfAbsent(newKey, value);
                if (key == 0) {
                    newState.computeIfPresent(born - 1, (_, v) -> v + value);
                    newState.putIfAbsent(born - 1, value);
                }
            }
            state = new HashMap<>(newState);
        }

        for (Long value : state.values()) {
            counter += value;
        }

        return counter;
    }

    public long computeAll(int days) {
        Map<Integer, Long> values = new HashMap<>();
        long counter = 0;
        for (Integer number : numbers) {
            if (!values.containsKey(number)) {
                values.put(number, computeOne(number, days));
            }
            counter += values.get(number);
        }
        return counter;
    }

    public long star1() {
        return computeAll(80);
    }

    public long star2() {
        return computeAll(256);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.readFile();
        System.out.println(solution.star1());
        System.out.println(solution.star2());
    }
}
