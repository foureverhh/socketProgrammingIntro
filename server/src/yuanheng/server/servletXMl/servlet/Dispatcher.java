package yuanheng.server.servletXMl.servlet;


import yuanheng.server.servletXMl.WebApp;
import yuanheng.server.servletXMl.utils.Request;
import yuanheng.server.servletXMl.utils.Response;

import java.io.IOException;
import java.net.Socket;




/*
* One request and one response become one instance
* */
public class Dispatcher implements Runnable{
    private Socket client;
    private Request req;
    private Response res;
    private int code = 200;

    public Dispatcher(Socket client)  {
        this.client = client;
        try {
            this.req = new Request(client.getInputStream());
            this.res = new Response(client.getOutputStream());
        } catch (IOException e) {
            code = 500;

        }
    }

    @Override
    public void run() {

       Servlet servlet = null;
        try {
            servlet =  WebApp.getServlet(req.getUrl());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        if(servlet == null){
            this.code = 404;
        }else{
            try {
                servlet.service(req,res);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        try{
            res.pushToClient(code);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
