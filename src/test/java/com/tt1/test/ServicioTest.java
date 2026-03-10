package com.tt1.test;

import com.tt1.test.mock.MockMailer;
import com.tt1.test.mock.MockRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServicioTest {

    private Servicio servicio;
    private MockRepositorio mockRepositorio;
    private MockMailer mockMailer;

    @BeforeEach
    void setUp() {
        mockRepositorio = new MockRepositorio();
        mockMailer = new MockMailer();
        servicio = new Servicio(mockRepositorio, mockMailer);
    }
    @Test
    void testCrearTareaLaGuardaEnElRepositorio() {
        LocalDate fechaLimite = LocalDate.of(2026, 12, 1);

        servicio.crearTarea("Tarea1", fechaLimite);

        ToDo guardada = mockRepositorio.encontrarTarea("Tarea1");
        assertNotNull(guardada);
        assertEquals("Tarea1", guardada.getNombre());
        assertEquals(fechaLimite, guardada.getFechaLimite());
    }
    @Test
    void testCrearTareaNoEstaCompletadaPorDefecto() {
        servicio.crearTarea("Tarea1", LocalDate.of(2026, 12, 1));
        assertFalse(mockRepositorio.encontrarTarea("Tarea1").isCompletado());
    }
    @Test
    void testAgregarEmailLoGuardaEnElRepositorio() {
        servicio.agregarEmail("user@test.com");
        List<String> emails = mockRepositorio.obtenerEmails();
        assertEquals(1, emails.size());
        assertEquals("user@test.com", emails.get(0));
    }
    @Test
    void testMarcarComoCompletadaActualizaElEstado() {
        servicio.crearTarea("Tarea1", LocalDate.of(2026, 12, 1));
        servicio.marcarComoCompletada("Tarea1");
        assertTrue(mockRepositorio.encontrarTarea("Tarea1").isCompletado());
    }
    @Test
    void testListarPendientesSoloRetornaNOCompletadas() {
        servicio.crearTarea("Pendiente", LocalDate.of(2026, 12, 1));
        servicio.crearTarea("Hecha", LocalDate.of(2026, 12, 1));
        servicio.marcarComoCompletada("Hecha");
        List<ToDo> pendientes = servicio.listarPendientes();
        assertEquals(1, pendientes.size());
        assertEquals("Pendiente", pendientes.get(0).getNombre());
    }
    @Test
    void testListarPendientesEnviaAlertaPorTareasVencidas() {
        LocalDate ayer = LocalDate.now().minusDays(1);
        servicio.crearTarea("Vencida", ayer);
        servicio.agregarEmail("alerta@test.com");
        servicio.listarPendientes();
        assertTrue(mockMailer.fueInvocado());
        assertEquals("alerta@test.com", mockMailer.getUltimaDireccion());
    }
    @Test
    void testListarPendientesNoEnviaAlertaSiNoHayVencidas() {
        LocalDate manana = LocalDate.now().plusDays(1);
        servicio.crearTarea("ALTiempo", manana);
        servicio.agregarEmail("alerta@test.com");
        servicio.listarPendientes();
        assertFalse(mockMailer.fueInvocado());
    }
    @Test
    void testTareaCompletadaNoGeneraAlertaAunqueEsteVencida() {
        LocalDate ayer = LocalDate.now().minusDays(1);
        servicio.crearTarea("Vencida", ayer);
        servicio.marcarComoCompletada("Vencida");
        servicio.agregarEmail("alerta@test.com");
        servicio.listarPendientes();
        assertFalse(mockMailer.fueInvocado());
    }
}
