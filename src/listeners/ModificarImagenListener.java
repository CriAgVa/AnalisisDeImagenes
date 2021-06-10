/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import gui.JFramePrincipal;
import gui.JInternalFrameAplicarFiltros;
import gui.JInternalFrameBinario;
import gui.JInternalFrameFiltros;
import gui.JInternalFrameFrecuencias;
import gui.JInternalFrameIluminacion;
import gui.JInternalFrameImagen;
import gui.JInternalFrameKernel;
import gui.JInternalFrameModificar;
import gui.JInternalFrameMoverImagen;
import gui.JInternalFrameRuido;
import java.awt.Color;
import trabajarImagen.UmbralizacionAutomatica;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JMenuItem;
import trabajarImagen.Histograma;

/**
 *
 * @author Cri
 */
public class ModificarImagenListener implements ActionListener{
    
     
    private  JFramePrincipal framePrincipal;

    public ModificarImagenListener(JFramePrincipal framePrincipal) {
        this.framePrincipal = framePrincipal;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem item = (JMenuItem)e.getSource();
        if (item.getText().equals("Modificar Pixeles")){
            JInternalFrameImagen internal = (JInternalFrameImagen) this.framePrincipal.getjDesktopPanePrincipal().getSelectedFrame();
            JInternalFrameModificar internalNuevo = new JInternalFrameModificar(internal);
            internalNuevo.setVisible(true);
            this.framePrincipal.getjDesktopPanePrincipal().add(internalNuevo);
        }
        
           if (item.getText().equals("Kernel")){
            JInternalFrameImagen internal = (JInternalFrameImagen) this.framePrincipal.getjDesktopPanePrincipal().getSelectedFrame();
                       
            JInternalFrameKernel internalNuevo = new JInternalFrameKernel(internal);
                       
            internalNuevo.setVisible(true);
            this.framePrincipal.getjDesktopPanePrincipal().add(internalNuevo);
        }
           if (item.getText().equals("Blanco y Negro")){
            JInternalFrameImagen internal = (JInternalFrameImagen) this.framePrincipal.getjDesktopPanePrincipal().getSelectedFrame();
                       
            JInternalFrameBinario internalNuevo = new JInternalFrameBinario(internal);
                       
            internalNuevo.setVisible(true);
            this.framePrincipal.getjDesktopPanePrincipal().add(internalNuevo);
        }
           
           if (item.getText().equals("Negativo")){
            JInternalFrameImagen internal = (JInternalFrameImagen) this.framePrincipal.getjDesktopPanePrincipal().getSelectedFrame();
            Image i = internal.getImagenOriginal();
            // convertimos a escala de grises
            Image negativa = trabajarImagen.ModificarImagen.convertirANegativo(i);
            
            JInternalFrameImagen internalNuevo = new JInternalFrameImagen(negativa);
                       
            internalNuevo.setVisible(true);
            this.framePrincipal.getjDesktopPanePrincipal().add(internalNuevo);
        }   
         
            if (item.getText().equals("Escala de Grises")){
            JInternalFrameImagen internal = (JInternalFrameImagen) this.framePrincipal.getjDesktopPanePrincipal().getSelectedFrame();
            Image i = internal.getImagenOriginal();
            // convertimos a escala de grises
            Image grises = trabajarImagen.ModificarImagen.convertirAGrises(i);
            
            JInternalFrameImagen internalNuevo = new JInternalFrameImagen(grises);
                       
            internalNuevo.setVisible(true);
            this.framePrincipal.getjDesktopPanePrincipal().add(internalNuevo);
        }
            
            if (item.getText().equals("Eliminar fondo")){
            JInternalFrameImagen internal = (JInternalFrameImagen) this.framePrincipal.getjDesktopPanePrincipal().getSelectedFrame();
            Image i = internal.getImagenOriginal();
            // convertimos a escala de grises
            Image grises = trabajarImagen.ModificarImagen.eliminarFondo(i);
            
            JInternalFrameImagen internalNuevo = new JInternalFrameImagen(grises);
                       
            internalNuevo.setVisible(true);
            this.framePrincipal.getjDesktopPanePrincipal().add(internalNuevo);
        }
            
            if (item.getText().equals("Histograma")){
            JInternalFrameImagen internal = (JInternalFrameImagen) this.framePrincipal.getjDesktopPanePrincipal().getSelectedFrame();
            Image i = internal.getImagenOriginal();
            // convertimos a escala de grises
            Histograma h = new Histograma(i);
            h.calcularHistogramas();
            h.graficar();
        }
            if (item.getText().equals("Iluminacion")){
            JInternalFrameImagen internal = (JInternalFrameImagen) this.framePrincipal.getjDesktopPanePrincipal().getSelectedFrame();
                       
            JInternalFrameIluminacion internalNuevo = new JInternalFrameIluminacion(internal);
                       
            internalNuevo.setVisible(true);
            this.framePrincipal.getjDesktopPanePrincipal().add(internalNuevo);
        }
            
            if (item.getText().equals("Binarizacion automatica")){
            JInternalFrameImagen internal = (JInternalFrameImagen) this.framePrincipal.getjDesktopPanePrincipal().getSelectedFrame();
            Image i = internal.getImagenOriginal();
            // convertimos a escala de grises
            Image ibA = trabajarImagen.ModificarImagen.binarizacionAutomatica(i);
            
            JInternalFrameImagen internalNuevo = new JInternalFrameImagen(ibA);
            internalNuevo.setVisible(true);
            this.framePrincipal.getjDesktopPanePrincipal().add(internalNuevo);
        }
            
            if (item.getText().equals("Binarizacion automatica Otsu")){
            JInternalFrameImagen internal = (JInternalFrameImagen) this.framePrincipal.getjDesktopPanePrincipal().getSelectedFrame();
            Image i = internal.getImagenOriginal();
            // convertimos a escala de grises
            Image ibA = trabajarImagen.ModificarImagen.binarizacionAutomaticaOtsu(i);
            
            JInternalFrameImagen internalNuevo = new JInternalFrameImagen(ibA);
            internalNuevo.setVisible(true);
            this.framePrincipal.getjDesktopPanePrincipal().add(internalNuevo);
        }
            
            if (item.getText().equals("Multiplicar pixeles")){
            JInternalFrameImagen internal = (JInternalFrameImagen) this.framePrincipal.getjDesktopPanePrincipal().getSelectedFrame();
            Image i = internal.getImagenOriginal();
            // convertimos a escala de grises
            Image multiplicacion = trabajarImagen.ModificarImagen.multiplicarPixeles(i);
            
            JInternalFrameImagen internalNuevo = new JInternalFrameImagen(multiplicacion);
                       
            internalNuevo.setVisible(true);
            this.framePrincipal.getjDesktopPanePrincipal().add(internalNuevo);
        }
            
            if (item.getText().equals("Multiplicar pixeles logaritmico")){
            JInternalFrameImagen internal = (JInternalFrameImagen) this.framePrincipal.getjDesktopPanePrincipal().getSelectedFrame();
            Image i = internal.getImagenOriginal();
            
            Image multiplicacion = trabajarImagen.ModificarImagen.multiplicarPixelesLogaritmico(i);
            
            JInternalFrameImagen internalNuevo = new JInternalFrameImagen(multiplicacion);
                       
            internalNuevo.setVisible(true);
            this.framePrincipal.getjDesktopPanePrincipal().add(internalNuevo);
        }
            
            if (item.getText().equals("Multiplicar pixeles exponencial")){
            JInternalFrameImagen internal = (JInternalFrameImagen) this.framePrincipal.getjDesktopPanePrincipal().getSelectedFrame();
            Image i = internal.getImagenOriginal();
            // convertimos a escala de grises
            Image multiplicacion = trabajarImagen.ModificarImagen.multiplicarPixelesExponencial(i);
            
            JInternalFrameImagen internalNuevo = new JInternalFrameImagen(multiplicacion);
                       
            internalNuevo.setVisible(true);
            this.framePrincipal.getjDesktopPanePrincipal().add(internalNuevo);
        }
            
            if (item.getText().equals("Ecualizar histograma")){
            JInternalFrameImagen internal = (JInternalFrameImagen) this.framePrincipal.getjDesktopPanePrincipal().getSelectedFrame();
            Image i = internal.getImagenOriginal();

            Image ecualizada = trabajarImagen.ModificarImagen.ecualizarHistograma(i);
            
            JInternalFrameImagen internalNuevo = new JInternalFrameImagen(ecualizada);
                       
            internalNuevo.setVisible(true);
            this.framePrincipal.getjDesktopPanePrincipal().add(internalNuevo);
        }
            
            if (item.getText().equals("Ruido")){
            JInternalFrameImagen internal = (JInternalFrameImagen) this.framePrincipal.getjDesktopPanePrincipal().getSelectedFrame();
                       
            JInternalFrameRuido internalNuevo = new JInternalFrameRuido(internal);
                       
            internalNuevo.setVisible(true);
            this.framePrincipal.getjDesktopPanePrincipal().add(internalNuevo);
        } 
            
            if (item.getText().equals("Frecuencias")){
            JInternalFrameImagen internal = (JInternalFrameImagen) this.framePrincipal.getjDesktopPanePrincipal().getSelectedFrame();
                       
            JInternalFrameFrecuencias internalNuevo = new JInternalFrameFrecuencias(internal, framePrincipal);
                       
            internalNuevo.setVisible(true);
            this.framePrincipal.getjDesktopPanePrincipal().add(internalNuevo);
        }
            
            if (item.getText().equals("Generacion de filtros")){
            JInternalFrameImagen internal = (JInternalFrameImagen) this.framePrincipal.getjDesktopPanePrincipal().getSelectedFrame();
                       
            JInternalFrameFiltros internalNuevo = new JInternalFrameFiltros(internal);
                       
            internalNuevo.setVisible(true);
            this.framePrincipal.getjDesktopPanePrincipal().add(internalNuevo);
        }
            
            if (item.getText().equals("Aplicacion de filtros")){
            JInternalFrameImagen internal = (JInternalFrameImagen) this.framePrincipal.getjDesktopPanePrincipal().getSelectedFrame();
                       
            JInternalFrameAplicarFiltros internalNuevo = new JInternalFrameAplicarFiltros(internal, framePrincipal);    
                       
            internalNuevo.setVisible(true);
            this.framePrincipal.getjDesktopPanePrincipal().add(internalNuevo);
        }
            
            if (item.getText().equals("Mover imagen")){
            JInternalFrameImagen internal = (JInternalFrameImagen) this.framePrincipal.getjDesktopPanePrincipal().getSelectedFrame();
                       
            JInternalFrameMoverImagen internalNuevo = new JInternalFrameMoverImagen(internal, framePrincipal);    
                       
            internalNuevo.setVisible(true);
            this.framePrincipal.getjDesktopPanePrincipal().add(internalNuevo);
        }
    }
}
