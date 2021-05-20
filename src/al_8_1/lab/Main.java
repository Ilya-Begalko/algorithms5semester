package al_8_1.lab;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] matrix = new int[m][n];
        int[][] matrix1 = new int[m][m];
        for(int[] elem : matrix1){
            Arrays.fill(elem, 0);
        }
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                matrix[i][j] = scanner.nextInt();
            }
        }
        Map<Vertex, Set<Integer>> map = new TreeMap<>(((Comparator<Vertex>) (o1, o2) -> -Integer.compare(o1.count, o2.count)).thenComparingInt(o -> o.value));
        for(int i = 0; i < n; i++){
            List<Integer> temp = new ArrayList<>();
            for(int j = 0; j < m; j++){
                if(matrix[j][i] == 1){
                    temp.add(j);
                }
            }
            for(int b = 0; b < temp.size(); b++){
                for(int c = b + 1; c < temp.size(); c++){
                    matrix1[temp.get(b)][temp.get(c)] = 1;
                    matrix1[temp.get(c)][temp.get(b)] = 1;
                }
            }
        }
        for(int i = 0; i < m; i++){
            Vertex vertex = new Vertex(i);
            Set<Integer> set = new HashSet<>();
            int count = 0;
            for(int j = 0; j < m; j++){
                if(matrix1[j][i] == 1){
                    count++;
                    set.add(j);
                }
            }
            vertex.count = count;
            map.put(vertex, set);
        }
        System.out.println(map.toString());
        List<Vertex> list = new ArrayList<>(map.keySet());
        list.get(0).color = 1;
        List<ArrayList<Vertex>> group = new ArrayList<>();
        group.add(new ArrayList<>());
        group.get(0).add(list.get(0));
        for(int i = 1; i <= m; i++){
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
//можно удалить
        System.out.println(group);
        for(int i = 0; i < m; i++){
            for(int j = 0; j < m; j++){
                System.out.print(matrix1[i][j] + " ");
            }
            System.out.println();
        }
    }
}
