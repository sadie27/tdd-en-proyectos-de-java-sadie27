package com.tt1.test;

import java.util.List;

public class Repositorio implements IRepositorio {
    private IDB db;

    public Repositorio(IDB db) {
        this.db = db;
    }

    @Override
    public ToDo encontrarTarea(String nombre) {
        return db.buscarTarea(nombre);
    }

    @Override
    public void marcarComoCompletada(String nombre) {
        ToDo tarea = db.buscarTarea(nombre);
        if (tarea != null) {
            tarea.setCompletado(true);
            db.actualizarTarea(tarea);
        }
    }

    @Override
    public void guardarTarea(ToDo tarea) {
        db.agregarTarea(tarea);
    }

    @Override
    public void guardarEmail(String email) {
        db.agregarEmail(email);
    }

    @Override
    public List<ToDo> listarTareas() {
        return db.obtenerTodas();
    }

    @Override
    public List<String> obtenerEmails() {
        return db.obtenerEmails();
    }
}
