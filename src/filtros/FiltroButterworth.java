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
public class FiltroButterworth extends FiltroFrecuencia{
    private int radio;
    private Dimension dim;
    private Image imagen;

    public FiltroButterworth(int ancho, int alto) {
        super(ancho, alto);
    }
    
    public FiltroButterworth(int radio, Dimension dim) {
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
                    if(r<=radiotemp && r>(radiotemp/100)*75){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(0, 0);
                    }
                    else if(r<=(radiotemp/100)*75 && r>(radiotemp/100)*70){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.90, 1-0.90);
                    }
                    else if(r<=(radiotemp/100)*70 && r>(radiotemp/100)*65){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.84, 1-0.84);
                    }
                    else if(r<=(radiotemp/100)*65 && r>(radiotemp/100)*60){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.78, 1-0.78);
                    }
                    else if(r<=(radiotemp/100)*60 && r>(radiotemp/100)*55){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.72, 1-0.72);
                    }
                    else if(r<=(radiotemp/100)*55 && r>(radiotemp/100)*50){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.66, 1-0.66);
                    }
                    else if(r<=(radiotemp/100)*50 && r>(radiotemp/100)*45){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.60, 1-0.60);
                    }
                    else if(r<=(radiotemp/100)*45 && r>(radiotemp/100)*40){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.54, 1-0.54);
                    }
                    else if(r<=(radiotemp/100)*40 && r>(radiotemp/100)*35){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.48, 1-0.48);
                    }
                    else if(r<=(radiotemp/100)*35 && r>(radiotemp/100)*30){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.42, 1-0.42);
                    }
                    else if(r<=(radiotemp/100)*30 && r>(radiotemp/100)*25 ){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.36, 1-0.36);
                    }
                    else if(r<=(radiotemp/100)*25 && r>(radiotemp/100)*20 ){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.30, 1-0.30);
                    }
                    else if(r<=(radiotemp/100)*20 && r>(radiotemp/100)*15 ){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.24, 1-0.24);
                    }
                    else if(r<=(radiotemp/100)*15 && r>(radiotemp/100)*10 ){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.18, 1-0.18);
                    }
                    else if(r<=(radiotemp/100)*10 && r>=(radiotemp/100)*5 ){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.12, 1-0.12);
                    }
                    else if(r<=(radiotemp/100)*5 && r>=(radiotemp/100)*0 ){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.06, 1-0.06);
                    }
                    else{
                        getFiltroEspacial()[i][j] = new NumeroComplejo(0, 0);
                    }
            }else {
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
                    if(r<=radiotemp && r>(radiotemp/100)*75){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1, 1);
                    }
                    else if(r<=(radiotemp/100)*75 && r>(radiotemp/100)*70){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.06, 1-0.06);
                    }
                    else if(r<=(radiotemp/100)*70 && r>(radiotemp/100)*65){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.12, 1-0.12);
                    }
                    else if(r<=(radiotemp/100)*65 && r>(radiotemp/100)*60){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.18, 1-0.18);
                    }
                    else if(r<=(radiotemp/100)*60 && r>(radiotemp/100)*55){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.24, 1-0.24);
                    }
                    else if(r<=(radiotemp/100)*55 && r>(radiotemp/100)*50){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.30, 1-0.30);
                    }
                    else if(r<=(radiotemp/100)*50 && r>(radiotemp/100)*45){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.36, 1-0.36);
                    }
                    else if(r<=(radiotemp/100)*45 && r>(radiotemp/100)*40){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.42, 1-0.42);
                    }
                    else if(r<=(radiotemp/100)*40 && r>(radiotemp/100)*35){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.48, 1-0.48);
                    }
                    else if(r<=(radiotemp/100)*35 && r>(radiotemp/100)*30){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.54, 1-0.54);
                    }
                    else if(r<=(radiotemp/100)*30 && r>(radiotemp/100)*25 ){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.60, 1-0.60);
                    }
                    else if(r<=(radiotemp/100)*25 && r>(radiotemp/100)*20 ){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.66, 1-0.66);
                    }
                    else if(r<=(radiotemp/100)*20 && r>(radiotemp/100)*15 ){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.72, 1-0.72);
                    }
                    else if(r<=(radiotemp/100)*15 && r>(radiotemp/100)*10 ){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.78, 1-0.78);
                    }
                    else if(r<=(radiotemp/100)*10 && r>=(radiotemp/100)*5 ){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.84, 1-0.84);
                    }
                    else if(r<=(radiotemp/100)*5 && r>=(radiotemp/100)*0 ){
                        getFiltroEspacial()[i][j] = new NumeroComplejo(1-0.90, 1-0.90);
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
