package yuanheng.server.servletXMl.servlet.register;

import yuanheng.server.servletXMl.servlet.Servlet;
import yuanheng.server.servletXMl.utils.Request;
import yuanheng.server.servletXMl.utils.Response;

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
