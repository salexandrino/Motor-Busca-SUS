public class TriagemSUS {
    private Paciente raiz;

    // Construtor sem argumentos (inicia a árvore vazia)
    public TriagemSUS() {
        this.raiz = null;
    }

    // Construtor opcional com raiz inicial
    public TriagemSUS(Paciente raiz) {
        this.raiz = raiz;
    }

    // 1. Cadastrar Paciente (Método Iterativo conforme o pseudocódigo)
    public Paciente cadastrarPaciente(Paciente novoNo) {
        if (this.raiz == null) {
            this.raiz = novoNo;
            System.out.println(" Paciente " + novoNo.getNome() + " cadastrado como raiz.");
            return this.raiz;
        }

        Paciente aux = this.raiz;
        Paciente pai = null;

        while (aux != null) {
            pai = aux;

            // Tratamento de CPF duplicado
            if (novoNo.getCpf().equals(aux.getCpf())) {
                System.out.println("⚠️ Alerta: CPF " + novoNo.getCpf() + " já cadastrado!");
                return this.raiz;
            }

            if (novoNo.getCpf() < aux.getCpf()) {
                aux = aux.getEsquerda();
            } else {
                aux = aux.getDireita();
            }
        }

        // Conecta o novo nó ao nó pai
        if (novoNo.getCpf() < pai.getCpf()) {
            pai.setEsquerda(novoNo);
        } else {
            pai.setDireita(novoNo);
        }

        System.out.println("Paciente " + novoNo.getNome() + " cadastrado com sucesso.");
        return this.raiz;
    }

    // 2. Buscar Paciente por CPF (Com contador de comparações exigido)
    public Paciente buscarPaciente(Long cpfBuscado) {
        Paciente aux = this.raiz;
        int comparacoes = 0;

        while (aux != null) {
            comparacoes++;

            if (cpfBuscado.equals(aux.getCpf())) {
                System.out.println("\n========================================");
                System.out.println(" FICHA DO PACIENTE LOCALIZADA");
                System.out.println("========================================");
                System.out.println("Nome: " + aux.getNome());
                System.out.println("CPF: " + aux.getCpf());
                System.out.println("Cartão SUS: " + aux.getCartaoSus());
                System.out.println("Tipo de Atendimento: " + aux.getTipoAtendimento());
                System.out.println(" Nós visitados (comparações): " + comparacoes);
                System.out.println("========================================\n");
                return aux;
            }

            if (cpfBuscado < aux.getCpf()) {
                aux = aux.getEsquerda();
            } else {
                aux = aux.getDireita();
            }
        }

        System.out.println("\n Paciente com CPF " + cpfBuscado + " não cadastrado na triagem do dia.");
        System.out.println("🔍 Nós visitados (comparações): " + comparacoes + "\n");
        return null;
    }

    // Getter da raiz caso precise testar externamente
    public Paciente getRaiz() {
        return raiz;
    }
}