/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KaraokeApp;

import KaraokeApp.model.Conn;
import KaraokeApp.service.BuildBase;
import KaraokeApp.service.LoadImg;
import KaraokeApp.view.LoginFrame;
import KaraokeApp.view.PopUp;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author 84374U
 */
public class Application {
    int flag = 0;
    public static void main(String[] args) {
//        BuildBase buidBase = new BuildBase();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoginFrame login = new LoginFrame();
                login.setVisible(true);
            }
        });
    }
}
