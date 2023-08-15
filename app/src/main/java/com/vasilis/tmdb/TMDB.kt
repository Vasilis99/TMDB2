package com.vasilis.tmdb

class TMDB {
    data class Token(
        var request_token: String
    )
    data class Session(
        var success: Boolean,
        var session_id: String
    )
    data class Movies(
        var page: Int,
        var results: List<MovieBasic>,
        var total_pages: Int,
        var total_results: Int
    )
    data class MovieBasic(
        var adult: Boolean,
        var backdrop_path: String,
        var genre_ids: List<Int>,
        var id: Int,
        var original_language: String,
        var original_title: String,
        var overview: String,
        var popularity: Double,
        var poster_path: String,
        var release_date: String,
        var title: String,
        var video: Boolean,
        var vote_average: Double,
        var vote_count: Int
    )
    data class MovieDetails(
        var adult: Boolean,
        var backdrop_path: String,
        var belongs_to_collection: BelongsToCollection,
        var budget: Int,
        var genres: List<GenrePair>,
        var homepage: String,
        var id: Int,
        var imdb_id: String,
        var original_language: String,
        var original_title: String,
        var overview: String,
        var popularity: Double,
        var poster_path: String,
        var production_companies: List<ProductionCompany>,
        var production_countries: List<ProductionCountry>,
        var release_date: String,
        var revenue: Int,
        var runtime: Int,
        var spoken_languages: List<SpokenLanguage>,
        var status: String,
        var tagline: String,
        var title: String,
        var video: Boolean,
        var vote_average: Double,
        var vote_count: Int
    )
    data class BelongsToCollection(
        var id: Int,
        var name: String,
        var poster_path: String,
        var backdrop_path: String
    )
    data class ProductionCompany(
        var id: Int,
        var logo_path: String,
        var name: String,
        var origin_country: String
    )
    data class ProductionCountry(
        var iso_3166_1: String,
        var name: String
    )
    data class SpokenLanguage(
        var english_name: String,
        var iso_639_1: String,
        var name: String
    )

    data class GenrePair(
        var id: Int,
        var name: String
    )
    data class TVShows(
        var page: Int,
        var results: List<TVShowBasic>,
        var total_pages: Int,
        var total_results: Int
    )
    data class TVShowBasic(
        var backdrop_path: String,
        var first_air_date: String,
        var genre_ids: List<Int>,
        var id: Int,
        var name: String,
        var origin_country: List<String>,
        var original_language: String,
        var original_name: String,
        var overview: String,
        var popularity: Double,
        var poster_path: String,
        var vote_average: Double,
        var vote_count: Int
    )

    data class TVShowDetails(
        var backdrop_path: String,
        var created_by: List<Creator>,
        var episode_run_time: List<Int>,
        var first_air_date: String,
        var genres: List<GenrePair>,
        var homepage: String,
        var id: Int,
        var inProduction: Boolean,
        var languages: List<String>,
        var last_air_date: String,
        var last_episode_to_air: LastEpisodeAir,
        var name: String,
        var next_episode_to_air: Object,
        var networks: List<Network>,
        var number_of_episodes: Int,
        var number_of_seasons: Int,
        var origin_country: List<String>,
        var original_language: String,
        var original_name: String,
        var overview: String,
        var popularity: Double,
        var poster_path: String,
        var production_companies: List<ProductionCompany>,
        var production_countries: List<ProductionCountry>,
        var seasons: List<Season>,
        var spoken_languages: List<SpokenLanguage>,
        var status: String,
        var tagline: String,
        var type: String,
        var vote_average: Double,
        var vote_count: Int
    )
    data class Creator(
        var id: Int,
        var credit_id: String,
        var name: String,
        var gender: Int,
        var profile_path: String
    )
    data class LastEpisodeAir(
        var air_date: String,
        var episode_number: Int,
        var id: Int,
        var name: String,
        var overview: String,
        var production_code: String,
        var season_number: Int,
        var still_path: String,
        var vote_average: Double,
        var vote_count: Int
    )
    data class Network(
        var name: String,
        var id: String,
        var logo_path: String,
        var origin_country: String
    )
    data class Season(
        var air_date: String,
        var episode_count: Int,
        var id: Int,
        var name: String,
        var overview: String,
        var poster_path: String,
        var season_number: Int
    )
    data class Reviews(
        var id: Int,
        var page: Int,
        var results: List<Review>,
        var total_pages: Int,
        var total_results: Int
    )
    data class Review(
        var author: String,
        var author_details: AuthorDetails,
        var content: String,
        var created_at: String,
        var id: String,
        var updated_at: String,
        var url: String
    )
    data class AuthorDetails(
        var name: String,
        var username: String,
        var avatar: String,
        var rating: Int
    )
}
