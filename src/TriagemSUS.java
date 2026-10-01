public class TriagemSUS {
    private Paciente raizCadastro;
    private Paciente raizAgendaDia;

    public boolean cadastrarPaciente(long cpf, String nomeCompleto, String cartaoSus,
                                     TipoAtendimento tipoAtendimento) {
        Paciente novo = new Paciente(cpf, nomeCompleto, cartaoSus, tipoAtendimento);
        if (!inserirNoCadastro(novo)) {
            System.out.println("Alerta: CPF " + cpf + " ja cadastrado na UBS.");
            return false;
        }
        System.out.println("Paciente " + nomeCompleto + " cadastrado na UBS.");
        return true;
    }

    private boolean inserirNoCadastro(Paciente novo) {
        if (raizCadastro == null) {
            raizCadastro = novo;
            return true;
        }
        Paciente aux = raizCadastro;
        Paciente pai = null;
        while (aux != null) {
            pai = aux;
            if (novo.getCpf() == aux.getCpf()) return false;
            aux = novo.getCpf() < aux.getCpf() ? aux.getEsquerda() : aux.getDireita();
        }
        novo.setPai(pai);
        if (novo.getCpf() < pai.getCpf()) pai.setEsquerda(novo);
        else pai.setDireita(novo);
        return true;
    }

    // Busca iterativa da aula, com contagem de nos visitados.
    public Paciente buscarPaciente(long cpf) {
        Paciente encontrado = buscar(raizCadastro, cpf, true);
        if (encontrado == null) {
            System.out.println("Paciente nao cadastrado na triagem do dia.");
        } else {
            System.out.println("Ficha localizada: " + encontrado.getNomeCompleto()
                    + " | CPF: " + encontrado.getCpf()
                    + " | Cartao SUS: " + encontrado.getCartaoSus()
                    + " | Tipo: " + encontrado.getTipoAtendimento());
        }
        return encontrado;
    }

    private Paciente buscar(Paciente raiz, long cpf, boolean mostrarComparacoes) {
        Paciente aux = raiz;
        int comparacoes = 0;
        while (aux != null) {
            comparacoes++;
            if (cpf == aux.getCpf()) {
                if (mostrarComparacoes) System.out.println("Nos visitados: " + comparacoes);
                return aux;
            }
            aux = cpf < aux.getCpf() ? aux.getEsquerda() : aux.getDireita();
        }
        if (mostrarComparacoes) System.out.println("Nos visitados: " + comparacoes);
        return null;
    }

    public void removerPorCpf(long cpf) {
        if (buscar(raizCadastro, cpf, false) == null) {
            System.out.println("Nao existe paciente cadastrado com esse CPF.");
            return;
        }
        raizCadastro = removerPaciente(raizCadastro, cpf);
        if (raizCadastro != null) raizCadastro.setPai(null);
        System.out.println("Paciente removido do cadastro da UBS.");
    }

    // Remocao recursiva: folha, um filho ou dois filhos (usa o sucessor).
    public Paciente removerPaciente(Paciente raiz, long cpf) {
        if (raiz == null) return null;
        if (cpf < raiz.getCpf()) {
            Paciente filho = removerPaciente(raiz.getEsquerda(), cpf);
            raiz.setEsquerda(filho);
            if (filho != null) filho.setPai(raiz);
        } else if (cpf > raiz.getCpf()) {
            Paciente filho = removerPaciente(raiz.getDireita(), cpf);
            raiz.setDireita(filho);
            if (filho != null) filho.setPai(raiz);
        } else {
            if (raiz.getEsquerda() == null) {
                Paciente filho = raiz.getDireita();
                if (filho != null) filho.setPai(raiz.getPai());
                return filho;
            }
            if (raiz.getDireita() == null) {
                Paciente filho = raiz.getEsquerda();
                if (filho != null) filho.setPai(raiz.getPai());
                return filho;
            }
            Paciente sucessor = menorElemento(raiz.getDireita());
            copiarDados(sucessor, raiz);
            Paciente filho = removerPaciente(raiz.getDireita(), sucessor.getCpf());
            raiz.setDireita(filho);
            if (filho != null) filho.setPai(raiz);
        }
        return raiz;
    }

    private Paciente menorElemento(Paciente no) {
        while (no.getEsquerda() != null) no = no.getEsquerda();
        return no;
    }

    private void copiarDados(Paciente origem, Paciente destino) {
        destino.setCpf(origem.getCpf());
        destino.setNomeCompleto(origem.getNomeCompleto());
        destino.setCartaoSus(origem.getCartaoSus());
        destino.setTipoAtendimento(origem.getTipoAtendimento());
    }

    // So deve ser chamado apos o paciente ser localizado no cadastro da UBS.
    public boolean cadastrarAtendimentoDia(Paciente paciente) {
        Paciente novo = new Paciente(paciente.getCpf(), paciente.getNomeCompleto(),
                paciente.getCartaoSus(), paciente.getTipoAtendimento());
        if (raizAgendaDia == null) {
            raizAgendaDia = novo;
            System.out.println("Inserido na agenda do dia: " + novo.getNomeCompleto());
            return true;
        }
        Paciente aux = raizAgendaDia;
        Paciente pai = null;
        while (aux != null) {
            pai = aux;
            if (novo.getCpf() == aux.getCpf()) {
                System.out.println("Paciente ja esta na agenda do dia.");
                return false;
            }
            aux = novo.getCpf() < aux.getCpf() ? aux.getEsquerda() : aux.getDireita();
        }
        novo.setPai(pai);
        if (novo.getCpf() < pai.getCpf()) pai.setEsquerda(novo);
        else pai.setDireita(novo);
        System.out.println("Inserido na agenda do dia: " + novo.getNomeCompleto());
        return true;
    }

    public void cadastrarAtendimentoDia(long cpf) {
        Paciente paciente = buscarPaciente(cpf);
        if (paciente != null) cadastrarAtendimentoDia(paciente);
    }

    // Busca em profundidade pre-ordem: no, esquerda e direita.
    public void imprimirAtendimentosDia() {
        if (raizAgendaDia == null) {
            System.out.println("Nenhum atendimento cadastrado para o dia.");
            return;
        }
        System.out.println("\nAGENDA DE ATENDIMENTOS DO DIA (PRE-ORDEM)");
        imprimirAtendimentosDia(raizAgendaDia);
    }

    private void imprimirAtendimentosDia(Paciente no) {
        if (no == null) return;
        System.out.println("Nome: " + no.getNomeCompleto() + " | CPF: " + no.getCpf());
        imprimirAtendimentosDia(no.getEsquerda());
        imprimirAtendimentosDia(no.getDireita());
    }

    public void imprimirCadastroEmOrdem() {
        imprimirCadastroEmOrdem(raizCadastro);
    }

    private void imprimirCadastroEmOrdem(Paciente no) {
        if (no == null) return;
        imprimirCadastroEmOrdem(no.getEsquerda());
        System.out.println(no.getCpf() + " - " + no.getNomeCompleto());
        imprimirCadastroEmOrdem(no.getDireita());
    }
}
