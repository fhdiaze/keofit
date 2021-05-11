package keo.fit.market;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Main s = new Main();
        s.execute();
    }

    private void execute() throws FileNotFoundException {
        //File file = new File(getClass().getResource("input.txt").getFile());
        Scanner scanner = new Scanner(System.in);

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
    }

    private class Problem {
        private Item[] objects;
        private int[] capacities;
        private Map<String, Integer> loads;

        public Problem(Item[] objects, int[] capacities) {
            this.objects = objects;
            this.capacities = capacities;
            this.loads = new HashMap<>();
        }

        public int solve() {
            int totalWeight = 0;

            for (int c : capacities) {
                totalWeight += this.loadPerson(objects, c, 0);
            }

            return totalWeight;
        }

        public int loadPerson(Item[] objects, int capacity, int start) {
            if (start == objects.length || capacity == 0) {
                return 0;
            }

            String key = String.format("%d,%d", start, capacity);

            int value = this.loads.getOrDefault(key, -1);

            if (value != -1) {
                return value;
            }

            int price = objects[start].getPrice();
            int weight = objects[start].getWeight();
            int tookValue = 0;
            int notTookValue = loadPerson(objects, capacity, start + 1);

            if (weight <= capacity) {
                tookValue = price + loadPerson(objects, capacity - weight, start + 1);
            }
            
            value = Math.max(tookValue, notTookValue);

            this.loads.put(key, value);
            
            return value;
        }
    }
}