package al_3.lab;

import java.util.Scanner;

public  class Utility {
   public static void main(String[] args) {
      Scanner scnr = new Scanner(System.in);
      Expression expr;
      String testInput;
      testInput = scnr.nextLine();
      expr = new Expression(testInput);
      System.out.println("Postfix: " + expr.postfix());
   }
}