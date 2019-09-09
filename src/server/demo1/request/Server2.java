package server.demo1.request;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {
    private ServerSocket server;


        public static void main(String[] args) throws IOException {
            Server2 server = new Server2();
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
                StringBuilder message = new StringBuilder("");
                String s = null;
                byte[] data = new byte[20480];
                int load = client.getInputStream().read(data);

                //接收到了客户端的信息
                String requestInfo = new String(data,0,load).trim();
                System.out.println("Message from client is: "+requestInfo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Stop server
        public void stop(){

        }

}
