/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KaraokeApp.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author 84374
 */
public class Conn {
        private static Conn single_instance = null;
  
    // Declaring a variable of type String
    public String url;
    private static final String FILE_CONFIG = "\\conFigApp.properties";
    // Constructor
    // Here we will be creating private constructor
    // restricted to this class itself
    private Conn()
    {   
            Properties properties = new Properties();
            InputStream inputStream = null;
        try {
            String currentDir = System.getProperty("user.dir");
            inputStream = new FileInputStream(currentDir + FILE_CONFIG);
            // load properties from file
            properties.load(inputStream);
            // get property by name
            url=properties.getProperty("url");
        } catch (IOException e) {
            e.printStackTrace();}
             
    }
  
    // Static method
    // Static method to create instance of Singleton class
    public static synchronized Conn getInstance()
    {
        if (single_instance == null)
            single_instance = new Conn();
  
        return single_instance;
    }
}
