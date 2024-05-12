package tp3;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        Font font = cellComponent.getFont();
        // Obtener el valor de la celda para la columna actual
        Object cellValue = table.getValueAt(row, column);
        cellComponent.setForeground(new Color(1,1,1));

        // Cambiar el color de fondo según el valor de la celda
        if (cellValue != null && cellValue instanceof String) {
            
            if (cellValue.equals("BUENA")) {
                cellComponent.setBackground(new Color(189,250,182)); // Celda verde si es buena
                
                cellComponent.setFont(font.deriveFont(Font.BOLD));
            } else {
                if(cellValue.equals("MALA")){
                     cellComponent.setBackground(new Color(220,82,82));
                     cellComponent.setFont(font.deriveFont(Font.BOLD));// Celda roja si es mala
                }
               
            }
        } else {
            cellComponent.setBackground(table.getBackground()); // Color de fondo predeterminado
        }

        return cellComponent;
    }
}