package com.example.watchsync.data.repository

import com.example.watchsync.data.model.Episode
import com.example.watchsync.data.model.Season
import com.example.watchsync.data.model.UserLibrary
import com.example.watchsync.data.model.WatchedContent
import com.example.watchsync.data.model.Watchable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object LibraryRepository {
    private val _userLibrary = MutableStateFlow<UserLibrary>(
        UserLibrary(
            ratedWatchables = emptyList(),
            watchlist = emptyList(),
            watched = emptyList()
        )
    )
    val userLibrary: StateFlow<UserLibrary> = _userLibrary.asStateFlow()

    fun setRatedWatchables(watchables: List<Watchable>) {
        val current = _userLibrary.value
        _userLibrary.value = current.copy(ratedWatchables = watchables)
    }

    fun addRatedWatchable(watchable: Watchable, rating: Int) {
        val current = _userLibrary.value
        // Not: Şu anki UserLibrary yapısında rating değeri saklanmıyor, sadece listeye ekliyoruz.
        // Eğer daha önce eklenmemişse ekle
        if (!current.ratedWatchables.any { it.id == watchable.id }) {
            _userLibrary.value = current.copy(
                ratedWatchables = current.ratedWatchables + watchable
            )
        }
    }

    fun getUserLibrary(): UserLibrary {
        return _userLibrary.value
    }

    fun addToWatchlist(watchable: Watchable) {
        val current = _userLibrary.value
        if (!current.watchlist.any { it.id == watchable.id }) {
            _userLibrary.value = current.copy(
                watchlist = current.watchlist + watchable
            )
        }
    }

    fun removeFromWatchlist(watchableId: String) {
        val current = _userLibrary.value
        _userLibrary.value = current.copy(
            watchlist = current.watchlist.filter { it.id != watchableId }
        )
    }

    fun markAsWatched(watchable: Watchable) {
        val current = _userLibrary.value
        if (!current.watched.any { it.watchable.id == watchable.id }) {
            _userLibrary.value = current.copy(
                watched = current.watched + WatchedContent(
                    watchable = watchable,
                    watchedAt = System.currentTimeMillis()
                )
            )
        }
    }

    fun toggleEpisodeWatched(showId: String, seasonNumber: Int, episodeNumber: Int) {
        val current = _userLibrary.value
        val watchedContent = current.watched.find { it.watchable.id == showId }
        
        if (watchedContent != null) {
            val watchedEpisodes = watchedContent.watchedEpisodes.toMutableMap()
            val seasonEpisodes = watchedEpisodes[seasonNumber]?.toMutableSet() ?: mutableSetOf()
            
            if (seasonEpisodes.contains(episodeNumber)) {
                seasonEpisodes.remove(episodeNumber)
            } else {
                seasonEpisodes.add(episodeNumber)
            }
            
            watchedEpisodes[seasonNumber] = seasonEpisodes
            
            val updatedWatched = current.watched.map {
                if (it.watchable.id == showId) {
                    it.copy(watchedEpisodes = watchedEpisodes)
                } else {
                    it
                }
            }
            
            _userLibrary.value = current.copy(watched = updatedWatched)
        }
    }

    fun getSeasonsForShow(showId: String): List<Season> {
        // Örnek sezon verileri - gerçek uygulamada API'den gelecek
        return listOf(
            Season(
                seasonNumber = 1,
                episodeCount = 10,
                episodes = (1..10).map { 
                    Episode(
                        episodeNumber = it,
                        title = "Bölüm $it",
                        duration = 45,
                        isWatched = false
                    )
                }
            ),
            Season(
                seasonNumber = 2,
                episodeCount = 10,
                episodes = (1..10).map { 
                    Episode(
                        episodeNumber = it,
                        title = "Bölüm $it",
                        duration = 45,
                        isWatched = false
                    )
                }
            ),
            Season(
                seasonNumber = 3,
                episodeCount = 8,
                episodes = (1..8).map { 
                    Episode(
                        episodeNumber = it,
                        title = "Bölüm $it",
                        duration = 50,
                        isWatched = false
                    )
                }
            )
        )
    }
}
