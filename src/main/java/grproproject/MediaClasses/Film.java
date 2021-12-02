package grproproject.MediaClasses;

import java.util.ArrayList;

public class Film extends Media{

    public String year;
    public Film(String title, String year, String genres, String rating){

        super(title, genres, rating);
        this.year = year;

    }

}
