package al_3.lab;

import java.util.Iterator;

public abstract class Node<T> {
   protected Node<T> parent;
   protected T data;

   public abstract Iterator<? extends Node<T>> children();

   public void setParent(Node<T> n) {
      parent = n;
   }

   public void setData(T t) {
      data = t;
   }

   public T getData() {
      return data;
   }
}