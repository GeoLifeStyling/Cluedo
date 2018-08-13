
public class Room extends Card{
	private String name; 
	
	public Room(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return ("Room: " + this.name + "\n");
	}
}