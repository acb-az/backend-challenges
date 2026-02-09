package az.acb.kafka.challenge.consumer;

import az.acb.kafka.challenge.model.InquiryMessage;
import az.acb.kafka.challenge.service.InquiryProcessorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class InquiryConsumer {

    private final InquiryProcessorService inquiryProcessorService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    // YANLIŞ TƏCRÜBƏ: Tənzimləmələr application.yml əvəzinə kodun daxilində yazılıb
    // Həmçinin pis JSON gəldikdə xəta verməsi üçün manual deserialization edilir
    @KafkaListener(topics = "inquiry-input-topic", groupId = "inquiry-group")
    public void listen(String message) {
        System.out.println("Received message: " + message);
        try {
            // Manual deserialization (Əl ilə obyektə çevirmə)
            InquiryMessage inquiry = objectMapper.readValue(message, InquiryMessage.class);
            inquiryProcessorService.processInquiry(inquiry);
        } catch (Exception e) {
            // POISON PILL (ZƏHƏRLİ MESAJ) PROBLEMİ:
            throw new RuntimeException("Error processing message", e);
        }
    }
}
