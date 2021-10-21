package com.me.structure.presentation.features.home.model

import androidx.databinding.ViewDataBinding
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.databinding.BR
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding4.view.clicks
import com.me.structure.R
import com.me.structure.databinding.ItemMovieBinding
import com.me.structure.domain.features.movie.model.Movie
import com.me.structure.presentation.features.home.listener.OnMovieListener
import com.me.structure.utils.AppConstConfig.Companion.DELAY_TIME
import java.util.concurrent.TimeUnit

@EpoxyModelClass
abstract class ItemMovieModel : DataBindingEpoxyModel(){
    override fun getDefaultLayout() = R.layout.item_movie

    @EpoxyAttribute
    var movie: Movie? = null

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var listener: OnMovieListener? = null

    override fun setDataBindingVariables(binding: ViewDataBinding?) {
        binding?.setVariable(BR.movie, movie?.title)
    }

    override fun bind(holder: DataBindingHolder) {
        super.bind(holder)
        val binding = holder.dataBinding as ItemMovieBinding
        updateDataView(binding)
    }

    private fun updateDataView(binding: ItemMovieBinding) {

        Glide
            .with(binding.ivPoster.context)
            .load("https://image.tmdb.org/t/p/w500/${movie?.posterPath}")
            .into(binding.ivPoster)

        binding.tvTitle.text = movie?.title


        binding.cardPoster.clicks().throttleFirst(DELAY_TIME, TimeUnit.MILLISECONDS)
            .subscribe {
                movie?.let { it1 -> listener?.goToMovieDetail(it1.id) }
            }


    }
}