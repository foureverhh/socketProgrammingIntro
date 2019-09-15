package server.demo1.servletXMl.servlet.register;

import server.demo1.servletXMl.servlet.Servlet;
import server.demo1.servletXMl.utils.Request;
import server.demo1.servletXMl.utils.Response;

public class RegisterServlet extends Servlet {
    @Override
    public void doGet(Request req, Response res) throws Exception {

    }

    @Override
    public void doPost(Request req, Response rep) throws Exception {
   /*     res.println("<html><head><title>注册成功</tile>");
        res.println("</head><body>");
        res.println("你的用户名为： ").println(req.getParameter("uname")).println(" !");
        res.println("</body></html>");*/

        rep.println("<html><head><title>Http Servlet</title>");
        rep.println("</head><body>");
        rep.println("You registered as ").println(req.getParameter("uname")).println(" !");
        rep.println("</body></html>");
    }
}
