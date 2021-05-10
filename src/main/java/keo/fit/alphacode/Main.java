package keo.fit.alphacode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Main s = new Main();
        s.execute();
    }

    private void execute() throws FileNotFoundException {
        //File file = new File(getClass().getResource("input.txt").getFile());
        Scanner scanner = new Scanner(System.in);
        Problem problem = new Problem();

        String message = scanner.nextLine();

        while(!message.equals("0")) {
            int solution = problem.solve(message);

            System.out.println(solution);

            message = scanner.nextLine();
        }

        scanner.close();
    }

    private class Problem {
        public int solve(String message) {
            int singles = 1;
            int pairs = 0;

            for(int i=1; i<message.length(); i++) {
                char c = message.charAt(i);
                int oldSingles = singles;
                singles += isValidCode(c) ? pairs : 0;

                if(isValidCode(message.substring(i-1, i+1))) {
                    pairs = oldSingles;
                }
            }

            return singles + pairs;
        }

        public boolean isValidCode(String chars) {
            int code = Integer.parseInt(chars);

            return isValidCode(code);
        }
        
        public boolean isValidCode(char c) {
            int code = Character.getNumericValue(c);

            return isValidCode(code);
        }

        public boolean isValidCode(int code) {
            return code > 0 && code <= 26;
        }
    }
}