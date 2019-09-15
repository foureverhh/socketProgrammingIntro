package server.demo1.servletXMl.servlet;

import java.util.HashMap;
import java.util.Map;

/*
* Factory pattern to create context
* */
public class ServletContext {
    //give each servlet an alias name
    //<login,loginServlet>  --> <login,server.demo1.servletSample01.servlet.login.LogInServlet >
    private Map<String,String> servlet;
    //<url,login>
    private Map<String,String> mapping;

    public ServletContext(){
        servlet = new HashMap<>();
        mapping = new HashMap<>();
    }

    public Map<String, String> getServlet() {
        return servlet;
    }

    public Map<String, String> getMapping() {
        return mapping;
    }

    public void setServlet(Map<String, String> servlet) {
        this.servlet = servlet ;
    }

    public void setMapping(Map<String, String> mapping) {
        this.mapping = mapping;
    }
}
