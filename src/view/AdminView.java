package view;

import business.BrandManager;
import entities.Brand;
import entities.Users;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AdminView extends Layout {
    private JPanel container;
    private JLabel lbl_welcome;
    private JTabbedPane tab_menu;
    private JButton btn_logout;
    private JPanel pnl_brand;
    private JScrollPane scrl_brand;
    private JTable tbl_brand;
    private Users users;
    private DefaultTableModel tmdl_brand = new DefaultTableModel();
    private BrandManager brandManager;
    private JPopupMenu jBrandMenu;

    public AdminView(Users user) throws HeadlessException {
        this.brandManager = new BrandManager();

        this.add(container);
        this.setTitle("Admin View");
        this.guiInitilaze(1000, 500);
        this.users = user;
        if (this.users == null) {
            dispose();
        }
        this.lbl_welcome.setText("Hoşgeldiniz " + user.getUsername());
        Object[] col_brand = {"Marka ID", "Marka Adı"};              //Brand için kolonlar
        ArrayList<Brand> brandArrayList = this.brandManager.findAll();   //barand managerdan dönen brand verileri listeye atadık.
        this.tmdl_brand.setColumnIdentifiers(col_brand);                 //Tablo kolonlarını ekledik

        for (Brand brand : brandArrayList) {
            Object[] obj = {brand.getId(), brand.getName()};
            this.tmdl_brand.addRow(obj);
        }

        this.tbl_brand.setModel(tmdl_brand); //tablo modelini tablo üzerinde görüntülemek için modeli ekledik.
        this.tbl_brand.getTableHeader().setReorderingAllowed(false);
        this.tbl_brand.setEnabled(false); // çift tıklayarak düzeltmeyi kapattık.

        this.tbl_brand.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) { //Tıklanılan satırın satır bilgisini al.
                    int selected_row = tbl_brand.rowAtPoint(e.getPoint());
                    tbl_brand.setRowSelectionInterval(selected_row,selected_row);
            }
        });

        this.jBrandMenu = new JPopupMenu();
        //Tıklanılan menuye göre işlemler yap.
        this.jBrandMenu.add("Yeni").addActionListener(e ->{
        BrandView brandView = new BrandView(null);

        });

        this.jBrandMenu.add("Güncelle").addActionListener(e -> {
                int selectId = Integer.parseInt(tbl_brand.getValueAt(tbl_brand.getSelectedRow(),0).toString());
                System.out.println(selectId);
                
        });
        this.jBrandMenu.add("Sil");

        this.tbl_brand.setComponentPopupMenu(jBrandMenu);
    }
}
