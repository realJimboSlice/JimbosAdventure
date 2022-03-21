package gameobjects;

public class Room extends ThingHolder implements java.io.Serializable{

    private int n, s, w, e, u, d;

    public Room(String aName, String aDescription, int aN, int aS, int aW, int aE, int aU, int aD,
            ThingList tl) {
        super(aName, aDescription, tl);
        this.n = aN;
        this.s = aS;
        this.w = aW;
        this.e = aE;
        this.u = aU;
        this.d = aD;
    }

    // --- accessor methods ---
    // n
    public int getN() {
        return n;
    }

    public void setN(int aN) {
        this.n = aN;
    }

    // s
    public int getS() {
        return s;
    }

    public void setS(int aS) {
        this.s = aS;
    }

    // e
    public int getE() {
        return e;
    }

    public void setE(int aE) {
        this.e = aE;
    }

    // w
    public int getW() {
        return w;
    }

    public void setW(int aW) {
        this.w = aW;
    }

    public int getU() {
        return u;
    }

    public void setU(int u) {
        this.u = u;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public String describe() {
        return String.format("%s. %s.",
                getName(), getDescription())
                + "\nThings here:\n" + getThings().describeThings();
    }
}
