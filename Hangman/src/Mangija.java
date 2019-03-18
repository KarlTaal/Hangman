public class Mangija {
    final private String nimi;
    private int skoor;
    private int arvatudSonu;
    private int viga;

    public Mangija(String nimi, int skoor, int arvatudSonu, int viga) {
        this.nimi = nimi;
        this.skoor = skoor;
        this.arvatudSonu = arvatudSonu;
        this.viga = viga;
    }

    public int getViga() {
        return viga;
    }

    public void setViga(int viga) {
        this.viga = viga;
    }

    public int getSkoor() {
        return skoor;
    }

    public void setSkoor(int skoor) {
        this.skoor = skoor;
    }

    public int getArvatudSonu() {
        return arvatudSonu;
    }

    public void setArvatudSonu(int arvatudSonu) {
        this.arvatudSonu += arvatudSonu;
    }

    public String dongerRating(int viga){
        if (viga == 1) {
            return ("༼つ ◕_◕ ༽つ  viga: 1");
        } else if (viga == 2) {
            return ("(งಠل͜ಠ)ง  viga: 2");
        } else if (viga == 3) {
            return ("໒( • ͜ʖ • )७  viga: 3");
        } else if (viga == 4) {
            return ("( * ಥ ⌂ ಥ * )  viga: 4");
        } else if (viga == 5) {
            return ("( ✖ _ ✖ ) viga: 5 (oh man sorry u dead)");
        }
        return null;
    }

    @Override
    public String toString() {
        return "Mangija{" +
                "nimi='" + nimi + '\'' +
                ", skoor=" + skoor +
                ", arvatudSonu=" + arvatudSonu +
                '}';
    }
}
