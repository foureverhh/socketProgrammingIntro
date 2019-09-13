package server.demo1.servletSample01.servlet;

import java.util.HashMap;
import java.util.Map;

/*
* Factory pattern to create context
* */
public class ServletContext {
    //give each servlet an alias name
    //<login,loginServlet>
    private Map<String,Servlet> servlet;
    //<url,login>
    private Map<String,String> mapping;

    public ServletContext(){
        servlet = new HashMap<>();
        mapping = new HashMap<>();
    }

    public Map<String, Servlet> getServlet() {
        return servlet;
    }

    public Map<String, String> getMapping() {
        return mapping;
    }

    public void setServlet(Map<String, Servlet> servlet) {
        this.servlet = servlet;
    }

    public void setMapping(Map<String, String> mapping) {
        this.mapping = mapping;
    }
}
