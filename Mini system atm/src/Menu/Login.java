package Menu;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

import Admin.Admin;
import env.env;

public class Login extends JFrame {
    private JPanel panel;
    public  JTextField textFieldNo_Rekening;
    private JButton submitButton;
    private JButton cancelButton;
    private final env env=new env();

    public Login(){
        setContentPane(panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(400,500));
        submitButton.addActionListener(e ->
                validateLogin()
        );
        cancelButton.addActionListener(e -> System.exit(0));
        setVisible(true);
    }

    private void validateLogin() {
        String no_rekening=textFieldNo_Rekening.getText();
        if (no_rekening.isEmpty()){
            JOptionPane.showMessageDialog(null,"Please completed form in above","Information Message",JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (no_rekening.equals("admin")){
            dispose();
            new Admin();
            return;
        }
        try{
            Connection connection= DriverManager.getConnection(env.DB_URL,env.USERNAME,env.PASSWORD);
            String Query_Check="SELECT no_rekening FROM nasabah WHERE no_rekening=?";
            PreparedStatement preparedStatement=connection.prepareStatement(Query_Check);
            preparedStatement.setInt(1,Integer.parseInt(no_rekening));

            ResultSet resultSet=preparedStatement.executeQuery();

            if (resultSet.next()){
                dispose();
                JOptionPane.showMessageDialog(this,"Validate is succes ","Succes",JOptionPane.INFORMATION_MESSAGE);
                MENU menu=new MENU();
                menu.label.setText(textFieldNo_Rekening.getText());
                menu.setTitle(textFieldNo_Rekening.getText());
            }
            else {
                JOptionPane.showMessageDialog(null,"Validate is Failed ","Failed",JOptionPane.ERROR_MESSAGE);
            }
            connection.close();
            preparedStatement.close();
        }
        catch (Exception e){
            e.getStackTrace();
        }
    }
    public static void main(String[] args) {
        new Login();
    }

}

