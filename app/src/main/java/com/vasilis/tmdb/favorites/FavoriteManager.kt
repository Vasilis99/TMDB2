package com.vasilis.tmdb.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager

class FavoriteManager(val app: Application) : AndroidViewModel(app) {
    private var favTvShows = MutableLiveData<List<Triple<Int, String, String>>>()
    private var favMovies = MutableLiveData<List<Triple<Int, String, String>>>()
    private fun storeMovieFavorites(movie: Triple<Int, String, String>) {
        val shared = PreferenceManager.getDefaultSharedPreferences(getApplication())
        var set = listOf(movie.second, movie.third).toSet()
        shared.edit().putStringSet("m${movie.first}", set).apply()
    }

    private fun deleteStoredMovieFavorites(movieID: Int) {
        val shared = PreferenceManager.getDefaultSharedPreferences(getApplication())
        shared.edit().remove("m$movieID").apply()
    }

    private fun getStoredMoviesFavorites(): List<Triple<Int, String, String>> {
        val shared = PreferenceManager.getDefaultSharedPreferences(getApplication())
        val moviesFavorites: MutableList<Triple<Int, String, String>> = mutableListOf()
        for (x in shared.all) {
            if (x.key.first() == 'm') {
                var movieID = x.key.substring(1, x.key.length).toInt()
                var set = x.value as Set<String>
                var name = ""
                var image = ""
                for (i in set) {
                    if (i.length > 4) {
                        if (i.substring(i.length - 4, i.length) == ".jpg")
                            image = i
                        else
                            name = i
                    } else {
                        name = i
                    }
                }
                var triple = Triple(movieID, name, image)
                moviesFavorites.add(triple)
            }
        }
        return moviesFavorites
    }

    public fun getMoviesFavorites(): MutableLiveData<List<Triple<Int, String, String>>> {
        if (favMovies.value == null) {
            favMovies = MutableLiveData<List<Triple<Int, String, String>>>()
            favMovies.value = getStoredMoviesFavorites()
        }
        return favMovies
    }

    public fun addMovieFavorites(tvShow: Triple<Int, String, String>) {
        storeMovieFavorites(tvShow)
        var list=getMoviesFavorites().value!!.toMutableList()
        list.add(tvShow)
        favMovies.value = list
        println("Add " + favMovies.value)
    }

    public fun deleteMovieFavorites(tvShow: Triple<Int, String, String>) {
        deleteStoredMovieFavorites(tvShow.first)
        var list = getMoviesFavorites().value!!.toMutableList()
        list.remove(tvShow)
        favMovies.value = list
        println("delete " + favMovies.value)
    }
    private fun storeTVShowFavorites(tvShow: Triple<Int, String, String>) {
        val shared = PreferenceManager.getDefaultSharedPreferences(getApplication())
        var set = listOf(tvShow.second, tvShow.third).toSet()
        shared.edit().putStringSet("t${tvShow.first}", set).apply()
    }

    private fun deleteStoredTVShowsFavorites(tvShowID: Int) {
        val shared = PreferenceManager.getDefaultSharedPreferences(getApplication())
        shared.edit().remove("t$tvShowID").apply()
    }

    private fun getStoredTVShowsFavorites(): MutableList<Triple<Int, String, String>> {
        val shared = PreferenceManager.getDefaultSharedPreferences(getApplication())
        val tvShowsFavorites: MutableList<Triple<Int, String, String>> = mutableListOf()
        for (x in shared.all) {
            if (x.key.first() == 't') {
                var tvShowID = x.key.substring(1, x.key.length).toInt()
                var set = x.value as Set<String>
                var name = ""
                var image = ""
                for (i in set) {
                    if (i.length > 4) {
                        if (i.substring(i.length - 4, i.length) == ".jpg")
                            image = i
                        else
                            name = i
                    } else {
                        name = i
                    }
                }
                var triple = Triple(tvShowID, name, image)
                tvShowsFavorites.add(triple)
            }
        }
        return tvShowsFavorites
    }

    public fun getTVShowsFavorites(): MutableLiveData<List<Triple<Int, String, String>>> {
        if (favTvShows.value == null) {
            favTvShows = MutableLiveData<List<Triple<Int, String, String>>>()
            favTvShows.value = getStoredTVShowsFavorites()
        }
        return favTvShows
    }

    public fun addTVShowFavorites(tvShow: Triple<Int, String, String>) {
        storeTVShowFavorites(tvShow)
        var list=getTVShowsFavorites().value!!.toMutableList()
        list.add(tvShow)
        favTvShows.value = list
        println("Add " + favTvShows.value)
    }

    public fun deleteTVShowFavorites(tvShow: Triple<Int, String, String>) {
        deleteStoredTVShowsFavorites(tvShow.first)
        var list = getTVShowsFavorites().value!!.toMutableList()
        list.remove(tvShow)
        favTvShows.value = list
        println("delete " + favTvShows.value)
    }
}