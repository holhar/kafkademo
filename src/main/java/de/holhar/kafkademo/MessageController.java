package de.holhar.kafkademo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private Producer producer;

    @PostMapping("")
    public MessageResponse sendMessage(@RequestBody Greeting greeting) {
        producer.sendMessage(greeting);
        return new MessageResponse("success");
    }
}
