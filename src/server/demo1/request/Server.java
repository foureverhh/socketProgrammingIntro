package server.demo1.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

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
            StringBuilder message = new StringBuilder("");
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String s = null;
            while((s=reader.readLine()).length()>0){
                message.append(s);
                message.append("\r\n");
                if(null == s)
                    break;
            }
            //接收到了客户端的信息
            String requestInfo = message.toString().trim();
            System.out.println("Message from client is: "+requestInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Stop server
    public void stop(){

    }
}
