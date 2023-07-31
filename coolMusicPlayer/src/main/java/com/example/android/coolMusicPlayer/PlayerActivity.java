package com.example.android.coolMusicPlayer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        ArrayList<Song> songs = new ArrayList<>();
        songs.add(new Song("Flowers","Miley Cyrus"));
        songs.add(new Song("Karma", "Taylor Swift"));
        songs.add(new Song("Eyes closed", "Ed Sheeran"));
        songs.add(new Song("Sweet Child of Mine", "Guns and roses"));
        songs.add(new Song("Stairway to heaven", "Led Zeppelin"));
        songs.add(new Song("Come as you are", "Nirvana"));
        songs.add(new Song("Mirrors", "Justin Timberlake"));
        songs.add(new Song("Jailhouse rock", "Elvis Presley"));


        SongsAdapter itemsAdapter = new SongsAdapter(this, songs);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Song song = (Song) parent.getAdapter().getItem(position);

                TextView title = findViewById(R.id.current_song_text_view);
                title.setText(song.getSong());
                TextView author = findViewById(R.id.current_author_text_view);
                author.setText(song.getAuthor());

                Toast.makeText(getApplicationContext(),
                                "Now playing " + song.getSong() + " by "+ song.getAuthor(),
                                Toast.LENGTH_LONG)
                        .show();

            }
        });

        ImageButton stopButton = findViewById(R.id.stop_button);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                TextView title = findViewById(R.id.current_song_text_view);
                title.setText("");
                TextView author = findViewById(R.id.current_author_text_view);
                author.setText("");

                ListView listView = findViewById(R.id.list);
            }
        });

        //TODO: click on play -> trigger onclick on current selected item in list

        //TODO: next button -> move to next

    }
}