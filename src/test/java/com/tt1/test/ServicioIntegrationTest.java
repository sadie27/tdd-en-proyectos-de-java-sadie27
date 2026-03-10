package com.tt1.test;

import com.tt1.test.mock.MockMailer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServicioIntegrationTest {

    private Servicio servicio;
    private MockMailer mockMailer;

    @BeforeEach
    void setUp() {
        DBStub db = new DBStub();
        Repositorio repositorio = new Repositorio(db);
        mockMailer = new MockMailer();
        servicio = new Servicio(repositorio, mockMailer);
    }
    @Test
    void testFlujoCompletoCrearYCompletarTarea() {
        servicio.crearTarea("Informe", LocalDate.of(2026, 12, 1));
        servicio.marcarComoCompletada("Informe");
        List<ToDo> pendientes = servicio.listarPendientes();
        assertEquals(0, pendientes.size());
    }
    @Test
    void testFlujoCompletoPendientesSinCompletar() {
        servicio.crearTarea("Tarea A", LocalDate.of(2026, 12, 1));
        servicio.crearTarea("Tarea B", LocalDate.of(2026, 12, 2));
        servicio.marcarComoCompletada("Tarea A");
        List<ToDo> pendientes = servicio.listarPendientes();
        assertEquals(1, pendientes.size());
        assertEquals("Tarea B", pendientes.get(0).getNombre());
    }
    @Test
    void testFlujoCompletoAlertaPorTareaVencida() {
        LocalDate ayer = LocalDate.now().minusDays(1);
        servicio.crearTarea("Vencida", ayer);
        servicio.agregarEmail("admin@test.com");
        servicio.listarPendientes();
        assertTrue(mockMailer.fueInvocado());
        assertEquals("admin@test.com", mockMailer.getUltimaDireccion());
    }
    @Test
    void testVariosEmailsRecibenAlertaPorTareaVencida() {
        servicio.agregarEmail("a@test.com");
        servicio.agregarEmail("b@test.com");
        LocalDate ayer = LocalDate.now().minusDays(1);
        servicio.crearTarea("Vencida", ayer);
        assertEquals(2, mockMailer.getVecesLlamado());
    }
}
