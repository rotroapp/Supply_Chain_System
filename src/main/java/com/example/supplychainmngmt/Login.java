package com.example.supplychainmngmt;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {

    DatabaseConnection dbconn= new DatabaseConnection();

    public static byte[] getSHA(String input)
    {
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return md.digest(input.getBytes(StandardCharsets.UTF_8));
        }catch (Exception e){

            e.printStackTrace();
        }

        return null;
    }

    static String getEncryptedPassword(String password)
    {
        String encryptedpassword = "";
        BigInteger number = new BigInteger(1,getSHA(password));
        StringBuilder hexString = new StringBuilder(number.toString(16));
        encryptedpassword = hexString.toString();
        return encryptedpassword;
    }
    public boolean customerLogin(String email, String password){

        String Password = getEncryptedPassword(password);
        try{
        String query = String.format("SELECT * FROM customer WHERE email = '%s' and password = '%s'",email,Password);
        ResultSet rs = dbconn.getQueryTable(query);
        if(rs.next()){
            return true;
        }
        else{
            return false;
        }}catch (Exception e){

            e.printStackTrace();
            return false;

        }
    }

    public boolean customerSignup(String email, String password){
        String Password = getEncryptedPassword(password);
        String addon = String.format("INSERT INTO customer(email,password)" + "VALUES ('%s', '%s' )",email,Password);

        try{

            dbconn.setQueryTable(addon);

            return true;
            }catch (Exception e){

            e.printStackTrace();

            return false;

        }

    }
//    public static void main(String[] args) {
//        System.out.print(getEncryptedPasswor("graja"));
//    }
}
