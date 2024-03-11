public class Cliente {
    private String nome;
    private String codiceUnivoco;
    private double guadagno;

    public Cliente(String nome, String codiceUnivoco, double guadagno) {
        this.nome = nome;
        this.codiceUnivoco = codiceUnivoco;
        this.guadagno = guadagno;
    }

    public Cliente() {

    }

    public Cliente(String nome, String codiceUnivoco) {
        this.nome = nome;
        this.codiceUnivoco = codiceUnivoco;
        this.guadagno = 0;
    }

    //GETTERS
    public String getNome() {
        return nome;
    }

    public String getCodiceUnivoco() {
        return codiceUnivoco;
    }

    public double getGuadagno() {
        return guadagno;
    }

    //SETTERS
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCodiceUnivoco(String codiceUnivoco) {
        this.codiceUnivoco = codiceUnivoco;
    }

    public void setGuadagno(double guadagno) {
        this.guadagno = guadagno;
    }

    //toString
    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", codiceUnivoco='" + codiceUnivoco + '\'' +
                ", guadagno=" + guadagno +
                '}';
    }
}
