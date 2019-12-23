package yuanheng.server.servletXMl.webParsing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* save servlet mapping
* */
public class Mapping {
    private String name;
    private List<String> urlPattern;

    public Mapping(){
        urlPattern = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrlPattern(List<String> urlPattern) {
        this.urlPattern = urlPattern;
    }

    public String getName() {
        return name;
    }

    public List<String> getUrlPattern() {
        return urlPattern;
    }

    @Override
    public String toString() {
        return this.getName()+ Arrays.toString(this.getUrlPattern().toArray());
    }
}
