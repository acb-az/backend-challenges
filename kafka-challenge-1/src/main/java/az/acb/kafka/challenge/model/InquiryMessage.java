package az.acb.kafka.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InquiryMessage {
    private String inquiryId;
    private String pin;
    private String status; // PENDING, APPROVED, REJECTED
    private BigDecimal amount;
}
