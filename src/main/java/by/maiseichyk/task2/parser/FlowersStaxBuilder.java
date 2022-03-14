package by.maiseichyk.task2.parser;

import by.maiseichyk.task2.builder.AbstractFlowerBuilder;
import by.maiseichyk.task2.entity.*;
import by.maiseichyk.task2.exception.CustomException;
import by.maiseichyk.task2.handler.FlowerXmlTag;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.YearMonth;
import java.util.HashSet;
import java.util.Set;

public class FlowersStaxBuilder extends AbstractFlowerBuilder {
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
    public void buildFlowersSet(String filename) {
        XMLStreamReader reader;
        String name;
        try(FileInputStream inputStream = new FileInputStream(filename)){
            reader = inputFactory.createXMLStreamReader(inputStream);
            while(reader.hasNext()){
                int type = reader.next();
                if (type == XMLStreamReader.START_ELEMENT){ // FIXME: 14.03.2022 IllegalStateException
                    name = reader.getLocalName();
                    if (name.equals(FlowerXmlTag.POISONOUS_FLOWER.getValue())){
                        PoisonousFlower poisonousFlower = (PoisonousFlower) buildPoisonousFlower(reader);
                        flowers.add(poisonousFlower);
                    }
                    if (name.equals(FlowerXmlTag.NON_POISONOUS_FLOWER.getValue())){
                        NonPoisonousFlower nonPoisonousFlower = (NonPoisonousFlower) buildNonPoisonousFlower(reader);
                        flowers.add(nonPoisonousFlower);
                    }
                }
            }
        } catch (XMLStreamException | IOException | CustomException exception){
            exception.printStackTrace();//logger
        }
    }

    private Flower buildPoisonousFlower(XMLStreamReader reader) throws XMLStreamException, CustomException {
        PoisonousFlower poisonousFlower = new PoisonousFlower();
        poisonousFlower.setId(reader.getAttributeValue(null, FlowerXmlTag.ID.getValue()));
        poisonousFlower.setDangerLevel(DangerLevel.getDangerLevelByValue(reader.getAttributeValue(null, FlowerXmlTag.DANGER_LEVEL.getValue())));
        poisonousFlower.setName(getXMLText(reader));
        poisonousFlower.setSoilType(Soil.getSoilTypeByValue(getXMLText(reader)));
        poisonousFlower.setOrigin(getXMLText(reader));
        poisonousFlower.setDate(YearMonth.parse(getXMLText(reader)));
        poisonousFlower.setVisualParameters(buildVisualParameters(reader));
        poisonousFlower.setGrowingTips(buildGrowingTips(reader));
        poisonousFlower.setMultiplyingType(Multiplying.getMultiplyingTypeByValue(getXMLText(reader)));
        return poisonousFlower;
    }

    private Flower buildNonPoisonousFlower(XMLStreamReader reader) throws XMLStreamException, CustomException {
        NonPoisonousFlower nonPoisonousFlower = new NonPoisonousFlower();
        nonPoisonousFlower.setId(reader.getAttributeValue(null, FlowerXmlTag.ID.getValue()));
        nonPoisonousFlower.setDangerLevel(DangerLevel.getDangerLevelByValue(reader.getAttributeValue(null, FlowerXmlTag.DANGER_LEVEL.getValue())));
        nonPoisonousFlower.setName(getXMLText(reader));
        nonPoisonousFlower.setSoilType(Soil.getSoilTypeByValue(getXMLText(reader)));
        nonPoisonousFlower.setOrigin(getXMLText(reader));
        nonPoisonousFlower.setDate(YearMonth.parse(getXMLText(reader)));
        nonPoisonousFlower.setVisualParameters(buildVisualParameters(reader));
        nonPoisonousFlower.setGrowingTips(buildGrowingTips(reader));
        nonPoisonousFlower.setMultiplyingType(Multiplying.getMultiplyingTypeByValue(getXMLText(reader)));
        return nonPoisonousFlower;
    }

    private VisualParameters buildVisualParameters(XMLStreamReader reader) throws XMLStreamException, CustomException {
        VisualParameters visualDescription = new VisualParameters();
        FlowerXmlTag tag;
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName();
                    tag = FlowerXmlTag.valueOf(name.toUpperCase());
                    switch (tag) {
                        case STEM_COLOUR -> visualDescription.setStemColour(Colour.getColourByValue(getXMLText(reader)));
                        case LEAF_COLOUR -> visualDescription.setLeafColour(Colour.getColourByValue(getXMLText(reader)));
                        case AVERAGE_PLANT_SIZE -> visualDescription.setAverageSize(Integer.parseInt(getXMLText(reader)));
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName();
                    tag = FlowerXmlTag.valueOf(name.toUpperCase());
                    if (tag == FlowerXmlTag.VISUAL_PARAMETERS) {
                        return visualDescription;
                    }
                }
            }
        }
        throw new CustomException("Illegal visual");
    }
    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
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
                    tag = FlowerXmlTag.valueOf(name.toUpperCase());
                    switch (tag) {
                        case WEEKLY_WATERING -> growingTips.setWeeklyWatering(Integer.parseInt(getXMLText(reader)));
                        case TEMPERATURE -> growingTips.setTemperature(Integer.parseInt(getXMLText(reader)));
                        case LIGHT_REQUIRING -> growingTips.setLightRequiring(Boolean.parseBoolean(getXMLText(reader)));
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName();
                    tag = FlowerXmlTag.valueOf(name.toUpperCase());
                    if (tag == FlowerXmlTag.GROWING_TIPS) {
                        return growingTips;
                    }
                }
            }
        }
        throw new CustomException("Illegal");
    }
}
