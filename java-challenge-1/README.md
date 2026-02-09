# Java Kod Refaktorinq Tapşırığı

## Ssenari
Siz maliyyə hesablama məntiqi köhnəlmiş və etibarsız olan bir layihəyə qoşulmusunuz. Tətbiq tez-tez "crash" olurvə borc hesablamaları dəqiqlik xətaları səbəbindən riyazi olaraq yanlışdır.

## Tapşırığınız
`LoanService.java` klasını müasir korporativ standartlara uyğun şəkildə yenidən yazın.

## Həll Edilməli Problemlər
1.  **Stabillik (Stability)**: Əgər daxil olan siyahı (list) `null` olarsa və ya içində `null` elementlər olarsa, tətbiq sınır (`NullPointerException` xətası verir).
2.  **Biznes Məntiqi (Business Logic)**: Hazırda kod *bütün* kreditlərin balansını toplayır. Yalnız statusu `ACTIVE` (aktiv) olan kreditlər toplanmalıdır.
3.  **Dəqiqlik (Precision)**: Kod valyuta hesablamaları üçün `double` istifadə edir, bu da "floating-point" xətalarına səbəb olur. Kodu daha düzgün variant ilə dəyişməlisiniz.
4.  **Modernizasiya**: Kodda köhnə üslubda `for` dövrü istifadə olunub.

## Gözlənilən Nəticə
`calculateTotalDebt` metodu:
- double əvəzinə daha düzgün tip qaytarmalıdır.
- "Edge case"-ləri (boş siyahı, null siyahı, null elementlər) xəta vermədən idarə etməlidir.
- Yalnız aktiv kreditlər üçün ümumi borcu dəqiq hesablamalıdır.

## Necə İşə Salmalı
Mövcud vəziyyəti test etmək (kod xəta verəcək) və həllinizi yoxlamaq üçün `Main` klasını aşağıdakı kimi işə sala bilərsiniz:

```bash
mvn clean compile exec:java
```
