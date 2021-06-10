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
public class FiltroIdeal extends FiltroFrecuencia {
    private int radio;
    private Dimension dim;
    private Image imagen;
    
    public FiltroIdeal(int radio, Dimension dim) {
        super((int)dim.getWidth(),(int) dim.getHeight());
        this.radio = radio;
        this.dim = dim;
        this.imagen = null;
    }
   
    @Override
    public void crearFiltro() {
    int tamanoImagen = (int)dim.getWidth();
    for(int i=0; i < tamanoImagen;i++){
        for(int j=0; j < tamanoImagen;j++){
            int u = -1*(tamanoImagen/2)+i;
            int v = (tamanoImagen/2)-j;
            
            double r = Math.sqrt(Math.pow(u,2)+Math.pow(v, 2));
            if(r<=this.radio){
                getFiltroEspacial()[i][j] = new NumeroComplejo(1, 1);
                setFiltroEspacial(getFiltroEspacial());
            }  else {
                getFiltroEspacial()[i][j] = new NumeroComplejo(0, 0);
                setFiltroEspacial(getFiltroEspacial());
            }     
        }
    }    
    this.imagen = FiltroFrecuencia.toImageDeComplejo(super.getFiltroEspacial());
    }
    
    public void crearFiltroPasaAltas(){
    int tamanoImagen = (int)dim.getWidth();
    for(int i=0; i < tamanoImagen;i++){
        for(int j=0; j < tamanoImagen;j++){
            int u = -1*(tamanoImagen/2)+i;
            int v = (tamanoImagen/2)-j;
            
            double r = Math.sqrt(Math.pow(u,2)+Math.pow(v, 2));
            if(r<=this.radio){
                getFiltroEspacial()[i][j] = new NumeroComplejo(0, 0);
                setFiltroEspacial(getFiltroEspacial());
            }else{
                getFiltroEspacial()[i][j] = new NumeroComplejo(1, 1);
                setFiltroEspacial(getFiltroEspacial());
            }     
        }
    }    
    this.imagen = FiltroFrecuencia.toImageDeComplejo(super.getFiltroEspacial());    
    }
    
    public void modificarFiltro(int radio){
      this.radio = radio;
      crearFiltro();
    }

    /**
     * @return the imagen
     */
    public Image getImagen() {
        return imagen;
    }
    
    
}
