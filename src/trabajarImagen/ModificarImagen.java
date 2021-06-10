/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajarImagen;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import  java.util.Arrays.*;
import static java.util.Arrays.sort;

/**
 *
 * @author Cri
 */
public class ModificarImagen {  
    
    public static Image convertirANegativo(Image imagenOriginal){
        BufferedImage bi = herramientas.HerramientasImagen.toBufferedImage(imagenOriginal);
        Color color;
        int r, g, b;
        for(int i=0; i < bi.getWidth(); i++){
            for(int j=0; j < bi.getHeight(); j++){
                color = new Color(bi.getRGB(i, j));

                r = color.getRed();
                g = color.getGreen();
                b = color.getBlue();

                bi.setRGB(i, j, new Color(255-r,255-g,255-b).getRGB());                                                                    
            }
        }        
        Image nueva = herramientas.HerramientasImagen.toImage(bi);
        return nueva;
    }
    
    public static Image convertirAGrises(Image imagenOriginal){
        BufferedImage bi = herramientas.HerramientasImagen.toBufferedImage(imagenOriginal);
        Color color;
        int r, g, b;
        for(int i=0;i<bi.getWidth();i++){
            for(int j=0;j<bi.getHeight();j++){
                color = new Color(bi.getRGB(i, j));
                int valorGris;

                r = color.getRed();
                g = color.getGreen();
                b = color.getBlue();   

                valorGris = (r+g+b)/3;

                Color gris = new Color(valorGris, valorGris, valorGris);

                bi.setRGB(i, j, gris.getRGB());                                                                    
            }
        }        
        Image nueva = herramientas.HerramientasImagen.toImage(bi);
        return nueva;
    }
    
    public static Image eliminarFondo(Image imagenOriginal){
        BufferedImage bi = herramientas.HerramientasImagen.toBufferedImage(imagenOriginal);   
                
        Color colorDominante = extraerColorDominante(bi);

        for(int i=0; i< bi.getWidth(); i++){
            for(int j=0; j< bi.getHeight(); j++){
                if(bi.getRGB(i, j) == colorDominante.getRGB()){
                    bi.setRGB(i, j, new Color(57, 255, 20).getRGB());
                }
            } 
        }
                
        Image nueva = herramientas.HerramientasImagen.toImage(bi);
        return nueva;
    }
    
    public static Color extraerColorDominante(BufferedImage bi){
        Color colorDominante = new Color(0,0,0);
        HashMap<Integer,Integer> colorCounter = new HashMap<Integer,Integer>();
        
        for (int i = 0; i < bi.getWidth(); i++) {
            for (int j = 0; j < bi.getHeight(); j++) {
                int colorKey = bi.getRGB(i, j);
                Color aux = new Color(bi.getRGB(i, j));
                if(colorCounter.containsKey(colorKey)){
                    colorCounter.put(colorKey, colorCounter.get(colorKey)+1);
                }else{
                    colorCounter.put(colorKey, 1);
                }
            }
        }
        
        
        int intColorDominante = 0;
        int intColorDominante2 = 0;
        int max = 0;
        
        for(int colorKey : colorCounter.keySet()){
            int count = colorCounter.get(colorKey);
            if(count > max){
                max = count;
                intColorDominante = colorKey;
            }
        }
        
        colorDominante = new Color(intColorDominante);

        return colorDominante;
    }
    
    public static Image binarizacionAutomatica(Image imagenOriginal){
        Image grises = trabajarImagen.ModificarImagen.convertirAGrises(imagenOriginal);
        
        Histograma h = new Histograma(grises);
        h.calcularHistogramas();
        
        UmbralizacionAutomatica uA = null;
            
        int ua = uA.metodoIterativo(h.getR());

        BufferedImage bi = herramientas.HerramientasImagen.toBufferedImage(grises);
             
        Color color;
        for(int x = 0; x<bi.getWidth(); x++){
            for(int y = 0; y<bi.getHeight(); y++){
                color = new Color(bi.getRGB(x, y));
                double v = (color.getRed() + color.getGreen() + color.getBlue())/3;
                if(v >= ua){
                    bi.setRGB(x, y, Color.WHITE.getRGB());
                }else{
                    bi.setRGB(x, y, Color.BLACK.getRGB());
                }
            }
        }
        Image nueva = herramientas.HerramientasImagen.toImage(bi);
        
        return nueva;
    }
    
