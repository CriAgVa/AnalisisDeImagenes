/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import herramientas.HerramientasImagen;
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
public class JInternalFrameKernel extends javax.swing.JInternalFrame {
    int[][] matrizKernel = new int[5][5];
    int[][] nMatrizKernel = new int[3][3];
    int[] divisorYoffset = new int[2];
    private JInternalFrameImagen internal;
    private Image imagenOriginal;
    int elementoCentral = 1;
    boolean rojo, verde, azul;
    
    /**
     * Creates new form JInternalFrameKernell
     */
    public JInternalFrameKernel(JInternalFrameImagen internal) {
        this.internal = internal;
        initComponents();
          
        this.imagenOriginal = herramientas.HerramientasImagen.copiarImagen(internal.getImagenOriginal());

        this.jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                matrizKernel[0][0] = Integer.parseInt(jTextField1.getText());
                matrizKernel[0][1] = Integer.parseInt(jTextField3.getText());
                matrizKernel[0][2] = Integer.parseInt(jTextField2.getText());
                matrizKernel[0][3] = Integer.parseInt(jTextField4.getText());
                matrizKernel[0][4] = Integer.parseInt(jTextField5.getText());
                matrizKernel[1][0] = Integer.parseInt(jTextField6.getText());
                matrizKernel[1][1] = Integer.parseInt(jTextField7.getText());
                matrizKernel[1][2] = Integer.parseInt(jTextField8.getText());
                matrizKernel[1][3] = Integer.parseInt(jTextField9.getText());
                matrizKernel[1][4] = Integer.parseInt(jTextField10.getText());
                matrizKernel[2][0] = Integer.parseInt(jTextField11.getText());
                matrizKernel[2][1] = Integer.parseInt(jTextField12.getText());
                matrizKernel[2][2] = Integer.parseInt(jTextField13.getText());
                matrizKernel[2][3] = Integer.parseInt(jTextField14.getText());
                matrizKernel[2][4] = Integer.parseInt(jTextField15.getText());
                matrizKernel[3][0] = Integer.parseInt(jTextField16.getText());
                matrizKernel[3][1] = Integer.parseInt(jTextField17.getText());
                matrizKernel[3][2] = Integer.parseInt(jTextField18.getText());
                matrizKernel[3][3] = Integer.parseInt(jTextField19.getText());
                matrizKernel[3][4] = Integer.parseInt(jTextField20.getText());
                matrizKernel[4][0] = Integer.parseInt(jTextField26.getText());
                matrizKernel[4][1] = Integer.parseInt(jTextField22.getText());
                matrizKernel[4][2] = Integer.parseInt(jTextField23.getText());
                matrizKernel[4][3] = Integer.parseInt(jTextField24.getText());
                matrizKernel[4][4] = Integer.parseInt(jTextField25.getText());
                
                divisorYoffset[0] = Integer.parseInt(jTextField21.getText());
                divisorYoffset[1] = Integer.parseInt(jTextField27.getText());
                
                boolean aux = ajustarMatriz();
                if (aux == true){
                    imprimirMatriz(nMatrizKernel);
                    elementoCentral = 1;
                    Image nueva = herramientas.HerramientasImagen.toImage(modificarImagen(imagenOriginal, nMatrizKernel));
                    internal.setImagen(nueva);
                    System.out.println();
                }else{
                    imprimirMatriz(matrizKernel);
                    elementoCentral = 2;
                    Image nueva = herramientas.HerramientasImagen.toImage(modificarImagen(imagenOriginal, matrizKernel));
                    internal.setImagen(nueva);
                }
            }
        });
       
        this.jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jRadioButton5.isSelected()){
                    elementoCentral = 1;
                    divisorYoffset[0] = Integer.parseInt(jTextField21.getText());
                    divisorYoffset[1] = Integer.parseInt(jTextField27.getText());
                    
                    int[][] kirsch1 = {{-3, -3, 5}, {-3, 0, 5}, {-3, -3, 5}};
                    int[][] kirsch2 = {{-3, 5, 5}, {-3, 0, 5}, {-3, -3, -3}};
                    int[][] kirsch3 = {{5, 5, 5}, {-3, 0, -3}, {-3, -3, -3}};
                    int[][] kirsch4 = {{5, 5, -3}, {5, 0, -3}, {-3, -3, -3}};
                    int[][] kirsch5 = {{5, -3, -3}, {5, 0, -3}, {5, -3, -3}};
                    int[][] kirsch6 = {{-3, -3, -3}, {5, 0, -3}, {5, 5, -3}};
                    int[][] kirsch7 = {{-3, -3, -3}, {-3, 0, -3}, {5, 5, 5}};
                    int[][] kirsch8 = {{-3, -3, -3}, {-3, 0, 5}, {-3, 5, 5}};
                            
                    int[][][] arregloMascaras = {kirsch1, kirsch2, kirsch3,kirsch4, kirsch5, kirsch6,kirsch7, kirsch8};
                    
                    BufferedImage[] bancoBI = new BufferedImage[8];
                            
                    for (int i = 0; i < arregloMascaras.length; i++) {
                        bancoBI[i] = modificarImagen(imagenOriginal, arregloMascaras[i]);
                    }
                   
                    BufferedImage finalBI = new BufferedImage(bancoBI[0].getWidth(), bancoBI[0].getHeight(), bancoBI[0].getType());
                    int[][] cAuxR = new int[bancoBI[0].getHeight()][bancoBI[0].getWidth()];
                    int[][] cAuxG = new int[bancoBI[0].getHeight()][bancoBI[0].getWidth()];
                    int[][] cAuxB = new int[bancoBI[0].getHeight()][bancoBI[0].getWidth()];

                    Color  cA;
                    int cR, cB, cG;
                    int xxxx;
                    
                    for (int i = 0; i < bancoBI.length; i++) {
                        for (int j = 0; j < bancoBI[i].getHeight(); j++) {
                            for (int k = 0; k < bancoBI[i].getWidth(); k++) {
                                cA = new Color(bancoBI[i].getRGB(k, j));
                                cR = cA.getRed();
                                cB = cA.getBlue();
                                cG = cA.getGreen();
                                
                                cAuxR[j][k] += cR;
                                cAuxG[j][k] += cG;
                                cAuxB[j][k] += cB;
                            }
                        }
                    }
                    
                    int cAuxpreR[][] = cAuxR;
                    int cAuxpreG[][] = cAuxG;
                    int cAuxpreB[][] = cAuxB;
                    for (int i = 0; i < bancoBI[0].getHeight(); i++) {
                        for (int j = 0; j < bancoBI[0].getWidth(); j++) {
                            cAuxR[i][j] = comprobarColor(cAuxR[i][j]);
                            cAuxG[i][j] = comprobarColor(cAuxG[i][j]);
                            cAuxB[i][j] = comprobarColor(cAuxB[i][j]);
                        }
                    }
                    
                    for (int i = 0; i < bancoBI[0].getHeight(); i++) {
                        for (int j = 0; j < bancoBI[0].getWidth(); j++) {
                            finalBI.setRGB(j, i, new Color(cAuxR[i][j], cAuxG[i][j], cAuxB[i][j]).getRGB());
                        }
                    }
                    internal.setImagen(herramientas.HerramientasImagen.toImage(finalBI));
                }else if (jRadioButton6.isSelected()){
                    BufferedImage imgNueva;
                    imgNueva = medianFilter(imagenOriginal);
                    internal.setImagen(herramientas.HerramientasImagen.toImage(imgNueva));
                }
            }
        });
    }
    
    public JInternalFrameKernel(int[][] nMatrizKernel){
        this.nMatrizKernel = nMatrizKernel;
    }
    
    public void imprimirMatriz(int[][] matriz){
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                System.out.print(matriz[i][j]);
                if(j != matriz.length-1){
                    System.out.print("|");
                }
            }
            System.out.println();
            System.out.println("---------");
        }
    }
    
    public boolean ajustarMatriz(){
        boolean ceros5x5 = false;
        int[] arrayAux;
        
        arrayAux = matrizKernel[0];
        for (int i = 0; i < arrayAux.length; i++) {
            if (arrayAux[i] == 0){
                ceros5x5 = true;
            }else{
                ceros5x5 = false;
                return false;
            }
        }
        arrayAux[0] = matrizKernel[0][0];
        arrayAux[1] = matrizKernel[1][0];
        arrayAux[2] = matrizKernel[2][0];
        arrayAux[3] = matrizKernel[3][0];
        arrayAux[4] = matrizKernel[4][0];
        for (int i = 0; i < arrayAux.length; i++) {
            if (arrayAux[i] == 0){
                ceros5x5 = true;
            }else{
                ceros5x5 = false;
                return false;
            }
        }
        arrayAux = matrizKernel[4];
        for (int i = 0; i < arrayAux.length; i++) {
            if (arrayAux[i] == 0){
                ceros5x5 = true;
            }else{
                ceros5x5 = false;
                return false;
            }
        }
        arrayAux[0] = matrizKernel[0][4];
        arrayAux[1] = matrizKernel[1][4];
        arrayAux[2] = matrizKernel[2][4];
        arrayAux[3] = matrizKernel[3][4];
        arrayAux[4] = matrizKernel[4][4];
        for (int i = 0; i < arrayAux.length; i++) {
            if (arrayAux[i] == 0){
                ceros5x5 = true;
            }else{
                ceros5x5 = false;
                return false;
            }
        }
        
        if (ceros5x5 == true){
            nMatrizKernel[0][0] = matrizKernel[1][1];
            nMatrizKernel[0][1] = matrizKernel[1][2];
            nMatrizKernel[0][2] = matrizKernel[1][3];
            
            nMatrizKernel[1][0] = matrizKernel[2][1];
            nMatrizKernel[1][1] = matrizKernel[2][2];
            nMatrizKernel[1][2] = matrizKernel[2][3];
            
            nMatrizKernel[2][0] = matrizKernel[3][1];
            nMatrizKernel[2][1] = matrizKernel[3][2];
            nMatrizKernel[2][2] = matrizKernel[3][3];
        }
        return ceros5x5;
    }
    
    public BufferedImage modificarImagen(Image imagen, int[][] kernel){
        BufferedImage bi = herramientas.HerramientasImagen.toBufferedImage(imagen);
        int[][] matrizResultante = new int[bi.getHeight()][bi.getWidth()];
        int sumatoriaR, sumatoriaG, sumatoriaB = 0;
        int[][] matrizImagenR = new int[bi.getHeight()][bi.getWidth()];
        int[][] matrizImagenG = new int[bi.getHeight()][bi.getWidth()];
        int[][] matrizImagenB = new int[bi.getHeight()][bi.getWidth()];
        
        System.out.println();
        
        for (int i = 0; i < bi.getHeight(); i++) {
            for (int j = 0; j < bi.getWidth(); j++) {
                Color colorAux;
                
                colorAux = new Color (bi.getRGB(j, i));
                
                matrizImagenR[i][j] = colorAux.getRed();
                matrizImagenG[i][j] = colorAux.getGreen();
                matrizImagenB[i][j] = colorAux.getBlue();
            }
        }
        
        for (int i = 0; i < bi.getHeight(); i++) {
            for (int j = 0; j < bi.getWidth(); j++) {
                int aux1 = 0, aux2 = 0;
                sumatoriaR = 0; 
                sumatoriaG = 0;
                sumatoriaB = 0;
                Color color = new Color(112);
                
                int[] arregloAux = new int[9];
                int contadorAux = 0;
                if (i == 0 && j == 0){
                    //caso 0, 0, cualquier tamano
                 //   System.out.println("Entro en: ("+i+" ,"+j+") en el metodo i == 0 && j == 0");
                    for (int k = elementoCentral; k < kernel.length; k++) {
                        aux2 = 0;
                        for (int l = elementoCentral; l < kernel.length; l++) {
                            
                            if (jRadioButton1.isSelected()){
                                sumatoriaR += kernel[k][l] * matrizImagenR[aux1][aux2];
                            }else{
                                sumatoriaR = 0;
                            }
                            
                            if (jRadioButton2.isSelected()) {
                                sumatoriaG += kernel[k][l] * matrizImagenG[aux1][aux2];
                            }else{
                                sumatoriaG = 0;
                            }
                            
                            if (jRadioButton4.isSelected()){
                                sumatoriaB += kernel[k][l] * matrizImagenB[aux1][aux2];
                            }else{
                                sumatoriaB = 0;
                            }

                            color = new Color(comprobarColor((sumatoriaR/divisorYoffset[0])+divisorYoffset[1]), comprobarColor((sumatoriaG/divisorYoffset[0])+divisorYoffset[1]), comprobarColor((sumatoriaB/divisorYoffset[0])+divisorYoffset[1]));
                            
                            aux2++;
                        }
                        aux1++;
                    }
                    matrizResultante[i][j] = color.getRGB();
               //     System.out.println("Acabe el: ("+i+" ,"+j+") en el metodo i == 0 && j == 0");
                //caso i 0, j 1, cualquier tamano
                }else if (i == 0 && j == 1){
                //    System.out.println("Entro en: ("+i+" ,"+j+") en el metodo i == 0 && j == 1");
                    for (int k = elementoCentral; k < kernel.length; k++) {
                        aux2 = 0;
                        for (int l = elementoCentral-1; l < kernel.length; l++) {
                            if (jRadioButton1.isSelected()){
                                sumatoriaR += kernel[k][l] * matrizImagenR[aux1][aux2];
                            }else{
                                sumatoriaR = 0;
                            }
                            
                            if (jRadioButton2.isSelected()) {
                                sumatoriaG += kernel[k][l] * matrizImagenG[aux1][aux2];
                            }else{
                                sumatoriaG = 0;
                            }
                            
                            if (jRadioButton4.isSelected()){
                                sumatoriaB += kernel[k][l] * matrizImagenB[aux1][aux2];
                            }else{
                                sumatoriaB = 0;
                            }
                            
                            color = new Color(comprobarColor((sumatoriaR/divisorYoffset[0])+divisorYoffset[1]), comprobarColor((sumatoriaG/divisorYoffset[0])+divisorYoffset[1]), comprobarColor((sumatoriaB/divisorYoffset[0])+divisorYoffset[1]));
                            
                            aux2++;
                        }
                        aux1++;
                    }
                    matrizResultante[i][j] = color.getRGB();
             //       System.out.println("Acabe el: ("+i+" ,"+j+") en el metodo i == 0 && j == 1");
                //caso i en borde, j > 1, antes del borde, cualquier tamano
                }else if (i == 0 && j > 1 && j < bi.getWidth()-3){
               //     System.out.println("Entro en: ("+i+" ,"+j+") en el metodo i == 0 && (j > 1 && j < bi.getWidth()-3)");
                    for (int k = elementoCentral; k < kernel.length; k++) {
                        aux2 = j-elementoCentral;
                        for (int l = 0; l < kernel.length; l++) {
                            if (jRadioButton1.isSelected()){
                                sumatoriaR += kernel[k][l] * matrizImagenR[aux1][aux2];
                            }else{
                                sumatoriaR = 0;
                            }
                            
                            if (jRadioButton2.isSelected()) {
                                sumatoriaG += kernel[k][l] * matrizImagenG[aux1][aux2];
                            }else{
                                sumatoriaG = 0;
                            }
                            
                            if (jRadioButton4.isSelected()){
                                sumatoriaB += kernel[k][l] * matrizImagenB[aux1][aux2];
                            }else{
                                sumatoriaB = 0;
                            }
                            
                            color = new Color(comprobarColor((sumatoriaR/divisorYoffset[0])+divisorYoffset[1]), comprobarColor((sumatoriaG/divisorYoffset[0])+divisorYoffset[1]), comprobarColor((sumatoriaB/divisorYoffset[0])+divisorYoffset[1]));
                            
                            aux2++;
                        }
                        aux1++;
                    }
                    matrizResultante[i][j] = color.getRGB();
             //       System.out.println("Acabe el: ("+i+" ,"+j+") en el metodo i == 0 && (j > 1 && j < bi.getWidth()-3)");
                //caso i en borde, j > 1, antes del borde, cualquier tamano
                }else if (i == 0 && j == bi.getWidth()-3){
              //      System.out.println("Entro en: ("+i+" ,"+j+") en el metodo i == 0 && j == bi.getWidth()-3"); 
                    for (int k = elementoCentral; k < kernel.length; k++) {
                        aux2 = j-elementoCentral;
                        for (int l = 0; l < kernel.length; l++) {
                            if (jRadioButton1.isSelected()){
                                sumatoriaR += kernel[k][l] * matrizImagenR[aux1][aux2];
                            }else{
                                sumatoriaR = 0;
                            }
                            
                            if (jRadioButton2.isSelected()) {
                                sumatoriaG += kernel[k][l] * matrizImagenG[aux1][aux2];
                            }else{
                                sumatoriaG = 0;
                            }
                            
                            if (jRadioButton4.isSelected()){
                                sumatoriaB += kernel[k][l] * matrizImagenB[aux1][aux2];
                            }else{
                                sumatoriaB = 0;
                            }
                            
                            color = new Color(comprobarColor((sumatoriaR/divisorYoffset[0])+divisorYoffset[1]), comprobarColor((sumatoriaG/divisorYoffset[0])+divisorYoffset[1]), comprobarColor((sumatoriaB/divisorYoffset[0])+divisorYoffset[1]));
                            
                            aux2++;
                        }
                        aux1++;
                    }
                    matrizResultante[i][j] = color.getRGB();
               //     System.out.println("Acabe el: ("+i+" ,"+j+") en el metodo i == 0 && j == bi.getWidth()-3"); 
                //caso i 0, j en borde, cualquier tamano?
                }else if (i == 0 && j == bi.getWidth() - 1){
                //    System.out.println("Entro en: ("+i+" ,"+j+") entro en i == 0 && j == bi.getWidth() - 1");
                    for (int k = elementoCentral; k < kernel.length; k++) {
                        aux2 = bi.getWidth()-1;
                        for (int l = elementoCentral; l >= 0; l--) {
                            if (jRadioButton1.isSelected()){
                                sumatoriaR += kernel[k][l] * matrizImagenR[aux1][aux2];
                            }else{
                                sumatoriaR = 0;
                            }
                            
                            if (jRadioButton2.isSelected()) {
                                sumatoriaG += kernel[k][l] * matrizImagenG[aux1][aux2];
                            }else{
                                sumatoriaG = 0;
                            }
                            
                            if (jRadioButton4.isSelected()){
                                sumatoriaB += kernel[k][l] * matrizImagenB[aux1][aux2];
                            }else{
                                sumatoriaB = 0;
                            }
                            
                            color = new Color(comprobarColor((sumatoriaR/divisorYoffset[0])+divisorYoffset[1]), comprobarColor((sumatoriaG/divisorYoffset[0])+divisorYoffset[1]), comprobarColor((sumatoriaB/divisorYoffset[0])+divisorYoffset[1]));
                            
                            aux2--;
                        }
                        aux1++;
                    }
                    matrizResultante[i][j] = color.getRGB();
                //    System.out.println("Acabe el: ("+i+" ,"+j+") entro en i == 0 && j == bi.getWidth() - 1");
                //caso i 0, j en borde, tamano 5
                }else if (i == 0 && j == bi.getWidth() - 1 && kernel.length > 3){
                //    System.out.println("Entro en: ("+i+" ,"+j+") entro en i == 0 && j == bi.getWidth() - 1 && matrizKernel.length > 3");
                    for (int k = elementoCentral; k < kernel.length; k++) {
                        aux2 = bi.getWidth()-1;
                        for (int l = kernel.length-1; l >= 0; l--) {
                            if (jRadioButton1.isSelected()){
                                sumatoriaR += kernel[k][l] * matrizImagenR[aux1][aux2];
                            }else{
                                sumatoriaR = 0;
                            }
                            
                            if (jRadioButton2.isSelected()) {
                                sumatoriaG += kernel[k][l] * matrizImagenG[aux1][aux2];
                            }else{
                                sumatoriaG = 0;
                            }
                            
                            if (jRadioButton4.isSelected()){
                                sumatoriaB += kernel[k][l] * matrizImagenB[aux1][aux2];
                            }else{
                                sumatoriaB = 0;
                            }
                            
                            color = new Color(comprobarColor((sumatoriaR/divisorYoffset[0])+divisorYoffset[1]), comprobarColor((sumatoriaG/divisorYoffset[0])+divisorYoffset[1]), comprobarColor((sumatoriaB/divisorYoffset[0])+divisorYoffset[1]));
                            
                            aux2--;
                        }
                        aux1++;
                    }
                    matrizResultante[i][j] = color.getRGB();
               //     System.out.println("Acabe el: ("+i+" ,"+j+") entro en i == 0 && j == bi.getWidth() - 1 && matrizKernel.length > 3");
                //caso i 0, j a 1 del borde, cualquier tamano 
                }else if (i == 0 && j == bi.getWidth() - 2){
                //    System.out.println("Entro en: ("+i+" ,"+j+") entro en i == 0 && j == bi.getWidth() - 2");
                    for (int k = elementoCentral; k < kernel.length; k++) {
                        aux2 = bi.getWidth()-1;
                        for (int l = kernel.length-1; l >= 0; l--) {
                            if (jRadioButton1.isSelected()){
                                sumatoriaR += kernel[k][l] * matrizImagenR[aux1][aux2];
                            }else{
                                sumatoriaR = 0;
                            }
                            
                            if (jRadioButton2.isSelected()) {
                                sumatoriaG += kernel[k][l] * matrizImagenG[aux1][aux2];
                            }else{
                                sumatoriaG = 0;
                            }
                            
                            if (jRadioButton4.isSelected()){
                                sumatoriaB += kernel[k][l] * matrizImagenB[aux1][aux2];
                            }else{
                                sumatoriaB = 0;
                            }
                            
                            color = new Color(comprobarColor((sumatoriaR/divisorYoffset[0])+divisorYoffset[1]), comprobarColor((sumatoriaG/divisorYoffset[0])+divisorYoffset[1]), comprobarColor((sumatoriaB/divisorYoffset[0])+divisorYoffset[1]));
                            
                            aux2--;
                        }
                        aux1++;
                    }
                    matrizResultante[i][j] = color.getRGB();
                //    System.out.println("Acabe el: ("+i+" ,"+j+") entro en i == 0 && j == bi.getWidth() - 2");
                //caso i 1, j 0, cualquier tamano
                }else if(i == 1 && j == 0){
                //     System.out.println("Entro en: ("+i+" ,"+j+") entro en i == 1 && j == 0");
                    for (int k = elementoCentral-1; k < kernel.length; k++) {
                        aux2 = 0;
                        for (int l = elementoCentral; l < kernel.length; l++) {
                            if (jRadioButton1.isSelected()){
                                sumatoriaR += kernel[k][l] * matrizImagenR[aux1][aux2];
                            }else{
                                sumatoriaR = 0;
                            }
                            
                            if (jRadioButton2.isSelected()) {
                                sumatoriaG += kernel[k][l] * matrizImagenG[aux1][aux2];
                            }else{
                                sumatoriaG = 0;
                            }
                            
                            if (jRadioButton4.isSelected()){
                                sumatoriaB += kernel[k][l] * matrizImagenB[aux1][aux2];
                            }else{
                                sumatoriaB = 0;
                            }
                            
                            color = new Color(comprobarColor((sumatoriaR/divisorYoffset[0])+divisorYoffset[1]), comprobarColor((sumatoriaG/divisorYoffset[0])+divisorYoffset[1]), comprobarColor((sumatoriaB/divisorYoffset[0])+divisorYoffset[1]));
                            
                            
                            aux2++;
                        }
                        aux1++;
                    }
                    matrizResultante[i][j] = color.getRGB();
              //      System.out.println("Acabe el: ("+i+" ,"+j+") entro en i == 1 && j == 0");
                //caso i > 1, a 2 del borde, j 0, cualquier tamano
                }else if((i < bi.getHeight()-2 && i > 1) && j == 0){
                    aux1 = bi.getHeight()-3;
                //   System.out.println("Entro en: ("+i+" ,"+j+") entro en i < bi.getHeight()-2 && i > 1) && j == 0");
                    for (int k = 0; k < kernel.length; k++) {
                        aux2 = 0;
                        for (int l = elementoCentral; l < kernel.length; l++) {
                                if (jRadioButton1.isSelected()){
                                    sumatoriaR += kernel[k][l] * matrizImagenR[aux1][aux2];
                                }else{
                                    sumatoriaR = 0;
                                }

                                if (jRadioButton2.isSelected()) {
                                    sumatoriaG += kernel[k][l] * matrizImagenG[aux1][aux2];
                                }else{
                                    sumatoriaG = 0;
                                }

                                if (jRadioButton4.isSelected()){
                                    sumatoriaB += kernel[k][l] * matrizImagenB[aux1][aux2];
                                }else{
                                    sumatoriaB = 0;
                                }
                                color = new Color(comprobarColor((sumatoriaR/divisorYoffset[0])+divisorYoffset[1]), comprobarColor((sumatoriaG/divisorYoffset[0])+divisorYoffset[1]), comprobarColor((sumatoriaB/divisorYoffset[0])+divisorYoffset[1]));   
                                
                                arregloAux[contadorAux] = matrizImagenB[aux1][aux2];
                                contadorAux++;
                            aux2++;
                        }
                        aux1++;
                    }
                        matrizResultante[i][j] = color.getRGB();
                 //   System.out.println("Acabe el: ("+i+" ,"+j+") entro en i < bi.getHeight()-2 && i > 1) && j == 0");
                //caso i > 1, en borde, cualquier tamano
                }else if((i < bi.getHeight()-1 && i > 1) && j == 0){
                    aux2 = 0;
              //  System.out.println("Entro en: ("+i+" ,"+j+") entro en (i < bi.getHeight()-1 && i > 1) && j == 0");
                    for (int k = elementoCentral; k <= 0; k--) {
                        aux1 = bi.getHeight()-1;
                        
                        for (int l = elementoCentral; l < kernel.length; l++) {
                            if (jRadioButton1.isSelected()){
                                sumatoriaR += kernel[k][l] * matrizImagenR[aux1][aux2];
                            }else{
                                sumatoriaR = 0;
                            }
                            
                            if (jRadioButton2.isSelected()) {
                                sumatoriaG += kernel[k][l] * matrizImagenG[aux1][aux2];
                            }else{
                                sumatoriaG = 0;
                            }
                            
                            if (jRadioButton4.isSelected()){
                                sumatoriaB += kernel[k][l] * matrizImagenB[aux1][aux2];
                            }else{
                                sumatoriaB = 0;
                            }
                            
                            color = new Color(comprobarColor((sumatoriaR/divisorYoffset[0])+divisorYoffset[1]), comprobarColor((sumatoriaG/divisorYoffset[0])+divisorYoffset[1]), comprobarColor((sumatoriaB/divisorYoffset[0])+divisorYoffset[1]));
                            
                            aux2++;
                        }
                        aux1--;
                    }
                    matrizResultante[i][j] = color.getRGB();
                  //  System.out.println("Acabe el: ("+i+" ,"+j+") entro en (i < bi.getHeight()-1 && i > 1) && j == 0");
                //caso i 1, j en borde, cualquier tamano
                }else if (i == bi.getHeight()-1 && j == 1){
                   //  System.out.println("Entro en: ("+i+" ,"+j+") entro en i == bi.getHeight()-1 && j == 1");
                    aux1 = bi.getHeight()-1;
                    for (int k = elementoCentral; k <= 0; k--) {
                        aux2 = 0;
                        for (int l = elementoCentral-1; l < kernel.length; l++) {
                            if (jRadioButton1.isSelected()){
                                sumatoriaR += kernel[k][l] * matrizImagenR[aux1][aux2];
                            }else{
                                sumatoriaR = 0;
                            }
                            
                            if (jRadioButton2.isSelected()) {
                                sumatoriaG += kernel[k][l] * matrizImagenG[aux1][aux2];
                            }else{
                                sumatoriaG = 0;
                            }
                            
                            if (jRadioButton4.isSelected()){
                                sumatoriaB += kernel[k][l] * matrizImagenB[aux1][aux2];
                            }else{
                                sumatoriaB = 0;
                            }
                            
                            color = new Color(comprobarColor((sumatoriaR/divisorYoffset[0])+divisorYoffset[1]), comprobarColor((sumatoriaG/divisorYoffset[0])+divisorYoffset[1]), comprobarColor((sumatoriaB/divisorYoffset[0])+divisorYoffset[1]));
                            
                            aux2++;
                        }
                        aux1--;
                    }
                    matrizResultante[i][j] = color.getRGB();
                   // System.out.println("Acabe el: ("+i+" ,"+j+") entro en i == bi.getHeight()-1 && j == 1");
                //caso i > 1, j en borde, cualquier tamano
                }else if (i == bi.getHeight()-1 && j == 0){
                    aux1 = bi.getHeight()-1;
                    for (int k = elementoCentral; k <= 0; k--) {
                        aux2 = 0;
                        for (int l = elementoCentral; l < kernel.length; l++) {
                            if (jRadioButton1.isSelected()){
                                sumatoriaR += kernel[k][l] * matrizImagenR[aux1][aux2];
                            }else{
                                sumatoriaR = 0;
                            }
                            
                            if (jRadioButton2.isSelected()) {
                                sumatoriaG += kernel[k][l] * matrizImagenG[aux1][aux2];
                            }else{
                                sumatoriaG = 0;
                            }
                            
                            if (jRadioButton4.isSelected()){
                                sumatoriaB += kernel[k][l] * matrizImagenB[aux1][aux2];
                            }else{
                                sumatoriaB = 0;
                            }
                            
                            color = new Color(comprobarColor((sumatoriaR/divisorYoffset[0])+divisorYoffset[1]), comprobarColor((sumatoriaG/divisorYoffset[0])+divisorYoffset[1]), comprobarColor((sumatoriaB/divisorYoffset[0])+divisorYoffset[1]));
                            
                            aux2++;
                        }
                        aux1--;
                    }
                    matrizResultante[i][j] = color.getRGB();
                //caso i > 1, j en borde, cualquier tamano
                }
                else if (i == bi.getHeight()-1 && j > 1 &&  j< bi.getWidth()-3){
                    aux1 = bi.getHeight()-1;
                    //System.out.println("Entro en: ("+i+" ,"+j+") entro en i == bi.getHeight()-1 && j > 1 &&  j< bi.getWidth()-3");
                    for (int k = elementoCentral; k <= 0; k--) {
                        
                        aux2 = j-elementoCentral;
                        for (int l = elementoCentral-1; l < kernel.length; l++) {
                            if (jRadioButton1.isSelected()){
                                sumatoriaR += kernel[k][l] * matrizImagenR[aux1][aux2];
                            }else{
                                sumatoriaR = 0;
                            }
                            
                            if (jRadioButton2.isSelected()) {
                                sumatoriaG += kernel[k][l] * matrizImagenG[aux1][aux2];
                            }else{
                                sumatoriaG = 0;
                            }
                            
                            if (jRadioButton4.isSelected()){
                                sumatoriaB += kernel[k][l] * matrizImagenB[aux1][aux2];
                            }else{
                                sumatoriaB = 0;
                            }
                            
                            color = new Color(comprobarColor((sumatoriaR/divisorYoffset[0])+divisorYoffset[1]), comprobarColor((sumatoriaG/divisorYoffset[0])+divisorYoffset[1]), comprobarColor((sumatoriaB/divisorYoffset[0])+divisorYoffset[1]));
                            
                            aux2++;
                        }
                        aux1--;
                    }
                    matrizResultante[i][j] = color.getRGB();
                   // System.out.println("Acabe el: ("+i+" ,"+j+") entro en i == bi.getHeight()-1 && j > 1 &&  j< bi.getWidth()-3");
                //caso i a 2 del borde, j en borde, cualquier tamano
                }
                else if (i == bi.getHeight()-1 && j > 1 &&  j == bi.getWidth()-3){
                    aux1 = bi.getHeight()-1;
                    for (int k = elementoCentral; k <= 0; k--) {
                        System.out.println("k: "+k);
                        aux2 = j-elementoCentral;
                        for (int l = elementoCentral-1; l < kernel.length; l++) {
                            if (jRadioButton1.isSelected()){
                                sumatoriaR += kernel[k][l] * matrizImagenR[aux1][aux2];
                            }else{
                                sumatoriaR = 0;
                            }
                            
                            if (jRadioButton2.isSelected()) {
                                sumatoriaG += kernel[k][l] * matrizImagenG[aux1][aux2];
                            }else{
                                sumatoriaG = 0;
                            }
                            
                            if (jRadioButton4.isSelected()){
                                sumatoriaB += kernel[k][l] * matrizImagenB[aux1][aux2];
                            }else{
                                sumatoriaB = 0;
                            }
                            
                            color = new Color(comprobarColor((sumatoriaR/divisorYoffset[0])+divisorYoffset[1]), comprobarColor((sumatoriaG/divisorYoffset[0])+divisorYoffset[1]), comprobarColor((sumatoriaB/divisorYoffset[0])+divisorYoffset[1]));
                            
                            aux2++;
                        }
                        aux1--;
                    }
                    matrizResultante[i][j] = color.getRGB();
                   // System.out.println("Acabe el: ("+i+" ,"+j+") entro en i == bi.getHeight()-1 && j > 1 &&  j< bi.getWidth()-3");
                //caso i a 2 del borde, j en borde, cualquier tamano
                }
                else if (i == bi.getHeight()-1 && j == bi.getWidth()-2){
                    aux1 = bi.getHeight()-1;
                  //  System.out.println("Entro en: ("+i+" ,"+j+") entro en i == bi.getHeight()-1 && j == bi.getWidth()-2");
                    for (int k = elementoCentral; k <= 0; k--) {
                        
                        aux2 = j-elementoCentral;
                        for (int l = elementoCentral-1; l < kernel.length; l++) {
                            if (jRadioButton1.isSelected()){
                                sumatoriaR += kernel[k][l] * matrizImagenR[aux1][aux2];
                            }else{
                                sumatoriaR = 0;
                            }
                            
                            if (jRadioButton2.isSelected()) {
                                sumatoriaG += kernel[k][l] * matrizImagenG[aux1][aux2];
                            }else{
                                sumatoriaG = 0;
                            }
                            
                            if (jRadioButton4.isSelected()){
                                sumatoriaB += kernel[k][l] * matrizImagenB[aux1][aux2];
                            }else{
                                sumatoriaB = 0;
                            }
                            
                            color = new Color(comprobarColor((sumatoriaR/divisorYoffset[0])+divisorYoffset[1]), comprobarColor((sumatoriaG/divisorYoffset[0])+divisorYoffset[1]), comprobarColor((sumatoriaB/divisorYoffset[0])+divisorYoffset[1]));
                            
                            aux2++;
                        }
                        aux1--;
                    }
                    matrizResultante[i][j] = color.getRGB();
                   // System.out.println("Acabe el: ("+i+" ,"+j+") entro en i == bi.getHeight()-1 && j == bi.getWidth()-2");
                //caso i en borde, j en borde, cualquier tamano
                }else if (i == bi.getHeight()-1 && j == bi.getWidth()-1){
                    aux1 = bi.getHeight()-1;
                   // System.out.println("Entro en: ("+i+" ,"+j+") entro en i == bi.getHeight()-1 && j == bi.getWidth()-1");
                    for (int k = elementoCentral; k <= 0; k--) {
                        
                        aux2 = bi.getHeight()-1;
                        for (int l = elementoCentral; l <= 0; l--) {
                            if (jRadioButton1.isSelected()){
                                sumatoriaR += kernel[k][l] * matrizImagenR[aux1][aux2];
                            }else{
                                sumatoriaR = 0;
                            }
                            
                            if (jRadioButton2.isSelected()) {
                                sumatoriaG += kernel[k][l] * matrizImagenG[aux1][aux2];
                            }else{
                                sumatoriaG = 0;
                            }
                            
                            if (jRadioButton4.isSelected()){
                                sumatoriaB += kernel[k][l] * matrizImagenB[aux1][aux2];
                            }else{
                                sumatoriaB = 0;
                            }
                            
                            color = new Color(comprobarColor((sumatoriaR/divisorYoffset[0])+divisorYoffset[1]), comprobarColor((sumatoriaG/divisorYoffset[0])+divisorYoffset[1]), comprobarColor((sumatoriaB/divisorYoffset[0])+divisorYoffset[1]));
                            
                            aux2--;
                        }
                        aux1--;
                    }
                    matrizResultante[i][j] = color.getRGB();
                  //  System.out.println("Acabe el: ("+i+" ,"+j+") entro en i == bi.getHeight()-1 && j == bi.getWidth()-1");
                }else if (i == bi.getHeight()-2 && j == bi.getWidth()-1){
                    aux2 = bi.getWidth()-1;
                  //  System.out.println("Entro en: ("+i+" ,"+j+") entro en i == bi.getHeight()-2 && j == bi.getWidth()-1");
                    for (int k = elementoCentral; k <= 0; k--) {
                        
                        aux1 = bi.getHeight()-1;
                        for (int l = elementoCentral-1; l < kernel.length; l++) {
                            if (jRadioButton1.isSelected()){
                                sumatoriaR += kernel[k][l] * matrizImagenR[aux1][aux2];
                            }else{
                                sumatoriaR = 0;
                            }
                            
                            if (jRadioButton2.isSelected()) {
                                sumatoriaG += kernel[k][l] * matrizImagenG[aux1][aux2];
                            }else{
                                sumatoriaG = 0;
                            }
                            
                            if (jRadioButton4.isSelected()){
                                sumatoriaB += kernel[k][l] * matrizImagenB[aux1][aux2];
                            }else{
                                sumatoriaB = 0;
                            }
                            
                            color = new Color(comprobarColor((sumatoriaR/divisorYoffset[0])+divisorYoffset[1]), comprobarColor((sumatoriaG/divisorYoffset[0])+divisorYoffset[1]), comprobarColor((sumatoriaB/divisorYoffset[0])+divisorYoffset[1]));
                            
                            aux1--;
                        }
                        aux2--;
                    }
                    matrizResultante[i][j] = color.getRGB();
                   // System.out.println("Acabe el: ("+i+" ,"+j+") entro en i == bi.getHeight()-2 && j == bi.getWidth()-1");
                }else if (i == 1 && j == bi.getWidth()-1){
                    aux2 = bi.getWidth()-1;
                  //  System.out.println("Entro en: ("+i+" ,"+j+") entro en i == 1 && j == bi.getWidth()-1");
                    for (int k = elementoCentral; k <= 0; k--) {
                        
                        aux1 = kernel.length-1;
                        for (int l = elementoCentral-1; l < kernel.length; l++) {
                           if (jRadioButton1.isSelected()){
                                sumatoriaR += kernel[k][l] * matrizImagenR[aux1][aux2];
                            }else{
                                sumatoriaR = 0;
                            }
                            
                            if (jRadioButton2.isSelected()) {
                                sumatoriaG += kernel[k][l] * matrizImagenG[aux1][aux2];
                            }else{
                                sumatoriaG = 0;
                            }
                            
                            if (jRadioButton4.isSelected()){
                                sumatoriaB += kernel[k][l] * matrizImagenB[aux1][aux2];
                            }else{
                                sumatoriaB = 0;
                            }
                            
                            color = new Color(comprobarColor((sumatoriaR/divisorYoffset[0])+divisorYoffset[1]), comprobarColor((sumatoriaG/divisorYoffset[0])+divisorYoffset[1]), comprobarColor((sumatoriaB/divisorYoffset[0])+divisorYoffset[1]));
                            
                            aux1--;
                        }
                        aux2--;
                    }
                    matrizResultante[i][j] = color.getRGB();
                  //  System.out.println("Acabe el: ("+i+" ,"+j+") entro en i == 1 && j == bi.getWidth()-1");
                }else if (i > 1 && j == bi.getWidth()-1){
                    aux2 = bi.getWidth()-1;
                   // System.out.println("Entro en: ("+i+" ,"+j+") entro en i == 1 && j == bi.getWidth()-1");
                    for (int k = elementoCentral; k <= 0; k--) {
                        
                        aux1 = elementoCentral-1;
                        for (int l = elementoCentral-1; l < kernel.length; l++) {
                            if (jRadioButton1.isSelected()){
                                sumatoriaR += kernel[k][l] * matrizImagenR[aux1][aux2];
                            }else{
                                sumatoriaR = 0;
                            }
                            
                            if (jRadioButton2.isSelected()) {
                                sumatoriaG += kernel[k][l] * matrizImagenG[aux1][aux2];
                            }else{
                                sumatoriaG = 0;
                            }
                            
                            if (jRadioButton4.isSelected()){
                                sumatoriaB += kernel[k][l] * matrizImagenB[aux1][aux2];
                            }else{
                                sumatoriaB = 0;
                            }
                            
                            color = new Color(comprobarColor((sumatoriaR/divisorYoffset[0])+divisorYoffset[1]), comprobarColor((sumatoriaG/divisorYoffset[0])+divisorYoffset[1]), comprobarColor((sumatoriaB/divisorYoffset[0])+divisorYoffset[1]));
                            
                            aux1--;
                        }
                        aux2--;
                    }
                    matrizResultante[i][j] = color.getRGB();
                    //System.out.println("Acabe el: ("+i+" ,"+j+") entro en i == 1 && j == bi.getWidth()-1");
                }else{
                    aux1 = i - elementoCentral;
                                   
                  //  System.out.println("Entro en: ("+i+" ,"+j+") entro en el metodo general");
                    for (int k = 0; k < kernel.length; k++) {
                        //System.out.println("K: "+k);
                        aux2 = j - elementoCentral;
                        for (int l = 0; l < kernel.length; l++) {
                            //System.out.println("L: "+l+" aux1: "+aux1+" aux2: "+aux2);
                            if (jRadioButton1.isSelected()){
                                sumatoriaR += kernel[k][l] * matrizImagenR[aux1][aux2];
                            }else{
                                sumatoriaR = 0;
                            }
                            
                            if (jRadioButton2.isSelected()) {
                                sumatoriaG += kernel[k][l] * matrizImagenG[aux1][aux2];
                            }else{
                                sumatoriaG = 0;
                            }
                            
                            if (jRadioButton4.isSelected()){
                                sumatoriaB += kernel[k][l] * matrizImagenB[aux1][aux2];
                            }else{
                                sumatoriaB = 0;
                            }
                            
                            color = new Color(comprobarColor((sumatoriaR/divisorYoffset[0])+divisorYoffset[1]), comprobarColor((sumatoriaG/divisorYoffset[0])+divisorYoffset[1]), comprobarColor((sumatoriaB/divisorYoffset[0])+divisorYoffset[1]));
                            
                            
                            aux2++;
                        }
                        aux1++;
                    }
                    matrizResultante[i][j] = color.getRGB();
                   // System.out.println("Acabe el: ("+i+" ,"+j+") entro en el metodo general");
                }
            }
        }
        for (int i = 0; i < bi.getHeight(); i++) {
            for (int j = 0; j < bi.getWidth(); j++) {
                bi.setRGB(j, i, matrizResultante[i][j]);
            }
        }
        
        
        return bi;
    }
    
    public BufferedImage medianFilter (Image imagen){
        BufferedImage bi = herramientas.HerramientasImagen.toBufferedImage(imagen);
        int[][] matrizResultante = new int[bi.getHeight()][bi.getWidth()];
        int sumatoriaR, sumatoriaG, sumatoriaB = 0;
        int[][] matrizImagenR = new int[bi.getHeight()][bi.getWidth()];
        int[][] matrizImagenG = new int[bi.getHeight()][bi.getWidth()];
        int[][] matrizImagenB = new int[bi.getHeight()][bi.getWidth()];
        
        System.out.println("median");
        
        for (int i = 0; i < bi.getHeight(); i++) {
            for (int j = 0; j < bi.getWidth(); j++) {
                Color colorAux;
                
                colorAux = new Color (bi.getRGB(j, i));
                
                matrizImagenR[i][j] = colorAux.getRed();
                matrizImagenG[i][j] = colorAux.getGreen();
                matrizImagenB[i][j] = colorAux.getBlue();
            }
        }
        
        for (int i = 0; i < bi.getHeight(); i++) {
            for (int j = 0; j < bi.getWidth(); j++) {
                int aux1 = 0, aux2 = 0;
                sumatoriaR = 0; 
                sumatoriaG = 0;
                sumatoriaB = 0;
                Color color = new Color(112);
                
                int[] arregloAux = new int[9];
                int contadorAux = 0;
                
                System.out.println();
                
                if (i == 0 && j == 0){
                    for (int k = elementoCentral; k < 3; k++) {
                        aux2 = 0;
                        for (int l = elementoCentral; l < 3; l++) {
                            arregloAux[contadorAux] = matrizImagenB[aux1][aux2];
                            contadorAux++;

                            aux2++;
                        }
                        aux1++;
                    }
                    color = filtromedio(arregloAux);
                    matrizResultante[i][j] = color.getRGB();
                }else if (i == 0 && j == 1){
                    contadorAux = 0;
                    for (int k = elementoCentral; k < 3; k++) {
                        aux2 = 0;
                        for (int l = elementoCentral-1; l < 3; l++) {
                            System.out.println();
                            arregloAux[contadorAux] = matrizImagenB[aux1][aux2];
                            contadorAux++;
                            
                            aux2++;
                        }
                        aux1++;
                    }
                    color = filtromedio(arregloAux);
                    matrizResultante[i][j] = color.getRGB();
                }else if (i == 0 && j > 1 && j < bi.getWidth()-3){
                    for (int k = elementoCentral; k < 3; k++) {
                        aux2 = j-elementoCentral;
                        for (int l = 0; l < 3; l++) {
                            arregloAux[contadorAux] = matrizImagenB[aux1][aux2];
                            contadorAux++;
                            
                            aux2++;
                        }
                        aux1++;
                    }
                    color = filtromedio(arregloAux);
                    matrizResultante[i][j] = color.getRGB();
                }else if (i == 0 && j == bi.getWidth()-3){
                    for (int k = elementoCentral; k < 3; k++) {
                        aux2 = j-elementoCentral;
                        for (int l = 0; l < 3; l++) {
                            arregloAux[contadorAux] = matrizImagenB[aux1][aux2];
                            contadorAux++;
                            
                            aux2++;
                        }
                        aux1++;
                    }
                    color = filtromedio(arregloAux);
                    matrizResultante[i][j] = color.getRGB();
                }else if (i == 0 && j == bi.getWidth() - 1){
                    for (int k = elementoCentral; k < 3; k++) {
                        aux2 = bi.getWidth()-1;
                        for (int l = elementoCentral; l >= 0; l--) {
                            arregloAux[contadorAux] = matrizImagenB[aux1][aux2];
                            contadorAux++;
                            
                            aux2--;
                        }
                        aux1++;
                    }
                    color = filtromedio(arregloAux);
                    matrizResultante[i][j] = color.getRGB();
                }else if (i == 0 && j == bi.getWidth() - 1){
                    for (int k = elementoCentral; k < 3; k++) {
                        aux2 = bi.getWidth()-1;
                        for (int l = 3; l >= 0; l--) {
                            arregloAux[contadorAux] = matrizImagenB[aux1][aux2];
                            contadorAux++;
                            
                            aux2--;
                        }
                        aux1++;
                    }
                    color = filtromedio(arregloAux);
                    matrizResultante[i][j] = color.getRGB();
                }else if (i == 0 && j == bi.getWidth() - 2){
                    for (int k = elementoCentral; k < 3; k++) {
                        aux2 = bi.getWidth()-1;
                        for (int l = 2; l >= 0; l--) {
                            arregloAux[contadorAux] = matrizImagenB[aux1][aux2];
                            contadorAux++;
                            
                            aux2--;
                        }
                        aux1++;
                    }
                    color = filtromedio(arregloAux);
                    matrizResultante[i][j] = color.getRGB();
                }else if(i == 1 && j == 0){
                    for (int k = elementoCentral-1; k < 3; k++) {
                        aux2 = 0;
                        for (int l = elementoCentral; l < 3; l++) {
                            arregloAux[contadorAux] = matrizImagenB[aux1][aux2];
                            contadorAux++;
                            
                            aux2++;
                        }
                        aux1++;
                    }
                    color = filtromedio(arregloAux);
                    matrizResultante[i][j] = color.getRGB();
                }else if((i < bi.getHeight()-2 && i > 1) && j == 0){
                    aux1 = bi.getHeight()-3;
                    for (int k = 0; k < 3; k++) {
                        aux2 = 0;
                        for (int l = elementoCentral; l < 3; l++) { 
                            arregloAux[contadorAux] = matrizImagenB[aux1][aux2];
                            contadorAux++;

                            aux2++;
                        }
                        aux1++;
                    }
                    color = filtromedio(arregloAux);
                    matrizResultante[i][j] = color.getRGB();
                }else if((i < bi.getHeight()-1 && i > 1) && j == 0){
                    aux2 = 0;
                    for (int k = elementoCentral; k <= 0; k--) {
                        aux1 = bi.getHeight()-1;
                        for (int l = elementoCentral; l < 3; l++) {
                            arregloAux[contadorAux] = matrizImagenB[aux1][aux2];
                            contadorAux++;
                            
                            aux2++;
                        }
                        aux1--;
                    }
                    color = filtromedio(arregloAux);
                    matrizResultante[i][j] = color.getRGB();
                }else if (i == bi.getHeight()-1 && j == 1){
                    aux1 = bi.getHeight()-1;
                    for (int k = elementoCentral; k <= 0; k--) {
                        aux2 = 0;
                        for (int l = elementoCentral-1; l < 3; l++) {
                            arregloAux[contadorAux] = matrizImagenB[aux1][aux2];
                            contadorAux++;
                            
                            aux2++;
                        }
                        aux1--;
                    }
                    color = filtromedio(arregloAux);
                    matrizResultante[i][j] = color.getRGB();
                }else if (i == bi.getHeight()-1 && j == 0){
                    aux1 = bi.getHeight()-1;
                    for (int k = elementoCentral; k <= 0; k--) {
                        aux2 = 0;
                        for (int l = elementoCentral; l < 3; l++) {
                            arregloAux[contadorAux] = matrizImagenB[aux1][aux2];
                            contadorAux++;
                            
                            aux2++;
                        }
                        aux1--;
                    }
                    color = filtromedio(arregloAux);
                    matrizResultante[i][j] = color.getRGB();
                }else if (i == bi.getHeight()-1 && j > 1 &&  j< bi.getWidth()-3){
                    aux1 = bi.getHeight()-1;
                    for (int k = elementoCentral; k <= 0; k--) {
                        aux2 = j-elementoCentral;
                        for (int l = elementoCentral-1; l < 3; l++) {
                            arregloAux[contadorAux] = matrizImagenB[aux1][aux2];
                            contadorAux++;
                            
                            aux2++;
                        }
                        aux1--;
                    }
                    color = filtromedio(arregloAux);
                    matrizResultante[i][j] = color.getRGB();
                }else if (i == bi.getHeight()-1 && j > 1 &&  j == bi.getWidth()-3){
                    aux1 = bi.getHeight()-1;
                    for (int k = elementoCentral; k <= 0; k--) {
                        aux2 = j-elementoCentral;
                        for (int l = elementoCentral-1; l < 3; l++) {
                            arregloAux[contadorAux] = matrizImagenB[aux1][aux2];
                            contadorAux++;
                            
                            aux2++;
                        }
                        aux1--;
                    }
                    color = filtromedio(arregloAux);
                    matrizResultante[i][j] = color.getRGB();
                }else if (i == bi.getHeight()-1 && j == bi.getWidth()-2){
                    aux1 = bi.getHeight()-1;
                    for (int k = elementoCentral; k <= 0; k--) {
                        aux2 = j-elementoCentral;
                        for (int l = elementoCentral-1; l < 3; l++) {
                            arregloAux[contadorAux] = matrizImagenB[aux1][aux2];
                            contadorAux++;
                            
                            aux2++;
                        }
                        aux1--;
                    }
                    color = filtromedio(arregloAux);
                    matrizResultante[i][j] = color.getRGB();
                }else if (i == bi.getHeight()-1 && j == bi.getWidth()-1){
                    aux1 = bi.getHeight()-1;
                    for (int k = elementoCentral; k <= 0; k--) {
                        aux2 = bi.getHeight()-1;
                        for (int l = elementoCentral; l <= 0; l--) {
                            arregloAux[contadorAux] = matrizImagenB[aux1][aux2];
                            contadorAux++;
                            
                            aux2--;
                        }
                        aux1--;
                    }
                    color = filtromedio(arregloAux);
                    matrizResultante[i][j] = color.getRGB();
                }else if (i == bi.getHeight()-2 && j == bi.getWidth()-1){
                    aux2 = bi.getWidth()-1;
                    for (int k = elementoCentral; k <= 0; k--) {
                        aux1 = bi.getHeight()-1;
                        for (int l = elementoCentral-1; l < 3; l++) {
                            arregloAux[contadorAux] = matrizImagenB[aux1][aux2];
                            contadorAux++;
                            
                            aux1--;
                        }
                        aux2--;
                    }
                    color = filtromedio(arregloAux);
                    matrizResultante[i][j] = color.getRGB();
                }else if (i == 1 && j == bi.getWidth()-1){
                    aux2 = bi.getWidth()-1;
                    for (int k = elementoCentral; k <= 0; k--) {
                        aux1 = 2;
                        for (int l = elementoCentral-1; l < 3; l++) {
                            arregloAux[contadorAux] = matrizImagenB[aux1][aux2];
                            contadorAux++;
                            
                            aux1--;
                        }
                        aux2--;
                    }
                    color = filtromedio(arregloAux);
                    matrizResultante[i][j] = color.getRGB();
                }else if (i > 1 && j == bi.getWidth()-1){
                    aux2 = bi.getWidth()-1;
                    for (int k = elementoCentral; k <= 0; k--) {
                        aux1 = elementoCentral-1;
                        for (int l = elementoCentral-1; l < 3; l++) {
                            arregloAux[contadorAux] = matrizImagenB[aux1][aux2];
                            contadorAux++;
                            
                            aux1--;
                        }
                        aux2--;
                    }
                    color = filtromedio(arregloAux);
                    matrizResultante[i][j] = color.getRGB();
                }else{
                    aux1 = i - elementoCentral;
                    for (int k = 0; k < 3; k++) {
                        aux2 = j - elementoCentral;
                        for (int l = 0; l < 3; l++) {
                            arregloAux[contadorAux] = matrizImagenB[aux1][aux2];
                            contadorAux++;
                            
                            aux2++;
                        }
                        aux1++;
                    }
                    color = filtromedio(arregloAux);
                    matrizResultante[i][j] = color.getRGB();
                }
            }
        }
        for (int i = 0; i < bi.getHeight(); i++) {
            for (int j = 0; j < bi.getWidth(); j++) {
                bi.setRGB(j, i, matrizResultante[i][j]);
            }
        }
           
        return bi;
    }
    
    private static int comprobarColor(int color){
        if (color > 255){
            color = 255;
        }else if(color < 0){
            color = 0;
        }
        
        return color;
    }

    private static Color filtromedio(int[] arreglo){
        Color color;
        
        arreglo = herramientas.Burbuja.burbuja(arreglo);
        
        color = new Color(arreglo[4]);
        
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

        jRadioButton3 = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        jTextField22 = new javax.swing.JTextField();
        jTextField23 = new javax.swing.JTextField();
        jTextField24 = new javax.swing.JTextField();
        jTextField25 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField26 = new javax.swing.JTextField();
        jTextField27 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();

        jRadioButton3.setText("Azul");

        setClosable(true);
        setResizable(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Matriz");

        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("0");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText("0");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setText("0");
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField4.setText("0");
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField5.setText("0");
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jTextField6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField6.setText("0");
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jTextField7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField7.setText("0");
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        jTextField8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField8.setText("0");
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });

        jTextField9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField9.setText("0");
        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });

        jTextField10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField10.setText("0");
        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });

        jTextField11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField11.setText("0");
        jTextField11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField11ActionPerformed(evt);
            }
        });

        jTextField12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField12.setText("0");
        jTextField12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField12ActionPerformed(evt);
            }
        });

        jTextField13.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField13.setText("0");
        jTextField13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField13ActionPerformed(evt);
            }
        });

        jTextField14.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField14.setText("0");
        jTextField14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField14ActionPerformed(evt);
            }
        });

        jTextField15.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField15.setText("0");
        jTextField15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField15ActionPerformed(evt);
            }
        });

        jTextField16.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField16.setText("0");
        jTextField16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField16ActionPerformed(evt);
            }
        });

        jTextField17.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField17.setText("0");
        jTextField17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField17ActionPerformed(evt);
            }
        });

        jTextField18.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField18.setText("0");
        jTextField18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField18ActionPerformed(evt);
            }
        });

        jTextField19.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField19.setText("0");
        jTextField19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField19ActionPerformed(evt);
            }
        });

        jTextField20.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField20.setText("0");
        jTextField20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField20ActionPerformed(evt);
            }
        });

        jTextField21.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField21.setText("1");
        jTextField21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField21ActionPerformed(evt);
            }
        });

        jTextField22.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField22.setText("0");
        jTextField22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField22ActionPerformed(evt);
            }
        });

        jTextField23.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField23.setText("0");
        jTextField23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField23ActionPerformed(evt);
            }
        });

        jTextField24.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField24.setText("0");
        jTextField24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField24ActionPerformed(evt);
            }
        });

        jTextField25.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField25.setText("0");
        jTextField25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField25ActionPerformed(evt);
            }
        });

        jLabel2.setText("Divisor");

        jTextField26.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField26.setText("0");
        jTextField26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField26ActionPerformed(evt);
            }
        });

        jTextField27.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField27.setText("0");
        jTextField27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField27ActionPerformed(evt);
            }
        });

        jLabel3.setText("Offset");

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Rojo");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setSelected(true);
        jRadioButton2.setText("Verde");

        jRadioButton4.setSelected(true);
        jRadioButton4.setText("Azul");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Colores");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Filtros");

        jButton2.setText("Aplicar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jRadioButton5.setText("Kirsch");
        jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton5ActionPerformed(evt);
            }
        });

        jRadioButton6.setText("Mediano");
        jRadioButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton4)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jButton1)
                                    .addComponent(jRadioButton1)
                                    .addComponent(jRadioButton2))
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButton6)
                                    .addComponent(jRadioButton5)
                                    .addComponent(jButton2)
                                    .addComponent(jLabel5))))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10ActionPerformed

    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField11ActionPerformed

    private void jTextField12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField12ActionPerformed

    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13ActionPerformed

    private void jTextField14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField14ActionPerformed

    private void jTextField15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField15ActionPerformed

    private void jTextField16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField16ActionPerformed

    private void jTextField17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField17ActionPerformed

    private void jTextField18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField18ActionPerformed

    private void jTextField19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField19ActionPerformed

    private void jTextField20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField20ActionPerformed

    private void jTextField21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField21ActionPerformed

    private void jTextField22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField22ActionPerformed

    private void jTextField23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField23ActionPerformed

    private void jTextField24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField24ActionPerformed

    private void jTextField25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField25ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField25ActionPerformed

    private void jTextField26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField26ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField26ActionPerformed

    private void jTextField27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField27ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton5ActionPerformed

    private void jRadioButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
