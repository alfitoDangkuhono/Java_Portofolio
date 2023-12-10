package Admin;

import javax.swing.*;
import java.awt.*;
import Register.Register;
import Deposit.Deposit;
import User.User;
import Menu.Login;

public class Admin extends JFrame{
    private JPanel panel;
    private JButton btnRegister;
    private JButton depositAwalButton;
    private JButton exitButton;

    public Admin(){
        setContentPane(panel);
        setMinimumSize(new Dimension(400,430));
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnRegister.addActionListener(e -> {
            Register register=new Register();
            User user=register.user;
            if (user != null){
                System.out.println("Registrasi Berhasil Dilakukan "+user.nama);
            }
        });
        depositAwalButton.addActionListener(e -> new Deposit());
        exitButton.addActionListener(e -> {
            int input=JOptionPane.showConfirmDialog(null,"are you sure want to exit?","exit",JOptionPane.YES_NO_OPTION);
            if (input==0){
                dispose();
                new Login();
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        new Admin();
    }
}
