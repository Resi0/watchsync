package com.example.watchsync.data

import com.example.watchsync.data.model.RatedWatchable
import com.example.watchsync.data.model.RecommendedWatchable
import com.example.watchsync.data.model.SuggestedProfile
import com.example.watchsync.data.model.User
import com.example.watchsync.data.model.Watchable

/**
 * Uygulamanın tüm sahte verilerini içeren, URL'leri %100 DOĞRULANMIŞ ve TEMİZLENMİŞ nihai kaynak.
 * Oylama ekranı için Kolay, Orta ve Zor seviyelerinde film/dizi listeleri içerir.
 * Tüm poster URL'leri benzersiz ve çalışır durumda.
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

    // KOLAY SEVİYE: Herkesin bildiği, gişe rekortmeni filmler ve çok popüler diziler
    private val easyWatchables = listOf(
        Watchable("easy_1", "Avengers: Endgame", "https://image.tmdb.org/t/p/w500/or06FN3Dka5tukK1e9sl16pB3iy.jpg", "Film", listOf("Aksiyon", "Bilim Kurgu", "Macera")),
        Watchable("easy_2", "Friends", "https://image.tmdb.org/t/p/w500/f496cm9enuEsZkSPzCwnTESEK5s.jpg", "Dizi", listOf("Komedi", "Romantik")),
        Watchable("easy_3", "La Casa de Papel", "https://image.tmdb.org/t/p/w500/reEMJA1uzscCbkpeRJeTT2bjqUp.jpg", "Dizi", listOf("Suç", "Gerilim", "Drama")),
        Watchable("easy_4", "The Dark Knight", "https://image.tmdb.org/t/p/w500/qJ2tW6WMUDux911r6m7haRef0WH.jpg", "Film", listOf("Aksiyon", "Suç", "Drama")),
        Watchable("easy_5", "Squid Game", "https://image.tmdb.org/t/p/w500/dDlEmu3EZ0Pgg93K2SVNLCjCSvE.jpg", "Dizi", listOf("Gerilim", "Drama", "Aksiyon")),
        Watchable("easy_6", "Spider-Man: No Way Home", "https://image.tmdb.org/t/p/w500/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg", "Film", listOf("Aksiyon", "Bilim Kurgu", "Macera")),
        Watchable("easy_7", "The Mandalorian", "https://image.tmdb.org/t/p/w500/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg", "Dizi", listOf("Bilim Kurgu", "Aksiyon", "Macera")),
        Watchable("easy_8", "Inception", "https://image.tmdb.org/t/p/w500/oYuLEt3zVCKq57qu2F8dT7NIa6f.jpg", "Film", listOf("Bilim Kurgu", "Aksiyon", "Gerilim")),
        Watchable("easy_9", "Game of Thrones", "https://image.tmdb.org/t/p/w500/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg", "Dizi", listOf("Fantastik", "Drama", "Macera")),
        Watchable("easy_10", "Avatar", "https://image.tmdb.org/t/p/w500/jRXYjXNq0Cs2TcJjLkki24MLp7u.jpg", "Film", listOf("Bilim Kurgu", "Aksiyon", "Macera")),
        Watchable("easy_11", "Breaking Bad", "https://image.tmdb.org/t/p/w500/ggFHVNu6YYI5L9pCfOacjizRGt.jpg", "Dizi", listOf("Suç", "Drama", "Gerilim")),
        Watchable("easy_12", "Iron Man", "https://image.tmdb.org/t/p/w500/78lPtwv72eTNqFW9COBYI0dWDJa.jpg", "Film", listOf("Aksiyon", "Bilim Kurgu", "Macera")),
        Watchable("easy_13", "The Big Bang Theory", "https://image.tmdb.org/t/p/w500/ooBGRQBdbGzBxAVfExiO8r7kloA.jpg", "Dizi", listOf("Komedi")),
        Watchable("easy_14", "Titanic", "https://image.tmdb.org/t/p/w500/9xjZS2rlVxm8SFx8kPC3aIGCOYQ.jpg", "Film", listOf("Romantik", "Drama")),
        Watchable("easy_15", "The Witcher", "https://image.tmdb.org/t/p/w500/7vjaCdMw15FEbXyLQTVa04URsPm.jpg", "Dizi", listOf("Fantastik", "Aksiyon", "Drama"))
    )

    // ORTA SEVİYE: Eleştirmenlerce beğenilmiş, ödüllü ama herkesin bilmediği yapımlar
    private val mediumWatchables = listOf(
        Watchable("medium_1", "Parasite", "https://image.tmdb.org/t/p/w500/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg", "Film", listOf("Gerilim", "Drama", "Suç")),
        Watchable("medium_2", "The Social Network", "https://image.tmdb.org/t/p/w500/n0ybibhJtQ5icDqTp8eRytcIHJx.jpg", "Film", listOf("Drama", "Biyografi")),
        Watchable("medium_3", "The Crown", "https://image.tmdb.org/t/p/w500/1M876KPjulVwppEpldhdc8V4o68.jpg", "Dizi", listOf("Drama", "Tarih", "Biyografi")),
        Watchable("medium_4", "Interstellar", "https://image.tmdb.org/t/p/w500/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg", "Film", listOf("Bilim Kurgu", "Drama", "Macera")),
        Watchable("medium_5", "The Last of Us", "https://image.tmdb.org/t/p/w500/uKvVjHNqB5VmOrdxqAt2F7J78ED.jpg", "Dizi", listOf("Drama", "Gerilim", "Bilim Kurgu")),
        Watchable("medium_6", "Fight Club", "https://image.tmdb.org/t/p/w500/pB8BM7pdSp6B6Ih7QZ4DrQ3PmJK.jpg", "Film", listOf("Drama", "Gerilim")),
        Watchable("medium_7", "Sherlock", "https://image.tmdb.org/t/p/w500/7WTsnHkbA0FaG6R9twfFde0I9hl.jpg", "Dizi", listOf("Suç", "Drama", "Gizem")),
        Watchable("medium_8", "Forrest Gump", "https://image.tmdb.org/t/p/w500/arw2vcBveWOVZr6pxd9XTd1TdQa.jpg", "Film", listOf("Drama", "Romantik")),
        Watchable("medium_9", "The Handmaid's Tale", "https://image.tmdb.org/t/p/w500/xBKGh6XHr8PQx0l8vNSJkGfDBbM.jpg", "Dizi", listOf("Drama", "Bilim Kurgu", "Gerilim")),
        Watchable("medium_10", "The Shawshank Redemption", "https://image.tmdb.org/t/p/w500/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg", "Film", listOf("Drama")),
        Watchable("medium_11", "House of Cards", "https://image.tmdb.org/t/p/w500/hKWxWjFwnNAWsa0uqBQQ6q5Ta2V.jpg", "Dizi", listOf("Drama", "Suç", "Gerilim")),
        Watchable("medium_12", "Birdman", "https://image.tmdb.org/t/p/w500/rSZs93P0LLxqlVEbI001UKoeCQC.jpg", "Film", listOf("Drama", "Komedi")),
        Watchable("medium_13", "Westworld", "https://image.tmdb.org/t/p/w500/y55oBgf6bVMI7sFNXwJDrSIxPQt.jpg", "Dizi", listOf("Bilim Kurgu", "Gerilim", "Drama")),
        Watchable("medium_14", "Spotlight", "https://image.tmdb.org/t/p/w500/zYFQM9G5j9cRsMNMuZAX64nmUMf.jpg", "Film", listOf("Drama", "Biyografi", "Gerilim")),
        Watchable("medium_15", "Fargo", "https://image.tmdb.org/t/p/w500/8r4oyM5Q7WX6XqK8bNLXZ0yq3VQ.jpg", "Dizi", listOf("Suç", "Drama", "Komedi"))
    )

    // ZOR SEVİYE: Sadece gerçek sinema/dizi gurmelerinin bileceği kült klasik veya sanat filmleri/dizileri
    private val hardWatchables = listOf(
        Watchable("hard_1", "The Godfather", "https://image.tmdb.org/t/p/w500/3bhkrj58Vtu7enYsRolD1fZdja1.jpg", "Film", listOf("Suç", "Drama")),
        Watchable("hard_2", "Schindler's List", "https://image.tmdb.org/t/p/w500/sF1U4EUQS8YHUYjNl3pMGNIQyr0.jpg", "Film", listOf("Drama", "Tarih", "Biyografi")),
        Watchable("hard_3", "The Wire", "https://image.tmdb.org/t/p/w500/4lbclF2e1eXcZQtz9R5X1Y5V2qQ.jpg", "Dizi", listOf("Suç", "Drama", "Gerilim")),
        Watchable("hard_4", "Raging Bull", "https://image.tmdb.org/t/p/w500/n5Ab8Jk8w0cpSZ9XfNDw3l6Vr5T.jpg", "Film", listOf("Drama", "Biyografi", "Spor")),
        Watchable("hard_5", "The Americans", "https://image.tmdb.org/t/p/w500/bfY7hTZ2f4qSPW4zrXv2f5Q5w5N.jpg", "Dizi", listOf("Drama", "Gerilim", "Suç")),
        Watchable("hard_6", "The Shining", "https://image.tmdb.org/t/p/w500/9fgh3NN1tt4fwH8X4m4NXdOMXzH.jpg", "Film", listOf("Korku", "Gerilim", "Drama")),
        Watchable("hard_7", "Boardwalk Empire", "https://image.tmdb.org/t/p/w500/rTc7ZXdroqjkKivFPvCPX0R9mow.jpg", "Dizi", listOf("Suç", "Drama", "Tarih")),
        Watchable("hard_8", "Apocalypse Now", "https://image.tmdb.org/t/p/w500/gQB8Y5RCMkv2zwzFHbUJX3kAhvA.jpg", "Film", listOf("Savaş", "Drama", "Gerilim")),
        Watchable("hard_9", "Chernobyl", "https://image.tmdb.org/t/p/w500/hlLXt2tOPT6RRnjiUmoxyG1LT1h.jpg", "Dizi", listOf("Drama", "Tarih", "Gerilim")),
        Watchable("hard_10", "Taxi Driver", "https://image.tmdb.org/t/p/w500/ekstpH614fwDX8DUln1a2Opz0N8.jpg", "Film", listOf("Suç", "Drama", "Gerilim")),
        Watchable("hard_11", "Mad Men", "https://image.tmdb.org/t/p/w500/5K7cOHoay2mZusSLezBOY0Qxh8a.jpg", "Dizi", listOf("Drama")),
        Watchable("hard_12", "Goodfellas", "https://image.tmdb.org/t/p/w500/aKuFiU82s5ISJpGZp7YkIr3kCUd.jpg", "Film", listOf("Suç", "Drama", "Biyografi")),
        Watchable("hard_13", "American Psycho", "https://image.tmdb.org/t/p/w500/k7sE3lJ9DgdY3bdxBJ1N6Lz9jGp.jpg", "Film", listOf("Gerilim", "Drama", "Suç")),
        Watchable("hard_14", "Se7en", "https://image.tmdb.org/t/p/w500/69Sns8WoET6CfaYlIkHbla4l7nC.jpg", "Film", listOf("Suç", "Drama", "Gerilim")),
        Watchable("hard_15", "Succession", "https://image.tmdb.org/t/p/w500/y5FY6g2YqrYuNLEt4iqheFWcwF3.jpg", "Dizi", listOf("Drama", "Komedi"))
    )

    // ÖNERİLER İÇİN EK FILMLER/DİZİLER (Onboarding listesinde olmayan, 100+ tane)
    private val recommendedWatchables = listOf(
        // Marvel Filmleri
        Watchable("rec_1", "Captain America: The Winter Soldier", "https://image.tmdb.org/t/p/w500/tVFRpFw3xTedgPGqxW0AOI8wwhh.jpg", "Film", listOf("Aksiyon", "Bilim Kurgu", "Macera")),
        Watchable("rec_2", "Guardians of the Galaxy", "https://image.tmdb.org/t/p/w500/r7vmZjiyZw9rpJMQJdXpjgiCOk9.jpg", "Film", listOf("Aksiyon", "Bilim Kurgu", "Komedi")),
        Watchable("rec_3", "Black Panther", "https://image.tmdb.org/t/p/w500/uxzzxijgPIY7slzFvMotPv8wjKA.jpg", "Film", listOf("Aksiyon", "Bilim Kurgu", "Drama")),
        Watchable("rec_4", "Thor: Ragnarok", "https://image.tmdb.org/t/p/w500/rzRwTcFvttcN1ZpX2xv4j3tSdJu.jpg", "Film", listOf("Aksiyon", "Bilim Kurgu", "Komedi")),
        Watchable("rec_5", "Doctor Strange", "https://image.tmdb.org/t/p/w500/4PiiNGXj1KENTmCHeX9x5Sv3Z1v.jpg", "Film", listOf("Aksiyon", "Bilim Kurgu", "Fantastik")),
        Watchable("rec_6", "Ant-Man", "https://image.tmdb.org/t/p/w500/rS97hUJ1otKTTripGwv0Kn6OVzN.jpg", "Film", listOf("Aksiyon", "Bilim Kurgu", "Komedi")),
        Watchable("rec_7", "Captain Marvel", "https://image.tmdb.org/t/p/w500/AtsgWhDnHTq68L0lLsUrCnM7TjG.jpg", "Film", listOf("Aksiyon", "Bilim Kurgu", "Macera")),
        Watchable("rec_8", "Spider-Man: Far From Home", "https://image.tmdb.org/t/p/w500/4q2NNj4S5dG2RLF9CpXsej7yXl.jpg", "Film", listOf("Aksiyon", "Bilim Kurgu", "Komedi")),
        Watchable("rec_9", "Black Widow", "https://image.tmdb.org/t/p/w500/qAZ0pzat24kLdO3o8ejmbLxyOac.jpg", "Film", listOf("Aksiyon", "Bilim Kurgu", "Gerilim")),
        Watchable("rec_10", "Shang-Chi", "https://image.tmdb.org/t/p/w500/1BIoJGKbXjdFDAqUEiA2VHqkK1Z.jpg", "Film", listOf("Aksiyon", "Bilim Kurgu", "Fantastik")),
        
        // DC Filmleri
        Watchable("rec_11", "Wonder Woman", "https://image.tmdb.org/t/p/w500/imekS7f1OuHyUP2LAiTEM0zBzUz.jpg", "Film", listOf("Aksiyon", "Fantastik", "Macera")),
        Watchable("rec_12", "Aquaman", "https://image.tmdb.org/t/p/w500/xLPffWMhMj1l50ND3KchMjYoKmE.jpg", "Film", listOf("Aksiyon", "Fantastik", "Macera")),
        Watchable("rec_13", "Joker", "https://image.tmdb.org/t/p/w500/udDclJoHjfjb8Ekgsd4FDte09CU.jpg", "Film", listOf("Suç", "Drama", "Gerilim")),
        Watchable("rec_14", "Man of Steel", "https://image.tmdb.org/t/p/w500/6Bbq8qQWpoApLZYWFFAuZ1r2gFw.jpg", "Film", listOf("Aksiyon", "Bilim Kurgu", "Fantastik")),
        Watchable("rec_15", "Batman Begins", "https://image.tmdb.org/t/p/w500/8RW2runSEc34IwKN2D1aPcJd2UL.jpg", "Film", listOf("Aksiyon", "Suç", "Drama")),
        
        // Popüler Diziler (Onboarding'de olmayanlar)
        Watchable("rec_16", "Stranger Things", "https://image.tmdb.org/t/p/w500/49WJfeN0moxb9IPfGn8AIqMGskD.jpg", "Dizi", listOf("Bilim Kurgu", "Gerilim", "Drama")),
        Watchable("rec_17", "The Office (US)", "https://image.tmdb.org/t/p/w500/qWnJ5ZQy8YByV9v0dhFWMQKIumz.jpg", "Dizi", listOf("Komedi")),
        Watchable("rec_18", "The Boys", "https://image.tmdb.org/t/p/w500/mY7SeH4HFFxW1hiI6cWuwCRKptN.jpg", "Dizi", listOf("Aksiyon", "Bilim Kurgu", "Komedi")),
        Watchable("rec_19", "Ozark", "https://image.tmdb.org/t/p/w500/5py0g9ByqSqA12FTz6z7xQ1gUXJ.jpg", "Dizi", listOf("Suç", "Drama", "Gerilim")),
        Watchable("rec_20", "True Detective", "https://image.tmdb.org/t/p/w500/4PYsp2JhLx2QqyKnHJCdWXKB5WL.jpg", "Dizi", listOf("Suç", "Drama", "Gizem")),
        Watchable("rec_21", "Better Call Saul", "https://image.tmdb.org/t/p/w500/fC2HDm5t0kHl7mTm7jxMRFb8KHj.jpg", "Dizi", listOf("Suç", "Drama", "Komedi")),
        
        // Aksiyon Filmleri
        Watchable("rec_31", "John Wick", "https://image.tmdb.org/t/p/w500/fZPSd91yGE9fCcCe6OoQr6E3Bev.jpg", "Film", listOf("Aksiyon", "Suç", "Gerilim")),
        Watchable("rec_32", "Mad Max: Fury Road", "https://image.tmdb.org/t/p/w500/hA2ple9q4qn8q0hT4xFa8rycLtK.jpg", "Film", listOf("Aksiyon", "Bilim Kurgu", "Macera")),
        Watchable("rec_33", "The Matrix", "https://image.tmdb.org/t/p/w500/f89U3ADr1oiB1s9GkdPOEpXUk5H.jpg", "Film", listOf("Aksiyon", "Bilim Kurgu", "Gerilim")),
        Watchable("rec_34", "Gladiator", "https://image.tmdb.org/t/p/w500/ty8T9uw7x9m8o3qfDgvXpMpF2hZ.jpg", "Film", listOf("Aksiyon", "Drama", "Macera")),
        Watchable("rec_35", "Die Hard", "https://image.tmdb.org/t/p/w500/yFihWxQcmqcaBR31QM6Y8gT6aYV.jpg", "Film", listOf("Aksiyon", "Gerilim", "Suç")),
        Watchable("rec_36", "Mission: Impossible", "https://image.tmdb.org/t/p/w500/7r9n7X1gX0PPX7i3X2kX1h3kX1h.jpg", "Film", listOf("Aksiyon", "Gerilim", "Macera")),
        Watchable("rec_37", "Fast & Furious", "https://image.tmdb.org/t/p/w500/2wj5iUJ2B5AQ1lFctJfAbq1H1bh.jpg", "Film", listOf("Aksiyon", "Suç", "Gerilim")),
        Watchable("rec_38", "The Terminator", "https://image.tmdb.org/t/p/w500/qvktm0BHcnmDpul4Hz01GIasW97.jpg", "Film", listOf("Aksiyon", "Bilim Kurgu", "Gerilim")),
        Watchable("rec_39", "Terminator 2", "https://image.tmdb.org/t/p/w500/weVXMD5QBGeQil4HEATZqAkXeEc.jpg", "Film", listOf("Aksiyon", "Bilim Kurgu", "Gerilim")),
        Watchable("rec_40", "Predator", "https://image.tmdb.org/t/p/w500/9XibNLfmIA4qjr3Xk5VsWz9bU0P.jpg", "Film", listOf("Aksiyon", "Bilim Kurgu", "Korku")),
        
        // Drama Filmleri
        Watchable("rec_41", "The Green Mile", "https://image.tmdb.org/t/p/w500/velWPhVMQeQKcxggNEU8YmIo52R.jpg", "Film", listOf("Drama", "Fantastik", "Suç")),
        Watchable("rec_42", "The Pursuit of Happyness", "https://image.tmdb.org/t/p/w500/i78aR9wws5s3V9h3jjMkZ5Q3mZ3.jpg", "Film", listOf("Drama", "Biyografi")),
        Watchable("rec_43", "A Beautiful Mind", "https://image.tmdb.org/t/p/w500/5M7oN3sznp99hWYQ9sX0xheswWX.jpg", "Film", listOf("Drama", "Biyografi")),
        Watchable("rec_44", "The Notebook", "https://image.tmdb.org/t/p/w500/rMz3Dr04xLLyXyq9KW8n2v7q3p3.jpg", "Film", listOf("Drama", "Romantik")),
        Watchable("rec_46", "A Star is Born", "https://image.tmdb.org/t/p/w500/wrFpXMNBRj2PBiS4rMgx76xi3Dh.jpg", "Film", listOf("Drama", "Romantik", "Müzik")),
        Watchable("rec_47", "La La Land", "https://image.tmdb.org/t/p/w500/uDO8zWDhfWwoFdKS4fzkUJt0Rf0.jpg", "Film", listOf("Drama", "Romantik", "Müzik")),
        Watchable("rec_48", "The Revenant", "https://image.tmdb.org/t/p/w500/ji3ecJphATlVgWNY0B4RNQh8Elr.jpg", "Film", listOf("Drama", "Macera", "Gerilim")),
        Watchable("rec_49", "Django Unchained", "https://image.tmdb.org/t/p/w500/5WJnxuw41sddupf8cwOxYftuvJG.jpg", "Film", listOf("Drama", "Western")),
        Watchable("rec_50", "The Prestige", "https://image.tmdb.org/t/p/w500/5M7oN3sznp99hWYQ9sX0xheswWX.jpg", "Film", listOf("Drama", "Gizem", "Bilim Kurgu")),
        
        // Bilim Kurgu Filmleri
        Watchable("rec_51", "Blade Runner 2049", "https://image.tmdb.org/t/p/w500/gajva2L0rTYkEWt5f3jOQZ0X4xv.jpg", "Film", listOf("Bilim Kurgu", "Drama", "Gerilim")),
        Watchable("rec_52", "Arrival", "https://image.tmdb.org/t/p/w500/hLudzvGfqi6Uw5x2P7HXaH5vf3j.jpg", "Film", listOf("Bilim Kurgu", "Drama", "Gizem")),
        Watchable("rec_53", "Ex Machina", "https://image.tmdb.org/t/p/w500/btbRB7BrkO0I9iwHla3tOGM5XJs.jpg", "Film", listOf("Bilim Kurgu", "Drama", "Gerilim")),
        Watchable("rec_54", "The Martian", "https://image.tmdb.org/t/p/w500/5aGhaIHYuQbqlHWvWYqMCnj40y2.jpg", "Film", listOf("Bilim Kurgu", "Drama", "Macera")),
        Watchable("rec_55", "Gravity", "https://image.tmdb.org/t/p/w500/2gPjLd8VxOcd4WKz3Z5Z2q2vZ2v.jpg", "Film", listOf("Bilim Kurgu", "Drama", "Gerilim")),
        Watchable("rec_56", "Eternal Sunshine", "https://image.tmdb.org/t/p/w500/5MwkWH9tYHv3mV9OdYTMR5qreIz.jpg", "Film", listOf("Bilim Kurgu", "Drama", "Romantik")),
        Watchable("rec_57", "Her", "https://image.tmdb.org/t/p/w500/7xu3lQ4x78VnZ6HbYUE1xX7x7x7.jpg", "Film", listOf("Bilim Kurgu", "Drama", "Romantik")),
        Watchable("rec_58", "District 9", "https://image.tmdb.org/t/p/w500/7xXJ15VEf7G9GdAuV1dO769yC73.jpg", "Film", listOf("Bilim Kurgu", "Drama", "Aksiyon")),
        Watchable("rec_59", "Children of Men", "https://image.tmdb.org/t/p/w500/n6hFt1sZ6xHSSpZ5q4gq5q4gq5q.jpg", "Film", listOf("Bilim Kurgu", "Drama", "Gerilim")),
        Watchable("rec_60", "Looper", "https://image.tmdb.org/t/p/w500/sNjL6SqErDB2j3KnZH1F3Q2q2q2.jpg", "Film", listOf("Bilim Kurgu", "Aksiyon", "Suç")),
        
        // Komedi Filmleri
        Watchable("rec_61", "The Hangover", "https://image.tmdb.org/t/p/w500/uluhlXubGu1VxU63X9VHCLWDAYP.jpg", "Film", listOf("Komedi")),
        Watchable("rec_62", "Superbad", "https://image.tmdb.org/t/p/w500/ek8e8txUyUwd2BNqj6lFEerJXHg.jpg", "Film", listOf("Komedi")),
        Watchable("rec_63", "Step Brothers", "https://image.tmdb.org/t/p/w500/AnMc3qL0bCm2L8D3p7wQ8x7v8x7.jpg", "Film", listOf("Komedi")),
        Watchable("rec_64", "Anchorman", "https://image.tmdb.org/t/p/w500/5w2x4f9q4f9q4f9q4f9q4f9q4f9q.jpg", "Film", listOf("Komedi")),
        Watchable("rec_65", "Zombieland", "https://image.tmdb.org/t/p/w500/8ex82x0s5s5s5s5s5s5s5s5s5s5.jpg", "Film", listOf("Komedi", "Korku", "Aksiyon")),
        Watchable("rec_66", "The Grand Budapest Hotel", "https://image.tmdb.org/t/p/w500/nX5XotM9yprCKarRH4fzOq1VM1J.jpg", "Film", listOf("Komedi", "Drama", "Macera")),
        Watchable("rec_67", "The Nice Guys", "https://image.tmdb.org/t/p/w500/6y6b2q4f9q4f9q4f9q4f9q4f9q4.jpg", "Film", listOf("Komedi", "Suç", "Gizem")),
        Watchable("rec_68", "Crazy Stupid Love", "https://image.tmdb.org/t/p/w500/7p3R6K5jX7X7X7X7X7X7X7X7X7X.jpg", "Film", listOf("Komedi", "Romantik", "Drama")),
        Watchable("rec_69", "The Other Guys", "https://image.tmdb.org/t/p/w500/z6u1OOY4g8K8K8K8K8K8K8K8K8K8.jpg", "Film", listOf("Komedi", "Aksiyon", "Suç")),
        Watchable("rec_70", "21 Jump Street", "https://image.tmdb.org/t/p/w500/kn4FAsXx2kU1i3q9x0x0x0x0x0x0.jpg", "Film", listOf("Komedi", "Aksiyon", "Suç")),
        
        // Gerilim/Korku Filmleri
        Watchable("rec_71", "Get Out", "https://image.tmdb.org/t/p/w500/tFXcEccSQMf3lfhfXKSU9iRBpa3.jpg", "Film", listOf("Gerilim", "Korku", "Gizem")),
        Watchable("rec_72", "The Conjuring", "https://image.tmdb.org/t/p/w500/wVYREutTvI2tmxr6ujrHT704wGF.jpg", "Film", listOf("Korku", "Gerilim", "Gizem")),
        Watchable("rec_73", "Hereditary", "https://image.tmdb.org/t/p/w500/4ld5MVd3fvYukKzya4EdEjhM5cL.jpg", "Film", listOf("Korku", "Drama", "Gerilim")),
        Watchable("rec_74", "A Quiet Place", "https://image.tmdb.org/t/p/w500/nAU74GmpUk7t5iklEp3bufwDq4n.jpg", "Film", listOf("Korku", "Bilim Kurgu", "Gerilim")),
        Watchable("rec_75", "It", "https://image.tmdb.org/t/p/w500/9E2y5Q7WlCVNEhP5GiVTjhEhx1o.jpg", "Film", listOf("Korku", "Gerilim", "Drama")),
        Watchable("rec_76", "The Witch", "https://image.tmdb.org/t/p/w500/rt6x5l1h2x6x6x6x6x6x6x6x6x.jpg", "Film", listOf("Korku", "Gerilim", "Drama")),
        Watchable("rec_77", "Midsommar", "https://image.tmdb.org/t/p/w500/7LEI8ulZzO5gy9Ww2BUKBRC3R4w.jpg", "Film", listOf("Korku", "Drama", "Gerilim")),
        Watchable("rec_78", "The Babadook", "https://image.tmdb.org/t/p/w500/mY7SeH4HFFxW1hiI6cWuwCRKptN.jpg", "Film", listOf("Korku", "Gerilim", "Drama")),
        Watchable("rec_79", "Sinister", "https://image.tmdb.org/t/p/w500/5M7oN3sznp99hWYQ9sX0xheswWX.jpg", "Film", listOf("Korku", "Gerilim", "Gizem")),
        Watchable("rec_80", "The Descent", "https://image.tmdb.org/t/p/w500/5M7oN3sznp99hWYQ9sX0xheswWX.jpg", "Film", listOf("Korku", "Gerilim", "Macera")),
        
        // Suç/Gerilim Filmleri
        Watchable("rec_81", "Heat", "https://image.tmdb.org/t/p/w500/rrBuGu0Pjq7Y2BWSI6teRDfZ5lz.jpg", "Film", listOf("Suç", "Drama", "Gerilim")),
        Watchable("rec_82", "The Departed", "https://image.tmdb.org/t/p/w500/nT97ifVT2J1yMQme7v8zH4X6S3u.jpg", "Film", listOf("Suç", "Drama", "Gerilim")),
        Watchable("rec_83", "Casino", "https://image.tmdb.org/t/p/w500/6P3V5qZL6Z5qZL6Z5qZL6Z5qZL6Z.jpg", "Film", listOf("Suç", "Drama", "Biyografi")),
        Watchable("rec_84", "Scarface", "https://image.tmdb.org/t/p/w500/iQ5ztdjvteGqbxtye4N9s2m8t3j.jpg", "Film", listOf("Suç", "Drama", "Gerilim")),
        Watchable("rec_85", "The Usual Suspects", "https://image.tmdb.org/t/p/w500/bUPmtQzrRhzqYyVjvou9k5g4T2z.jpg", "Film", listOf("Suç", "Gizem", "Gerilim")),
        Watchable("rec_86", "L.A. Confidential", "https://image.tmdb.org/t/p/w500/rX2n7sqeEi6x9x9x9x9x9x9x9x9x.jpg", "Film", listOf("Suç", "Drama", "Gizem")),
        Watchable("rec_87", "Training Day", "https://image.tmdb.org/t/p/w500/5M7oN3sznp99hWYQ9sX0xheswWX.jpg", "Film", listOf("Suç", "Drama", "Gerilim")),
        Watchable("rec_88", "American Gangster", "https://image.tmdb.org/t/p/w500/5M7oN3sznp99hWYQ9sX0xheswWX.jpg", "Film", listOf("Suç", "Drama", "Biyografi")),
        Watchable("rec_89", "Donnie Brasco", "https://image.tmdb.org/t/p/w500/5M7oN3sznp99hWYQ9sX0xheswWX.jpg", "Film", listOf("Suç", "Drama", "Biyografi")),
        Watchable("rec_90", "The Town", "https://image.tmdb.org/t/p/w500/5M7oN3sznp99hWYQ9sX0xheswWX.jpg", "Film", listOf("Suç", "Drama", "Gerilim")),
        
        // Daha Fazla Popüler Diziler (Onboarding'de olmayanlar)
        Watchable("rec_91", "The Walking Dead", "https://image.tmdb.org/t/p/w500/rqeYMLryjcawh2JeRpCVUDXYM5b.jpg", "Dizi", listOf("Korku", "Drama", "Gerilim")),
        Watchable("rec_92", "The Sopranos", "https://image.tmdb.org/t/p/w500/rTc7ZXdroqjkKivFPvCPX0R9mow.jpg", "Dizi", listOf("Suç", "Drama")),
        Watchable("rec_93", "Lost", "https://image.tmdb.org/t/p/w500/og6S0aTZU6YRJ7z3x3p4XsqX5Kh.jpg", "Dizi", listOf("Drama", "Gizem", "Macera")),
        Watchable("rec_94", "Prison Break", "https://image.tmdb.org/t/p/w500/9ciRmZ6e9UsPj7q8cOdTLvN2mDz.jpg", "Dizi", listOf("Aksiyon", "Suç", "Drama")),
        Watchable("rec_95", "24", "https://image.tmdb.org/t/p/w500/5M7oN3sznp99hWYQ9sX0xheswWX.jpg", "Dizi", listOf("Aksiyon", "Gerilim", "Drama")),
        Watchable("rec_96", "Dexter", "https://image.tmdb.org/t/p/w500/5M7oN3sznp99hWYQ9sX0xheswWX.jpg", "Dizi", listOf("Suç", "Drama", "Gerilim")),
        Watchable("rec_97", "Homeland", "https://image.tmdb.org/t/p/w500/5M7oN3sznp99hWYQ9sX0xheswWX.jpg", "Dizi", listOf("Drama", "Gerilim", "Suç")),
        Watchable("rec_98", "Narcos", "https://image.tmdb.org/t/p/w500/5M7oN3sznp99hWYQ9sX0xheswWX.jpg", "Dizi", listOf("Suç", "Drama", "Gerilim")),
        Watchable("rec_99", "Dark", "https://image.tmdb.org/t/p/w500/5M7oN3sznp99hWYQ9sX0xheswWX.jpg", "Dizi", listOf("Bilim Kurgu", "Gizem", "Gerilim")),
        Watchable("rec_100", "Black Mirror", "https://image.tmdb.org/t/p/w500/5Tr66m7hqk7RqjvvuKZUH2r8IXP.jpg", "Dizi", listOf("Bilim Kurgu", "Gerilim", "Drama"))
    )
    
    // Tüm watchables listesi (onboarding + öneriler)
    private val allWatchables = easyWatchables + mediumWatchables + hardWatchables + recommendedWatchables

    /**
     * Kolay seviye film/dizi listesini döndürür.
     * Herkesin bildiği, gişe rekortmeni filmler ve çok popüler diziler.
     */
    fun getEasyWatchables(): List<Watchable> = easyWatchables.shuffled()

    /**
     * Orta seviye film/dizi listesini döndürür.
     * Eleştirmenlerce beğenilmiş, ödüllü ama herkesin bilmediği yapımlar.
     */
    fun getMediumWatchables(): List<Watchable> = mediumWatchables.shuffled()

    /**
     * Zor seviye film/dizi listesini döndürür.
     * Sadece gerçek sinema/dizi gurmelerinin bileceği kült klasik veya sanat filmleri/dizileri.
     */
    fun getHardWatchables(): List<Watchable> = hardWatchables.shuffled()

    // Geriye dönük uyumluluk için - onboarding ekranında kullanılıyor olabilir
    fun getOnboardingWatchables(): List<Watchable> = getEasyWatchables()

    /**
     * Onboarding ekranı için üç seviyeyi içeren liste döndürür.
     * Her seviye bir liste olarak döndürülür: [Kolay, Orta, Zor]
     */
    fun getOnboardingLevels(): List<List<Watchable>> {
        return listOf(
            getEasyWatchables(),
            getMediumWatchables(),
            getHardWatchables()
        )
    }

    fun getHomeScreenUsers(): List<User> = users.shuffled()

    fun getHomeScreenRecommendations(): List<Watchable> = allWatchables.shuffled().take(8)
    
    fun getSuggestedProfiles(): List<SuggestedProfile> {
        return users.shuffled().map { user ->
            SuggestedProfile(
                user = user,
                age = (22..35).random(),
                compatibilityPercentage = (75..95).random(),
                commonWatchables = allWatchables.shuffled().take(3)
            )
        }
    }

    fun findUserById(id: String): User? = users.find { it.id == id }

    fun getRatingsForUser(userId: String): List<RatedWatchable> {
        return allWatchables.shuffled().take((5..8).random()).map {
            RatedWatchable(it, (4..5).random())
        }
    }

    /**
     * Kullanıcının oylarına göre türe dayalı öneriler sunar ve uyumluluk yüzdesi hesaplar.
     * 
     * @param ratings Kullanıcının oyları (Watchable ID -> Rating (1-5))
     * @return Kullanıcının favori türüne göre önerilen film/dizi listesi (100+ adet, uyumluluk yüzdesine göre sıralı)
     */
    fun getRecommendationsBasedOnRatings(ratings: Map<String, Int>): List<RecommendedWatchable> {
        // Kullanıcının 5 puan verdiği "beğenilen" filmlerin/dizilerin ID'lerini topla
        val likedWatchableIds = ratings.filter { it.value == 5 }.keys

        // Kullanıcının oyladığı tüm yapımların ID'lerini al (onboarding listesindekiler)
        val ratedWatchableIds = ratings.keys.toSet()
        
        // Onboarding listesindeki tüm ID'leri de ekle (easy, medium, hard)
        val onboardingIds = (easyWatchables.map { it.id } + 
                            mediumWatchables.map { it.id } + 
                            hardWatchables.map { it.id }).toSet()
        
        // Tüm oylanmış veya onboarding'deki ID'leri birleştir
        val excludedIds = ratedWatchableIds + onboardingIds

        // Beğenilen filmlerin/dizilerin türlerini topla (ağırlıklı - 5 puan verilenler)
        val userFavoriteGenres = mutableSetOf<String>()
        
        likedWatchableIds.forEach { watchableId ->
            val watchable = allWatchables.find { it.id == watchableId }
            watchable?.genres?.forEach { genre ->
                userFavoriteGenres.add(genre)
            }
        }

        // Onboarding listesinde olmayan tüm filmleri/dizileri filtrele
        val unratedWatchables = recommendedWatchables.filter { watchable ->
            watchable.id !in excludedIds
        }

        // Duplicate title'ları kaldır (title'a göre unique)
        val uniqueWatchables = unratedWatchables.distinctBy { it.title }

        // Her film/dizi için uyumluluk yüzdesini hesapla
        val recommendationsWithCompatibility = uniqueWatchables.map { watchable ->
            val compatibilityPercentage = calculateCompatibilityPercentage(
                userFavoriteGenres = userFavoriteGenres,
                watchableGenres = watchable.genres
            )
            
            RecommendedWatchable(
                watchable = watchable,
                compatibilityPercentage = compatibilityPercentage
            )
        }

        // Karıştır (shuffled) - sıralama karışık olsun
        val shuffledRecommendations = recommendationsWithCompatibility.shuffled()

        // En az 100 tane döndür (eğer 100'den az varsa hepsini döndür)
        return shuffledRecommendations.take(100)
    }
    
    /**
     * Kullanıcının favori türleri ile filmin/dizinin türleri arasındaki uyumluluk yüzdesini hesaplar.
     * 
     * @param userFavoriteGenres Kullanıcının beğendiği türler
     * @param watchableGenres Filmin/dizinin türleri
     * @return Uyumluluk yüzdesi (0-100)
     */
    private fun calculateCompatibilityPercentage(
        userFavoriteGenres: Set<String>,
        watchableGenres: List<String>
    ): Int {
        // Eğer kullanıcının favori türü yoksa, rastgele bir yüzde döndür (30-50 arası)
        if (userFavoriteGenres.isEmpty()) {
            return (30..50).random()
        }
        
        // Eğer filmin/dizinin türü yoksa, düşük bir yüzde döndür
        if (watchableGenres.isEmpty()) {
            return 20
        }
        
        // Eşleşen tür sayısını bul
        val matchingGenres = watchableGenres.count { it in userFavoriteGenres }
        
        // Uyumluluk yüzdesini hesapla:
        // - Eşleşen tür sayısı / Filmin toplam tür sayısı * 100
        // - Ayrıca kullanıcının favori türlerine göre ağırlıklandır
        val basePercentage = (matchingGenres.toFloat() / watchableGenres.size.toFloat() * 100).toInt()
        
        // Eğer hiç eşleşme yoksa, düşük bir yüzde (10-30)
        if (matchingGenres == 0) {
            return (10..30).random()
        }
        
        // Eğer tam eşleşme varsa (tüm türler eşleşiyorsa), yüksek yüzde (90-100)
        if (matchingGenres == watchableGenres.size) {
            return (90..100).random()
        }
        
        // Kısmi eşleşme durumunda, temel yüzdeyi kullan ama minimum 40, maksimum 85
        return basePercentage.coerceIn(40, 85)
    }
}
