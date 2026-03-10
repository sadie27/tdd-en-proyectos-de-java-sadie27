package com.tt1.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DBStubTest {

    private DBStub db;
    private ToDo tarea;

    @BeforeEach
    void setUp() {
        db = new DBStub();
        tarea = new ToDo("Estudiar", "Repasar TDD", LocalDate.of(2026, 6, 1));
    }
    @Test
    void testAgregarYBuscarTarea() {
        db.agregarTarea(tarea);
        ToDo resultado = db.buscarTarea("Estudiar");
        assertEquals("Estudiar", resultado.getNombre());
    }
    @Test
    void testBuscarTareaInexistenteRetornaNull() {
        ToDo resultado = db.buscarTarea("NoExiste");
        assertNull(resultado);
    }
    @Test
    void testObtenerTodasRetornaTodasLasTareas() {
        ToDo otraTarea = new ToDo("Leer", "Leer libro", LocalDate.of(2026, 7, 1));
        db.agregarTarea(tarea);
        db.agregarTarea(otraTarea);
        List<ToDo> todas = db.obtenerTodas();
        assertEquals(2, todas.size());
    }
    @Test
    void testActualizarTarea() {
        db.agregarTarea(tarea);
        tarea.setCompletado(true);
        db.actualizarTarea(tarea);
        ToDo resultado = db.buscarTarea("Estudiar");
        assertTrue(resultado.isCompletado());
    }

    @Test
    void testEliminarTarea() {
        db.agregarTarea(tarea);
        db.eliminarTarea("Estudiar");
        ToDo resultado = db.buscarTarea("Estudiar");
        assertNull(resultado);
    }
    @Test
    void testAgregarYObtenerEmails() {
        db.agregarEmail("a@test.com");
        db.agregarEmail("b@test.com");
        List<String> emails = db.obtenerEmails();
        assertEquals(2, emails.size());
        assertTrue(emails.contains("a@test.com"));
        assertTrue(emails.contains("b@test.com"));
    }
}
