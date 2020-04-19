package com.example.movieboxproject.Data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "movies", foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userID", onDelete = ForeignKey.CASCADE))
public class Movie implements Serializable {


    @PrimaryKey(autoGenerate = true)
    public int id;

    public int userID;

    private String title;
    private String director;
    private String year;
    private String genre;
    private String rating;

    public Movie() {

    }

    public Movie(String title, String director, String year, String genre, String rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.genre = genre;
        this.rating = rating;
    }

    protected Movie(Parcel in) {
        title = in.readString();
        director = in.readString();
        year = in.readString();
        genre = in.readString();
        rating = in.readString();
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }


    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", year='" + year + '\'' +
                ", genre='" + genre + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
