import java.util.Scanner;

public class Game {

  private boolean running = true;
  private Text text;
  private Player player;

  public void mainFrame() {
    Map map = new Map();
    map.World();

    text = new Text();

    player = new Player(map.getPlayerStartRoom());
  }

  public void startGame() {
    text.introduction();

    running = true;


    Scanner input = new Scanner(System.in);
    while (running) {
      System.out.print("Enter command\n> ");
      String command = input.nextLine();
      String noun = "";
      if (command.startsWith("take") || command.startsWith("drop") || command.startsWith("eat") ||
          command.startsWith("equip") || command.startsWith("unequip") || command.startsWith("attack")) {
        int firstSpace = command.indexOf(" ");
        noun = command.substring(firstSpace + 1);
        command = command.substring(0, firstSpace);
      }
      switch (command) {
        case "n" -> player.goNorth();
        case "s" -> player.goSouth();
        case "e" -> player.goEast();
        case "w" -> player.goWest();
        case "help" -> Help();
        case "look" -> player.Look();
        case "i", "inv" -> player.myInventory();
        case "take" -> player.takeItem(noun);
        case "drop" -> player.dropItem(noun);
        case "eat" -> player.eatItem(noun);
        case "health" -> player.health();
        case "equip" -> player.equip(noun);
        case "unequip" -> player.unequip(noun);
        case "attack" -> player.attack(noun);
        case "q" -> Quit();

      }
    }
  }

  public void Help() {
    String help = """
        For directions, enter: [n, s, e, w].
        For the other shit, enter: "q" (to quit), "i" (for inventory),
        "take (to take)", "drop" (to drop a deuce), "eat" (to eat it),
        "health" (to check up on your health),
        "equip", "unequip", and most importantly "attack"
        """;
    System.out.println(help);

  }

  public void Quit() {
    System.out.println("Quitters never win, winners never quit");
    System.exit(0);
  }
}