    public static Image binarizacionAutomaticaOtsu(Image imagenOriginal){
        Image grises = trabajarImagen.ModificarImagen.convertirAGrises(imagenOriginal);
        
        Histograma h = new Histograma(grises);
        h.calcularHistogramas();
        
        UmbralizacionAutomatica uA = null;
            
        int ua = uA.otsu(h.getR());

        BufferedImage bi = herramientas.HerramientasImagen.toBufferedImage(grises);
             
        Color color;
        for(int x = 0; x<bi.getWidth(); x++){
            for(int y = 0; y<bi.getHeight(); y++){
                color = new Color(bi.getRGB(x, y));
                double v = (color.getRed() + color.getGreen() + color.getBlue())/3;
                if(v >= ua){
                    bi.setRGB(x, y, Color.WHITE.getRGB());
                }else{
                    bi.setRGB(x, y, Color.BLACK.getRGB());
                }
            }
        }
        Image nueva = herramientas.HerramientasImagen.toImage(bi);
        
        return nueva;
    }
    
    public static Image multiplicarPixeles(Image imagenOriginal){
        BufferedImage bi = herramientas.HerramientasImagen.toBufferedImage(imagenOriginal);
        
        Histograma h = new Histograma(imagenOriginal);
        h.calcularHistogramas();
        double rojos[] = h.getR();
        double verdes[] = h.getG();
        double azules[] = h.getB();
        
        int r1 = calcularR1(rojos, verdes, azules);
        int r2 = calcularR2(rojos, verdes, azules);

        Color color;
        int r, nR, g, nG, b, nB;
        for(int i = 0 ; i < bi.getWidth(); i++){
            for(int j = 0 ; j < bi.getHeight(); j++){  
                color = new Color(bi.getRGB(i, j));
                r = color.getRed();
                nR = comprobarColor((r-r1)*(255/(r2-r1)));

                g = color.getGreen();
                nG = comprobarColor((g-r1)*(255/(r2-r1)));

                b = color.getBlue();
                nB = comprobarColor((b-r1)*(255/(r2-r1)));

                bi.setRGB(i, j, new Color(nR, nG, nB).getRGB()); 
            }
        }
        Image nueva = herramientas.HerramientasImagen.toImage(bi);
        
        return nueva;
    }
    
    public static Image multiplicarPixelesLogaritmico(Image imagenOriginal){
        BufferedImage bi = herramientas.HerramientasImagen.toBufferedImage(imagenOriginal);
        
        Histograma h = new Histograma(imagenOriginal);
        h.calcularHistogramas();
        double rojos[] = h.getR();
        double verdes[] = h.getG();
        double azules[] = h.getB();
        
        int r1 = calcularR1(rojos, verdes, azules);
        int r2 = calcularR2(rojos, verdes, azules);

        Color color;
        int r, nR, g, nG, b, nB;
        Random ran = new Random();
        int alpha = (int)(ran.nextDouble() * r2 + r1);
        
        for(int i = 0 ; i < bi.getWidth(); i++){
            for(int j = 0 ; j < bi.getHeight(); j++){  
                color = new Color(bi.getRGB(i, j));
                
                r = color.getRed();
                nR = comprobarColor((int)((alpha*Math.log(1+r))/Math.log(1+alpha)));
                
                g = color.getGreen();
                nG = comprobarColor((int)((alpha*Math.log(1+g))/Math.log(1+alpha)));
                
                b = color.getBlue();
                nB = comprobarColor((int)((alpha*Math.log(1+b))/Math.log(1+alpha)));
                
                bi.setRGB(i, j, new Color(nR, nG, nB).getRGB()); 
            }
        }
        Image nueva = herramientas.HerramientasImagen.toImage(bi);
        
        return nueva;
    }
    
