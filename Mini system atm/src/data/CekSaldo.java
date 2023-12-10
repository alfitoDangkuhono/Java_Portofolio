package data;

import env.env;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CekSaldo {
    private static final env env=new env();
    public String NoRekening;
    public void  checkingSaldo(){
        try{
            Connection connection = DriverManager.getConnection(env.DB_URL, env.USERNAME, env.PASSWORD);
            String QUERY_CHECK="SELECT no_rekening,saldo FROM saldo WHERE no_rekening=? ";
            PreparedStatement preparedStatement=connection.prepareStatement(QUERY_CHECK);
            preparedStatement.setInt(1,Integer.parseInt(NoRekening));
            ResultSet resultSet=preparedStatement.executeQuery();
            if (resultSet.next()){
                JOptionPane.showMessageDialog(null,"Saldo bank kamu adalah Rp "+resultSet.getString("saldo") ,"info",JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch (Exception e){
            e.getStackTrace();
        }
    }
}
