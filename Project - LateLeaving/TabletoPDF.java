import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.time.Instant;
import java.util.Calendar;
import java.io.FileOutputStream;
import java.io.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class TabletoPDF {  
        public static void main(String[] args) throws DocumentException,IOException, ClassNotFoundException, SQLException{
        	Calendar now = Calendar.getInstance();
  	      int day=now.get(Calendar.DAY_OF_WEEK); 
  	      int hour=now.get(Calendar.HOUR_OF_DAY);
  	      int minutes=now.get(Calendar.MINUTE);
  	if(hour==18&&minutes==1)
  	{
  	      if(day==6)
  	      {

              Date mydate = new Date();
              SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
              String arun= formatter.format(mydate);

  	    	 for(int i=0;i<=2;i++)
  	    	 {
  	            Class.forName ("com.mysql.jdbc.Driver"); 
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
                Statement stmt = conn.createStatement();
                Calendar cal = Calendar.getInstance();
                cal.setTime(Date.from(Instant.now()));
                ResultSet query_set = stmt.executeQuery("SELECT id,name,ll,work,reason,req_cab,drop_loc,date from lateleaving where date='"+arun+"'");
String result = String.format(
                        "LateLeavingForm "+arun+".pdf");
                Document report=new Document();
                PdfWriter writer = PdfWriter.getInstance(report, new FileOutputStream(result));
                report.open();            
                PdfPTable my_report_table = new PdfPTable(8);
                //create a cell object
                PdfPCell table_cell;
                   table_cell=new PdfPCell(new Phrase("Employee Id"));
     my_report_table.addCell(table_cell);
     table_cell=new PdfPCell(new Phrase("Employee Name"));
     my_report_table.addCell(table_cell);
     table_cell=new PdfPCell(new Phrase("Lab Leader"));
     my_report_table.addCell(table_cell);
     table_cell=new PdfPCell(new Phrase("Work"));
     my_report_table.addCell(table_cell);
     table_cell=new PdfPCell(new Phrase("Reason"));
     my_report_table.addCell(table_cell);
     table_cell=new PdfPCell(new Phrase("Date"));
     my_report_table.addCell(table_cell);
     table_cell=new PdfPCell(new Phrase("Require Cab"));
     my_report_table.addCell(table_cell);
     table_cell=new PdfPCell(new Phrase("Drop Location"));
     my_report_table.addCell(table_cell);
                while (query_set.next()) {                             
                	            String emp_id = query_set.getString("id");
                                table_cell=new PdfPCell(new Phrase(emp_id));
                                my_report_table.addCell(table_cell);
                               
                                String emp_name=query_set.getString("name");
                                table_cell=new PdfPCell(new Phrase(emp_name));
                                my_report_table.addCell(table_cell);
                                
                                String ll=query_set.getString("ll");
                                table_cell=new PdfPCell(new Phrase(ll));
                                my_report_table.addCell(table_cell);
                                
                                String work=query_set.getString("work");
                                table_cell=new PdfPCell(new Phrase(work));
                                my_report_table.addCell(table_cell);
                                
                                String reason=query_set.getString("reason");
                                table_cell=new PdfPCell(new Phrase(reason));
                                my_report_table.addCell(table_cell);
                                
                                String date=query_set.getString("date");
                                table_cell=new PdfPCell(new Phrase(date));
                                my_report_table.addCell(table_cell);
                                
                                String req_cab=query_set.getString("req_cab");
                                table_cell=new PdfPCell(new Phrase(req_cab));
                                my_report_table.addCell(table_cell);
                                
                                String drop_loc=query_set.getString("drop_loc");
                                table_cell=new PdfPCell(new Phrase(drop_loc));
                                my_report_table.addCell(table_cell);
                                }
                report.add(my_report_table);                       
                report.close();
                query_set.close();
                stmt.close(); 
                conn.close();                    
       
                final String username = "maheshkumar161295@gmail.com";//change accordingly
                final String password = "Joshua@2018";

                Properties props = new Properties();
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host","smtp.gmail.com");
                props.put("mail.smtp.port", "587");

                // Get the Session object.
                Session session = Session.getInstance(props,
                   new javax.mail.Authenticator() {
                      protected PasswordAuthentication getPasswordAuthentication() {
                         return new PasswordAuthentication(username, password);
                      }
                   });

                try {
                   // Create a default MimeMessage object.
                   Message message = new MimeMessage(session);

                   message.setFrom(new InternetAddress("maheshkumar161295@gmail.com"));
                   message.addRecipients(Message.RecipientType.CC, 
                           InternetAddress.parse("mktrichy16121995@gmail.com"));
                   // Set To: header field of the header.
                   message.setRecipients(Message.RecipientType.TO,
                      InternetAddress.parse("iamarunbalaji@icloud.com,virubalaji.ab@gmail.com"));
                           // Calendar cal = Calendar.getInstance();
                   cal.setTime(Date.from(Instant.now()));
                   String result1 = String.format(
                           "LateLeavingForm "+arun);

                   // Set Subject: header field
                   message.setSubject(result1);

                   // Create the message part
                   BodyPart messageBodyPart = new MimeBodyPart();

                   // Now set the actual message
                   messageBodyPart.setText("Hi Today's Late Leaving Form");

                   // Create a multipar message
                   Multipart multipart = new MimeMultipart();

                   // Set text message part
                   multipart.addBodyPart(messageBodyPart);

                   // Part two is attachment
                   messageBodyPart = new MimeBodyPart();
                  
                   String result2 = String.format(
                           "LateLeavingForm "+arun+ ".pdf", cal);
                   String filename = "C:\\Users\\win8.1\\eclipse-workspace\\ValuetoTable\\"+result2;
                   DataSource source = new FileDataSource(filename);
                   messageBodyPart.setDataHandler(new DataHandler(source));
                   messageBodyPart.setFileName("Late Leaving.pdf");
                   multipart.addBodyPart(messageBodyPart);

                   // Send the complete message parts
                   message.setContent(multipart);

                   // Send message
                   Transport.send(message);

                   System.out.println("Sent message successfully....");
            
                } catch (MessagingException e) {
                   throw new RuntimeException(e);
                }
                String oldDate = arun;  
            	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            	Calendar c = Calendar.getInstance();
            	try{
            	   //Setting the date to the given date
            	   c.setTime(sdf.parse(oldDate));
            	}catch(ParseException e){
            		e.printStackTrace();
            	 }
            	c.add(Calendar.DAY_OF_MONTH, 1);  
            	arun = sdf.format(c.getTime());  
  	    	 }   
  	    	 }	

  	      
  	      else if(day==7||day==1)
  	      {
  	    	  System.out.println("Form Already Sent");
  	      }
  	      
  	      
  	      
  	      else
  	    	  {
  	    	  
  	        Class.forName ("com.mysql.jdbc.Driver"); 
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
            Statement stmt = conn.createStatement();
            Calendar cal = Calendar.getInstance();
            cal.setTime(Date.from(Instant.now()));
            Date mydate = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            String balaji= formatter.format(mydate);
            ResultSet query_set = stmt.executeQuery("SELECT id,name,ll,work,reason,req_cab,drop_loc,date from lateleaving where date='"+balaji+"'");
String result = String.format(
                    "LateLeavingForm "+balaji+".pdf");
            Document report=new Document();
            PdfWriter writer = PdfWriter.getInstance(report, new FileOutputStream(result));
            report.open();            
            PdfPTable my_report_table = new PdfPTable(8);
            //create a cell object
            PdfPCell table_cell;
               table_cell=new PdfPCell(new Phrase("Employee Id"));
 my_report_table.addCell(table_cell);
 table_cell=new PdfPCell(new Phrase("Employee Name"));
 my_report_table.addCell(table_cell);
 table_cell=new PdfPCell(new Phrase("Lab Leader"));
 my_report_table.addCell(table_cell);
 table_cell=new PdfPCell(new Phrase("Work"));
 my_report_table.addCell(table_cell);
 table_cell=new PdfPCell(new Phrase("Reason"));
 my_report_table.addCell(table_cell);
 table_cell=new PdfPCell(new Phrase("Date"));
 my_report_table.addCell(table_cell);
 table_cell=new PdfPCell(new Phrase("Require Cab"));
 my_report_table.addCell(table_cell);
 table_cell=new PdfPCell(new Phrase("Drop Location"));
 my_report_table.addCell(table_cell);
            while (query_set.next()) {                             
            	            String emp_id = query_set.getString("id");
                            table_cell=new PdfPCell(new Phrase(emp_id));
                            my_report_table.addCell(table_cell);
                           
                            String emp_name=query_set.getString("name");
                            table_cell=new PdfPCell(new Phrase(emp_name));
                            my_report_table.addCell(table_cell);
                            
                            String ll=query_set.getString("ll");
                            table_cell=new PdfPCell(new Phrase(ll));
                            my_report_table.addCell(table_cell);
                            
                            String work=query_set.getString("work");
                            table_cell=new PdfPCell(new Phrase(work));
                            my_report_table.addCell(table_cell);
                            
                            String reason=query_set.getString("reason");
                            table_cell=new PdfPCell(new Phrase(reason));
                            my_report_table.addCell(table_cell);
                            
                            String date=query_set.getString("date");
                            table_cell=new PdfPCell(new Phrase(date));
                            my_report_table.addCell(table_cell);
                            
                            String req_cab=query_set.getString("req_cab");
                            table_cell=new PdfPCell(new Phrase(req_cab));
                            my_report_table.addCell(table_cell);
                            
                            String drop_loc=query_set.getString("drop_loc");
                            table_cell=new PdfPCell(new Phrase(drop_loc));
                            my_report_table.addCell(table_cell);
                            }
            report.add(my_report_table);                       
            report.close();
            query_set.close();
            stmt.close(); 
            conn.close();                    
  	    	  
            final String username = "maheshkumar161295@gmail.com";//change accordingly
            final String password = "Joshua@2018";

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host","smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            // Get the Session object.
            Session session = Session.getInstance(props,
               new javax.mail.Authenticator() {
                  protected PasswordAuthentication getPasswordAuthentication() {
                     return new PasswordAuthentication(username, password);
                  }
               });

            try {
               // Create a default MimeMessage object.
               Message message = new MimeMessage(session);

               message.setFrom(new InternetAddress("maheshkumar161295@gmail.com"));
               message.addRecipients(Message.RecipientType.CC, 
                       InternetAddress.parse("mktrichy16121995@gmail.com"));
               // Set To: header field of the header.
               message.setRecipients(Message.RecipientType.TO,
                  InternetAddress.parse("iamarunbalaji@icloud.com,virubalaji.ab@gmail.com"));
//                        Calendar cal = Calendar.getInstance();
               cal.setTime(Date.from(Instant.now()));
               String result4 = String.format(
                       "LateLeavingForm "+balaji);

               // Set Subject: header field
               message.setSubject(result4);

               // Create the message part
               BodyPart messageBodyPart = new MimeBodyPart();

               // Now set the actual message
               messageBodyPart.setText("Hi Today's Late Leaving Form");

               // Create a multipar message
               Multipart multipart = new MimeMultipart();

               // Set text message part
               multipart.addBodyPart(messageBodyPart);

               // Part two is attachment
               messageBodyPart = new MimeBodyPart();
              
               String result5 = String.format(
                       "LateLeavingForm " +balaji+".pdf", cal);
               String filename = "C:\\Users\\win8.1\\eclipse-workspace\\ValuetoTable\\"+result5;
               DataSource source = new FileDataSource(filename);
               messageBodyPart.setDataHandler(new DataHandler(source));
               messageBodyPart.setFileName("Late Leaving.pdf");
               multipart.addBodyPart(messageBodyPart);

               // Send the complete message parts
               message.setContent(multipart);

               // Send message
               Transport.send(message);

               System.out.println("Sent message successfully....");
        
            } catch (MessagingException e) {
               throw new RuntimeException(e);
            }
  	    	  }
  	    	  }
        }
        }