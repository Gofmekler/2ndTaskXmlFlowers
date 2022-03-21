package by.maiseichyk.task2.entity;

import java.time.YearMonth;
import java.util.Objects;

public class NonPoisonousFlower extends Flower{
    private DangerLevel dangerLevel;

    public NonPoisonousFlower(){
        super();
    }

    public NonPoisonousFlower(String id, String name, Soil soilType, String origin, YearMonth date, VisualParameters visualParameters, GrowingTips growingTips, Multiplying multiplyingType, DangerLevel dangerLevel){
        super(id, name, soilType, origin, date, visualParameters, growingTips, multiplyingType);
        this.dangerLevel = dangerLevel;
    }

    public void setDangerLevel(DangerLevel dangerLevel) {
        this.dangerLevel = dangerLevel;
    }

    @Override
    public String toString() {
        return "NonPoisonousFlower{" + id + '\'' +
                ", " + name + '\'' +
                ", " + origin + '\'' +
                ", " + soilType +
                ", " + visualParameters +
                ", " + multiplyingType +
                ", " + growingTips +
                ", " + date +
                ", " + dangerLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        NonPoisonousFlower that = (NonPoisonousFlower) o;
        return dangerLevel == that.dangerLevel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dangerLevel);
    }
}





