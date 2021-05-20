package eight_2.lab;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] matrix = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                matrix[i][j] = scanner.nextInt();
            }
        }
        Map<Vertex, Set<Integer>> map = new TreeMap<>(((Comparator<Vertex>) (o1, o2) -> -Integer.compare(o1.count, o2.count)).thenComparingInt(o -> o.value));
        for(int i = 0; i < n; i++){
            Vertex vertex = new Vertex(i);
            Set<Integer> set = new HashSet<>();
            int count = 0;
            for(int j = 0; j < n; j++){
                if(matrix[j][i] == 1){
                    count++;
                    set.add(j);
                }
            }
            vertex.count = count;
            map.put(vertex, set);
        }
       // System.out.println(map.toString());
        List<Vertex> list = new ArrayList<>(map.keySet());
        list.get(0).color = 1;
        List<ArrayList<Vertex>> group = new ArrayList<>();
        group.add(new ArrayList<>());
        group.get(0).add(list.get(0));
        for(int i = 1; i <= n; i++){
            for (Map.Entry<Vertex, Set<Integer>> elem : map.entrySet()) {
                if(elem.getKey().color == 0){
                    List<Vertex> conteains = group.get(i-1);
                    boolean flag = false;
                    for(int j = 0; j < conteains.size(); j++) {//!elem.getKey().equals(conteains.get(j))
                        if (elem.getValue().contains(conteains.get(j).value)){
                            flag = true;
                        }
                    }
                    if((conteains.isEmpty()) || (!flag && !conteains.contains(elem.getKey()))){
                        elem.getKey().color = i;
                        group.get(i-1).add(elem.getKey());
                    }
                }
            }
            group.add(new ArrayList<>());
        }
        for (int i = 0; i < group.size(); i++) {
            if(group.get(i).isEmpty()){
                group.remove(i);
                i--;
            }
        }
        System.out.println(group.size());
        System.out.println(group);
    }
}
