package grproproject;

import grproproject.MediaClasses.Media;
import grproproject.MediaClasses.Film;
import grproproject.MediaClasses.Series;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Locale;

public class FileParser {

    private FileParser(){}

    private static class SingletonHelper{
        private static final FileParser INSTANCE = new FileParser();
    }

    public static FileParser getInstance(){
        return SingletonHelper.INSTANCE;
    }

    public List<Media> readFile(String mediatype){

        List<Media> listOfMedia = new ArrayList();
        String path = "assets/files/film.txt";

        if(mediatype.equals("series")){

            path = "assets/files/serier.txt";

        }

        Scanner sc = new Scanner(getClass().getClassLoader().getResourceAsStream(path));

        sc.useDelimiter(";\\s*");
        sc.useLocale(Locale.FRENCH);

        while(sc.hasNext()){
            String title = sc.next();
            String year = sc.next().trim();
            String catagories = sc.next().trim();
            String rating = sc.next();
            if(mediatype.equals("series")){
                String seasons = sc.next();
                listOfMedia.add(new Series(title, year, catagories, rating, seasons));

            }else{

                listOfMedia.add(new Film(title, year, catagories, rating));

            }

        }
        sc.close();
        return listOfMedia;
    }

}
