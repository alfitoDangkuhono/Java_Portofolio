package Register;

import javax.swing.*;
import java.awt.*;

import java.sql.*;
import java.util.Random;
import env.env;
import User.*;

public class Register extends JDialog {

    private JTextField textFieldName;
    private JTextField textFieldEmail;
    private JTextField textFieldPhone;
    private JTextField textFieldPIN;
    private JPasswordField textFieldConfirmPIN;
    private JButton registrasiButton;
    private JButton cancelButton;
    private JPanel RegisPanel;
    private JTextField textFieldAlamat;

    public Register(){
        JFrame frame=new JFrame();
        setTitle("New Account");
        setContentPane(RegisPanel);
        setMinimumSize(new Dimension(450,344));
        setModal(true);
        setLocationRelativeTo(frame);
        frame.setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        registrasiButton.addActionListener(e -> registerUser());
        cancelButton.addActionListener(e -> {
          dispose();
          System.out.println("Registrasi Di Batalkan");
        });
        setVisible(true);
    }
    private long no_Rekening(){
        return new Random().nextLong(100000000L);
    }

    private void registerUser () {
        int no_rekening= Math.toIntExact(no_Rekening());
        String nama=textFieldName.getText();
        String email=textFieldEmail.getText();
        String phone=textFieldPhone.getText();
        String PIN= textFieldPIN.getText();
        String alamat=textFieldAlamat.getText();
        String confirmPIN= String.valueOf(textFieldConfirmPIN.getPassword());

        if (nama.isEmpty()||email.isEmpty()||phone.isEmpty()||PIN.isEmpty()||alamat.isEmpty()){
            JOptionPane.showMessageDialog(this,"Form belum lengkap","Error Message ",JOptionPane.ERROR_MESSAGE );
            return;
        }
        if (!PIN.equals(confirmPIN)){
            JOptionPane.showMessageDialog(this,"PIN tidak cocok dengan PIN diatas","Error Message",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (PIN.length()<6){
            JOptionPane.showMessageDialog(this,"Jumlah PIN Kurang","Error Message",JOptionPane.WARNING_MESSAGE);
            return;
        } else if (PIN.length()>6) {
            JOptionPane.showMessageDialog(this,"Jumlah PIN Berlebihan","Error Message",JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            JOptionPane.showMessageDialog(null,"Berhasil ditambahkan","Suksess",JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
        user = tambahUserKeDatabase( no_rekening ,nama, email, Integer.valueOf(phone),Integer.valueOf(PIN),alamat);
        if (user !=null){
            dispose();
        }
    }
    public User user;

    private User tambahUserKeDatabase(long no_rekening, String nama, String email, Integer phone, Integer PIN,  String alamat){
        User user=null;
        env env=new env();

        try{
            Connection connection= DriverManager.getConnection(env.DB_URL,env.USERNAME,env.PASSWORD);
            Statement statement=connection.createStatement();
            String sql_Query="INSERT INTO nasabah(no_rekening,nama,email,phone,PIN,alamat)"+"VALUES(?,?,?,?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(sql_Query);
            preparedStatement.setInt(1, (int) no_rekening);
            preparedStatement.setString(2,nama);
            preparedStatement.setString(3,email);
            preparedStatement.setString(4,String.valueOf(phone));
            preparedStatement.setString(5, String.valueOf(PIN));
            preparedStatement.setString(6,alamat);

            int addedRows=preparedStatement.executeUpdate();
            if (addedRows>0){
                user =new User();
                user.no_rekening=no_rekening;
                user.nama=nama;
                user.email=email;
                user.phone=phone;
                user.PIN= PIN;
                user.alamat=alamat;
            }
            statement.close();
            connection.close();
        }
        catch (Exception e){
            e.getStackTrace();
        }
        return user;
    }
}
