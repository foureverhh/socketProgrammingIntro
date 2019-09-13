package server.demo1.servletSample01.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class Request {
    //request method
    private String method;
    //request resource
    private String url;
    //request parameter
    private Map<String, List<String>> parameterMapValues;

    private static final String CRLF = "\r\n";
    private InputStream is;
    private String requestInfo;

    public Request(){
        method = "";
        url = "";
        parameterMapValues = new HashMap<>();
        requestInfo = "";
    }

    public Request(InputStream is){
        this();
        this.is = is;
        byte[] data = new byte[204800];
        int length = 0;
        try {
            length = this.is.read(data);

                requestInfo = new String(data,0,length);
        } catch (IOException e) {
            return;
        }
        //Analyse request info
        parseRequestInfo();
    }

    private void parseRequestInfo() {
        if(null == requestInfo || (requestInfo = requestInfo.trim()).equals("")){
            return;
        }
        //retrieve request method
        String paramString = "";
        /*
        * 从信息的首行分解出：请求方式  请求路径  请求参数（get可能存在）
         *   如：GET /index.html?uname=intputUname&pwd=inputPassword HTTP/1.1
         *
         * 如果为post方式，请求参数可能在最后正文
        * */
        //1.retrieve parameter resource ,get the first line
        String firstLine = requestInfo.substring(0,requestInfo.indexOf(CRLF));
        int index = requestInfo.indexOf("/");
        //get method
        this.method = firstLine.substring(0,index).trim();
        String urlStr = firstLine.substring(index,firstLine.indexOf("HTTP/")).trim();
        if(this.method.equalsIgnoreCase("post")){//take care of post
            this.url = urlStr;
            paramString = requestInfo.substring(requestInfo.lastIndexOf(CRLF)).trim();
        }else if(this.method.equalsIgnoreCase("get")){
            //check whether here is parameter in resource
            if(urlStr.contains("?")){
                String[] urlArray = urlStr.split("\\?");
                this.url = urlArray[0];
                paramString = urlArray[1];
            }else {
                this.url = urlStr;
            }
        }

        /*
        //2.encapsulate to Map
        if(!paramString.equals("")){
            return;
        }
        */
        parseParams(paramString);

    }

    private String decode(String value, String code){
        try {
            return java.net.URLDecoder.decode(value,code);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void parseParams(String paraString){
        //Split paraString
        //String[] parameters = paraString.split("\\&");
        StringTokenizer token = new StringTokenizer(paraString,"&");
        while (token.hasMoreTokens()){
            String keyValue = token.nextToken();
            String[] keyValues = keyValue.split("=");
            if(keyValues.length == 1){
                keyValues = Arrays.copyOf(keyValues,2);
                keyValues[1]=null;
            }
            String key = keyValues[0].trim();
            String value = keyValues[1]==null? null : decode(keyValues[1].trim(),"gbk");
            //Convert to map
            if(!parameterMapValues.containsKey(key)){
                parameterMapValues.put(key,new ArrayList<String>());
            }
            List<String> values = parameterMapValues.get(key);
            values.add(value);
        }
    }

    public String[] getParameterValues(String name){
        List<String> values = null;
        if((values = parameterMapValues.get(name))==null){
            return null;
        }
        return values.toArray(new String[0]);
    }

    public String getParameter(String name){
        String[] values = getParameterValues(name);
        if(null==values){
            return null;
        }
        return values[0];
    }

    public String getUrl() {
        return url;
    }
}
