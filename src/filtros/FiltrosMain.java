/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filtros;

import fourier.Gestor;
import fourier.HerramientasColor;
import fourier.NumeroComplejo;
import gui.JFrameFFT;
import herramientas.HerramientasImagen;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author Cri
 */
public class FiltrosMain {
  public static void main(String[] args) {
      /*
      Image imagenO = herramientas.HerramientasImagen.abrirImagen();
      
      Image grises = trabajarImagen.ModificarImagen.convertirAGrises(imagenO);
        JFrameFFT frame = new JFrameFFT(grises);
        frame.setVisible(true);
        BufferedImage bImage = HerramientasImagen.toBufferedImage(grises);
      Gestor gestor = new Gestor(bImage);
        NumeroComplejo[][] imagenOriginal = gestor.obtenerDatosPorCanal(bImage, HerramientasColor.CanalColor.ROJO);
        BufferedImage iFre = gestor.obtenerImagenFrecuencias(imagenOriginal, bImage.getWidth(),
        bImage.getHeight(), true);
      JFrameFFT frame2 = new JFrameFFT(herramientas.HerramientasImagen.toImage(iFre));
        frame2.setVisible(true);
      // creamos el filtro
        FiltroIdealPasaBajas fipb = new FiltroIdealPasaBajas(35,new Dimension(512, 512));
        fipb.crearFiltro();
        NumeroComplejo[][] filtro = fipb.getFiltroEspacial();
        JFrameFFT frameFil = new JFrameFFT(fipb.getImagen());
        iFre = gestor.aplicarFiltro(filtro);
      
      
        BufferedImage imagenEspacial = gestor.obtenerImagenEspacial();
       JFrameFFT frame3 = new JFrameFFT(herramientas.HerramientasImagen.toImage(imagenEspacial));
       frame3.setVisible(true);
      */

       
     
    
    }  
}
