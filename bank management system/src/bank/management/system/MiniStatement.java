
package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
public class MiniStatement extends JFrame{

    JLabel bank,mini,card,balance;
    MiniStatement(String pinnumber){
        setTitle("Mini Statement");
        
        setLayout(null);
        
        mini=new JLabel();
        mini.setBounds(20, 140, 400, 200);
        //text.setForeground(Color.WHITE);
        //text.setFont(new Font("System",Font.BOLD,16));
        add(mini);
        
         bank=new JLabel(" VDS BANK ");
        bank.setBounds(150, 20, 100, 20);
        bank.setForeground(Color.BLACK);
        bank.setFont(new Font("System",Font.BOLD,16));
        add(bank);
        
        balance=new JLabel();
        balance.setBounds(20,400,300,20);
        add(balance);
        
        card=new JLabel();
        card.setBounds(20, 80, 300, 20);
        card.setForeground(Color.BLACK);
        card.setFont(new Font("System",Font.BOLD,16));
        add(card);
        
        try{
            Conn conn=new Conn();
            ResultSet rs= conn.s.executeQuery("select * from login where pin_number ='"+pinnumber+"'");
            while(rs.next()){
                card.setText("Card Number"+rs.getString("card_number").substring(0,4)+"XXXXXXXX"+rs.getString("card_number").substring(12));
            }
        }catch (Exception e){
            System.out.println(e);
        }
        
        try{
            Conn conn=new Conn();
            int bal=0;
            ResultSet rs= conn.s.executeQuery("select * from bank where pin ='"+pinnumber+"'");
            while(rs.next()){
               mini.setText(mini.getText()+"<html>"+ rs.getString("date") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("amount")+"<br><br><html>"); 
               
               if(rs.getString("type").equals("Deposit")){
                            bal += Integer.parseInt(rs.getString("amount"));
                        }else{
                            bal -= Integer.parseInt(rs.getString("amount"));
                        }
            }
            balance.setText("your Current Account Balance is Rs " + bal);
        }catch(Exception e){
            System.out.println(e);
        }
        
        
        setSize(400,600);
        setLocation(20,20);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }
   
    public static void main(String[] args) {
        new MiniStatement("");
    }
    
}
