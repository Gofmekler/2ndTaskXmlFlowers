package by.maiseichyk.task2.validator;

import by.maiseichyk.task2.exception.CustomException;
import org.xml.sax.SAXException;
import java.io.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public class CustomXmlValidator {
    private static Logger logger = LogManager.getLogger();

    public boolean validate() {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        String fileName = "flowers.xml";
        String schemaName = "flowers2.xsd";
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaName);
        try {
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(fileName);
            validator.validate(source);
        } catch (SAXException | IOException e) {
            logger.error(fileName + " is not correct or valid");
            return false;
        }
        return true;
    }
}
