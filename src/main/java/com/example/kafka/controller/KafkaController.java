package com.example.kafka.controller;

import com.example.kafka.model.FieldModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class KafkaController {
    @Autowired
    private KafkaTemplate<String, FieldModel> kafkaTemplate;

    @PostMapping("/api/kafka")
    public void post(@RequestBody FieldModel fieldModel) {
        kafkaTemplate.send("myTopic", fieldModel);
    }

    @KafkaListener(topics = "myTopic")
    public void listenGroupFoo(FieldModel fieldModel) {
        System.out.println("Received Message in group   : " + fieldModel.getField1() +" "+ fieldModel.getField2());
    }
}
