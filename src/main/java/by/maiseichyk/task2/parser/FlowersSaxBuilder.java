package by.maiseichyk.task2.parser;

import by.maiseichyk.task2.builder.AbstractFlowerBuilder;
import by.maiseichyk.task2.entity.Flower;
import by.maiseichyk.task2.exception.CustomException;
import by.maiseichyk.task2.handler.FlowerErrorHandler;
import by.maiseichyk.task2.handler.FlowerHandler;
import by.maiseichyk.task2.validator.CustomXmlValidator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Set;

public class FlowersSaxBuilder extends AbstractFlowerBuilder {
        private static Logger logger = LogManager.getLogger();
    private Set<Flower> flowers;
    private FlowerHandler handler = new FlowerHandler();
    private XMLReader xmlReader;

    public FlowersSaxBuilder() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            xmlReader = saxParser.getXMLReader();
        } catch (ParserConfigurationException | SAXException exception) {
            logger.error(exception);
            exception.printStackTrace();
        }
        xmlReader.setErrorHandler(new FlowerErrorHandler());
        xmlReader.setContentHandler(handler);

    }

    public Set<Flower> getFlowers() {
        return flowers;
    }

    public void buildFlowersSet(String filename, String schemaName) throws CustomException {
        CustomXmlValidator validator = new CustomXmlValidator();
        if (validator.validate(filename, schemaName)) {
            try {
                xmlReader.parse(filename);
            } catch (IOException | SAXException exception) {
                logger.error(exception);
            }
            flowers = handler.getFlowers();
            logger.info("Set" + flowers + "was created");
        }
        else {
            logger.info(filename + " does not match schema " + schemaName);
            throw new CustomException(filename + "does not match schema " + schemaName);

        }
    }
}

