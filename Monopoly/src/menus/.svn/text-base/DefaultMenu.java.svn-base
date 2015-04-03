package menus;
import game.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import properties.Property;
import game.Board;
import game.Player;

public class DefaultMenu implements Menu{
	
	public enum Purpose {HOUSE,SELL,MORTGAGE,UNMORTGAGE}


	private Player player;
	private Board board;

	public DefaultMenu(Player _player, Board _board){
		player = _player;
		board = _board;
	}


	public String display() {
		// TODO Auto-generated method stub
		String display = "(P)Buy Property";
		if(player!= null){
			if(player.currentPosition() != null){
				if(player.currentPosition() instanceof Property){
				if(((Property) (player.currentPosition())).getOwner()== null){
				display = display + " $"+ ((Property) (player.currentPosition())).getCost();
				}
			}
			}
			}


		
		display = display +
				"\n\n(H)Buy house\n" +
				"\n(M)Mortgage a property\n" +
				"\n(U)Unmortgage a property\n" +
				"\n(S)Sell a property\n" +
				"\n[space]End Turn";
		
		return display;
	}


	@Override
	public void keyPressed(KeyEvent e) {
		char code = (char)e.getKeyCode();
		if(code == 'H'){
			PropertyMenu buyHouse = new PropertyMenu(player, board, Purpose.HOUSE);
			board.addMenu(buyHouse);
		}
		if(code == 'M'){
			PropertyMenu MortgageProperty = new PropertyMenu(player, board, Purpose.MORTGAGE);
			board.addMenu(MortgageProperty);
		}
		if(code == 'U'){
			PropertyMenu unmortgageProperty = new PropertyMenu(player, board, Purpose.UNMORTGAGE);
			board.addMenu(unmortgageProperty);
		}
		if(code == 'S'){
			PropertyMenu sellProperty = new PropertyMenu(player, board, Purpose.SELL);
			board.addMenu(sellProperty);
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
