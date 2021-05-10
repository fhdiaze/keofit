package keo.fit.digits;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Main s = new Main();
        s.execute();
    }

    private void execute() throws FileNotFoundException {
        File file = new File(getClass().getResource("input.txt").getFile());
        Scanner scanner = new Scanner(file);

        String line = scanner.nextLine();
        String[] fields = line.split(" ");
        int length = Integer.parseInt(fields[0]);
        int sum = Integer.parseInt(fields[1]);

        Problem problem = new Problem();
        String[] solution = problem.solve(length, sum);

        System.out.println(String.join(" ", solution));

        scanner.close();
    }

    private class Problem {
        public String[] solve(int length, int targetSum) {
            String max, min;
            int nines = targetSum / 9;
            int lastDigit = targetSum % 9;
            int digits = nines + (lastDigit > 0 ? 1 : 0);

            if (digits <= length && targetSum > 0) {
                StringBuilder builder = new StringBuilder(length);
                builder.append("9".repeat(nines));

                if (lastDigit > 0) {
                    builder.append(lastDigit);
                }

                int padding = length - digits;
                String maxPad = "0".repeat(padding);
                String minPad = maxPad.replaceFirst("0", "1");

                max = builder.toString().concat(maxPad);

                if (padding > 0) {
                    builder = builder.reverse();
                    int digit = Character.getNumericValue(builder.charAt(0)) - 1;
                    min = minPad + builder.replace(0, 1, String.valueOf(digit)).toString();
                } else {
                    min = builder.reverse().toString();
                }
            } else if(targetSum == 0 && length == 1) {
                max = "0";
                min = "0";
            } else {
                max = "-1";
                min = "-1";
            }

            return new String[] { min, max };
        }
    }
}