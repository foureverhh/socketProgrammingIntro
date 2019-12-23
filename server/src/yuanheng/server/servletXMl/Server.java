package yuanheng.server.servletXMl;



import yuanheng.server.servletXMl.servlet.Dispatcher;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*request and response*/
public class Server {
    private ServerSocket server;
    private boolean isShutDown = false;

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.start(8888);
    }

    //Start server
    public void start(){
        try {
            start(8888);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Start server
    public void start(int port) throws IOException {
        try {
            server = new ServerSocket(port);
            this.receive();
        } catch (IOException e) {
            stop();
        }
    }
        //Receive client
        private void receive(){
            try{
                while(!isShutDown){
                    Socket client = server.accept();
                    Dispatcher dispatcher = new Dispatcher(client);
                    new Thread(dispatcher).start();
                }
                /*
                //Use dispatcher instead
                //接收客户端请求信息
                Request request = new Request(client.getInputStream());
                //System.out.println(request.toString());
                Response response = new Response(client.getOutputStream());
                */

                /*
                //Move to Dispatcher.run() to realise multi-thread
                Servlet servlet = new Servlet();
                servlet.service(request,response);
                response.pushToClient(200);
                */
            } catch (IOException e) {
                try {
                    stop();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                e.printStackTrace();
            }
        }

        //Stop server
        public void stop() throws IOException {
            isShutDown = true;
            server.close();
        }

}
