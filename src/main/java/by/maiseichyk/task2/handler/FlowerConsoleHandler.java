package by.maiseichyk.task2.handler;

import by.maiseichyk.task2.entity.Flower;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Set;

public class FlowerConsoleHandler extends DefaultHandler {
    public Set<Flower> flowers;

    public void startDocument() {
        System.out.println("Document starts");
    }

    public void endDocument() {
        System.out.println("Document ends");
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes){
        String tagData = qName;
        for (int i = 0; i < attributes.getLength(); i++){
            tagData += attributes.getQName(i) + ":" + attributes.getValue(i);
        }
        System.out.println(tagData);
    }

    public void endElement(String uri, String localName, String qName){
        System.out.println(" " + qName);
    }

    public void characters(char ch[], int start, int length){
        System.out.println(new String(ch, start, length));
    }

    public Set<Flower> getFlowers() {
        return flowers;
    }
}
