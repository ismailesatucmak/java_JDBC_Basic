import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

    }

    public static void selectDemo() {
        Connection connection = null;
        DbHelper helper = new DbHelper();
        Statement statement = null;
        ResultSet resultSet;

        try {
            connection = helper.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select Code,Name,Continent,Region from country");
            ArrayList<Country> countries = new ArrayList<Country>();
            while (resultSet.next()) {
                countries.add(new Country(
                        resultSet.getString("Code"),
                        resultSet.getString("Name"),
                        resultSet.getString("Continent"),
                        resultSet.getString("Region")));
            }

            System.out.println(countries.size());

        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public static void insertData(){
        Connection connection = null;
        DbHelper helper = new DbHelper();
        PreparedStatement statement = null;
        ResultSet resultSet;

        try {
            connection = helper.getConnection();
            String sql = "insert into city (Name,CountryCode,District,Population) values (?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1,"Selçuklu");
            statement.setString(2,"TUR");
            statement.setString(3,"Konya");
            statement.setInt(4,550000);

            int result = statement.executeUpdate();
            System.out.println(result);
            System.out.println("Kayıt Eklendi");

        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public static void updateData(){
        Connection connection = null;
        DbHelper helper = new DbHelper();
        PreparedStatement statement = null;
        ResultSet resultSet;

        try {
            connection = helper.getConnection();
            String sql = "update city set population = 313131,district ='Turkey' where id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,4082);
            int result = statement.executeUpdate();
            System.out.println(result);
            System.out.println("Kayıt Güncellendi.");

        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public static void deleteData(){
        Connection connection = null;
        DbHelper helper = new DbHelper();
        PreparedStatement statement = null;
        ResultSet resultSet;

        try {
            connection = helper.getConnection();
            String sql = "delete from city where id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,4081);
            int result = statement.executeUpdate();
            System.out.println(result);
            System.out.println("Kayıt Silindi.");

        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}