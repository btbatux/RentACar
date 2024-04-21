package core;

import javax.swing.*;
import java.awt.*;

public class Helper {
    public static void showMsg(String str) {
        String msg;
        String title;

        switch (str) {
            case "fill":  //Eğer tüm alanlar boş ise bu mesajı göster
                msg = "Boş alanları doldurun";
                title = "Uyarı";
                break;
            case "done":  //Eğer tüm alanlar dolu ise bu mesajı göster
                msg = "Başarılı";
                title = "Sonuç";
                break;
            case "notFound":
                msg = "Kayıt bulunamadı.";
                title = "Sonuç";
                break;
            case "error":
                msg = "Hata Alındı.";
                title = "Hata!";
            default:  //Varsayılan mesaj
                msg = str;
                title = "Mesaj";
        }
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }


    public static boolean isFieldEmpty(JTextField jTextField) {
        return jTextField.getText().trim().isEmpty(); //userName ve Password boş mu kontrol et.
    }

    public static boolean isFieldListEmpty(JTextField[] checkFieldList) {
        for (JTextField jTextField : checkFieldList) { //Username ve Password kutularını dön ve isFieldEmpty metoduna gönder
            if (isFieldEmpty(jTextField)) { //alan boş ise true gönder.
                return true;
            }
        }
        return false;
    }

    public static int getLocationPoint(String type, Dimension size) {
        return switch (type) {
            case "x" ->
                    (Toolkit.getDefaultToolkit().getScreenSize().width - size.getSize().width) / 2; //ekranın tam orda genişlik noktası
            case "y" ->
                    (Toolkit.getDefaultToolkit().getScreenSize().height - size.getSize().height) / 2; //ekranın tam orda genişlik noktası
            default -> 0;
        };


    }
}
