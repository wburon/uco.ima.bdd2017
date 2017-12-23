package model;

public enum Name {
	
	OUI,
	NON,
	PEU_IMPORTE;
	
	public boolean value(){
		switch(this){
		case OUI :
			return true;
		case NON :
			return false;
		}
		return false;
	}
	
	public static Name getName(String n){
		switch(n){
		case "OUI":
			return Name.OUI;
		case "NON":
			return Name.NON;
		default:
			return Name.PEU_IMPORTE;
		}
	}
	

}
