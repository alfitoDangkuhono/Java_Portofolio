package depo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import env.env;

public class Depo extends JDialog{
    private JPanel panel;
    private JTextField textFieldNominal;
    private JButton cancelButton;
    private JButton submitButton;
    private static final env env=new env();
    public String NoRekening;

    public Depo(){
        setContentPane(panel);
        setMinimumSize(new Dimension(450,450));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JFrame frame=new JFrame();
        setLocationRelativeTo(frame);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                depo();
            }
        });
    }
    private void depo(){
        try {
            Connection connection= DriverManager.getConnection(env.DB_URL, env.USERNAME,env.PASSWORD);
            String QUERY_CHECK_SALDO="SELECT no_rekening,saldo FROM saldo WHERE no_rekening=?  ";
            PreparedStatement preparedStatementSaldo=connection.prepareStatement(QUERY_CHECK_SALDO);
            preparedStatementSaldo.setInt(1,Integer.parseInt(NoRekening));
            ResultSet resultSet=preparedStatementSaldo.executeQuery();
            if (resultSet.next()){
                int Nominal=Integer.parseInt(textFieldNominal.getText());
                int saldo=Integer.parseInt(resultSet.getString("saldo"));

                int value= saldo+Nominal;
                try{
                    Statement statement=connection.createStatement();
                    String SQL_QUERY_UPDATE="UPDATE saldo SET saldo=? WHERE no_rekening=?";
                    PreparedStatement preparedStatement=connection.prepareStatement(SQL_QUERY_UPDATE);
                    preparedStatement.setDouble(1, value);
                    preparedStatement.setInt(2,Integer.parseInt(NoRekening));
                    preparedStatement.executeUpdate();
                    statement.close();
                    preparedStatement.close();
                    JOptionPane.showMessageDialog(this,"Deposit successful","Info",JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
                catch (Exception e){
                    e.getStackTrace();
                }
            }
            else {
                System.out.println("this method not working brooo");
            }
        }
        catch (Exception e){
            e.getStackTrace();
        }


    }
}
