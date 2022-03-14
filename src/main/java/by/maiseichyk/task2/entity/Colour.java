package by.maiseichyk.task2.entity;

import by.maiseichyk.task2.exception.CustomException;

public enum Colour {
        RED("red"),
        LIGHT_RED("light red"),
        DARK_GREEN("dark green"),
        GREEN("green"),
        LIGHT_GREEN("light green"),
        PINK("pink"),
        BROWN("brown"),
        RED_GREEN("red-green"),
        BLUE("blue"),
        WHITE("white");

        private String value;

        Colour(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public static Colour getColourByValue(String value) throws CustomException {
            Colour[] values = Colour.values();
            for (Colour colour : values) {
                if (colour.value.equals(value)) {
                    return colour;
                }
            }
            throw new CustomException("ILLEGAL");
        }

    @Override
    public String toString() {
        return "Colour{" +
                "value='" + value + '\'' +
                '}';
    }
}

