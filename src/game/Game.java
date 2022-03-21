package game;

import gameobjects.*;
import globals.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Game implements java.io.Serializable {

    private ArrayList<Room> map;
    private Actor player;

    List<String> commands = new ArrayList<>(Arrays.asList(
            "take", "drop", "look", "l", "i", "inventory",
            "n", "s", "w", "e", "u", "d",
            "h", "help"));
    List<String> objects = new ArrayList<>(Arrays.asList("stick"));

    public Game() {
        this.map = new ArrayList<Room>();

        ThingList RoomOneList = new ThingList();
        RoomOneList.add(new Treasure("stick", "it's an ordinary stick, yo", 1));

        ThingList playerlist = new ThingList();
        // Add Rooms to the map
        // Room (The format is: name, description, direction (N,S,E,W,U,D), item-list)
        map.add(new Room("Troll Room", "A dank room that smells of troll",
            Direction.NOEXIT, 2, Direction.NOEXIT, 1, Direction.NOEXIT, Direction.NOEXIT, RoomOneList));

        // create player and place in Room 0
        player = new Actor("player", "a loveable game-player", playerlist, map.get(0));
    }

    // access methods
    ArrayList getMap() {
        return map;
    }

    void setMap(ArrayList aMap) {
        map = aMap;
    }

    // player
    public Actor getPlayer() {
        return player;
    }

    public void setPlayer(Actor aPlayer) {
        player = aPlayer;
    }

    // take and drop
    private void transferOb(Thing t, ThingList fromlist, ThingList tolist) {
        fromlist.remove(t);
        tolist.add(t);
    }

    public String takeOb(String obname) {
        String retStr = "";
        Thing t = player.getRoom().getThings().thisOb(obname);
        if (obname.equals("")) {
            obname = "nameless object"; // if no object specified
        }
        if (t == null) {
            retStr = "There is no " + obname + " here!";
        } else {
            transferOb(t, player.getRoom().getThings(), player.getThings());
            retStr = obname + " taken!";
        }
        return retStr;
    }

    public String dropOb(String obname) {
        String retStr = "";
        Thing t = player.getThings().thisOb(obname);
        if (obname.equals("")) {
            retStr = "You'll have to tell me which object you want to drop, man!"; // if no object specified
        } else if (t == null) {
            retStr = "You ain't got one of those";
        } else {
            transferOb(t, player.getThings(), player.getRoom().getThings());
            retStr = obname + " dropped!";
        }
        return retStr;
    }

    // move Player to a Room
    void moveActorTo(Actor p, Room aRoom) {
        p.setRoom(aRoom);
    }

    // move an Actor in direction 'dir'
    int moveTo(Actor anActor, Direction dir) {
        Room r = anActor.getRoom();
        int exit = switch (dir) {
            case NORTH -> r.getN();
            case SOUTH -> r.getS();
            case EAST -> r.getE();
            case WEST -> r.getW();
            case UP -> r.getU();
            case DOWN -> r.getD();
            default -> Direction.NOEXIT; // noexit - stay in same room
        };

        if (exit != Direction.NOEXIT) {
            moveActorTo(anActor, map.get(exit));
        }
        return exit;
    }

    public int movePlayerTo(Direction dir) {
        // return: Constant representing the room number moved to
        // or NOEXIT (see moveTo())
        //        
        return moveTo(player, dir);
    }

    private void goN() {
        showRoomDescription(movePlayerTo(Direction.NORTH));
    }

    private void goS() {
        showRoomDescription(movePlayerTo(Direction.SOUTH));
    }

    private void goE() {
        showRoomDescription(movePlayerTo(Direction.EAST));
    }

    private void goW() {
        showRoomDescription(movePlayerTo(Direction.WEST));
    }

    private void goU() {
        showRoomDescription(movePlayerTo(Direction.UP));
    }

    private void goD() {
        showRoomDescription(movePlayerTo(Direction.DOWN));
    }



    private void look() {
        showStr("You are in the " + getPlayer().getRoom().describe());
    }

    private void showStr(String s) {
        System.out.println(s);
    }

    private void showRoomDescription(int roomNumber) {
        // if roomNumber = NOEXIT, display a special message, otherwise
        // display text (e.g. name and description of room)        
        String s;
        if (roomNumber == Direction.NOEXIT) {
            s = "No Exit!";
        } else {
            Room r = getPlayer().getRoom();
            s = "You are in the " + r.describe();
        }
        showStr(s);
    }

    private void showInventory() {
        showStr("You have " + getPlayer().getThings());
    }
   
    public String processVerb(List<String> wordlist) {
        String verb;
        String msg = "";
        verb = wordlist.get(0);
        if (!commands.contains(verb)) {
            msg = verb + " is not a known verb! ";
        } else {
            switch (verb) {
                case "n" -> goN();
                case "s" -> goS();
                case "w" -> goW();
                case "e" -> goE();
                case "u" -> goU();
                case "d" -> goD();
                case "l", "look" -> look();
                case "inventory", "i" -> showInventory();
                case "help", "h" -> help();
                default -> msg = verb + " (not yet implemented)";
            }
        }
        return msg;
    }

    public String processVerbNoun(List<String> wordlist) {
        String verb;
        String noun;
        String msg = "";
        boolean error = false;
        verb = wordlist.get(0);
        noun = wordlist.get(1);
        if (!commands.contains(verb)) {
            msg = verb + " is not a known verb! ";
            error = true;
        }
        if (!objects.contains(noun)) {
            msg += (noun + " is not a known noun!");
            error = true;
        }
        if (!error) {
            switch (verb) {
                case "take":
                    msg = takeOb(noun);
                    break;
                case "drop":
                    msg = dropOb(noun);
                    break;
                default:
                    msg += " (not yet implemented)";
                    break;
            }
        }
        return msg;
    }

    public String parseCommand(List<String> wordlist) {
        String msg;
        if (wordlist.size() == 1) {
            msg = processVerb(wordlist);
        } else if (wordlist.size() == 2) {
            msg = processVerbNoun(wordlist);
        } else {
            msg = "Only 2 word commands allowed!";
        }
        return msg;
    }

    public List<String> wordList(String input) {
        String delims = " \t,.:;?!\"'";
        List<String> strlist = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(input, delims);
        String t;

        while (tokenizer.hasMoreTokens()) {
            t = tokenizer.nextToken();
            strlist.add(t);
        }
        return strlist;
    }

    public void showIntro() {
        String s;
        s = """
            You have fallen down a rabbit hole and arrived in
            an underground cavern that smells strongly of troll.
            Where do you want to go? [Enter n, s, w, e, u, d]
            Need help? [Enter help]
            (or enter q to quit)""";
        showStr(s);
        showRoomDescription(0);
    }

    public void help() {
        String h;
        h = """
                Directions: n, s, e, w, u, d
                Actions: l (or look) - to look
                         i (or inv) - to view inventory
                         take (item) - to take item
                         drop (item) - to drop item
            """;
        showStr(h);
    }

    public String runCommand(String inputstr) {
        List<String> wordlist;
        String s = "ok";
        String lowstr = inputstr.trim().toLowerCase();
        if (!lowstr.equals("q")) {
            if (lowstr.equals("")) {
                s = "You must enter a command";
            } else {
                wordlist = wordList(lowstr);
                s = parseCommand(wordlist);
            }
        }
        return s;
    }

}
