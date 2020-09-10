package com.example.solace;

import com.solacesystems.jcsmp.JCSMPFactory;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.TextMessage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ErrorHandler;

@SpringBootApplication
public class SolaceReceiver {

  private static final String QUEUE = "TEST";

  @JmsListener(destination = QUEUE)
  public void handleDocManifest(Message message) {
    try {
      System.out.println(new JmsTemplate().getMessageConverter().fromMessage(message));

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Service
  public class SolaceErrorHandler implements ErrorHandler {

    @Override
    public void handleError(Throwable t) {
      System.out.println("ErrorHandler !!!!!!!");
    }
  }

  @Bean
  public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory connectionFactory, SolaceErrorHandler errorHandler) {
    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory);
    factory.setErrorHandler(errorHandler);
//        factory.setConcurrency("1");
    return factory;
  }
}
