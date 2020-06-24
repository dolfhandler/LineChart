/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Charts.EventosGraficaDePuntos;

import Charts.GraficaDePuntos.GraficaDePuntos;
import Charts.GraficaDePuntos.Punto;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author DOLFHANDLER
 */
public class EventoMouseMotion implements MouseMotionListener {

    private GraficaDePuntos gp = null;

    public EventoMouseMotion(Object objReferencia) {
        gp = (GraficaDePuntos) objReferencia;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Actualizar();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        actividadSobreElPunto(e);
        Actualizar();
    }

    private void actividadSobreElPunto(MouseEvent e) {
        if (gp != null) {
            for (Punto punto : gp.listaDePuntos) {
                if (punto.estaSobreElemento(e.getPoint())) {
                    gp.setToolTipText(
                            "" + punto.getEtiquetaEjeX() + ": "
                            + punto.getValorX()
                            + "\n"
                            + "" + punto.getEtiquetaEjeY() + ": "
                            + punto.getValorY()
                    );
                    break;
                }
            }
        }
    }

    private void Actualizar() {
        gp.Actualizar();
    }

}
