import java.sql.*;

public class Prueba1 {
    public static void main(String[] args) {
        String url = "jdbc:mariadb://localhost:3306/adt_2425_t3_ejemplo";
        String user = "root";
        String password = "";
        try {
            // Establecer conexión
            Connection connection = DriverManager.getConnection(url, user, password);
            if (connection != null){
                System.out.println("Conexión realizada");
            }

            //Ejecutar consultas
            mostrarPizzas(connection);
            mostrarBebidas(connection);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static void mostrarBebidas(Connection connection) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs;
        System.out.println();
        System.out.println("------BEBIDAS-------");
        rs = stmt.executeQuery("SELECT * FROM bebidas");

        while(rs.next()){
            String nombre = rs.getString("nombre");
            float precio = rs.getFloat(("precio"));
            System.out.println(nombre + " (" + precio + ")");
        }
    }

    private static void mostrarPizzas(Connection connection) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM pizzas");
        System.out.println("------PIZZAS-------");
        while(rs.next()){
            String nombre = rs.getString("nombre");
            float precio = rs.getFloat("precio");
            String ingredientes = rs.getString("ingredientes");
            System.out.println(nombre + " (" + precio + ")");
            System.out.println(ingredientes);
            System.out.println("-----------------------");

        }
    }
}
