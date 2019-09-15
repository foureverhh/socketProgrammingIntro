package server.demo1.servletXMl.webParsing;
/*
* Save Servlet
* */
public class Entity {
    private String name;
    private String clz;


    public void setName(String name) {
        this.name = name;
    }

    public void setClz(String clz) {
        this.clz = clz;
    }

    public String getName() {
        return name;
    }

    public String getClz() {
        return clz;
    }

    @Override
    public String toString() {
        return this.getName()+" "+this.getClz();
    }
}
