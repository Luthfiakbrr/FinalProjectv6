# Automation Framework - Web UI (SauceDemo) & API (DummyAPI)

## Ringkasan
Framework ini menggabungkan pengujian Web UI (SauceDemo) dan API (DummyAPI) menggunakan:
- Java
- Gradle
- Cucumber (Gherkin) + JUnit4
- Selenium WebDriver (Chrome)
- RestAssured (API)
- GitHub Actions (CI)

## Struktur
(Lihat struktur file di README sebelumnya)

## Prasyarat
- JDK 11+ (disarankan 17)
- Gradle wrapper (`./gradlew`)
- Chrome + compatible chromedriver (untuk local UI tests), atau jalankan UI tests di container/CI yang sudah terpasang Chrome.
- app-id DummyAPI diset ke: `63a804408eb0cb069b57e43a` (sudah default di ApiHelper)

## Menjalankan test local
API tests:
```bash
./gradlew apiTest
# Laporan: build/reports/cucumber-api.html & cucumber-api.json
