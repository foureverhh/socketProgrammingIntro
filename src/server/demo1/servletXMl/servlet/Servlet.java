package server.demo1.servletXMl.servlet;

import server.demo1.servletXMl.utils.Request;
import server.demo1.servletXMl.utils.Response;

public abstract class Servlet {
    public void service(Request req, Response res) throws Exception{
        this.doGet(req,res);
        this.doPost(req,res);
    }

    public abstract void doGet(Request req, Response res) throws Exception;
    public abstract void doPost(Request req, Response res)throws Exception;

}
