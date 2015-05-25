/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.stage;

import com.mycompany.components.AudioPlay;
import com.mycompany.components.ImageButton;
import com.mycompany.components.RootComponent;
import com.mycompany.components.TextCenterComponent;
import com.mycompany.tetris.GComponent;
import com.mycompany.tetris.GComponentClickAction;
import com.mycompany.tetris.Main;
import com.mycompany.tetris.ScreenClickEvent;
import com.mycompany.tetris.StageEvent;
import com.mycompany.tetris.StageInterface;
import com.mycompany.utils.ParticleEffect;
import com.mycompany.utils.Record;
import com.mycompany.utils.TemplateOfFigure;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author user
 */
public class TetrisStage implements StageInterface, GComponentClickAction, KeyListener {

    private static final Logger log = LoggerFactory.getLogger(TetrisStage.class);
    private RootComponent root;
    private ImageButton closeButton;
    private ImageButton pauseButton;
    private ImageButton playButton;
    private TextCenterComponent label;
    private TextCenterComponent timeTCC;
    private static final byte matrX = 10;
    private static final byte matrY = 20;
    private static TemplateOfFigure figure;
    private TemplateOfFigure figureNext;
    private static byte[][] figureSave = new byte[matrY][matrX];
    private TemplateOfFigure figureShadow;
    private int cnt = 30;
    private int indentUp = 100;
    private int indentLeft = Main.getInstance().getWidth() / 2 - 5 * cnt;
    private TextCenterComponent countTCC;
    private int pointsNow;
    private static int pointsAll;
    private static int fallSpeed;
    private Color alphaBlack = new Color(0, 0, 0, 140);
    private Color alphaShadow = new Color(0, 0, 0, 140);
    private long timeDown;
    private long timeLastDown;
    private static boolean downing;

    public static byte[][] getFigureSave() {
	return figureSave;
    }

    private int getSpeed(int totalPoints) {
	if (1000 - totalPoints / 50 > 200) {
	    return 1000 - totalPoints / 50;
	}
	return 200;
    }

    public void continueGame() {
	if (!verifyGameOver()) {
	    if (timeLastDown % fallSpeed > timeDown % fallSpeed) {
		if (figure.isDownBarrier()) {
		    for (int i = 0; i < matrY; i++) {
			for (int j = 0; j < matrX; j++) {
			    figureSave[i][j] += figure.getFigure()[i][j];
			}
		    }

		    for (int i = 0; i < matrY; i++) {
			for (int j = 0; j < matrX; j++) {
			    if (figureSave[i][j] == 0) {
				break;
			    }
			    if (j == matrX - 1) {
				deleteFullLine(i);
				for (int t = 0; t < 10; t++) {
				    particles.add(new ParticleEffect(converFromIndexX(t), converFromIndexY(i)));
				}
				start = ParticleEffect.TIME;
				pointsNow++;
			    }
			}
		    }
		    if (pointsNow != 0) {
			pointsAll += countPoints(pointsNow);
			countTCC.setText("Счет: " + pointsAll);
		    }
		    if (!gameOver) {
			downing = false;
			figure = figureNext;
			figureNext = new TemplateOfFigure(new Random().nextInt(7) + 1);//new Random().nextInt(7) + 1
			int rotateRandom = new Random().nextInt(4);
			for (int i = 0; i < rotateRandom; i++) {
			    figureNext.rotate();
			}
			fallSpeed = getSpeed(pointsAll);
			pointsNow = 0;
			timeDown = 0;
			timeLastDown = 0;

//                        AudioPlay.getInstance().setAudioText("kick");
		    }
		} else {
		    figure.down();
		}
	    }
	    for (int i = 0; i < matrY; i++) {
		System.arraycopy(figure.getFigure()[i], 0, figureShadow.getFigure()[i], 0, matrX);
	    }

	    while (!figureShadow.isDownBarrier()) {
		figureShadow.down();
	    }
	}
    }

    public int countPoints(int points) {
	switch (points) {
	    case 1: {
		return 100;
	    }
	    case 2: {
		return 300;
	    }
	    case 3: {
		return 700;
	    }
	    case 4: {
		return 1500;
	    }
	}
	return 0;
    }
    private List<ParticleEffect> particles = new ArrayList<ParticleEffect>();
    private int start = 0;

    public void deleteFullLine(int lineNumber) {
	for (int i = lineNumber - 1; i >= 0; i--) {
	    for (int j = 0; j < matrX; j++) {
		figureSave[i + 1][j] = figureSave[i][j];
		figureSave[i][j] = 0;
	    }
	}
    }

