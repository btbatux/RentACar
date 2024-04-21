package view;

import business.BrandManager;
import core.Helper;
import entities.Brand;

import javax.swing.*;

public class BrandView extends Layout {
    private JPanel container;
    private JLabel lbl_brand;
    private JLabel lbl_bran_name;
    private JTextField fld_brand_name;
    private JButton btn_brand_save;
    private Brand brand;
    private BrandManager brandManager;

    public BrandView(Brand brand) {
        this.brandManager = new BrandManager();
        this.add(container);
        this.guiInitilaze(300, 200);
        this.brand = brand;


        if(brand != null)
        {
            fld_brand_name.setText(brand.getName()); // Güncellenmek istenen markanın textini fielda yazdır
        }


        btn_brand_save.addActionListener(e -> {
            if (Helper.isFieldEmpty(this.fld_brand_name)) {
                Helper.showMsg("fill"); //Alan boş ise uyar
            }
            else {
                boolean result = true;
                if (this.brand == null) { // null ise insert işlemi yap
                    Brand obj = new Brand(fld_brand_name.getText());
                    result = this.brandManager.save(obj);
                }
                else { //null değilse update işlemi yap
                    this.brand.setName(fld_brand_name.getText());
                    result = this.brandManager.update(this.brand);
                }
                if (result) {
                    Helper.showMsg("done");
                } else {
                    Helper.showMsg("error");
                }
            }
        });
    }
}
