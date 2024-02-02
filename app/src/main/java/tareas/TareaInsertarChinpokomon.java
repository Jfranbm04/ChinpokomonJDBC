package tareas;

import com.example.trabajo01_multimedia.clases.Chinpokomon;

import java.util.concurrent.Callable;

import Modelo.ChinpokomonDB;


public class TareaInsertarChinpokomon implements Callable<Boolean> {
    private Chinpokomon c = null;

    public TareaInsertarChinpokomon(Chinpokomon c) {
        this.c = c;
    }

    @Override
    public Boolean call() throws Exception {
        try{
            boolean insercionOK = ChinpokomonDB.insertarChinpokomon(c);
            return insercionOK;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
