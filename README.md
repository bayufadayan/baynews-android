# BayNews 📰

Aplikasi berita Android modern yang menampilkan berita bisnis terkini dari seluruh dunia menggunakan News API. Dibangun dengan Jetpack Compose dan mengikuti best practices Android development.

## ✨ Fitur

- **Top Headlines**: Menampilkan 5 berita utama bisnis dalam format carousel
- **News Feed**: Daftar berita bisnis dengan infinite scroll/pagination
- **Detail Artikel**: Tampilan detail berita dengan gambar dan konten lengkap
- **Profile**: Halaman profil pengembang dengan link ke repository dan LinkedIn
- **Splash Screen**: Layar pembuka saat aplikasi diluncurkan
- **Offline Error Handling**: Penanganan error dengan opsi retry
- **Material Design 3**: UI modern mengikuti Material Design 3 guidelines

## 🛠️ Teknologi & Library

### Core
- **Kotlin** - Bahasa pemrograman
- **Jetpack Compose** - UI toolkit deklaratif
- **Material Design 3** - Design system

### Architecture & Lifecycle
- **MVVM Architecture** - Pemisahan logika business dan UI
- **ViewModel** - Manajemen state UI
- **StateFlow** - Reactive state management
- **Coroutines** - Asynchronous programming

### Networking
- **Retrofit** - HTTP client
- **Gson Converter** - JSON parsing
- **OkHttp Logging Interceptor** - Network logging

### UI & Navigation
- **Navigation Compose** - Navigasi antar screen
- **Coil** - Image loading library

### API
- **News API** - Sumber data berita (https://newsapi.org)

## 📱 Screenshots & Struktur

### Screens
1. **Splash Screen** - Animasi pembuka
2. **Home Screen** - Top headlines + news feed dengan pagination
3. **Detail Screen** - Detail artikel dengan gambar dan konten
4. **Profile Screen** - Profil pengembang

### Arsitektur Package
```
com.example.baynews
├── data
│   ├── remote
│   │   ├── ApiClient.kt           # Retrofit configuration
│   │   ├── NewsApiService.kt      # API endpoints
│   │   ├── NewsDto.kt             # Data transfer objects
│   │   └── NewsResponse.kt        # API response models
│   ├── NewsRepository.kt          # Data repository
│   └── NewsMapper.kt              # DTO to Domain mapping
├── domain
│   └── Article.kt                 # Domain model
├── screens
│   ├── components
│   │   └── NewsComponents.kt      # Reusable UI components
│   ├── SplashScreen.kt
│   ├── HomeScreen.kt
│   ├── DetailScreen.kt
│   └── ProfileScreen.kt
├── ui
│   ├── home
│   │   ├── HomeViewModel.kt       # Home screen state management
│   │   └── HomeUiState.kt         # UI state data class
│   └── theme
│       ├── Color.kt
│       ├── Theme.kt
│       └── Type.kt
├── BayNewsApp.kt                  # Navigation setup
└── MainActivity.kt                # Entry point
```

## 🚀 Setup & Installation

### Prerequisites
- Android Studio Ladybug atau lebih baru
- JDK 11 atau lebih tinggi
- Android SDK API 24 (minimum) - API 36 (target)
- News API Key dari [newsapi.org](https://newsapi.org)

### Langkah Instalasi

1. **Clone repository**
   ```bash
   git clone https://github.com/bayufadayan/baynews-android.git
   cd baynews-android
   ```

2. **Dapatkan News API Key**
   - Daftar gratis di [https://newsapi.org](https://newsapi.org)
   - Copy API key Anda

3. **Konfigurasi API Key**
   - Buat/edit file `local.properties` di root project
   - Tambahkan API key:
   ```properties
   sdk.dir=C\:\\Users\\YourName\\AppData\\Local\\Android\\Sdk
   NEWS_API_KEY=your_api_key_here
   ```
   > ⚠️ **Penting**: Jangan commit file `local.properties` ke Git

4. **Sync & Build**
   - Buka project di Android Studio
   - Sync Gradle: `File > Sync Project with Gradle Files`
   - Build project: `Build > Make Project`

5. **Run**
   - Jalankan di emulator atau device fisik
   - Minimal Android 7.0 (API 24)

## 🔧 Konfigurasi

### Build Configuration
- **Namespace**: `com.example.baynews`
- **Compile SDK**: 36
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 36
- **JVM Target**: 11
- **Kotlin Version**: 2.0.21

### API Endpoints
```kotlin
// Top Headlines
GET /v2/top-headlines?category=business&pageSize=5

// Everything (Feed)
GET /v2/everything?q=business&page={page}&pageSize=10
```

## 📝 Cara Penggunaan

1. **Buka aplikasi** - Splash screen akan tampil
2. **Home Screen** - Scroll top headlines secara horizontal
3. **News Feed** - Scroll vertical untuk berita lebih banyak (auto-load pagination)
4. **Klik artikel** - Lihat detail lengkap dengan gambar
5. **Profile** - Tap icon profil di top bar untuk melihat info pengembang

## 🏗️ Build & Deploy

### Debug Build
```bash
./gradlew assembleDebug
```

### Release Build
```bash
./gradlew assembleRelease
```

APK akan tersedia di: `app/build/outputs/apk/release/`

## 🧪 Testing

### Run Unit Tests
```bash
./gradlew test
```

### Run Instrumented Tests
```bash
./gradlew connectedAndroidTest
```

## 📦 Dependencies

Lihat detail di [gradle/libs.versions.toml](gradle/libs.versions.toml) dan [app/build.gradle.kts](app/build.gradle.kts)

Dependency utama:
- androidx.core:core-ktx:1.17.0
- androidx.compose.bom:2024.09.00
- androidx.navigation:navigation-compose:2.8.7
- androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7
- com.squareup.retrofit2:retrofit:2.11.0
- io.coil-kt:coil-compose:2.7.0

## 🤝 Contributing

Kontribusi selalu diterima! Untuk perubahan besar, silakan buka issue terlebih dahulu untuk diskusi.

1. Fork repository
2. Create feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Open Pull Request

## 👨‍💻 Developer

**Muhamad Bayu Fadayan (Bay)**
- Computer Science Student
- Email: bayu.065121100@unpak.ac.id
- GitHub: [@bayufadayan](https://github.com/bayufadayan)
- LinkedIn: [muhamad-bayu-fadayan](https://linkedin.com/in/muhamad-bayu-fadayan)
- Repository: [baynews-android](https://github.com/bayufadayan/baynews-android)

## 📄 License

Project ini dibuat untuk keperluan pembelajaran dan portfolio.

## 🙏 Acknowledgments

- [News API](https://newsapi.org) - Sumber data berita
- [Material Design 3](https://m3.material.io) - Design guidelines
- [Jetpack Compose](https://developer.android.com/jetpack/compose) - UI toolkit
- Komunitas Android Developer Indonesia

## 📞 Support

Jika ada pertanyaan atau masalah:
- Buka [issue](https://github.com/bayufadayan/baynews-android/issues)
- Email: bayu.065121100@unpak.ac.id

---

**⭐ Jika project ini bermanfaat, jangan lupa beri star di repository!**
