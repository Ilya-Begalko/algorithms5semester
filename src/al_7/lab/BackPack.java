package al_7.lab;

import java.util.ArrayList;
import java.util.List;

//**** Сложность O(n log(n)) ****//

class alghoritm {

    private List<Item> bestItems = null;

    private double maxW;
    private double bestPrice;

    //вычисляет общий вес набора предметов
    private double calcWeigth(List<Item> items) {
        double result = 0;
        for (Item item : items) {
            result += item.getWeigth();
        }
        return result;
    }

    // вычисляет общую стоимость набора предметов
    private double calcPrice(List<Item> items) {
        double result = 0;
        for (Item item : items) {
            result += item.getPrice();
        }
        return result;
    }

    //проверка, является ли данный набор лучшим решением задачи
    private void checkSet(List<Item> items) {
        if (this.bestItems == null) {
            if (this.calcWeigth(items) <= this.maxW) {
                this.bestItems = items;
                this.bestPrice = this.calcPrice(items);
            }
        } else {
            if (this.calcWeigth(items) <= this.maxW && this.calcPrice(items) > this.bestPrice) {
                this.bestItems = items;
                this.bestPrice = this.calcPrice(items);
            }
        }
    }

    //создание всех наборов перестановок значений
    public void makeAllSets(List<Item> items) {
        if (items.size() > 0) {
            this.checkSet(items);
        }
        for (int i = 0; i < items.size(); i++) {
            List<Item> newSet = new ArrayList<>(items);
            newSet.remove(i);
            makeAllSets(newSet);
        }
    }

    public alghoritm(double maxW) {
        this.maxW = maxW;
    }

    public List<Item> getBestItems() {
        return this.bestItems;
    }
}

class Item {

    private String name;
    private double weigth;
    private double price;

    public Item(String name, double weigth, double price) {
        this.name = name;
        this.weigth = weigth;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeigth() {
        return weigth;
    }

    public void setWeigth(double weigth) {
        this.weigth = weigth;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", weigth=" + weigth +
                ", price=" + price +
                '}';
    }
}

public class BackPack{

    public static void main(String[] args) {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Book", 1, 600));
        items.add(new Item("Binoculars", 2, 5000));
        items.add(new Item("First-aid kid", 4, 1500));
        items.add(new Item("Notebook", 2, 40000));
        items.add(new Item("Cauldron", 1, 500));

        alghoritm backpack = new alghoritm(8);
        backpack.makeAllSets(items);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(backpack.getBestItems());
    }

}