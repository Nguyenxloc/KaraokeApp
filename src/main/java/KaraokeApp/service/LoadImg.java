/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package KaraokeApp.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import javax.imageio.ImageIO;

/**
 *
 * @author 84374
 */
public class LoadImg {

    private byte[] imgByte;

    public LoadImg() {
    }

    public String getDirToImg() {
        String dir=null;
        try {
            String path = "images/karaoke.png";
            File file = new File(path);
            String absolutePath = file.getAbsolutePath();
            dir=absolutePath;
            System.out.println(dir);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dir;
    }
    public String getAddImg() {
        String dirAdd=null;
        try {
            String path = "images/add.png";
            File file = new File(path);
            String absolutePath = file.getAbsolutePath();
            dirAdd=absolutePath;
            System.out.println(dirAdd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(dirAdd);
        return dirAdd;
    }
    public String getUpDateImg() {
        String dirUp=null;
        try {
            String path = "images/update.png";
            File file = new File(path);
            String absolutePath = file.getAbsolutePath();
            dirUp=absolutePath;
            System.out.println(dirUp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dirUp;
    }
    
    public String getSearchImg() {
        String dirSearch=null;
        try {
            String path = "images/search.jpg";
            File file = new File(path);
            String absolutePath = file.getAbsolutePath();
            dirSearch=absolutePath;
            System.out.println(dirSearch);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dirSearch;
    }
    
    public String getRevenueImg() {
        String dirRevenue=null;
        try {
            String path = "images/revenue.png";
            File file = new File(path);
            String absolutePath = file.getAbsolutePath();
            dirRevenue=absolutePath;
            System.out.println(dirRevenue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dirRevenue;
    }
    public String getGenBillImg() {
        String dirGen=null;
        try {
            String path = "images/genbill.png";
            File file = new File(path);
            String absolutePath = file.getAbsolutePath();
            dirGen=absolutePath;
            System.out.println(dirGen);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dirGen;
    }

    
    
    
}
