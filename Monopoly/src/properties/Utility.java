package properties;

import positioning.Coordinate;
import game.Dice;
import game.Player;

public class Utility extends Property {

	private int cost;
	private Dice dice;
	private Utility neighbour;
	public Utility(String _name, int _cost, Dice _dice, Coordinate _coordinate) {
		super(_name,_cost,_coordinate);
		dice = _dice;
	}

	public String buyProperty(Player currentPlayer) {

		if(owner == null){
			return buy(currentPlayer);
		}
		else
			return "This property is already owned";
	}

	public void payRent(Player currentPlayer) {
		if (owner == null) return;	//dont pay if nobody owns the property
		if(owner == currentPlayer) return;//don't pay if you own the property
		if(owner.inJail())return;//dont pay if the owner is in jail
		if(isMortgaged)return;// dont pay if property is mortgaged
		
		//***Otherwise**
		
		int toPay = 0;
		if(neighbour.getOwner() ==owner){
			toPay = 4 * 2 * dice.lastDiceRoll();
		}
		else toPay = 4*dice.lastDiceRoll();
		currentPlayer.deduct(toPay);
		owner.add(toPay);
		System.out.println(currentPlayer.name()+" paid $"+toPay+" to "+ owner.name()+" in rent.");
		
	}

	public void setNeighbour(Utility _neighbour) {
		neighbour = _neighbour;
		
	}
	public String printOption(Player currentPlayer) {
		if(owner == null)
		return "Buy "+ name;
		else return null;
	}





}
