package Modelo;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.example.trabajo01_multimedia.clases.Chinpokomon;
import com.example.trabajo01_multimedia.utilidades.ImagenesBlobBitmap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ChinpokomonDB {
    //-------------------------------------------------------------------------------
    public static ArrayList<Chinpokomon> obtenerChinpokomon() {
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return null;
        }
        ArrayList<Chinpokomon> listaChinpokomon = new ArrayList<Chinpokomon>();
        try {
            Statement sentencia = conexion.createStatement();
            String ordenSQL = "SELECT * FROM chinpokomon ORDER BY codigo";
            ResultSet resultado = sentencia.executeQuery(ordenSQL);
            while(resultado.next())
            {
                int codigo = resultado.getInt("codigo");
                String nombre = resultado.getString("nombre");
                int nivel = resultado.getInt("nivel");
                String tipo = resultado.getString("tipo");
                String movimiento = resultado.getString("movimientos");

                Chinpokomon chinpokomon1 = new Chinpokomon(codigo, nombre, nivel, tipo, movimiento);
                listaChinpokomon.add(chinpokomon1);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return listaChinpokomon;
        } catch (SQLException e) {
            Log.i("sql", "error sql");
            return listaChinpokomon;
        }
    }
    //-------------------------------------------------------------------------------
    public static boolean insertarChinpokomon(Chinpokomon c) {
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return false;
        }
        try {
            String ordensql = "INSERT INTO chinpokomon (`codigo`, `nombre`,`nivel`, `tipo`,`movimiento`) VALUES (?,?,?,?,?);";
            PreparedStatement sentencia = conexion.prepareStatement(ordensql);
            sentencia.setInt(1, c.getCodigo());
            sentencia.setString(2, c.getNombre());
            sentencia.setInt(3, c.getNivel());
            sentencia.setString(4, c.getTipo());
            sentencia.setString(5, c.getMovimiento());


            int filasafectadas = sentencia.executeUpdate();
            sentencia.close();
            conexion.close();
            if(filasafectadas > 0)
            {
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
    }
    //------------------------------------------------------------
    /*
    public static boolean insertarFotodb(String codigo, ImageView fotoNueva) {
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        {
            return false;
        }
        try {
            String sql = "INSERT INTO chinpokomon_fotos (codigo, imagen) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
                preparedStatement.setString(1, codigo);

                fotoNueva.buildDrawingCache();
                Bitmap fotoBm = fotoNueva.getDrawingCache();
                byte[] fotoBytes = ImagenesBlobBitmap.bitmap_to_bytes_png(fotoBm);
                String fotoString = ImagenesBlobBitmap.byte_to_string(fotoBytes);

                preparedStatement.setString(2, fotoString);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Imagen registrada");
                } else {
                    System.out.println("Error al subir la foto");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Manejar la excepción según tus necesidades
        }
    }
    */
    //------------------------------------------------------------

    public static boolean borrarChinpokomon(String codigo) {
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return false;
        }
        try {
            String ordensql = "DELETE FROM `chinpokomon` WHERE (`codigo` = ?);";
            PreparedStatement sentencia = conexion.prepareStatement(ordensql);
            sentencia.setString(1, codigo);
            int filasafectadas = sentencia.executeUpdate();
            sentencia.close();
            conexion.close();
            if(filasafectadas > 0)
            {
                return true;
            }
            else {
                return false;
            }

        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean actualizarChinpokomon(Chinpokomon c, int codigo) {
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return false;
        }
        try {
            String ordensql = "UPDATE chinpokomon SET nombre = ?, nivel = ?, tipo = ?, movimiento = ? WHERE codigo = ?";
            PreparedStatement pst = conexion.prepareStatement(ordensql);
            pst.setString(1, c.getNombre());
            pst.setInt(2, c.getNivel());
            pst.setString(3, c.getTipo());
            pst.setString(4, c.getMovimiento());
            int filasAfectadas = pst.executeUpdate();
            pst.close();
            conexion.close();
            if(filasAfectadas > 0)
            {
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
    }
    //-----------------------------------------------------------------------



}
