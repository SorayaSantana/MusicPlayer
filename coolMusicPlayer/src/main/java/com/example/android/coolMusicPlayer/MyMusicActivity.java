package com.example.android.coolMusicPlayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MyMusicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list);

        ArrayList<Song> songs = new ArrayList<Song>();
        songs.add(new Song("Smells like teen spirit","Nirvana"));
        songs.add(new Song("Imagine", "John Lennon"));
        songs.add(new Song("One", "U2"));
        songs.add(new Song("Billie Jean", "Michael Jackson"));
        songs.add(new Song("Bohemian Rhapsody", "Queen"));
        songs.add(new Song("Hey Jude", "The Beatles"));
        songs.add(new Song("Like a rolling stone", "Bob Dylan"));
        songs.add(new Song("I can´t get no satisfaction", "Rollins Stones"));
        songs.add(new Song("I will survive", "Gloria Gaynor"));
        songs.add(new Song("Whole lotta love", "Led Zeppelin"));

        SongsAdapter itemsAdapter = new SongsAdapter(this, songs);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);
    }
}