package com.example.watchsync.data

import com.example.watchsync.data.model.ChatRoom
import com.example.watchsync.data.model.CreditPlan
import com.example.watchsync.data.model.RatedWatchable
import com.example.watchsync.data.model.RecommendedWatchable
import com.example.watchsync.data.model.RoomType
import com.example.watchsync.data.model.SuggestedProfile
import com.example.watchsync.data.model.Tweet
import com.example.watchsync.data.model.User
import com.example.watchsync.data.model.UserComment
import com.example.watchsync.data.model.Watchable
import kotlin.random.Random

object FakeData {

    private val users = listOf(
        User(
            id = "user_1",
            username = "Ahmet Yılmaz",
            bio = "Bilim kurgu ve karmaşık senaryolar... Beyin yakan filmler favorim.",
            profileImageUrl = "https://images.unsplash.com/photo-1539571696357-5a69c17a67c6?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=800&q=80",
            firstName = "Ahmet",
            lastName = "Yılmaz",
            nickname = "ahmet_yilmaz",
            age = 24,
            credits = 150,
            city = "Istanbul",
            country = "Turkey",
            followerIds = listOf("user_2", "user_3", "user_4"),
            followingIds = listOf("user_2", "user_3"),
            profileImages = listOf(
                "https://images.unsplash.com/photo-1539571696357-5a69c17a67c6?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=800&q=80",
                "https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=800&q=80"
            )
        ),
        User(
            id = "user_2",
            username = "Ayşe Kaya",
            bio = "Bir fincan kahve ve sürükleyici bir drama dizisi... Daha ne olsun?",
            profileImageUrl = "https://images.unsplash.com/photo-1524504388940-b1c1722653e1?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=800&q=80",
            firstName = "Ayşe",
            lastName = "Kaya",
            nickname = "ayse_kaya",
            age = 22,
            credits = 200,
            city = "Ankara",
            country = "Turkey",
            followerIds = listOf("user_1", "user_3"),
            followingIds = listOf("user_1", "user_3", "user_4"),
            profileImages = listOf(
                "https://images.unsplash.com/photo-1524504388940-b1c1722653e1?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=800&q=80"
            )
        ),
        User(
            id = "user_3",
            username = "Zeynep Arslan",
            bio = "90'lar komedileri ve kült filmler. Eskiler her zaman daha iyiydi!",
            profileImageUrl = "https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=800&q=80",
            firstName = "Zeynep",
            lastName = "Arslan",
            nickname = "zeynep_arslan",
            age = 26,
            credits = 200,
            city = "Izmir",
            country = "Turkey",
            followerIds = emptyList(),
            followingIds = listOf("user_1", "user_2"),
            profileImages = emptyList()
        ),
        User(
            id = "user_4",
            username = "Mehmet Demir",
            bio = "Aksiyon ve gerilim olmadan bir gün bile geçmez. Bol patlama, bol heyecan!",
            profileImageUrl = "https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=800&q=80",
            firstName = "Mehmet",
            lastName = "Demir",
            nickname = "mehmet_demir",
            age = 25,
            credits = 50,
            city = "Bursa",
            country = "Turkey",
            followerIds = listOf("user_1", "user_2"),
            followingIds = listOf("user_1"),
            profileImages = listOf(
                "https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=800&q=80"
            )
        )
    )

