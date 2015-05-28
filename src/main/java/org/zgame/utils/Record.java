/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zgame.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author user
 */
public class Record implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(Record.class);
    private static final long serialVersionUID = 6529685098267757690L;
    private String id;
    private String FIO;
    private Integer count;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public static List<Record> getRecord() {
        List<Record> rec = new ArrayList<Record>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("resources/records.txt"));
            try {
                rec = (ArrayList<Record>) ois.readObject();
            } catch (ClassNotFoundException ex) {
                log.error(ex.getMessage(), ex);
            }
            ois.close();
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
        return rec;
    }

    public static void setRecord(List<Record> rec) {
        CountComparator cc = new CountComparator();
        Collections.sort(rec, cc);
        int size = rec.size();
        if (size > 10) {
            for (int i = 10; i < size; i++) {
                rec.remove(i);
            }
        }
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("resources/records.txt"));
            oos.writeObject(rec);
            log.info("writeObject...  {}", rec);
            oos.close();
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}
