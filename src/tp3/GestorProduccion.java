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
    

    public GestorProduccion(Pantalla views){
        this.views = views;
        this.views.btn_gen_sim.addActionListener(this);
        
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
         
       
            
            int cantColumnas = (lote * 2) + 3;
            
      
            for (int j = 0; j < veces; j++) {
                Produccion produccion = new Produccion();
                ArrayList<Pieza> piezas = produccion.producirPiezas(lote);
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
                row[len-1] = produccion.calcularCosto();
                
                model.addRow(row);
                
            }
           
            
            /*
        
            */
         
            
        }
    }
    public void cleanTable() {
        for (int i = 0; i < model.getRowCount(); i++) {
            model.removeRow(i);
            
  
        }
       
    }
     
   
 
}
