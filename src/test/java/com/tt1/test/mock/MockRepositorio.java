package com.tt1.test.mock;

import com.tt1.test.IRepositorio;
import com.tt1.test.ToDo;

import java.util.ArrayList;
import java.util.List;

public class MockRepositorio implements IRepositorio {
    private List<ToDo> tareas = new ArrayList<>();
    private List<String> emails = new ArrayList<>();

    @Override
    public void guardarTarea(ToDo tarea) {
        tareas.add(tarea);
    }

    @Override
    public ToDo encontrarTarea(String nombre) {
        return tareas.stream()
                .filter(t -> t.getNombre().equals(nombre))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void marcarComoCompletada(String nombre) {
        tareas.stream()
                .filter(t -> t.getNombre().equals(nombre))
                .findFirst()
                .ifPresent(t -> t.setCompletado(true));
    }

    @Override
    public void guardarEmail(String email) {
        emails.add(email);
    }

    @Override
    public List<ToDo> listarTareas() {
        return new ArrayList<>(tareas);
    }

    @Override
    public List<String> obtenerEmails() {
        return new ArrayList<>(emails);
    }
}
