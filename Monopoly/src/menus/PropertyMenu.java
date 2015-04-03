package menus;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

import menus.DefaultMenu.Purpose;

import properties.Property;
import properties.Street;

import game.Board;
import game.Player;

public class PropertyMenu implements Menu {
	private Player currentPlayer;
	private Board board;
	private Purpose purpose;
	private ArrayList<Integer> options = new ArrayList<Integer>();
	
	public PropertyMenu(Player _currentPlayer, Board _board, Purpose _purpose){
		currentPlayer = _currentPlayer;
		board = _board;
		purpose = _purpose;
		int i = 65;
		for(Property p: currentPlayer.ownedProperties){
			options.add(i++);
		}
	}

	
	public String display() {
		String display = "";
		if(currentPlayer!= null){
			display = "Which property?";
			int i = 65;
		for(Property p: currentPlayer.ownedProperties){
			display = display +"\n"+(char)(i++)+") "+ p.name();
		}
		}
		display = display +"\n[ESCAPE] return";
		return  display;
				
}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = (char)e.getKeyCode();
		if(options.contains(code)){
			Property selectedProperty = currentPlayer.ownedProperties.get(code-65);
			switch (purpose) {
			case SELL:
					PlayerMenu Sell = new PlayerMenu(currentPlayer, board, board.getPlayers(),Purpose.SELL);
					Sell.setToSell(currentPlayer.ownedProperties.get(code-65));
					board.addMenu(Sell);
				break;
			case UNMORTGAGE:
				board.setActionInfo(selectedProperty.unmortgage());
				break;
			case MORTGAGE:
				board.setActionInfo(selectedProperty.mortgage(currentPlayer));
				break;
			case HOUSE:
				if(selectedProperty instanceof Street){
				Street selectedStreet = (Street)selectedProperty;
				board.setActionInfo(selectedStreet.buyHouse(currentPlayer));
				}
				else board.setActionInfo("You cannot build here,\n this is not a street");
				break;
			default:
				break;
			}
		
		}
		
	}
	


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
