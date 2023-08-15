package com.vasilis.tmdb

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MyApi {

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey: String, @Query("page") page: Int): Response<TMDB.Movies>

    @GET("authentication/token/new?api_key=287f6ab6616e3724955e2b4c6841ea63")
    suspend fun getToken(): Response<TMDB.Token>

    @POST("authentication/session/new?api_key=287f6ab6616e3724955e2b4c6841ea63")
    suspend fun getSessionID(@Body token: TMDB.Token): Response<TMDB.Session>

    @GET("movie/{id}?api_key=287f6ab6616e3724955e2b4c6841ea63")
    suspend fun getMovieDetails(@Path("id") movieID: Int): Response<TMDB.MovieDetails>

    @GET("tv/top_rated")
    suspend fun getTopTVShows(@Query("api_key") apiKey: String, @Query("page") page: Int): Response<TMDB.TVShows>

    @GET("tv/{id}?api_key=287f6ab6616e3724955e2b4c6841ea63")
    suspend fun getTVShowDetails(@Path("id") tvShowID: Int): Response<TMDB.TVShowDetails>

    @GET("movie")
    suspend fun searchMovie(@Query("api_key") apiKey: String, @Query("query") movie: String): Response<TMDB.Movies>

    @GET("tv")
    suspend fun searchTVShow(@Query("api_key") apiKey: String, @Query("query") tvShow: String): Response<TMDB.TVShows>

    @GET("movie/{movieID}/reviews?api_key=287f6ab6616e3724955e2b4c6841ea63")
    suspend fun getMovieReviews(@Path("movieID") movieID: Int): Response<TMDB.Reviews>

    @GET("tv/{tvShowID}/reviews?api_key=287f6ab6616e3724955e2b4c6841ea63")
    suspend fun getTVShowReviews(@Path("tvShowID") tvShowID: Int): Response<TMDB.Reviews>
}
