public class Paciente {
    private Long cpf;
    private String nome;
    private String cartaoSus;
    private TipoAtendimento tipoAtendimento;

    private Paciente esquerda;
    private Paciente direita;

    public Paciente(Long cpf, String nome, String cartaoSus, TipoAtendimento tipoAtendimento) {
        this.cpf = cpf;
        this.nome = nome;
        this.cartaoSus = cartaoSus;
        this.tipoAtendimento = tipoAtendimento;
        this.esquerda = esquerda;
        this.direita = direita;
    }

    public Long getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getCartaoSus() {
        return cartaoSus;
    }

    public TipoAtendimento getTipoAtendimento() {
        return tipoAtendimento;
    }

    public Paciente getEsquerda() {
        return esquerda;
    }

    public Paciente getDireita() {
        return direita;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCartaoSus(String cartaoSus) {
        this.cartaoSus = cartaoSus;
    }

    public void setTipoAtendimento(TipoAtendimento tipoAtendimento) {
        this.tipoAtendimento = tipoAtendimento;
    }

    public void setEsquerda(Paciente esquerda) {
        this.esquerda = esquerda;
    }

    public void setDireita(Paciente direita) {
        this.direita = direita;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "cpf=" + cpf +
                ", nome='" + nome + '\'' +
                ", cartaoSus='" + cartaoSus + '\'' +
                ", tipoAtendimento=" + tipoAtendimento +
                ", esquerda=" + esquerda +
                ", direita=" + direita +
                '}';
    }
}
