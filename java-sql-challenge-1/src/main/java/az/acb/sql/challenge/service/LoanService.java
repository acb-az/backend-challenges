package az.acb.sql.challenge.service;

import az.acb.sql.challenge.entity.Loan;
import az.acb.sql.challenge.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    // PROBLEM 1: Boş siyahı (empty list) idarə olunmayıb
    public Loan getFirstLoanByPin(String pin) {
        List<Loan> loans = loanRepository.findAllByBorrowerPin(pin);
        // Burada birbaşa ilk elementi götürməyə çalışır.
        // Əgər bazada bu FIN-ə uyğun kredit yoxdursa, IndexOutOfBoundsException atacaq.
        return loans.get(0);
    }

    // PROBLEM 2: N+1 Problemi
    public List<String> getAllLoanDetails() {
        List<Loan> loans = loanRepository.findAll();
        // Burada hər loan üçün bazaya təkrar Borrower sorğusu gedəcək (Lazy loading).
        return loans.stream()
                .map(loan -> loan.getBorrower().getFullName() + ": " + loan.getAmount())
                .toList();
    }

    // PROBLEM 3: Transactional olmaması və Native Query
    public void updateLoanAmountDirectly(Long id, Double newAmount) {
        // @Transactional olmadığı üçün bu metod işləməyə bilər (TransactionRequiredException)
        // və ya dəyişikliklər commit olunmaya bilər.
        loanRepository.updateAmountNative(id, newAmount);
    }
}
