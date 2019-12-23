package yuanheng.server.servletXMl.webParsing;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;


public class WebHandler extends DefaultHandler {
    private List<Entity> entityList;
    private List<Mapping> mappingList;
    private Entity entity;
    private Mapping mapping;
    private String tag;
    private boolean isMap;//To check whether it is parsing map entity or entity


    @Override
    public void startDocument() throws SAXException {
        entityList = new ArrayList<>();
        mappingList = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(null != qName){
            tag = qName;
        }
        if(null != tag && tag.equals("servlet")){
            isMap = false;
            entity = new Entity();
        }else if(null!= tag && tag.equals("servlet-mapping")){
            isMap = true;
            mapping = new Mapping();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        //Handle content

        if(null != tag ) {
            String str = new String(ch, start, length);
            if (!isMap) {
                if (tag.equals("servlet-name")) {
                    entity.setName(str);
                } else if (tag.equals("servlet-class")) {
                    entity.setClz(str);
                }
            } else {
                if (tag.equals("servlet-name")) {
                    mapping.setName(str);
                } else if (tag.equals("url-pattern")) {
                    mapping.getUrlPattern().add(str);
                }
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(null!=qName){
            if(qName.equals("servlet")){
                entityList.add(entity);
            }else if(qName.equals("servlet-mapping")){
                mappingList.add(mapping);
            }
        }
        tag = null;
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    public List<Entity> getEntityList() {
        return entityList;
    }

    public List<Mapping> getMappingList() {
        return mappingList;
    }

   /*public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        WebHandler handler = new WebHandler();
        parser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream(
                "server/demo1/servletXMl/webParsing/web.xml"),handler);

      List<Entity> entities = handler.getEntityList();
        List<Mapping> mappings = handler.getMappingList();
        System.out.println("Mapping is: ");
        for(Mapping mapping: handler.getMappingList()){
            System.out.println("To œŒ"+mapping.getName());
            System.out.println("Urls are:");
            for(String url : mapping.getUrlPattern()){
                System.out.print(url+" ");
            }
            System.out.println();
        }
        mappings.forEach(mapping -> System.out.println(mapping.getName()+" "+ Arrays.toString(mapping.getUrlPattern().toArray())));
        System.out.println("Entity is: ");
        System.out.println(entities.size());
        entities.forEach(entity-> System.out.println(entity.getName()+" "+entity.getClz()));
    }*/
}
