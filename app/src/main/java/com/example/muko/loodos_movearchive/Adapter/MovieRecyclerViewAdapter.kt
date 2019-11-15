package com.example.muko.loodos_movearchive.Adapter

import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.muko.loodos_movearchive.Activities.MovieDetailActivity
import com.example.muko.loodos_movearchive.Model.MovieData
import com.example.muko.loodos_movearchive.R
import com.squareup.picasso.Picasso

class MovieRecyclerViewAdapter(allMovies: ArrayList<MovieData>) :
    RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieViewHolder>() {

    var movies = ArrayList<MovieData>()

    init {
        movies = allMovies
    }

    override fun getItemCount(): Int {
        return movies.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        var inflater = LayoutInflater.from(parent.context)
        var moviesView = inflater.inflate(R.layout.movie_row_view, parent, false)
        return MovieViewHolder(moviesView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies.get(position)
        val posterLink = movie.getPoster()

        holder.title.setText(movie.getTitle())
        holder.type.setText(movie.getMovieType())

        Picasso.get()
            .load(posterLink)
            .placeholder(android.R.drawable.ic_btn_speak_now)
            .into(holder.poster)

        holder.year.setText(movie.getYear())
        var oanolıs = movies.get(position)
        holder.itemView.setOnClickListener { v->
            var intent = Intent(v.context, MovieDetailActivity::class.java)
            intent.putExtra("title", movie.getTitle())
            v.context.startActivity(intent)
        }
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var title = itemView.findViewById(R.id.movieTitleID) as TextView
        var poster = itemView.findViewById(R.id.img_movie) as ImageView
        var year = itemView.findViewById(R.id.movieYear) as TextView
        var type = itemView.findViewById(R.id.movieCatID) as TextView

       }
    }
