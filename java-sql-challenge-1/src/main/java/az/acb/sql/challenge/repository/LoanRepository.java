package az.acb.sql.challenge.repository;

import az.acb.sql.challenge.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findAllByBorrowerPin(String pin);

    // YANLIŞ TƏCRÜBƏ: SQL Injection riski və ya Transactional olmaması
    // Bu metod Service-də çağırılacaq və @Transactional olmadığı üçün xəta verə bilər (və ya commit etməyə bilər)
    @Modifying
    @Query(value = "UPDATE loans SET amount = " +
            ":newAmount WHERE id = :id", nativeQuery = true)
    void updateAmountNative(@Param("id") Long id, @Param("newAmount") Double newAmount);
}
