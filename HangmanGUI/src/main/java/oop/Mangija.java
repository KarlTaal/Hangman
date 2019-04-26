package oop;

public class Mangija implements Comparable<Mangija> {
    private String nimi;
    private int skoor;
    private int arvatudSonu;

    public Mangija(String nimi, int skoor, int arvatudSonu) {
        this.nimi = nimi;
        this.skoor = skoor;
        this.arvatudSonu = arvatudSonu;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
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

    public String getNimi() {
        return nimi;
    }

    public void setArvatudSonu(int arvatudSonu) {
        this.arvatudSonu += arvatudSonu;
    }

    //meetod, mis ütleb mille järgi sorteerida
    public int compareTo(Mangija võrreldav) {
        if (getSkoor() < võrreldav.getSkoor()) {
            return 1;
        }
        if (getSkoor() > võrreldav.getSkoor()) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return nimi + " skoor=" + skoor + " arvatud sõnu:" + arvatudSonu;
    }
}
