package cadastrobd;

import cadastrobd.model.*;
import java.util.ArrayList;

public class CadastroBDTeste {
    public static void main(String[] args) {
        PessoaFisicaDAO pfDAO = new PessoaFisicaDAO();
        PessoaFisica pf = new PessoaFisica(0, "Maria Oliveira", "Rua B", "São Paulo", "SP", "2222-2222", "maria@email.com", "98765432100");
        
        System.out.println("Incluindo pessoa física...");
        pfDAO.incluir(pf);
        
        System.out.println("Alterando nome...");
        pf.setNome("Maria Oliveira da Silva");
        pfDAO.alterar(pf);

        System.out.println("Listando pessoas físicas:");
        ArrayList<PessoaFisica> lista = pfDAO.getPessoas();
        for (PessoaFisica p : lista) {
            p.exibir();
            System.out.println("-----");
        }

        System.out.println("Excluindo pessoa física...");
        pfDAO.excluir(pf.id);
    }
}
