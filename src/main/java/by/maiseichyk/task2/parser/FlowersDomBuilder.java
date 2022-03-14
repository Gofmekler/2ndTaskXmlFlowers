package by.maiseichyk.task2.parser;

import by.maiseichyk.task2.builder.AbstractFlowerBuilder;
import by.maiseichyk.task2.entity.*;
import by.maiseichyk.task2.exception.CustomException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import  org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.YearMonth;
import java.util.HashSet;
import java.util.Set;

public class FlowersDomBuilder extends AbstractFlowerBuilder{
    private Set<Flower> flowers;
    private DocumentBuilder documentBuilder;
    public FlowersDomBuilder(){
        flowers = new HashSet<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        }
    }
    public Set<Flower> getFlowers(){
        return flowers;
    }

    public void buildFlowersSet(String filename) {
        Document document = null;
        try{
            document = documentBuilder.parse(filename);
            Element root = document.getDocumentElement();
            NodeList poisonousFlowersList = root.getElementsByTagName("poisonous-flower");
            for (int i = 0; i < poisonousFlowersList.getLength(); i++) {
                Element poisonousFlowerElement = (Element) poisonousFlowersList.item(i);
                Flower poisonousFlower = buildPoisonousFlower(poisonousFlowerElement);
                flowers.add(poisonousFlower);
            }
            NodeList nonPoisonousFlowersList = root.getElementsByTagName("non-poisonous-flower");
            for (int i = 0; i < nonPoisonousFlowersList.getLength(); i++) {
                Element nonPoisonousFlowerElement = (Element) nonPoisonousFlowersList.item(i);
                Flower nonPoisonousFlower = buildNonPoisonousFlower(nonPoisonousFlowerElement);
                flowers.add(nonPoisonousFlower);
            }
        }
        catch (SAXException | IOException | CustomException exception){
            exception.printStackTrace();//logger
        }

    }

    private PoisonousFlower buildPoisonousFlower(Element poisonousFlowerElement) throws CustomException {
        Element visualParametersElement = (Element) poisonousFlowerElement.getElementsByTagName("visual-parameters").item(0);
        Element growingTipsElement = (Element) poisonousFlowerElement.getElementsByTagName("growing-tips").item(0);
        PoisonousFlower poisonousFlower = new PoisonousFlower();
        poisonousFlower.setId(poisonousFlowerElement.getAttribute("id"));
        poisonousFlower.setDangerLevel(DangerLevel.getDangerLevelByValue(poisonousFlowerElement.getAttribute("danger-level")));
        poisonousFlower.setName(getElementTextContent(poisonousFlowerElement, "name"));
        poisonousFlower.setSoilType(Soil.getSoilTypeByValue(getElementTextContent(poisonousFlowerElement, "soil")));
        poisonousFlower.setOrigin(getElementTextContent(poisonousFlowerElement, "origin"));
        poisonousFlower.setDate(YearMonth.parse(getElementTextContent(poisonousFlowerElement, "plant-discovery-date")));
        poisonousFlower.setVisualParameters(buildVisualParameters(visualParametersElement));
        poisonousFlower.setGrowingTips(buildGrowingTips(growingTipsElement));
        poisonousFlower.setMultiplyingType(Multiplying.getMultiplyingTypeByValue(getElementTextContent(poisonousFlowerElement, "multiplying")));
        return poisonousFlower;
    }

    private NonPoisonousFlower buildNonPoisonousFlower(Element nonPoisonousFlowerElement) throws CustomException {
        Element visualParametersElement = (Element) nonPoisonousFlowerElement.getElementsByTagName("visual-parameters").item(0);
        Element growingTipsElement = (Element) nonPoisonousFlowerElement.getElementsByTagName("growing-tips").item(0);
        NonPoisonousFlower nonPoisonousFlower = new NonPoisonousFlower();
        nonPoisonousFlower.setId(nonPoisonousFlowerElement.getAttribute("id"));// FIXME: 13.03.2022 NullPointerException
        nonPoisonousFlower.setDangerLevel(DangerLevel.getDangerLevelByValue(nonPoisonousFlowerElement.getAttribute("danger-level")));
        nonPoisonousFlower.setName(getElementTextContent(nonPoisonousFlowerElement, "name"));
        nonPoisonousFlower.setSoilType(Soil.getSoilTypeByValue(getElementTextContent(nonPoisonousFlowerElement, "soil")));
        nonPoisonousFlower.setOrigin(getElementTextContent(nonPoisonousFlowerElement, "origin"));
        nonPoisonousFlower.setDate(YearMonth.parse(getElementTextContent(nonPoisonousFlowerElement, "plant-discovery-date")));
        nonPoisonousFlower.setVisualParameters(buildVisualParameters(visualParametersElement));
        nonPoisonousFlower.setGrowingTips(buildGrowingTips(growingTipsElement));
        nonPoisonousFlower.setMultiplyingType(Multiplying.getMultiplyingTypeByValue(getElementTextContent(nonPoisonousFlowerElement, "multiplying")));
        return nonPoisonousFlower;
    }

    private GrowingTips buildGrowingTips(Element growingTipsElement) throws CustomException {
        GrowingTips growingTips = new GrowingTips();
        growingTips.setTemperature(Integer.parseInt(getElementTextContent(growingTipsElement, "temperature")));
        growingTips.setWeeklyWatering(Integer.parseInt(getElementTextContent(growingTipsElement, "weekly-watering")));
        growingTips.setLightRequiring(Boolean.parseBoolean(getElementTextContent(growingTipsElement, "light-requiring")));
        return growingTips;
    }

    private VisualParameters buildVisualParameters(Element visualParametersElement) throws CustomException {
        VisualParameters visualParameters = new VisualParameters();
        visualParameters.setAverageSize(Integer.parseInt(getElementTextContent(visualParametersElement, "average-plant-size")));
        visualParameters.setStemColour(Colour.getColourByValue(getElementTextContent(visualParametersElement, "stem-colour")));
        visualParameters.setLeafColour(Colour.getColourByValue(getElementTextContent(visualParametersElement, "leaf-colour")));
        return visualParameters;
    }

    private static String getElementTextContent(Element element, String elementName){
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        return node.getTextContent();
    }
}
