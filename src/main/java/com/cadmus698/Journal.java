package com.cadmus698;

import java.util.ArrayList;

public class Journal {
    public ArrayList<Chapter> chapters;

    public Journal(){
        chapters = new ArrayList<>();
    }

    public void add(Chapter c){
        chapters.add(c);
    }
}
