package bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EjemploJDBC3 {
	public static void main(String args[]) {
		try {
			// Carga del driver
			String driver = "com.mysql.cj.jdbc.Driver";
			Class.forName(driver);
			System.out.println("DRIVER CARGADO  ---> " + driver);

			// Creaci�n de la "url"
			// se asume que el servidor de la base de datos esta en el localhost
			String url = "jdbc:mysql://localhost:3306/VentasDeLibros";

			// Conexi�n a la base de datos representada por la url
			// con el nombre de usuario SA y la clave en blanco
			Connection con = DriverManager.getConnection(url, "root", "root");
			System.out.println("CONECTADO A   ---> " + url);

			// Utilizar la conexi�n para crear el objeto sentencia
			Statement stmt = con.createStatement();

			// Ejecuci�n de la consulta usando el objeto de tipo
			// Statement para obtener el ResultSet
			String consulta = "SELECT * FROM Cliente ORDER BY NomCliente";
			ResultSet rs = stmt.executeQuery(consulta);
			System.out.println("CONSULTA EJECUTADA ---> " + consulta);

			// Imprimir fila por fila los resultados
			System.out.println("\nPROCESO DE RESULTADOS:\n");
			while (rs.next()) {
				System.out.println("  DNI    : " + rs.getString("DNI").trim());
				System.out.println("  NOMBRE   : "
						+ rs.getString("NomCliente").trim());
				System.out.println("  DIRECCION: "
						+ rs.getString("Direccion").trim());
				System.out.println("");
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
