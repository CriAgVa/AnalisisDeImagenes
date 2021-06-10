/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fourier;

import gui.JFrameFFT;
import herramientas.HerramientasImagen;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author Cri
 */
public class FrecuenciasMain {
    public static void main(String[] args) {
        Image imagen = herramientas.HerramientasImagen.abrirImagen();
        Image grises = trabajarImagen.ModificarImagen.convertirAGrises(imagen);
        
        BufferedImage bI = HerramientasImagen.toBufferedImage(imagen);
        Gestor gestor = new Gestor();
        
        NumeroComplejo[][] imagenOriginal = gestor.obtenerDatosPorCanal(bI, HerramientasColor.CanalColor.AZUL);
        BufferedImage biRes = gestor.obtenerImagenFrecuencias(imagenOriginal, bI.getWidth(), bI.getHeight(), true, HerramientasColor.CanalColor.AZUL);
        Image iRes = HerramientasImagen.toImage(biRes);
        BufferedImage biEs = gestor.obtenerImagenEspacial(HerramientasColor.CanalColor.AZUL);
        Image iEs = herramientas.HerramientasImagen.toImage(biEs);
        JFrameFFT frame = new JFrameFFT(imagen);
        JFrameFFT frame1 = new JFrameFFT(iRes);
        JFrameFFT frame2 = new JFrameFFT(iEs);
        
        frame.setVisible(true);
        frame2.setVisible(true);
        /*
        JFrameFFT frame = new JFrameFFT(grises);
        frame.setVisible(true);
        BufferedImage bImage = HerramientasImagen.toBufferedImage(grises);
        Gestor gestor = new Gestor();
        NumeroComplejo[][] imagenOriginal = gestor.obtenerDatosPorCanal(bImage, HerramientasColor.CanalColor.ROJO);
        BufferedImage biRes = gestor.obtenerImagenFrecuencias(imagenOriginal, bImage.getWidth(),
        bImage.getHeight(), true);
        Image iRes = herramientas.HerramientasImagen.toImage(biRes);
        JFrameFFT frame2 = new JFrameFFT(iRes);
        frame2.setVisible(true);
        */
        
    }    
}
