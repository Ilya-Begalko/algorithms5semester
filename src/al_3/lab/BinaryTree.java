package al_3.lab;

import java.util.ArrayList;
public  class BinaryTree<T> extends Tree<T> {
   public BinaryTree() {
      super();
   }

   public ArrayList<BNode<T>> inOrder() {
      ArrayList<BNode<T>> answer = new ArrayList<BNode<T>>();
      inOrder((BNode<T>) root(), answer);
      return answer;
   }

   public void inOrder(BNode<T> n, ArrayList<BNode<T>> v) {
      if (n == null) return;
      inOrder(n.getLeft(), v);
      v.add(n);
      inOrder(n.getRight(), v);
   }
}