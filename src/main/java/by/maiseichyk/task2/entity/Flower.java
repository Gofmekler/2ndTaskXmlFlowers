package by.maiseichyk.task2.entity;

import java.time.YearMonth;
import java.util.Objects;

public class Flower {
    private String id;
    private String name;
    private String origin;
    private Soil soilType;
    private VisualParameters visualParameters = new VisualParameters();
    private Multiplying multiplyingType;
    private GrowingTips growingTips = new GrowingTips();
    private YearMonth date;

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

    public Soil getSoilType() {
        return soilType;
    }

    public void setSoilType(Soil soilType) {
        this.soilType = soilType;
    }

    public VisualParameters getVisualParameters() {
        return visualParameters;
    }

    public void setVisualParameters(VisualParameters visualParameters) {
        this.visualParameters = visualParameters;
    }

    public Multiplying getMultiplyingType() {
        return multiplyingType;
    }

    public void setMultiplyingType(Multiplying multiplyingType) {
        this.multiplyingType = multiplyingType;
    }

    public GrowingTips getGrowingTips() {
        return growingTips;
    }

    public void setGrowingTips(GrowingTips growingTips) {
        this.growingTips = growingTips;
    }

    public YearMonth getDate() {
        return date;
    }

    public void setDate(YearMonth date) {
        this.date = date;
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
