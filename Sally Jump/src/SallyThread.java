
public class SallyThread extends Thread{
	
	private SallyCanvas c;
	private SallyJump j;
	private Sally mainChar;
	private boolean moveBackground;
	public SallyThread(SallyCanvas c, SallyJump sallyJump){
		this.c = c;
		this.j = sallyJump;
		this.mainChar = j.getChar();
	}
	@Override
	public void run() {
	while(1==1){
			try {
				sleep(30);
			} catch (Exception e) {}
			c.repaint();
			if(mainChar.getY()<SallyCanvas.Height/3){
				moveBackground = true;
			}else{
				moveBackground = false;
			}
			mainChar.step(moveBackground);
			
		}
	}

}
