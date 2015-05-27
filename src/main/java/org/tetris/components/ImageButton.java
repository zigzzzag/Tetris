/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tetris.components;

import org.tetris.tetris.GComponent;
import org.tetris.utils.ImageCache;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author user
 */
public class ImageButton extends GComponent {

    private BufferedImage image;
    private String text, font;
    private Color color;

    public ImageButton(String name, String image, String text) {
        super(name);
        this.image = ImageCache.getInstance().getImage(image);
        this.text = text;
        this.font = "Arial-bold-24";
        this.color = Color.WHITE;
        width = this.image.getWidth();
        height = this.image.getHeight();
    }

    @Override
    protected void renderComponentOffset(int offsetX, int offsetY, Graphics2D gr) {
        gr.drawImage(image, null, offsetX + componentPosX, offsetY + componentPosY);
        gr.setColor(color);
        gr.setFont(Font.decode(font));
        FontMetrics metrics = gr.getFontMetrics();
        int textWidth = metrics.stringWidth(text);
        int textHeight = metrics.getHeight();

        gr.drawString(text, width / 2 + offsetX + componentPosX - textWidth / 2,
                height / 2 + offsetY + componentPosY + textHeight / 4);
    }
}
