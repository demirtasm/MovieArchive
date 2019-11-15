package com.example.muko.loodos_movearchive.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.muko.loodos_movearchive.Model.MovieData
import com.example.muko.loodos_movearchive.Model.MovieUrl
import com.example.muko.loodos_movearchive.R
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.search_view.view.*
import org.json.JSONException
import android.widget.EditText
import android.widget.GridLayout
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.example.muko.loodos_movearchive.Adapter.MovieRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList


class MainActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var movieRecyclerViewAdapter: MovieRecyclerViewAdapter? = null
    private var arrayList = ArrayList<MovieData>()
    private var queue: RequestQueue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        queue = Volley.newRequestQueue(this)
        recyclerView = findViewById(R.id.checkMovie) as RecyclerView

        var myAdapter = MovieRecyclerViewAdapter(arrayList)
        checkMovie.adapter = myAdapter
        var linearLayoutManager = GridLayoutManager(this, 2)
        checkMovie.layoutManager = linearLayoutManager
        checkOutMoves()

        val fab = findViewById<FloatingActionButton>(R.id.fab_search)
        fab.setOnClickListener {
            var intent = Intent(applicationContext, SearchFilmActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mainmenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        var Id = item?.itemId
        when (Id) {
            R.id.menuLinearViewHorizontal -> {
//                var linearLayoutManager =LinearLayoutManager(LinearLayoutManager.HORIZONTAL)
//                checkMovie.layoutManager = linearLayoutManager
            }
            R.id.menuLinearViewVertical -> {}
            R.id.menuGrid -> {}
            R.id.menuStaggeredHorizontal->{}
            R.id.menuStaggeredVertical->{}
        }
        return super.onOptionsItemSelected(item)
    }

    private fun checkOutMoves() {
        var url = "http://www.omdbapi.com/?s=godzilla&apikey=22efe740"
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET,
            url,
            Response.Listener { response ->
                try {
                    val moviesArray = response.getJSONArray("Search")

                    for (i in 0 until moviesArray.length()) {

                        val movieObj = moviesArray.getJSONObject(i)

                        val movie = MovieData()
                        movie.setTitle(movieObj.getString("Title"))
                        movie.setYear("Year : " + movieObj.getString("Year"))
                        movie.setMovieType("Type: " + movieObj.getString("Type"))
                        movie.setPoster(movieObj.getString("Poster"))

                        Log.d("Movies: ", movie.getTitle())

                        arrayList.add(movie)
                    }
                    movieRecyclerViewAdapter?.notifyDataSetChanged()

                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener {

                FancyToast.makeText(this, "Someting went wrong!", FancyToast.LENGTH_LONG, FancyToast.ERROR, false)
                    .show()
            })

        queue?.add(jsonObjectRequest)
    }
}