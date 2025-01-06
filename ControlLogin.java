
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import Model.Conta;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ControlLogin {

    Connection conexao;
    PreparedStatement pst;

    // Método para autenticação de usuário
    public ResultSet altenticar_usuario(Conta User) {

        conexao = new Conexao().conexaoSQL();

        try {

            // SQL para selecionar registros na tabela 'pessoa' onde o nome e a senha correspondem
            String sql = "select * from pessoa where nome = ? and senha = ?";

            // Prepara a instrução SQL para execução
            pst = conexao.prepareStatement(sql);

            // Primeiro parâmetro (nome)
            pst.setString(1, User.getUsername());

            // Primeiro parâmetro (senha)
            pst.setString(2, User.getSenhauser());

            // Executa a consulta e obtém os resultados
            ResultSet rs = pst.executeQuery();

            // Retorna o conjunto de resultados
            return rs;
        } catch (Exception e) {

            // Mostra uma mensagem de erro em uma caixa de diálogo em caso de exceção
            JOptionPane.showMessageDialog(null, "erro na classe controllLogin" + e.getMessage());

            // Retorna null em caso de erro
            return null;
        }

    }

  public void Criaconta(Conta User) {
    conexao = new Conexao().conexaoSQL();
    String sql = "INSERT INTO pessoa (nome, senha) VALUES (?, ?)";

    try {
        pst = conexao.prepareStatement(sql);
        pst.setString(1, User.getUsername());
        pst.setString(2, User.getSenhauser());
        pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "inserido com sucesso");
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro no metodo criar conta: " + e);
    }
}


}
