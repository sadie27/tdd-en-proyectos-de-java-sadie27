package com.tt1.test.mock;

import com.tt1.test.IMailer;

public class MockMailer implements IMailer {
    private String ultimaDireccion;
    private String ultimoMensaje;
    private int vecesLlamado = 0;

    @Override
    public boolean enviarCorreo(String direccion, String mensaje) {
        this.ultimaDireccion = direccion;
        this.ultimoMensaje = mensaje;
        this.vecesLlamado++;
        return true;
    }

    public String getUltimaDireccion() { return ultimaDireccion; }
    public String getUltimoMensaje() { return ultimoMensaje; }
    public int getVecesLlamado() { return vecesLlamado; }
    public boolean fueInvocado() { return vecesLlamado > 0; }
}
