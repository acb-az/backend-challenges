# Kafka Challenge 1 - Junior Backend Developer

Bu layihə Junior Backend Developer namizədlərinin Spring Boot və Apache Kafka biliklərini yoxlamaq üçün hazırlanmışdır.

## Ssenari
Bankın kredit müraciəti (inquiry) sisteminin bir hissəsini inkişaf etdirirsiniz. `InquiryService` (bu mikroservis) kənardan gələn müraciətləri qəbul edir və yalnız təsdiqlənmiş (APPROVED) müraciətləri növbəti mərhələyə ötürür.

## Tapşırıqlar

Sizdən aşağıdakı problemləri həll etmək və kodu refaktorinq etmək tələb olunur:

### 1. Error Handling (Poison Pill)
`InquiryConsumer` klasında gələn mesaj JSON formatında olmadıqda (məsələn, "INVALID_JSON") sistem xəta verir və mesajı təkrar-təkrar oxumağa çalışır (sonsuz dövr).
*   **Hədəf:** Xarab mesajların sistemi dondurmasının qarşısını alın. (Məsələn: ErrorHandlingDeserializer, Dead Letter Topic (DLT) və ya sadəcə xətanı loglayıb keçmək).

### 2. Efficiency (Səmərəlilik)
`InquiryProducer` klasında hər mesaj göndəriləndə yeni bir `KafkaProducer` yaradılır. Bu, çox resurs tələb edən yanlış yanaşmadır.
*   **Hədəf:** Spring Kafka-nın təqdim etdiyi `KafkaTemplate`-dən istifadə edərək bu hissəni optimallaşdırın.

### 3. Configuration Management
Kafka konfiqurasiyaları (bootstrap servers, topic adları və s.) kodun içində "hardcoded" edilib.
*   **Hədəf:** Bu dəyərləri `src/main/resources/application.properties` (və ya `.yml`) faylına köçürün və kodda oradan oxunmasını təmin edin.

### 4. Idempotency (Əlavə sual)
Eyni `inquiryId` ilə iki dəfə mesaj gəlsə, sistem necə davranmalıdır? Bunu necə həll edərdiniz? (Kod yazmaq mütləq deyil, müsahibə zamanı izah etməyiniz kifayətdir).

## Texniki Məlumat
*   **Topic (Giriş):** `inquiry-input-topic`
*   **Topic (Çıxış):** `approved-inquiries-topic`
*   **Java Version:** 17+
*   **Spring Boot:** 3.x

## İşə Salmaq Üçün
Kompüterinizdə Kafka işlək vəziyyətdə olmalıdır (`localhost:9092`).

```bash
mvn spring-boot:run
```
