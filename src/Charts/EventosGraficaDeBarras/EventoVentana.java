/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Charts.EventosGraficaDeBarras;

import Charts.EventosGraficaDeBarras.*;
import Charts.GraficaDeBarras.GraficaDeBarras;
import Charts.GraficaDePuntos.GraficaDePuntos;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 *
 * @author DOLFHANDLER
 */
public class EventoVentana implements ComponentListener {

    private GraficaDeBarras gb = null;

    public EventoVentana(Object objReferencia) {
        gb = (GraficaDeBarras) objReferencia;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        gb.setSize(e.getComponent().getSize());
        gb.iniciarComponentes();
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
        gb.Actualizar();
    }

}
