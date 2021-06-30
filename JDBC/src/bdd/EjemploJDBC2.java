package bdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

//EJEMPLO BASICO DE CONEXION JDBC A BASE DE DATOS MYSQL CON EJECUCION DE CONSULTAS A TRAVES DE SENTENCIAS (STATEMENT)

public class EjemploJDBC2 {
	private java.sql.Connection con = null;
	private final String protocolo = "jdbc:mysql://";
	private final String nombreServidor = "localhost";
	private final String numeroPuerto = "3306";
	private final String nombreBDD = "VentasDeLibros";
	private final String nombreUsuario = "root";
	private final String clave = "root";

	// Constructor
	public EjemploJDBC2() {
	}

	private String getUrlDeConexion() {
		return protocolo + nombreServidor + ":" + numeroPuerto + "/"
				+ nombreBDD + "?" + "user=" + nombreUsuario + "&password="
				+ clave;
	}

	private java.sql.Connection getConexion() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = java.sql.DriverManager.getConnection(getUrlDeConexion(),
					nombreUsuario, clave);
			if (con != null)
				System.out.println("Conexi�n exitosa!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error. Trace en getConexion(): "
					+ e.getMessage());
		}
		return con;
	}

	public void consultaConJoin() {
		try {
			Connection con = getConexion();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT C.DNI, C.NomCliente, C.Direccion, L.ISBN FROM Cliente C INNER JOIN Libros L "
							+ "ON C.DNI = L.DNI;");

			while (rs.next()) {

				System.out.println("\t" + rs.getString("C.DNI") + "\t"
						+ rs.getString("C.NomCliente") + "\t" + rs.getString("C.Direccion") + "\t"
						+ rs.getString("L.ISBN"));
			}
			// Mostrando el resultado uno por uno
			rs = stmt.executeQuery("SELECT C.DNI, C.NomCliente, C.Direccion, L.ISBN FROM Cliente C INNER JOIN Libros L "
							+ "ON C.DNI = L.DNI;");

			// Obtener los meta datos del resultset
			ResultSetMetaData rsmd = rs.getMetaData();

			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				System.out.println("Columna " + i + " Nombre: "+ rsmd.getColumnName(i));
			}
			while (rs.next()) {
				System.out.println("\tDNI: " + rs.getString(1));
				System.out.println("\tNomCliente: " + rs.getString(2));
				System.out.println("\tDireccion: " + rs.getString(3));
				System.out.println("\tISBN: " + rs.getString(4));
			}
		} catch (SQLException e) {
			System.err.println("Error en consulta");
			e.printStackTrace();
		}
	}

	private void closeConnection() {
		try {
			if (con != null)
				con.close();
			con = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		EjemploJDBC2 pruebaBDD = new EjemploJDBC2();
		pruebaBDD.consultaConJoin();
		pruebaBDD.closeConnection();
	}
}
