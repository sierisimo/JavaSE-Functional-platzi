package com.platzi.functional._10_chaining;

public class Chaining {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hola")
                .append("alumno")
                .append("de")
                .append("platzi");

        Chainer chainer = new Chainer();

        Chainer chainer2 = chainer.sayHi();
        Chainer chainer3 = chainer2.sayBye();
        chainer.sayHi().sayBye();
    }

    static class Chainer {
        public Chainer sayHi() {
            System.out.println("Hola");
            return this;
        }

        public Chainer sayBye() {
            System.out.println("Bye");
            return this;
        }
    }
}
