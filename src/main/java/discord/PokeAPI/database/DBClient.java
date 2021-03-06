package discord.PokeAPI.database;

import java.sql.*;

/*
 *  This class is used to interact with the sqlite database, simply to retrieve the pokemonId from the pokemon's name.
 *  This class can be extended to be the source of all pokemon information instead of the PokeAPI.
 */
public class DBClient {
    private final static String url = "jdbc:sqlite:rsrc/pokedex.sqlite";
    private static Connection connection = null;

    public static void connect() {
        if (connection != null) return;
        try {
            connection = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void close(){
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static int getId(String species){
        try {
            Statement s = connection.createStatement();
            String sql = String.format("SELECT id FROM pokemon_species WHERE identifier=\"%s\"", species);
            ResultSet rs = s.executeQuery(sql);
            if (rs.isClosed()) return -1;
            return rs.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static String getPokemonProse(int id){
        try {
            Statement s = connection.createStatement();
            String sql = String.format("SELECT form_description FROM pokemon_species_prose WHERE pokemon_species_id=\"%d\"", id);
            ResultSet rs = s.executeQuery(sql);
            return rs.getString("form_description");
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

}
