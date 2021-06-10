/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package herramientas;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author Cri
 */
public class Convolucion {
    double[][] matrizK;
    double divisor;
    private Image imagenOriginal;
    int elementoCentral = 1;

    public Convolucion(double[][] matrizK, double divisor, Image imagenOriginal) {
        this.matrizK = matrizK;
        this.divisor = divisor;
        this.imagenOriginal = imagenOriginal;
    }
    
    public BufferedImage modificarImagen(Image imagen, double[][] kernel){
        BufferedImage bi = herramientas.HerramientasImagen.toBufferedImage(imagen);
        double[][] matrizResultante = new double[bi.getHeight()][bi.getWidth()];
        double sumatoriaR, sumatoriaG, sumatoriaB = 0;
        double[][] matrizImagenR = new double[bi.getHeight()][bi.getWidth()];
        double[][] matrizImagenG = new double[bi.getHeight()][bi.getWidth()];
        double[][] matrizImagenB = new double[bi.getHeight()][bi.getWidth()];
        
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
                            sumatoriaR += kernel[k][l] * matrizImagenR[aux1][aux2];
                            sumatoriaG += kernel[k][l] * matrizImagenG[aux1][aux2];
                            sumatoriaB += kernel[k][l] * matrizImagenB[aux1][aux2];

                            color = new Color(comprobarColor((int) (sumatoriaR/divisor)), comprobarColor((int) (sumatoriaG/divisor)), comprobarColor((int) (sumatoriaB/divisor)));
                            
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
                            sumatoriaR += kernel[k][l] * matrizImagenR[aux1][aux2];
                            sumatoriaG += kernel[k][l] * matrizImagenG[aux1][aux2];
                            sumatoriaB += kernel[k][l] * matrizImagenB[aux1][aux2];
                            
                            color = new Color(comprobarColor((int) (sumatoriaR/divisor)), comprobarColor((int) (sumatoriaG/divisor)), comprobarColor((int) (sumatoriaB/divisor)));
                            
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
                            sumatoriaR += kernel[k][l] * matrizImagenR[aux1][aux2];
                            sumatoriaG += kernel[k][l] * matrizImagenG[aux1][aux2];
                            sumatoriaB += kernel[k][l] * matrizImagenB[aux1][aux2];
                            
                            color = new Color(comprobarColor((int) (sumatoriaR/divisor)), comprobarColor((int) (sumatoriaG/divisor)), comprobarColor((int) (sumatoriaB/divisor)));
                            
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
                            sumatoriaR += kernel[k][l] * matrizImagenR[aux1][aux2];
                            sumatoriaG += kernel[k][l] * matrizImagenG[aux1][aux2];
                            sumatoriaB += kernel[k][l] * matrizImagenB[aux1][aux2];
                            
                            color = new Color(comprobarColor((int) (sumatoriaR/divisor)), comprobarColor((int) (sumatoriaG/divisor)), comprobarColor((int) (sumatoriaB/divisor)));
                            
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
                            sumatoriaR += kernel[k][l] * matrizImagenR[aux1][aux2];
                            sumatoriaG += kernel[k][l] * matrizImagenG[aux1][aux2];
                            sumatoriaB += kernel[k][l] * matrizImagenB[aux1][aux2];
                            
                            color = new Color(comprobarColor((int) (sumatoriaR/divisor)), comprobarColor((int) (sumatoriaG/divisor)), comprobarColor((int) (sumatoriaB/divisor)));
                            
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
                            sumatoriaR += kernel[k][l] * matrizImagenR[aux1][aux2];
                            sumatoriaG += kernel[k][l] * matrizImagenG[aux1][aux2];
                            sumatoriaB += kernel[k][l] * matrizImagenB[aux1][aux2];
                            
                            color = new Color(comprobarColor((int) (sumatoriaR/divisor)), comprobarColor((int) (sumatoriaG/divisor)), comprobarColor((int) (sumatoriaB/divisor)));
                            
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
                            sumatoriaR += kernel[k][l] * matrizImagenR[aux1][aux2];
                            sumatoriaG += kernel[k][l] * matrizImagenG[aux1][aux2];
                            sumatoriaB += kernel[k][l] * matrizImagenB[aux1][aux2];
                            
                            color = new Color(comprobarColor((int) (sumatoriaR/divisor)), comprobarColor((int) (sumatoriaG/divisor)), comprobarColor((int) (sumatoriaB/divisor)));
                            
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
                            sumatoriaR += kernel[k][l] * matrizImagenR[aux1][aux2];
                            sumatoriaG += kernel[k][l] * matrizImagenG[aux1][aux2];
                            sumatoriaB += kernel[k][l] * matrizImagenB[aux1][aux2];
                            
                            color = new Color(comprobarColor((int) (sumatoriaR/divisor)), comprobarColor((int) (sumatoriaG/divisor)), comprobarColor((int) (sumatoriaB/divisor)));
                            
                            
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
                                sumatoriaR += kernel[k][l] * matrizImagenR[aux1][aux2];
                                sumatoriaG += kernel[k][l] * matrizImagenG[aux1][aux2];
                                sumatoriaB += kernel[k][l] * matrizImagenB[aux1][aux2];

                                color = new Color(comprobarColor((int) (sumatoriaR/divisor)), comprobarColor((int) (sumatoriaG/divisor)), comprobarColor((int) (sumatoriaB/divisor)));   
                                
                                arregloAux[contadorAux] = (int) matrizImagenB[aux1][aux2];
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
                            sumatoriaR += kernel[k][l] * matrizImagenR[aux1][aux2];
                            sumatoriaG += kernel[k][l] * matrizImagenG[aux1][aux2];
                            sumatoriaB += kernel[k][l] * matrizImagenB[aux1][aux2];
                            
                            color = new Color(comprobarColor((int) (sumatoriaR/divisor)), comprobarColor((int) (sumatoriaG/divisor)), comprobarColor((int) (sumatoriaB/divisor)));
                            
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
                            sumatoriaR += kernel[k][l] * matrizImagenR[aux1][aux2];
                            sumatoriaG += kernel[k][l] * matrizImagenG[aux1][aux2];
                            sumatoriaB += kernel[k][l] * matrizImagenB[aux1][aux2];
                            
                            color = new Color(comprobarColor((int) (sumatoriaR/divisor)), comprobarColor((int) (sumatoriaG/divisor)), comprobarColor((int) (sumatoriaB/divisor)));
                            
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
                            sumatoriaR += kernel[k][l] * matrizImagenR[aux1][aux2];
                            sumatoriaG += kernel[k][l] * matrizImagenG[aux1][aux2];
                            sumatoriaB += kernel[k][l] * matrizImagenB[aux1][aux2];
                            
                            color = new Color(comprobarColor((int) (sumatoriaR/divisor)), comprobarColor((int) (sumatoriaG/divisor)), comprobarColor((int) (sumatoriaB/divisor)));
                            
                            aux2++;
                        }
                        aux1--;
                    }
                    matrizResultante[i][j] = color.getRGB();
                //caso i > 1, j en borde, cualquier tamano
                }else if (i == bi.getHeight()-1 && j > 1 &&  j< bi.getWidth()-3){
                    aux1 = bi.getHeight()-1;
                    //System.out.println("Entro en: ("+i+" ,"+j+") entro en i == bi.getHeight()-1 && j > 1 &&  j< bi.getWidth()-3");
                    for (int k = elementoCentral; k <= 0; k--) {                       
                        aux2 = j-elementoCentral;
                        for (int l = elementoCentral-1; l < kernel.length; l++) {
                            sumatoriaR += kernel[k][l] * matrizImagenR[aux1][aux2];
                            sumatoriaG += kernel[k][l] * matrizImagenG[aux1][aux2];
                            sumatoriaB += kernel[k][l] * matrizImagenB[aux1][aux2];
                            
                            color = new Color(comprobarColor((int) (sumatoriaR/divisor)), comprobarColor((int) (sumatoriaG/divisor)), comprobarColor((int) (sumatoriaB/divisor)));
                            
                            aux2++;
                        }
                        aux1--;
                    }
                    matrizResultante[i][j] = color.getRGB();
                   // System.out.println("Acabe el: ("+i+" ,"+j+") entro en i == bi.getHeight()-1 && j > 1 &&  j< bi.getWidth()-3");
                //caso i a 2 del borde, j en borde, cualquier tamano
                }else if (i == bi.getHeight()-1 && j > 1 &&  j == bi.getWidth()-3){
                    aux1 = bi.getHeight()-1;
                    for (int k = elementoCentral; k <= 0; k--) {
                        System.out.println("k: "+k);
                        aux2 = j-elementoCentral;
                        for (int l = elementoCentral-1; l < kernel.length; l++) {
                                sumatoriaR += kernel[k][l] * matrizImagenR[aux1][aux2];
                                sumatoriaG += kernel[k][l] * matrizImagenG[aux1][aux2];
                                sumatoriaB += kernel[k][l] * matrizImagenB[aux1][aux2];
                            
                            color = new Color(comprobarColor((int) (sumatoriaR/divisor)), comprobarColor((int) (sumatoriaG/divisor)), comprobarColor((int) (sumatoriaB/divisor)));
                            
                            aux2++;
                        }
                        aux1--;
                    }
                    matrizResultante[i][j] = color.getRGB();
                   // System.out.println("Acabe el: ("+i+" ,"+j+") entro en i == bi.getHeight()-1 && j > 1 &&  j< bi.getWidth()-3");
                //caso i a 2 del borde, j en borde, cualquier tamano
                }else if (i == bi.getHeight()-1 && j == bi.getWidth()-2){
                    aux1 = bi.getHeight()-1;
                  //  System.out.println("Entro en: ("+i+" ,"+j+") entro en i == bi.getHeight()-1 && j == bi.getWidth()-2");
                    for (int k = elementoCentral; k <= 0; k--) {                      
                        aux2 = j-elementoCentral;
                        for (int l = elementoCentral-1; l < kernel.length; l++) {
                            sumatoriaR += kernel[k][l] * matrizImagenR[aux1][aux2];
                            sumatoriaG += kernel[k][l] * matrizImagenG[aux1][aux2];
                            sumatoriaB += kernel[k][l] * matrizImagenB[aux1][aux2];
                            
                            color = new Color(comprobarColor((int) (sumatoriaR/divisor)), comprobarColor((int) (sumatoriaG/divisor)), comprobarColor((int) (sumatoriaB/divisor)));
                            
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
                            sumatoriaR += kernel[k][l] * matrizImagenR[aux1][aux2];
                            sumatoriaG += kernel[k][l] * matrizImagenG[aux1][aux2];
                            sumatoriaB += kernel[k][l] * matrizImagenB[aux1][aux2];
                            
                            color = new Color(comprobarColor((int) (sumatoriaR/divisor)), comprobarColor((int) (sumatoriaG/divisor)), comprobarColor((int) (sumatoriaB/divisor)));
                            
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
                            sumatoriaR += kernel[k][l] * matrizImagenR[aux1][aux2];
                            sumatoriaG += kernel[k][l] * matrizImagenG[aux1][aux2];
                            sumatoriaB += kernel[k][l] * matrizImagenB[aux1][aux2];
                            
                            color = new Color(comprobarColor((int) (sumatoriaR/divisor)), comprobarColor((int) (sumatoriaG/divisor)), comprobarColor((int) (sumatoriaB/divisor)));
                            
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
                            sumatoriaR += kernel[k][l] * matrizImagenR[aux1][aux2];
                            sumatoriaG += kernel[k][l] * matrizImagenG[aux1][aux2];
                            sumatoriaB += kernel[k][l] * matrizImagenB[aux1][aux2];
                            
                            color = new Color(comprobarColor((int) (sumatoriaR/divisor)), comprobarColor((int) (sumatoriaG/divisor)), comprobarColor((int) (sumatoriaB/divisor)));
                            
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
                            sumatoriaR += kernel[k][l] * matrizImagenR[aux1][aux2];
                            sumatoriaG += kernel[k][l] * matrizImagenG[aux1][aux2];                         
                            sumatoriaB += kernel[k][l] * matrizImagenB[aux1][aux2];
                            
                            color = new Color(comprobarColor((int) (sumatoriaR/divisor)), comprobarColor((int) (sumatoriaG/divisor)), comprobarColor((int) (sumatoriaB/divisor)));
                            
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
                            sumatoriaR += kernel[k][l] * matrizImagenR[aux1][aux2];
                            sumatoriaG += kernel[k][l] * matrizImagenG[aux1][aux2];                           
                            sumatoriaB += kernel[k][l] * matrizImagenB[aux1][aux2];
                            
                            color = new Color(comprobarColor((int) (sumatoriaR/divisor)), comprobarColor((int) (sumatoriaG/divisor)), comprobarColor((int) (sumatoriaB/divisor)));
                            
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
                bi.setRGB(j, i, (int) matrizResultante[i][j]);
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
}
