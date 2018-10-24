
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import simplejdbc.DAOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pedago
 */
public class Et {

    private final DataSource myDataSource;
    
    
    
    

	public Et(DataSource dataSource) {
		this.myDataSource = dataSource;
	}
        
        
        
        public List<String> region() throws DAOException {
            List<String> etats = new ArrayList<String>();

            String sql = "SELECT DISTINCT STATE FROM CUSTOMER";
            // Syntaxe "try with resources" 
            // cf. https://stackoverflow.com/questions/22671697/try-try-with-resources-and-connection-statement-and-resultset-closing
            try (   Connection connection = myDataSource.getConnection(); // Ouvrir une connexion
                    Statement stmt = connection.createStatement(); // On crée un statement pour exécuter une requête
                    ResultSet rs = stmt.executeQuery(sql) // Un ResultSet pour parcourir les enregistrements du résultat
            ) {
                    while (rs.next()) { // Pas la peine de faire while, il y a 1 seul enregistrement
                            // On récupère le champ NUMBER de l'enregistrement courant
                           etats.add(rs.getNString("STATE"));
                    }
            } catch (SQLException ex) {
                    Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
                    throw new DAOException(ex.getMessage());
            }

            return etats;

}
        
}
