package com.example.android.coolMusicPlayer;

public class Song {
    private String mSong;
    private String mAuthor;

    private  boolean mCurrentPlaying = false;

    void setSongPlaying(boolean playing)
    {
        mCurrentPlaying = playing;
    }
    boolean isPlaying(){
        return mCurrentPlaying;
    }

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
