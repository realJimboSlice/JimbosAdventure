public class Item {
  public String itemName;
  public String itemDescription;
  public boolean itemTrue;

  public Item(String itemName, String itemDescription) {
    this.itemName = itemName;
    this.itemDescription = itemDescription;
    this.itemTrue = false;
  }

  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    itemName = itemName;
  }

  public String getItemDescription() {
    return itemDescription;
  }

  public void setItemDescription(String itemDescription) {
    this.itemDescription = itemDescription;
  }

  public boolean isItemTrue() {
    return itemTrue;
  }

  public void setItemTrue(boolean itemTrue) {
    this.itemTrue = itemTrue;
  }

  public String toString() {
    return itemName;
  }

}