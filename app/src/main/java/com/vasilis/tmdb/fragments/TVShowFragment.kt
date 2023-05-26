package com.vasilis.tmdb.fragments

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.vasilis.tmdb.*
import com.vasilis.tmdb.adapters.TVShowCreatorAdapter
import com.vasilis.tmdb.adapters.TVShowProductionCompanyAdapter
import com.vasilis.tmdb.views.TVShowLastEpisodeView
import com.vasilis.tmdb.views.SpecialView
import com.vasilis.tmdb.views.TVShowView
import com.vasilis.tmdb.views.TitleDescriptionView


class TVShowFragment : Fragment() {
    private var tID: Int = 0
    private lateinit var tvShowDetails: TMDB.TVShowDetails

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("TV show fragment")
        val bundle = arguments
        if (bundle != null && bundle.containsKey("tvShowID")) {
            tID = bundle.getInt("tvShowID")

        } else if (bundle == null) {
            Toast.makeText(activity, "Error", Toast.LENGTH_LONG).show();
        }

        //val latestFragment= ViewModelProvider(this)[FragmentsViewModel::class.java]
       // latestFragment.addFragment(this)
    }

    override fun onDestroy() {
        super.onDestroy()

        println("On destroy tvshow fragment")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = TVShowView(inflater.context).apply {
        lifecycleScope.launchWhenResumed {
           tvShowsShimmer.startShimmer()
            val myApi = RetrofitHelper.getInstance().create(MyApi::class.java)
            var response = myApi.getTVShowDetails(tID)
            tvShowDetails = response.body()!!
            (tvShowViews[0] as TextView).text = tvShowDetails.name
            var image=tvShowViews[1] as ImageView

            image.load("https://image.tmdb.org/t/p/original" + tvShowDetails.backdrop_path){
                placeholder(R.drawable.my_placeholder)
            }
            println("height="+image.height+"\nwidth="+image.width)
            var createdBy = tvShowViews[2] as SpecialView
            createdBy.title.text="Creators"
            if (tvShowDetails.created_by.isEmpty()) {
                createdBy.addView(createdBy.unknown)
            } else {
                createdBy.addView(createdBy.recyclerView)
                createdBy.recyclerView.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                createdBy.recyclerView.adapter =
                    TVShowCreatorAdapter(tvShowDetails.created_by)
            }
            var epRunTimeString = ""
            for ((i,x) in tvShowDetails.episode_run_time.withIndex()) {
                epRunTimeString +=if(i!=tvShowDetails.episode_run_time.size-1) "$x\n" else x
            }
            if(tvShowDetails.episode_run_time.isEmpty())
                epRunTimeString="Unknown"
            var epRunTime = (tvShowViews[3] as TitleDescriptionView)
            epRunTime.title.text = "Episode run time"
            epRunTime.desc.text = epRunTimeString
            var firstAirDate = tvShowViews[4] as TitleDescriptionView
            firstAirDate.title.text = "First air date"
            firstAirDate.desc.text = tvShowDetails.first_air_date

            var genresString = ""
            for ((i,x) in tvShowDetails.genres.withIndex()) {
                genresString += if(i!=tvShowDetails.genres.size-1) "${x.name}\n" else x.name
            }
            var genres = tvShowViews[5] as TitleDescriptionView
            genres.title.text = "Genres"
            genres.desc.text = genresString
            var homepage = tvShowViews[6] as TitleDescriptionView
            homepage.title.text = "Homepage"

            val spannableString = SpannableString("Go to homepage")
            spannableString.setSpan(UnderlineSpan(),0,spannableString.length,0)
            homepage.desc.apply{setTextColor(Color.BLUE)}
            homepage.desc.text = spannableString
            homepage.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(tvShowDetails.homepage)))
            }
            var tvShowID = tvShowViews[7] as TitleDescriptionView
            tvShowID.title.text = "TV Show ID"
            tvShowID.desc.text = tvShowDetails.id.toString()
            var production = tvShowViews[8] as TitleDescriptionView
            production.title.text = "In production"
            production.desc.text = (if (tvShowDetails.inProduction) "Yes" else "No")

            var lang = ""
            for ((i,x) in tvShowDetails.languages.withIndex()) {
                lang += if(i!=tvShowDetails.languages.size-1) "${x}\n" else x
            }
            var languages = tvShowViews[9] as TitleDescriptionView
            languages.title.text = "languages"
            languages.desc.text = lang

            var lastAirDate = tvShowViews[10] as TitleDescriptionView
            lastAirDate.title.text = "Last air date"
            lastAirDate.desc.text = tvShowDetails.last_air_date
            var lastEpisodeAir = tvShowViews[11] as TVShowLastEpisodeView
            lastEpisodeAir.title.text="Last Episode to Air"
            if (tvShowDetails.last_episode_to_air != null) {
                var airDate = lastEpisodeAir.lastEpViews[0] as TitleDescriptionView
                airDate.title.text = "Air Date"
                airDate.desc.text = tvShowDetails.last_episode_to_air.air_date

                var episodeNumber = lastEpisodeAir.lastEpViews[1] as TitleDescriptionView
                episodeNumber.title.text = "Episode number"
                episodeNumber.desc.text =
                    tvShowDetails.last_episode_to_air.episode_number.toString()

                var episodeName = lastEpisodeAir.lastEpViews[2] as TitleDescriptionView
                episodeName.title.text = "Episode name"
                episodeName.desc.text = tvShowDetails.last_episode_to_air.name

                var seasonNumber = lastEpisodeAir.lastEpViews[3] as TitleDescriptionView
                seasonNumber.title.text = "Season number"
                seasonNumber.desc.text = tvShowDetails.last_episode_to_air.season_number.toString()

                var voteAverage = lastEpisodeAir.lastEpViews[4] as TitleDescriptionView
                voteAverage.title.text = "Vote average"
                voteAverage.desc.text = tvShowDetails.last_episode_to_air.vote_average.toString()

                var voteCount = lastEpisodeAir.lastEpViews[5] as TitleDescriptionView
                voteCount.title.text = "Vote count"
                voteCount.desc.text = tvShowDetails.last_episode_to_air.vote_count.toString()

                var lastEpisodeOverview = lastEpisodeAir.overview as TitleDescriptionView
                lastEpisodeOverview.title.text = "Overview"
                lastEpisodeOverview.desc.text = tvShowDetails.last_episode_to_air.overview

                lastEpisodeAir.image.load("https://image.tmdb.org/t/p/original" + tvShowDetails.last_episode_to_air.still_path){

                }

                lastEpisodeAir.addView(lastEpisodeAir.scrollView)


            } else {
                lastEpisodeAir.addView(lastEpisodeAir.unknown)
            }

            var networks = tvShowViews[12] as TitleDescriptionView
            networks.title.text = "Networks"
            var networksString=""

           for ((i,x) in tvShowDetails.networks.withIndex()) {
               networksString += if(i!=tvShowDetails.networks.size-1){
                   x.name+"\n"
               } else{
                   x.name
               }
           }
            if(tvShowDetails.networks.isEmpty()){
                networks.desc.text="Unknown"
            }
            else{
                networks.desc.text=networksString
            }
            var numberOfEpisodes=tvShowViews[13] as TitleDescriptionView
            numberOfEpisodes.title.text="Number of episodes"
            numberOfEpisodes.desc.text=tvShowDetails.number_of_episodes.toString()

            var numberOfSeasons=tvShowViews[14] as TitleDescriptionView
            numberOfSeasons.title.text="Number of seasons"
            numberOfSeasons.desc.text=tvShowDetails.number_of_seasons.toString()

            var originCountries = tvShowViews[15] as TitleDescriptionView
            originCountries.title.text = "Origin country"
            var countriesString=""

            for ((i,x) in tvShowDetails.origin_country.withIndex()) {
                countriesString += if(i!=tvShowDetails.origin_country.size-1){
                    x+"\n"
                } else{
                    x
                }
            }
            if(tvShowDetails.origin_country.isEmpty()){
                originCountries.desc.text="Unknown"
            }
            else{
                originCountries.desc.text=countriesString
            }
            var originalLanguage = tvShowViews[16] as TitleDescriptionView
            originalLanguage.title.text = "Original language"
            originalLanguage.desc.text=tvShowDetails.original_language

            var overview = tvShowViews[17] as TitleDescriptionView
            overview.title.text = "Overview"
            overview.desc.text=tvShowDetails.overview

            var popularity = tvShowViews[18] as TitleDescriptionView
            popularity.title.text = "Popularity"
            popularity.desc.text=tvShowDetails.popularity.toString()

            var poster = tvShowViews[19] as ImageView

            poster.load("https://image.tmdb.org/t/p/original" + tvShowDetails.poster_path){
                placeholder(R.drawable.my_placeholder)
            }

            var productionCompanies = (tvShowViews[20] as SpecialView)
            productionCompanies.title.text="Production companies"
            if (tvShowDetails.production_companies.isEmpty()) {
                productionCompanies.addView(productionCompanies.unknown)

            } else {
                productionCompanies.addView(productionCompanies.recyclerView)
                productionCompanies.recyclerView.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                productionCompanies.recyclerView.adapter =
                    TVShowProductionCompanyAdapter(tvShowDetails.production_companies)

            }

            var productionCountries=tvShowViews[21] as TitleDescriptionView
            productionCountries.title.text="Production countries"
            var prodCountStr=""

            for ((i,x) in tvShowDetails.production_countries.withIndex()) {
                prodCountStr += if(i!=tvShowDetails.production_countries.size-1){
                    x.name+"\n"
                } else{
                    x.name
                }
            }
            if(tvShowDetails.production_countries.isEmpty()){
                productionCountries.desc.text="Unknown"
            }
            else{
                productionCountries.desc.text=prodCountStr
            }

            var spokenLanguages=tvShowViews[22] as TitleDescriptionView
            spokenLanguages.title.text="Spoken languages"
            var spokenLangStr=""

            for ((i,x) in tvShowDetails.spoken_languages.withIndex()) {
                spokenLangStr += if(i!=tvShowDetails.spoken_languages.size-1){
                    x.english_name+"\n"
                } else{
                    x.english_name
                }
            }
            if(tvShowDetails.spoken_languages.isEmpty()){
                spokenLanguages.desc.text="Unknown"
            }
            else{
                spokenLanguages.desc.text=spokenLangStr
            }


            var status=tvShowViews[23] as TitleDescriptionView
            status.title.text="Status"
            status.desc.text=tvShowDetails.status

            var tagline=tvShowViews[24] as TitleDescriptionView
            tagline.title.text="Tagline"
            tagline.desc.text=tvShowDetails.tagline

            var type=tvShowViews[25] as TitleDescriptionView
            type.title.text="Type"
            type.desc.text=tvShowDetails.type

            var voteAverage=tvShowViews[26] as TitleDescriptionView
            voteAverage.title.text="Vote average"
            voteAverage.desc.text=tvShowDetails.vote_average.toString()

            var voteCount=tvShowViews[27] as TitleDescriptionView
            voteCount.title.text="Vote count"
            voteCount.desc.text=tvShowDetails.vote_count.toString()

            var button=tvShowViews[28] as Button
            tvShowsShimmer.stopShimmer()
            removeView(tvShowsShimmer)
            addView(linearLayout)

            button.setOnClickListener {
                println(poster.width.toString()+'\n'+poster.height)
                var tvShowReviewsFragment =
                    TVShowReviewsFragment.newInstance(tvShowDetails.id, tvShowDetails.name)

                (activity as? MainActivity)?.myLayout?.id?.let { it1 ->

                    var transaction =
                        activity?.supportFragmentManager?.beginTransaction()
                    transaction?.add(it1, tvShowReviewsFragment)?.commit()
                    transaction?.addToBackStack(null)
                }
            }


        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    companion object {
        @JvmStatic
        fun newInstance(id: Int) =
            TVShowFragment().apply {
                arguments = Bundle().apply {
                    putInt("tvShowID", id)
                }
            }
    }
}