package com.example.muko.loodos_movearchive.Activities

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.muko.loodos_movearchive.Adapter.MovieRecyclerViewAdapter
import com.example.muko.loodos_movearchive.Model.MovieData
import com.example.muko.loodos_movearchive.Model.MovieUrl
import com.example.muko.loodos_movearchive.R
import kotlinx.android.synthetic.main.activity_search_film.*
import org.json.JSONException
import kotlin.collections.ArrayList

class SearchFilmActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var movieRecyclerViewAdapter: MovieRecyclerViewAdapter? = null
    private var arrayList = ArrayList<MovieData>()//Creating an empty arraylis
    private var queue: RequestQueue? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_film)

        queue = Volley.newRequestQueue(this)

        btnSearch.setOnClickListener {
          var searchingMovie = edtSearch.text.toString()
           getMovies(searchingMovie)
            recyclerView = findViewById(R.id.recyclerViewsearch) as RecyclerView

            recyclerView?.setHasFixedSize(true)
            recyclerView?.setLayoutManager(LinearLayoutManager(this))
            movieRecyclerViewAdapter = MovieRecyclerViewAdapter(arrayList)
            recyclerView?.setAdapter(movieRecyclerViewAdapter)
            movieRecyclerViewAdapter?.notifyDataSetChanged()


        }
    }

    fun getMovies(searchTerm: String) {
        arrayList.clear()
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET,
            MovieUrl().URL_LEFT + searchTerm + MovieUrl().URL_RIGHT + MovieUrl().API_KEY,
            Response.Listener { response ->
                try {
                    val moviesArray = response.getJSONArray("Search")

                    for (i in 0 until moviesArray.length()) {

                        val movieObj = moviesArray.getJSONObject(i)

                        val movie = MovieData()
                        movie.setTitle(movieObj.getString("Title"))
                        movie.setYear("Year Released: " + movieObj.getString("Year"))
                        movie.setMovieType("Type: " + movieObj.getString("Type"))
                        movie.setPoster(movieObj.getString("Poster"))
                        //movie.setImdbId(movieObj.getString("imdbID"))

                        Log.d("Movies Search: ", movie.getTitle())

                        arrayList.add(movie)
                    }
                    movieRecyclerViewAdapter?.notifyDataSetChanged()
                    Log.e("deneme","selam")
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener {})

        queue?.add(jsonObjectRequest)
    }
}
