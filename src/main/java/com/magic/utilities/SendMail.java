package com.magic.utilities;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
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

public class SendMail {
	
	
	   public static void mail(String to[],String cc[],final String username,final String password,final String filename,final String extentreport) throws IOException
	   {
	   Date date=new Date();

      // Sender's email ID needs to be mentioned
      String from = "mlabsautoreports@gmail.com";

      // Assuming you are sending email through relay.jangosmtp.net
      String host = "smtp.gmail.com";

      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
      props.put("mail.smtp.socketFactory.fallback", "false");
     
      props.put("mail.smtp.debug", "true");

      
//    String[]ports = {"587","465","25"};
      String[]ports = {"25","465","587"};
      //props.put("mail.smtp.port", "587");
     // props.put("mail.smtp.port", "465");
      
     /* props.put("mail.smtp.host", host);
      props.put("mail.smtp.socketFactory.port", "587");
      props.put("mail.smtp.socketFactory.class",
              "javax.net.ssl.SSLSocketFactory");
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.port", "587");*/

     /* // Get the Session object.
      Session session = Session.getInstance(props,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
            }
         });*/
   
   	
   	// creates a new session with an authenticator
      Authenticator auth = new Authenticator() {
          public PasswordAuthentication getPasswordAuthentication() {
              return new PasswordAuthentication(username, password);
          }
      };
   
      int portCount = 0;
      for (; portCount < 3; portCount ++) {
    
      try {
    	  
    	// Get the Session object.
    	  props.put("mail.smtp.port", ports[portCount]);
    	  props.put("mail.smtp.socketFactory.port", ports[portCount]);
    	  Session session = Session.getInstance(props, auth);   
      
         // Create a default MimeMessage object.
         Message message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         InternetAddress[] toAddress=new InternetAddress[to.length];
         
         for(int i=0;i<to.length;i++)
         {
        	 toAddress[i]= new InternetAddress(to[i]);
         }
         for(int i=0;i<toAddress.length;i++)
         {
         message.addRecipient(Message.RecipientType.TO,toAddress[i]);
         }
         InternetAddress[] ccAddress = new InternetAddress[cc.length];
         
         // To get the array of ccaddresses
         for( int i = 0; i < cc.length; i++ ) {
             ccAddress[i] = new InternetAddress(cc[i]);
         }
         
         // Set cc: header field of the header.
         for( int i = 0; i < ccAddress.length; i++) {
             message.addRecipient(Message.RecipientType.CC, ccAddress[i]);
         }
         // Set Subject: header field
         message.setSubject("New Frameork"+ date.toString()+" Sanity Automation Report");

         // Create the message part
         BodyPart messageBodyPart = new MimeBodyPart();

        
         // Now set the actual message
         messageBodyPart.setText("This is message body");
         //messageBodyPart.setContent(readFile(filename, StandardCharsets.UTF_8), "text/html");
         
         // Create a multipar message
         Multipart multipart = new MimeMultipart();

         // Set text message part
         multipart.addBodyPart(messageBodyPart);

         // Part two is attachment
         messageBodyPart = new MimeBodyPart();
         //String filename = "D://Workspace//Alert WAP//Reports//TestReport.html";
         DataSource source = new FileDataSource(extentreport);
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName("TestReport.html");
         multipart.addBodyPart(messageBodyPart);

         // Send the complete message parts
         message.setContent(multipart);

         // Send message
         Transport.send(message,message.getAllRecipients());
         System.out.println(ports[portCount]);
         System.out.println(username);
         System.out.println(password);
         System.out.println();
         System.out.println("Sent message successfully....");
         break;
      	} catch (MessagingException e) {
    	  e.printStackTrace();
    	  continue;
//         throw new RuntimeException(e);
      	}

      }
      if(portCount==3)
      	{
    	  System.out.println("No Mail Send Due to Port Failure");
      	} 
      //return this;
     }
	   
	   
	   static String readFile(String path, Charset encoding) 
				  throws IOException 
				{
				  byte[] encoded = Files.readAllBytes(Paths.get(path));
				  return new String(encoded, encoding);
				}
//	   public static void main(String args[]){
//		   SendMail done= new SendMail();
//				   try {
//					done.mail(Constant.to, Constant.cc, Constant.username, Constant.password, Constant.filename, Constant.Extentfilename);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//	   }
}
