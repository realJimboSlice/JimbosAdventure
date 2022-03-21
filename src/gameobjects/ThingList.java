package gameobjects;

import java.util.ArrayList;

public class ThingList extends ArrayList<Thing> implements java.io.Serializable{

    public String describeThings() {
        String s = "";
        if (this.size() == 0) {
            s = "nothing.\n";
        } else {
            for (Thing t : this) {
                s = s + t.getName() + ": " + t.getDescription() + "\n";
            }
        }
        return s;
    }

    public Thing thisOb(String aName) {
        Thing aThing = null;
        String thingName = "";
        String aNameLowCase = aName.trim().toLowerCase();
        for (Thing t : this) {
            thingName = t.getName().trim().toLowerCase();
            if (thingName.equals(aNameLowCase)) {
                aThing = t;
            }
        }
        return aThing;
    }
}
