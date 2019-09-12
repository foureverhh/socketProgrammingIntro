package server.demo1.servletSample.servlet;

import server.demo1.servletSample.utils.Request;
import server.demo1.servletSample.utils.Response;

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
           return;
        }
    }

    @Override
    public void run() {
        Servlet servlet = new Servlet();
        servlet.service(req,res);
        try {
            res.pushToClient(code);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
