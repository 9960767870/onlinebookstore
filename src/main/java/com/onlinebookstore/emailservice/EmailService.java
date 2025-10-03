package com.onlinebookstore.emailservice;

import com.onlinebookstore.entity.Order;
import com.onlinebookstore.entity.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOrderConfirmationHtmlEmail(User user, Order order) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(user.getEmail());
            helper.setSubject("Your Order Confirmation – Thank You for Shopping with Us!");

            String htmlContent = "<html><body>" +
                    "<h2>Order Confirmation</h2>" +
                    "<p>Hi <b>" + user.getUserName() + "</b>,</p>" +
                    "<p>Thank you for shopping at our Online Book Store!</p>" +
                    "<p><b>Order ID:</b> " + order.getOrderId() + "<br>" +
                    "<b>Order Date:</b> " + order.getOrderDate() + "<br>" +
                    "<b>Total Amount:</b> $" + order.getTotalPrice() + "<br>" +
                    "<b>Status:</b> " + order.getStatus() + "</p>" +
                    "<p>We hope you enjoy your books! 📚</p>" +
                    "<p>If you have any questions, please contact support@example.com</p>" +
                    "</body></html>";

            helper.setText(htmlContent, true);  // true = HTML mode

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to send email");
        }
    }

}
