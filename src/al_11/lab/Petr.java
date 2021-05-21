package al_11.lab;

import al_10.lab.Sampling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Petr {

    public static void main(String[] args) {
        HashMap<String, Integer> table = new HashMap<>() {
            {
                put("Карандаш", 12);
                put("Линейка", 20);
                put("Ластик", 1);
                put("Ручка", 10);
                put("Точилка", 13);
            }
        };
        ArrayList<Map.Entry<String, Integer>> buyList = new ArrayList<>() {
            {
                add(Map.entry("Карандаш", 2));
                add(Map.entry("Ручка", 1));
                add(Map.entry("Линейка", 1));
                add(Map.entry("Ластик", 2));
            }
        };

        List<String> indexes = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : buyList) {
            for (int i = 0; i < entry.getValue(); i++) {
                indexes.add(entry.getKey());
            }
        }
        List<List<String>> ar = new Sampling().subsets(indexes.toArray(String[]::new));
        int target = 40;
        List<String> toBuy = new ArrayList<>();
        int max = 0;
        for (List<String> a : ar) {
            int val = 0;
            for (String name : a) {
                val += table.get(name);
            }
            if (val <= target) {
                if (val > max) {
                    max = val;
                    toBuy = a;
                } else if (val == max) {
                    if (a.size() > toBuy.size()) {
                        toBuy = a;
                        max = val;
                    }
                }
            }
        }
        System.out.println(toBuy);
    }
}