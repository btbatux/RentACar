package view;

import core.Helper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class Layout extends JFrame {

    public void guiInitilaze(int widhth, int heigth) {
        //BU sınıfı miras alan viewler bu
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(widhth, heigth);
        //pencere tam merkezde açılması için x,y lokasyonları.
        this.setLocation(Helper.getLocationPoint("x", this.getSize()), Helper.getLocationPoint("y", this.getSize()));
        this.setVisible(true);
    }

    public void createTable(DefaultTableModel defaultTableModel, JTable jTable, Object[] coloumn, ArrayList<Object[]> rows) {
        defaultTableModel.setColumnIdentifiers(coloumn);
        jTable.setModel(defaultTableModel);
        jTable.getTableHeader().setReorderingAllowed(false);
        jTable.getColumnModel().getColumn(0).setMaxWidth(75);
        jTable.setEnabled(false);

        DefaultTableModel clearModel = (DefaultTableModel) jTable.getModel();
        clearModel.setRowCount(0);

        if (rows == null) {
            rows = new ArrayList<>();

        }

        for (Object[] row : rows) {
            defaultTableModel.addRow(row);
        }
    }
}
