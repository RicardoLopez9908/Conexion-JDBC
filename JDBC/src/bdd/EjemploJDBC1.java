package bdd;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class EjemploJDBC1 {
	private java.sql.Connection con = null;
	private final String protocolo = "jdbc:mysql://";			
	private final String nombreServidor = "localhost";			
	private final String numeroPuerto = "3306";					
	private final String nombreBDD = "VentasDeLibros";		
	private final String nombreUsuario = "root";
	private final String clave = "root";						//depende de nuestra clave definida en MySql		
	private final String driver = "com.mysql.cj.jdbc.Driver"; 	//Es el utilizado para la version actual del conector 

	// Constructor
	public EjemploJDBC1() {
	}

	private String getUrlDeConexion() {
		return protocolo + nombreServidor + ":" + numeroPuerto + "/"
				+ nombreBDD + "?" + "user=" + nombreUsuario + "&password="
				+ clave;
	}

	private Connection getConexion() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(getUrlDeConexion(),nombreUsuario, clave);
			if (con != null)
				System.out.println("Conexi�n exitosa!!!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error. Trace en getConnection() : "
					+ e.getMessage());
		}
		return con;
	}

	/*
	 * Mostrar en pantalla las propiedades del driver y los detalles de la base
	 * de datos
	 */

	public void mostrarPropiedadesBDD() {
		DatabaseMetaData bddMetadatos = null;
		ResultSet rs = null;
		try {
			con = this.getConexion();
			if (con != null) {
				bddMetadatos = con.getMetaData();
				System.out.println("Informaci�n del Driver");
				System.out.println("\tNombre del Driver: "
						+ bddMetadatos.getDriverName());
				System.out.println("\tVersi�n del Driver: "
						+ bddMetadatos.getDriverVersion());
				System.out
						.println("\nInformaci�n del Servidor de Base de Datos ");
				System.out.println("\tNombre del Servidor de Base de Datos: "
						+ bddMetadatos.getDatabaseProductName());
				System.out.println("\tVersi�n del Servidor de Base de Datos: "
						+ bddMetadatos.getDatabaseProductVersion());
				System.out.println("Cat�logos disponibles (BDD) ");
				rs = bddMetadatos.getCatalogs();
				while (rs.next()) {
					System.out.println("\tCat�logo (BDD): " + rs.getString(1));
				}
				rs.close();
				rs = null;
				cerrarConexion();
			} else
				System.out.println("Error: No hay conexi�n activa");
		} catch (Exception e) {
			e.printStackTrace();
		}
		bddMetadatos = null;
	}

	private void cerrarConexion() {
		try {
			if (con != null)
				con.close();
			con = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		EjemploJDBC1 pruebaBDD = new EjemploJDBC1();
		pruebaBDD.mostrarPropiedadesBDD();
	}
}
