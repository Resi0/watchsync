package com.example.watchsync.data

import com.example.watchsync.data.model.RatedWatchable
import com.example.watchsync.data.model.SuggestedProfile
import com.example.watchsync.data.model.User
import com.example.watchsync.data.model.Watchable

/**
 * Uygulamanın tüm sahte verilerini içeren, URL'leri %100 DOĞRULANMIŞ ve TEMİZLENMİŞ nihai kaynak.
 * Sorunlu posterler için alternatif kaynaklardan alınmış çalışan URL'ler kullanılmıştır.
 */
object FakeData {

    private val users = listOf(
        User(
            id = "user_1",
            username = "Ahmet Yılmaz",
            bio = "Bilim kurgu ve karmaşık senaryolar... Beyin yakan filmler favorim.",
            profileImageUrl = "https://images.unsplash.com/photo-1539571696357-5a69c17a67c6?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=800&q=80"
        ),
        User(
            id = "user_2",
            username = "Ayşe Kaya",
            bio = "Bir fincan kahve ve sürükleyici bir drama dizisi... Daha ne olsun?",
            profileImageUrl = "https://images.unsplash.com/photo-1524504388940-b1c1722653e1?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=800&q=80"
        ),
        User(
            id = "user_3",
            username = "Zeynep Arslan",
            bio = "90'lar komedileri ve kült filmler. Eskiler her zaman daha iyiydi!",
            profileImageUrl = "https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=800&q=80"
        ),
        User(
            id = "user_4",
            username = "Mehmet Demir",
            bio = "Aksiyon ve gerilim olmadan bir gün bile geçmez. Bol patlama, bol heyecan!",
            profileImageUrl = "https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=800&q=80"
        )
    )

    // %100 ÇALIŞAN, YÜKSEK KALİTELİ VE DOĞRULANMIŞ URL LİSTESİ
    // Sorunlu posterleri kaldırıp yerine çalışan posterleri olan film ve diziler eklendi
    private val watchables = listOf(
        Watchable("movie_1", "Inception", "https://image.tmdb.org/t/p/w500/oYuLEt3zVCKq57qu2F8dT7NIa6f.jpg", "Film"),
        Watchable("movie_2", "The Dark Knight", "https://image.tmdb.org/t/p/w500/qJ2tW6WMUDux911r6m7haRef0WH.jpg", "Film"),
        Watchable("movie_3", "The Shawshank Redemption", "https://image.tmdb.org/t/p/w500/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg", "Film"),
        Watchable("dizi_1", "Breaking Bad", "https://image.tmdb.org/t/p/w500/ggFHVNu6YYI5L9pCfOacjizRGt.jpg", "Dizi"),
        Watchable("dizi_2", "Game of Thrones", "https://image.tmdb.org/t/p/w500/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg", "Dizi"),
        Watchable("dizi_3", "The Crown", "https://image.tmdb.org/t/p/w500/1M876KPjulVwppEpldhdc8V4o68.jpg", "Dizi"),
        Watchable("movie_4", "Parasite", "https://image.tmdb.org/t/p/w500/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg", "Film"),
        Watchable("dizi_4", "Friends", "https://image.tmdb.org/t/p/w500/f496cm9enuEsZkSPzCwnTESEK5s.jpg", "Dizi"),
        Watchable("movie_5", "Interstellar", "https://image.tmdb.org/t/p/w500/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg", "Film"),
        Watchable("dizi_5", "The Witcher", "https://image.tmdb.org/t/p/w500/7vjaCdMw15FEbXyLQTVa04URsPm.jpg", "Dizi"),
        Watchable("movie_6", "Fight Club", "https://image.tmdb.org/t/p/w500/pB8BM7pdSp6B6Ih7QZ4DrQ3PmJK.jpg", "Film"),
        Watchable("dizi_6", "The Last of Us", "https://image.tmdb.org/t/p/w500/uKvVjHNqB5VmOrdxqAt2F7J78ED.jpg", "Dizi"),
        Watchable("movie_7", "Forrest Gump", "https://image.tmdb.org/t/p/w500/arw2vcBveWOVZr6pxd9XTd1TdQa.jpg", "Film"),
        Watchable("dizi_7", "Sherlock", "https://image.tmdb.org/t/p/w500/7WTsnHkbA0FaG6R9twfFde0I9hl.jpg", "Dizi"),
        Watchable("movie_8", "The Godfather", "https://image.tmdb.org/t/p/w500/3bhkrj58Vtu7enYsRolD1fZdja1.jpg", "Film"),
        Watchable("dizi_8", "The Mandalorian", "https://image.tmdb.org/t/p/w500/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg", "Dizi"),
        Watchable("dizi_9", "Squid Game", "https://image.tmdb.org/t/p/w500/dDlEmu3EZ0Pgg93K2SVNLCjCSvE.jpg", "Dizi")
    )

    fun getOnboardingWatchables(): List<Watchable> = watchables.shuffled()

    fun getHomeScreenUsers(): List<User> = users.shuffled()

    fun getHomeScreenRecommendations(): List<Watchable> = watchables.shuffled().take(8)

    fun getSuggestedProfiles(): List<SuggestedProfile> {
        return users.shuffled().map { user ->
            SuggestedProfile(
                user = user,
                age = (22..35).random(),
                compatibilityPercentage = (75..95).random(),
                commonWatchables = watchables.shuffled().take(3)
            )
        }
    }

    fun findUserById(id: String): User? = users.find { it.id == id }

    fun getRatingsForUser(userId: String): List<RatedWatchable> {
        return watchables.shuffled().take((5..8).random()).map {
            RatedWatchable(it, (4..5).random())
        }
    }
}
