/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Charts.EventosGraficaDePuntos;

import Charts.GraficaDePuntos.GraficaDePuntos;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 *
 * @author DOLFHANDLER
 */
public class EventoVentana implements ComponentListener {

    private GraficaDePuntos gp = null;

    public EventoVentana(Object objReferencia) {
        gp = (GraficaDePuntos) objReferencia;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        gp.setSize(e.getComponent().getSize());
        gp.iniciarComponentes();
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }

    private void Actualizar() {
        gp.Actualizar();
    }

}
