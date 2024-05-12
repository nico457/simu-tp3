/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author nicoe
 */
public class GestorProduccion implements ActionListener{
    private Pantalla views;
    private DefaultTableModel model = new DefaultTableModel();

    double menorCosto;

    public GestorProduccion(Pantalla views){
        this.views = views;
        this.views.btn_gen_sim.addActionListener(this);
        this.menorCosto = 99999999;
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btn_gen_sim) {
            
            int lote = Integer.parseInt(views.lote.getText());
            int veces = Integer.parseInt(views.veces.getText());
            cleanTable();
    
            model = (DefaultTableModel) views.tabla.getModel();
            for (int i = 0; i < lote; i++) {
                model.addColumn("RND " + (i + 1));
                model.addColumn("COND " + (i + 1));
            }
            model.addColumn("BUENAS");
            model.addColumn("COSTO");
         
            double promedioCostos = 0;
            double acumCostos = 0;
            for (int j = 0; j < veces; j++) {
                Produccion produccion = new Produccion();
                ArrayList<Pieza> piezas = produccion.producirPiezas(lote);
                int cantColumnas = (lote * 2) + 3;
                Object[] row = new Object[cantColumnas];
                row[0] = j+1;
                int cont = 0;
                
                for(int i = 1; i<= lote*2;i+=2){
                    
                    row[i] =  piezas.get(cont).getRnd();
                    row[i+1] =  piezas.get(cont).esBuena();      
                    cont++;
               
            }

                int len = row.length;
                row[len-2] = produccion.getContBuenas();
                double costo = produccion.calcularCosto();
                acumCostos += costo;
                row[len-1] = costo;
                
                model.addRow(row);
                
            }
            promedioCostos = acumCostos / veces;
            views.promedio.setText(String.valueOf(promedioCostos));
            
            
            
            if(promedioCostos <= menorCosto){
                menorCosto = promedioCostos;
                views.mejorLote.setText(String.valueOf(lote));
            }
                  
        }
    }
    public void cleanTable() {
       DefaultTableModel model = (DefaultTableModel) views.tabla.getModel();
        model.setRowCount(0);
        model.setColumnCount(1);
       
    }
     
   
 
}
