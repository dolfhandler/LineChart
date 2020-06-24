/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Charts.GraficaDeBarras;

import Charts.IGraficos;
import Charts.Rectangulo;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author DOLFHANDLER
 */
public class Barra extends Rectangulo implements IGraficos {

    private String valorX;
    private String valorY;
    private String EtiquetaEjeY;
    private String EtiquetaEjeX;

    public Barra(String EtiquetaEjeX, String EtiquetaEjeY, String valorX, String valorY, int x, int y, int ancho, int alto, Color colorFondo, Color colorTexto) {
        super(x, y, ancho, alto, colorFondo, colorTexto);
        this.valorX = valorX;
        this.valorY = valorY;
        this.EtiquetaEjeY = EtiquetaEjeY;
        this.EtiquetaEjeX = EtiquetaEjeX;
    }

    @Override
    public void pintar(Graphics2D g) {
        g.setColor(colorFondo);
        g.fillRect(x, y, ancho, alto);
    }

    public String getValorX() {
        return valorX;
    }

    public void setValorX(String valorX) {
        this.valorX = valorX;
    }

    public String getValorY() {
        return valorY;
    }

    public void setValorY(String valorY) {
        this.valorY = valorY;
    }

    public String getEtiquetaEjeY() {
        return EtiquetaEjeY;
    }

    public void setEtiquetaEjeY(String EtiquetaEjeY) {
        this.EtiquetaEjeY = EtiquetaEjeY;
    }

    public String getEtiquetaEjeX() {
        return EtiquetaEjeX;
    }

    public void setEtiquetaEjeX(String EtiquetaEjeX) {
        this.EtiquetaEjeX = EtiquetaEjeX;
    }

}
