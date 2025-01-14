package org.example.implementations;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Constants;
import org.example.interfaces.Statistic;

@Getter
public class LongStatistic implements Statistic<Long> {
    private static final Logger log = LogManager.getLogger(LongStatistic.class);

    private long counter;
    private long min;
    private long max;
    private long sum;
    private double average;
    private final String statisticPrintMode;

    public LongStatistic(String mode) {
        this.counter = Constants.START_LONG_VALUE;
        this.min = Constants.START_LONG_VALUE;
        this.max = Constants.START_LONG_VALUE;
        this.sum = Constants.START_LONG_VALUE;
        this.average = Constants.START_DOUBLE_VALUE;
        this.statisticPrintMode = mode;
    }

    @Override
    public void addItem(Long l){
        increaseCounter();
        setMin(l);
        setMax(l);
        computeSum(l);
    }

    @Override
    public void printStatistic() {
        computeAverage();
        System.out.println("-----------Статистика integer-файла-----------");
        if (this.statisticPrintMode.equals(Constants.SHORT_STATISTIC_MODE))
            printShortStatistic();
        else
            printFullStatistic();
    }

    private void printFullStatistic() {
        System.out.println("Всего элементов: " + counter);
        System.out.println("Минимальный элемент: " + min);
        System.out.println("Максимальный элемент: " + max);
        System.out.println("Сумма: " + sum);
        computeAverage();
        System.out.println("Среднее значение: " + average);
    }

    private void printShortStatistic(){
        System.out.println("Всего элементов: " + counter);
    }

    private void computeAverage(){
        try{
            this.average = (double) this.sum / this.counter;
        }
        catch (ArithmeticException e){
            System.out.println("Деление на 0.");
            log.error("Деление на 0.", e);
        }
    }

    private void computeSum(long l){
        this.sum += l;
    }

    private void setMin(long value){
        if (min != Constants.START_LONG_VALUE){
            if (this.min > value){
                this.min = value;
            }
            return;
        }
        min = value;
    }

    private void setMax(long value){
        if (this.max < value)
            this.max = value;
    }

    private void increaseCounter(){
        counter++;
    }
}
