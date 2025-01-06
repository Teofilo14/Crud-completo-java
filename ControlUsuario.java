/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import Model.Usuario;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 *
 * @author JACINTO T T MIGUEL
 */
public class ControlUsuario {

    Connection con;
    PreparedStatement pst = null;
    ResultSet rs;
    ArrayList<Usuario> lista = new ArrayList<>();

    public void inserir(Usuario user) {

        con = new Conexao().conexaoSQL();
        String sql = "INSERT INTO usuarios(nome, email,funcao,nif) VALUES(?, ?, ?,?);";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, user.getNome());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getFuncao());
            pst.setString(4, user.getNif());

            pst.executeUpdate();
            pst.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro no método inserir: " + e.getMessage());

        }

    }

    public ArrayList<Usuario> adicionarTabela() {
        con = new Conexao().conexaoSQL();
        String sql = "select * from usuarios";
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Usuario user = new Usuario();
                user.setId(rs.getInt("id"));
                user.setNome(rs.getString("nome"));
                user.setFuncao(rs.getString("funcao"));
                user.setEmail(rs.getString("email"));
                user.setNif(rs.getString("nif"));

                lista.add(user);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro ao adicionar dados na tabela ");

        }
        return lista;
    }

    public ArrayList<Usuario> pesquisar(Usuario user) {
        con = new Conexao().conexaoSQL();
        String sql = "select * from Usuarios where nome = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, user.getNome());

            rs = pst.executeQuery();

            while (rs.next()) {

                user.setId(rs.getInt("id"));
                user.setNome(rs.getString("nome"));
                user.setFuncao(rs.getString("funcao"));
                user.setEmail(rs.getString("email"));
                user.setNif(rs.getString("nif"));

                lista.add(user);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro ao adicionar dados na tabela ");

        }
        return lista;
    }

    public void Actualizar(Usuario user) {

        String sql = " update usuarios set nome = ?, email =?,funcao=? where nif= ? ";

        con = new Conexao().conexaoSQL();
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, user.getNome());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getFuncao());
            pst.setString(4, user.getNif());
            pst.execute();

            JOptionPane.showMessageDialog(null, " registro actualizado");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "falha no metodo actualizar" + e);
        }

    }

    public void Eliminar(Usuario user) {
        String sql = "delete from usuarios where email=?";
        con = new Conexao().conexaoSQL();
        try {
            pst = con.prepareStatement(sql);

            pst.setString(1, user.getEmail());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "registro eliminado");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro no metodo excluir" + e);

        }

    }

}
