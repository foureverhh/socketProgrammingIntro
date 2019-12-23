package yuanheng.server.servletXMl;

import org.xml.sax.SAXException;
import yuanheng.server.servletXMl.servlet.Servlet;
import yuanheng.server.servletXMl.servlet.ServletContext;
import yuanheng.server.servletXMl.webParsing.Entity;
import yuanheng.server.servletXMl.webParsing.Mapping;
import yuanheng.server.servletXMl.webParsing.WebHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class WebApp {
    private static ServletContext context;
    static {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = null;
        try {
            parser = factory.newSAXParser();
            WebHandler handler = new WebHandler();
            parser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream(
                    "server/src/yuanheng/server/servletXMl/WEB_INFO/web.xml"),handler);


        context = new ServletContext();
        Map<String,String> servlet = context.getServlet();
        //Store servlet-name and servlet-class
        for(Entity entity: handler.getEntityList()){
            servlet.put(entity.getName(),entity.getClz());
        }
         /*
        mapping.put("/login","login");
        mapping.put("/log","login");
        mapping.put("/reg","register");
        mapping.put("/register","register");
        */

        Map<String,String> mapping = context.getMapping();
        for(Mapping map: handler.getMappingList()){
            List<String> urls = map.getUrlPattern();
            for(String url: urls){
                System.out.println(url);
                System.out.println(map.getName());
                mapping.put(url,map.getName());
            }
        }

        /*Not to store object but to save .class file,and to use reflection as below
        servlet.put("login",new LogInServlet());
        servlet.put("register", new RegisterServlet());
        */
        /*
        servlet.put("login","server.demo1.servletSample01.servlet.login.LogInServlet");
        servlet.put("register", "server.demo1.servletSample01.servlet.register.RegisterServlet");
        */
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static yuanheng.server.servletXMl.servlet.Servlet getServlet(String url) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if(url == null || url.trim().equals("")){
            return null;
        }
        String servletName = context.getServlet().get(context.getMapping().get(url));
        System.out.println(context.getMapping().get(url));

        System.out.println(servletName);
        Class<?> clz = Class.forName(servletName);
        Servlet servletInstance = (Servlet) clz.newInstance();
        //return context.getServlet().get(context.getMapping().get(url));
        return servletInstance;

    }

}
