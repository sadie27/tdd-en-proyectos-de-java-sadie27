package com.tt1.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ToDoTest {

    private ToDo tarea;

    @BeforeEach
    void setUp() {
        tarea = new ToDo("Estudiar", "Repasar apuntes", LocalDate.of(2026, 6, 1));
    }
    @Test
    void testConstructorInicializaNombre() {
        assertEquals("Estudiar", tarea.getNombre());
    }
    @Test
    void testConstructorInicializaDescripcion() {
        assertEquals("Repasar apuntes", tarea.getDescripcion());
    }
    @Test
    void testConstructorInicializaFechaLimite() {
        assertEquals(LocalDate.of(2026, 6, 1), tarea.getFechaLimite());
    }
    @Test
    void testCompletadoEsFalsePorDefecto() {
        assertFalse(tarea.isCompletado());
    }
    @Test
    void testSetCompletado() {
        tarea.setCompletado(true);

        assertTrue(tarea.isCompletado());
    }
    @Test
    void testSetNombre() {
        tarea.setNombre("Leer");
        assertEquals("Leer", tarea.getNombre());
    }
    @Test
    void testSetDescripcion() {
        tarea.setDescripcion("Nueva descripcion");
        assertEquals("Nueva descripcion", tarea.getDescripcion());
    }
    @Test
    void testSetFechaLimite() {
        LocalDate nuevaFecha = LocalDate.of(2026, 12, 31);
        tarea.setFechaLimite(nuevaFecha);
        assertEquals(nuevaFecha, tarea.getFechaLimite());
    }
}
