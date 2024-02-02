package tareas;

import java.util.concurrent.Callable;

import Modelo.ChinpokomonDB;

public class TareaBorrarChinpokomon implements Callable<Boolean> {
    private String codigo = null;

    public TareaBorrarChinpokomon(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public Boolean call() throws Exception {
        try{
            boolean borradoOK = ChinpokomonDB.borrarChinpokomon(codigo);
            return borradoOK;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
