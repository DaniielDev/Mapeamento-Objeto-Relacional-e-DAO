package cadastrobd.model;

import cadastrobd.model.util.*;
import java.sql.*;
import java.util.ArrayList;

public class PessoaFisicaDAO {

    public PessoaFisica getPessoa(int id) {
        PessoaFisica pf = null;
        try (Connection conn = ConectorBD.getConnection()) {
            String sql = "SELECT * FROM Pessoa p JOIN PessoaFisica pf ON p.id = pf.id WHERE p.id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                pf = new PessoaFisica(
                    rs.getInt("id"), rs.getString("nome"), rs.getString("logradouro"),
                    rs.getString("cidade"), rs.getString("estado"), rs.getString("telefone"),
                    rs.getString("email"), rs.getString("cpf"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pf;
    }

    public ArrayList<PessoaFisica> getPessoas() {
        ArrayList<PessoaFisica> lista = new ArrayList<>();
        try (Connection conn = ConectorBD.getConnection()) {
            String sql = "SELECT * FROM Pessoa p JOIN PessoaFisica pf ON p.id = pf.id";
            ResultSet rs = ConectorBD.getSelect(conn, sql);
            while (rs.next()) {
                PessoaFisica pf = new PessoaFisica(
                    rs.getInt("id"), rs.getString("nome"), rs.getString("logradouro"),
                    rs.getString("cidade"), rs.getString("estado"), rs.getString("telefone"),
                    rs.getString("email"), rs.getString("cpf"));
                lista.add(pf);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void incluir(PessoaFisica pf) {
        int id = SequenceManager.getValue("seq_id");
        pf.id = id;
        try (Connection conn = ConectorBD.getConnection()) {
            PreparedStatement stmt1 = conn.prepareStatement("INSERT INTO Pessoa VALUES (?, ?, ?, ?, ?, ?, ?)");
            stmt1.setInt(1, id);
            stmt1.setString(2, pf.nome);
            stmt1.setString(3, pf.logradouro);
            stmt1.setString(4, pf.cidade);
            stmt1.setString(5, pf.estado);
            stmt1.setString(6, pf.telefone);
            stmt1.setString(7, pf.email);
            stmt1.executeUpdate();

            PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO PessoaFisica VALUES (?, ?)");
            stmt2.setInt(1, id);
            stmt2.setString(2, pf.getCpf());
            stmt2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterar(PessoaFisica pf) {
        try (Connection conn = ConectorBD.getConnection()) {
            PreparedStatement stmt1 = conn.prepareStatement("UPDATE Pessoa SET nome=?, logradouro=?, cidade=?, estado=?, telefone=?, email=? WHERE id=?");
            stmt1.setString(1, pf.nome);
            stmt1.setString(2, pf.logradouro);
            stmt1.setString(3, pf.cidade);
            stmt1.setString(4, pf.estado);
            stmt1.setString(5, pf.telefone);
            stmt1.setString(6, pf.email);
            stmt1.setInt(7, pf.id);
            stmt1.executeUpdate();

            PreparedStatement stmt2 = conn.prepareStatement("UPDATE PessoaFisica SET cpf=? WHERE id=?");
            stmt2.setString(1, pf.getCpf());
            stmt2.setInt(2, pf.id);
            stmt2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        try (Connection conn = ConectorBD.getConnection()) {
            PreparedStatement stmt1 = conn.prepareStatement("DELETE FROM PessoaFisica WHERE id=?");
            stmt1.setInt(1, id);
            stmt1.executeUpdate();

            PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM Pessoa WHERE id=?");
            stmt2.setInt(1, id);
            stmt2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
