import java.util.ArrayList;

/**
 * This class is the actual token on the board that requires interaction with the player tokens for
 * a feature in the game.
 */
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

    /**
     * Adds player to Room
     * @param player - Player - current player
     */
    public void addplayerToRoom(Player player){
        this.players.add(player);
    }
    /**
     * This would have been used if i managed to swap the weapons within rooms
     * @return
     */
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
