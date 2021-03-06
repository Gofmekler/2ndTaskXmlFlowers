package by.maiseichyk.task2.entity;

import by.maiseichyk.task2.exception.CustomException;

import java.util.Objects;

public class GrowingTips {
    private int temperature; //celsium
    private boolean lightRequiring;
    private int weeklyWatering; //ml

    public GrowingTips(){
    }

    public GrowingTips(int temperature,  int weeklyWatering, boolean lightRequiring) {
        this.temperature = temperature;
        this.weeklyWatering = weeklyWatering;
        this.lightRequiring = lightRequiring;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setLightRequiring(boolean lightRequiring) {
        this.lightRequiring = lightRequiring;
    }

    public void setWeeklyWatering(int weeklyWatering) throws CustomException {
        if (weeklyWatering < 0){
            throw new CustomException("Illegal argument");
        }
        this.weeklyWatering = weeklyWatering;
    }

    @Override
    public String toString() {
        return "GrowingTips{" +
                "temperature=" + temperature +
                ", lightRequiring=" + lightRequiring +
                ", weeklyWatering=" + weeklyWatering +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrowingTips that = (GrowingTips) o;
        return temperature == that.temperature && lightRequiring == that.lightRequiring && weeklyWatering == that.weeklyWatering;
    }

    @Override
    public int hashCode() {
        return Objects.hash(temperature, lightRequiring, weeklyWatering);
    }
}
