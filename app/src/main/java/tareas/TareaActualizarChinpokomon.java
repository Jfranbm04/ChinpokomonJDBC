package tareas;

import com.example.trabajo01_multimedia.clases.Chinpokomon;

import java.util.concurrent.Callable;

import Modelo.ChinpokomonDB;


public class TareaActualizarChinpokomon implements Callable<Boolean> {
    private Chinpokomon c = null;
    private int codigo = -1;

    public TareaActualizarChinpokomon(Chinpokomon c, int codigo) {
        this.c = c;
        this.codigo = codigo;
    }

    @Override
    public Boolean call() throws Exception {
        try{
            boolean actualizadoOK = ChinpokomonDB.actualizarChinpokomon(c, codigo);
            return actualizadoOK;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
