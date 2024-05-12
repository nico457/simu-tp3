/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
public class GestorProduccion implements ActionListener{
    private Pantalla views;
    private DefaultTableModel model = new DefaultTableModel();
    private CellRenderer cell = new CellRenderer();

    double menorCosto;

    public GestorProduccion(Pantalla views){
        this.views = views;
        this.views.btn_gen_sim.addActionListener(this);
        this.menorCosto = 99999999;
        this.views.tabla.setDefaultRenderer(Object.class, cell);
        
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
            model.addColumn("VECES PROD");
            model.addColumn("BUENAS");
            model.addColumn("COSTO");

         
            double promedioCostos = 0;
            double acumCostos = 0;
            for (int j = 0; j < veces; j++) {
                Produccion produccion = new Produccion();
                ArrayList<Pieza> piezas = produccion.producirPiezas(lote);
               
                int cantColumnas = (lote * 2) + 4;
                Object[] row = new Object[cantColumnas];
                row[0] = j+1;
                int cont = 0;
                
                for(int i = 1; i<= lote*2;i+=2){
                    
                    row[i] =  Double.parseDouble(piezas.get(cont).getRnd().replace(',', '.'));
                    row[i+1] =  piezas.get(cont).esBuena()?"BUENA":"MALA";      
                    cont++;
               
            }
                
                
                int len = row.length;
                row[len-3] = piezas.size()/lote;
                row[len-2] = produccion.getContBuenas();
                double costo = produccion.calcularCosto();
                acumCostos += costo;
                row[len-1] = costo;
                
                model.addRow(row);
                
            }
            promedioCostos = acumCostos / veces;
            views.promedio.setText(String.format("%.2f",promedioCostos));
            
            
            
            if(promedioCostos <= menorCosto){
                menorCosto = promedioCostos;
                views.mejorLote.setText(String.valueOf(lote));
                views.txt_menorCosto.setText(String.format("%.2f",promedioCostos));
            }
                  
        }
    }
    public void cleanTable() {
       DefaultTableModel model = (DefaultTableModel) views.tabla.getModel();
        model.setRowCount(0);
        model.setColumnCount(1);
       
    }
     
   
 
}
