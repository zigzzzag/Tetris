/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zgame.utils;

import java.util.Comparator;

/**
 * @author user
 */
public class CountComparator implements Comparator<Record> {

    @Override
    public int compare(Record o1, Record o2) {
        return o2.getCount().compareTo(o1.getCount());
    }
}
