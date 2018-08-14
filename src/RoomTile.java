import java.util.ArrayList;

public class RoomTile {

    private String token;
    private String name;
    private ArrayList<Player> players = new ArrayList<>();
    private Card weapon;

    public RoomTile(String name, String token){
        this.name = name;
        this.token = token;
    }

    public void addWeapon(Card weapon){
        this.weapon = weapon;
    }

    public Card getWeapon(){
        return this.weapon;
    }

    public ArrayList<Player> getPlayersInRoom(){
        return this.players;
    }

    public String getToken(){
        return this.token;
    }

    public String toString(){
        return "Room: " + this.name;
    }


}
