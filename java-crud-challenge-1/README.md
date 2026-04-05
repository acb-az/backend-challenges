# Java CRUD & Architecture Challenge

Bu layihə işə qəbul prosesi üçün hazırlanmış bir tapşırıqdır. Namizədin Java, Spring Boot, verilənlər bazası ilə işləmə və arxitektura qurma bacarıqlarını yoxlamaq üçün nəzərdə tutulub.

## Tapşırıq

Sizdən **Country** (Ölkə) və **Region** (Region) entitiləri üçün tam bir CRUD (Yarat, Oxu, Yenilə, Sil) tətbiqi hazırlamaq tələb olunur.

### Tələblər:

1.  **Texnologiya:**
    *   Java 21
    *   Spring Boot 3.x
    *   İstənilən Relational Database (H2, PostgreSQL, MySQL və s.)

2.  **Biznes Analitika və Model Tələbləri:**

    Tətbiq aşağıdakı entitilər və onların arasındakı əlaqələr üzərində qurulmalıdır. Bütün zəruri (Required) sahələr üçün validasiya mütləqdir.

    #### 2.1 Country (Ölkə)
    *   `id`: Unikal identifikator (Long).
    *   `name`: Ölkənin adı.
        *   Zəruridir.
        *   Uzunluq: 2 - 100 simvol.
    *   `code`: Ölkənin beynəlxalq kodu (məs: "AZE", "TUR").
        *   Zəruridir.
        *   Uzunluq: tam 3 simvol.
    *   `population`: Əhali sayı.
        *   Zəruridir.
        *   Minimum: 1.
    *   `area`: Sahəsi (kv. km).
        *   Könüllüdür.
        *   Minimum: 0.1.

    #### 2.2 Region (Region/Rayon)
    *   `id`: Unikal identifikator (Long).
    *   `name`: Regionun adı.
        *   Zəruridir.
        *   Uzunluq: 3 - 50 simvol.
    *   `description`: Region haqqında qısa məlumat.
        *   Könüllüdür.
        *   Uzunluq: max 255 simvol.
    *   `country_id`: Aid olduğu ölkə.
        *   Zəruridir.

    #### 2.3 Əlaqə (Relation)
    *   `Country` və `Region` arasında **One-to-Many** əlaqəsi olmalıdır (Bir ölkədə çoxlu regionlar/rayonlar ola bilər).

3.  **Xüsusi Biznes Tələbi (Business Rule):**
    *   Eyni ölkə daxilində eyni `name` (region adı) ilə ikinci bir region yaradıla bilməz.
    *   Ölkə silindikdə, ona bağlı olan bütün regionlar da sistemdən silinməlidir (Cascade Delete).
    *   Fərdi region silinə bilər, lakin bu zaman bağlı olduğu Ölkə silinməməlidir.

4.  **Arxitektura:**
    *   Namizəd öz istədiyi arxitekturanı (Layered, Hexagonal, Clean Architecture və s.) tətbiq edə bilər. Seçimini əsaslandırması müsbət qarşılanacaq.

5.  **Performans (N+1 Problemi):**
    *   Tətbiqdə elə bir endpoint əlavə edilməlidir ki, orada `Country` siyahısı ilə bərabər onlara bağlı olan `Region` məlumatları da gəlsin.
    *   Bu endpoint-də Hibernate-in **N+1 query** probleminin qarşısını necə aldığınızı göstərməlisiniz.

6.  **Əlavə Tələblər (Bonus):**
    *   **Dockerfile:** Layihənin konteynerləşdirilməsi.
    *   **CI/CD:** GitHub Actions və ya bənzəri bir vasitə ilə sadə bir pipeline-ın qurulması.
    *   **Unit & Integration Tests:** Kodun testlərlə əhatə olunması. Validasiyaların və biznes qaydasının test edilməsi tövsiyə olunur.

## Necə başlamalı?

1.  Layihəni fork edin.
2.  `src/main/java/az/acb/crud/challenge` qovluğu altında öz arxitekturanıza uyğun paketlər yaradın.
3.  README faylında qurduğunuz arxitektura və N+1 həlli barədə qısa qeydlər əlavə edin.

Uğurlar!
