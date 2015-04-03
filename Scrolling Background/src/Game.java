import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class Game {

	private GameThread gameThread;
	private GameCanvas gameCanvas;
	private Helicopter helicopter;
	private Graphics paintBrush;
	private Color backgroundColor;
	private GameThread gameTimer;
	private boolean drawingOn;
	private Background background;
	private int currentScore;
	private BufferedImage backgroundImage;
	private Graphics2D backgroundImageGffx;
	private static int highscore;
	
	public Game(GameCanvas canvas){
		this.gameCanvas = canvas;
		this.background = new Background();
		this.helicopter = new Helicopter();
		this.backgroundColor = Color.black;
		this.gameTimer = new GameThread(this);
		paintBrush = gameCanvas.getBrush();
		paintBrush.setColor(Color.lightGray);
		paintBrush.fillRect(0,0,800,800);
		gameTimer.start();
	}
	public void toggleDrawing(){
		drawingOn = !drawingOn;
	}
	public void stepRight() {
		helicopter.moveRight();
	}
	public void stepLeft() {
		helicopter.moveLeft();
	}
	public void stepDown() {
		helicopter.moveDown();
	}
	public void stepUp() {
		helicopter.moveUp();
	}
	public static void main(String[] args) {
		new GameFrame(new Game(new GameCanvas()));
	}
	public Component getCanvas() {
		return gameCanvas;
	}
	public void refresh() {
		backgroundImage = new BufferedImage(800,800,BufferedImage.TYPE_3BYTE_BGR);
		backgroundImageGffx = backgroundImage.createGraphics();
		currentScore++;
		if(currentScore> highscore){
			highscore = currentScore;
		}	
		if(background.isTouching(helicopter.getBoundingBox())){
			gameTimer.setNewGame();
		}
		if(!helicopter.step()){
		background.step(helicopter.getXSpeed());
		}
		else
			background.step(0);
		
		while(gameCanvas.repainting){
			try{Thread.sleep(10);}catch (Exception e) {}
		}
		gameCanvas.drawing = true;
		drawBackground(backgroundImageGffx);
		drawState(backgroundImageGffx);
		backgroundImageGffx.setColor(Color.blue);
		backgroundImageGffx.setFont(paintBrush.getFont().deriveFont(25f));
		backgroundImageGffx.drawString("CurrentScore: "+currentScore, 30, 30);
		backgroundImageGffx.drawString("HighScore: "+highscore, 30, 60);
		paintBrush.drawImage(backgroundImage,0,0,null);
		gameCanvas.drawing = false;
		gameCanvas.repaint();
	}
	private void drawState(Graphics backgroundImageGffx) {
		helicopter.checkBoundaries(gameCanvas.getWidth(), gameCanvas.getHeight());
		helicopter.draw(backgroundImageGffx);
	}
	private void drawBackground(Graphics backgroundImageGffx) {
		background.draw(backgroundImageGffx);
		}
	public void showEndGameMessage(int i) {
		paintBrush.setFont(paintBrush.getFont().deriveFont(25f));
		paintBrush.setColor(Color.YELLOW);
		paintBrush.fillRect(290, 290, 400, 220);
		paintBrush.setColor(Color.BLACK);
		paintBrush.fillRect(300, 300, 380, 200);
		paintBrush.setColor(Color.YELLOW);
		paintBrush.drawString("Game Over",330,330);
		paintBrush.drawString("Starting new game in:", 330, 380);
		paintBrush.drawString(""+i,420,440);
		gameCanvas.repaint();
	}
	public void reset() {
		helicopter = new Helicopter();
		background = new Background();
		currentScore = 0;
	}
		
	
}
