package com.nicolas.zenos.recyclerview.activity.model;

public class Movie {

    private String movieTitle;
    private String genre;
    private String year;

    public Movie() {

    }

    public Movie(String movieTitle, String genre, String year) {
        this.movieTitle = movieTitle;
        this.genre = genre;
        this.year = year;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
