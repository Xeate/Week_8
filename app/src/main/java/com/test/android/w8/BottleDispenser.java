package com.test.android.w8;

public class BottleDispenser {

    private int bottles;

    private float money;

    //Luodaan singleton
    private static BottleDispenser bottledispenser = new BottleDispenser();

    private BottleDispenser() {

        bottles = 5;

        money = 0;
    }

    public static BottleDispenser getInstance() {
        return bottledispenser;
    }

    public int getBottles() {
        return bottles;
    }

    public void setBottles() {
        bottles -= 1;
    }

    public void addMoney(int add) {

        money += add;

        System.out.println("Klink! Added more money!");

    }

    public int buyBottle(String name, double price) {

        if (bottles == 0) {

            System.out.println("Unlucky keissi. Pullot loppu!");
            return 0;
        }

        else if (money < price) {

            System.out.println("Add money first!");
            return 0;

        } else {

            System.out.println("KACHUNK! " + name + " came out of the dispenser!");
            money -= price;
            return 1;
        }
    }

    public void returnMoney() {

        System.out.print("Klink klink. Money came out! You got ");
        System.out.printf("%.2f", money);
        System.out.println("€ back");

        money = 0;

    }
}