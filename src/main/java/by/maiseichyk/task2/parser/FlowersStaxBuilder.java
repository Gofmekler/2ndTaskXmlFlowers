package by.maiseichyk.task2.parser;

import by.maiseichyk.task2.builder.AbstractFlowerBuilder;
import by.maiseichyk.task2.entity.*;
import by.maiseichyk.task2.exception.CustomException;
import by.maiseichyk.task2.handler.FlowerXmlTag;
import by.maiseichyk.task2.validator.CustomXmlValidator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.YearMonth;
import java.util.HashSet;
import java.util.Set;

import static by.maiseichyk.task2.handler.FlowerXmlTag.NON_POISONOUS_FLOWER;
import static by.maiseichyk.task2.handler.FlowerXmlTag.POISONOUS_FLOWER;

public class FlowersStaxBuilder extends AbstractFlowerBuilder {
    private static Logger logger = LogManager.getLogger();
    private Set<Flower> flowers;
    private XMLInputFactory inputFactory;
    public FlowersStaxBuilder(){
        inputFactory = XMLInputFactory.newInstance();
        flowers = new HashSet<>();
    }
    public Set<Flower> getFlowers(){
        return flowers;
    }
    @Override
    public void buildFlowersSet(String filename, String schemaName) throws CustomException {
        XMLStreamReader reader;
        String name;
        CustomXmlValidator validator = new CustomXmlValidator();
        if(validator.validate(filename, schemaName)) {
            try (FileInputStream inputStream = new FileInputStream(filename)) {
                reader = inputFactory.createXMLStreamReader(inputStream);
                while (reader.hasNext()) {
                    int type = reader.next();
                    if (type == XMLStreamReader.START_ELEMENT) {
                        name = reader.getLocalName();
                        if (POISONOUS_FLOWER.getValue().equals(name)) {
                            PoisonousFlower poisonousFlower = new PoisonousFlower();
                            buildFlower(reader, poisonousFlower);
                            flowers.add(poisonousFlower);
                        }
                        if (NON_POISONOUS_FLOWER.getValue().equals(name)) {
                            NonPoisonousFlower nonPoisonousFlower = new NonPoisonousFlower();
                            buildFlower(reader, nonPoisonousFlower);
                            flowers.add(nonPoisonousFlower);
                        }
                    }
                }
            } catch (XMLStreamException | IOException | CustomException exception) {
                logger.error(exception);
            }
        }
        else {
            logger.info(filename + " does not match schema " + schemaName);
            throw new CustomException(filename + "does not match schema " + schemaName);
        }
    }

    private Flower buildFlower(XMLStreamReader reader, Flower currentFlower) throws CustomException, XMLStreamException {
        String name;
        currentFlower.setId(reader.getAttributeValue(null, FlowerXmlTag.ID.getValue()));
        if (reader.getAttributeCount() == 2){
            currentFlower.setDangerLevel(DangerLevel.getDangerLevelByValue(reader.getAttributeValue(null, FlowerXmlTag.DANGER_LEVEL.getValue())));
        }
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName().replace("-", "_").toUpperCase();
                    switch (FlowerXmlTag.valueOf(name)) {
                        case NAME -> currentFlower.setName(getXMLText(reader));
                        case SOIL -> currentFlower.setSoilType(Soil.getSoilTypeByValue(getXMLText(reader)));
                        case ORIGIN -> currentFlower.setOrigin(getXMLText(reader));
                        case PLANT_DISCOVERY_DATE -> currentFlower.setDate(YearMonth.parse(getXMLText(reader)));
                        case VISUAL_PARAMETERS -> currentFlower.setVisualParameters(buildVisualParameters(reader));
                        case GROWING_TIPS -> currentFlower.setGrowingTips(buildGrowingTips(reader));
                        case MULTIPLYING -> currentFlower.setMultiplyingType(Multiplying.getMultiplyingTypeByValue(getXMLText(reader)));
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName().replace("-", "_");//replace todo
                    if (FlowerXmlTag.valueOf(name.toUpperCase()) == NON_POISONOUS_FLOWER
                            || FlowerXmlTag.valueOf(name.toUpperCase()) == POISONOUS_FLOWER) {
                        return currentFlower;
                    }
                }
            }
        }
        throw new XMLStreamException();
    }

    private VisualParameters buildVisualParameters(XMLStreamReader reader) throws XMLStreamException, CustomException {
        VisualParameters visualParameters = new VisualParameters();
        FlowerXmlTag tag;
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName().toUpperCase().replace("-", "_");
                    tag = FlowerXmlTag.valueOf(name.toUpperCase());
                    switch (tag) {
                        case STEM_COLOUR -> visualParameters.setStemColour(Colour.getColourByValue(getXMLText(reader)));
                        case LEAF_COLOUR -> visualParameters.setLeafColour(Colour.getColourByValue(getXMLText(reader)));
                        case AVERAGE_PLANT_SIZE -> visualParameters.setAverageSize(Integer.parseInt(getXMLText(reader)));
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName();
                    tag = FlowerXmlTag.valueOf(name.toUpperCase().replace("-", "_"));
                    if (tag == FlowerXmlTag.VISUAL_PARAMETERS) {
                        return visualParameters;
                    }
                }
            }
        }
        throw new CustomException("Illegal visual");
    }

    private GrowingTips buildGrowingTips(XMLStreamReader reader) throws XMLStreamException, CustomException {
        GrowingTips growingTips = new GrowingTips();
        FlowerXmlTag tag;
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName();
                    tag = FlowerXmlTag.valueOf(name.toUpperCase().replace("-", "_"));
                    switch (tag) {
                        case WEEKLY_WATERING -> growingTips.setWeeklyWatering(Integer.parseInt(getXMLText(reader)));
                        case TEMPERATURE -> growingTips.setTemperature(Integer.parseInt(getXMLText(reader)));
                        case LIGHT_REQUIRING -> growingTips.setLightRequiring(Boolean.parseBoolean(getXMLText(reader)));
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName();
                    tag = FlowerXmlTag.valueOf(name.toUpperCase().replace("-", "_"));
                    if (tag == FlowerXmlTag.GROWING_TIPS) {
                        return growingTips;
                    }
                }
            }
        }
        throw new CustomException("Illegal");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
