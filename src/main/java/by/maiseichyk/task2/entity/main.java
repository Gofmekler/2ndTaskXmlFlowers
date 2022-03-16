package by.maiseichyk.task2.entity;

import by.maiseichyk.task2.exception.CustomException;
import by.maiseichyk.task2.handler.FlowerHandler;
import by.maiseichyk.task2.parser.FlowersDomBuilder;
import by.maiseichyk.task2.parser.FlowersSaxBuilder;
import by.maiseichyk.task2.parser.FlowersStaxBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class main {
    public static String FILE_PATH_VALID = "src/main/resources/flowers.xml";
        public static void main(String[] args) throws CustomException {
//            try{
//                SAXParserFactory factory = SAXParserFactory.newInstance();
//                SAXParser parser = factory.newSAXParser();
//                XMLReader reader = parser.getXMLReader();
//                reader.setContentHandler(new FlowerHandler());
//                reader.parse(FILE_PATH_VALID);
//                FlowersSaxBuilder saxBuilder = new FlowersSaxBuilder();
//                saxBuilder.buildFlowersSet(FILE_PATH_VALID);
//                System.out.println(saxBuilder.getFlowers());
//                FlowersDomBuilder domBuilder = new FlowersDomBuilder();
//                domBuilder.buildFlowersSet("src/main/resources/flowers.xml");
//                System.out.println(domBuilder.getFlowers());
            FlowersStaxBuilder staxBuilder = new FlowersStaxBuilder();
            staxBuilder.buildFlowersSet(FILE_PATH_VALID);
            System.out.println(staxBuilder.getFlowers());
//        }
//            catch (SAXException | IOException | ParserConfigurationException e){
//                e.printStackTrace();
//            }
    }
}
