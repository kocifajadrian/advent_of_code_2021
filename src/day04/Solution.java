package day04;

import java.io.*;
import java.util.ArrayList;


public class Solution {

    String fileName = "src/day04/input.txt";
    ArrayList<Integer> drawNumbers = new ArrayList<>();
    ArrayList<Board> boards = new ArrayList<>();
    public static int boardSize = 5;

    public void readFile() {
        try {
            File file = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            for (String number : line.split(",")) {
                drawNumbers.add(Integer.valueOf(number));
            }
            reader.readLine();
            boards.add(new Board());
            ArrayList<ArrayList<Integer>> ns = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    boards.getLast().addNumbers(ns);
                    boards.add(new Board());
                    ns.clear();
                    continue;
                }
                ns.add(new ArrayList<>());
                line = line.replace("  ", " ").strip();
                for (String number : line.split(" ")) {
                    ns.getLast().add(Integer.valueOf(number));
                }
            }
            boards.getLast().addNumbers(ns);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int star1() {
        int counter = 0;
        for (int number : drawNumbers) {
            counter++;
            for (Board board : boards) {
                board.addDrawn(number);
                if (counter < boardSize) continue;
                if (board.win()) return board.score() * number;
            }
        }
        return 0;
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
