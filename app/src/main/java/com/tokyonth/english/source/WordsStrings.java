package com.tokyonth.english.source;

import com.tokyonth.english.model.WordResources;

import java.util.ArrayList;

public class WordsStrings {

    public ArrayList<WordResources> words = new ArrayList<>();

    private static final String path = "/data/data/com.tokyonth.english/files/sources/";
    private static final String format = ".mp3";

    public static String musicPath(String str){
        return path + str + format;
    }

}
