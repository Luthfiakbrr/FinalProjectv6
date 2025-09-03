# 🧪 Automation Framework - Web UI (SauceDemo) & API (DummyAPI)

## 📌 Ringkasan
Framework ini menggabungkan pengujian:
- **Web UI** → [SauceDemo](https://www.saucedemo.com) (Selenium + Cucumber)
- **API** → [DummyAPI.io](https://dummyapi.io) (RestAssured + Cucumber)

Semua test dijalankan dengan **Gradle**, hasilnya divisualisasikan dengan **Allure Report**, dan otomatis di-*deploy* lewat **GitHub Actions CI/CD**.

---

## ⚙️ Tech Stack
| Komponen      | Teknologi                    |
|---------------|------------------------------|
| Bahasa        | Java 17                      |
| Build Tool    | Gradle                       |
| Testing       | Cucumber (Gherkin) + JUnit4  |
| UI Automation | Selenium WebDriver           |
| API Testing   | RestAssured                  |
| Reporting     | Allure Report                |
| CI/CD         | GitHub Actions               |

---

## 📂 Struktur Proyek
FinalProjectv6
├── .github/workflows/ci-test.yml # Pipeline CI/CD GitHub Actions
├── build.gradle # Konfigurasi Gradle
├── src
│ └── test
│ ├── java/com/sdd
│ │ ├── api/stepdefinitions/UserSteps.java
│ │ ├── ui/stepdefinitions/LoginSteps.java
│ │ ├── ui/pages/... (Page Object Model)
│ │ ├── helpers/ApiHelper.java, PopupHandler.java
│ │ ├── hooks/Hooks.java
│ │ └── runners/
│ │ ├── ApiTestRunner.java
│ │ ├── WebTestRunner.java
│ │ └── CombinedTestRunner.java
│ └── resources/features
│ ├── api/user.feature # Test API (DummyAPI)
│ └── web/login.feature # Test Web UI (SauceDemo)
└── ...

## 🔑 Prasyarat
- **Java 17** (min. 11)
- **Gradle Wrapper** (`./gradlew`)
- **Browser**: Chrome / Firefox / Edge (otomatis via [WebDriverManager](https://github.com/bonigarcia/webdrivermanager))

> Untuk test lokal pastikan browser terinstal. Driver akan otomatis diatur oleh WebDriverManager.

---

## ▶️ Menjalankan Test Lokal

### API Tests
```bash
./gradlew apiTest
Web UI Tests (default: Chrome)
bash
Copy code
./gradlew webTest
Combined Tests (API + Web)
bash
Copy code
./gradlew combinedTest
Menjalankan di browser tertentu
bash
Copy code
./gradlew webTest -Dbrowser=firefox
./gradlew webTest -Dbrowser=edge
Menjalankan di headless mode
bash
Copy code
./gradlew webTest -Dheadless=true
📊 Allure Report
Generate laporan setelah test:

bash
Copy code
./gradlew allureReport
Buka report di browser:

bash
Copy code
./gradlew allureServe
Atau langsung cek hasilnya di:

bash
Copy code
build/reports/allure-report
⚡ CI/CD (GitHub Actions)
Pipeline otomatis (.github/workflows/ci-test.yml) akan:

Menjalankan apiTest, webTest (headless), dan combinedTest

Membuat Allure Report dan upload sebagai artifact

Menyimpan Allure history agar grafik trend test tetap terjaga

Menambahkan summary hasil test di Pull Request

👤 Tester
Nama: Luthfi Akbar