/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajarImagen;

/**
 *
 * @author Cri
 */
public class UmbralizacionAutomatica {
    public static int metodoIterativo(double[] histograma){
        int ui = calcularUmbralInicial(histograma);
        int uNuevo=0;
        
        do{
            uNuevo = ui;
            ui = reajustarUmbral(ui,histograma);
        }while(uNuevo != ui);
        
        return ui;
    }

    private static int calcularUmbralInicial(double[] histograma) {
        int numPixels = 0;
        int suma = 0;
        for(int x=0;x<histograma.length;x++){
        numPixels+=histograma[x];
        suma+=histograma[x]*x;
        }
        return (int)(suma/numPixels);
    }

    private static int reajustarUmbral(int ui, double[] histograma) {
       int u1,u2;
       int a1=0,a2=0,n1=0,n2=0;
       a1+=histograma[0];
       for(int x=1;x<ui;x++){
        a1+=histograma[x]*x;
        n1+=histograma[x];
       }
       
        for(int y=ui;y<=255;y++){
        a2+=histograma[y]*y;
        n2+=histograma[y];
       }
        if (n1==0 || n2==0) return 0;
        u1 = a1/n1;
        u2 = a2/n2;
       return (int)((u1+u2)/2);
    }
    
    public static int otsu(double[] histograma){
        int total = 0;
        for(int i = 0;i<histograma.length;i++)total+=histograma[i];

        int top = 256;
        int sumaBB = 0;
        int wB = 0;

        double maximo = 0.0;
        int wF;
        int mF;
        double valor;
        int umbral = 0;
        int suma1 = 0;
        int[] range = new int[top];
        for(int i = 0;i<top;i++)range[i]=i;

        for(int i = 0;i<histograma.length;i++)suma1+=range[i]*histograma[i];
        for(int i = 1;i<top;i++){
            wF = total - wB;

            if(wB > 0 && wF > 0){

                mF = (suma1 - sumaBB) / wF;

                valor = wB*wF*((sumaBB/wB)-mF)*((sumaBB/wB)-mF);
                if (valor >= maximo){
                    umbral = i;
                    maximo = valor;
                }   
            }
            wB = wB + (int)histograma[i];
            sumaBB = sumaBB + (i-1) *(int) histograma[i];
        }
        return umbral; 
    }
}
