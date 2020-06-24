/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Charts.GraficaDeBarras;

import Charts.EventosGraficaDeBarras.EventoVentana;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author DOLFHANDLER
 */
public class GraficaDeBarras extends JPanel {
    private Object datos;
    private JPanel panelContenedor;
    private int sepX;
    private int sepY;
    private int margenX;
    private int margenY;
    private int sepLogicaY;
    private ArrayList<Color> listaDeColores;
    public  ArrayList<Barra> listaDeBarras;
    private final int anchoLinea = 3;
    private int maximo;
    private int minimo;
    private int anchoTextoMaximo;
    private String nombreEjeX;
    private String nombreEjeY;
    private FontMetrics fm;
    private String titulo;
    public int band = 0;

    public GraficaDeBarras(Object datos, JPanel contenedor) {
        this.datos = datos;
        this.panelContenedor = contenedor;
        iniciarComponentes();
        panelContenedor.addComponentListener(new EventoVentana(this));
    }
    
    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        g.setFont(new Font("Calibri", Font.PLAIN, 14));
        pintarFondo(g2d);
        pintarEjeX(g2d);
        pintarEjeY(g2d);
        pintarTituloEjeX(g2d);
        pintarTituloEjeY(g2d);
        pintarTituloGrafica(g2d);
        pintarEtiquetasEjeX(g2d);
        pintarEtiquetasEjeY(g2d);
//        pintarUnionEntrePuntos(g2d);
        pintarListaDeBarras(g2d);
        
