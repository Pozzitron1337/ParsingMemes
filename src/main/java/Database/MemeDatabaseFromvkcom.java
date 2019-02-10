package Database;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MemeDatabaseFromvkcom extends MemeDatabase {

    public MemeDatabaseFromvkcom(String url, String user, String password){
        super(url,user,password);
    }
    public void createTables(){
        /*
        This function creates tables vkpublics and vkmemes
        vkpublics is filling by hands.
        vkmemes in filling automatically
        */
        try{
            Statement statement=connection.createStatement();
            statement.execute("CREATE TABLE `vkpublics` (  `id` int(11) NOT NULL,  `public_url` varchar(255) NOT NULL,  PRIMARY KEY (`id`),  UNIQUE KEY `id_UNIQUE` (`id`),  UNIQUE KEY `public_url_UNIQUE` (`public_url`)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci");
            statement.execute("CREATE TABLE `memes`.`vkmemes` (\n" +
                    "  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,\n" +
                    "  `public_id` INT NOT NULL,\n" +
                    "  `public_name` VARCHAR(255) NOT NULL,\n" +
                    "  `urlmeme` VARCHAR(255) NOT NULL,\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,\n" +
                    "  UNIQUE INDEX `urlmeme_UNIQUE` (`urlmeme` ASC) VISIBLE);");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public Map<Integer,String> getPublics(){
        Map<Integer,String> urls=new HashMap<Integer, String>();
        try {
            Statement stm=connection.createStatement();
            ResultSet rs=stm.executeQuery("SELECT * FROM vkpublics");
            while (rs.next()){
                urls.put(rs.getInt("id"),rs.getString("public_url"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return urls;
    }
    public void addMeme(int public_id,String publicName,String link){
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO vkmemes (public_id,public_name,urlmeme) values (?,?,?)");
            preparedStatement.setInt(1,public_id);
            preparedStatement.setString(2,publicName);
            preparedStatement.setString(3,link);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
