package com.vasilis.tmdb.views

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.vasilis.tmdb.MyItemDecoration
import com.vasilis.tmdb.views.shimmer.MovieShimmer

class MovieView(context: Context) : ScrollView(context) {
    var movieMap: MutableMap<String, View> = mutableMapOf<String, View>()
    var linearLayout = LinearLayout(context).apply{id=View.generateViewId()}
    var movieShimmer=MovieShimmer(context)

    init {
        apply{id=View.generateViewId()}
        this.setBackgroundColor(Color.WHITE)
        linearLayout.layoutParams= LayoutParams(MATCH_PARENT, MATCH_PARENT)
        linearLayout.orientation=LinearLayout.VERTICAL

        movieMap["title"] = TextView(context).apply{
            id=View.generateViewId()
            setTextColor(Color.BLACK)
            typeface= Typeface.DEFAULT_BOLD
            textSize=22F
        }
        movieMap["adult"] = TitleDescriptionView(context).vertical()
        movieMap["backdrop"] = ImageView(context).apply{id=View.generateViewId()}
        movieMap["collection"] = CollectionView(context)
        movieMap["budget"] = TitleDescriptionView(context).vertical()
        movieMap["genres"] = TitleDescriptionView(context).vertical()
        movieMap["homepage"] = TitleDescriptionView(context).vertical()
        movieMap["movieID"] = TitleDescriptionView(context).vertical()
        movieMap["originalLanguage"] = TitleDescriptionView(context).vertical()
        movieMap["overview"] = TitleDescriptionView(context).vertical()
        movieMap["popularity"] = TitleDescriptionView(context).vertical()
        movieMap["posterImage"] = ImageView(context).apply{id=View.generateViewId()}
        movieMap["productionCompanies"] = SpecialView(context)
        movieMap["productionCountries"] = TitleDescriptionView(context).vertical()
        movieMap["releaseDate"] = TitleDescriptionView(context).vertical()
        movieMap["revenue"] = TitleDescriptionView(context).vertical()
        movieMap["runtime"] = TitleDescriptionView(context).vertical()
        movieMap["spokenLanguages"] = TitleDescriptionView(context).vertical()
        movieMap["status"] = TitleDescriptionView(context).vertical()
        movieMap["tagline"] = TitleDescriptionView(context).vertical()
        movieMap["voteAverage"] = TitleDescriptionView(context).vertical()
        movieMap["voteCount"] = TitleDescriptionView(context).vertical()
        movieMap["reviewsButton"] = Button(context).apply{
            id=View.generateViewId()
            text="Reviews"
        }

            movieMap.forEach {
                linearLayout.addView(it.value)
            }

        addView(movieShimmer)
    }
}