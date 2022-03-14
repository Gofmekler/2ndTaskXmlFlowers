package by.maiseichyk.task2.entity;

import by.maiseichyk.task2.exception.CustomException;

public enum Multiplying {
    SEEDS("seeds"),
    CUTTINGS("cuttings");

    private String value;

    Multiplying(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Multiplying getMultiplyingTypeByValue(String value) throws CustomException {
        Multiplying[] values = Multiplying.values();
        for (Multiplying multiplyingMethod : values) {
            if (multiplyingMethod.value.equals(value)) {
                return multiplyingMethod;
            }
        }
        throw new CustomException("Illegal argument!");
    }

    @Override
    public String toString() {
        return "Multiplying{" +
                "value='" + value + '\'' +
                '}';
    }
}