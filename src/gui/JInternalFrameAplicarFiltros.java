/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import filtros.FiltroButterworth;
import filtros.FiltroGaussiano;
import filtros.FiltroIdeal;
import fourier.Gestor;
import fourier.HerramientasColor;
import fourier.NumeroComplejo;
import herramientas.Convolucion;
import herramientas.HerramientasImagen;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 *
 * @author Cri
 */
public class JInternalFrameAplicarFiltros extends javax.swing.JInternalFrame {

    private JInternalFrameImagen internal;
    private Image imagenOg;
    private JFramePrincipal framePrincipal;
    /**
     * Creates new form JInternalFrameFiltros
     */
    public JInternalFrameAplicarFiltros(JInternalFrameImagen internal, JFramePrincipal framePrincipal) {
        this.framePrincipal = framePrincipal;
        this.internal = internal;
        initComponents();
        this.imagenOg = herramientas.HerramientasImagen.copiarImagen(internal.getImagenOriginal());
        BufferedImage bi = herramientas.HerramientasImagen.toBufferedImage(imagenOg);
        
        Dimension dim = new Dimension(bi.getWidth(), bi.getHeight());//int dim;
        
        this.jButton1.addActionListener(new ActionListener() {//pasa altas
            @Override
            public void actionPerformed(ActionEvent e) {
                Image grises = trabajarImagen.ModificarImagen.convertirAGrises(imagenOg);
                BufferedImage bI = HerramientasImagen.toBufferedImage(grises);
                Gestor gestor = new Gestor();
                NumeroComplejo[][] imagenOriginal = gestor.obtenerDatosPorCanal(bI, HerramientasColor.CanalColor.ROJO);
                BufferedImage biRes = gestor.obtenerImagenFrecuencias(imagenOriginal, bI.getWidth(), bI.getHeight(), true, HerramientasColor.CanalColor.ROJO);
                Image iRes = HerramientasImagen.toImage(biRes);

                JInternalFrameImagen internalNuevo = new JInternalFrameImagen(iRes);
                
                int radio = (bi.getHeight()/100) * (jSlider2.getValue());
                FiltroIdeal fi = new FiltroIdeal(radio, dim);
                fi.crearFiltroPasaAltas();
                Image nueva = fi.getImagen();
                
                double[][] kernel = new double[3][3];
                kernel[0][0] = 0;
                kernel[0][1] = -1;
                kernel[0][2] = 0;
                kernel[1][0] = -1;
                kernel[1][1] = 4;
                kernel[1][2] = -1;
                kernel[2][0] = 0;
                kernel[2][1] = -1;
                kernel[2][2] = 0;
                int divisor = jSlider2.getValue()/10;
                Convolucion conv = new Convolucion(kernel, divisor, imagenOg);
                BufferedImage biConv = conv.modificarImagen(imagenOg, kernel);
                Image nuevaConv = HerramientasImagen.toImage(biConv);
                JInternalFrameImagen internalNuevo2 = new JInternalFrameImagen(nuevaConv);
                
                internalNuevo.setVisible(true);
                internalNuevo2.setVisible(true);
                internal.setImagen(nueva); 
                framePrincipal.getjDesktopPanePrincipal().add(internalNuevo);
                framePrincipal.getjDesktopPanePrincipal().add(internalNuevo2);
            }
        });
        
        this.jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Image grises = trabajarImagen.ModificarImagen.convertirAGrises(imagenOg);
                BufferedImage bI = HerramientasImagen.toBufferedImage(grises);
                Gestor gestor = new Gestor();
                NumeroComplejo[][] imagenOriginal = gestor.obtenerDatosPorCanal(bI, HerramientasColor.CanalColor.ROJO);
                BufferedImage biRes = gestor.obtenerImagenFrecuencias(imagenOriginal, bI.getWidth(), bI.getHeight(), true, HerramientasColor.CanalColor.ROJO);
                Image iRes = HerramientasImagen.toImage(biRes);

                JInternalFrameImagen internalNuevo = new JInternalFrameImagen(iRes);
                
                int radio = (bi.getHeight()/100) * (jSlider2.getValue());
                FiltroIdeal fi = new FiltroIdeal(radio, dim);
                fi.crearFiltro();
                Image nueva = fi.getImagen();
                
                double[][] kernel = new double[3][3];
                kernel[0][0] = .1;
                kernel[0][1] = .1;
                kernel[0][2] = .1;
                kernel[1][0] = .1;
                kernel[1][1] = .1;
                kernel[1][2] = .1;
                kernel[2][0] = .1;
                kernel[2][1] = .1;
                kernel[2][2] = .1;
                int divisor = jSlider2.getValue()/10;
                Convolucion conv = new Convolucion(kernel, divisor, imagenOg);
                BufferedImage biConv = conv.modificarImagen(imagenOg, kernel);
                Image nuevaConv = HerramientasImagen.toImage(biConv);
                JInternalFrameImagen internalNuevo2 = new JInternalFrameImagen(nuevaConv);
                
                internalNuevo.setVisible(true);
                internalNuevo2.setVisible(true);
                internal.setImagen(nueva); 
                framePrincipal.getjDesktopPanePrincipal().add(internalNuevo);
                framePrincipal.getjDesktopPanePrincipal().add(internalNuevo2);
            }
        });
        
        this.jButton3.addActionListener(new ActionListener() {//pasa altas gaussiano
            @Override
            public void actionPerformed(ActionEvent e) {
                Image grises = trabajarImagen.ModificarImagen.convertirAGrises(imagenOg);
                BufferedImage bI = HerramientasImagen.toBufferedImage(grises);
                Gestor gestor = new Gestor();
                NumeroComplejo[][] imagenOriginal = gestor.obtenerDatosPorCanal(bI, HerramientasColor.CanalColor.ROJO);
                BufferedImage biRes = gestor.obtenerImagenFrecuencias(imagenOriginal, bI.getWidth(), bI.getHeight(), true, HerramientasColor.CanalColor.ROJO);
                Image iRes = HerramientasImagen.toImage(biRes);

                JInternalFrameImagen internalNuevo = new JInternalFrameImagen(iRes);
                
                int radio = (bi.getHeight()/100) * (jSlider2.getValue());
                FiltroGaussiano fi = new FiltroGaussiano(radio, dim);
                fi.crearFiltroPasaAltas();
                Image nueva = fi.getImagen();
                
                double[][] kernel = new double[3][3];
                kernel[0][0] = 0.1;
                kernel[0][1] = -.99;
                kernel[0][2] = 0.1;
                kernel[1][0] = -.99;
                kernel[1][1] = 4.1;
                kernel[1][2] = -.99;
                kernel[2][0] = 0.1;
                kernel[2][1] = -.99;
                kernel[2][2] = 0.1;
                
                int divisor = jSlider2.getValue()/10;
                Convolucion conv = new Convolucion(kernel, divisor, imagenOg);
                BufferedImage biConv = conv.modificarImagen(imagenOg, kernel);
                Image nuevaConv = HerramientasImagen.toImage(biConv);
                JInternalFrameImagen internalNuevo2 = new JInternalFrameImagen(nuevaConv);
                
                internalNuevo.setVisible(true);
                internalNuevo2.setVisible(true);
                internal.setImagen(nueva); 
                framePrincipal.getjDesktopPanePrincipal().add(internalNuevo);
                framePrincipal.getjDesktopPanePrincipal().add(internalNuevo2); 
            }
        });
        
        this.jButton4.addActionListener(new ActionListener() {//pasa bajas gaussiano
            @Override
            public void actionPerformed(ActionEvent e) {
                Image grises = trabajarImagen.ModificarImagen.convertirAGrises(imagenOg);
                BufferedImage bI = HerramientasImagen.toBufferedImage(grises);
                Gestor gestor = new Gestor();
                NumeroComplejo[][] imagenOriginal = gestor.obtenerDatosPorCanal(bI, HerramientasColor.CanalColor.ROJO);
                BufferedImage biRes = gestor.obtenerImagenFrecuencias(imagenOriginal, bI.getWidth(), bI.getHeight(), true, HerramientasColor.CanalColor.ROJO);
                Image iRes = HerramientasImagen.toImage(biRes);

                JInternalFrameImagen internalNuevo = new JInternalFrameImagen(iRes);
                
                int radio = (bi.getHeight()/100) * (jSlider2.getValue());
                FiltroGaussiano fi = new FiltroGaussiano(radio, dim);
                fi.crearFiltro();
                Image nueva = fi.getImagen();
                
                double[][] kernel = new double[3][3];
                kernel[0][0] = .2;
                kernel[0][1] = .2;
                kernel[0][2] = .2;
                kernel[1][0] = .2;
                kernel[1][1] = .2;
                kernel[1][2] = .2;
                kernel[2][0] = .2;
                kernel[2][1] = .2;
                kernel[2][2] = .2;
                
                
                int divisor = jSlider2.getValue()/10;
                Convolucion conv = new Convolucion(kernel, divisor, imagenOg);
                BufferedImage biConv = conv.modificarImagen(imagenOg, kernel);
                Image nuevaConv = HerramientasImagen.toImage(biConv);
                JInternalFrameImagen internalNuevo2 = new JInternalFrameImagen(nuevaConv);
                
                internalNuevo.setVisible(true);
                internalNuevo2.setVisible(true);
                internal.setImagen(nueva); 
                framePrincipal.getjDesktopPanePrincipal().add(internalNuevo);
                framePrincipal.getjDesktopPanePrincipal().add(internalNuevo2);
            }
        });
        
        this.jButton5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Image grises = trabajarImagen.ModificarImagen.convertirAGrises(imagenOg);
                BufferedImage bI = HerramientasImagen.toBufferedImage(grises);
                Gestor gestor = new Gestor();
                NumeroComplejo[][] imagenOriginal = gestor.obtenerDatosPorCanal(bI, HerramientasColor.CanalColor.ROJO);
                BufferedImage biRes = gestor.obtenerImagenFrecuencias(imagenOriginal, bI.getWidth(), bI.getHeight(), true, HerramientasColor.CanalColor.ROJO);
                Image iRes = HerramientasImagen.toImage(biRes);

                JInternalFrameImagen internalNuevo = new JInternalFrameImagen(iRes);
                
                int radio = (bi.getHeight()/100) * (jSlider2.getValue());
                FiltroButterworth fi = new FiltroButterworth(radio, dim);
                fi.crearFiltroPasaAltas();
                Image nueva = fi.getImagen();
                
                double[][] kernel = new double[3][3];
                kernel[0][0] = -0.05;
                kernel[0][1] = -1.05;
                kernel[0][2] = -0.05;
                kernel[1][0] = -1.05;
                kernel[1][1] = 3.95;
                kernel[1][2] = -1.05;
                kernel[2][0] = -0.05;
                kernel[2][1] = -1.05;
                kernel[2][2] = -0.05;
                int divisor = jSlider2.getValue()/10;
                Convolucion conv = new Convolucion(kernel, divisor, imagenOg);
                BufferedImage biConv = conv.modificarImagen(imagenOg, kernel);
                Image nuevaConv = HerramientasImagen.toImage(biConv);
                JInternalFrameImagen internalNuevo2 = new JInternalFrameImagen(nuevaConv);
                
                internalNuevo.setVisible(true);
                internalNuevo2.setVisible(true);
                internal.setImagen(nueva); 
                framePrincipal.getjDesktopPanePrincipal().add(internalNuevo);
                framePrincipal.getjDesktopPanePrincipal().add(internalNuevo2);
            }
        });
        
        this.jButton6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Image grises = trabajarImagen.ModificarImagen.convertirAGrises(imagenOg);
                BufferedImage bI = HerramientasImagen.toBufferedImage(grises);
                Gestor gestor = new Gestor();
                NumeroComplejo[][] imagenOriginal = gestor.obtenerDatosPorCanal(bI, HerramientasColor.CanalColor.ROJO);
                BufferedImage biRes = gestor.obtenerImagenFrecuencias(imagenOriginal, bI.getWidth(), bI.getHeight(), true, HerramientasColor.CanalColor.ROJO);
                Image iRes = HerramientasImagen.toImage(biRes);

                JInternalFrameImagen internalNuevo = new JInternalFrameImagen(iRes);
                
                int radio = (bi.getHeight()/100) * (jSlider2.getValue());
                FiltroButterworth fi = new FiltroButterworth(radio, dim);
                fi.crearFiltro();
                Image nueva = fi.getImagen();
                
                double[][] kernel = new double[3][3];
                kernel[0][0] = 0.05;
                kernel[0][1] = 0.05;
                kernel[0][2] = 0.05;
                kernel[1][0] = 0.05;
                kernel[1][1] = 0.05;
                kernel[1][2] = 0.05;
                kernel[2][0] = 0.05;
                kernel[2][1] = 0.05;
                kernel[2][2] = 0.05;
                int divisor = jSlider2.getValue()/10;
                Convolucion conv = new Convolucion(kernel, divisor, imagenOg);
                BufferedImage biConv = conv.modificarImagen(imagenOg, kernel);
                Image nuevaConv = HerramientasImagen.toImage(biConv);
                JInternalFrameImagen internalNuevo2 = new JInternalFrameImagen(nuevaConv);
                
                internalNuevo.setVisible(true);
                internalNuevo2.setVisible(true);
                internal.setImagen(nueva); 
                framePrincipal.getjDesktopPanePrincipal().add(internalNuevo);
                framePrincipal.getjDesktopPanePrincipal().add(internalNuevo2);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jSlider2 = new javax.swing.JSlider();

        setClosable(true);

        jLabel1.setText("Filtro Ideal");

        jButton1.setText("Pasa Altas");

        jButton2.setText("Pasa Bajas");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Filtro Gaussiano");

        jButton3.setText("Pasa Altas");

        jButton4.setText("Pasa Bajas");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel3.setText("Filtro Butterworth");

        jButton5.setText("Pasa Altas");

        jButton6.setText("Pasa Bajas");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel4.setText("Radio");

        jSlider2.setMajorTickSpacing(10);
        jSlider2.setPaintLabels(true);
        jSlider2.setPaintTicks(true);
        jSlider2.setValue(0);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton3)
                                .addGap(61, 61, 61)
                                .addComponent(jButton4))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(61, 61, 61)
                                .addComponent(jButton2))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton5)
                                .addGap(61, 61, 61)
                                .addComponent(jButton6))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(167, 167, 167)
                        .addComponent(jLabel4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 44, Short.MAX_VALUE)
                .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSlider jSlider2;
    // End of variables declaration//GEN-END:variables
}