        g.dispose();
    }

    public void Actualizar() {
        repaint();
    }

    public void iniciarComponentes() {
        titulo = "Titulo del grafico";
        this.setSize(panelContenedor.getSize());
        margenX = 100;
        margenY = 100;
        listaDeBarras = new ArrayList<>();
        listaDeColores = new ArrayList<>();
        cargarColores();
        cargarMaximoYMinimo();
        cargarNombreEjes();
        cargarSeparacionXY();
        cargarListaDeBarras();
    }

    private void cargarColores() {
        listaDeColores.add(new Color(231, 76, 60));
        listaDeColores.add(new Color(52, 152, 219));
        listaDeColores.add(new Color(39, 174, 96));
        listaDeColores.add(new Color(142, 68, 173));
        listaDeColores.add(new Color(241, 196, 15));
    }

    private void cargarMaximoYMinimo() {
        ArrayList<ArrayList<Object[]>> data = new ArrayList<>();
        data = (ArrayList<ArrayList<Object[]>>) datos;
        maximo = getMaximoValor(data);
        minimo = getMinimoValor(data);
        sepLogicaY = minimo;
    }

    private int getMaximoValor(ArrayList<ArrayList<Object[]>> data) {
        int max = 0;
        for (int a = 0; a < data.size(); a++) {
            for (int i = 1; i < data.get(a).size(); i++) {
                if (max <= Integer.parseInt(data.get(a).get(i)[1].toString())) {
                    max = Integer.parseInt(data.get(a).get(i)[1].toString());
                }
            }
        }
        return max;
    }

    private int getMinimoValor(ArrayList<ArrayList<Object[]>> data) {
        int min = 0;
        for (int a = 0; a < data.size(); a++) {
            for (int i = 1; i < data.get(a).size(); i++) {
                if (i == 1) {
                    min = Integer.parseInt(data.get(a).get(i)[1].toString());
                }
                if (min >= Integer.parseInt(data.get(a).get(i)[1].toString())) {
                    min = Integer.parseInt(data.get(a).get(i)[1].toString());
                }
            }
        }
        return min;
    }

    private void cargarNombreEjes() {
        ArrayList<ArrayList<Object[]>> data = new ArrayList<>();
        data = (ArrayList<ArrayList<Object[]>>) datos;
        nombreEjeX = data.get(0).get(0)[0].toString();
        nombreEjeY = data.get(0).get(0)[1].toString();
    }

    private void cargarSeparacionXY() {
        int espacioDisponibleX = this.getWidth() - margenX * 2;
        ArrayList<ArrayList<Object[]>> data = new ArrayList<>();
        data = (ArrayList<ArrayList<Object[]>>) datos;
        int cantidadDeDatos = data.get(0).size();
        int separacionX = Math.round(espacioDisponibleX / cantidadDeDatos);
        sepX = separacionX;

        int espacioDisponibleY = this.getHeight() - margenY * 2;
        int separacionY = Math.round(espacioDisponibleY / (maximo / minimo + 1));
        sepY = separacionY;
    }

    private int cargarMaximoAnchoTexto(ArrayList<ArrayList<Object[]>> data, Graphics2D g) {
        int maximo = 0;
        FontMetrics fm = g.getFontMetrics();
        for (int a = 0; a < data.size(); a++) {
            for (int i = 1; i < data.get(a).size(); i++) {
                String texto = "" + data.get(a).get(i)[1];
                if (maximo <= fm.stringWidth(texto)) {
                    maximo = fm.stringWidth(texto);
                }
            }
        }
        return maximo;
    }

    private void cargarListaDeBarras() {
        ArrayList<ArrayList<Object[]>> data = new ArrayList<>();
        data = (ArrayList<ArrayList<Object[]>>) datos;
        listaDeBarras = new ArrayList<>();
        int sepBarras = 0;
        int ancho = anchoTextoMaximo-sepBarras;

        for (int a = 0; a < data.size(); a++) {
            Color color = listaDeColores.get(a);
            for (int i = 1; i < data.get(a).size(); i++) {
                int y = Integer.parseInt(data.get(a).get(i)[1].toString());
                int ty = Math.round(y * sepY / sepLogicaY);//se calcula la proporcion para la altura del punto en el eje Y

                listaDeBarras.add(new Barra(
                        data.get(a).get(0)[0].toString(),
                        data.get(a).get(0)[1].toString(),
                        data.get(a).get(i)[0].toString(),
                        data.get(a).get(i)[1].toString(),
                        getTX(sepX * i)-ancho/2,
                        getTY(ty),
                        ancho, ty,
                        color,
                        color)
                );
            }
        }
    }

    private int getTY(int valor) {
        return (this.getHeight() - margenY) - valor;
    }

    private int getTX(int valor) {
        return valor + margenX;
    }

    private void pintarFondo(Graphics2D g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    private void pintarEjeX(Graphics2D g) {
        anchoTextoMaximo = cargarMaximoAnchoTexto((ArrayList<ArrayList<Object[]>>) datos, g);
        int margen = margenX + anchoTextoMaximo;
        g.setColor(Color.gray);
        g.fillRect(margen, this.getHeight() - margenY, this.getWidth() - margen * 2, anchoLinea);//eje x
    }

    private void pintarEjeY(Graphics2D g) {
        int espacioDisponibleX = this.getWidth() - margenX * 2;
        int margen = anchoTextoMaximo;

        g.setColor(Color.lightGray);
        int a = 0;
        for (int i = 0; i < maximo + minimo; i += minimo) {
            g.drawRect(getTX(margen), getTY(a * sepY), espacioDisponibleX - (anchoTextoMaximo * 2), 0);
            a++;
        }
    }

    private void pintarEtiquetasEjeX(Graphics2D g) {
        ArrayList<ArrayList<Object[]>> data = new ArrayList<>();
        data = (ArrayList<ArrayList<Object[]>>) datos;

        g.setColor(Color.darkGray);
        FontMetrics fm = g.getFontMetrics();
        int a = 1;
        for (int i = 0; a < data.get(0).size(); i += sepX) {
            Rectangle2D recTexto = fm.getStringBounds(data.get(0).get(a)[0].toString(), g);
            int anchoTexto = (int) recTexto.getWidth();
            int altoTexto = (int) recTexto.getHeight();
            g.drawString(
                    data.get(0).get(a)[0].toString(),
                    getTX(i + sepX) - (anchoTexto / 2),
                    this.getHeight() - margenY + altoTexto
            );
            a++;
        }
    }

    private void pintarEtiquetasEjeY(Graphics2D g) {
        g.setColor(Color.darkGray);
        FontMetrics fm = g.getFontMetrics();
        int a = 0;
        for (int i = 0; i < maximo + minimo; i += minimo) {
            g.drawString(
                    "" + i,
                    margenX - (fm.stringWidth("" + i)),
                    getTY(a * sepY)
            );
            a++;
        }
    }
    
    private void pintarListaDeBarras(Graphics2D g) {
        for (int i = 0; i < listaDeBarras.size(); i++) {
            listaDeBarras.get(i).pintar(g);
        }
    }

    private void pintarTituloEjeY(Graphics2D g) {
        int centroY = this.getHeight() / 2;
        fm = g.getFontMetrics();
        int anchoTexto = fm.stringWidth(nombreEjeY);
        int x = margenX - anchoTextoMaximo - anchoTexto;

        g.setColor(Color.darkGray);
        drawRotate(g, x, centroY, 90, nombreEjeY);
    }
    
    private void pintarTituloEjeX(Graphics2D g) {
        fm = g.getFontMetrics();
        int anchoTexto = fm.stringWidth(nombreEjeX);
        int y = this.getHeight()-margenY+anchoTexto;
        int x = this.getWidth() / 2 - anchoTexto / 2;

        g.setColor(Color.darkGray);
        g.drawString(nombreEjeX, x, y);
    }

    private void pintarTituloGrafica(Graphics2D g) {
        fm = g.getFontMetrics();
        int anchoTexto = fm.stringWidth(titulo);
        int y = margenY / 2;
        int x = this.getWidth() / 2 - anchoTexto / 2;

        g.setColor(Color.darkGray);
        g.drawString(titulo, x, y);
    }

    public static void drawRotate(Graphics2D g, double x, double y, int angle, String text) {
        g.translate((float) x, (float) y);
        g.rotate(Math.toRadians(angle));
        g.drawString(text, 0, 0);
        g.rotate(-Math.toRadians(angle));
        g.translate(-(float) x, -(float) y);
    }
}
