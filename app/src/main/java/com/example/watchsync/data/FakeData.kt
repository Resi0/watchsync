package com.example.watchsync.data

import com.example.watchsync.data.model.RatedWatchable
import com.example.watchsync.data.model.RecommendedWatchable
import com.example.watchsync.data.model.SuggestedProfile
import com.example.watchsync.data.model.Tweet
import com.example.watchsync.data.model.User
import com.example.watchsync.data.model.UserComment
import com.example.watchsync.data.model.Watchable
import kotlin.random.Random

object FakeData {

    private val users = listOf(
        User("user_1", "Ahmet Yılmaz", "Bilim kurgu ve karmaşık senaryolar... Beyin yakan filmler favorim.", "https://images.unsplash.com/photo-1539571696357-5a69c17a67c6?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=800&q=80"),
        User("user_2", "Ayşe Kaya", "Bir fincan kahve ve sürükleyici bir drama dizisi... Daha ne olsun?", "https://images.unsplash.com/photo-1524504388940-b1c1722653e1?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=800&q=80"),
        User("user_3", "Zeynep Arslan", "90'lar komedileri ve kült filmler. Eskiler her zaman daha iyiydi!", "https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=800&q=80"),
        User("user_4", "Mehmet Demir", "Aksiyon ve gerilim olmadan bir gün bile geçmez. Bol patlama, bol heyecan!", "https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=800&q=80")
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
        Tweet("tweet_1", users[0], "ahmet_yilmaz", "2s", "Inception'ın finali hakkında saatlerce konuşabilirim. Nolan yine yapmış yapacağını! #inception", watchables.find { it.id == "movie_1" }, true, 120, 12, 34),
        Tweet("tweet_2", users[1], "ayse_kaya", "5dk", "Breaking Bad'i 5. kez bitirdim, hala ilk günkü gibi etkiliyor. Walter White... sen nasıl bir karaktersin. #breakingbad", watchables.find { it.id == "dizi_1" }, false, 560, 88, 102),
        Tweet("tweet_3", users[2], "zeynep_arslan", "20dk", "Friends izleyip keyiflenmek gibisi yok. Günün stresi anında gidiyor. #friends", watchables.find { it.id == "dizi_4" }, false, 34, 2, 5),
        Tweet("tweet_4", users[3], "mehmet_demir", "1s", "The Dark Knight'taki Joker performansı... Heath Ledger'ın üzerine tanımam. Sinema tarihinin en iyi kötü karakteri olabilir mi? #joker", watchables.find { it.id == "movie_2" }, false, 982, 150, 210),
        Tweet("tweet_5", users[0], "ahmet_yilmaz", "3s", "The Mandalorian'ın son bölümü efsaneydi. Grogu çok tatlı değil mi? #starwars", watchables.find { it.id == "dizi_8" }, false, 45, 5, 8),
        Tweet("tweet_6", users[1], "ayse_kaya", "8s", "Fleabag... 4. duvarı yıkan o anlar... Phoebe Waller-Bridge bir dahi. #fleabag", watchables.find { it.id == "dizi_10" }, true, 250, 40, 60),
        Tweet("tweet_7", users[3], "mehmet_demir", "1g", "Dune 2'yi IMAX'te izledim. Görüntüler, sesler... Tam bir sinema şöleni. Villeneuve bu işi biliyor. #dune", watchables.find { it.id == "movie_9" }, false, 1200, 300, 450),
        Tweet("tweet_8", users[2], "zeynep_arslan", "2g", "Pulp Fiction'daki dans sahnesi... Sinema tarihinin en ikonik anlarından. #tarantino", watchables.find { it.id == "movie_10" }, false, 78, 15, 22),
        Tweet("tweet_9", users[0], "ahmet_yilmaz", "3g", "Black Mirror'ın yeni sezonu yine beyin yaktı. Her bölümü ayrı bir film gibi. #netflix", watchables.find { it.id == "dizi_6" }, false, 450, 60, 95),
        Tweet("tweet_10", users[1], "ayse_kaya", "4g", "Chernobyl... İzlerken nefesim kesildi. İnsan hatasının ne kadar büyük felaketlere yol açabileceğinin kanıtı. #hbo", watchables.find { it.id == "dizi_12" }, false, 880, 120, 180),
        Tweet("tweet_11", users[3], "mehmet_demir", "5g", "The Boys dizisi süper kahraman işlerine farklı bir bakış açısı getiriyor. Sert, komik ve acımasız. #theboys", watchables.find { it.id == "dizi_11" }, true, 620, 90, 130)
    )

    private val allComments = listOf(
        UserComment("comment_1", users[0], watchables.find { it.id == "movie_2" }, "Joker rolü efsaneydi!", "2 saat önce"),
        UserComment("comment_2", users[1], watchables.find { it.id == "movie_10" }, "Tarantino yine döktürmüş.", "5 saat önce"),
        UserComment("comment_3", users[2], watchables.find { it.id == "dizi_2" }, "Ejderhalar... daha ne olsun!", "1 gün önce")
    )

    fun getOnboardingLevels(): List<List<Watchable>> = listOf(watchables.shuffled().take(8), watchables.shuffled().take(8), watchables.shuffled().take(8))

    fun getHomeScreenUsers(): List<User> = users.shuffled()

    fun getHomeScreenRecommendations(ratings: Map<String, Int>): List<RecommendedWatchable> {
        if (ratings.isEmpty()) return watchables.shuffled().take(5).map { RecommendedWatchable(it, "Popüler olduğu için %${Random.nextInt(80, 96)} uyumlu") }

        val likedGenres = ratings.filter { it.value == 5 }.mapNotNull { watchables.find { w -> w.id == it.key }?.genres }.flatten()
        val dislikedGenres = ratings.filter { it.value == 1 }.mapNotNull { watchables.find { w -> w.id == it.key }?.genres }.flatten()

        val oylananlar = ratings.keys

        return watchables
            .filter { it.id !in oylananlar }
            .map { watchable ->
                var score = 70 // Taban uyum puanı
                score += watchable.genres.count { it in likedGenres } * 10 // Beğenilen her tür için +10 puan
                score -= watchable.genres.count { it in dislikedGenres } * 15 // Beğenilmeyen her tür için -15 puan
                val finalScore = score.coerceIn(40, 99) // Puanı 40-99 arasında sınırla
                val reason = if (finalScore > 85) "Zevklerinize çok uygun!" else "Buna bir göz atın!"
                RecommendedWatchable(watchable, "%$finalScore uyumlu - $reason")
            }
            .sortedByDescending { recommended -> recommended.reason.filter { it.isDigit() }.toIntOrNull() ?: 0 }
            .take(10)
    }

    fun getExploreTweets(): List<Tweet> = allTweets.shuffled()

    fun getTweetsByCategory(category: String): List<Tweet> = allTweets.filter { it.watchable?.type == category }.shuffled()

    fun getSuggestedProfiles(): List<SuggestedProfile> = users.shuffled().map { user -> SuggestedProfile(user, (22..35).random(), (75..95).random(), watchables.shuffled().take(3)) }

    fun findUserById(id: String): User? = users.find { it.id == id }

    fun getRatingsForUser(userId: String): List<RatedWatchable> = watchables.shuffled().take((5..8).random()).map { RatedWatchable(it, (4..5).random()) }
    
    fun getFollowedUsersComments(): List<UserComment> = allComments.shuffled()

    fun getWatchablesByGenre(genre: String): List<Watchable> = watchables.filter { it.genres.contains(genre) }.shuffled()
}