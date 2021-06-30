package bdd;

import java.sql.*;
import java.util.*;
import java.io.*;

//EJEMPLO DE CONEXION JDBC A BASE DE DATOS MYSQL UTILIZANDO UN ARCHIVO DE TEXTO PARA OBTENER LAS ESPECIFICACIONES NECESARIAS. (RECOMENDABLE)

public class EjemploJDBC4 {
	public static void main(String args[]) {
		try {
			// Crear el objeto a Properties
			Properties p = new Properties();
			// Cargar el objeto a Properties desde un archivo
			p.load(new FileInputStream("Propiedadesjdbc.txt"));
			// Inicializar el valor de driver al de la propiedad Driverjdbc
			String driver = p.getProperty("Driverjdbc");
			Class.forName(driver);
			System.out.println("DRIVER CARGADO   ---> " + driver);

			// Inicializar el valor de url al de la propiedad Urljdbc
			String url = p.getProperty("Urljdbc");
			// Inicializar el valor de url al de la propiedad Usuariojdbc
			String usuario = p.getProperty("Usuariojdbc");
			// Inicializar el valor de url al de la propiedad Clavejdbc
			String clave = p.getProperty("Clavejdbc");
			// Conexión a la base de datos representada por la url
			// con el nombre de usuario "usuario" y la clave "clave"
			Connection con = DriverManager.getConnection(url, usuario, clave);
			System.out.println("CONECTADO A    ---> " + url);

			// Utilizar la conexión para crear el objeto sentencia
			Statement stmt = con.createStatement();

			// Ejecución de la consulta usando el objeto de tipo
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
