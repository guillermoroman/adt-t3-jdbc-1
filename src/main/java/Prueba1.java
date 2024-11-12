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

            // Ejecutar consultas
            mostrarPizzas(connection);
            mostrarBebidas(connection);

            // Insertar una nueva pizza
            insertarPizza(connection, "Pizza Margarita", 8.5f, 13.9f, "Tomate, Mozzarella, Albahaca");
            insertarBebida(connection, "Zumo", 5.0f);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static void insertarPizza(Connection connection, String nombre, float coste, float precio, String ingredientes) {
        String query = "INSERT INTO pizzas (nombre, coste, precio, ingredientes) VALUES (?, ?, ?, ?)";
        try(PreparedStatement pstmt = connection.prepareStatement(query)){
            pstmt.setString(1, nombre);
            pstmt.setFloat(2, coste);
            pstmt.setFloat(3, precio);
            pstmt.setString(4, ingredientes);

            int rowsInserted = pstmt.executeUpdate();
            if(rowsInserted > 0){
                System.out.println("Pizza " + nombre + " añadida con éxito");
            } else {
                System.out.println("Impresión desde else");
            }


        } catch (SQLException e) {
            System.out.println("Error al insertar la pizza");
        }
    }

    private static void insertarBebida(Connection connection, String nombre, float precio) {
        String query = "INSERT INTO bebidas (nombre, precio) VALUES (?, ?)";
        try(PreparedStatement pstmt = connection.prepareStatement(query)){
            pstmt.setString(1, nombre);
            pstmt.setFloat(2, precio);

            int rowsInserted = pstmt.executeUpdate();
            if(rowsInserted > 0){
                System.out.println("Bebida " + nombre + " añadida con éxito");
            } else {
                System.out.println("Impresión desde else");
            }


        } catch (SQLException e) {
            System.out.println("Error al insertar la pizza");
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
