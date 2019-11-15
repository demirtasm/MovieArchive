package com.example.muko.loodos_movearchive.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.*
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.muko.loodos_movearchive.Adapter.MovieRecyclerViewAdapter
import com.example.muko.loodos_movearchive.Model.MovieData
import com.example.muko.loodos_movearchive.Model.MovieUrl
import com.example.muko.loodos_movearchive.R
import com.shashank.sony.fancytoastlib.FancyToast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.activity_search_film.*
import kotlinx.android.synthetic.main.movie_row_view.*
import org.json.JSONException
import org.json.JSONObject

class MovieDetailActivity : AppCompatActivity() {

    private var movie: MovieData? = null
    private var movieTitle: TextView? = null
    private var movieYear: TextView? = null
    private var movieID: TextView? = null
    private var movieType: TextView?= null
    private var movieImage: ImageView?= null
    private var movieGenre : TextView?= null
    private var movieRuntime : TextView?= null
    private var movieDirector : TextView?= null
    private var movieWriter : TextView?= null
    private var movieActors : TextView?= null
    private var moviePlot : TextView?= null
    private var movieLanguage : TextView?= null
    private var movieImdbRating : TextView?= null
    private var movieProductions : TextView?= null



    private var queue: RequestQueue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        var gelenIntent = intent

        queue = Volley.newRequestQueue(this)

        if (gelenIntent != null) {
            tv_title.text = gelenIntent.getStringExtra("title")
            setUpUI()
            setup(gelenIntent.getStringExtra("title").toString())


            Log.e("gelen veri: ", gelenIntent.getStringExtra("title").toString())
        }
    }

    private fun setup(name: String) {

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET,
            MovieUrl().URL + name + MovieUrl().API_KEY, Response.Listener { response ->

                try {
                    val jresponse: Boolean = response.getBoolean("Response")

                    movieTitle?.setText(response.getString("Title"))
                    movieYear?.setText(response.getString("Year"))
                    movieID?.setText(response.getString("imdbID"))
                    movieType?.setText(response.getString("Type"))
                    //movieImage?.setImageResource()
                    movieGenre?.setText(response.getString("Genre"))
                    movieRuntime?.setText(response.getString("Runtime"))
                    movieWriter?.setText(response.getString("Writer"))
                    movieActors?.setText(response.getString("Actors"))
                    moviePlot?.setText(response.getString("Plot"))
                    movieLanguage?.setText(response.getString("Language"))
                    movieImdbRating?.setText(response.getString("imdbRating"))
                    movieProductions?.setText(response.getString("Production"))

                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener { error -> VolleyLog.d("Error:", error.message) })
        queue?.add(jsonObjectRequest)

    }

    private fun setUpUI() {

        movieTitle = findViewById(R.id.tv_title) as TextView
        movieYear = findViewById(R.id.tv_year) as TextView
        movieID = findViewById(R.id.tv_imdbid) as TextView
        movieType = findViewById(R.id.tv_type) as TextView
        //movieImage = findViewById(R.id.img_detail_movie) as ImageView
        movieGenre = findViewById(R.id.tv_genre) as TextView
        movieRuntime = findViewById(R.id.tv_runtime) as TextView
        movieDirector = findViewById(R.id.tv_director) as TextView
        movieWriter = findViewById(R.id.tv_writer) as TextView
        movieActors = findViewById(R.id.tv_actors) as TextView
        moviePlot = findViewById(R.id.tv_plot) as TextView
        movieLanguage = findViewById(R.id.tv_language) as TextView
        movieImdbRating = findViewById(R.id.tv_imdbRating) as TextView
        movieProductions = findViewById(R.id.tv_imdbProdictions) as TextView
    }
}