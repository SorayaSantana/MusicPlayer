package com.example.android.coolMusicPlayer;

public class Song {
    private String mSong;
    private String mAuthor;

    public Song(String song, String author){
        mSong = song;
        mAuthor = author;
    }

    public String getSong(){
        return mSong;
    }

    public String getAuthor(){
        return mAuthor;
    }
}
