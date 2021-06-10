/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filtros;

import fourier.NumeroComplejo;
import java.awt.Dimension;
import java.awt.Image;

/**
 *
 * @author Cri
 */
public class FiltroGaussiano extends FiltroFrecuencia{
    private int radio;
    private Dimension dim;
    private Image imagen;

    public FiltroGaussiano(int ancho, int alto) {
        super(ancho, alto);
    }
    
    public FiltroGaussiano(int radio, Dimension dim) {
        super((int)dim.getWidth(),(int) dim.getHeight());
        this.radio = radio;
        this.dim = dim;
        this.imagen = null;
    }    

    @Override
    public void crearFiltro() {
        double radiotemp=this.radio;
        double gris=1;  
        double resta=0;
        int tamanoImagen = (int)dim.getWidth();
        
        for(int i=0; i < tamanoImagen;i++){
            for(int j=0; j < tamanoImagen;j++){
                int u = -1*(tamanoImagen/2)+i;
                int v = (tamanoImagen/2)-j;
         
                double r = Math.sqrt(Math.pow(u,2)+Math.pow(v, 2));
                if(r<=this.radio){
                    if(r==radiotemp && r>(radiotemp/100)*95){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(0, 0);
                    }
                    else if(r<=(radiotemp/100)*95 && r>(radiotemp/100)*90){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.95, 1-0.95);
                    }
                    else if(r<=(radiotemp/100)*90 && r>(radiotemp/100)*85){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.9, 1-0.9);
                    }
                    else if(r<=(radiotemp/100)*85 && r>(radiotemp/100)*80){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.85, 1-0.85);
                    }
                    else if(r<=(radiotemp/100)*80 && r>(radiotemp/100)*75){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.80, 1-0.80);
                    }
                    else if(r<=(radiotemp/100)*75 && r>(radiotemp/100)*70){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.75, 1-0.75);
                    }
                    else if(r<=(radiotemp/100)*70 && r>(radiotemp/100)*65){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.70, 1-0.70);
                    }
                    else if(r<=(radiotemp/100)*65 && r>(radiotemp/100)*60){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.65, 1-0.65);
                    }
                    else if(r<=(radiotemp/100)*60 && r>(radiotemp/100)*55){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.60, 1-0.60);
                    }
                    else if(r<=(radiotemp/100)*55 && r>(radiotemp/100)*50){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.55, 1-0.55);
                    }
                    else if(r<=(radiotemp/100)*50 && r>(radiotemp/100)*45){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.50, 1-0.50);
                    }
                    else if(r<=(radiotemp/100)*45 && r>(radiotemp/100)*40){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.45, 1-0.45);
                    }
                    else if(r<=(radiotemp/100)*40 && r>(radiotemp/100)*35){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.40, 1-0.40);
                    }
                    else if(r<=(radiotemp/100)*35 && r>(radiotemp/100)*30){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.35, 1-0.35);
                    }
                    else if(r<=(radiotemp/100)*30 && r>(radiotemp/100)*25 ){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.30, 1-0.30);
                    }
                    else if(r<=(radiotemp/100)*25 && r>(radiotemp/100)*20 ){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.25, 1-0.25);
                    }
                    else if(r<=(radiotemp/100)*20 && r>(radiotemp/100)*15 ){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.20, 1-0.20);
                    }
                    else if(r<=(radiotemp/100)*15 && r>(radiotemp/100)*10 ){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.15, 1-0.15);
                    }
                    else if(r<=(radiotemp/100)*10 && r>=(radiotemp/100)*5 ){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.1, 1-0.1);
                    }
                    else if(r<=(radiotemp/100)*5 && r>=(radiotemp/100)*0 ){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.05, 1-0.05);
                    }
                    else{
                        getFiltroEspacial()[i][j] = new NumeroComplejo(0, 0);
                    }
                }else{
                   getFiltroEspacial()[i][j] = new NumeroComplejo(0, 0); 
                }     
            }
        }    
        this.imagen = FiltroFrecuencia.toImageDeComplejo(super.getFiltroEspacial());
    }
    
    public void crearFiltroPasaAltas(){
        double radiotemp=this.radio;
        double gris=1;  
        double resta=0;
        int tamanoImagen = (int)dim.getWidth();
        
        for(int i=0; i < tamanoImagen;i++){
            for(int j=0; j < tamanoImagen;j++){
                int u = -1*(tamanoImagen/2)+i;
                int v = (tamanoImagen/2)-j;
         
                double r = Math.sqrt(Math.pow(u,2)+Math.pow(v, 2));
                if(r<=this.radio){
                    if(r<=radiotemp && r>(radiotemp/100)*95){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1, 1);
                    }
                    else if(r<=(radiotemp/100)*95 && r>(radiotemp/100)*90){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.05, 1-0.05);
                    }
                    else if(r<=(radiotemp/100)*90 && r>(radiotemp/100)*85){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.1, 1-0.1);
                    }
                    else if(r<=(radiotemp/100)*85 && r>(radiotemp/100)*80){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.15, 1-0.15);
                    }
                    else if(r<=(radiotemp/100)*80 && r>(radiotemp/100)*75){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.20, 1-0.20);
                    }
                    else if(r<=(radiotemp/100)*75 && r>(radiotemp/100)*70){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.25, 1-0.25);
                    }
                    else if(r<=(radiotemp/100)*70 && r>(radiotemp/100)*65){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.30, 1-0.30);
                    }
                    else if(r<=(radiotemp/100)*65 && r>(radiotemp/100)*60){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.35, 1-0.35);
                    }
                    else if(r<=(radiotemp/100)*60 && r>(radiotemp/100)*55){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.40, 1-0.40);
                    }
                    else if(r<=(radiotemp/100)*55 && r>(radiotemp/100)*50){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.45, 1-0.45);
                    }
                    else if(r<=(radiotemp/100)*50 && r>(radiotemp/100)*45){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.50, 1-0.50);
                    }
                    else if(r<=(radiotemp/100)*45 && r>(radiotemp/100)*40){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.55, 1-0.55);
                    }
                    else if(r<=(radiotemp/100)*40 && r>(radiotemp/100)*35){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.60, 1-0.60);
                    }
                    else if(r<=(radiotemp/100)*35 && r>(radiotemp/100)*30){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.65, 1-0.65);
                    }
                    else if(r<=(radiotemp/100)*30 && r>(radiotemp/100)*25 ){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.70, 1-0.70);
                    }
                    else if(r<=(radiotemp/100)*25 && r>(radiotemp/100)*20 ){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.75, 1-0.75);
                    }
                    else if(r<=(radiotemp/100)*20 && r>(radiotemp/100)*15 ){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.80, 1-0.80);
                    }
                    else if(r<=(radiotemp/100)*15 && r>(radiotemp/100)*10 ){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.85, 1-0.85);
                    }
                    else if(r<=(radiotemp/100)*10 && r>=(radiotemp/100)*5 ){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.90, 1-0.90);
                    }
                    else if(r<=(radiotemp/100)*5 && r>=(radiotemp/100)*0 ){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.95, 1-0.95);
                    }
                    else{
                        getFiltroEspacial()[i][j] = new NumeroComplejo(0, 0);
                    }
            } else {
               getFiltroEspacial()[i][j] = new NumeroComplejo(1, 1); 
            }     
        }
    }    
    this.imagen = FiltroFrecuencia.toImageDeComplejo(super.getFiltroEspacial());
    }
    
    public Image getImagen() {
        return imagen;
    }
}
