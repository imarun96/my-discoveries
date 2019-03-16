import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JCheckBox;
import javax.swing.JToggleButton;
import java.awt.Font;
;public class lateleaving extends JFrame {
	private JPanel contentPane;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					lateleaving frame = new lateleaving();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					
					//System.out.println("HIIHIHIH");
				}
			}
		});
	}
	public lateleaving() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 741, 503);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLateLeavingForm = new JLabel("Late Leaving Form");
		lblLateLeavingForm.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLateLeavingForm.setBounds(295, 11, 256, 34);
		contentPane.add(lblLateLeavingForm);
		
		JLabel lblEmployeeId = new JLabel("Employee Id*");
		lblEmployeeId.setBounds(57, 86, 104, 19);
		contentPane.add(lblEmployeeId);
		
		JLabel lblName = new JLabel("Name*");
		lblName.setBounds(57, 130, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblLabLeader = new JLabel("Lab Leader*");
		lblLabLeader.setBounds(57, 164, 89, 19);
		contentPane.add(lblLabLeader);
		
		
		JLabel lblWork = new JLabel("Work*");
		lblWork.setBounds(57, 206, 46, 14);
		contentPane.add(lblWork);
		
		JLabel lblReason = new JLabel("Reason*");
		lblReason.setBounds(57, 242, 62, 14);
		contentPane.add(lblReason);
		
		JLabel lblDate = new JLabel("Date*");
		lblDate.setBounds(57, 283, 46, 14);
		contentPane.add(lblDate);
		
		JLabel lblNewLabel = new JLabel("Require Cab*");
		lblNewLabel.setBounds(57, 322, 89, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblDropLocation = new JLabel("Drop Location*");
		lblDropLocation.setBounds(57, 361, 89, 14);
		contentPane.add(lblDropLocation);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(171, 86, 122, 22);
		contentPane.add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(171, 125, 122, 22);
		contentPane.add(textArea_1);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setBounds(171, 161, 122, 22);
		contentPane.add(textArea_2);
		
		JTextArea textArea_3 = new JTextArea();
		textArea_3.setBounds(171, 198, 122, 22);
		contentPane.add(textArea_3);
		
		JTextArea textArea_4 = new JTextArea();
		textArea_4.setBounds(171, 237, 122, 22);
		contentPane.add(textArea_4);
		
		JTextArea textArea_5 = new JTextArea();
		textArea_5.setBounds(171, 317, 122, 22);
		contentPane.add(textArea_5);
		
		JTextArea textArea_6 = new JTextArea();
		textArea_6.setBounds(171, 356, 122, 22);
		contentPane.add(textArea_6);
		
		JTextArea textArea_7 = new JTextArea();
		textArea_7.setBounds(172, 278, 121, 22);
		contentPane.add(textArea_7);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblName, textArea_1, lblLabLeader, textArea, textArea_5, lblWork, lblReason, lblNewLabel, lblDropLocation, textArea_2, textArea_3, textArea_4, textArea_6, lblLateLeavingForm, lblEmployeeId}));

		JButton btnSubmit = new JButton("Submit");
		Calendar now = Calendar.getInstance();
	      int hour=now.get(Calendar.HOUR_OF_DAY); 
	      
	      if((hour>=10)&&(hour<18))
	      {
	    	  btnSubmit.setEnabled(true);
	      }
	      else
	      {
	    	  btnSubmit.setEnabled(false);
	          JOptionPane.showMessageDialog(null,"Your are not Supposed to Submit after 6 PM. Please Submit between 10 AM to 6 AM");
	      }
	      
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
							Calendar now = Calendar.getInstance();
				      int hour=now.get(Calendar.HOUR_OF_DAY);       
				      Connection con=null;
						Statement stmt=null;
				      if((hour>=10)&&(hour<18))
				      {
				    	  btnSubmit.setEnabled(true);
							
				try {		
					Class.forName("com.mysql.jdbc.Driver");
								con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
								stmt=con.createStatement();
								String sql= "insert into lateleaving (id,name,ll,work,reason,req_cab,drop_loc,date) values('"+textArea.getText()+"','"+textArea_1.getText()+"','"+textArea_2.getText()+"','"+textArea_3.getText()+"','"+textArea_4.getText()+"','"+textArea_5.getText()+"','"+textArea_6.getText()+"','"+textArea_7.getText()+"')";
								JOptionPane.showMessageDialog(null,"Submitted");
								stmt.executeUpdate(sql);
								con.close();
								btnSubmit.setEnabled(false);
								textArea.setText(" ");
								textArea_1.setText(" ");
								textArea_2.setText(" ");
								textArea_3.setText(" ");
								textArea_4.setText(" ");
								textArea_5.setText(" ");
								textArea_6.setText(" ");
								textArea_7.setText(" ");						
				}
				catch(Exception k) 
				{
					System.out.println(k.getMessage());
				}
				      }
				      else
				      {
				    	  btnSubmit.setEnabled(false);
				    	  JOptionPane.showMessageDialog(null,"Your are not Supposed to Submit after 6 PM. Please Submit between 10 AM to 6 PM");
				  	  }
}
						});
		btnSubmit.setBounds(315, 412, 89, 23);
		contentPane.add(btnSubmit);
		textArea.setText(" ");		
	}
}
