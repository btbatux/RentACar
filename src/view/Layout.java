package view;

import core.Helper;

import javax.swing.*;

public class Layout extends JFrame {

    public void guiInitilaze(int widhth,int heigth)
    {
        //BU sınıfı miras alan viewler bu
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(widhth, heigth);
        //pencere tam merkezde açılması için x,y lokasyonları.
        this.setLocation(Helper.getLocationPoint("x", this.getSize()), Helper.getLocationPoint("y", this.getSize()));
        this.setVisible(true);
    }
}
