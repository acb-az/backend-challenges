package az.acb.kafka.challenge.service;

import az.acb.kafka.challenge.model.InquiryMessage;
import az.acb.kafka.challenge.producer.InquiryProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InquiryProcessorService {

    private final InquiryProducer inquiryProducer;

    public void processInquiry(InquiryMessage inquiry) {
        // Biznes Məntiqi
        if ("APPROVED".equalsIgnoreCase(inquiry.getStatus())) {
            System.out.println("Inquiry " + inquiry.getInquiryId() + " is APPROVED. Sending to next topic...");
            inquiryProducer.sendApprovedInquiry(inquiry);
        } else {
            System.out.println("Inquiry " + inquiry.getInquiryId() + " is " + inquiry.getStatus() + ". Ignoring.");
        }
    }
}
