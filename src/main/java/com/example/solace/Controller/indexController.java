package com.example.solace.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("index")
public class indexController {

  @Autowired
  private JmsTemplate jmsQueueTemplate;

  @GetMapping("index")
  public int index() {
    jmsQueueTemplate.convertAndSend("Q/Test", "Send test to queue");
    return 0;
  }

}
