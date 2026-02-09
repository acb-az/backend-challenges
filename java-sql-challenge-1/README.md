# Java SQL & JPA Challenge - Junior Backend Developer

Bu layihə Junior Backend Developer namizədlərinin Java, Spring Data JPA və SQL biliklərini yoxlamaq üçün hazırlanmışdır.

## Ssenari
Bankın kredit sistemi üçün sadə bir servis (`LoanService`) yazılıb. Lakin bu servisdə performans problemləri, təhlükəsizlik riskləri və potensial xətalar var.

## Tapşırıqlar

`LoanService.java` faylını açın və aşağıdakı problemləri həll edin:

### 1. Siyahı və Null Safety (List Handling)
`getFirstLoanByPin` metodunda `loans.get(0)` birbaşa çağırılır.
*   **Problem:** Əgər həmin PİN-ə aid kredit yoxdursa, `IndexOutOfBoundsException` baş verir.
*   **Hədəf:** Bu halı düzgün idarə edin (məsələn, `Optional` istifadə edərək və ya siyahını yoxlayaraq).

### 2. JPA Performans (N+1 Problemi)
`getAllLoanDetails` metodu bütün kreditləri gətirir və sonra hər kredit üçün borcalanın (`Borrower`) adını oxuyur.
*   **Problem:** `Loan` entity-də `Borrower` əlaqəsi `Lazy` olduğu üçün, hər bir dövr (loop) iterasiyasında bazaya əlavə sorğu gedir (N+1 problemi).
*   **Hədəf:** `JOIN FETCH` və ya `@EntityGraph` istifadə edərək sorğuların sayını optimallaşdırın.

### 3. SQL və Verilənlər Tipi
`updateLoanAmountDirectly` metodu və `Loan` entity-si.
*   **Problem:** Pul məbləği üçün `Double` istifadə olunub (dəqiqlik itkisi). Native SQL sorğusu istifadə olunur və `@Transactional` yoxdur.
*   **Hədəf:**
    *   Pul üçün daha düzgün bir tip istifadə edin.
    *   Metodun transactional olmasını təmin edin.

## Texniki Məlumat
*   **Java Version:** 21
*   **Spring Boot:** 3.x
*   **Database:** H2 (In-memory)

## İşə Salmaq Üçün

```bash
mvn spring-boot:run
```
Proqram işə düşəndə `DataInitializer` avtomatik olaraq test məlumatlarını bazaya yazacaq.