    public static Image multiplicarPixelesExponencial(Image imagenOriginal){
        BufferedImage bi = herramientas.HerramientasImagen.toBufferedImage(imagenOriginal);
        
        Histograma h = new Histograma(imagenOriginal);
        h.calcularHistogramas();
        double rojos[] = h.getR();
        double verdes[] = h.getG();
        double azules[] = h.getB();
        
        int r1 = calcularR1(rojos, verdes, azules);
        int r2 = calcularR2(rojos, verdes, azules);
        
        Color color;
        int r, nR, g, nG, b, nB;
        Random ran = new Random();
        double alpha = (int)(ran.nextDouble() * 8 + 2);
        
        //X’ = A(1-e-αx/q), donde α ∈ [0, q]    
        
        for(int i = 0 ; i < bi.getWidth(); i++){
            for(int j = 0 ; j < bi.getHeight(); j++){  
                color = new Color(bi.getRGB(i, j));
                r = color.getRed();
                nR = comprobarColor((int) ((r2/(Math.exp(alpha)-1)) * (Math.exp(alpha*r/r2)-1)));

                g = color.getGreen();
                nG = comprobarColor((int) ((r2/(Math.exp(alpha)-1)) * (Math.exp(alpha*g/r2)-1)));
                
                b = color.getBlue();
                nB = comprobarColor((int) ((r2/(Math.exp(alpha)-1)) * (Math.exp(alpha*b/r2)-1)));
                
                bi.setRGB(i, j, new Color(nR, nG, nB).getRGB()); 
            }
        }
        
        Image nueva = herramientas.HerramientasImagen.toImage(bi);
        
        return nueva;
    }
    
    public static int comprobarColor(int color){
        if (color > 255){
            color = 255;
        }else if(color < 0){
            color = 0;
        }
        
        return color;
    }
    
    private static int calcularR1(double rojos[], double verdes[], double azules[]){
        int r1 = 0;
        int menorR = 0, menorG = 0, menorB = 0;
        
        for (int i = 0; i < rojos.length; i++) {
            if(rojos [i]< menorR) {
                menorR = i;
            }
        }
        
        for (int i = 0; i < azules.length; i++) {
            if(azules [i]< menorB) {
                menorB = i;
            }
        }
        
        for (int i = 0; i < verdes.length; i++) {
            if(verdes [i]< menorG) {
                menorG = i;
            }
        }
        
        if (menorR < menorG && menorR < menorG){
            r1 = menorR;
        }else if (menorG < menorR && menorG < menorB){
            r1 = menorG;
        }else{
            r1 = menorB;
        }
        
        return r1;
    }
    
    private static int calcularR2(double rojos[], double verdes[], double azules[]){
        int r2 = 0;
        int mayorR = 0, mayorG = 0, mayorB = 0;
        
        for (int i = 0; i < rojos.length; i++) {
            if(rojos [i] > mayorR) {
                mayorR = i;
            }
        }
        
        for (int i = 0; i < azules.length; i++) {
            if(azules [i] > mayorB) {
                mayorB = i;
            }
        }
        
        for (int i = 0; i < verdes.length; i++) {
            if(verdes [i] > mayorG) {
                mayorG = i;
            }
        }
        
        if (mayorR > mayorG && mayorR > mayorG){
            r2 = mayorR;
        }else if (mayorG > mayorR && mayorG > mayorB){
            r2 = mayorG;
        }else{
            r2 = mayorB;
        }
        
        return r2;
    }
    
