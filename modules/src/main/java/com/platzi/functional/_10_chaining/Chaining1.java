package com.platzi.functional._10_chaining;

public class Chaining1 {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hola")
                .append("Mundo")
                .append("De")
                .append("Mierda");
        System.out.println(stringBuilder);
        Chainer chainer = new Chainer();
        chainer.sayHi().sayBye();
        Chainer chainer1 = chainer.sayHi();
        Chainer chainer2 = chainer1.sayBye();
    }
    static class Chainer{
        public Chainer sayHi(){
            System.out.println("Hello");
            return this;
        }

        public Chainer sayBye(){
            System.out.println("Bye");
            return this;
        }
    }
}
