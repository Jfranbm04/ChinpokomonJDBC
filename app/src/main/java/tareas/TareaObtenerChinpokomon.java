package tareas;

import com.example.trabajo01_multimedia.clases.Chinpokomon;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import Modelo.ChinpokomonDB;

public class TareaObtenerChinpokomon implements Callable<ArrayList<Chinpokomon>> {

    @Override
    public ArrayList<Chinpokomon> call() throws Exception {
        ArrayList<Chinpokomon> listaChinpokomon= ChinpokomonDB.obtenerChinpokomon();
        return listaChinpokomon;
    }
}
