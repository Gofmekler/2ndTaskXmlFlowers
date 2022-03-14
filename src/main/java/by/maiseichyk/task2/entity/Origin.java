package by.maiseichyk.task2.entity;

import by.maiseichyk.task2.exception.CustomException;

public enum Origin {
    CHILI("Chili"),
    CHINA("China"),
    JAPAN("Japan"),
    BRAZIL("Brazil"),
    MEXICO("Mexico"),
    MADAGASCAR("Madagascar"),
    INDIA("India"),
    HAWAII("Hawaii");

    private String value;

    Origin(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Origin getOriginByValue(String value) throws CustomException {
        Origin[] values = Origin.values();
        for (Origin origin : values) {
            if (origin.value.equals(value)) {
                return origin;
            }
        }
        throw new CustomException("Illegal argument!");
    }

    @Override
    public String toString() {
        return "Origin{" +
                "value='" + value + '\'' +
                '}';
    }
}
