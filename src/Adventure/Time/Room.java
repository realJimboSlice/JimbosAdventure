package Adventure.Time;

public class Room {

  // The privates

  private String name;
  private String description;

  private Room north;
  private Room south;
  private Room east;
  private Room west;

  public Room(String name, String description) {

    this.name = name;
    this.description = description;
  }

  public String descriptionName() {
    return name;
  }

  public String descriptionTwo() {
    return description;
  }

  // Get'n and set'n

  public Room getNorth() {
    return north;
  }

  public void setNorth(Room north) {
    this.north = north;
  }

  public Room getEast() {
    return east;
  }

  public void setEast(Room east) {
    this.east = east;
  }

  public Room getWest() {
    return west;
  }

  public void setWest(Room west) {
    this.west = west;
  }

  public Room getSouth() {
    return south;
  }

  public void setSouth(Room south) {
    this.south = south;
  }
}


    /*

    Room 1 () - north = null, east = room 2, west = null, south = room 4

    Room 2 () - north = null, east = room 3, west = room 1, south = null

    Room 3 () - north = null, east = null, west = room 2, south = room 6

    Room 4 () - north = room 1, east = null, west = null, south = room 7

    Room 5 () - north = null, east = null, west = null, south = room 8

    Room 6 () - north = room 3, east = null, west = null, south = room 9

    Room 7 () - north = room 4, east = room 8, west = null, south = null

    Room 8 () - north = room 9, east = room , west = null, south = null

    Room 9 () - north = room 6, east = null, west = room 8, south = null

    */