/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zgame.components;

import org.zgame.tetris.GComponent;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

/**
 *
 * @author user
 */
public class TextCenterComponent extends GComponent {

    private String text;
    private String font;
    private Color color;
    private FontMetrics metrics;

    public void setColor(Color color) {
        this.color = color;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TextCenterComponent(String elementName, String text, String font, Color color, int posX, int posY) {
        super(elementName);
        this.text = text;
        this.font = font;
        this.color = color;
        componentPosX = posX;
        componentPosY = posY;
    }

    @Override
    protected void renderComponentOffset(int offsetX, int offsetY, Graphics2D gr) {
        if (width == 0 && height == 0) {
            metrics = gr.getFontMetrics(Font.decode(font));
            height = metrics.getHeight();
            width = metrics.stringWidth(text);
        }
        gr.setFont(Font.decode(font));
        gr.setColor(color);
        gr.drawString(text, offsetX + componentPosX - width / 2, offsetY + componentPosY + height / 2);
    }
}
