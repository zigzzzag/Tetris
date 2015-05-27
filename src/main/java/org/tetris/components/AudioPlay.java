/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tetris.components;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mnikiforov
 */
public class AudioPlay implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(AudioPlay.class);
    private final int BUFFER_SIZE = 1024;
    private AudioInputStream audioStream;
    private AudioFormat audioFormat;
    private SourceDataLine sourceLine;
    private static Map<String, byte[]> soundData = new HashMap<String, byte[]>();
    private static AudioPlay instance;
    private String audioText;
    private Queue<String> queueText = new ConcurrentLinkedQueue<String>();
    private boolean audioStop = false;

    public static AudioPlay getInstance() {
	if (instance == null) {
	    instance = new AudioPlay();
	    try {
		loadSounds();
	    } catch (UnsupportedAudioFileException ex) {
		log.error(ex.getMessage(), ex);
	    } catch (IOException ex) {
		log.error(ex.getMessage(), ex);
	    }
	}
	return instance;
    }

    private void initAudio() {
	try {
	    audioStream = AudioSystem.getAudioInputStream(new ByteArrayInputStream(soundData.get("0")));
	} catch (Exception e) {
	    return;
	}
	audioFormat = new AudioFormat(16000.0f, 16, 1, false, false);

	DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
	try {
	    sourceLine = (SourceDataLine) AudioSystem.getLine(info);
	    sourceLine.open(audioFormat);
	    //sourceLine.open();
	} catch (Exception e) {
	    log.error(e.getMessage(), e);
	}
    }

    private void doneAudio() {
	sourceLine.drain();
	sourceLine.close();
    }

    private void playAudio(String text) {
//        text = text.replace(" 0 ", " ");
//        String[] a = text.split("\\s");
//        for (String num : a) {

	try {
	    audioStream = AudioSystem.getAudioInputStream(new ByteArrayInputStream(soundData.get(text)));
	    if (!sourceLine.isActive()) {
		sourceLine.start();
	    }
	    int nBytesRead = 0;
	    byte[] abData = new byte[BUFFER_SIZE];
	    while (nBytesRead != -1) {
		try {
		    nBytesRead = audioStream.read(abData);
		} catch (IOException e) {
		    log.error(e.getMessage(), e);
		}
		if (nBytesRead >= 0) {
		    sourceLine.write(abData, 0, nBytesRead);
		}
	    }
	} catch (Exception ex) {
	    log.error(ex.getMessage(), ex);
	}
    }

    public void run() {
	initAudio();
	while (!audioStop) {
	    if (queueText.isEmpty()) {
		try {
		    Thread.sleep(100);
		} catch (InterruptedException e) {
		    audioStop = true;
		}
		continue;
	    }

	    String num = queueText.poll();
	    log.info("num: {}", num);
	    playAudio(num);
	}
	doneAudio();
    }

    private static void loadSound(File file) throws UnsupportedAudioFileException, IOException {

	FileInputStream fis = new FileInputStream(file);
	byte[] data = new byte[fis.available()];
	fis.read(data);
	fis.close();

	soundData.put(file.getName().split("\\.")[0], data);
    }

    private static void loadSounds() throws UnsupportedAudioFileException, IOException {
	File dir = new File("digits");

	File[] list = dir.listFiles();
	for (File file : list) {
	    if (file.getName().endsWith("wav")) {
		loadSound(file);
	    }
	}
    }

    public String getAudioText() {
	return audioText;
    }

    public void setAudioText(String audioText) {
	this.audioText = audioText.toLowerCase();
	log.info("this.audioText: {}", this.audioText);
	queueText.add(this.audioText);
    }

    public boolean isAudioStop() {
	return audioStop;
    }

    public void setAudioStop(boolean audioStop) {
	this.audioStop = audioStop;
    }

    public static void main(String[] args) {
	Thread t = new Thread(AudioPlay.getInstance());
	t.start();

	try {
	    System.out.println("");
	    AudioPlay.getInstance().loadSounds();
	    AudioPlay.getInstance().setAudioText("kick");
	    AudioPlay.getInstance().setAudioText("kick");
	    AudioPlay.getInstance().setAudioText("kick");
	    AudioPlay.getInstance().setAudioText("kick");
	    AudioPlay.getInstance().setAudioText("kick");
	    AudioPlay.getInstance().setAudioText("kick");
	    AudioPlay.getInstance().setAudioText("kick");
	    AudioPlay.getInstance().setAudioText("kick");
	    AudioPlay.getInstance().setAudioText("kick");
	    AudioPlay.getInstance().setAudioText("kick");
	    AudioPlay.getInstance().setAudioText("kick");
	    AudioPlay.getInstance().setAudioText("kick");
	    AudioPlay.getInstance().setAudioText("kick");
	    AudioPlay.getInstance().setAudioText("kick");
	    AudioPlay.getInstance().setAudioText("kick");
//            AudioPlay.getInstance().setAudioText("client 222 window 310");
	} catch (Exception e) {
	    log.error(e.getMessage(), e);
	}
    }
}
