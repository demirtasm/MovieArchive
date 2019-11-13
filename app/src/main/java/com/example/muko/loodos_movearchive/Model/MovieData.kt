package com.example.muko.loodos_movearchive.Model

import java.io.Serializable

class MovieData : Serializable {
    private var title: String? = null
    private var director: String? = null
    private var year: String? = null
    private var runTime: String? = null
    private var imdbId: String? = null
    private var poster: String? = null
    private var genre: String? = null
    private var writer: String? = null
    private var actors: String? = null
    private var plot: String? = null
    private var rating: String? = null
    private var dvdRelease: String? = null
    private var productionCompany: String? = null
    private var country: String? = null
    private var awards: String? = null
    private var tvRated: String? = null
    private var movieType: String? = null


    fun Movie() {
    }
        fun getTitle(): String? {
            return title
        }

        fun setTitle(title: String) {
            this.title = title
        }

        fun getDirector(): String? {
            return director
        }

        fun setDirector(director: String) {
            this.director = director
        }

        fun getYear(): String? {
            return year
        }

        fun setYear(year: String) {
            this.year = year
        }

        fun getRunTime(): String? {
            return runTime
        }

        fun setRunTime(runTime: String) {
            this.runTime = runTime
        }

        fun getImdbId(): String? {
            return imdbId
        }

        fun setImdbId(imdbId: String) {
            this.imdbId = imdbId
        }

        fun getPoster(): String? {
            return poster
        }

        fun setPoster(poster: String) {
            this.poster = poster
        }

        fun getGenre(): String? {
            return genre
        }

        fun setGenre(genre: String) {
            this.genre = genre
        }

        fun getWriter(): String? {
            return writer
        }

        fun setWriter(writer: String) {
            this.writer = writer
        }

        fun getActors(): String? {
            return actors
        }

        fun setActors(actors: String) {
            this.actors = actors
        }

        fun getPlot(): String? {
            return plot
        }

        fun setPlot(plot: String) {
            this.plot = plot
        }

        fun getRating(): String? {
            return rating
        }

        fun setRating(rating: String) {
            this.rating = rating
        }

        fun getDvdRelease(): String? {
            return dvdRelease
        }

        fun setDvdRelease(dvdRelease: String) {
            this.dvdRelease = dvdRelease
        }

        fun getProductionCompany(): String? {
            return productionCompany
        }

        fun setProductionCompany(productionCompany: String) {
            this.productionCompany = productionCompany
        }

        fun getCountry(): String? {
            return country
        }

        fun setCountry(country: String) {
            this.country = country
        }

        fun getAwards(): String? {
            return awards
        }

        fun setAwards(awards: String) {
            this.awards = awards
        }

        fun getTvRated(): String? {
            return tvRated
        }

        fun setTvRated(tvRated: String) {
            this.tvRated = tvRated
        }

        fun getMovieType(): String? {
            return movieType
        }

        fun setMovieType(movieType: String) {
            this.movieType = movieType
        }
    }