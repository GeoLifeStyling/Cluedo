import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameTest{

    @Test //char cards
    void createCharCards() {
        ArrayList<Card> charCards = new ArrayList<Card>();
        Card missScar = new Character("Miss Scarlet");
        Card profPlum = new Character("Professor Plum");
        Card mrsPea = new Character("Mrs. Peacock");
        Card mrsWhi = new Character("Mrs. White");
        Card colMus = new Character("Colonel Mustard");
        Card mrGreen = new Character("Mr. Green");
        charCards.add(missScar);
        charCards.add(profPlum);
        charCards.add(mrsPea);
        charCards.add(mrsWhi);
        charCards.add(colMus);
        charCards.add(mrGreen);

        assertEquals(charCards.get(0), missScar);
        assertEquals(charCards.get(1), profPlum);
        assertEquals(charCards.get(2), mrsPea);
        assertEquals(charCards.get(3), mrsWhi);
        assertEquals(charCards.get(4), colMus);
        assertEquals(charCards.get(5), mrGreen);
    }

    @Test //room cards
    void createRoomCards() {
        ArrayList<Card> roomCards = new ArrayList<Card>();
        Card kit = new Room("Kitchen");
        Card bal = new Room("Ball Room");
        Card con = new Room("Conservatory");
        Card bil = new Room("Billiard Room");
        Card lib = new Room("Library");
        Card stu = new Room("Study");
        Card hal = new Room("Hall");
        Card lou = new Room("Lounge");
        Card din = new Room("Dining Room");
        roomCards.add(kit);
        roomCards.add(bal);
        roomCards.add(con);
        roomCards.add(bil);
        roomCards.add(lib);
        roomCards.add(stu);
        roomCards.add(hal);
        roomCards.add(lou);
        roomCards.add(din);

        assertEquals(roomCards.get(0), kit);
        assertEquals(roomCards.get(1), bal);
        assertEquals(roomCards.get(2), con);
        assertEquals(roomCards.get(3), bil);
        assertEquals(roomCards.get(4), lib);
        assertEquals(roomCards.get(5), stu);
        assertEquals(roomCards.get(6), hal);
        assertEquals(roomCards.get(7), lou);
        assertEquals(roomCards.get(8), din);
    }
    @Test //weapon cards
    void createWeaponCards() {
        ArrayList<Card> weapCards = new ArrayList<Card>();
        Card candle = new Weapon("Candle Stick");
        Card dagger = new Weapon("Dagger");
        Card leadPipe = new Weapon("Lead Pipe");
        Card revolver = new Weapon("Revolver");
        Card rope = new Weapon("Rope");
        Card spanner = new Weapon("Spanner");
        weapCards.add(candle);
        weapCards.add(dagger);
        weapCards.add(leadPipe);
        weapCards.add(revolver);
        weapCards.add(rope);
        weapCards.add(spanner);

        assertEquals(weapCards.get(0), candle);
        assertEquals(weapCards.get(1), dagger);
        assertEquals(weapCards.get(2), leadPipe);
        assertEquals(weapCards.get(3), revolver);
        assertEquals(weapCards.get(4), rope);
        assertEquals(weapCards.get(5), spanner);
    }

    @Test // create players
    void createPlayers(){
        ArrayList<Player> players = new ArrayList<>();
        Position p1pos = new Position(25,8);
        Position p2pos = new Position(20,24);
        Position p3pos = new Position(7,24);
        Position p4pos = new Position(1,10);
        Position p5pos = new Position(18,1);
        Position p6pos = new Position(1,15);

        Player p1 = new Player("Miss Scarlet", "1", p1pos);
        Player p2 = new Player("Professor Plum", "2", p2pos);
        Player p3 = new Player("Mrs. Peacock", "3", p3pos);
        Player p4 = new Player("Mrs. White", "4", p4pos);
        Player p5 = new Player("Colonel Mustard", "5", p5pos);
        Player p6 = new Player("Mr. Green", "6", p6pos);

        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
        players.add(p5);
        players.add(p6);

        assertEquals(players.get(0), p1);
        assertEquals(players.get(1), p2);
        assertEquals(players.get(2), p3);
        assertEquals(players.get(3), p4);
        assertEquals(players.get(4), p5);
        assertEquals(players.get(5), p6);

    }

    @Test // Test added Positions
    void addPlayerPositions(){
        ArrayList<Player> players = new ArrayList<>();
        Position p1pos = new Position(25,8);
        Position p2pos = new Position(20,24);
        Position p3pos = new Position(7,24);
        Position p4pos = new Position(1,10);
        Position p5pos = new Position(18,1);
        Position p6pos = new Position(1,15);

        Player p1 = new Player("Miss Scarlet", "1", p1pos);
        Player p2 = new Player("Professor Plum", "2", p2pos);
        Player p3 = new Player("Mrs. Peacock", "3", p3pos);
        Player p4 = new Player("Mrs. White", "4", p4pos);
        Player p5 = new Player("Colonel Mustard", "5", p5pos);
        Player p6 = new Player("Mr. Green", "6", p6pos);

        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
        players.add(p5);
        players.add(p6);

        assertEquals(players.get(0).getXPosition(), p1pos.x);
        assertEquals(players.get(0).getYPosition(), p1pos.y);
        assertEquals(players.get(1).getXPosition(), p2pos.x);
        assertEquals(players.get(1).getYPosition(), p2pos.y);
        assertEquals(players.get(2).getXPosition(), p3pos.x);
        assertEquals(players.get(2).getYPosition(), p3pos.y);
        assertEquals(players.get(3).getXPosition(), p4pos.x);
        assertEquals(players.get(3).getYPosition(), p4pos.y);
        assertEquals(players.get(4).getXPosition(), p5pos.x);
        assertEquals(players.get(4).getYPosition(), p5pos.y);
        assertEquals(players.get(5).getXPosition(), p6pos.x);
        assertEquals(players.get(5).getYPosition(), p6pos.y);
    }

    @Test // move player new positon
    void movePlayerNewPostionCheck(){
        Position p1pos = new Position(25,8);
        Player p1 = new Player("Miss Scarlet", "1", p1pos);
        Position p = new Position(1,0);

        p1.newPosition(p.x,p.y);
        assertEquals(p1.getXPosition(), 26);
        assertEquals(p1.getYPosition(), 8);
    }

    @Test // player old position correct
    void movePlayerOldPosCheck(){
        Position p1pos = new Position(25,8);
        Player p1 = new Player("Miss Scarlet", "1", p1pos);
        Position p = new Position(1,0);

        p1.newPosition(p.x,p.y);
        assertEquals(p1.getOldXPosition(), 25);
        assertEquals(p1.getOldYPosition(), 8);
    }


    @Test // test string return
    void getCharCardName(){
        Card cha = new Character("Miss Scarlet");
            assertEquals(cha.toString(), "Character:Miss Scarlet");
    }

    @Test // test string return
    void getRoomCardName(){
        Card room = new Room("Kitchen");
        assertEquals(room.toString(), "Room: Kitchen");
    }

    @Test // test string return
    void getWeapCardName(){
        Card weap = new Weapon("Pipe");
        assertEquals(weap.toString(), "Weapon: Pipe");
    }

    @Test // getBoard Letter
    void getBoardLetter(){
        String[][] stdBoard = new String[1][1];
        stdBoard[0][0] = "A";
        assertEquals(stdBoard[0][0], "A");
    }

    @Test // translates characters correctly
    void TranslateTile(){
        Board b = new Board();
        String s = "K";
        assertEquals(b.translateTile(s), " ");
    }

    @Test// RoomTile
    void addWeaponToRoom(){
        ArrayList<RoomTile> rooms = new ArrayList<>();
        Card weap = new Weapon("Pipe");
        RoomTile kitchen = new RoomTile("Kitchen", "K");
        rooms.add(kitchen);
        rooms.get(0).addWeapon(weap);
        assertEquals(rooms.get(0).getWeapon(), weap);
    }

    @Test //
    void getTokenFromRoom(){
        ArrayList<RoomTile> rooms = new ArrayList<>();
        RoomTile kitchen = new RoomTile("Kitchen", "K");
        rooms.add(kitchen);
        assertEquals(rooms.get(0).getToken(), "K");
    }

    @Test
    void getPlayersInRoom(){
        ArrayList<Player> players = new ArrayList<>();
        RoomTile kitchen = new RoomTile("Kitchen", "K");
        Position p1pos = new Position(1,15);
        Player p1 = new Player("Miss Scarlet", "1", p1pos);
        players.add(p1);
        kitchen.addplayerToRoom(p1);
        assertEquals(kitchen.getPlayersInRoom(), players);
    }


    @Test
    void addWeaponsToRooms() {
            Card weap = new Weapon("Pipe");
            RoomTile kitchen = new RoomTile("Kitchen", "K");
            kitchen.addWeapon(weap);
            assertEquals(kitchen.getWeapon(), weap);
    }

    @Test
    void createRoomTiles() {
        ArrayList<RoomTile> rooms = new ArrayList<>();
        RoomTile kitchen = new RoomTile("Kitchen", "K");
        rooms.add(kitchen);
        assertEquals(rooms.get(0), kitchen);
    }

    @Test
    void getMoves() {
        Position p1pos = new Position(25, 8);
        Player p1 = new Player("Miss Scarlet", "1", p1pos);
        p1.addMoves(2);
        assertEquals(p1.getMoves(), 2);
    }

    @Test
    void addMoves() {
        Position p1pos = new Position(25, 8);
        Player p1 = new Player("Miss Scarlet", "1", p1pos);
        p1.addMoves(2);
        assertEquals(p1.getMoves(), 2);
    }

    @Test
    void moveshaveBeenSubtracted() {
        Position p1pos = new Position(25, 8);
        Player p1 = new Player("Miss Scarlet", "1", p1pos);
        p1.addMoves(2);
        p1.moved();
        assertEquals(p1.getMoves(), 1);
    }


}