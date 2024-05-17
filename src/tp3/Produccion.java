package tp3;

import java.util.ArrayList;

public class Produccion {
    
    ArrayList<Pieza> piezas = new ArrayList();
     int contBuenas = 0;
     int contMalas = 0;
     int acumCosto=300;
     int sobrante=0;
    

    public ArrayList<Pieza> producirPiezas(int lote){
           
            for (int j = 1;j <= lote ; j++){
                Pieza pieza = new Pieza();
                piezas.add(pieza);
                if (pieza.esBuena()){
                    contBuenas++;
                }
                
                if (contBuenas == 6) {
                        for (int k = j + 1; k <= lote; k++) {
                        piezas.add(new Pieza(1.1, false)); // Pieza nula
                        sobrante++;
                    }
                    break;
                }
            }
            if(contBuenas < 6){
                acumCosto += 300;
                producirPiezas(lote);
            }
            
        return piezas;
         
    }
    public double calcularCosto(){
        acumCosto += (piezas.size() * 100) + (piezas.size() - sobrante) * 100;  
       return acumCosto;
    }

    public int getContBuenas() {
        return contBuenas;
    }
    
    public int getSobrante() {
        return sobrante;
    }
    
    public int getContMalas() {
        contMalas = (piezas.size() - sobrante) - contBuenas;
        return contMalas;
    }
    

}
