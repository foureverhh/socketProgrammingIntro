package server.demo1.capsuleRequest;

import server.demo1.capsuleRequest.Response;

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
                response.println("<html><head><title>HTTP example</title>");
                response.println("</head><body>");
                response.println("Welcome ").println(request.getParameter("uname")).println(" come back!");
                response.println("</body></html>");
                response.pushToClient(200);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Stop server
        public void stop(){

        }

}
