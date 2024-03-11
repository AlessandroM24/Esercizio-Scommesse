public class ScommessaNuoto extends Scommessa {
    private String stile;
    private int distanza;
    private String vincitore;

    public ScommessaNuoto(String data, int puntata, double quota, boolean vittoria, Cliente cliente, String sport, String stile, int distanza, String vincitore) {
        super(data, puntata, quota, vittoria, cliente, sport);
        this.stile = stile;
        this.distanza = distanza;
        this.vincitore = vincitore;
    }

    //GETTERS
    public String getStile() {
        return stile;
    }

    public int getDistanza() {
        return distanza;
    }

    public String getVincitore() {
        return vincitore;
    }

    //toString
    @Override
    public String toString() {
        return "ScommessaNuoto{" +
                "data='" + getData() + '\'' +
                ", puntata=" + getPuntata() +
                ", quota=" + getQuota() +
                ", vittoria=" + isVittoria() +
                ", cliente=" + getCliente() +
                ", sport=" + getSport() +
                ", stile='" + stile + '\'' +
                ", distanza=" + distanza +
                ", vincitore='" + vincitore + '\'' +
                '}';
    }
}
