package com.tt1.test.mock;

import com.tt1.test.IDB;
import com.tt1.test.ToDo;

import java.util.ArrayList;
import java.util.List;

public class MockDB implements IDB {
    private List<ToDo> tareas = new ArrayList<>();
    private List<String> emails = new ArrayList<>();

    @Override
    public void agregarTarea(ToDo tarea) {
        tareas.add(tarea);
    }

    @Override
    public ToDo buscarTarea(String nombre) {
        return tareas.stream()
                .filter(t -> t.getNombre().equals(nombre))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<ToDo> obtenerTodas() {
        return new ArrayList<>(tareas);
    }

    @Override
    public void actualizarTarea(ToDo tarea) {
        for (int i = 0; i < tareas.size(); i++) {
            if (tareas.get(i).getNombre().equals(tarea.getNombre())) {
                tareas.set(i, tarea);
                return;
            }
        }
    }

    @Override
    public void eliminarTarea(String nombre) {
        tareas.removeIf(t -> t.getNombre().equals(nombre));
    }

    @Override
    public void agregarEmail(String email) {
        emails.add(email);
    }

    @Override
    public List<String> obtenerEmails() {
        return new ArrayList<>(emails);
    }
}
