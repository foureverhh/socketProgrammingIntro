package server.demo1.servletSample.servlet;

import server.demo1.servletSample.utils.Request;
import server.demo1.servletSample.utils.Response;

public class Servlet {
    public void service(Request req, Response res){
        res.println("<html><head><title>Http Servlet</title>");
        res.println("</head><body>");
        res.println("Welcome ").println(req.getParameter("uname")).println(" back!");
        res.println("</body></html>");
    }
}
