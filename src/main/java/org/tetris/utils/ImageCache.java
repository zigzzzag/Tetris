/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tetris.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author user
 */
public class ImageCache {

    private static ImageCache instance = null;
    private Map<String, BufferedImage> imageMap = new LinkedHashMap<String, BufferedImage>();

    private ImageCache() {
    }

    public static ImageCache getInstance() {
        if (instance == null) {
            instance = new ImageCache();
        }
        return instance;
    }

    public BufferedImage getImage(String fileName) {
        String templateFileName = fileName.toUpperCase();

        if (imageMap.containsKey(templateFileName)) {
            return imageMap.get(templateFileName);
        }
        try {
            imageMap.put(templateFileName, ImageIO.read(new FileInputStream("resources/" + fileName)));
        } catch (IOException ex) {
            Logger.getLogger(ImageCache.class.getName()).log(Level.SEVERE, null, ex);
        }
        return imageMap.get(templateFileName);
    }
}
