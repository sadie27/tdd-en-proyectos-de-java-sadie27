package com.tt1.test;

import java.util.List;

public interface IRepositorio {
    void guardarTarea(ToDo tarea);
    ToDo encontrarTarea(String nombre);
    void marcarComoCompletada(String nombre);
    void guardarEmail(String email);
    List<ToDo> listarTareas();
    List<String> obtenerEmails();
}
