package az.acb.challenge;

import java.util.List;

public class LoanService {

    public double calculateTotalDebt(List<LoanAccount> accounts) {
        double total = 0.0;
        // Müsahibə tapşırığı üçün qəsdən yazılmış "Pis Kod":
        // 1. Standart for dövrü (Stream API istifadə etmir)
        // 2. List və ya elementlər üçün null yoxlanışı yoxdur
        // 3. Məntiq xətası: LoanStatus-dan asılı olmayaraq bütün balansları toplayır
        // 4. Dəqiqlik xətası: Pul üçün yanlış tip istifadə edir
        for (int i = 0; i < accounts.size(); i++) {
            LoanAccount acc = accounts.get(i);
            total += acc.getBalance();
        }
        return total;
    }
}
