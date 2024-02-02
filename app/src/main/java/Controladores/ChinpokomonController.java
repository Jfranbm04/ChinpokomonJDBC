package Controladores;

import android.util.Log;

import com.example.trabajo01_multimedia.clases.Chinpokomon;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import Modelo.ChinpokomonDB;
import tareas.TareaActualizarChinpokomon;
import tareas.TareaBorrarChinpokomon;
import tareas.TareaInsertarChinpokomon;
import tareas.TareaObtenerChinpokomon;

public class ChinpokomonController {
    //------------------------------------------------------------------------------------------
    public static ArrayList<Chinpokomon> obtenerChinpos() {
        ArrayList<Chinpokomon> listaChinpokomon = null;
        FutureTask t = new FutureTask (new TareaObtenerChinpokomon());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        try {
            // Especifica un tiempo de espera razonable (por ejemplo, 5 segundos)
            listaChinpokomon = (ArrayList<Chinpokomon>) t.get(5000, TimeUnit.MILLISECONDS);
        } catch (ExecutionException e) {
            // Maneja la excepción de ejecución
            e.printStackTrace();
            // Registra el error de alguna manera
            Log.i("sql", "Error al obtener Chinpokomon: " + e.getMessage());
        } catch (InterruptedException | TimeoutException e) {
            // Maneja la excepción de interrupción o tiempo de espera agotado
            e.printStackTrace();
            // Registra el error de alguna manera
            Log.i("sql", "Error al obtener Chinpokomon: " + e.getMessage());
        }  finally {
            // Intenta detener el servicio de ejecución
            es.shutdown();
        }
        return listaChinpokomon;
    }
    //------------------------------------------------------------------------------------------

    public static boolean insertarChinpo(Chinpokomon c)
    {
        FutureTask t = new FutureTask(new TareaInsertarChinpokomon(c));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        boolean insercionOK = false;
        try {
            // Especifica un tiempo de espera razonable (por ejemplo, 5 segundos)
            insercionOK = (boolean) t.get(5000, TimeUnit.MILLISECONDS);
        } catch (ExecutionException e) {
            // Maneja la excepción de ejecución
            e.printStackTrace();
            // Registra el error de alguna manera
            Log.i("sql", "Error al insertar el Chinpokomon: " + e.getMessage());
        } catch (InterruptedException | TimeoutException e1) {
            // Maneja la excepción de interrupción o tiempo de espera agotado
            e1.printStackTrace();
            // Registra el error de alguna manera
            Log.i("sql", "Error al insertar el Chinpokomon: " + e1.getMessage());
        }  finally {
            // Intenta detener el servicio de ejecución
            es.shutdown();
        }
        return insercionOK;

    }
    //------------------------------------------------------------------------------------------
    public static boolean borrarChinpo(String codigo)
    {
        FutureTask t = new FutureTask(new TareaBorrarChinpokomon(codigo));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        boolean borradoOK = false;
        try {
            borradoOK = (boolean) t.get(5000, TimeUnit.MILLISECONDS);
        } catch (ExecutionException e) {
            // Maneja la excepción de ejecución
            e.printStackTrace();
            // Registra el error de alguna manera
            Log.i("sql", "Error al borrar el Chinpokomon: " + e.getMessage());
        } catch (InterruptedException | TimeoutException e1) {
            // Maneja la excepción de interrupción o tiempo de espera agotado
            e1.printStackTrace();
            // Registra el error de alguna manera
            Log.i("sql", "Error al borrar el Chinpokomon: " + e1.getMessage());
        }  finally {
            // Intenta detener el servicio de ejecución
            es.shutdown();
        }
        return borradoOK;

    }
    //------------------------------------------------------------------------------------------
    public static boolean actualizarChinpo(Chinpokomon c, int codigo) {
        FutureTask t = new FutureTask(new TareaActualizarChinpokomon(c, codigo));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        boolean actualizadoOK = false;
        try {
            actualizadoOK = (boolean) t.get(5000, TimeUnit.MILLISECONDS);
        } catch (ExecutionException e) {
            // Maneja la excepción de ejecución
            e.printStackTrace();
            // Registra el error de alguna manera
            Log.i("sql", "Error al actualizar el Chinpokomon: " + e.getMessage());
        } catch (InterruptedException | TimeoutException e1) {
            // Maneja la excepción de interrupción o tiempo de espera agotado
            e1.printStackTrace();
            // Registra el error de alguna manera
            Log.i("sql", "Error al actualizar el Chinpokomon: " + e1.getMessage());
        }  finally {
            // Intenta detener el servicio de ejecución
            es.shutdown();
        }
        return actualizadoOK;
    }

}
