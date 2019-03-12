public class Mangija {
    final private String nimi;
    private int skoor;
    private int arvatudSonu;

    public Mangija(String nimi, int skoor, int arvatudSonu) {
        this.nimi = nimi;
        this.skoor = skoor;
        this.arvatudSonu = arvatudSonu;
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
                '}';
    }
}
