package server.demo1.servletSample;

import server.demo1.servletSample.utils.Request;
import server.demo1.servletSample.utils.Response;
import server.demo1.servletSample.servlet.Servlet;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*request and response*/
public class Server {
    private ServerSocket server;


        public static void main(String[] args) throws IOException {
            Server server = new Server();
            server.start();
        }

        //Start server
        public void start(){
            try {
                server = new ServerSocket(8888);

                this.receive();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Receive client
        private void receive(){
            try {
                Socket client = server.accept();
                //接收客户端请求信息
                Request request = new Request(client.getInputStream());
                System.out.println(request.toString());

                Response response = new Response(client.getOutputStream());
                new Servlet().service(request,response);
                response.pushToClient(200);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Stop server
        public void stop(){

        }

}
