package az.acb.challenge;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LoanService service = new LoanService();
        List<LoanAccount> accounts = new ArrayList<>();

        // Hesablar əlavə edilir: ən azı bir CLOSED və bir ACTIVE
        accounts.add(new LoanAccount(1L, 1000.50, 5.0, LoanStatus.ACTIVE));
        accounts.add(new LoanAccount(2L, 2000.75, 4.5, LoanStatus.CLOSED));
        accounts.add(null); 

        System.out.println("Borc Hesablanması Başlayır...");
        
        try {
            // Bu, NullPointerException xətasına səbəb olmalıdır və ya null silinərsə yanlış cəm qaytarmalıdır
            double total = service.calculateTotalDebt(accounts);
            System.out.println("Ümumi Borc: " + total);
        } catch (Exception e) {
            System.err.println("Xəta: " + e);
            e.printStackTrace();
        }
    }
}
