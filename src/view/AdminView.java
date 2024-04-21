package view;

import business.BrandManager;
import entities.Brand;
import entities.Users;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
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

        loadBrandTable();
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
        brandView.addWindowListener(new WindowAdapter() { //markaları güncelledikten sonra tabloyu yenile
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                loadBrandTable();
            }
        });
        });

        this.jBrandMenu.add("Güncelle").addActionListener(e -> {
                int selectBrandId = Integer.parseInt(tbl_brand.getValueAt(tbl_brand.getSelectedRow(),0).toString());
                BrandView brandView = new BrandView(this.brandManager.getById(selectBrandId));
            brandView.addWindowListener(new WindowAdapter() { //markaları güncelledikten sonra tabloyu yenile
                @Override
                public void windowClosed(WindowEvent e) {
                    super.windowClosed(e);
                    loadBrandTable();
                }
            });
        });
        this.jBrandMenu.add("Sil");

        this.tbl_brand.setComponentPopupMenu(jBrandMenu);
    }

    public void loadBrandTable() //colonları ve satırları listelemek için
    {
        Object[] col_brand = {"Marka ID", "Marka Adı"};              //Brand için kolonlar
        ArrayList<Object[]> brandArrayList = this.brandManager.getForTable(col_brand.length);   //barand managerdan dönen brand verileri listeye atadık.
        this.createTable(this.tmdl_brand,tbl_brand,col_brand,brandArrayList);
    }
}
