package az.acb.sql.challenge;

import az.acb.sql.challenge.entity.Borrower;
import az.acb.sql.challenge.entity.Loan;
import az.acb.sql.challenge.repository.BorrowerRepository;
import az.acb.sql.challenge.repository.LoanRepository;
import az.acb.sql.challenge.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final BorrowerRepository borrowerRepository;
    private final LoanRepository loanRepository;
    private final LoanService loanService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Məlumatlar bazaya yüklənir...");

        Borrower b1 = new Borrower("1234567", "Ali Huseynov");
        Borrower b2 = new Borrower("7654321", "Vali Aliyev");

        borrowerRepository.save(b1);
        borrowerRepository.save(b2);

        // N+1 problemini göstərmək üçün bir neçə kredit əlavə edirik
        for (int i = 0; i < 10; i++) {
            loanRepository.save(new Loan(100.0 + i, "AZN", b1));
            loanRepository.save(new Loan(200.0 + i, "USD", b2));
        }

        System.out.println("Bazaya məlumat yükləndi.");
        System.out.println("Testləri başlamaq üçün 'LoanService' metodlarını çağıra bilərsiniz.");
        
        // Test nümunəsi:
        // try {
        //     loanService.getFirstLoanByPin("OLMAYAN_PIN");
        // } catch (Exception e) {
        //     System.out.println("Xəta baş verdi: " + e.getMessage());
        // }
    }
}
