public class TriagemSUS {
    private Paciente raiz;

    public TriagemSUS() {
        this.raiz = null;
    }

    public TriagemSUS(Paciente raiz) {
        this.raiz = raiz;
    }

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

            if (novoNo.getCpf().equals(aux.getCpf())) {
                System.out.println(" Alerta: CPF " + novoNo.getCpf() + " já cadastrado!");
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
        System.out.println(" Nós visitados (comparações): " + comparacoes + "\n");
        return null;
    }

    public void exibirEmOrdem() {
        if (this.raiz == null) {
            System.out.println("Nenhum paciente cadastrado na árvore.");
            return;
        }
        exibirEmOrdemRecursivo(this.raiz);
    }

    private void exibirEmOrdemRecursivo(Paciente no) {
        if (no != null) {
            exibirEmOrdemRecursivo(no.getEsquerda());
            System.out.println("CPF: " + no.getCpf() + " | Nome: " + no.getNome() + " | Atendimento: " + no.getTipoAtendimento());
            exibirEmOrdemRecursivo(no.getDireita());
        }
    }

    public Paciente getRaiz() {
        return raiz;
    }


    public Paciente removerPaciente(Paciente raiz, Long cpf) {
        if (raiz == null) {
            return null;
        }

        if (cpf < raiz.getCpf()) {
            raiz.setEsquerda(removerPaciente(raiz.getEsquerda(), cpf));
        } else if (cpf > raiz.getCpf()) {
            raiz.setDireita(removerPaciente(raiz.getDireita(), cpf));
        } else {
            if (raiz.getEsquerda() == null && raiz.getDireita() == null) {
                return null;
            }
            else if (raiz.getEsquerda() == null) {
                return raiz.getDireita();
            }
            else if (raiz.getDireita() == null) {
                return raiz.getEsquerda();
            }
            else {
                Paciente sucessor = menorElemento(raiz.getDireita());

                raiz.setCpf(sucessor.getCpf());
                raiz.setNome(sucessor.getNome());
                raiz.setCartaoSus(sucessor.getCartaoSus());
                raiz.setTipoAtendimento(sucessor.getTipoAtendimento());

                raiz.setDireita(removerPaciente(raiz.getDireita(), sucessor.getCpf()));
            }
        }
        return raiz;
    }

    // Função auxiliar para encontrar o menor elemento (usada no Caso 4)
    private Paciente menorElemento(Paciente no) {
        Paciente atual = no;
        // O menor elemento em uma BST sempre fica o mais à esquerda possível
        while (atual.getEsquerda() != null) {
            atual = atual.getEsquerda();
        }
        return atual;
    }

    public Paciente cadastrarAtendimentoDia(Long cpf, String nome, String cartaoSus, TipoAtendimento tipoAtendimento) {

        Paciente novoNo = new Paciente(cpf, nome, cartaoSus, tipoAtendimento);

        // Se a árvore estiver vazia, o novo nó torna-se a raiz
        if (this.raiz == null) {
            this.raiz = novoNo;
            System.out.println("Paciente " + nome + " cadastrado como primeiro do dia.");
            return this.raiz;
        }

        Paciente aux = this.raiz;
        Paciente pai = null;

        // Procura a posição correta de inserção
        while (aux != null) {
            pai = aux;

            // Tratamento extra para evitar CPFs duplicados na fila do dia
            if (cpf.equals(aux.getCpf())) {
                System.out.println("Erro: O paciente com CPF " + cpf + " já está cadastrado hoje.");
                return this.raiz;
            }

            // Desce na árvore comparando os CPFs
            if (cpf < aux.getCpf()) {
                aux = aux.getEsquerda();
            } else {
                aux = aux.getDireita();
            }
        }

        // Conecta o novo nó ao nó pai na posição que ficou vazia (NULO)
        if (cpf < pai.getCpf()) {
            pai.setEsquerda(novoNo);
        } else {
            pai.setDireita(novoNo);
        }

        System.out.println("Paciente " + nome + " cadastrado na triagem do dia com sucesso!");
        return this.raiz;
    }


    public void exibirAtendimentosFila() {
        if (this.raiz == null) {
            System.out.println("Nenhum atendimento cadastrado para o dia.");
            return;
        }
        System.out.println("\n--- FILA DE ATENDIMENTO (Pré-Ordem) ---");
        exibirPreOrdemRecursivo(this.raiz);
        System.out.println("---------------------------------------");
    }

    private void exibirPreOrdemRecursivo(Paciente no) {
        if (no != null) {
            // PRÉ-ORDEM: 1º Imprime o valor (Nó)
            System.out.println("-> " + no.getNome() + " (CPF: " + no.getCpf() + ") - " + no.getTipoAtendimento());

            // 2º Percorre a subárvore à esquerda
            exibirPreOrdemRecursivo(no.getEsquerda());

            // 3º Percorre a subárvore à direita
            exibirPreOrdemRecursivo(no.getDireita());
        }
    }

    public void imprimir_atendimentos_dia(Paciente no) {
        if (no != null) {
            // 1. NÓ: Imprime o nome e o CPF do paciente atual
            System.out.println("Nome: " + no.getNome() + " | CPF: " + no.getCpf());

            // 2. ESQUERDA: Percorre recursivamente a subárvore esquerda
            imprimir_atendimentos_dia(no.getEsquerda());

            // 3. DIREITA: Percorre recursivamente a subárvore direita
            imprimir_atendimentos_dia(no.getDireita());
        }
    }

    public void removerPorCpf(Long cpf) {
        this.raiz = removerPaciente(this.raiz, cpf);
    }


}
