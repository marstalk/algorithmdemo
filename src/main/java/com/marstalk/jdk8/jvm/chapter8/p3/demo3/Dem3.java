package com.marstalk.jdk8.jvm.chapter8.p3.demo3;

public class Dem3 {
    public static void main(String[] args) {
        Food food = new Food();
        Food hotPot = new HotPot();
        Food steak = new Steak();

        Human human = new Human();
        human.eat(food);
        human.eat(hotPot);
        human.eat(steak);

        Human father = new Father();
        father.eat(food);
        father.eat(hotPot);
        father.eat(steak);

        Human son = new Son();
        son.eat(food);
        son.eat(hotPot);
        son.eat(steak);
        son.eat((HotPot)hotPot);
        son.eat((Steak)steak);
    }
}
class Food{
    @Override
    public String toString() {
        return "food";
    }
}
class HotPot extends Food{
    @Override
    public String toString() {
        return "hotpot";
    }
}
class Steak extends Food{
    @Override
    public String toString() {
        return "steak";
    }
}

class Human{
    public void eat(Food food){
        System.out.println("human eat food: " + food);
    }
    public void eat(HotPot hotPot){
        System.out.println("human eat hotpot: " + hotPot);
    }
    public void eat(Steak steak){
        System.out.println("human eat steak: " + steak);
    }
}
class Father extends Human{
    @Override
    public void eat(Food food) {
        System.out.println("father eat food: " + food);
    }
    @Override
    public void eat(HotPot hotPot) {
        System.out.println("father eat hotpot: " + hotPot);
    }
    @Override
    public void eat(Steak steak) {
        System.out.println("father eat steak: " + steak);
    }
}
class Son extends Father{
    @Override
    public void eat(Food food) {
        System.out.println("son eat food: " + food);
    }
    @Override
    public void eat(HotPot hotPot) {
        System.out.println("son eat hotpot: " + hotPot);
    }
    @Override
    public void eat(Steak steak) {
        System.out.println("son eat steak: " + steak);
    }
}
