// problem: https://adventofcode.com/2021/day/5


package day05;

import java.io.*;
import java.util.*;


public class Solution {

    String fileName = "src/day05/input.txt";
    ArrayList<String[]> values = new ArrayList<>();

    public void readFile() {
        try {
            File file = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                values.add(line.replace(" -> ", ",").split(","));
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int compute(boolean withDiagonals) {
        Map<ArrayList<Integer>, Integer> map = new HashMap<>();
        int sx, sy, ex, ey;
        int counter = 0;

        for (String[] numbers : values) {
            sy = Integer.parseInt(numbers[0]);
            sx = Integer.parseInt(numbers[1]);
            ey = Integer.parseInt(numbers[2]);
            ex = Integer.parseInt(numbers[3]);

            if (sy == ey || sx == ex) {
                for (int y = Math.min(sy, ey); y < Math.max(sy, ey) + 1; y++) {
                    for (int x = Math.min(sx, ex); x < Math.max(sx, ex) + 1; x++) {
                        ArrayList<Integer> position = new ArrayList<>();
                        position.add(y);
                        position.add(x);
                        map.computeIfPresent(position, (_, value) -> value + 1);
                        map.putIfAbsent(position, 1);
                    }
                }
            } else if (withDiagonals) {
                int loops = Math.abs(sy - ey) + 1;
                int dy = ey - sy < 0 ? -1 : 1;
                int dx = ex - sx < 0 ? -1 : 1;
                for (int i = 0; i < loops; i++) {
                    ArrayList<Integer> position = new ArrayList<>();
                    position.add(sy + i * dy);
                    position.add(sx + i * dx);
                    map.computeIfPresent(position, (_, value) -> value + 1);
                    map.putIfAbsent(position, 1);
                }
            }
        }

        for (int value : map.values()) {
            if (value > 1) counter++;
        }

        return counter;
    }

    public int star1() {
        return compute(false);
    }

    public int star2() {
        return compute(true);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.readFile();
        System.out.println(solution.star1());
        System.out.println(solution.star2());
    }
}
