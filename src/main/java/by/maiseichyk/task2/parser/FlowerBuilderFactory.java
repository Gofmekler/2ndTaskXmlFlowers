package by.maiseichyk.task2.parser;

import by.maiseichyk.task2.builder.AbstractFlowerBuilder;
import by.maiseichyk.task2.exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FlowerBuilderFactory {
    private static Logger logger = LogManager.getLogger();
    private static final FlowerBuilderFactory instance = new FlowerBuilderFactory();

    private FlowerBuilderFactory() {

    }

    public static FlowerBuilderFactory getInstance() {
        return instance;
    }

    public AbstractFlowerBuilder createFlowerBuilder(ParserType type) throws CustomException {
        switch (type) {
            case SAX -> {
                return new FlowersSaxBuilder();
            }

            case DOM -> {
                return new FlowersDomBuilder();
            }

            case STAX -> {
                return  new FlowersStaxBuilder();
            }
            default -> {
                logger.error("No constant" + type);
                throw new CustomException("No constant in enum" + type);
            }

        }
    }
}
