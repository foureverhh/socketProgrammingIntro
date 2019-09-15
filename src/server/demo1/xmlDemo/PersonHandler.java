package server.demo1.xmlDemo;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PersonHandler extends DefaultHandler {
    private List<Person> persons;
    private Person person;
    private String tag;//记录标签名

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start to handle document");
        persons = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println("Start to handle an element "+qName);
        if(null != qName){
            tag = qName;
        }
        if(null!=qName && qName.equals("person")){
            person = new Person();
        }
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        System.out.println(new String(ch,start,length));
        String str = new String(ch,start,length);
        if(null!=tag && tag.equals("name")){
            person.setName(str);
        }else if(null!=tag && tag.equals("age")){
            person.setAge(Integer.parseInt(str.trim()));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("End to handle an element "+qName);
        if(qName.equals("person")){
            this.persons.add(this.person);
        }
        tag = null;
    }


    @Override
    public void endDocument() throws SAXException {
        System.out.println("End to handle document");
    }

    public List getPersons(){
        return persons;
    }
}