    private val watchables = listOf(
        Watchable("movie_1", "Inception", "https://image.tmdb.org/t/p/w500/oYuLEt3zVCKq57qu2F8dT7NIa6f.jpg", "Film", listOf("Bilim Kurgu", "Aksiyon", "Gerilim")),
        Watchable("movie_2", "The Dark Knight", "https://image.tmdb.org/t/p/w500/qJ2tW6WMUDux911r6m7haRef0WH.jpg", "Film", listOf("Drama", "Aksiyon", "Suç")),
        Watchable("dizi_1", "Breaking Bad", "https://image.tmdb.org/t/p/w500/ggFHVNu6YYI5L9pCfOacjizRGt.jpg", "Dizi", listOf("Drama", "Suç", "Gerilim")),
        Watchable("dizi_2", "Game of Thrones", "https://image.tmdb.org/t/p/w500/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg", "Dizi", listOf("Fantastik", "Drama", "Macera")),
        Watchable("dizi_3", "Stranger Things", "https://image.tmdb.org/t/p/w500/49WJfeN0moxb9IPfGn1XOrothcn.jpg", "Dizi", listOf("Bilim Kurgu", "Korku", "Gizem")),
        Watchable("dizi_4", "Friends", "https://image.tmdb.org/t/p/w500/f496cm9enuEsZkSPzCwnTESEK5s.jpg", "Dizi", listOf("Komedi", "Romantik")),
        Watchable("movie_4", "Parasite", "https://image.tmdb.org/t/p/w500/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg", "Film", listOf("Gerilim", "Komedi", "Drama")),
        Watchable("movie_5", "Interstellar", "https://image.tmdb.org/t/p/w500/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg", "Film", listOf("Bilim Kurgu", "Drama", "Macera")),
        Watchable("dizi_5", "The Office", "https://image.tmdb.org/t/p/w500/7iBenN8I5sXy2vO3iN2I2K3wQ3A.jpg", "Dizi", listOf("Komedi")),
        Watchable("movie_6", "Fight Club", "https://image.tmdb.org/t/p/w500/pB8BM7pdSp6B6Ih7QZ4DrQ3PmJK.jpg", "Film", listOf("Drama")),
        Watchable("dizi_6", "Black Mirror", "https://image.tmdb.org/t/p/w500/5TR6G2Tp1nKgZT5XZdAxj3s5E3B.jpg", "Dizi", listOf("Bilim Kurgu", "Gerilim", "Drama")),
        Watchable("dizi_7", "Sherlock", "https://image.tmdb.org/t/p/w500/7WTsnHkbA0FaG6R9twfFde0I9hl.jpg", "Dizi", listOf("Suç", "Gizem", "Drama")),
        Watchable("movie_8", "The Matrix", "https://image.tmdb.org/t/p/w500/f89JxwIhLENSzLJSfnUTB0jPnpb.jpg", "Film", listOf("Aksiyon", "Bilim Kurgu")),
        Watchable("dizi_8", "The Mandalorian", "https://image.tmdb.org/t/p/w500/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg", "Dizi", listOf("Bilim Kurgu", "Aksiyon", "Macera")),
        Watchable("dizi_9", "Arcane", "https://image.tmdb.org/t/p/w500/fqldf2t8ztc9aiwn3k6mlX3tvRT.jpg", "Dizi", listOf("Animasyon", "Aksiyon", "Bilim Kurgu")),
        Watchable("movie_9", "Dune: Part Two", "https://image.tmdb.org/t/p/w500/8b8R8l88Qje9dn9OE8MTLNbIIeN.jpg", "Film", listOf("Bilim Kurgu", "Macera")),
        Watchable("movie_10", "Pulp Fiction", "https://image.tmdb.org/t/p/w500/d5iIlFn5s0ImszBPY82aA5dA0qq.jpg", "Film", listOf("Suç", "Drama")),
        Watchable("dizi_10", "Fleabag", "https://image.tmdb.org/t/p/w500/2yEdP4fr68X7oB6eMlyw26aIjR.jpg", "Dizi", listOf("Komedi", "Drama")),
        Watchable("movie_11", "Goodfellas", "https://image.tmdb.org/t/p/w500/aKuFiU82s5ISJpGZp7YkIr3kCUd.jpg", "Film", listOf("Drama", "Suç")),
        Watchable("dizi_11", "The Boys", "https://image.tmdb.org/t/p/w500/2zmTngn1tSlAUKht2xB32Q2oWwR.jpg", "Dizi", listOf("Aksiyon", "Komedi", "Suç")),
        Watchable("movie_12", "The Lord of the Rings: The Fellowship of the Ring", "https://image.tmdb.org/t/p/w500/6oom5QYQ2yQTMJIbnvbkBL9cHo6.jpg", "Film", listOf("Fantastik", "Macera", "Aksiyon")),
        Watchable("dizi_12", "Chernobyl", "https://image.tmdb.org/t/p/w500/3S23Dk0Hwmw6Oa1d8o2lXoOd6iI.jpg", "Dizi", listOf("Drama", "Tarih")),
        Watchable("movie_13", "Forrest Gump", "https://image.tmdb.org/t/p/w500/arw2vcBveWOVZr6pxd9XTd1TdQa.jpg", "Film", listOf("Komedi", "Drama", "Romantik"))
    )

