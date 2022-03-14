package by.maiseichyk.task2.entity;

import by.maiseichyk.task2.exception.CustomException;

public enum DangerLevel {
        HIGH("high"),
        LOW("low"),
        NONE("none");

        private String value;

        DangerLevel(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static DangerLevel getDangerLevelByValue(String value) throws CustomException {
            DangerLevel[] values = DangerLevel.values();
            for (DangerLevel dangerLevel : values) {
                if (dangerLevel.value.equals(value)) {
                    return dangerLevel;
                }
            }
            throw new CustomException("Illegal argument!");
        }

    @Override
    public String toString() {
        return "DangerLevel{" +
                "value='" + value + '\'' +
                '}';
    }
}
