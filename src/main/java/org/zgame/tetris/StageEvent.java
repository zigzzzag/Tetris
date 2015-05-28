/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zgame.tetris;

/**
 * @author user
 */
public class StageEvent {

    public static enum EventTypes {

        SCREEN_CLICK
    }

    private EventTypes event;

    public StageEvent(EventTypes event) {
        this.event = event;
    }

    public EventTypes getEvent() {
        return event;
    }

    public void setEvent(EventTypes event) {
        this.event = event;
    }
}
