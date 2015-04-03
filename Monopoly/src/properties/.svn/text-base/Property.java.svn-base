package properties;

import positioning.Coordinate;
import game.Player;

public abstract class Property implements Location {
	protected String name;
	protected Player owner;
	protected int cost;
	protected Coordinate coordinate;
	protected boolean isMortgaged;
	protected boolean cannotBeMortgaged;
	
	public Property(String _name, int _cost,Coordinate _coordinate) {
		name = _name;
		cost = _cost;
		coordinate = _coordinate;
	}
	public String name(){
		return name;
	}
	public String buy(Player p) {
		if (owner != null) return "This property is already owned \nby "+owner.name()+"!";
		if (p.totalMoney() >= cost && owner == null) {
			owner = p;
			p.addProperty(this);
			p.deduct(cost);
			return p.name()+" has bought "+this.name()+ "\n for "+this.getCost();
		}
		else{
			return "You cannot afford this!";
		}
	}
	public String mortgage(Player p) {
		if(cannotBeMortgaged){return"This property has been mortgaged\nbefore you cannot do so again.";}
		owner = p;
		p.add(cost/2);
		
		isMortgaged = true;
		cannotBeMortgaged = true;
		return owner.name()+" has now mortgaged "+name+".";
	}

	public String unmortgage() {
		if(!isMortgaged){return "This property is not mortgaged!";}
		 
		if(owner.totalMoney()>(cost/2)+(cost*0.10)){
		owner.deduct((int) ((cost/2)+(cost*0.10)));//Pay back the bank mortgage + 10%
		isMortgaged = false;
		return owner.name()+" has now unmortgaged "+name+".";
		}
		else return "You cannot afford to unmortgage at \nthis time";
	}
	public boolean isMortgaged(){
		return isMortgaged;
	}
	public String isOwnedBy() {
		if(owner == null) return" for sale!";
		else
		return " owned by "+owner.name();
	}
	public String toString(){
		 String returnString = name+":\n"+((owner==null)?"Buy for $"+cost:"Owned by "+owner.name()+((isMortgaged)?"\nMortgaged ":""));
		 return returnString;
		 
	}
	public void setOwner(Player p){
		owner = p;
		owner.addProperty(this);
	}
	public Player getOwner(){
		return owner;
	}

	public String autoAction(Player currentPlayer){
		return payRent(currentPlayer);
	}
	private String payRent(Player currentPlayer) {
		// TODO Auto-generated method stub
		return "";
	}
	public String propertyListing(Player currentPlayer) {
		return "Cannot build a house here";

	}
	public Coordinate getCoordinates(){
		return coordinate;
	}

	public int getCost() {
		return cost;
	}

}
