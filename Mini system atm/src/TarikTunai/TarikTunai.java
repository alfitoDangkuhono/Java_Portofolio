package TarikTunai;

import env.env;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class TarikTunai extends JDialog{
    private JPanel panel;
    private JTextField textFieldNominal;
    private JButton cancelButton;
    private JButton submitButton;
    public String NoRekening;
    private static final env env=new env();
    public TarikTunai (){
        JFrame frame=new JFrame();
        setContentPane(panel);
        setMinimumSize(new Dimension(400,300));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(frame);
        cancelButton.addActionListener(e -> dispose());
        setVisible(true);
        submitButton.addActionListener(e -> gettingMoneyFromDatabase());
    }
    private void gettingMoneyFromDatabase() {
        String textFieldGettingMoneyForUser=textFieldNominal.getText();
        if (textFieldGettingMoneyForUser.isEmpty()){
            JOptionPane.showMessageDialog(null,"Form not complete","Completely form",JOptionPane.WARNING_MESSAGE);
        }
        else {
            try{
                Connection connection = DriverManager.getConnection(env.DB_URL,env.USERNAME, env.PASSWORD);
                String QUERY_CHECK="SELECT no_rekening,saldo FROM saldo WHERE no_rekening=? ";
                PreparedStatement preparedStatement=connection.prepareStatement(QUERY_CHECK);
                preparedStatement.setInt(1,Integer.parseInt(NoRekening));
                ResultSet resultSet=preparedStatement.executeQuery();

                if (resultSet.next()){
                    int value= Integer.parseInt(resultSet.getString("saldo"));
                    double resultSaldo=value-Double.parseDouble(textFieldNominal.getText());
                    int declareMoneyFromUser= Integer.parseInt(textFieldNominal.getText());

                    if (value <= 15000){
                        JOptionPane.showMessageDialog(null,"Saldo di bank terkena limit penarikan","Information",JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    if(Integer.parseInt(textFieldGettingMoneyForUser) >= value){
                        JOptionPane.showMessageDialog(null, "Saldo kamu tidak cukup", "Information", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    else {
                        try{
                            Statement statement=connection.createStatement();
                            String SQL_QUERY_UPDATE="UPDATE saldo SET saldo=? WHERE no_rekening=?";
                            PreparedStatement preparedStatementUpdate=connection.prepareStatement(SQL_QUERY_UPDATE);
                            preparedStatementUpdate.setDouble(1, resultSaldo);
                            preparedStatementUpdate.setString(2,NoRekening);
                            preparedStatementUpdate.executeUpdate();
                            statement.close();
                            JOptionPane.showMessageDialog(this,"Nominal uang yang kamu ambil adalah Rp"+declareMoneyFromUser+" Sisa saldo kamu dibank adalah Rp"+resultSaldo);
                            dispose();
                        }
                        catch (Exception e){
                            e.getStackTrace();
                        }
                    }
                }
                else {
                    System.out.println("this method not working");
                }
                preparedStatement.close();
                connection.close();
            }
            catch (Exception e){
              e.getStackTrace();
            }
        }
    }
}
