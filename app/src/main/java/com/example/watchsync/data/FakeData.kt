package com.example.watchsync.data

import com.example.watchsync.data.model.RatedWatchable
import com.example.watchsync.data.model.User
import com.example.watchsync.data.model.Watchable
import com.example.watchsync.presentation.matches.SuggestedProfile

object FakeData {

    private val allUsers = listOf(
        User(
            id = "user_1",
            username = "Ahmet Yılmaz",
            bio = "Film ve dizi tutkunu. Özellikle bilim kurgu ve drama türlerini seviyorum. Birlikte izleyecek arkadaşlar arıyorum!",
            profileImageUrl = "https://images.unsplash.com/photo-1539571696357-5a69c17a67c6?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=800&q=80"
        ),
        User(
            id = "user_2",
            username = "Ayşe Kaya",
            bio = "Korku filmi kraliçesi. Yeni çıkan her şeyi anında izlerim. Spoiler vermem, söz!",
            profileImageUrl = "https://images.unsplash.com/photo-1524504388940-b1c1722653e1?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=800&q=80"
        ),
        User(
            id = "user_3",
            username = "Mehmet Demir",
            bio = "Sadece Christopher Nolan filmleri için bile yaşayabilirim. Karmaşık senaryolar favorim.",
            profileImageUrl = "https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=800&q=80"
        ),
        User(
            id = "user_4",
            username = "Zeynep Arslan",
            bio = "Romantik komedi ve animasyonlar... Hayata pozitif bakmak için!",
            profileImageUrl = "https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=800&q=80"
        )
    )

    private val allWatchables = listOf(
        Watchable("movie_1", "Inception", "https://image.tmdb.org/t/p/w500/9gk7adHYeDvHkCKDyf1Nh2AEtv6.jpg", "Film"),
        Watchable("movie_2", "The Dark Knight", "https://image.tmdb.org/t/p/w500/qJ2tW6WMUDux911r6m7haRef0WH.jpg", "Film"),
        Watchable("movie_3", "Pulp Fiction", "https://image.tmdb.org/t/p/w500/d5iIlFn5s0ImszBPY82aA5dA0qq.jpg", "Film"),
        Watchable("dizi_1", "Breaking Bad", "https://image.tmdb.org/t/p/w500/ggFHVNu6YYI5L9pCfOacjizRGt.jpg", "Dizi"),
        Watchable("dizi_2", "Game of Thrones", "https://image.tmdb.org/t/p/w500/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg", "Dizi"),
        Watchable("dizi_3", "Stranger Things", "https://image.tmdb.org/t/p/w500/49WJfeN0moxb9IPfGn1XOrothcn.jpg", "Dizi"),
        Watchable("movie_4", "Parasite", "https://image.tmdb.org/t/p/w500/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg", "Film"),
        Watchable("dizi_4", "Friends", "https://image.tmdb.org/t/p/w500/f496cm9enuEsZkSPzCwnTESEK5s.jpg", "Dizi"),
        Watchable("movie_5", "Interstellar", "https://image.tmdb.org/t/p/w500/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg", "Film")
    )

    fun getHomeScreenUsers(): List<User> = allUsers.shuffled()

    fun getHomeScreenRecommendations(): List<Watchable> = allWatchables.shuffled().take(7)

    fun getOnboardingWatchables(): List<Watchable> {
        return listOf(
            Watchable("onboarding_1", "Inception", "https://image.tmdb.org/t/p/w500/9gk7adHYeDvHkCKDyf1Nh2AEtv6.jpg", "Film"),
            Watchable("onboarding_2", "The Dark Knight", "https://image.tmdb.org/t/p/w500/qJ2tW6WMUDux911r6m7haRef0WH.jpg", "Film"),
            Watchable("onboarding_3", "Breaking Bad", "https://image.tmdb.org/t/p/w500/ggFHVNu6YYI5L9pCfOacjizRGt.jpg", "Dizi"),
            Watchable("onboarding_4", "Game of Thrones", "https://image.tmdb.org/t/p/w500/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg", "Dizi"),
            Watchable("onboarding_5", "Stranger Things", "https://image.tmdb.org/t/p/w500/49WJfeN0moxb9IPfGn1XOrothcn.jpg", "Dizi"),
            Watchable("onboarding_6", "Pulp Fiction", "https://image.tmdb.org/t/p/w500/d5iIlFn5s0ImszBPY82aA5dA0qq.jpg", "Film"),
            Watchable("onboarding_7", "Parasite", "https://image.tmdb.org/t/p/w500/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg", "Film"),
            Watchable("onboarding_8", "Friends", "https://image.tmdb.org/t/p/w500/f496cm9enuEsZkSPzCwnTESEK5s.jpg", "Dizi"),
            Watchable("onboarding_9", "Interstellar", "https://image.tmdb.org/t/p/w500/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg", "Film"),
            Watchable("onboarding_10", "The Office", "https://image.tmdb.org/t/p/w500/7iBenN8I5sXy2vO3iN2I2K3wQ3A.jpg", "Dizi"),
            Watchable("onboarding_11", "Fight Club", "https://image.tmdb.org/t/p/w500/pB8BM7pdSp6B6Ih7QZ4DrQ3PmJK.jpg", "Film"),
            Watchable("onboarding_12", "Black Mirror", "https://image.tmdb.org/t/p/w500/5TR6G2Tp1nKgZT5XZdAxj3s5E3B.jpg", "Dizi"),
            Watchable("onboarding_13", "Forrest Gump", "https://image.tmdb.org/t/p/w500/arw2vcBveWOVZr6pxd9XTd1TdQa.jpg", "Film"),
            Watchable("onboarding_14", "Sherlock", "https://image.tmdb.org/t/p/w500/7WTsnHkbA0FaG6R9twfFde0I9hl.jpg", "Dizi"),
            Watchable("onboarding_15", "The Matrix", "https://image.tmdb.org/t/p/w500/f89JxwIhLENSzLJSfnUTB0jPnpb.jpg", "Film")
        ).shuffled()
    }
    
    fun getSuggestedProfiles(): List<SuggestedProfile> {
        return listOf(
            SuggestedProfile(
                user = allUsers[0], // Ahmet
                age = 28,
                compatibilityPercentage = 92,
                commonWatchables = listOf(allWatchables[0], allWatchables[8], allWatchables[1])
            ),
            SuggestedProfile(
                user = allUsers[1], // Ayşe
                age = 25,
                compatibilityPercentage = 88,
                commonWatchables = listOf(allWatchables[5], allWatchables[3], allWatchables[6])
            ),
            SuggestedProfile(
                user = allUsers[2], // Mehmet
                age = 30,
                compatibilityPercentage = 85,
                commonWatchables = listOf(allWatchables[0], allWatchables[1], allWatchables[4])
            ),
             SuggestedProfile(
                user = allUsers[3], // Zeynep
                age = 27,
                compatibilityPercentage = 90,
                commonWatchables = listOf(allWatchables[5], allWatchables[7], allWatchables[4])
            )
        )
    }

    fun findUserById(id: String): User? = allUsers.find { it.id == id }

    fun getRatingsForUser(userId: String): List<RatedWatchable> {
        return when (userId) {
            "user_1" -> listOf(
                RatedWatchable(allWatchables[0], 5), 
                RatedWatchable(allWatchables[4], 5), 
                RatedWatchable(allWatchables[2], 4)
            )
            "user_2" -> listOf(
                RatedWatchable(allWatchables[5], 5), 
                RatedWatchable(allWatchables[6], 5), 
                RatedWatchable(allWatchables[3], 4)
            )
            else -> emptyList()
        }
    }
}