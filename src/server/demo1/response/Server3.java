package server.demo1.response;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/*request and response*/
public class Server3 {
    private ServerSocket server;
    public static final String CRLF = "\r\n";
    public static final String BLANK = " ";


        public static void main(String[] args) throws IOException {
            Server3 server = new Server3();
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
                System.out.println(requestInfo);

                //Give client response
                StringBuilder responseContent = new StringBuilder();
                //Between response head and content,there must be a space line
                responseContent.append("<html><head><title>HTTP响应实例</title></head><body>Hello Tomcat!</body></html>");

                StringBuilder response = new StringBuilder();
                //1.HTTP协议版本、转态代码、描述
                response.append("HTTP/1.1").append(BLANK).append("200").append(BLANK).append("OK").append(CRLF);
                //2.Response head
                response.append("Server:localhost Server/0.0.1").append(CRLF);
                response.append("Date:").append(new Date()).append(CRLF);
                response.append("Content-type:text/html;charset=GBK").append(CRLF);
                response.append("Content-Length:").append(responseContent.toString().getBytes().length).append(CRLF);
                //3.Before response content
                response.append(CRLF);
                //4.content
                response.append(responseContent);

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                writer.write(response.toString());
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Stop server
        public void stop(){

        }

}
