public class Mangija {
    final private String nimi;
    private int skoor;
    private int arvatudSonu;
    private int vigu;

    public Mangija(String nimi, int skoor, int arvatudSonu, int vigu) {
        this.nimi = nimi;
        this.skoor = skoor;
        this.arvatudSonu = arvatudSonu;
        this.vigu = vigu;
    }

    public void setSkoor(int skoor) {
        this.skoor += skoor;
    }

    public void setArvatudSonu(int arvatudSonu) {
        this.arvatudSonu += arvatudSonu;
    }

    @Override
    public String toString() {
        return "Mangija{" +
                "nimi='" + nimi + '\'' +
                ", skoor=" + skoor +
                ", arvatudSonu=" + arvatudSonu +
                ", vigu=" + vigu +
                '}';
    }
}
