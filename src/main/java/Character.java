
public class Character extends Card{
	private String name;
	public Character(String s) {
		this.name = s;
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return ("Character:" + this.name);
	}
}
