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
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 *
 * @author Cri
 */
public class JInternalFrameMoverImagen extends javax.swing.JInternalFrame {

    private JInternalFrameImagen internal;
    private Image imagenOg;
    private JFramePrincipal framePrincipal;
    /**
     * Creates new form JInternalFrameFrecuencias
     */
    public JInternalFrameMoverImagen(JInternalFrameImagen internal, JFramePrincipal framePrincipal) {
        this.framePrincipal = framePrincipal;
        this.internal = internal;
        initComponents();
        
        this.imagenOg = herramientas.HerramientasImagen.copiarImagen(internal.getImagenOriginal());
        this.jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int pixX = Integer.parseInt(jTextField1.getText());
                int pixY = Integer.parseInt(jTextField2.getText());
                BufferedImage biImgOg = HerramientasImagen.toBufferedImage(imagenOg);
                BufferedImage biLienzo = new BufferedImage((biImgOg.getWidth())+100, (biImgOg.getHeight())+100, 1);
            
                
                
                for (int i = 0; i < biLienzo.getWidth(); i++) {
                    for (int j = 0; j < biLienzo.getHeight(); j++) {
                        biLienzo.setRGB(i, j, new Color(255, 255, 255).getRGB());
                    }
                }
                int subContX = 0;
                int subContY = 0;
                
                int contadorY = 0;
                int contadorX = 0;
                
                int nX = 0, nY = 0;
                
                for (int i = 0; i < biLienzo.getWidth(); i++) {
                    contadorY = 0;
                    pixY = 0;
                    for (int j = 0; j < biLienzo.getHeight(); j++) {
                        //System.out.println("px lienzo en "+i+" "+j);
                         //   System.out.println("contador en "+contadorX+" "+contadorY);
                          //  System.out.println("pxX en "+pixX+" pxY en "+pixY);
                            
                        if (contadorX == pixX && contadorY == pixY){
                            if (pixX < biImgOg.getWidth() && pixY < biImgOg.getHeight()){
                              //  System.out.println("entro en al caso 1");
                                biLienzo.setRGB(i, j, biImgOg.getRGB(contadorX, contadorY));

                                pixY++;
                                
                            } else if (!(pixX < biImgOg.getWidth()) && pixY < biImgOg.getHeight()){
                                //pixX = Integer.parseInt(jTextField1.getText()) + nX; 
                               // pixY = Integer.parseInt(jTextField2.getText()) + nY; 
                                //nX++;
                               // nY++;
                            } else if ((pixX < biImgOg.getWidth()) && !(pixY < biImgOg.getHeight())){
                               // pixX = Integer.parseInt(jTextField1.getText()) + nX; 
                                //pixY = Integer.parseInt(jTextField2.getText()) + nY; 
                                //nX++;
                                //nY++;
                            }
                            
                            if (pixY == biImgOg.getHeight()){
                                pixX++;
                                
                            }
                        } 
                        /*
                        else if (contadorX != pixX && contadorY == pixY){
                            if (pixX < biImgOg.getWidth() && pixY < biImgOg.getHeight()){
                                System.out.println("entro en al caso 2");
                                biLienzo.setRGB(i, j, biImgOg.getRGB(contadorX, contadorY));
                                
                                pixY++;
                            } else {
                                pixY = Integer.parseInt(jTextField2.getText()) + nY;  
                                nY++;
                            }   
                            
                        } else if (contadorX == pixX && contadorY != pixY){
                            if (pixX < biImgOg.getWidth() && pixY < biImgOg.getHeight()){
                                System.out.println("entro en al caso 3");
                                biLienzo.setRGB(i, j, biImgOg.getRGB(contadorX, contadorY));
                                
                                pixX++;
                            } else{
                                pixX = Integer.parseInt(jTextField1.getText()) + nX;
                                nX++;
                            }  
                        }
                        */
                        
                    contadorY++;    
                    }
                    //pixX++;
                    contadorX++;
                }
              
                Image nueva = HerramientasImagen.toImage(biLienzo);
                JInternalFrameImagen internalNuevo = new JInternalFrameImagen(nueva);
                internalNuevo.setVisible(true);
                framePrincipal.getjDesktopPanePrincipal().add(internalNuevo);
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
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();

        setClosable(true);

        jLabel1.setText("Pixeles en x");

        jButton1.setText("Mover");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Pixeles en y");

        jTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField1.setText("0");
        jTextField1.setMinimumSize(new java.awt.Dimension(57, 20));
        jTextField1.setPreferredSize(new java.awt.Dimension(57, 20));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField2.setText("0");
        jTextField2.setMinimumSize(new java.awt.Dimension(57, 20));
        jTextField2.setPreferredSize(new java.awt.Dimension(57, 20));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
