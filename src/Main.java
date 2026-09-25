import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TriagemSUS sistema = new TriagemSUS();
        int opcao = 0;

        // Pré-cadastrando alguns pacientes usando a nova função de atendimento do dia
        sistema.cadastrarAtendimentoDia(55555555500L, "Sthefanny Lara", "898000111222333", TipoAtendimento.CONSULTA_AGENDADA);
        sistema.cadastrarAtendimentoDia(22222222200L, "Adriana Carvalho", "898000444555666", TipoAtendimento.TRIAGEM);
        sistema.cadastrarAtendimentoDia(88888888800L, "Saymon Ryan", "898000777888999", TipoAtendimento.VACINACAO);

        System.out.println("\n=================================================");
        System.out.println("🏥 SISTEMA DE TRIAGEM E PRONTUÁRIO ELETRÔNICO - SUS");
        System.out.println("=================================================");

        while (opcao != 6) {
            System.out.println("\n--- MENU DE ATENDIMENTO ---");
            System.out.println("1. Cadastrar Atendimento do Dia");
            System.out.println("2. Buscar Paciente (Recepção)");
            System.out.println("3. Remover Paciente (Ex: Mudou de bairro)");
            System.out.println("4. Imprimir Fila do Dia (Pré-Ordem)");
            System.out.println("5. Listar Pacientes em Ordem de CPF (Em-Ordem)");
            System.out.println("6. Sair do Sistema");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        System.out.println("\n--- CADASTRO DE ATENDIMENTO DO DIA ---");
                        System.out.print("Digite o CPF (apenas números): ");
                        Long cpf = Long.parseLong(scanner.nextLine());

                        System.out.print("Digite o Nome Completo: ");
                        String nome = scanner.nextLine();

                        System.out.print("Digite o Número do Cartão SUS: ");
                        String cartaoSus = scanner.nextLine();

                        System.out.println("Selecione o Tipo de Atendimento:");
                        System.out.println(" 1 - Triagem");
                        System.out.println(" 2 - Vacinação");
                        System.out.println(" 3 - Consulta Agendada");
                        System.out.print("Opção: ");
                        int tipoOpcao = Integer.parseInt(scanner.nextLine());

                        TipoAtendimento tipo;
                        switch (tipoOpcao) {
                            case 1 -> tipo = TipoAtendimento.TRIAGEM;
                            case 2 -> tipo = TipoAtendimento.VACINACAO;
                            case 3 -> tipo = TipoAtendimento.CONSULTA_AGENDADA;
                            default -> {
                                System.out.println("Opção inválida. Definido como TRIAGEM padrão.");
                                tipo = TipoAtendimento.TRIAGEM;
                            }
                        }

                        // Usando a nova função iterativa
                        sistema.cadastrarAtendimentoDia(cpf, nome, cartaoSus, tipo);
                        break;

                    case 2:
                        System.out.println("\n--- BUSCA DE PACIENTE ---");
                        System.out.print("Digite o CPF do paciente para buscar: ");
                        Long cpfBusca = Long.parseLong(scanner.nextLine());
                        sistema.buscarPaciente(cpfBusca);
                        break;

                    case 3:
                        System.out.println("\n--- REMOÇÃO DE PACIENTE ---");
                        System.out.print("Digite o CPF do paciente a ser removido: ");
                        Long cpfRemover = Long.parseLong(scanner.nextLine());
                        // Chama o método "casca" que discutimos na resposta de remoção
                        sistema.removerPorCpf(cpfRemover);
                        System.out.println("Operação de remoção finalizada.");
                        break;

                    case 4:
                        System.out.println("\n--- FILA DE ATENDIMENTOS (PRÉ-ORDEM) ---");
                        if (sistema.getRaiz() == null) {
                            System.out.println("Nenhum paciente cadastrado hoje.");
                        } else {
                            // Chama a função de impressão pré-ordem passando a raiz
                            sistema.imprimir_atendimentos_dia(sistema.getRaiz());
                        }
                        break;

                    case 5:
                        System.out.println("\n--- LISTAGEM DE PACIENTES (ORDEM CRESCENTE POR CPF) ---");
                        sistema.exibirEmOrdem();
                        break;

                    case 6:
                        System.out.println("\nEncerrou o sistema de triagem do SUS. Bom trabalho!");
                        break;

                    default:
                        System.out.println("\nOpção inválida! Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nErro: Entrada inválida. Digite apenas números para CPF e escolhas de menu.");
            }
        }

        scanner.close();
    }
}