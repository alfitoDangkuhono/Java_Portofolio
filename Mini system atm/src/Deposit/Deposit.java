package Deposit;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import env.env;

public class Deposit extends JDialog{
    private JPanel panel;
    private JButton loginBtn;
    private JButton cancelBtn;
    private JTextField textFieldDeposit;
    public JTextField textFieldNoRekening;
    public JLabel label;

    private static final env env=new env();

    public Deposit(){
        JFrame frame=new JFrame();
        setTitle("Validate Deposit");
        setContentPane(panel);
        setMinimumSize(new Dimension(450,344));
        setModal(true);
        setLocationRelativeTo(frame);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        cancelBtn.addActionListener(e -> cancelBtn());
        loginBtn.addActionListener(e -> validasi());
        setVisible(true);
        textFieldNoRekening.setEnabled(false);
    }

    private  void cancelBtn(){
        dispose();
    }

    private void  validasi() {
        String No_Rekening=textFieldNoRekening.getText();
        String deposit=textFieldDeposit.getText();

        if (No_Rekening.isEmpty()||deposit.isEmpty()){
            JOptionPane.showMessageDialog(this,"Form Belum Lengkap","Info",JOptionPane.WARNING_MESSAGE);
        }

        else {
            try {
                Connection connection = DriverManager.getConnection(env.DB_URL, env.USERNAME, env.PASSWORD);
                String QUERY_CHECK="SELECT no_rekening FROM nasabah WHERE no_rekening=?";
                PreparedStatement preparedStatement=connection.prepareStatement(QUERY_CHECK);
                preparedStatement.setInt(1,Integer.parseInt(No_Rekening));

                ResultSet resultSet=preparedStatement.executeQuery();
                if (resultSet.next()){
                    validateNo_RekeningOnDatabase();
                }
                else {
                    JOptionPane.showMessageDialog(this,"No Rekening tidak valid","Error",JOptionPane.WARNING_MESSAGE);
                }
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    private void validateNo_RekeningOnDatabase(){
        String No_Rekening=textFieldNoRekening.getText();
        try{
            Connection connection= DriverManager.getConnection(env.DB_URL,env.USERNAME,env.PASSWORD);
            Statement statement=connection.createStatement();
            String sql_Query="SELECT no_rekening FROM saldo WHERE no_rekening=?";
            PreparedStatement preparedStatement=connection.prepareStatement(sql_Query);
            preparedStatement.setInt(1, Integer.parseInt(No_Rekening));
            ResultSet resultSet=preparedStatement.executeQuery();

            if(resultSet.next()) {
                JOptionPane.showMessageDialog(null,"No rekening has exist in Database","Checking no rekening in Database",JOptionPane.WARNING_MESSAGE);
            }
            else {
                deposit();
                dispose();
                System.out.println("Validate and method successful");
                JOptionPane.showMessageDialog(this,"Validate and Deposit successful","Success",JOptionPane.INFORMATION_MESSAGE);
            }
            statement.close();
            connection.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    private void deposit() {
        String No_Rekening=String.valueOf(textFieldNoRekening.getText());
        String Money=textFieldDeposit.getText();

        try{
            Connection connection= DriverManager.getConnection(env.DB_URL,env.USERNAME,env.PASSWORD);
            Statement statement=connection.createStatement();
            String sql_Query="INSERT INTO saldo (no_rekening,saldo)"+"VALUES(?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(sql_Query);
            preparedStatement.setInt(1, Integer.parseInt(No_Rekening));
            preparedStatement.setString(2, Money);

            preparedStatement.executeUpdate();
            statement.close();
            connection.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
