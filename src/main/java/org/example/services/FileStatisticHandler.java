package org.example.services;

import lombok.Getter;
import org.example.implementations.FloatStatistic;
import org.example.implementations.LongStatistic;
import org.example.implementations.StringStatistic;
import org.example.interfaces.Statistic;


@Getter
public class FileStatisticHandler {
    private final String statisticMode;
    private final Statistic<Long> longStatistic;
    private final Statistic<Float> floatStatistic;
    private final Statistic<String> stringStatistic;

    public FileStatisticHandler(String statisticPrintMode) {
        this.statisticMode = statisticPrintMode;
        this.longStatistic = new LongStatistic(statisticPrintMode);
        this.floatStatistic = new FloatStatistic(statisticPrintMode);
        this.stringStatistic = new StringStatistic(statisticPrintMode);
    }

    public void addLong(Long l) {
        longStatistic.addItem(l);
    }

    public void addFloat(Float f) {
        floatStatistic.addItem(f);
    }

    public void addString(String s) {
        stringStatistic.addItem(s);
    }

    public void printResults() {
        longStatistic.printStatistic();
        floatStatistic.printStatistic();
        stringStatistic.printStatistic();
    }
}
