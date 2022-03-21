package gameobjects;

public class Thing implements java.io.Serializable{

    private String name;
    private String description;

    public Thing(String aName, String aDescription) {
        this.name = aName;
        this.description = aDescription;
    }

    public String getName() {
        return name;
    }

    public void setName(String aName) {
        this.name = aName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String aDescription) {
        this.description = aDescription;
    }
}
