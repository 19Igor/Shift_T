package org.example.implementations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Constants;
import org.example.interfaces.Statistic;

public class FloatStatistic implements Statistic<Float> {
    private static final Logger log = LogManager.getLogger(FloatStatistic.class);

    private long counter;
    private float min;
    private float max;
    private float sum;
    private double average;
    private final String statisticPrintMode;

    public FloatStatistic(String mode) {
        this.counter = Constants.START_LONG_VALUE;
        this.min = Constants.START_FLOAT_VALUE;
        this.max = Constants.START_FLOAT_VALUE;
        this.sum = Constants.START_FLOAT_VALUE;
        this.average = Constants.START_DOUBLE_VALUE;
        this.statisticPrintMode = mode;
    }

    @Override
    public void addItem(Float f) {
        increaseCounter();
        setMin(f);
        setMax(f);
        computeSum(f);
    }

    @Override
    public void printStatistic() {
        computeAverage();
        System.out.println("-----------Статистика float-файла-----------");
        if (statisticPrintMode.equals(Constants.SHORT_STATISTIC_MODE))
            printShortStatistic();
        else
            printFullStatistic();
    }

    private void computeAverage() {
        try{
            average = (double) sum / counter;
        }
        catch (ArithmeticException e){
            System.out.println("Деление на 0.");
            log.error("Деление на 0.", e);
        }
    }

    private void printShortStatistic() {
        System.out.println("Всего элементов: " + counter);
    }

    private void printFullStatistic() {
        System.out.println("Всего элементов: " + counter);
        System.out.println("Минимальный элемент: " + min);
        System.out.println("Максимальный элемент: " + max);
        System.out.println("Сумма: " + sum);

        System.out.println("Среднее значение: " + average);
    }

    private void computeSum(Float f) {
        this.sum += f;
    }

    private void setMax(Float f) {
        if (f > this.max)
            this.max = f;
    }

    private void setMin(Float f) {
        if (f < this.min){
            this.min = f;
        }
    }

    private void increaseCounter() {
        this.counter++;
    }
}
