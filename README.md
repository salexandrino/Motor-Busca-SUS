# 🏥 Sistema de Triagem e Prontuário Eletrônico - SUS

Um sistema interativo desenvolvido em Java que simula a recepção e triagem de pacientes em uma unidade do SUS. O projeto utiliza a estrutura de dados **Árvore Binária de Busca (BST - Binary Search Tree)** para otimizar o cadastro, a organização e a busca rápida de pacientes utilizando o CPF como chave principal.

---

## 🎥 Vídeo de Apresentação (2 minutos)

Assista à demonstração do funcionamento do sistema, explicação do código e execução na prática:

▶️ **[Clique aqui para assistir à apresentação](https://drive.google.com/file/d/13hmes2vjwFLR33j7E-nQ5KE95CP1yHjP/view?usp=drive_link)**

---

## 🚀 Funcionalidades

- **Cadastro de Paciente:** Insere novos pacientes na Árvore Binária de Busca ordenando pelo CPF.
- **Busca Rápida por CPF:** Localiza registros e exibe o número total de comparações (nós visitados) para encontrar o paciente.
- **Listagem Em-Ordem:** Percorre a árvore (*In-Order Traversal*) e exibe todos os pacientes cadastrados em ordem crescente de CPF.
- **Tratamento de Exceções:** Evita CPFs duplicados e trata entradas inválidas no menu.

---

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java (JDK 17+)
- **Estrutura de Dados:** Árvore Binária de Busca (BST)
- **Paradigma:** Programação Orientada a Objetos (POO)

---

## 📂 Estrutura das Classes

- `Main.java`: Interface via terminal (CLI) e controle do fluxo do menu.
- `TriagemSUS.java`: Implementação dos algoritmos da Árvore Binária (inserção, busca e caminhamento em-ordem).
- `Paciente.java`: Nó da árvore contendo os dados do paciente e os ponteiros `esquerda` e `direita`.
- `TipoAtendimento.java`: Enumeração dos tipos de atendimento disponíveis (*TRIAGEM*, *VACINACAO*, *CONSULTA_AGENDADA*).

---

## 💻 Como Executar o Projeto

1. **Clone este repositório:**
   ```bash
   git clone [https://github.com/seu-usuario/seu-repositorio.git](https://github.com/seu-usuario/seu-repositorio.git)
