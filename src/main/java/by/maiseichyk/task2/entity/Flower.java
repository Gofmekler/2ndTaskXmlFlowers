package by.maiseichyk.task2.entity;

import java.time.YearMonth;
import java.util.Objects;

public class Flower {
    protected String id;
    protected String name;
    protected String origin;
    protected Soil soilType;
    protected VisualParameters visualParameters = new VisualParameters();
    protected Multiplying multiplyingType;
    protected GrowingTips growingTips = new GrowingTips();
    protected YearMonth date;
    protected DangerLevel dangerLevel;

    public Flower(){
    }

    public Flower(String id, String name, Soil soilType, String origin, YearMonth date, VisualParameters visualParameters, GrowingTips growingTips, Multiplying multiplyingType) {
        this.id = id;
        this.name = name;
        this.soilType = soilType;
        this.origin = origin;
        this.date = date;
        this.visualParameters = visualParameters;
        this.growingTips = growingTips;
        this.multiplyingType = multiplyingType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setSoilType(Soil soilType) {
        this.soilType = soilType;
    }

    public void setVisualParameters(VisualParameters visualParameters) {
        this.visualParameters = visualParameters;
    }

    public void setMultiplyingType(Multiplying multiplyingType) {
        this.multiplyingType = multiplyingType;
    }

    public void setGrowingTips(GrowingTips growingTips) {
        this.growingTips = growingTips;
    }

    public void setDate(YearMonth date) {
        this.date = date;
    }


    public void setDangerLevel(DangerLevel dangerLevel) {
        this.dangerLevel = dangerLevel;
    }

    @Override
    public String toString() {
        return "Flower{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", origin='" + origin + '\'' +
                ", soilType=" + soilType +
                ", visualParameters=" + visualParameters +
                ", multiplyingType=" + multiplyingType +
                ", growingTips=" + growingTips +
                ", date=" + date +
                ", dangerLevel" + dangerLevel +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flower flower = (Flower) o;
        return Objects.equals(id, flower.id) && Objects.equals(name, flower.name) && Objects.equals(origin, flower.origin) && soilType == flower.soilType && Objects.equals(visualParameters, flower.visualParameters) && multiplyingType == flower.multiplyingType && Objects.equals(growingTips, flower.growingTips) && Objects.equals(date, flower.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, origin, soilType, visualParameters, multiplyingType, growingTips, date);
    }
}
