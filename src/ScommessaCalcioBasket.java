public class ScommessaCalcioBasket extends Scommessa {
    private String squadra1;
    private String squadra2;
    private String risultato;

    public ScommessaCalcioBasket(String data, int puntata, double quota, boolean vittoria, Cliente cliente, String sport, String squadra1, String squadra2, String risultato) {
        super(data, puntata, quota, vittoria, cliente, sport);
        this.squadra1 = squadra1;
        this.squadra2 = squadra2;
        this.risultato = risultato;
    }

    //GETTERS
    public String getSquadra1() {
        return squadra1;
    }

    public String getSquadra2() {
        return squadra2;
    }

    public String getRisultato() {
        return risultato;
    }


    //toString
    @Override
    public String toString() {
        return "ScommessaCalcioBasket{" +
                "data='" + getData() + '\'' +
                ", puntata=" + getPuntata() +
                ", quota=" + getQuota() +
                ", vittoria=" + isVittoria() +
                ", cliente=" + getCliente() +
                ", sport=" + getSport() +
                ", squadra1='" + squadra1 + '\'' +
                ", squadra2='" + squadra2 + '\'' +
                ", risultato='" + risultato + '\'' +
                '}';
    }
}
