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

    // BAD PRACTICE: Hardcoded properties inside the code instead of application.yml
    // Also manual deserialization inside the listener to provoke errors on bad JSON
    @KafkaListener(topics = "inquiry-input-topic", groupId = "inquiry-group")
    public void listen(String message) {
        System.out.println("Received message: " + message);
        try {
            // Manual deserialization
            InquiryMessage inquiry = objectMapper.readValue(message, InquiryMessage.class);
            inquiryProcessorService.processInquiry(inquiry);
        } catch (Exception e) {
            // POISON PILL ISSUE:
            // If we catch it here, it won't crash the consumer loop, but the prompt says:
            // "Intentionally Bad: Deserialize xətalarını nəzərə alma (məsələn, xarab json gəlsə, proqram çöksün)."
            // So I should throw the exception or NOT catch it.
            // If I throw it, the default ErrorHandler might retry forever or stop the container.
            throw new RuntimeException("Error processing message", e);
        }
    }
}
