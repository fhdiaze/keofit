package keo.fit.market;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Main s = new Main();
        s.execute();
    }

    private void execute() throws FileNotFoundException {
        File file = new File(getClass().getResource("input.txt").getFile());
        Scanner scanner = new Scanner(file);

        int cases = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < cases; i++) {
            int objects = Integer.parseInt(scanner.nextLine());
            Item[] items = new Item[objects];

            for (int j = 0; j < objects; j++) {
                String line = scanner.nextLine();
                String[] fields = line.split(" ");
                items[j] = new Item(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]));
            }

            int people = Integer.parseInt(scanner.nextLine());
            int[] capacities = new int[people];

            for (int p = 0; p < people; p++) {
                capacities[p] = Integer.parseInt(scanner.nextLine());
            }

            Problem problem = new Problem(items, capacities);
            int solution = problem.solve();

            System.out.println(solution);
        }

        scanner.close();
    }

    private class Item {
        private int price;
        private int weight;

        public Item(int price, int weight) {
            this.price = price;
            this.weight = weight;
        }

        public int getPrice() {
            return this.price;
        }

        public int getWeight() {
            return this.weight;
        }

        public double getRate() {
            return price * 1.0 / weight;
        }
    }

    private class Problem {
        private Item[] objects;
        private int[] capacities;

        public Problem(Item[] objects, int[] capacities) {
            Arrays.sort(objects, Comparator.comparing(Item::getRate).reversed());
            this.objects = objects;
            this.capacities = capacities;
        }

        public int solve() {
            int totalWeight = 0;

            for (int c : capacities) {
                totalWeight += this.loadPerson(objects, c);
            }

            return totalWeight;
        }

        public int loadPerson(Item[] objects, int capacity) {
            int value = 0;

            for (Item o : objects) {
                if (o.getWeight() <= capacity) {
                    value += o.getPrice();
                    capacity -= o.getWeight();
                }
            }

            return value;
        }
    }
}