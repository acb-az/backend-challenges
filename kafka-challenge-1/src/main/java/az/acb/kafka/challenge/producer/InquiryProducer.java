package az.acb.kafka.challenge.producer;

import az.acb.kafka.challenge.model.InquiryMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class InquiryProducer {

    // BAD PRACTICE: Hardcoded configuration
    private final String BOOTSTRAP_SERVERS = "localhost:9092";
    private final String TOPIC = "approved-inquiries-topic";

    public void sendApprovedInquiry(InquiryMessage message) {
        // BAD PRACTICE: Creating a new producer for every message (Efficiency issue)
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(message);
            
            ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, message.getInquiryId(), json);
            producer.send(record);
            // producer.close(); // If they forget to close, it's even worse (leak), but let's be nice and close it or let it leak for the challenge? 
            // The prompt says "Efficiency: Check usage of KafkaTemplate".
            // So creating new producer is the inefficiency.
            producer.close(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
