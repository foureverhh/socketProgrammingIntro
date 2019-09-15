package server.demo1.xmlDemo;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ParseDemo01 {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        //Instantiate factory
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        //Instantiate parser
        SAXParser parser = parserFactory.newSAXParser();
        PersonHandler handler = new PersonHandler();
        //parse document
        parser.parse(Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("server/demo1/xmlDemo/person.xml"),
                handler);
        List<Person> persons = handler.getPersons();
        persons.forEach(System.out::println);
    }

}
