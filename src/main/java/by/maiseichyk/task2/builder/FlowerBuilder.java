package by.maiseichyk.task2.builder;

import by.maiseichyk.task2.entity.*;

import java.time.YearMonth;

public class FlowerBuilder{
    private Flower flower;

    public FlowerBuilder() {
        Flower flower = new Flower();
    }

    public FlowerBuilder id(String id) {
        flower.setId(id);
        return this;
    }

    public FlowerBuilder name(String name) {
        flower.setName(name);
        return this;
    }

    public FlowerBuilder origin(String origin) {
        flower.setOrigin(origin);
        return this;
    }

    public FlowerBuilder soilType(Soil soilType) {
        flower.setSoilType(soilType);
        return this;
    }

    public FlowerBuilder visualParameters(VisualParameters visualParameters) {
        flower.setVisualParameters(visualParameters);
        return this;
    }

    public FlowerBuilder multiplyingType(Multiplying multiplyingType) {
        flower.setMultiplyingType(multiplyingType);
        return this;
    }

    public FlowerBuilder growingTips(GrowingTips growingTips) {
        flower.setGrowingTips(growingTips);
        return this;
    }

    public FlowerBuilder date(YearMonth date) {
        flower.setDate(date);
        return this;
    }

    public Flower build() {
        return flower;
    }
}
