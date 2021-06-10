/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import fourier.Gestor;
import fourier.HerramientasColor;
import fourier.NumeroComplejo;
import herramientas.HerramientasImagen;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 *
 * @author Cri
 */
public class JInternalFrameFrecuencias extends javax.swing.JInternalFrame {

    private JInternalFrameImagen internal;
    private Image imagenOg;
    private JFramePrincipal framePrincipal;
    /**
     * Creates new form JInternalFrameFrecuencias
     */
    public JInternalFrameFrecuencias(JInternalFrameImagen internal, JFramePrincipal framePrincipal) {
        this.framePrincipal = framePrincipal;
        this.internal = internal;
        initComponents();
        
        this.imagenOg = herramientas.HerramientasImagen.copiarImagen(internal.getImagenOriginal());
        this.jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jRadioButton1.isSelected()){
                    Image grises = trabajarImagen.ModificarImagen.convertirAGrises(imagenOg);
                    BufferedImage bI = HerramientasImagen.toBufferedImage(grises);
                    Gestor gestor = new Gestor();
                    NumeroComplejo[][] imagenOriginal = gestor.obtenerDatosPorCanal(bI, HerramientasColor.CanalColor.ROJO);
                    BufferedImage biRes = gestor.obtenerImagenFrecuencias(imagenOriginal, bI.getWidth(), bI.getHeight(), true, HerramientasColor.CanalColor.ROJO);
                    Image iRes = HerramientasImagen.toImage(biRes);
                    BufferedImage biEs = gestor.obtenerImagenEspacial(HerramientasColor.CanalColor.ROJO);
                    Image iEs = herramientas.HerramientasImagen.toImage(biEs);
                    
                    JInternalFrameImagen internalNuevo = new JInternalFrameImagen(iRes);
                    JInternalFrameImagen internalNuevo2 = new JInternalFrameImagen(iEs);
                       
                    internalNuevo.setVisible(true);
                    internalNuevo2.setVisible(true);
                    
                    framePrincipal.getjDesktopPanePrincipal().add(internalNuevo);
                    framePrincipal.getjDesktopPanePrincipal().add(internalNuevo2);
                }else if (jRadioButton2.isSelected()){
                    BufferedImage bI = HerramientasImagen.toBufferedImage(imagenOg);
                    Gestor gestor = new Gestor();

                    NumeroComplejo[][] imagenOriginal = gestor.obtenerDatosPorCanal(bI, HerramientasColor.CanalColor.AZUL);
                    BufferedImage biRes = gestor.obtenerImagenFrecuencias(imagenOriginal, bI.getWidth(), bI.getHeight(), true, HerramientasColor.CanalColor.AZUL);
                    Image iRes = HerramientasImagen.toImage(biRes);
                    BufferedImage biEs = gestor.obtenerImagenEspacial(HerramientasColor.CanalColor.AZUL);
                    Image iEs = herramientas.HerramientasImagen.toImage(biEs);
                    JInternalFrameImagen internalNuevo = new JInternalFrameImagen(iRes);
                    JInternalFrameImagen internalNuevo2 = new JInternalFrameImagen(iEs);
                       
                    internalNuevo.setVisible(true);
                    internalNuevo2.setVisible(true);
                    
                    framePrincipal.getjDesktopPanePrincipal().add(internalNuevo);
                    framePrincipal.getjDesktopPanePrincipal().add(internalNuevo2);
                }else if (jRadioButton3.isSelected()){
                    BufferedImage bI = HerramientasImagen.toBufferedImage(imagenOg);
                    Gestor gestor = new Gestor();

                    NumeroComplejo[][] imagenOriginal = gestor.obtenerDatosPorCanal(bI, HerramientasColor.CanalColor.VERDE);
                    BufferedImage biRes = gestor.obtenerImagenFrecuencias(imagenOriginal, bI.getWidth(), bI.getHeight(), true, HerramientasColor.CanalColor.VERDE);
                    Image iRes = HerramientasImagen.toImage(biRes);
                    BufferedImage biEs = gestor.obtenerImagenEspacial(HerramientasColor.CanalColor.VERDE);
                    Image iEs = herramientas.HerramientasImagen.toImage(biEs);
                    JInternalFrameImagen internalNuevo = new JInternalFrameImagen(iRes);
                    JInternalFrameImagen internalNuevo2 = new JInternalFrameImagen(iEs);
                       
                    internalNuevo.setVisible(true);
                    internalNuevo2.setVisible(true);
                    
                    framePrincipal.getjDesktopPanePrincipal().add(internalNuevo);
                    framePrincipal.getjDesktopPanePrincipal().add(internalNuevo2);
                }
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

        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setClosable(true);

        jRadioButton1.setText("Rojo");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setText("Azul");
        jRadioButton2.setActionCommand("Azul");

        jRadioButton3.setText("Verde");

        jLabel1.setText("Canal de color");

        jButton1.setText("Aplicar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton3)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton3))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    // End of variables declaration//GEN-END:variables
}
