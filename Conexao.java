package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author JACINTO T T MIGUEL
 */
public class Conexao{

    private static final String url = "jdbc:mysql://localhost:3306/registro";
    private static final String usuarioBD = "root";
    private static final String SenhaBD= "root";

    public Connection conexaoSQL() {
        Connection conectar = null;
        try {
            conectar = DriverManager.getConnection(url, usuarioBD,SenhaBD);
          
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de conexão na classe Conexao: " + e.getMessage());
        }
        return conectar;
    }


}
