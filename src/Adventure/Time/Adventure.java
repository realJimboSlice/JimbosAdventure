package Adventure.Time;

import java.util.Scanner;

public class Adventure {

  //Scanner
  Scanner sc = new Scanner(System.in);

  // The privates
  private Room currentRoom;
  private Room requestedRoom;

  private boolean isThisGameOn = true;

  public void everyRoom() {
    Room room1 = new Room("Room 1", "This is Room 1");
    Room room2 = new Room("Room 2", "This is Room 2");
    Room room3 = new Room("Room 3", "This is Room 3");
    Room room4 = new Room("Room 4", "This is Room 4");
    Room room5 = new Room("Room 5", "This is Room 5");
    Room room6 = new Room("Room 6", "This is Room 6");
    Room room7 = new Room("Room 7", "This is Room 7");
    Room room8 = new Room("Room 8", "This is Room 8");
    Room room9 = new Room("Room 9", "This is Room 9");

    //All the room directions
    room1.setEast(room2);
    room1.setSouth(room4);
    room2.setWest(room1);
    room2.setEast(room3);
    room3.setWest(room2);
    room3.setSouth(room6);
    room4.setNorth(room1);
    room4.setSouth(room7);
    room5.setSouth(room8);
    room6.setNorth(room3);
    room6.setSouth(room9);
    room7.setNorth(room4);
    room7.setEast(room8);
    room8.setWest(room7);
    room8.setNorth(room5);
    room8.setEast(room9);
    room9.setWest(room8);
    room9.setNorth(room6);
    currentRoom = room1;
  }

  public void goSouth() {
    if (currentRoom.getSouth() != null) {
      currentRoom = currentRoom.getSouth();
      System.out.println("You're in:");
      System.out.println(currentRoom.nameDescription());
    } else {
      System.out.println("It's a wall");
    }
  }

  public void goNorth() {
    if (currentRoom.getNorth() != null) {
      currentRoom = currentRoom.getNorth();
      System.out.println("You're in:");
      System.out.println(currentRoom.nameDescription());
    } else {
      System.out.println("It's a wall");
    }
  }

  public void goEast() {
    requestedRoom = currentRoom.getEast();
    if (requestedRoom != null) {
      currentRoom = requestedRoom;
      System.out.println("You're in:");
      System.out.println(currentRoom.nameDescription());
    } else {
      System.out.println("It's a wall");
    }
  }

  public void goWest() {
    if (currentRoom.getWest() != null) {
      currentRoom = currentRoom.getWest();
      System.out.println("You're in:");
      System.out.println(currentRoom.nameDescription());
    } else {
      System.out.println("It's a wall");
    }
  }

  public void look() {
    System.out.println(currentRoom.descriptionOfDescription());
  }

  public void exitGame() {
    isThisGameOn = false;
    System.out.println("Game over");
  }

  public void mainMenu() {
    everyRoom();


    while (isThisGameOn) {
      char input = sc.next().charAt(0);
      System.out.println();
      if (input == 'n') {
        System.out.println("Going north");
        goNorth();

      } else if (input == 's') {
        System.out.println("Going south");
        goSouth();

      } else if (input == 'e') {
        System.out.println("Going east");
        goEast();

      } else if (input == 'w') {
        System.out.println("Going west");
        goWest();

      } else if (input == 'l') {
        System.out.println("Look");
        look();

      } else if (input == 'e') {
        System.out.println("exit confirmed");
        exitGame();
      }
    }

  }

  public static void main(String[] args) {
    Adventure thing = new Adventure();
    thing.mainMenu();
  }
}
