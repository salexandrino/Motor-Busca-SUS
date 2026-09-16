public class TriagemSUS
    private Paciente raiz;

    public TriagemSUS(Paciente raiz) {
        this.raiz = raiz;
    }

    public Paciente cadastrarPaciente(Paciente novoNo){
        if (this.raiz ==null){
            this.raiz = novoNo;
            System.out.println("Paciente " + novoNo.getNome() + "cadastrado como raiz");
            return this.raiz
        }

        Paciente aux = raiz;
        Paciente pai = null;

        while (aux != null){
            pai = aux;
            if (novoNo.getCpf().equals(aux.getCpf())){
                System.out.println("Cpf " + novoNo.getCpf() + "ja cadastrado!");
                return this.raiz;
            }

            if (novoNo.getCpf() < aux.getCpf()) {
                aux = aux.getEsquerda();
            } else {
                aux = aux.getDireita();
            }
        }

        if (novoNo.getCpf() < pai.getCpf()) {
            pai.setEsquerda(novoNo);
        } else {
            pai.setDireita(novoNo);
        }
        System.out.println("Paciente " + novoNo.getNome() + "cadastrado" );
        return this.raiz;

    }

    public Paciente buscarPaciente(Paciente raiz, Paciente Pacbuscado){
        if (raiz == null || raiz.getCpf == Pacbuscado.getCpf()){
            return raiz;
        }
        if (Pacbuscado < raiz.getCpf()) {
            return (buscarPaciente(raiz.esquerda, Pacbuscado));
        } else {
            return buscarPaciente(raiz.direita, Pacbuscado)
        }
}
