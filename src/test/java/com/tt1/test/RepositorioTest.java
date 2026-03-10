package com.tt1.test;
import com.tt1.test.mock.MockDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RepositorioTest {

    private Repositorio repositorio;
    private MockDB mockDB;
    private ToDo tarea;

    @BeforeEach
    void setUp() {
        mockDB = new MockDB();
        repositorio = new Repositorio(mockDB);
        tarea = new ToDo("Comprar", "Ir al super", LocalDate.of(2026, 5, 1));
    }
    @Test
    void testGuardarTareaLaPersiste() {
        repositorio.guardarTarea(tarea);
        ToDo resultado = repositorio.encontrarTarea("Comprar");
        assertEquals("Comprar", resultado.getNombre());
    }
    @Test
    void testEncontrarTareaInexistenteRetornaNull() {
        ToDo resultado = repositorio.encontrarTarea("NoExiste");
        assertNull(resultado);
    }
    @Test
    void testMarcarComoCompletadaActualizaElEstado() {
        repositorio.guardarTarea(tarea);
        repositorio.marcarComoCompletada("Comprar");
        assertTrue(repositorio.encontrarTarea("Comprar").isCompletado());
    }
    @Test
    void testGuardarEmailLoAlmacena() {
        repositorio.guardarEmail("user@test.com");
        List<String> emails = repositorio.obtenerEmails();
        assertEquals(1, emails.size());
        assertEquals("user@test.com", emails.get(0));
    }
    @Test
    void testListarTareasRetornaTodasLasGuardadas() {
        ToDo otraTarea = new ToDo("Llamar", "Llamar al médico", LocalDate.of(2026, 6, 1));
        repositorio.guardarTarea(tarea);
        repositorio.guardarTarea(otraTarea);
        List<ToDo> tareas = repositorio.listarTareas();
        assertEquals(2, tareas.size());
    }
}