    public TetrisStage() {
//        Thread t = new Thread(AudioPlay.getInstance());
//        t.start();
//        AudioPlay.loadSounds();

	root = new RootComponent();
	root.setWidth(Main.getInstance().getWidth());
	root.setHeight(Main.getInstance().getHeight());

	fallSpeed = 1000;
	downing = false;

	figureShadow = new TemplateOfFigure();

//        for (int i = 19; i >= 6; i--) {
//            for (int j = 0; j < 10; j++) {
//                figureSave[i][9] = 7;
//                figureSave[i][8] = 7;
//                figureSave[i][7] = 7;
//                figureSave[i][0] = 7;
//                figureSave[i][1] = 7;
//                figureSave[i][2] = 7;
//                figureSave[i][3] = 7;
//                figureSave[i][4] = 7;
//            }
//        }
//        figureSave[15][6] = 2;
//        figureSave[10][7] = 3;
	figure = new TemplateOfFigure(new Random().nextInt(7) + 1);
	figureNext = new TemplateOfFigure(new Random().nextInt(7) + 1);//new Random().nextInt(7) + 1

	closeButton = new ImageButton("CLOSE", "blueButton.png", "ВЫХОД");
	closeButton.setComponentPosX(Main.getInstance().getWidth() - closeButton.getWidth() - 20);
	closeButton.setComponentPosY(20);
	root.appendChildElement(closeButton);
	closeButton.setAction((GComponentClickAction) this);

	pauseButton = new ImageButton("PAUSE", "pauseButton.png", "");
	pauseButton.setComponentPosX(pauseButton.getWidth() + 20);
	pauseButton.setComponentPosY(20);
	root.appendChildElement(pauseButton);
	pauseButton.setAction((GComponentClickAction) this);

	playButton = new ImageButton("PLAY", "playButton.png", "");
	playButton.setComponentPosX(2 * pauseButton.getWidth() + 40);
	playButton.setComponentPosY(20);
	root.appendChildElement(playButton);
	playButton.setAction((GComponentClickAction) this);

	label = new TextCenterComponent("LABEL", "Tetris game", "Arial-bold-48", Color.BLACK, root.getWidth() / 2, 50);
	root.appendChildElement(label);

	timeTCC = new TextCenterComponent("LABEL", "", "Arial-normal-12", Color.BLACK, 50, 250);
	root.appendChildElement(timeTCC);

	countTCC = new TextCenterComponent("COUNT", "Счет: 0", "Arial-bold-48", Color.white, root.getWidth() - 200, 200);
	root.appendChildElement(countTCC);
    }

    public int converFromIndexX(int j) {
	return j * cnt + indentLeft;
    }

    public int converFromIndexY(int i) {
	return i * cnt + indentUp;
    }
    private long timeNow;
    private long timeLast;
    private long fullTimeLast = System.currentTimeMillis();
    private long frameAll;
    private long fpsMiddle;
    private long fpsLastSecond;
    private long fpsLastSecondSave;
    private boolean gameOver = true;
    private static boolean pause;

    public void setGameOver(boolean gameOver) {
	this.gameOver = gameOver;
    }

    @Override
    public void render(Graphics2D gr2d) {

	if (!gameOver && !pause) {
	    continueGame();
	}
	if (!pause) {
	    timeLast = timeNow;
	    timeNow += System.currentTimeMillis() - fullTimeLast;
	    timeLastDown = timeDown;
	    timeDown += System.currentTimeMillis() - fullTimeLast;
	}
	fullTimeLast = System.currentTimeMillis();

	frameAll++;
	fpsMiddle = frameAll * 1000 / (timeNow == 0 ? 1 : timeNow);

	fpsLastSecond++;

	timeTCC.setText("Время: " + timeNow);
	root.render(gr2d);

	gr2d.setFont(Font.decode("Arial-norm-14"));

	gr2d.setColor(Color.ORANGE);
	gr2d.drawString(timeDown + "", 180, 200);
	gr2d.drawString("FPS_MIDDLE: " + fpsMiddle, 180, 220);

	if (timeLast % 1000 > timeNow % 1000) {
	    fpsLastSecondSave = fpsLastSecond;
	    fpsLastSecond = 0;
	}
	gr2d.drawString("FPS_LAST_SECOND: " + fpsLastSecondSave, 180, 240);

	gr2d.setColor(alphaBlack);
	gr2d.fillRoundRect(indentLeft - 10, indentUp - 10, cnt * 10 + 20, cnt * 20 + 20, 10, 10);

	gr2d.setColor(Color.BLACK);
	gr2d.drawRect(indentLeft, indentUp, cnt * 10, cnt * 20);

	for (int i = 0; i < matrY; i++) {
	    for (int j = 0; j < matrX; j++) {
		if (figureShadow.getFigure()[i][j] != 0) {
		    gr2d.setColor(alphaShadow);
		    gr2d.fillRect(converFromIndexX(j), converFromIndexY(i), cnt, cnt);
		    gr2d.setColor(Color.black);
		    gr2d.drawRect(converFromIndexX(j), converFromIndexY(i), cnt, cnt);
		}
		if (figure.getFigure()[i][j] != 0) {
		    gradientFigure(gr2d, lightColor(figure.getFigure()[i][j]),
			    darkColor(figure.getFigure()[i][j]), converFromIndexX(j), converFromIndexY(i));
		}
		if (figureNext.getFigure()[i][j] != 0) {
		    gradientFigure(gr2d, lightColor(figureNext.getFigure()[i][j]),
			    darkColor(figureNext.getFigure()[i][j]), converFromIndexX(j) - 300, converFromIndexY(i));
		}
	    }
	}

	for (int i = 0; i < matrY; i++) {
	    for (int j = 0; j < matrX; j++) {
//                gr2d.drawString(String.valueOf(figure.getFigure()[i][j]), 50 + 10 * j, 450 + 15 * i);
//                gr2d.drawString(String.valueOf(figureSave[i][j]), 250 + 10 * j, 450 + 15 * i);

		if (figureSave[i][j] != 0) {
		    gradientFigure(gr2d, lightColor(figureSave[i][j]), darkColor(figureSave[i][j]), converFromIndexX(j), converFromIndexY(i));
		}
		gr2d.setColor(Color.black);
	    }
	}

	if (start < 0) {
	    start = 0;
	    particles.clear();
	} else {
	    start--;
	    for (ParticleEffect p : particles) {
		p.render(gr2d);
	    }
	}

    }

