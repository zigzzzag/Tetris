/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zgame.tetris;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zgame.stage.InputNameFrame;
import org.zgame.stage.WelcomeStage;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author user
 */
public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);
    private static Screen scr;
    private static String name;
    private static URL tetrisscore;

    public static void setName(String name) {
        Main.name = name;
    }

    public static String getName() {
        return name;
    }

    public static Screen getScreen() {
        return scr;
    }

    public static void main(String[] args) {
//        List<Record> rec = new ArrayList<Record>();
//        for (int i = 0; i < 10; i++) {
//            Record r = new Record();
//            r.setFIO("User_" + i);
//            r.setCount(i * 1000);
//            rec.add(r);
////            r.setId(i);
//        }
//
//        for (Record r : rec) {
//            log.info(r.getFIO() + "   " + r.getCount());
//        }
//
//        try {
//            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("resources/records.txt"));
//            oos.writeObject(rec);
//            log.info("ok   " + rec);
//            oos.close();
//        } catch (IOException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        List<Record> rec = new ArrayList<Record>();
//        try {
//            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("resources/records.txt"));
//            try {
//                rec = (ArrayList<Record>) ois.readObject();
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            ois.close();
//        } catch (IOException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        for (Record key : rec) {
//            log.info(key.getFIO() + "   " + key.getCount());
//        }

        InputNameFrame inputName = new InputNameFrame();
        inputName.go();

        try {
            tetrisscore = new URL("http://tetrisscore2.appspot.com/");
            InputStreamReader isr = new InputStreamReader(tetrisscore.openStream(), "UTF-8");
            try {
                log.info(convertStreamToString(isr));
            } finally {
                isr.close();
            }
        } catch (MalformedURLException ex) {
            log.error(ex.getMessage(), ex);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        scr = new Screen();
        scr.initScreen(new WelcomeStage());
        scr.updateLoop();
    }

    private static String convertStreamToString(java.io.InputStreamReader is) {
        java.util.Scanner s = new java.util.Scanner(is);
        String str = "";
        while (s.hasNext()) {
            str += s.next();
        }
        return str;
    }
}
