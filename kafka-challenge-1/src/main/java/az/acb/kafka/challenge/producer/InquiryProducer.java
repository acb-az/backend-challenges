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

    // YANLIŞ TƏCRÜBƏ: Konfiqurasiya kodun içinə yazılıb (hardcoded)
    private final String BOOTSTRAP_SERVERS = "localhost:9092";
    private final String TOPIC = "approved-inquiries-topic";

    public void sendApprovedInquiry(InquiryMessage message) {
        // YANLIŞ TƏCRÜBƏ: Hər mesaj üçün yeni producer yaradılır (Səmərəlilik problemi)
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
            // producer.close(); // Bağlanmasa resurs sızması (leak) olacaq.
            // Tapşırıq: KafkaTemplate istifadəsini yoxlayın.
            // Hər dəfə yeni producer yaratmaq səmərəsizdir.
            producer.close(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
