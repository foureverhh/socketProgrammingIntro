package server.demo1.servletSample01;

import server.demo1.servletSample01.servlet.Servlet;
import server.demo1.servletSample01.servlet.ServletContext;
import server.demo1.servletSample01.servlet.login.LogInServlet;
import server.demo1.servletSample01.servlet.register.RegisterServlet;

import java.util.Map;

public class WebApp {
    private static ServletContext context;
    static {
        context = new ServletContext();
        Map<String,String> mapping = context.getMapping();
        mapping.put("/login","login");
        mapping.put("/log","login");
        mapping.put("/reg","register");
        mapping.put("/register","register");

        Map<String,Servlet> servlet = context.getServlet();
        servlet.put("login",new LogInServlet());
        servlet.put("register", new RegisterServlet());
    }

    public static Servlet getServlet(String url){
        if(url == null || url.trim().equals("")){
            return null;
        }
        return context.getServlet().get(context.getMapping().get(url));

    }

}
