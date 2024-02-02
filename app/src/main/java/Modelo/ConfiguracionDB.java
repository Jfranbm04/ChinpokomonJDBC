package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfiguracionDB {
/*
    // public static final String DIRECCION_URL_RAIZ = "https://www.almansameteo.com/2dam/productos";
    public static final String DIRECCION_URL_RAIZ = "http://192.168.1.131/chinpokomonsql";
 */
    public static final int ancho_imagen = 500;
    public static final int alto_imagen = 500;

    // --------------------------------------------------------
    public static final String HOSTDB = "192.168.1.131";
    public static final String NOMBREDB = "chinpokomonsql";
    public static final String USUARIODB = "root";
    public static final String CLAVEDB = "";
    private static final String OPCIONESHORA = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    // las opciones de hora tambien las puedes poner en mysql
    // SET GLOBAL time_zone = '+1:00';
    public static final String PUERTOMYSQL = "3306";
    public static final String URLMYSQL = "jdbc:mysql://"+ HOSTDB +":"+PUERTOMYSQL + "/" + NOMBREDB + OPCIONESHORA;

    //----------------------------------------------------------....
    public static Connection conectarConBaseDeDatos() {
        try {
            Connection conexion = DriverManager.getConnection(URLMYSQL, USUARIODB, CLAVEDB);
            return conexion;
        } catch (SQLException e) {
            System.out.println("no se pudo establecer la conexion con la base de datos");
            return null;
        }
    }
    //-----------------------------------------------------------

}