    public static Image ecualizarHistograma(Image imagenOriginal) {
        BufferedImage bi = herramientas.HerramientasImagen.toBufferedImage(imagenOriginal);
        
            int width = bi.getWidth();
            int height = bi.getHeight();
            int srcRGBs[] = bi.getRGB(0, 0, width, height, null, 0, width);
            int rgb[]=new int[3];
            int rgb1[]=new int[3];
            BufferedImage destImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            double H,S,I;
            double count[] = new double[256];
            for (int j = 0; j < height; j++) {
                    for(int i=0; i<width; i++) {
                            decodeColor(srcRGBs[j*width+i],rgb);
                            I= ((double)(rgb[0]+rgb[1]+rgb[2])/(double)3);
                            if (count[(int)I]==0){
                                    count[(int)I]=1;
                            }else {
                                    count[(int)I]+=1;
                            }
                    }
            }
            double gl[]=new double[256];
            for (int i=0;i<256;i++){
                    gl[i]=(double)count[i]/(double)(width*height);
            }
            double sk[] = new double[256];
            for (int i=0;i<256;i++){
                    for (int j=0;j<=i;j++){
                            sk[i]+=gl[j];
                    }
            }

            double Sk[]=new double[256];
            for (int i=0;i<256;i++){
                    Sk[i]=(255*sk[i]+0.5);
            }
            for (int j=0;j<height;j++){
                    for(int i=0;i<width;i++){
                            decodeColor(srcRGBs[j*width+i],rgb);
                            if (rgb[1]>rgb[2]){
                                    H=Math.acos((rgb[0]-rgb[1]+rgb[0]-rgb[2])
                                                    /(2.0*Math.sqrt((rgb[0]-rgb[1])*(rgb[0]-rgb[1])+(rgb[0]-rgb[2])*(rgb[1]-rgb[2]))));
                            }else{
                                    H=2.0*Math.PI-Math.acos((rgb[0]-rgb[1]+rgb[0]-rgb[2])
                                                    /(2.0*Math.sqrt((rgb[0]-rgb[1])*(rgb[0]-rgb[1])+(rgb[0]-rgb[2])*(rgb[1]-rgb[2]))));
                            }
                            sort(rgb);
                            I= ((double)(rgb[0]+rgb[1]+rgb[2])/(double)3);
                            I=Sk[(int)I];
                            if (rgb[0]==rgb[1]&&rgb[1]==rgb[2]){
                                    rgb1[0]=(int)I;
                                    rgb1[1]=(int)I;
                                    rgb1[2]=(int)I;
                            }else {
                                    S = 1 - ((double) 3 / (double) (rgb[0] + rgb[1] + rgb[2])) * (double) rgb[0];
                                    if(H<=2.09){
                                            rgb1[0]=(int)(I*(1.0+(S*Math.cos(H))/Math.cos((Math.PI/3.0)-H)));  //jxh
                                            rgb1[0]=rgb1[0]>255?255:rgb1[0];
                                            rgb1[0]=rgb1[0]<0?0:rgb1[0];
                                            rgb1[2]=(int)(I*(1.0-S));
                                            rgb1[2]=rgb1[2]<0?0:rgb1[2];
                                            rgb1[2]=rgb1[2]>255?255:rgb1[2];
                                            rgb1[1]=(int)(3.0*I-rgb1[0]-rgb1[2]);
                                            rgb1[1]=rgb1[1]>255?255:rgb1[1];
                                            rgb1[1]=rgb1[1]<0?0:rgb1[1];
                                    }else if(H>2.09&&H<=4.18){
                                            rgb1[1]=(int)(I*(1.0+(S*Math.cos(H-(Math.PI*2.0/3.0)))/Math.cos((Math.PI)-H)));//jxh
                                            rgb1[1]=rgb1[1]>255?255:rgb1[1];
                                            rgb1[1]=rgb1[1]<0?0:rgb1[1];
                                            rgb1[0]=(int)(I*(1.0-S));
                                            rgb1[0]=rgb1[0]>255?255:rgb1[0];
                                            rgb1[0]=rgb1[0]<0?0:rgb1[0];
                                            rgb1[2]=(int)(3.0*I-rgb1[0]-rgb1[1]);
                                            rgb1[2]=rgb1[2]>255?255:rgb1[2];
                                            rgb1[2]=rgb1[2]<0?0:rgb1[2];
                                    }else if(H>4.18){
                                            rgb1[2]=(int)(I*(1.0+(S*Math.cos(H-(Math.PI*4.0/3.0)))/Math.cos((Math.PI*5.0/3.0)-H))); //jxh
                                            rgb1[2]=rgb1[2]>255?255:rgb1[2];
                                            rgb1[2]=rgb1[2]<0?0:rgb1[2];
                                            rgb1[1]=(int)(I*(1.0-S));
                                            rgb1[1]=rgb1[1]>255?255:rgb1[1];
                                            rgb1[1]=rgb1[1]<0?0:rgb1[1];
                                            rgb1[0]=(int)(3.0*I-rgb1[1]-rgb1[2]);
                                            rgb1[0]=rgb1[0]>255?255:rgb1[0];
                                            rgb1[0]=rgb1[0]<0?0:rgb1[0];
                                    }
                            }

                            destImage.setRGB(i,j, encodeColor(rgb1));
                    }
            }
            Image nueva = herramientas.HerramientasImagen.toImage(destImage);
            
            return nueva;
    }
    
