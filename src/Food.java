public class Food extends Item {
  private int foodHealth;
  private Enums consumable;

  public Food(String ItemName, String ItemLongName, Enums edible, int health) {
    super(ItemName, ItemLongName);
    this.foodHealth = health;
    this.consumable = edible;
  }

  public int getFoodHealth() {
    return foodHealth;
  }

  public void setFoodHealth(int health) {
    this.foodHealth = foodHealth;
  }

  public Enums getConsumable() {
    return this.consumable;
  }

  public void setConsumable(Enums consumable) {
    this.consumable = consumable;
  }

}