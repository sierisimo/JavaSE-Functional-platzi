package com.platzi.functional._09_defaults;

public class StringFunctions {
    @FunctionalInterface
    interface StringOperations{
        int getAmount();
        default void operate(String text){
            int x =  getAmount();
            while (x-- > 0){
                System.out.println(text);
            }
        }
    }
    @FunctionalInterface
    interface DoOperation{
        void take(String text);

        default void execute(int x, String text){
            while(x-- > 0){
                take(text);
            }
        }
    }
    public static void main(String[] args) {
        StringOperations six = () -> 6;
        six.operate("Alumno");
        DoOperation operateFive = System.out::println;
        operateFive.execute(5, "Nestor");
    }
}
