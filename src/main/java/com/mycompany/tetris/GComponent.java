/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tetris;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author user
 */
public abstract class GComponent {

    private static final Logger log = LoggerFactory.getLogger(GComponent.class);
    private List<GComponent> childElements = new LinkedList<GComponent>();
    private GComponent parent = null;
    protected String componentName = "";
    protected int width = 0;
    protected int height = 0;
    protected int componentPosX = 0;
    protected int componentPosY = 0;
    private GComponentClickAction action = null;

    public String getComponentName() {
        return componentName;
    }

    public GComponentClickAction getAction() {
        return action;
    }

    public void setAction(GComponentClickAction action) {
        this.action = action;
    }

    public void setComponentPosX(int componentPosX) {
        this.componentPosX = componentPosX;
    }

    public void setComponentPosY(int componentPosY) {
        this.componentPosY = componentPosY;
    }

    public void appendChildElement(GComponent element) {
        element.parent = this;
        childElements.add(element);
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public GComponent(String name) {
        this.componentName = name;
    }

    public boolean processClickAction(ScreenClickEvent evt) {
        Iterator<GComponent> it = new LinkedList(childElements).iterator();
        boolean process;
        while (it.hasNext()) {
            GComponent comp = it.next();
            if (process = comp.processClickActionOffset(componentPosX, componentPosY, evt)) {
                return process;
            }
        }

        if (evt.getX() >= componentPosX && evt.getX() <= componentPosX + width
                && evt.getY() >= componentPosY && evt.getY() <= componentPosY + height && action != null) {
            log.info("clickAction: {}", this.getComponentName());
            return action.actionClick(this, evt);
        }
        return false;
    }

    public boolean processClickActionOffset(int offsetX, int offsetY, ScreenClickEvent evt) {
        Iterator<GComponent> it = new LinkedList(childElements).iterator();
        boolean process;
        while (it.hasNext()) {
            if (process = it.next().processClickActionOffset(componentPosX + offsetX, componentPosY + offsetY, evt)) {
                return process;
            }
        }

        if (evt.getX() >= componentPosX + offsetX && evt.getX() <= componentPosX + width + offsetX
                && evt.getY() >= componentPosY + offsetY && evt.getY() <= componentPosY + height + offsetY && action != null) {
            log.info("clickAction offset: {}", this.getComponentName());
            return action.actionClick(this, evt);
        }
        return false;
    }

    public void renderChildComponents(Graphics2D gr) {
        Iterator<GComponent> it = new LinkedList(childElements).iterator();
        while (it.hasNext()) {
            GComponent next = it.next();
            next.renderComponentOffset(componentPosX, componentPosY, gr);
            if (next.childElements.size() > 0) {
                next.renderChildComponentsOffset(componentPosX, componentPosY, gr);
            }
        }
    }

    public void renderChildComponentsOffset(int offsetX, int offsetY, Graphics2D gr) {
        Iterator<GComponent> it = new LinkedList(childElements).iterator();

        while (it.hasNext()) {
            GComponent next = it.next();
            next.renderComponentOffset(offsetX + componentPosX, offsetY + componentPosY, gr);
        }
    }

    public void renderComponent(Graphics2D gr) {
        renderComponentOffset(0, 0, gr);
        renderChildComponents(gr);
    }

    protected abstract void renderComponentOffset(int offsetX, int offsetY, Graphics2D gr);

    public void render(Graphics2D gr) {
        renderComponent(gr);
    }
}
