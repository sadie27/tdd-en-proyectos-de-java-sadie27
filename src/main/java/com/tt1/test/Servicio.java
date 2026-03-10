package com.tt1.test;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Servicio {
    private IRepositorio repositorio;
    private IMailer mailer;

    public Servicio(IRepositorio repositorio, IMailer mailer) {
        this.repositorio = repositorio;
        this.mailer = mailer;
    }
    public void crearTarea(String nombre, LocalDate fechaLimite) {
        ToDo tarea = new ToDo(nombre, "", fechaLimite);
        repositorio.guardarTarea(tarea);
        comprobarYAlertarVencidas();
    }
    public void agregarEmail(String email) {
        repositorio.guardarEmail(email);
        comprobarYAlertarVencidas();
    }
    public void marcarComoCompletada(String nombre) {
        repositorio.marcarComoCompletada(nombre);
        comprobarYAlertarVencidas();
    }
    public List<ToDo> listarPendientes() {
        comprobarYAlertarVencidas();
        return repositorio.listarTareas().stream()
                .filter(t -> !t.isCompletado())
                .collect(Collectors.toList());
    }
    private void comprobarYAlertarVencidas() {
        List<ToDo> vencidas = repositorio.listarTareas().stream()
                .filter(t -> !t.isCompletado() && t.getFechaLimite().isBefore(LocalDate.now()))
                .collect(Collectors.toList());

        if (!vencidas.isEmpty()) {
            List<String> emails = repositorio.obtenerEmails();
            for (ToDo tarea : vencidas) {
                for (String email : emails) {
                    mailer.enviarCorreo(email, "La tarea '" + tarea.getNombre() + "' ha vencido.");
                }
            }
        }
    }
}
