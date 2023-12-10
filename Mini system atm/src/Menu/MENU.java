package Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import TarikTunai.TarikTunai;
import TransferDana.TransferDana;
import Update_PIN.*;
import data.CekSaldo;
import depo.Depo;

public class MENU extends JFrame{
    private JButton btnCekSaldo;
    private JPanel panelMenu;
    private JButton btnExit;
    private JButton btnTarikTunai;
    private JButton btnTransferDana;
    private JButton btnGantiPIN;
    public  JLabel label;
    private JButton depositButton;

    public  MENU(){
        setContentPane(panelMenu);
        setMinimumSize(new Dimension(500,400));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        label.setVisible(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnGantiPIN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Update_PIN updatePin=new Update_PIN();
                updatePin.NoRekening=label.getText();
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int input=JOptionPane.showConfirmDialog(null,"are you sure want to exit?","Exit",JOptionPane.YES_NO_OPTION);
                if (input==0){
                    dispose();
                    new Login();
                }
            }
        });
        setVisible(true);

        btnCekSaldo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CekSaldo cekSaldo=new CekSaldo();
                cekSaldo.NoRekening=label.getText();
                cekSaldo.checkingSaldo();
            }
        });
        btnTarikTunai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TarikTunai tarikTunai=new TarikTunai();
                tarikTunai.NoRekening= label.getText();
            }
        });
        btnTransferDana.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 TransferDana transferDana=new TransferDana();
                 transferDana.NoRekening=label.getText();

            }
        });
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Depo depo=new Depo();
                depo.NoRekening=label.getText();
            }
        });


    }

}