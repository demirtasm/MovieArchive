package com.example.muko.loodos_movearchive.Singleton

import android.content.Context
import android.graphics.Bitmap
import android.util.LruCache
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.Volley

class MovieSingleton private constructor(context: Context) {
    private var requestQueue: RequestQueue? = null

    init {
        mCtx = context
        requestQueue = getRequestQueue()
    }

    fun getRequestQueue(): RequestQueue? {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(mCtx.applicationContext)
        }
        return requestQueue
    }

    fun <T> addToRequestque(request: Request<T>) {
        requestQueue!!.add(request)
    }

    companion object {
        private var mInstance: MovieSingleton? = null
        lateinit var mCtx: Context
        @Synchronized
        fun getInstance(context: Context): MovieSingleton {
            if (mInstance == null) {
                mInstance = MovieSingleton(context)
            }
            return mInstance as MovieSingleton
        }
    }

}