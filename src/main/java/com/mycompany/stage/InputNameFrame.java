/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.stage;

import com.mycompany.tetris.Main;
import com.mycompany.utils.NameVerifier;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author user
 */
public class InputNameFrame {

    private static JFrame frame = new JFrame("Hello");
    private static Panel panel;
    int widthPanel = 300, heightPanel = 100;
    private JTextField inputName;
    private NameVerifier nv;

    public void go() {
        setUpGui();
    }

    private static String convertStreamToString(java.io.InputStreamReader is) {
        java.util.Scanner s = new java.util.Scanner(is);
        String str = "";
        while (s.hasNext()) {
            str += s.next();
        }
        return str;
    }

    private void setUpGui() {
        nv = new NameVerifier();

        panel = new Panel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);

        JLabel label = new JLabel("Input Name:");
        inputName = new JTextField(20);
//
//        String url = "http://localhost:8888/myfirstservlet";
//        InputStreamReader isr = null;
//        try {
//            isr = new InputStreamReader(new URL(url).openStream(), "UTF-8");
//        } catch (IOException ex) {
//            Logger.getLogger(InputNameFrame.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        if (isr != null) {
//            inputName.setText(convertStreamToString(isr));
//        }
//        inputName.setInputVerifier(new NameVerifier());
        panel.add(label);
        panel.add(inputName);

        JButton ok = new JButton("OK");

        panel.add(ok);

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nv.verify(inputName)) {
                    Main.setStart(true);
                    Main.setName(inputName.getText());
                    frame.dispose();
                }
            }
        });

        frame.setSize(widthPanel, heightPanel);
        frame.setResizable(false);
        frame.setVisible(true);

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();
        frame.setLocation(width / 2 - widthPanel / 2, height / 2 - heightPanel / 2);
    }

    class Panel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
        }
    }
}
