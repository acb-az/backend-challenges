package az.acb.sql.challenge.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "borrowers")
public class Borrower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String pin;

    @Column(nullable = false)
    private String fullName;

    // Default Lazy loading
    @OneToMany(mappedBy = "borrower", cascade = CascadeType.ALL)
    private List<Loan> loans = new ArrayList<>();

    public Borrower(String pin, String fullName) {
        this.pin = pin;
        this.fullName = fullName;
    }
}
