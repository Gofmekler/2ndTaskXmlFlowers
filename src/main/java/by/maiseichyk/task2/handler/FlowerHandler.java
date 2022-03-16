package by.maiseichyk.task2.handler;

import by.maiseichyk.task2.entity.*;
import by.maiseichyk.task2.exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.YearMonth;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import static by.maiseichyk.task2.handler.FlowerXmlTag.*;

public class FlowerHandler extends DefaultHandler {
    private static Logger logger = LogManager.getLogger();
    private Set<Flower> flowers;
    private Flower currentFlower;
    private VisualParameters currentVisualParameters;
    private GrowingTips currentGrowingTips;
    private FlowerXmlTag currentXmlTag;
    private EnumSet<FlowerXmlTag> withText;

    public FlowerHandler() {
        flowers = new HashSet<>();
        withText = EnumSet.range(FlowerXmlTag.NAME, FlowerXmlTag.MULTIPLYING);
    }

    public Set<Flower> getFlowers() {
        return flowers;
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if (NON_POISONOUS_FLOWER.getValue().equals(qName)) {
            currentFlower = new NonPoisonousFlower();
            currentFlower.setId(attrs.getValue(0));
            if (attrs.getLength() == 2) {
                currentFlower.setDangerLevel(DangerLevel.valueOf(attrs.getValue(1).toUpperCase()));
            }
        }

        else if (POISONOUS_FLOWER.getValue().equals(qName)) {
            currentFlower = new PoisonousFlower();
            currentFlower.setId(attrs.getValue(0));
            if (attrs.getLength() == 2) {
                currentFlower.setDangerLevel(DangerLevel.valueOf(attrs.getValue(1).toUpperCase()));
            }
        }

        else if (VISUAL_PARAMETERS.getValue().equals(qName)) {
            currentVisualParameters = new VisualParameters();
            currentFlower.setVisualParameters(currentVisualParameters);


        }

        else if (GROWING_TIPS.getValue().equals(qName)) {
            currentGrowingTips = new GrowingTips();
            currentFlower.setGrowingTips(currentGrowingTips);
        }

        else {
            FlowerXmlTag temp = FlowerXmlTag.valueOf(qName.toUpperCase().replace("-", "_"));
            if (withText.contains(temp)) {
                currentXmlTag = temp;
            }
        }
    }

    public void endElement(String uri, String localName, String qName) {
        if (NON_POISONOUS_FLOWER.getValue().equals(qName) ) {
            flowers.add(currentFlower);
        }
        else if (POISONOUS_FLOWER.getValue().equals(qName)){
            flowers.add(currentFlower);
        }
    }

    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length);
        if (currentXmlTag != null) {
            try {
                switch (currentXmlTag) {
                    case NAME -> currentFlower.setName(data);
                    case SOIL -> currentFlower.setSoilType(Soil.getSoilTypeByValue(data));
                    case ORIGIN -> currentFlower.setOrigin(data);
                    case PLANT_DISCOVERY_DATE -> currentFlower.setDate(YearMonth.parse(data));
                    case STEM_COLOUR -> currentVisualParameters.setStemColour(Colour.getColourByValue(data));
                    case LEAF_COLOUR -> currentVisualParameters.setLeafColour(Colour.getColourByValue(data));
                    case AVERAGE_PLANT_SIZE -> currentVisualParameters.setAverageSize(Integer.parseInt(data));
                    case TEMPERATURE -> currentGrowingTips.setTemperature(Integer.parseInt(data));
                    case WEEKLY_WATERING -> currentGrowingTips.setWeeklyWatering(Integer.parseInt(data));
                    case LIGHT_REQUIRING -> currentGrowingTips.setLightRequiring(Boolean.parseBoolean(data));
                    case MULTIPLYING -> currentFlower.setMultiplyingType(Multiplying.getMultiplyingTypeByValue(data));
                    default -> throw new EnumConstantNotPresentException(currentXmlTag.getDeclaringClass(), currentXmlTag.name());

                }
            } catch (CustomException exception) {
                logger.error(exception);
            }
        }
        currentXmlTag = null;
    }
}


