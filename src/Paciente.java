public class Paciente {
    private long cpf;
    private String nomeCompleto;
    private String cartaoSus;
    private TipoAtendimento tipoAtendimento;

    private Paciente pai;
    private Paciente esquerda;
    private Paciente direita;

    public Paciente(long cpf, String nomeCompleto, String cartaoSus,
                    TipoAtendimento tipoAtendimento) {
        this.cpf = cpf;
        this.nomeCompleto = nomeCompleto;
        this.cartaoSus = cartaoSus;
        this.tipoAtendimento = tipoAtendimento;
    }

    public long getCpf() { return cpf; }
    public String getNomeCompleto() { return nomeCompleto; }
    public String getCartaoSus() { return cartaoSus; }
    public TipoAtendimento getTipoAtendimento() { return tipoAtendimento; }
    public Paciente getPai() { return pai; }
    public Paciente getEsquerda() { return esquerda; }
    public Paciente getDireita() { return direita; }

    public void setCpf(long cpf) { this.cpf = cpf; }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }
    public void setCartaoSus(String cartaoSus) { this.cartaoSus = cartaoSus; }
    public void setTipoAtendimento(TipoAtendimento tipoAtendimento) {
        this.tipoAtendimento = tipoAtendimento;
    }
    public void setPai(Paciente pai) { this.pai = pai; }
    public void setEsquerda(Paciente esquerda) { this.esquerda = esquerda; }
    public void setDireita(Paciente direita) { this.direita = direita; }
}
