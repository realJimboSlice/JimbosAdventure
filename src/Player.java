import java.util.ArrayList;
import java.util.Scanner;

public class Player {
  private Room currentRoom;
  private int playerHealth;
  private Weapon currentWeapon;
  private ArrayList<Item> inventory;

  public Player(Room currentRoom) {
    this.currentRoom = currentRoom;
    this.playerHealth = 420;
    this.currentWeapon = null;
    inventory = new ArrayList<>();
  }

  public void takeItem(String itemName) {
    Item item = currentRoom.itemRemove(itemName);
    inventory.add(item);

  }

  public void dropItem(String itemName) {
    Item foundItem = searchInventory(itemName);
    if (foundItem != null) {
      inventory.remove(foundItem);
      currentRoom.getRoomInventory().add(foundItem);
    }
  }

  public Item searchInventory(String itemName) {
    for (int i = 0; i < inventory.size(); i++) {
      Item temp = inventory.get(i);
      if (temp.getItemName().equals(itemName)) {
        inventory.remove(temp);
        return temp;
      }
    }
    return null;
  }

  public void myInventory() {
    System.out.println(this.inventory);
  }

  public void eatItem(String itemName) {
    Item itemEat = searchInventory(itemName);
    if (itemEat != null) {
      if (itemEat instanceof Food food) {
        inventory.remove(food);
        setPlayerHealth(food.getFoodHealth());
        isItConsumable(food);
      }
    }
  }

  public Enums playerEats(String itemName) {
    Item itemConsume = searchInventory(itemName);
    if (itemConsume != null) {
      if (itemConsume instanceof Food food) {
        inventory.remove(food);
        return food.getConsumable();
      }
    }
    return Enums.INVALID;
  }

  public void isItConsumable(Food food) {
    if (food.getConsumable().equals(Enums.EDIBLE)) {
      System.out.println("Hp is now: " + getPlayerHealth());
      healthStatus();
    } else if (food.getConsumable().equals(Enums.INEDIBLE)) {
      System.out.println("Inedible.");
    } else if (food.getConsumable().equals(Enums.INVALID)) {
      System.out.println("No food left.");
    }
  }

  public void health() {
    healthStatus();
  }

  public void healthStatus() {
    if (getPlayerHealth() > 0) {
      System.out.println("Current health: " + getPlayerHealth());
    } else if (getPlayerHealth() == 0) {
      System.out.println("Current health: " + getPlayerHealth());
      System.out.println("You're dead, homie...");
      System.exit(1);
    }
  }

  public void equip(String itemName) {
    Item itemWeapon = searchInventory(itemName);
    if (itemWeapon != null) {
      if (itemWeapon instanceof Weapon weapon) {
        inventory.remove(weapon);
        this.currentWeapon = weapon;
        System.out.println(weapon + " has been equipped");
      } else {
        System.out.println("No weapons equipped");
      }
    }
  }

  public void unequip(String itemName) {
    Item itemWeapon = searchInventory(itemName);
    if (currentWeapon != null) {
      if (itemWeapon instanceof Weapon weapon) {
        weapon = currentWeapon;
        inventory.add(weapon);
        this.currentWeapon = null;
        System.out.println(weapon + " has been unequipped");
      } else {
        System.out.println("No weapons to unequip");
      }
    }
  }


  public void attack(String enemyName) {
    Enemy enemyHere = currentRoom.searchForEnemy(enemyName);
    Scanner input = new Scanner(System.in);
    if (enemyHere != null) {
      if (currentWeapon != null) {
        if (currentWeapon instanceof RangedWeapon) {
          ((RangedWeapon) currentWeapon).shoot();
          enemyHere.setEnemyHealth(currentWeapon.getDamage());
          takeDamage(enemyHere);
          System.out.println(playerHealth);
        } else if (enemyHere.getEnemyHealth() > 0) {
        }


        if (enemyHere.getEnemyHealth() <= 0) {
          System.out.println("You killed your enemy. Not bad, kid.");
          currentRoom.removeEnemy(enemyHere);
          currentRoom.addItem(enemyHere.getEnemyDamage());
        }
        System.out.println("Enemy hp: " + enemyHere.getEnemyHealth());

      } else {
        System.out.println("You're unarmed. Equip a weapon, stupid.");
      }
    } else {
      System.out.println("All the monsters are dead, bro. You're the last one standing... Nice!");
    }
  }

  public void goNorth() {
    Room room = currentRoom.getNorth();
    goToRoom(room);
  }

  public void goSouth() {
    Room room = currentRoom.getSouth();
    goToRoom(room);
  }

  public void goEast() {
    Room room = currentRoom.getEast();
    goToRoom(room);
  }

  public void goWest() {
    Room room = currentRoom.getWest();
    goToRoom(room);
  }

  public void goToRoom(Room room) {
    if (room == null) {
      System.out.println("No passage through here.");
    } else {
      currentRoom.setCurrentRoom(true);
      currentRoom = room;

      if (!currentRoom.getCurrentRoom()) {
        Look();
      } else {
        Look();

      }
    }
  }

  public void Look() {
    System.out.println(getCurrentRoom() + "\n" + "Items currently here: " + getCurrentRoom().getRoomInventory());
    System.out.println("Enemies currently here: " + getCurrentRoom().getRoomEnemy());
  }

  //Getters and setters for currentRoom
  public void setCurrentRoom(Room currentRoom) {
    this.currentRoom = currentRoom;
  }

  public Room getCurrentRoom() {
    return currentRoom;
  }

  public int getPlayerHealth() {
    return playerHealth;
  }

  public void setPlayerHealth(int healthChange) {
    this.playerHealth += healthChange;
  }

  public void takeDamage(Enemy enemy) {
    this.playerHealth -= enemy.getEnemyDamage().getDamage();
  }
}



