package keo.fit.even;

import java.io.File;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Main s = new Main();
        s.execute();
    }

    private void execute() throws FileNotFoundException {
        //File file = new File(getClass().getResource("input.txt").getFile());
        Scanner scanner = new Scanner(System.in);
        Problem problem = new Problem();

        int cases = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < cases; i++) {
            int length = Integer.parseInt(scanner.nextLine());
            String line = scanner.nextLine();
            String[] elements = line.split(" ");
            int[] array = Stream.of(elements).mapToInt(Integer::parseInt).toArray();

            int solution = problem.solve(array);

            System.out.println(solution);
        }

        scanner.close();
    }

    private class Problem {
        public int solve(int[] array) {
            int moves = 0;
            int badEvens = 0;
            int badOdds = 0;

            for (int i = 0; i < array.length; i++) {
                if (i % 2 == 0) {
                    if (array[i] % 2 == 1) {
                        badEvens++;
                    }
                } else if (array[i] % 2 == 0) {
                    badOdds++;
                }
            }
            
            moves = badEvens == badOdds ? badOdds : -1;

            return moves;
        }
    }
}