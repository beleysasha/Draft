package fr.it.cluster;

import javax.servlet.ServletException;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Singleton implements Interface {
    public String path="";
    public String index;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = getHtml(index);
    }

    private static final Singleton ourInstance = new Singleton();
    public static Singleton getInstance(){
        return ourInstance;
    }


    public void setPath(String path) {
        this.path = path;
    }


    public String getPath() {
        return path;
    }
    public String getHtml(String filename) {
        Path path = Paths.get(this.path+filename);
        StringBuilder builder = new StringBuilder();
        Charset charset = Charset.forName("UTF-8");
        try {
            String str;
            BufferedReader reader = Files.newBufferedReader(path,charset);
            while ((str=reader.readLine())!=null){
                builder.append(str).append("\n");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return builder.toString();
    }

}

