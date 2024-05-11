package tp3;

import java.util.ArrayList;

public class Produccion {
    
    ArrayList<Pieza> piezas = new ArrayList();
     int contBuenas = 0;
     int acumCosto=300;
    

    public ArrayList<Pieza> producirPiezas(int lote){
           
            for (int j = 1;j <= lote ; j++){
                Pieza pieza = new Pieza();
                piezas.add(pieza);
                if (pieza.esBuena()){
                    contBuenas++;
                }
            }
            if(contBuenas < 6){
                acumCosto += 300;
                producirPiezas(lote);
            }
            
        return piezas;
         
    }
    public double calcularCosto(){
        
        acumCosto += piezas.size() * 200;
        if (contBuenas > 6){
            acumCosto += (contBuenas-6)*100;
       
        }
     
       return acumCosto;
    }

    public int getContBuenas() {
        return contBuenas;
    }
    

}