    public Color lightColor(int index) {
	switch (index) {
	    case 1: {
		return new Color(0, 0, 205);
	    }
	    case 2: {
		return new Color(0, 205, 100);
	    }
	    case 3: {
		return new Color(205, 0, 0);
	    }
	    case 4: {
		return new Color(218, 112, 214);
	    }
	    case 5: {
		return new Color(255, 255, 0);//yellow
	    }
	    case 6: {
		return Color.gray;
	    }
	    case 7: {
		return new Color(255, 165, 0);//orange
	    }
	    case 8: {
		return new Color(0, 255, 0);//lime
	    }
	}
	return new Color(0, 0, 0);
    }

    public Color darkColor(int index) {
	switch (index) {
	    case 1: {
		return new Color(0, 0, 139);
	    }
	    case 2: {
		return new Color(0, 139, 0);
	    }
	    case 3: {
		return new Color(139, 0, 0);
	    }
	    case 4: {
		return new Color(199, 21, 133);//dark pink
	    }
	    case 5: {
		return new Color(255, 165, 0);//orange
	    }
	    case 6: {
		return Color.darkGray;
	    }
	    case 7: {
		return new Color(255, 120, 0);//dark orange
	    }
	    case 8: {
		return new Color(50, 205, 50);//limegreen
	    }
	}
	return new Color(0, 0, 0);
    }

    public void gameReset() {
	for (int i = 0; i < matrY; i++) {
	    for (int j = 0; j < matrX; j++) {
		figureSave[i][j] = 0;
	    }
	}
	fallSpeed = 1000;
	gameOver = true;
	figure = new TemplateOfFigure(new Random().nextInt(6) + 1);
    }

    public Boolean verifyGameOver() {
	boolean over = false;
	for (int j = 2; j < matrX - 2; j++) {
	    if (figureSave[0][j] != 0) {
		over = true;
	    }
	}

	if (over || figureSave[1][4] != 0 || figureSave[1][5] != 0) {
	    gameOver = true;

	    log.info("GAME OVER!!!");

	    List<Record> recordList = Record.getRecord();
	    Record record = new Record();
	    record.setCount(pointsAll);
	    record.setFIO(Main.getName());
	    recordList.add(record);
	    Record.setRecord(recordList);

	    GameOverStage gos = new GameOverStage();
	    gos.setReturnStage(this);
	    Main.getInstance().setCurrentStage(gos);
	    return true;
	}
	return false;
    }

    @Override
    public void processEvent(StageEvent evt) {
	root.processClickAction((ScreenClickEvent) evt);
    }

    @Override
    public boolean actionClick(GComponent target, ScreenClickEvent event) {
	final String action = target.getComponentName();
	if ("CLOSE".equals(action)) {
	    gameReset();
	    Main.getInstance().setCurrentStage(new WelcomeStage());
	}

	if ("PAUSE".equals(action)) {
	    pause = true;
	}

	if ("PLAY".equals(action)) {
	    pause = false;
	}
	return true;
    }

    private void gradientFigure(Graphics2D gr2d, Color color1, Color color2, int x, int y) {
	GradientPaint gp;
	gp = new GradientPaint(x, y, color1, x + cnt, y + cnt, color2, false);
	gr2d.setPaint(gp);
	gr2d.fillRect(x, y, cnt, cnt);

	gp = new GradientPaint(x + 2, y + 2, color2,
		x + cnt - 2, y + cnt - 2, color1, false);
	gr2d.setPaint(gp);
	gr2d.fillRect(x + 2, y + 2, cnt - 4, cnt - 4);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
	if (!pause && !downing) {
	    switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT: {
		    figure.right();
		    break;
		}
		case KeyEvent.VK_LEFT: {
		    figure.left();
		    break;
		}
		case KeyEvent.VK_DOWN: {
		    figure.down();
		    break;
		}
		case KeyEvent.VK_UP: {
		    figure.rotate();
		    break;
		}
		case KeyEvent.VK_SPACE: {
		    downing = true;
		    fallSpeed = 20;
		    break;
		}
	    }
	}
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
