import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;


public class Game {
	public static boolean lose;
	private PlayerBlock playerBlock;
	private ArrayList<Block> blocks = new ArrayList<Block>();
	private int difficulty = 2000;
	private int r = (int)(Math.random()*255);
	private int g = (int)(Math.random()*255);
	private int b = (int)(Math.random()*255);
	private GameCanvas canvas;
	private GameThread gameThread;
	private Graphics brush;
	private int bMult = 1;
	private int gMult = 1;
	private int rMult = 1;
	private GameFrame frame;
	private MouseController mouseController;
	private int timer;
	private int currentScore;
	private static int  highscore;
	
	public Game(){
		this.blocks  = generateBlocks(difficulty);
		this.playerBlock = new PlayerBlock(50,50);
		this.canvas = new GameCanvas(this);
		this.brush = canvas.getBrush();
		this.frame = new GameFrame(this,canvas,50,50);
		this.mouseController = new MouseController(playerBlock);
		this.canvas.addMouseMotionListener(mouseController);
		this.canvas.addMouseListener(mouseController);
		this.gameThread = new GameThread(this);
		this.gameThread.start();
	}
	private ArrayList<Block> generateBlocks(int difficulty) {
		ArrayList<Block> blocks = new ArrayList<Block>();
		for(int i = 0; i<difficulty; i++){
			blocks.add(new Block(30,30));
		}
		return blocks;
	}
	public static void main(String[] args) {
		new Game();
	}
	public void tick() {
		if(false){
			
			if(timer == 0){
				timer = 5;
			}
			drawLoseMessage(timer--);
			if(timer == 0){
				currentScore = 0;
				lose = false;
				blocks = generateBlocks(difficulty);
				r = (int)(Math.random()*255);
				g = (int)(Math.random()*255);
				b = (int)(Math.random()*255);
			}
			try{
			Thread.sleep(300);
			}catch(Exception e){System.err.println(e);}
			return;
		}
		currentScore++;
		if(currentScore> highscore){
			highscore = currentScore;
		}	
		for(Block b: blocks){
			b.step();
		}
		cycleColors();
		brush.setColor(new Color(r,g,b));
		brush.fillRect(0, 0, canvas.width, canvas.height);
		Rectangle boundingBox = playerBlock.getBoundingBox();
		for(Block b: blocks){
			b.draw(brush);
			if(b.isTouching(boundingBox)){
				lose = true;
			}
		}
		drawScore(brush);
		canvas.repaint();
	}
	private void drawScore(Graphics backgroundImageGffx) {
		backgroundImageGffx.setColor(Color.blue);
		backgroundImageGffx.setFont(backgroundImageGffx.getFont().deriveFont(25f));
		backgroundImageGffx.drawString("CurrentScore: "+currentScore, 30, 30);
		backgroundImageGffx.drawString("HighScore: "+highscore, 30, 60);
	}
	private void drawLoseMessage(int i) {
		brush.setFont(brush.getFont().deriveFont(25f));
		brush.setColor(Color.YELLOW);
		brush.fillRect(190, 190, 400, 220);
		brush.setColor(Color.BLACK);
		brush.fillRect(200, 200, 380, 200);
		brush.setColor(Color.YELLOW);
		brush.drawString("Game Over",230,230);
		brush.drawString("Starting new game in:", 230, 280);
		brush.drawString(""+i,320,340);
		canvas.repaint();
	}
	private void cycleColors() {
		if(r == 255 | r == 0) rMult *= -1;
		if(g == 255 | g == 0) gMult *= -1;
		if(b == 255 | b == 0) bMult *= -1;
		r += rMult;
		g += gMult;
		b += bMult;
	}
}
