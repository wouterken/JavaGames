package com.pico.display;


import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.Canvas;

import com.pico.controllers.KeyController;
import com.pico.controllers.MessageKeys;
import com.pico.controllers.ModKeys;
import com.pico.controllers.MouseController;
import com.pico.controllers.MouseEventScaler;
import com.pico.display.screens.GameScreen;
import com.pico.display.screens.Screen;
import com.pico.objects.characters.player.Player;
import com.pico.prefs.PREFS;
import com.pico.system.Game;



public class Controller extends Thread {

	public static Frame gameFrame;
	public static Canvas gameCanvas;
	public static MouseEventScaler mouse;
	public static KeyController keys;
	private static ModKeys modkeys;
	private static MessageKeys messageKeys;
	private static BufferedImage background;
	
	private static GraphicsConfiguration config =
            GraphicsEnvironment.getLocalGraphicsEnvironment()
                    .getDefaultScreenDevice()
                    .getDefaultConfiguration();
	private static BufferStrategy strategy;
	private static Screen screen;

// create a hardware accelerated image
public static final BufferedImage create(final int width, final int height,
            final boolean alpha) {
    return config.createCompatibleImage(width, height, alpha
                    ? Transparency.TRANSLUCENT : Transparency.OPAQUE);
}
private static Controller instance;

	private Controller(){
		
	}
	
	public static Controller getInstance(){
		if(instance == null){
			instance = new Controller();
		}
		return instance;
	}
	public static void setScreen(Screen _screen){
		getInstance().switchScreen(_screen);
	}
	public void switchScreen(Screen _screen){
		screen = _screen;
		keys.clearAll();
		keys.addAll(screen.getKeyListeners());
	}
	public void init(Screen _screen){
		keys = KeyController.getInstance();
		modkeys = new ModKeys();

		mouse = new MouseEventScaler();
		screen = _screen;
		Player player = Player.getInstance();
		((GameScreen)(screen)).setPlayer(player);
	
		gameFrame = new Frame();
		//gameCanvas = Canvas.getInstance();
		gameCanvas = new Canvas();
		gameCanvas.setSize(PREFS.GAMEWIDTH, PREFS.GAMEWIDTH);
		gameFrame.add(gameCanvas,0);
		gameFrame.setSize(PREFS.GAMEWIDTH,PREFS.GAMEHEIGHT);
		
		
		gameFrame.addKeyListener(keys);
		gameFrame.addMouseListener(mouse);
		gameFrame.addMouseMotionListener(mouse);
		
		//gameCanvas.setScreen(screen);
		//PainterThread.init(gameCanvas);
		
		Rectangle bounds = gameFrame.getBounds();
		gameFrame.setBounds(new Rectangle(bounds.x,bounds.y,PREFS.WINDOWWIDTH,PREFS.WINDOWHEIGHT));
		gameFrame.setVisible(true);
		
		background = create(PREFS.GAMEWIDTH, PREFS.GAMEHEIGHT, false);
		gameCanvas.createBufferStrategy(2);
        do {
                strategy = gameCanvas.getBufferStrategy();
        } while (strategy == null);
        start();
	}
	private Graphics2D graphics;
	private Graphics2D backgroundGraphics;
	private boolean isRunning = true;
	private long timeNow;
	private long timeSinceLast;
	private long timeLast;
	private int totalFPS;
	private int numberOfRefreshes;

private Graphics2D getBuffer() {
    if (graphics == null) {
            try {
                    graphics = (Graphics2D) strategy.getDrawGraphics();
            } catch (IllegalStateException e) {
                    return null;
            }
    }
    return graphics;
}
private boolean updateScreen() {
    graphics.dispose();
    graphics = null;
    try {
            strategy.show();
            Toolkit.getDefaultToolkit().sync();
            return (!strategy.contentsLost());

    } catch (NullPointerException e) {
            return true;

    } catch (IllegalStateException e) {
            return true;
    }
}
public void run() {
    backgroundGraphics = (Graphics2D) background.getGraphics();
    long fpsWait = (long) (1.0 / 1000 * 1000);
    main: while (isRunning ) {
            long renderStart = System.nanoTime();


            // Update Graphics
            do {
                    Graphics2D bg = getBuffer();
                    if (!isRunning) {
                            break main;
                    }
                    timeNow = System.currentTimeMillis();
            		timeSinceLast = timeNow - timeLast;
            		PREFS.CURFPS = (int) (1000/timeSinceLast);
            		totalFPS += PREFS.CURFPS;
            		numberOfRefreshes++;
            		PREFS.AVERAGEFPS = totalFPS/numberOfRefreshes ;
            		timeLast = timeNow;
                    screen.draw(backgroundGraphics, background.getWidth(), background.getHeight());
                    
                    bg.drawImage(background, 0, 0, gameFrame.getWidth(), gameFrame.getHeight(),0,0,background.getWidth(),background.getHeight(),null);
                    
                    bg.dispose();
            } while (!updateScreen());

            // Better do some FPS limiting here
            long renderTime = (System.nanoTime() - renderStart) / 1000000;
            try {
                    Thread.sleep(Math.max(0, fpsWait - renderTime));
            } catch (InterruptedException e) {
                    Thread.interrupted();
                    break;
            }
            renderTime = (System.nanoTime() - renderStart) / 1000000;

    }
   
}
}