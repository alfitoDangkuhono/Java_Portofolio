package Update_PIN;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

import env.env;

public class Update_PIN extends JDialog {
    private JPanel update_Panel;
    private JTextField textField_PINBaru;
    private JPasswordField passwordField_ConfirmPIN;
    private JButton cancelButton;
    private JButton saveButton;
    private JTextField txtPINLama;
    private final env env=new env();
    public String NoRekening;


    public  Update_PIN(){
      JFrame frame=new JFrame();
      frame.setResizable(false);
      setContentPane(update_Panel);
      setMinimumSize(new Dimension(450,344));
      setTitle("Form Change PIN");
      setLocationRelativeTo(frame);
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        saveButton.addActionListener(e -> save_PIN());

        cancelButton.addActionListener(e -> dispose());
        setVisible(true);
    }

    private void save_PIN()  {
        String pin_Lama=txtPINLama.getText();
        String pin_Baru=textField_PINBaru.getText();
        String confirm_PIN=String.valueOf(passwordField_ConfirmPIN.getPassword());

        if (pin_Lama.isEmpty()||pin_Baru.isEmpty()||confirm_PIN.isEmpty()){
            JOptionPane.showMessageDialog(this,"Form not complete","Error Message",JOptionPane.WARNING_MESSAGE);
            return ;
        }
        if (!pin_Baru.equals(confirm_PIN)){
            JOptionPane.showMessageDialog(this,"New PIN is not suitable with PIN confirm","Warning message",JOptionPane.WARNING_MESSAGE);
            return ;
        }
        if (pin_Lama.length() >6){
            JOptionPane.showMessageDialog(this,"Amount PIN excessive","Warning Message",JOptionPane.WARNING_MESSAGE);
            return;
        } else if (pin_Lama.length() <6) {
            JOptionPane.showMessageDialog(this,"Amount PIN not enough","Warning Message",JOptionPane.WARNING_MESSAGE);
            return;
        }
        try{
            Connection connection= DriverManager.getConnection(env.DB_URL,env.USERNAME,env.PASSWORD);
            String SQL_Query="SELECT no_rekening,PIN FROM nasabah WHERE no_rekening=? AND PIN=?";
            PreparedStatement preparedStatement =connection.prepareStatement(SQL_Query);
            preparedStatement.setInt(1, Integer.parseInt(String.valueOf(NoRekening)));
            preparedStatement.setInt(2, Integer.parseInt(pin_Lama));

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                System.out.println("Update success");
                JOptionPane.showMessageDialog(this,"PIN has changing","Success Message",JOptionPane.INFORMATION_MESSAGE);

                try{
                    Statement statement=connection.createStatement();
                    String SQL_QUERY_UPDATE="UPDATE nasabah SET PIN=? WHERE no_rekening=?";
                    PreparedStatement preparedStatementUpdate=connection.prepareStatement(SQL_QUERY_UPDATE);
                    preparedStatementUpdate.setInt(1, Integer.parseInt(pin_Baru));
                    preparedStatementUpdate.setInt(2, Integer.parseInt(NoRekening));
                    preparedStatementUpdate.executeUpdate();
                    statement.close();
                    dispose();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            else {
                JOptionPane.showMessageDialog(null,"Method Failed", "Error Message",JOptionPane.ERROR_MESSAGE);
                return;
            }
            connection.close();
            preparedStatement.close();
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
