package goEuro;

public class Airport {

	int _id;
	String name;
	String type;
	double latitude;
	double longitude;
	
	
	public String toString(){
		return _id+";"+name+";"+type+";"+latitude+";"+longitude;
	}
}
