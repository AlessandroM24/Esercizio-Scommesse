public class ScommessaCiclismo extends Scommessa {
    private String ciclista1;
    private String ciclista2;
    private String ciclista3;

    public ScommessaCiclismo(String data, int puntata, double quota, boolean vittoria, Cliente cliente, String sport, String ciclista1, String ciclista2, String ciclista3) {
        super(data, puntata, quota, vittoria, cliente, sport);
        this.ciclista1 = ciclista1;
        this.ciclista2 = ciclista2;
        this.ciclista3 = ciclista3;
    }

    //GETTERS
    public String getCiclista1() {
        return ciclista1;
    }

    public String getCiclista2() {
        return ciclista2;
    }

    public String getCiclista3() {
        return ciclista3;
    }

    //toString
    @Override
    public String toString() {
        return "ScommessaCiclismo{" +
                "data='" + getData() + '\'' +
                ", puntata=" + getPuntata() +
                ", quota=" + getQuota() +
                ", vittoria=" + isVittoria() +
                ", cliente=" + getCliente() +
                ", sport=" + getSport() +
                ", ciclista1='" + ciclista1 + '\'' +
                ", ciclista2='" + ciclista2 + '\'' +
                ", ciclista3='" + ciclista3 + '\'' +
                '}';
    }
}
