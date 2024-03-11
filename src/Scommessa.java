abstract public class Scommessa {
    private final String data;
    private final int puntata;
    private final double quota;
    private final boolean vittoria;
    private final String sport;
    private final Cliente cliente;

    public Scommessa(String data, int puntata, double quota, boolean vittoria, Cliente cliente, String sport) {
        this.data = data;
        this.puntata = puntata;
        this.quota = quota;
        this.vittoria = vittoria;
        this.cliente = cliente;
        this.sport = sport;
    }

    //GETTERS
    public String getData() {
        return data;
    }

    public int getPuntata() {
        return puntata;
    }

    public double getQuota() {
        return quota;
    }

    public boolean isVittoria() {
        return vittoria;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getSport() {
        return sport;
    }

    //toString
    @Override
    public String toString() {
        return "Scommessa{" +
                "data='" + data + '\'' +
                ", puntata=" + puntata +
                ", quota=" + quota +
                ", vittoria=" + vittoria +
                ", cliente=" + cliente +
                '}';
    }
}