    private val allTweets = listOf(
        Tweet("tweet_1", users[0], "ahmet_yilmaz", "2sa önce", "Inception'ın finali hakkında saatlerce konuşabilirim. Nolan yine yapmış yapacağını! #inception", watchables[0], false, 245, 12, 34),
        Tweet("tweet_2", users[1], "ayse_kaya", "5sa önce", "Breaking Bad finali hala beni etkiliyor. Walter White karakteri mükemmel!", watchables[2], true, 189, 8, 22)
    )

    private val allComments = listOf(
        UserComment("comment_1", users[0], watchables[1], "Joker rolü efsaneydi!", "2 saat önce"),
        UserComment("comment_2", users[1], watchables[0], "Beyin yakan filmler...", "5 saat önce")
    )

    private val chatRooms = listOf(
        ChatRoom("room_1", "Game of Thrones Finali Tartışma Odası", "Spoiler içerir!", RoomType.VOICE_CHAT, watchables.find { it.id == "dizi_2" }, "Sezon 8, Bölüm 6", 45, true),
        ChatRoom("room_2", "Friends ile Akşam Keyfi", "1. sezondan başlıyoruz!", RoomType.WATCH_TOGETHER, null, "S01E01", 12, false)
    )

    private val creditPlans = listOf(
        CreditPlan("plan_1", "Başlangıç Paketi", 100, 0, 49.99, "TL"),
        CreditPlan("plan_2", "Popüler Paket", 500, 50, 199.99, "TL", true),
        CreditPlan("plan_3", "Mega Paket", 1000, 150, 349.99, "TL"),
        CreditPlan("plan_4", "Ultra Paket", 2500, 500, 749.99, "TL")
    )

    fun getOnboardingLevels(): List<List<Watchable>> = listOf(watchables.shuffled().take(8), watchables.shuffled().take(8), watchables.shuffled().take(8))
    fun getHomeScreenUsers(): List<User> = users.shuffled()
    fun getHomeScreenRecommendations(ratings: Map<String, Int>): List<RecommendedWatchable> = watchables.shuffled().map { RecommendedWatchable(it, "Popüler olduğu için") }
    fun getExploreTweets(): List<Tweet> = allTweets.shuffled()
    fun getTrendingTweets(): List<Tweet> = allTweets.sortedByDescending { it.likeCount }
    fun getTweetsByCategory(category: String): List<Tweet> = allTweets.filter { it.watchable?.type == category }.shuffled()
    fun getSuggestedProfiles(): List<SuggestedProfile> = users.shuffled().map { user -> 
            SuggestedProfile(
                user = user,
                age = (22..35).random(),
                compatibilityPercentage = (75..95).random(),
                commonWatchables = watchables.shuffled().take(3)
            )
        }
    fun findUserById(id: String): User? = users.find { it.id == id }
    fun getRatingsForUser(userId: String): List<RatedWatchable> = emptyList()
    fun getFollowedUsersComments(): List<UserComment> = allComments.shuffled()
    fun getWatchablesByGenre(genre: String): List<Watchable> = watchables.filter { it.genres.contains(genre) }.shuffled()
    fun getFollowerCount(userId: String): Int = users.find { it.id == userId }?.followerIds?.size ?: 0
    fun getFollowingCount(userId: String): Int = users.find { it.id == userId }?.followingIds?.size ?: 0
    fun getFavoriteWatchables(userId: String): List<Watchable> = watchables.shuffled().take(5)
    fun getSavedWatchables(userId: String): List<Watchable> = watchables.shuffled().take(10)
    fun getTopMatches(userId: String): List<Watchable> = watchables.shuffled().take(5)
    fun getFollowers(userId: String): List<User> = users.filter { it.id in users.find { u -> u.id == userId }?.followerIds.orEmpty() }
    fun getFollowing(userId: String): List<User> = users.filter { it.id in users.find { u -> u.id == userId }?.followingIds.orEmpty() }
    fun getChatRooms(): List<ChatRoom> = chatRooms
    fun getLikedMeUsers(): List<User> = users.shuffled().take(3)
    fun getMyLikedUsers(): List<User> = users.shuffled().take(3)
    fun getAllWatchables(): List<Watchable> = watchables
    
    // EKLENEN FONKSİYON
    fun getCreditPlans(): List<CreditPlan> = creditPlans
}
