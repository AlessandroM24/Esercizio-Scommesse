public class ScommessaAtletica extends Scommessa {
    private String specialita;
    private String vincitore;

    public ScommessaAtletica(String data, int puntata, double quota, boolean vittoria, Cliente cliente, String sport, String specialita, String vincitore) {
        super(data, puntata, quota, vittoria, cliente, sport);
        this.specialita = specialita;
        this.vincitore = vincitore;
    }

    //GETTERS
    public String getSpecialita() {
        return specialita;
    }

    public String getVincitore() {
        return vincitore;
    }

    //toString
    @Override
    public String toString() {
        return "ScommessaAtletica{" +
                "data='" + getData() + '\'' +
                ", puntata=" + getPuntata() +
                ", quota=" + getQuota() +
                ", vittoria=" + isVittoria() +
                ", cliente=" + getCliente() +
                ", sport=" + getSport() +
                ", specialita='" + specialita + '\'' +
                ", vincitore='" + vincitore + '\'' +
                '}';
    }
}
