package third.lab;

import third.lab.Node;
import java.util.ArrayList;
import java.util.Iterator;

public class Tree<T> {
   protected Node<T> root;
   public int size;

   public Tree() {
      root = null;
      size = 0;
   }

   public Node<T> root() {
      return root;
   }

   public Iterator<? extends Node<T>> children(Node<T> v) {
      return v.children();
   }

   public ArrayList<Node<T>> postOrder() {
      ArrayList<Node<T>> answer = new ArrayList<Node<T>>();
      postOrder(root(), answer);
      return answer;
   }

   public void postOrder(Node<T> n, ArrayList<Node<T>> v) {
      if (n == null)
         return;
      Iterator<? extends Node<T>> x = children(n);
      while (x.hasNext()) {
         Node<T> m = x.next();
         postOrder(m, v);
      }
      v.add(n);
   }
}