package cadastrobd;
import cadastrobd.model;
import java.util.Scanner;


public class cadastrobd {
    public static void main(String[] args) {
        


public class CadastroBDTeste {
    private static Scanner scanner = new Scanner(System.in);
    private static PessoaFisicaDAO pfDAO = new PessoaFisicaDAO();
    private static PessoaJuridicaDAO pjDAO = new PessoaJuridicaDAO();

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1 - Incluir");
            System.out.println("2 - Alterar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Exibir por ID");
            System.out.println("5 - Exibir todos");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            try {
                switch (opcao) {
                    case 1 -> incluir();
                    case 2 -> alterar();
                    case 3 -> excluir();
                    case 4 -> exibirPorId();
                    case 5 -> exibirTodos();
                    case 0 -> System.out.println("Saindo do sistema...");
                    default -> System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
                e.printStackTrace();
            }
        } while (opcao != 0);
    }

    private static void incluir() {
        System.out.print("Pessoa Física (F) ou Jurídica (J)? ");
        String tipo = scanner.nextLine().toUpperCase();
        if (tipo.equals("F")) {
            PessoaFisica pf = lerPessoaFisica();
            pfDAO.incluir(pf);
            System.out.println("Pessoa física incluída com sucesso!");
        } else if (tipo.equals("J")) {
            PessoaJuridica pj = lerPessoaJuridica();
            pjDAO.incluir(pj);
            System.out.println("Pessoa jurídica incluída com sucesso!");
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void alterar() {
        System.out.print("Pessoa Física (F) ou Jurídica (J)? ");
        String tipo = scanner.nextLine().toUpperCase();
        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (tipo.equals("F")) {
            PessoaFisica pf = pfDAO.getPessoa(id);
            if (pf != null) {
                System.out.println("Dados atuais:");
                pf.exibir();
                PessoaFisica novaPf = lerPessoaFisica();
                novaPf.setId(id);
                pfDAO.alterar(novaPf);
                System.out.println("Pessoa física alterada com sucesso!");
            } else {
                System.out.println("Pessoa física não encontrada.");
            }
        } else if (tipo.equals("J")) {
            PessoaJuridica pj = pjDAO.getPessoa(id);
            if (pj != null) {
                System.out.println("Dados atuais:");
                pj.exibir();
                PessoaJuridica novaPj = lerPessoaJuridica();
                novaPj.setId(id);
                pjDAO.alterar(novaPj);
                System.out.println("Pessoa jurídica alterada com sucesso!");
            } else {
                System.out.println("Pessoa jurídica não encontrada.");
            }
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void excluir() {
        System.out.print("Pessoa Física (F) ou Jurídica (J)? ");
        String tipo = scanner.nextLine().toUpperCase();
        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (tipo.equals("F")) {
            pfDAO.excluir(id);
            System.out.println("Pessoa física excluída com sucesso!");
        } else if (tipo.equals("J")) {
            pjDAO.excluir(id);
            System.out.println("Pessoa jurídica excluída com sucesso!");
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void exibirPorId() {
        System.out.print("Pessoa Física (F) ou Jurídica (J)? ");
        String tipo = scanner.nextLine().toUpperCase();
        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (tipo.equals("F")) {
            PessoaFisica pf = pfDAO.getPessoa(id);
            if (pf != null) {
                pf.exibir();
            } else {
                System.out.println("Pessoa física não encontrada.");
            }
        } else if (tipo.equals("J")) {
            PessoaJuridica pj = pjDAO.getPessoa(id);
            if (pj != null) {
                pj.exibir();
            } else {
                System.out.println("Pessoa jurídica não encontrada.");
            }
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void exibirTodos() {
        System.out.print("Pessoa Física (F) ou Jurídica (J)? ");
        String tipo = scanner.nextLine().toUpperCase();

        if (tipo.equals("F")) {
            List<PessoaFisica> listaPf = pfDAO.getPessoas();
            if (listaPf.isEmpty()) {
                System.out.println("Nenhuma pessoa física cadastrada.");
            } else {
                for (PessoaFisica pf : listaPf) {
                    pf.exibir();
                }
            }
        } else if (tipo.equals("J")) {
            List<PessoaJuridica> listaPj = pjDAO.getPessoas();
            if (listaPj.isEmpty()) {
                System.out.println("Nenhuma pessoa jurídica cadastrada.");
            } else {
                for (PessoaJuridica pj : listaPj) {
                    pj.exibir();
                }
            }
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static PessoaFisica lerPessoaFisica() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Logradouro: ");
        String logradouro = scanner.nextLine();
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        System.out.print("Estado: ");
        String estado = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        return new PessoaFisica(0, nome, logradouro, cidade, estado, telefone, email, cpf);
    }

    private static PessoaJuridica lerPessoaJuridica() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Logradouro: ");
        String logradouro = scanner.nextLine();
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        System.out.print("Estado: ");
        String estado = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("CNPJ: ");
        String cnpj = scanner.nextLine();
        return new PessoaJuridica(0, nome, logradouro, cidade, estado, telefone, email, cnpj);
    }
}

    }
}
