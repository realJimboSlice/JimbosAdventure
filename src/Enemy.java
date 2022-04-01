import java.util.ArrayList;

public class Enemy {
  private String enemyName;
  private String enemyDescription;
  private int enemyHealth;
  private Weapon enemyDamage;
  private ArrayList<Weapon> enemyInventory;

  public Enemy(String enemyName, String enemyDescription, int enemyHealth, Weapon enemyDamage) {
    this.enemyName = enemyName;
    this.enemyDescription = enemyDescription;
    this.enemyHealth = enemyHealth;
    this.enemyDamage = enemyDamage;
    enemyInventory = new ArrayList<>();

  }

  public String getEnemyName() {
    return enemyName;
  }

  public void setEnemyName(String enemyName) {
    this.enemyName = enemyName;
  }

  public int getEnemyHealth() {
    return enemyHealth;
  }

  public void setEnemyHealth(int enemyHealthChange) {
    this.enemyHealth -= enemyHealthChange;

  }

  public String getEnemyDescription() {
    return enemyDescription;
  }

  public void setEnemyDescription(String enemyDescription) {
    this.enemyDescription = enemyDescription;
  }

  public Weapon getEnemyDamage() {
    return enemyDamage;
  }

  public void setEnemyDamage(Weapon enemyDamage) {
    this.enemyDamage = enemyDamage;
  }

  public String toString() {
    return getEnemyName();
  }


}