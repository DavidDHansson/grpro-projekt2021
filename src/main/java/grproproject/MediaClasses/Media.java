package grproproject.MediaClasses;


import java.util.ArrayList;

public abstract class Media {

    public String title;
    public ArrayList<String> genres;
    public String rating;

    public Media(String title, String genres, String rating){

        this.title = title;
        this.genres = new ArrayList<String>();
        StringBuilder sb = new StringBuilder("");
        for(int i = 0; i < genres.length()-2; i++){

            if(genres.substring(i,i+2).equals(", ")){

                this.genres.add(sb.toString());
                sb.delete(0,i-1);
            }
            sb.append(genres.substring(i,i+1));

        }
        this.rating = rating;

    }

}
