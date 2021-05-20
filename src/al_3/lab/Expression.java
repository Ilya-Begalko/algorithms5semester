package al_3.lab;

import java.util.*;

public class Expression extends ExpressionTree {
  public BNode<String> node;
  public BNode<String> node1;
  public BNode<String> node2;

   public Expression(String s) {
      Stack<BNode<String>> stack = new Stack<>();
      s = s.replace(" ", ""); //удалить исходные пробелы, чтобы не создавать повторяющиеся пробелы
      s = addSpace(s); //добавить индикатор, где добавить пробел для разделения строки
      s = s.replace(".", " "); //добавить пробел, где был добавлен индикатор
      String[] ex = s.split(" "); //разделить строку на части
      String[] post = Infix(ex); //превратить инфиксное выражение в постфикс, вызвав другой метод

      for(int i = 0; i<post.length; i++){
         if(!isOperator(post[i])){
            node = new BNode<String>(post[i], null, null, null); //если T не является оператором, помещается в стек как узел
            stack.push(node);
         }
         else{
            node = new BNode<String>(post[i], null, null, null); //если T - оператор, удалите два узла из стека и установите их как левый и правый лист
            node1 = stack.pop(); //вытащить стек и установить его равным узлу
            node2 = stack.pop(); //снова вытащить стек и установить равным узлу
            node.setRight(node1); //затем установите правую часть узла оператора, когда появится первый узел
            node.setLeft(node2); //затем установите левую часть узла оператора, когда появится второй узел
            stack.push(node); //засунуть дерево в стек
         }
      }
      node = stack.pop(); //вытолкнуть верхний узел стека
      while(!stack.isEmpty()){
         node = stack.pop();  
      }
      this.root = node; //установить корень дерева выражений равным последнему узлу, извлеченному из стека
   }
   

   public String addSpace(String s){ //добавить индикаторы для добавления пробелов
      if(s.contains("-")){
         s = s.replaceAll("-", ".-.");
      }
      if(s.contains("+")){
         s = s.replace("+", ".+.");
      }
      if(s.contains("*")){
         s = s.replace("*", ".*.");
      }
      if(s.contains("/")){
         s = s.replace("/", "./.");
      }
      if(s.contains("(")){
         s = s.replace("(", "(.");  
      }
      if(s.contains(")")){
         s = s.replace(")", ".)");  
      }
      return s; //вернуть измененную строку
   }
   
   public boolean isOperator(String c){ //проверьте, является ли символ оператором
      if(c.equals("-") || c.equals("*") || c.equals("+") || c.equals("/")){
         return true;  
      }
      return false;
   }
   
   public boolean isParentheses(String c){ //проверьте, является ли символ круглыми скобками
      if(c.equals("(") || c.equals(")")){
         return true;  
      }
      return false;
   }
   
   public String[] Infix(String[] ex){ //превратить инфикс в постфикс
      Stack<String> s = new Stack<String>();
      ArrayList<String> word = new ArrayList<>();
      for(int i = 0; i<ex.length; i++){ 
         if(isOperator(ex[i]) == false && isParentheses(ex[i]) == false){ //если строка не является оператором или скобками, добавьте ее в arrayylist
            word.add(ex[i]);
         }
         else if(ex[i].equals("(")){ //если строка является открытой круглой скобкой, добавьте ее в стек
            s.push(ex[i]);  
         }
         else if(ex[i].equals(")")){ //если строка является закрывающей круглой скобкой, выталкивайте стек, пока не встретите открывающую скобку, или если стек не пуст
            while(!s.peek().equals("(") && !s.isEmpty()){
               word.add(s.pop()); //добавить операторы из стека в Arraylist
            }
            s.pop(); //вытащить / удалить открытую скобку из стека
         }
         else if(isOperator(ex[i]) == true){
            if (s.isEmpty() || outPrec(ex[i]) > inPrec(s.peek())) { //если оператор вне стека имеет более высокий приоритет, чем оператор
               s.push(ex[i]);                                       //внутри стека мы добавляем тот, который находится снаружи, в Arraylist
            } 
            else{
               while(!s.isEmpty() && outPrec(ex[i]) < inPrec(s.peek())){ //если оператор вне стека имеет меньший приоритет, то мы
                  word.add(s.pop());                                    //вытащить оператор из стека и добавить его в arraylist
               }                                                         //оператор внутри стека с меньшим приоритетом или до тех пор, пока стек не станет пустым.
               s.push(ex[i]);                                            //наконец, мы помещаем оператор вне стека в стек
            }
         }
      }
      while(!s.isEmpty()){ //если стек не пуст, добавьте остальную часть стека в arrayylist
         word.add(s.pop());
      }
      String[] retword = new String[word.size()];
      for(int j = 0; j<word.size(); j++){ //скопировать arrayylist в массив строк
         retword[j] = word.get(j);
      }
      return retword; //вернуть массив строк
   }
   
   //функция для возврата значения приоритета
   //если оператор находится внутри стека
  public static int inPrec(String input){
      switch (input){ 
        case "+": 
        case "-": 
            return 2; 
        case "*": 
        case "/": 
            return 4; 
      } 
      return 0;  
   } 
  
   // функция для возврата значения приоритета
   // если оператор присутствует вне стека.
  public static int outPrec(String input) {
      switch (input) { 
        case "+": 
        case "-": 
            return 1; 
        case "*": 
        case "/": 
            return 3; 
      } 
      return 0; 
   } 
}
