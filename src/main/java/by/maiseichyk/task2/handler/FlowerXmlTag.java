package by.maiseichyk.task2.handler;

public enum FlowerXmlTag {

        FLOWERS("flowers"),

        NON_POISONOUS_FLOWER("non-poisonous-flower"),

        POISONOUS_FLOWER("poisonous-flower"),

        ID("id"),

        DANGER_LEVEL("danger-level"),

        VISUAL_PARAMETERS("visual-parameters"),

        GROWING_TIPS("growing-tips"),

        NAME("name"),

        SOIL("soil"),

        ORIGIN("origin"),

        PLANT_DISCOVERY_DATE("plant-discovery-date"),

        STEM_COLOUR("stem-colour"),

        LEAF_COLOUR("leaf-colour"),

        AVERAGE_PLANT_SIZE("average-plant-size"),

        TEMPERATURE("temperature"),

        WEEKLY_WATERING("weekly-watering"),

        LIGHT_REQUIRING("light-requiring"),

        MULTIPLYING("multiplying");

        private String value;

        FlowerXmlTag(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

}
