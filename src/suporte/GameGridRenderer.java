package suporte;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
*
* @author adilsonv77
*/

@SuppressWarnings("serial")
public class GameGridRenderer extends DefaultTableCellRenderer {

    public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus, int row,
                    int column) {
        setIcon((ImageIcon) value);

        return this;
    }

    public GameGridRenderer() {
        this.setHorizontalAlignment(0);
    }

}