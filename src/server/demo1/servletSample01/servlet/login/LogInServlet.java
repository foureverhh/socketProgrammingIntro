package server.demo1.servletSample01.servlet.login;

import server.demo1.servletSample01.servlet.Servlet;
import server.demo1.servletSample01.utils.Request;
import server.demo1.servletSample01.utils.Response;

public class LogInServlet extends Servlet {
    @Override
    public void doGet(Request req, Response rep) throws Exception {
      /*  rep.println("<html><head><title>Http Servlet</title>");
        rep.println("</head><body>");
        rep.println("Welcome ").println(req.getParameter("uname")).println(" back!");
        rep.println("</body></html>");*/
        String name = req.getParameter("uname");
        String pwd = req.getParameter("pwd");
        if(login(name,pwd)){
            rep.println("Log in succeed.");
        }else{
            rep.println("Log in failed.");
        }

    }

    public boolean login(String name, String pwd){
        return name.equals("hh")&&pwd.equals("123");
    }

    @Override
    public void doPost(Request req, Response rep) throws Exception {

    }
}
