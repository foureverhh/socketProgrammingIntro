package server.demo1.servletSample01.utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;

/*Capsule response info*/
public class Response {
    private static final String CRLF = "\r\n";
    private static final String BLANK = " ";

    //Content info
    private StringBuilder content;
    //Head info
    private StringBuilder headInfo;
    //Content length
    private int contentLength;

    //StreamWriter to write out info
    BufferedWriter bw;



    public Response(){
        headInfo = new StringBuilder();
        contentLength = 0;
        content = new StringBuilder();
    }

    public Response(OutputStream outputStream){
        this();
        bw = new BufferedWriter(new OutputStreamWriter(outputStream));
    }

    /*Build up content*/
    public Response print(String info){
        content.append(info);
        contentLength+=info.getBytes().length;

        return this;
    }
    /*Build up content with \r\n*/
    public Response println(String info){
        content.append(info).append(CRLF);
        contentLength+=(info+CRLF).getBytes().length;
        return this;
    }

    /*Build up response header*/
    private void createHead(int code){
        //1.HTTP协议版本、转态代码、描述
        headInfo.append("HTTP/1.1").append(BLANK).append(code).append(BLANK);
        switch (code){
            case 200:
                headInfo.append("OK");
                break;
            case 404:
                headInfo.append("Not Found");
                break;
            case 500:
                headInfo.append("Bad Gateway");
                break;
        }
        headInfo.append(CRLF);
        //2.Response head
        headInfo.append("Server:localhost Server/0.0.1").append(CRLF);
        headInfo.append("Date:").append(new Date()).append(CRLF);
        headInfo.append("Content-type:text/html;charset=GBK").append(CRLF);
        headInfo.append("Content-Length:").append(contentLength).append(CRLF);
        //3.Before response content!!!!
        headInfo.append(CRLF);
        /*
        //4.content
        headInfo.append(content);
        */
    }

    public void pushToClient(int code) throws IOException {
        if(null==headInfo){
            code =500;
        }
        createHead(code);
        //Head info
        bw.append(headInfo.toString());
        //a separation of new line between head and content
        //bw.append(CRLF);
        bw.append(content.toString());
        bw.flush();
        bw.close();
    }
}
