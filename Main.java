package com.company;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static class CheckExp{
        private StringBuilder exp;
        private final List<Integer> qusIndex = new ArrayList<>();
        final String start = "";
        private int countSolutions = 1;

        public int getQusCount(){
            return qusIndex.size();
        }
        public void setQusCount(int count){
            countSolutions = count;
        }

        public void setExp(String str) {
            exp = new StringBuilder(str);
            for (int i = 0; i < exp.length(); i++) {
                if (exp.charAt(i) == '?'){
                    qusIndex.add(i);
                }
            }
        }

        public boolean check(){
            String[] arr = exp.toString().split("[^\\d?]+");
            if ( (arr[0].charAt(0) == '0' || arr[1].charAt(0) == '0'||arr[2].charAt(0) == '0')){
                return false;
            }
            return (Integer.parseInt(arr[0]) + Integer.parseInt(arr[1]) == Integer.parseInt(arr[2]));

        }

        public void searhSolusion(int ind, String start){
            StringBuilder str = new StringBuilder(start);
            if (countSolutions > 0) {
                for (int i = 0; i < 10; ++i) {
                    str.append(i);
                    if (ind == 0) {
                        for (int j = 0; j < qusIndex.size(); j++) {
                            exp.setCharAt(qusIndex.get(j), str.charAt(j));
                        }
                        if (check()) {
                            System.out.println(exp);
                            --countSolutions;
                            break;
                        }
                    }
                    else  {
                        searhSolusion(ind - 1, str.toString());
                        str = new StringBuilder(start);
                    }
                }
            }
        }

        public void searhSolusion(){
            int ind = qusIndex.size();
            StringBuilder str = new StringBuilder(start);
            if (countSolutions > 0) {
                for (int i = 0; i < 10; ++i) {
                    str.append(i);
                    if (ind == 0) {
                        for (int j = 0; j < qusIndex.size(); j++) {
                            exp.setCharAt(qusIndex.get(j), str.charAt(j));
                        }
                        if (check()) {
                            System.out.println(exp);
                            --countSolutions;
                            break;
                        }
                    }
                    else  {
                        searhSolusion(ind - 1, str.toString());
                        str = new StringBuilder(start);
                    }
                }
            }
            if(countSolutions != 0) {
                System.out.println("Нет решений!");
            }
        }
    }

    public static void main(String[] args) {
        //Вычислить n-ое треугольного число(сумма чисел от 1 до n),
        // n! (произведение чисел от 1 до n)
        System.out.println(tNum((int)input("Введите n, для вычисление его треугольного числа: ")));
        System.out.println(factorial((int)input("Введите n, для вычисление его факториала: ")));
        //Вывести все простые числа от 1 до 1000
        System.out.println(primeNumTo((int)input("Введите n до которого нужно найти простые числа: ")));
        //Реализовать простой калькулятор
        calculator();
        //*+Задано уравнение вида q + w = e, q, w, e >= 0.
        // Некоторые цифры могут быть заменены знаком вопроса, например 2? + ?5 = 69.
        // Требуется восстановить выражение до верного равенства.
        // Предложить хотя бы одно решение или сообщить, что его нет.
        String exp = "9? + ??? = ???8";
        System.out.println("Дано уравнение: " + exp);
        CheckExp ch = new CheckExp();
        ch.setExp(exp);
        ch.searhSolusion();
    }

    public static double input(String in) {
        System.out.print(in);
        return (new Scanner(System.in)).nextDouble();
    }
    //Треугольное число вычисляется без рекурсии
    public static int tNum(int n) {
        return ((n) * (n + 1)/2);
    }

    //Вычисление факториала числа
    public static long factorial(int n) {
        long result = 1;
        while (n>1) {
            result *= n;
            --n;
        }
        return result;
    }
    //Нахождение простых чисел
    public static String primeNumTo(int n) {
        if (n < 3) {
            return String.valueOf(n);
        }
        int nextNumber = 3;
        List<Integer> primeList = new ArrayList<>();
        primeList.add(2);
        while (nextNumber < n + 1) {
            boolean isPrimeNum = true;
            for (Integer integer : primeList) {
                if (nextNumber % integer == 0) {
                    isPrimeNum = false;
                    break;
                }
            }
            if (isPrimeNum) {
                primeList.add(nextNumber);
            }
            ++nextNumber;
        }
        primeList.add(0,1);
        return String.valueOf(primeList);
    }
    //Калькулятор
    public static void calculator(){
        double x = input("Введите первое число: ");
        System.out.print("Введите операцию: ");
        String str = (new Scanner(System.in)).next();
        double y = input("Введите второе число: ");
        str = switch (str){
            case "/" -> x+" / "+y+" = "+ (x/y);
            case "*" -> x+" * "+y+" = "+ (x*y);
            case "+" -> x+" + "+y+" = "+ (x+y);
            case "-" -> x+" - "+y+" = "+ (x-y);
            case "%" -> x+" % "+y+" = "+ (x%y);
            default -> "Некорректный ввод!";
        };
        System.out.println(str);
    }

}


