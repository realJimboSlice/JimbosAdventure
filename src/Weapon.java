public abstract class Weapon extends Item {
  private boolean equipable;
  private int damage;

  public Weapon(String ItemName, String itemDescription, int damage) {
    super(ItemName, itemDescription);
    this.equipable = equipable;
    this.damage = damage;
  }

  public boolean isEquipable() {
    return equipable;
  }

  public void setEquipable(boolean equipable) {
    this.equipable = equipable;
  }
  public int getDamage() {
    return damage;
  }

  public void setDamage(int damage) {
    this.damage = damage;
  }

}