package server.demo1.capsuleResponse;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

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
                StringBuilder message = new StringBuilder();
                String s = null;
                byte[] data = new byte[20480];
                int load = client.getInputStream().read(data);

                //接收到了客户端的request
                String requestInfo = new String(data,0,load).trim();
                System.out.println("Message from client is: "+requestInfo);

                Response response = new Response(client.getOutputStream());
                response.println("<html><head><title>HTTP响应实例</title></head><body>Server!</body></html>");
                response.pushToClient(404);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Stop server
        public void stop(){

        }

}
