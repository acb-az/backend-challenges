package az.acb.sql.challenge.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // YANLIŞ TƏCRÜBƏ: Pul üçün Double istifadə olunub
    private Double amount;

    private String currency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "borrower_id")
    private Borrower borrower;

    public Loan(Double amount, String currency, Borrower borrower) {
        this.amount = amount;
        this.currency = currency;
        this.borrower = borrower;
    }
}
