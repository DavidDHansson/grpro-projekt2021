package grproproject;

import java.util.ArrayList;
import java.util.List;

import grproproject.MediaClasses.Media;
import grproproject.MediaClasses.Film;
import grproproject.MediaClasses.Series;

public class DemoFileParser {

    public static void tets1() {


        List<Media> listOfMedia;
        FileParser fileParser = FileParser.getInstance();
        //only "film" works, not "series"
        listOfMedia = fileParser.readFile("film");
        display(listOfMedia);

    }

    public static void display(List<Media> listOfMedia) {


        for(int i = 0; i < listOfMedia.size(); i++){

            System.out.println(listOfMedia.get(i).title + " " + listOfMedia.get(i).rating + " " + listOfMedia.get(i));

        }


    }

}
