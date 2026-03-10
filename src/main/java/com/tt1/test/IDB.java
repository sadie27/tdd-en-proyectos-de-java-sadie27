package com.tt1.test;

import java.util.List;

public interface IDB {
    void agregarTarea(ToDo tarea);
    ToDo buscarTarea(String nombre);
    List<ToDo> obtenerTodas();
    void actualizarTarea(ToDo tarea);
    void eliminarTarea(String nombre);
    void agregarEmail(String email);
    List<String> obtenerEmails();
}