    public static int[] decodeColor(int color, int rgb[]) {
        if(rgb == null) rgb = new int[3];
        rgb[0] = (color & 0x00ff0000) >> 16;
        rgb[1] = (color & 0x0000ff00) >> 8;
        rgb[2] = (color & 0x000000ff);  
        return rgb;
    }
     
    public static int encodeColor(int rgb[]) {
        int color = (255 << 24) | (rgb[0] << 16) | (rgb[1] << 8) | rgb[2];
        return color;
    }
   
    /*
    public static Image agregarRuido(Image imagenOriginal, float limite){
        BufferedImage bi = herramientas.HerramientasImagen.toBufferedImage(imagenOriginal);
        Color color;
        int r, g, b;
        
        int[][] pixelesRuidoSal = new int[bi.getWidth()][bi.getHeight()];
        int[][] pixelesRuidoPimienta = new int[bi.getWidth()][bi.getHeight()];
        
        for (int i = 0; i < bi.getWidth(); i++) {
            for (int j = 0; j < bi.getHeight(); j++) {
                pixelesRuidoSal[i][j] = 0;
                pixelesRuidoPimienta[i][j] = 0;
            }
        }
        
        for (int i = 0; i < bi.getWidth()*limite; i++) {
            for (int j = 0; j < bi.getHeight()*limite; j++) {
                Random random = new Random();

                int valorRandomSalX = random.nextInt(bi.getWidth());
                int valorRandomSalY = random.nextInt(bi.getHeight());
                
                int valorRandomPimientaX  = random.nextInt(bi.getWidth());
                int valorRandomPimientaY  = random.nextInt(bi.getHeight());
                
                pixelesRuidoSal[valorRandomSalX][valorRandomSalY] = 1;
                pixelesRuidoPimienta[valorRandomPimientaX][valorRandomPimientaY] = 1;
            }
        }

        for(int i=0; i < bi.getWidth(); i++){
            for(int j=0; j < bi.getHeight(); j++){
                if (pixelesRuidoSal[i][j] == 1){
                    bi.setRGB(i, j, new Color(255, 255, 255).getRGB());
                }
                
                if (pixelesRuidoPimienta[i][j] == 1){
                    bi.setRGB(i, j, new Color(0, 0, 0).getRGB());
                }
            }
        }        
        
        Image nueva = herramientas.HerramientasImagen.toImage(bi);
        return nueva;
    }
    */
}
