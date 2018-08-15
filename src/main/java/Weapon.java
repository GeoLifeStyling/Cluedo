
public class Weapon extends Card{
	private String name;
	
	public Weapon(String name) {
		this.name = name;
	}

	public String getName(){
		return this.name;
	}
	
	public String toString() {
		return ("Weapon: " + this.name);
	}
}
