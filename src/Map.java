public class Map {
  private Room StartRoom;


  public Map() {
    this.StartRoom = null;
  }

  public void World() {
    // A lot of rooms incoming
    Room room1 = new Room("A dimly-lit room.", "It reeks of Mountain Dew and stale Doritos.");
    Room room2 = new Room("A balrog nest.", "'Nuff said.");
    Room room3 = new Room("Duff Beer Brewery", "Brewery where the finest Duff Beer flows like water.");
    Room room4 = new Room("Your local Genbrugs-shop", "A truly evil place where old witches will try to finagle you out of your money");
    Room room5 = new Room("Satan's Hell Hole", "Time for a boss fight, yo.");
    Room room6 = new Room("A Cloud.", "...What. How're you not falling down? You some kind of aerial Moses? ");
    Room room7 = new Room("A Night Club.", "Time to bust out a move or two. Oh shit, that guy just stepped on your shoes. Time for a brawl!");  //room 7
    Room room8 = new Room("Australia", "The most dangerous place on earth. Beware of bogans, koalas and kangaroos!");
    Room room9 = new Room("Malmø", "It's time to take back Skåne, bitches!");

    room1.setSouth(room4);
    room1.setEast(room2);

    room2.setEast(room3);
    room2.setWest(room1);

    room3.setSouth(room6);
    room3.setWest(room2);

    room4.setSouth(room7);
    room4.setNorth(room1);

    room5.setSouth(room8);

    room6.setSouth(room9);
    room6.setNorth(room3);

    room7.setEast(room8);
    room7.setNorth(room4);

    room8.setEast(room9);
    room8.setNorth(room5);
    room8.setWest(room7);

    room9.setWest(room8);
    room9.setNorth(room6);

    StartRoom = room1;

    // Weapons and items
    MeleeWeapon stick = new MeleeWeapon("stick", "It's a stick. What did you expect?", 7);
    RangedWeapon glock = new RangedWeapon("glock", "Turn it sideways for -500 accuracy and +500 street cred.", 10,20);
    MeleeWeapon whip = new MeleeWeapon("whip", "I'm feelin' like Indiana Jones!", 20);
    RangedWeapon shuriken = new RangedWeapon("shuriken", "Don't cut yourself!", 20, 40);

    // Food
    Food brownie = new Food("brownie", "One of those 'funny' brownies", Enums.EDIBLE, 420);
    Food gummybears = new Food("gummybears", "A staple since childhood", Enums.EDIBLE, 50);
    Food jawbreaker = new Food("jawbreaker", "Sounds like a weapon, but it's not. Sike!", Enums.EDIBLE, 100);

    // Enemies
    Enemy satan = new Enemy("satan", "The Prince of Darkness", 1000, new MeleeWeapon("hellflames", "They're pretty hot", 25));
    Enemy duffman = new Enemy("duffman", "He stands in the way of you and the beer", 70,new RangedWeapon("duffcans", "like gettin' hit with a rock", 10, 99));
    Enemy balrog = new Enemy("balrog", "A monster straight of the movies", 75, new MeleeWeapon("claws", "They're kinda sharp", 15));

    //putting items in the rooms
    room1.addItem(stick);
    room4.addItem(whip);
    room7.addItem(glock);
    room6.addItem(shuriken);

    //putting food in rooms
    room1.addItem(brownie);
    room2.addItem(gummybears);
    room3.addItem(jawbreaker);

    //putting enemy in a room
    room8.setEnemies(satan);
    room2.setEnemies(balrog);
    room3.setEnemies(duffman);
  }

  public Room getStartRoom() {
    return this.StartRoom;
  }

}