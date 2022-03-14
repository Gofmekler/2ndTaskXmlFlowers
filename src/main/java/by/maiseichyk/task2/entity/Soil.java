package by.maiseichyk.task2.entity;

import by.maiseichyk.task2.exception.CustomException;

public enum Soil {
    ACIDIC_SOIL("acidic soil"),
    SLIGHTLY_ACIDIC_SOIL("slightly acidic soil"),
    NEUTRAL_SOIL("neutral soil");

    private String value;

    Soil(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Soil getSoilTypeByValue(String value) throws CustomException {
        Soil[] values = Soil.values();
        for (Soil soilType : values) {
            if (soilType.value.equals(value)) {
                return soilType;
            }
        }
        throw new CustomException("Illegal argument!");
    }

    @Override
    public String toString() {
        return "Soil{" +
                "value='" + value + '\'' +
                '}';
    }
}
