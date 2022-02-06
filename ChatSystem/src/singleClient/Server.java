package singleClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class Server extends JFrame implements ActionListener{
	
	JPanel p1;
	JTextField t1;
	JButton b1;
	static JTextArea a1;
	
	static ServerSocket skt;
	static Socket s;
	static DataInputStream din;
    static DataOutputStream dout;
	
	Server(){
		
		//Green Panel on Top
		p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(7, 94, 84));
        p1.setBounds(0, 0, 350, 55);
        add(p1);
        
        //Username
        JLabel l1 = new JLabel("Client");
        l1.setFont(new Font("SAN_SERIF", Font.BOLD, 16));
        l1.setForeground(Color.WHITE);
        l1.setBounds(5, 12, 100, 18);
        p1.add(l1);   

        //User Status
        JLabel l2 = new JLabel("Active");
        l2.setFont(new Font("SAN_SERIF", Font.PLAIN, 10));
        l2.setForeground(Color.WHITE);
        l2.setBounds(5, 28, 100, 20);
        p1.add(l2);   
        
        //Chats Area
        a1 = new JTextArea();
        a1.setBounds(5, 60, 325, 455);
        a1.setBackground(Color.PINK);
        a1.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
        a1.setEditable(false);
        a1.setLineWrap(true);
        a1.setWrapStyleWord(true);
        add(a1);
        
        //Msg Typing Area
        t1 = new JTextField();
        t1.setBounds(5, 520, 240, 30);
        t1.setFont(new Font("SAN_SERIF", Font.PLAIN, 12));
        add(t1);

        //Send Button
        b1 = new JButton("Send");
        b1.setBounds(245, 520, 85, 30);
        b1.setBackground(new Color(7, 94, 84));
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("SAN_SERIF", Font.PLAIN, 12));
        b1.addActionListener(this);
        add(b1);
		
		getContentPane().setBackground(Color.WHITE);
	       setLayout(null);
	       setSize(350, 600);
	       setLocation(200, 130);
	       setVisible(true);
	       
	       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
		String out = t1.getText();
		a1.setText(a1.getText() +"\n\t\t"+ out);
		dout.writeUTF(out);
		t1.setText("");
		}
		catch(Exception exp) {}
	}
	
	public static void main (String[] args) {
		new Server().setVisible(true);
		
		String msgInput = "";
		try {
			skt = new ServerSocket(4848);
			s = skt.accept();
			din = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream(s.getOutputStream());
			
			while(true) {
				msgInput = din.readUTF();
				a1.setText(a1.getText() +"\n"+ msgInput);
			}
			
		}
		catch(Exception e) {
			
		}
	}

}
