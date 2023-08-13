package com.vasilis.tmdb.tv_show

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.vasilis.tmdb.TMDB

class TVShowProductionCompanyAdapter(var productionCompanies: List<TMDB.ProductionCompany>):RecyclerView.Adapter<TVShowProductionCompanyAdapter.TVShowProductionCompanyViewHolder>() {

        class TVShowProductionCompanyViewHolder(val view: TVShowProductionCompanyViewItem): RecyclerView.ViewHolder(view)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowProductionCompanyViewHolder {
            return TVShowProductionCompanyViewHolder(TVShowProductionCompanyViewItem(parent.context))

        }

        override fun getItemCount(): Int {
            return productionCompanies.size
        }

        override fun onBindViewHolder(holder: TVShowProductionCompanyViewHolder, position: Int) {
            holder.view.name.text=productionCompanies[position].name
            println(holder.view.name.text)
            holder.view.image.load("https://image.tmdb.org/t/p/w500" + productionCompanies[position].logo_path)

        }

}