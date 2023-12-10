package TransferDana;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.sql.*;

import env.env;

public class TransferDana extends JDialog {
    private JPanel panel;
    private JTextField textFieldNoRekeningTujuan;
    private JTextField textFieldNominal;
    private JPasswordField pinField;
    private JButton submitButton;
    private JButton cancelButton;
    public String NoRekening;
    private String TargetNo;
    private static final env env=new env();
    public TransferDana(){
        JFrame frame=new JFrame();
        setResizable(false);
        setContentPane(panel);
        setMinimumSize(new Dimension(400,250));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(frame);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
        submitButton.addComponentListener(new ComponentAdapter() {
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transferDanaToTargetNoRekening();
            }
        });
    }
    private void transferDanaToTargetNoRekening(){
        String PIN=String.valueOf(pinField.getPassword());
        String NoRekeningTarget=textFieldNoRekeningTujuan.getText();
        String nominalForTransfer=textFieldNominal.getText();

        if (NoRekeningTarget.isEmpty()||nominalForTransfer.isEmpty()||PIN.isEmpty()){
            JOptionPane.showMessageDialog(this,"Form not complete","Information",JOptionPane.WARNING_MESSAGE);
            return;
        }
        try{
            Connection connection = DriverManager.getConnection(env.DB_URL,env.USERNAME, env.PASSWORD);
            String Query_Check="SELECT no_rekening FROM saldo WHERE no_rekening=?";
            PreparedStatement preparedStatement=connection.prepareStatement(Query_Check);
            preparedStatement.setInt(1,Integer.parseInt(NoRekeningTarget));

            ResultSet resultSet=preparedStatement.executeQuery();
            if (resultSet.next()){
                try{

                    String QUERY_CHECK="SELECT no_rekening,PIN FROM nasabah WHERE no_rekening=? AND PIN=? ";
                    PreparedStatement preparedStatementCheck=connection.prepareStatement(QUERY_CHECK);
                    preparedStatementCheck.setInt(1,Integer.parseInt(NoRekening));
                    preparedStatementCheck.setInt(2,Integer.parseInt(PIN));
                    ResultSet resultSetCheck=preparedStatement.executeQuery();

                    if (resultSetCheck.next()){
                        try{
                            String QUERY_CHECK_SALDO="SELECT no_rekening,saldo FROM saldo WHERE no_rekening=?";
                            PreparedStatement preparedStatementSaldo=connection.prepareStatement(QUERY_CHECK_SALDO);
                            preparedStatementSaldo.setInt(1,Integer.parseInt(NoRekening));
                            ResultSet resultSet_Saldo=preparedStatementSaldo.executeQuery();

                            if (resultSet_Saldo.next()){
                                double nominalForTarget=Double.parseDouble(nominalForTransfer);
                                int saldo=Integer.parseInt(resultSet_Saldo.getString("saldo"));
                                double resultSaldoDecrease=saldo-nominalForTarget;

                                if (saldo<=15000) {
                                    JOptionPane.showMessageDialog(null, "Saldo di bank terkena limit penarikan", "Information", JOptionPane.WARNING_MESSAGE);
                                    return;
                                }
                                if(nominalForTarget >= saldo){
                                    JOptionPane.showMessageDialog(null, "Saldo di bank kamu tidak cukup", "Information", JOptionPane.WARNING_MESSAGE);
                                    return;
                                }

                                try{
                                    connection.createStatement();
                                    String SQL_QUERY_UPDATE="UPDATE saldo SET saldo=? WHERE no_rekening=?";
                                    PreparedStatement preparedStatementUpdate=connection.prepareStatement(SQL_QUERY_UPDATE);
                                    preparedStatementUpdate.setDouble(1, resultSaldoDecrease);
                                    preparedStatementUpdate.setInt(2,Integer.parseInt(NoRekening));
                                    preparedStatementUpdate.executeUpdate();
                                    JOptionPane.showMessageDialog(this,"Nominal uang yang kamu transfer adalah Rp "+nominalForTransfer);

                                    try{
                                        String QUERY_CHECK_SALDO_TARGET="SELECT no_rekening,saldo FROM saldo WHERE no_rekening=?";
                                        PreparedStatement preparedStatementSaldoTarget=connection.prepareStatement(QUERY_CHECK_SALDO_TARGET);
                                        preparedStatementSaldoTarget.setInt(1,Integer.parseInt(NoRekeningTarget));
                                        ResultSet resultSetSaldoTarget=preparedStatementSaldoTarget.executeQuery();

                                        if (resultSetSaldoTarget.next()){
                                            int saldoTarget=Integer.parseInt(resultSetSaldoTarget.getString("saldo"));
                                            double resultSaldoIncrease=saldoTarget+nominalForTarget;

                                            try{
                                                Statement statementTarget=connection.createStatement();
                                                String SQL_QUERY_UPDATE_TO_TARGET_NOREKENING="UPDATE saldo SET saldo=? WHERE no_rekening=?";
                                                PreparedStatement preparedStatementUpdateTarget=connection.prepareStatement(SQL_QUERY_UPDATE_TO_TARGET_NOREKENING);
                                                preparedStatementUpdateTarget.setDouble(1, resultSaldoIncrease);
                                                preparedStatementUpdateTarget.setInt(2,Integer.parseInt(NoRekeningTarget));
                                                preparedStatementUpdateTarget.executeUpdate();
                                                statementTarget.close();
                                                preparedStatementUpdateTarget.close();
                                                dispose();
                                            }
                                            catch (Exception e){
                                                e.getStackTrace();
                                            }
                                        }
                                    }
                                    catch (Exception e){
                                        e.getStackTrace();
                                    }
                                }
                                catch(Exception e){
                                    e.getStackTrace();
                                }
                            }
                        }
                        catch (Exception e){
                            e.getStackTrace();
                        }
                    }
                }
                catch (Exception e){
                    e.getStackTrace();
                }
            }
            else {
                JOptionPane.showMessageDialog(null,"Target no rekening does not exist","Information",JOptionPane.WARNING_MESSAGE);
            }
        }
        catch (Exception e){
            e.getStackTrace();
        }


    }

}

