package az.acb.challenge;

public class LoanAccount {
    private Long id;
    private double balance;
    private double interestRate;
    private LoanStatus status;

    public LoanAccount(Long id, double balance, double interestRate, LoanStatus status) {
        this.id = id;
        this.balance = balance;
        this.interestRate = interestRate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public LoanStatus getStatus() {
        return status;
    }
}
