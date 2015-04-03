package menus;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import menus.DefaultMenu.Purpose;

import properties.Property;
import properties.Street;

import game.Board;
import game.Player;

public class PlayerMenu<Purpose> implements Menu {
	private Player currentPlayer;
	private Board board;
	private ArrayList<Player> players;
	private Purpose purpose;
	private Property toSell = null;
	private ArrayList<Integer> options = new ArrayList<Integer>();

	public PlayerMenu(Player _currentPlayer, Board _board, ArrayList<Player>_players, Purpose _purpose){
		currentPlayer = _currentPlayer;
		board = _board;
		players = (ArrayList<Player>) _players.clone();
		purpose = _purpose;
		int i = 65;
		players.remove(currentPlayer);
		for(Player p: players){
			options.add(i++);
		}
	}

	public String display() {
		String display = "";
		if(currentPlayer!= null){
			display = "Which player would you like to sell to?";
			int i = 65;
		for(Player p: players){
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
			Player toSellTo = players.get(code-65);
			int amount = 0;
			while(amount == 0 || amount> toSellTo.totalMoney()){
			try{
			amount = Integer.parseInt(JOptionPane.showInputDialog(null, "How much for?", "1-"+toSellTo.totalMoney(), JOptionPane.DEFAULT_OPTION));  
			}catch (Exception exc) {
				// TODO: handle exception
			}
			}
			String[] options = {"Yes","No"};
			int choice = JOptionPane.showOptionDialog(null, "Do you agree?", toSellTo.name(), JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options);
			if(choice == 1) return;
			else board.setActionInfo("Sale successfull!");
			toSell.setOwner(toSellTo);
			toSellTo.deduct(amount);
			currentPlayer.add(amount);
			currentPlayer.removeProperty(toSell);
			board.menuReturn();
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

	public void setToSell(Property property) {
		toSell = property;
		
	}

	}

