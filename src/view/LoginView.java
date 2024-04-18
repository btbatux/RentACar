package view;

import business.UserManager;
import core.Helper;
import entities.Users;

import javax.swing.*;


public class LoginView extends JFrame {
    private JPanel container;
    private JPanel w_top;
    private JLabel lbl_welcome;
    private JLabel lbl_welcome2;
    private JPanel w_bottom;
    private JTextField fld_username;
    private JTextField fld_pass;
    private JButton btn_login;
    private JLabel lbl_username;
    private JLabel lbl_pass;
    private final UserManager userManager;


    public LoginView() {
        this.userManager = new UserManager();

        this.add(container);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Rent A Car");
        this.setSize(400, 500);


        this.setLocation(Helper.getLocationPoint("x", this.getSize()), Helper.getLocationPoint("y", this.getSize())); //pencere tam merkezde açılması için x,y lokasyonları.
        this.setVisible(true);

        btn_login.addActionListener(e -> {
            JTextField[] checkFieldList = {this.fld_username, this.fld_pass};
            if (Helper.isFieldListEmpty(checkFieldList)) //Username field boş ise;
            {
                Helper.showMsg("fill"); //tamamı boşsa tüm alanları doldurun mesajı
            } else { //tüm alanlar dolu ise verileri userManager'e gönder geriy dönen değeri al.
                Users loginUser = this.userManager.findByLogin(this.fld_username.getText(), this.fld_pass.getText());
                if (loginUser == null) { //eğer user yok ise kayıt bulunamadı hatası
                    Helper.showMsg("notFound");
                }else{
                    System.out.println(loginUser.toString());
                }
            }
        });
    }
}
