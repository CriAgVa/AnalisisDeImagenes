/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import org.jfree.chart.ChartColor;

/**
 *
 * @author Cri
 */
public class JInternalFrameIluminacion extends javax.swing.JInternalFrame {

    private JInternalFrameImagen internal;
    private Image imagenOriginal;
    /**
     * Creates new form JInternalFrameBinario
     */
    public JInternalFrameIluminacion(JInternalFrameImagen internal) {
        this.internal = internal;
        initComponents();
       
        this.imagenOriginal = herramientas.HerramientasImagen.copiarImagen(internal.getImagenOriginal());
        this.jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               int u = jSlider1.getValue();
               BufferedImage bi = herramientas.HerramientasImagen.toBufferedImage(imagenOriginal);
                Color color;
                int r, nR, g, nG, b, nB;
                for(int i = 0 ; i< bi.getWidth(); i++){
                    for(int j = 0 ; j < bi.getHeight(); j++){  
                        color = new Color(bi.getRGB(i, j));

                        r = color.getRed();
                        nR = comprobarColor(r + u);
                        
                        g = color.getGreen();
                        nG = comprobarColor(g + u);
                        
                        b = color.getBlue();
                        nB = comprobarColor(b + u);
                        
                        bi.setRGB(i, j, new Color(nR, nG, nB).getRGB()); 
                    }
                }
                Image nueva = herramientas.HerramientasImagen.toImage(bi);
                internal.setImagen(nueva); 
            }
        });
    }
    
    private int comprobarColor(int color){
        if (color > 255){
            color = 255;
        }else if(color < 0){
            color = 0;
        }
        
        return color;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSlider1 = new javax.swing.JSlider();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setTitle("Nivel de iluminacion");

        jSlider1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jSlider1.setMajorTickSpacing(10);
        jSlider1.setMaximum(255);
        jSlider1.setMinimum(-255);
        jSlider1.setPaintLabels(true);
        jSlider1.setPaintTicks(true);
        jSlider1.setValue(15);

        jButton1.setText("Modificar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 1029, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JSlider jSlider1;
    // End of variables declaration//GEN-END:variables
}
