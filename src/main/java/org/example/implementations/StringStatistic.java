package org.example.implementations;

import org.example.Constants;
import org.example.interfaces.Statistic;

public class StringStatistic implements Statistic<String> {
    private long counter;
    private String shortString;
    private String longString;
    private final String statisticPrintMode;

    public StringStatistic(String mode) {
        this.counter = Constants.START_LONG_VALUE;
        this.shortString = Constants.START_STRING_VALUE;
        this.longString = Constants.START_STRING_VALUE;
        this.statisticPrintMode = mode;
    }

    @Override
    public void addItem(String s) {
        increaseCounter();
        setMin(s);
        setMax(s);
    }

    @Override
    public void printStatistic() {
        System.out.println("-----------Статистика string-файла-----------");
        if (this.statisticPrintMode.equals(Constants.SHORT_STATISTIC_MODE))
            printShortStatistic();
        else
            printFullStatistic();
    }

    private void setMax(String s) {
        if (!longString.equals(Constants.START_STRING_VALUE)){
            if (s.length() >  longString.length()){
                longString = s;
            }
            return;
        }
        longString = s;
    }

    private void setMin(String s) {
        if (!shortString.equals(Constants.START_STRING_VALUE)){
            if (s.length() < shortString.length()){
                shortString = s;
            }
            return;
        }
        shortString = s;
    }

    private void increaseCounter() {
        counter++;
    }

    private void printFullStatistic() {
        System.out.println("Всего элементов: " + counter);
        System.out.println("Самый длинный элемент: " + longString);
        System.out.println("Самый короткий элемент: " + shortString);
    }

    private void printShortStatistic() {
        System.out.println("Всего элементов: " + counter);
    }
}
