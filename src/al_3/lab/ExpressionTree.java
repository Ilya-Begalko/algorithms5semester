package al_3.lab;

import java.util.ArrayList;
public abstract class ExpressionTree extends BinaryTree<String> {
   public ExpressionTree() {
      super();
   }
   
   public final String postfix() {
      String ans = "";
      ArrayList<Node<String>> l = postOrder(); 
      for (Node<String> b:l) ans += b.getData() + " ";
      return ans;
   }
}