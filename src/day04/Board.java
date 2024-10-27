package day04;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class Board {

    public static int boardSize = 5;
    ArrayList<ArrayList<Integer>> numbers = null;
    Set<Integer> drawn = new HashSet<>();

    public void addNumbers(ArrayList<ArrayList<Integer>> board) {
        numbers = new ArrayList<>(board);
    }

    public void addDrawn(int number) {
        drawn.add(number);
    }

    public boolean win() {
        for (int i = 0; i < boardSize; i++) {
            boolean condition = true;
            for (int j = 0; j < boardSize; j++) {
                if (!drawn.contains(numbers.get(i).get(j))) {
                    condition = false;
                    break;
                }
            }
            if (condition) return true;

            condition = true;
            for (int j = 0; j < boardSize; j++) {
                if (!drawn.contains(numbers.get(j).get(i))) {
                    condition = false;
                    break;
                }
            }
            if (condition) return true;
        }
        return false;
    }

    public int score() {
        int result = 0;
        for (ArrayList<Integer> row : numbers) {
            for (Integer number : row) {
                if (!drawn.contains(number)) result += number;
            }
        }
        return result;
    }
}
