import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TriagemSUS sistema = new TriagemSUS();
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n--- UBS: CADASTRO E AGENDA DO DIA ---");
            System.out.println("1 - Cadastrar paciente na UBS");
            System.out.println("2 - Remover paciente da UBS");
            System.out.println("3 - Chegada para atendimento (busca e inclui na agenda)");
            System.out.println("4 - Imprimir agenda do dia (pre-ordem)");
            System.out.println("5 - Executar roteiro exigido na atividade");
            System.out.println("0 - Sair");
            System.out.print("Opcao: ");
            opcao = Integer.parseInt(scanner.nextLine());
            switch (opcao) {
                case 1 -> cadastrar(scanner, sistema);
                case 2 -> {
                    System.out.print("CPF: ");
                    sistema.removerPorCpf(Long.parseLong(scanner.nextLine()));
                }
                case 3 -> {
                    System.out.print("CPF do paciente que chegou: ");
                    sistema.cadastrarAtendimentoDia(Long.parseLong(scanner.nextLine()));
                }
                case 4 -> sistema.imprimirAtendimentosDia();
                case 5 -> executarRoteiro(sistema);
                case 0 -> System.out.println("Sistema encerrado.");
                default -> System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);
        scanner.close();
    }

    private static void cadastrar(Scanner scanner, TriagemSUS sistema) {
        System.out.print("CPF: ");
        long cpf = Long.parseLong(scanner.nextLine());
        System.out.print("Nome completo: ");
        String nome = scanner.nextLine();
        System.out.print("Cartao SUS: ");
        String cartaoSus = scanner.nextLine();
        sistema.cadastrarPaciente(cpf, nome, cartaoSus, TipoAtendimento.TRIAGEM);
    }

    private static void executarRoteiro(TriagemSUS sistema) {
        System.out.println("\n1. CARGA INICIAL: seis pacientes da UBS");
        sistema.cadastrarPaciente(600L, "Sthefanny Lara", "SUS001", TipoAtendimento.CONSULTA_AGENDADA);
        sistema.cadastrarPaciente(300L, "Bruno Costa", "SUS002", TipoAtendimento.TRIAGEM);
        sistema.cadastrarPaciente(800L, "Carla Souza", "SUS003", TipoAtendimento.VACINACAO);
        sistema.cadastrarPaciente(200L, "Adriana Alves", "SUS004", TipoAtendimento.TRIAGEM);
        sistema.cadastrarPaciente(500L, "Cicero Moura", "SUS005", TipoAtendimento.CONSULTA_AGENDADA);
        sistema.cadastrarPaciente(700L, "Saymon Rocha", "SUS006", TipoAtendimento.VACINACAO);

        System.out.println("\n2. REMOCAO: Bruno Costa mudou de bairro");
        sistema.removerPorCpf(300L);

        System.out.println("\n3. CHEGADA DE CINCO PACIENTES");
        sistema.cadastrarAtendimentoDia(600L);
        sistema.cadastrarAtendimentoDia(800L);
        sistema.cadastrarAtendimentoDia(200L);
        sistema.cadastrarAtendimentoDia(500L);
        sistema.cadastrarAtendimentoDia(700L);

        System.out.println("\n4. IMPRESSAO DA AGENDA");
        sistema.imprimirAtendimentosDia();
    }
}
