package com.marstalk.jdk8;

public class MethodDispatch {


    public static void main(String[] args) {
        Food hotPotFood = new HotPot();
        Food steakFood = new Steak();
        Human man1 = new Father();
        Human man2 = new Son();

        /**
         * 1, father eat food
         * 2, father eat food
         * 3, father eat hotpot
         * 4, father eat steak
         */
        man1.eat(hotPotFood);
        man1.eat(steakFood);
        man1.eat(new HotPot());
        man1.eat(new Steak());

        /**
         * 1, son eat food
         * 2, son eat food
         * 3, son eat hotpot
         * 4, son eat steak
         */
        man2.eat(hotPotFood);
        man2.eat(steakFood);
        man2.eat(new HotPot());
        man2.eat(new Steak());
    }
}

class Food{
    @Override public String toString() {
        return "food";
    }
}
class HotPot extends Food{
    @Override public String toString() {
        return "hotPot";
    }
}
class Steak extends Food{
    @Override public String toString() {
        return "steak";
    }
}
interface Human{
    void eat(Food food);
    void eat(HotPot hotPot);
    void eat(Steak steak);
}
class Father implements Human{
    @Override public void eat(Food food) {
        System.out.println("1 father eat " + food);
    }

    @Override public void eat(HotPot hotPot) {
        System.out.println("2 father eat " + hotPot);
    }

    @Override public void eat(Steak steak) {
        System.out.println("3 father eat " + steak);
    }
}
class Son extends Father implements Human{
    @Override public void eat(Food food) {
        System.out.println("1 son eat " + food);
    }

    @Override public void eat(HotPot hotPot) {
        System.out.println("2 eat " + hotPot);
    }

    @Override public void eat(Steak steak) {
        System.out.println("3 eat " + steak);
    }
}
