/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KaraokeApp.view;

import java.awt.Container;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;

/**
 *
 * @author 84374
 */
public class PopUp extends javax.swing.JFrame implements ActionListener {

    private Container c;
    private JLabel title;
    private JLabel name;
    private JLabel gender;
    private JLabel dob;
    private JLabel bankNum;

    public PopUp() {
        setTitle("Karaoke nhom 4 welcome, Sing for fun");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        title = new JLabel("Karaoke nhom 4 welcome, Sing for fun");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(300, 30);
        c.add(title);

        name = new JLabel("Name");
        name.setFont(new Font("Arial", Font.PLAIN, 30));
        name.setSize(100, 20);
        name.setLocation(100, 100);
        c.add(name);

        gender = new JLabel("Gender");
        gender.setFont(new Font("Arial", Font.ITALIC, 20));
        gender.setSize(100, 20);
        gender.setLocation(100, 200);
        c.add(gender);
        
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
