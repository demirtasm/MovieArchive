package com.example.muko.loodos_movearchive.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.android.volley.*
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.muko.loodos_movearchive.Adapter.MovieRecyclerViewAdapter
import com.example.muko.loodos_movearchive.Model.MovieData
import com.example.muko.loodos_movearchive.Model.MovieUrl
import com.example.muko.loodos_movearchive.R
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.activity_search_film.*
import org.json.JSONException
import kotlin.collections.ArrayList

class SearchFilmActivity : AppCompatActivity(){


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

            var myAdapter = MovieRecyclerViewAdapter(arrayList)
            recyclerViewsearch.adapter = myAdapter
            var linearLayoutManager = GridLayoutManager(this, 2)
            recyclerViewsearch.layoutManager = linearLayoutManager


        }
    }

    fun getMovies(searchTerm: String) {
        arrayList.clear()
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET,
            MovieUrl().URL + searchTerm + MovieUrl().API_KEY,
            Response.Listener { response ->
                try {
                    val jresponse:Boolean = response.getBoolean("Response")

                    if (jresponse == true) {
                        val moviesArray = response.getJSONArray("Search")
                        for (i in 0 until moviesArray.length()) {
                            val movieObj = moviesArray.getJSONObject(i)
                            val movie = MovieData()
                            movie.setTitle(movieObj.getString("Title"))
                            movie.setYear("Year Released: " + movieObj.getString("Year"))
                            movie.setMovieType("Type: " + movieObj.getString("Type"))
                            movie.setPoster(movieObj.getString("Poster"))

                            Log.d("Movies Search: ", movie.getTitle())

                            arrayList.add(movie)
                        }
                        movieRecyclerViewAdapter?.notifyDataSetChanged()
                    } else {
                        val searchError = response.getString("Error")
                        FancyToast.makeText(this,searchError, FancyToast.LENGTH_LONG, FancyToast.ERROR,false).show()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                    FancyToast.makeText(this,"Someting went wrong!", FancyToast.LENGTH_LONG, FancyToast.ERROR,false).show()

                }
            }, Response.ErrorListener {

                FancyToast.makeText(this,"Someting went wrong!", FancyToast.LENGTH_LONG, FancyToast.ERROR,false).show()
            })

        queue?.add(jsonObjectRequest)
    }


}
