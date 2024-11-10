package chatting_app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.border.EmptyBorder;




public class Client implements ActionListener{ // JFrame is the main Frame of the application.
    JTextField chat;
    static JPanel chatbox ;
    static Box vertical = Box.createVerticalBox(); // this is will help the messages to load below other message one by one whenever you send
    static DataOutputStream dout;
    static JFrame f = new JFrame();
    Client(){ // this is the constructor 
        f.setSize(400,700); // here we are setting the size of the frame using w and h .
        f.setLocation(800,80); // by deafult whenever we execute the main method we get the frame on the top left corner and now we are setting it to our new loc  
        f.setLayout(null);//
        f.getContentPane().setBackground(Color.WHITE);// get content pane method catches full frame to use  and set background color is to set the background color of the frame . 
        

        // this panel is to show the info about the person..
        JPanel p1 = new JPanel(); // JPanel is used to set the panels inside the frame.
        p1.setBackground(new Color(23,23,23));//we have set a new color to it if we use (color.white) then we can select just the color but if we select the method using (new color ()) we can select the rgb of the color 
        p1.setBounds(0,0,400,60);// this is to set the jpanel size into the frame
        p1.setLayout(null);
        f.add(p1);// and this is to add the jpanel into jframe which is created 


        //this is the image that i have set for the video call but still i havent gave any function for that
        ImageIcon i1 = new ImageIcon("C:\\Users\\varun\\OneDrive\\Desktop\\whatsapp clone\\svgtopng\\video-solid 2 png.png");
        Image i2 = i1.getImage().getScaledInstance(22, 22,Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel vc = new JLabel(i3);
        vc.setBounds(263,15,26,32);
        p1.add(vc);


        //this is the image that i have set for the call but still i havent gave any function for that
        ImageIcon j1 = new ImageIcon("C:\\Users\\varun\\OneDrive\\Desktop\\whatsapp clone\\svgtopng\\phone-solid.png");
        Image j2 = j1.getImage().getScaledInstance(20, 20,Image.SCALE_SMOOTH);
        ImageIcon j3 = new ImageIcon(j2);
        JLabel call = new JLabel(j3);
        call.setBounds(315,17,26,26);
        p1.add(call);


        //this is the image that i have set for that more menu button but still i havent gave any function for that
        ImageIcon k1 = new ImageIcon("C:\\Users\\varun\\OneDrive\\Desktop\\whatsapp clone\\svgtopng\\ellipsis-vertical-solid.png");
        Image k2 = k1.getImage().getScaledInstance(5, 26,Image.SCALE_SMOOTH);
        ImageIcon k3 = new ImageIcon(k2);
        JLabel dot = new JLabel(k3);
        dot.setBounds(360,18,20,26);
        p1.add(dot);


        //this is the image that i have set for the back button in top left and with an button function.
        ImageIcon l1 = new ImageIcon("C:\\Users\\varun\\OneDrive\\Desktop\\whatsapp clone\\svgtopng\\chevron-left-solid.png");
        Image l2 = l1.getImage().getScaledInstance(15, 24,Image.SCALE_SMOOTH);
        ImageIcon l3 = new ImageIcon(l2);
        JLabel back = new JLabel(l3);
        back.setBounds(10,20,23,23);
        p1.add(back);
        
        back.addMouseListener(new MouseAdapter() {  //back button logic.
            @Override
            public void mouseClicked(MouseEvent ae){
                System.exit(0);
            }
        });

        //this is the image that i have set in for profile photo.
        ImageIcon m1 = new ImageIcon("C:\\Users\\varun\\OneDrive\\Desktop\\whatsapp clone\\svgtopng\\WP img 3.png");
        Image m2 = m1.getImage().getScaledInstance(30, 30,Image.SCALE_SMOOTH);
        ImageIcon m3 = new ImageIcon(m2);
        JLabel pic1 = new JLabel(m3);
        pic1.setBounds(37,12,35,35);
        p1.add(pic1);


        //this is what i have set to show the name and info 
        JLabel name = new JLabel("Gayu");
        JLabel status = new JLabel("Online");
        name.setBounds(87,-10,65,65);
        name.setFont(new Font("SAN_SERIF",Font.BOLD,16));
        status.setBounds(87,30,50,25);
        name.setForeground(Color.white);
        status.setForeground(Color.white);
        p1.add(name);
        p1.add(status);

        //this is the chatbox which i have set it as a background image 
        chatbox = new JPanel();
        chatbox.setBounds(0,60,400,700);
        chatbox.setBackground(new Color(33,33,33));
        chatbox.setLayout(null);
        f.setUndecorated(true);
        f.add(chatbox);
       
        //this is send btn image i have set in chatbox
        ImageIcon r1 = new ImageIcon("C:\\Users\\varun\\OneDrive\\Desktop\\whatsapp clone\\svgtopng\\paper-plane-solid.png");
        Image r2 = r1.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon r3 = new ImageIcon(r2);
        JLabel sendBtn = new JLabel(r3);
        sendBtn.setBounds(350,590,25,25);
        chatbox.add(sendBtn);

        sendBtn.addMouseListener(new MouseAdapter() {  //back button logic.
            @Override
            public void mouseClicked(MouseEvent ae){
            try{   String out = chat.getText(); 
               JPanel p2 = formatLabel(out);
            //    p2.add(output);
               chatbox.setLayout(new BorderLayout());

               JPanel right = new JPanel(new BorderLayout()); // this is to set the message on the right side .
               right.add(p2,BorderLayout.LINE_END);// this is to set the message on the right side .
               vertical.add(right); // but if you have multiple images then this vertivcal.add(right) will set that all messages into right side 
               vertical.add(Box.createVerticalStrut(20));
               chatbox.add(vertical,BorderLayout.PAGE_START);
               right.setOpaque(true);
               right.setBackground(new Color(33,33,33));
               dout.writeUTF(out);
               chat.setText("");
               chatbox.repaint();
               chatbox.invalidate();
               chatbox.validate();
            }catch(Exception e){
                e.printStackTrace();
            }

            }
            public static JPanel formatLabel(String out){
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            JLabel output = new JLabel(out);
            panel.add(output);
            // output.setBackground(new Color(54,57,63));
            output.setBackground(new Color(7,94,84));
                
                output.setOpaque(true);
                output.setBorder(new EmptyBorder(15,15,15,15));
                output.setFont(new Font("SAN_SERIF",Font.PLAIN,18));
                output.setForeground(Color.WHITE);
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
                JLabel time = new JLabel();
                time.setText(sdf.format(cal.getTime()));
                panel.add(time);
                time.setForeground(Color.WHITE);
                panel.setOpaque(false);
               
                return panel;
            }
        });

        

        //this is emoji image i have set in chatbox
        ImageIcon s1 = new ImageIcon("C:\\Users\\varun\\OneDrive\\Desktop\\whatsapp clone\\svgtopng\\face-smile-regular.png");
        Image s2 = s1.getImage().getScaledInstance(25,25,Image.SCALE_SMOOTH);
        ImageIcon s3 = new ImageIcon(s2);
        JLabel emoji = new JLabel(s3);
        emoji.setBounds(8,595,25,25);
        chatbox.add(emoji);

        //this is mic image i have set in chatbox
        ImageIcon t1 = new ImageIcon("C:\\Users\\varun\\OneDrive\\Desktop\\whatsapp clone\\svgtopng\\microphone-solid.png");
        Image t2 = t1.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon t3 = new ImageIcon(t2);
        JLabel mic = new JLabel(t3);
        mic.setBounds(305,590,25,25);
        chatbox.add(mic);

        //this is link image i have set in chatbox
        ImageIcon u1 = new ImageIcon("C:\\Users\\varun\\OneDrive\\Desktop\\whatsapp clone\\svgtopng\\paperclip-solid.png");
        Image u2 = u1.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon u3 = new ImageIcon(u2);
        JLabel link = new JLabel(u3);
        link.setBounds(255,592,25,25);
        chatbox.add(link);

        // this the text area in which we are writing.
        chat = new JTextField();
        chat.setBounds(45,650,200,40);
        chat.setBackground(new Color(23,23,23));
        chat.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        chat.setForeground(Color.WHITE);
        chat.setBorder(null);
        f.add(chat);

        f.setVisible(true); // here we select visiblity to true to set the visible of the frame true if we didnt set it will n ot visible to use after executed.

    }
    public static void main(String[] args) {
        new Client(); // this is to call the constructor which is created
        try {
            Socket s = new Socket("127.0.0.1",8080);
             DataInputStream din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            while(true){
                chatbox.setLayout(new BorderLayout());
                String msg = din.readUTF();
                JPanel panel = formatLabel(msg);
                JPanel left = new JPanel(new BorderLayout());
                left.add(panel,BorderLayout.LINE_START);
                left.setBackground(new Color(33,33,33));
                vertical.add(left);
                left.setOpaque(false);
                vertical.add(Box.createVerticalStrut(15));
                chatbox.add(vertical,BorderLayout.PAGE_START);
                f.validate();
                                      }
                                      // 54, 57, 63
                            
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    public static JPanel formatLabel(String out){
                        JPanel panel = new JPanel();
                        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                        JLabel output = new JLabel(out);
                        panel.add(output);
                         output.setBackground(new Color(7,94,84));
                        // output.setBackground(Color.YELLOW);
                       
                        
                            output.setOpaque(true);
                            output.setBorder(new EmptyBorder(15,15,15,15));
                            output.setFont(new Font("SAN_SERIF",Font.PLAIN,18));
                            output.setForeground(Color.WHITE);
                            Calendar cal = Calendar.getInstance();
                            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
                            JLabel time = new JLabel();
                            time.setText(sdf.format(cal.getTime()));
                            panel.add(time);
                            time.setForeground(Color.WHITE);
                            panel.setOpaque(false);
                            return panel;
                        }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
