package com.example.muko.loodos_movearchive.Adapter

import android.content.Context
import android.graphics.Bitmap
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.muko.loodos_movearchive.Model.MovieData
import com.example.muko.loodos_movearchive.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_row_view.view.*

class MovieRecyclerViewAdapter(allMovies: ArrayList<MovieData>): RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieViewHolder>() {
    var movies = ArrayList<MovieData>()

    init {
        movies = allMovies
    }

    override fun getItemCount(): Int { return movies.count() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var moviesView = inflater.inflate(R.layout.movie_row_view, parent, false)
        return MovieViewHolder(moviesView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

//        val posterLink = movies.get(position).getPoster()
//
//       holder.movieTitle.setText(movies.get(position).getTitle());
//        holder.movieType.setText(movies.get(position).getMovieType())
//
//        Picasso.get()
//            .load(posterLink)
//            .placeholder(android.R.drawable.ic_btn_speak_now)
//            .into(holder.moviePoster)
//
//        holder.movieYear.setText(movies.get(position).getYear())
        val movie = movies.get(position)
        val posterLink = movie.getPoster()

        holder.title.setText(movie.getTitle())
        holder.type.setText(movie.getMovieType())

        Picasso.get()
            .load(posterLink)
            .placeholder(android.R.drawable.ic_btn_speak_now)
            .into(holder.poster)

        holder.year.setText(movie.getYear())
    }

    class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

//        var moviesView = itemView as CardView
//
//        var movieTitle = moviesView.movieTitleID
//        var moviePoster = moviesView.img_movie
//        var movieYear = moviesView.movieReleaseID
//        var movieType = moviesView.movieCatID
        var title = itemView.findViewById(R.id.movieTitleID) as TextView
        var poster = itemView.findViewById(R.id.img_movie) as ImageView
        var year = itemView.findViewById(R.id.movieReleaseID) as TextView
        var type = itemView.findViewById(R.id.movieCatID) as TextView

    }
}