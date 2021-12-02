package grproproject.MediaClasses;

import java.util.ArrayList;

public class Series extends Media{

    public String startYear;
    public String endYear;
    public String seasons;

    public Series(String title, String year, String genres, String rating, String seasons){

        super(title, genres, rating);

        /* This code will only work, when this exception is handled: Fargo; 2014- ; Crime, Drama, Thriller; 9,0; 1-10; 2-10; 3-10;
        System.out.println(title + year + genres + rating + seasons);
        if(4 == year.length()){
            this.startYear = year;
            this.endYear = this.startYear;

        }else if(5 == year.length()) {
            this.startYear = year.substring(0,5);
            this.endYear = "0";

        }else{

            this.startYear = year.substring(0,5);
            this.endYear = year.substring(6);

        }*/

        this.seasons = seasons;

        /* This code might work?
        int x = 0;
        for(int i = 0; i < seasons.length()-1; i++){
            x++;

            if(i+1 > seasons.length()){

                this.seasons.add(seasons.substring(seasons.length()-x));

            }else if(seasons.substring(i,i+1).equals(",")){

                this.seasons.add(seasons.substring(0,i));
                x = 0;

            }

        }*/


    }

}
