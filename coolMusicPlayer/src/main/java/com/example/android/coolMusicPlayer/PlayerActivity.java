package com.example.android.coolMusicPlayer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PlayerActivity extends AppCompatActivity {
    ArrayList<Song> mSongs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        mSongs = new ArrayList<>();
        mSongs.add(new Song("Flowers","Miley Cyrus"));
        mSongs.add(new Song("Karma", "Taylor Swift"));
        mSongs.add(new Song("Eyes closed", "Ed Sheeran"));
        mSongs.add(new Song("Sweet Child of Mine", "Guns and roses"));
        mSongs.add(new Song("Stairway to heaven", "Led Zeppelin"));
        mSongs.add(new Song("Come as you are", "Nirvana"));
        mSongs.add(new Song("Mirrors", "Justin Timberlake"));
        mSongs.add(new Song("Jailhouse rock", "Elvis Presley"));

        SongsAdapter itemsAdapter = new SongsAdapter(this, mSongs);

        ListView listView = findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

        //listView.setItemChecked(0, true); //first song checked by default.

        ImageButton button = findViewById(R.id.stop_button);
        button.setEnabled(false);
        button = findViewById(R.id.next_button);
        button.setEnabled(false);
        button = findViewById(R.id.prev_button);
        button.setEnabled(false);

        button = findViewById(R.id.play_button);
        button.setEnabled(false);
        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          ListView listView = findViewById(R.id.list);
                                          int songIndex = listView.getCheckedItemPosition();
                                          Song song = (Song) listView.getAdapter().getItem(songIndex);
                                          if(song == null)
                                              return; // better safe than sorry

                                          ImageButton button = findViewById(R.id.stop_button);
                                          button.performClick();

                                          song.setSongPlaying(true);
                                          ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();

                                          playSong(song);
                                      }
                                  });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                listView.setItemChecked(position, true);
                //listView.requestFocusFromTouch();
                listView.setSelection(position);
                Song song = (Song) listView.getAdapter().getItem(position);

                Log.i("ItemOnClick", "current song="+ position);

                ImageButton button = findViewById(R.id.play_button);
                button.setEnabled(true);

            }
        });

        ImageButton stopButton = findViewById(R.id.stop_button);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                ListView listView = findViewById(R.id.list);
                int songIndex = ((SongsAdapter)listView.getAdapter()).getCurrentSongPlaying();
                if(songIndex < 0) return; //none playing

                Log.i("stopButton", "current song="+ songIndex);

                Song song = (Song) listView.getAdapter().getItem(songIndex);
                song.setSongPlaying(false);
                ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();

                stopSong(song);
            }
        });

        ImageButton nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                ListView listView = findViewById(R.id.list);
                int songIndex = ((SongsAdapter)listView.getAdapter()).getCurrentSongPlaying();
                if(songIndex < 0) return; //none playing

                Log.i("nextButton", "current song="+ songIndex);

                Song currentSong = (Song) listView.getAdapter().getItem(songIndex);
                Song nextSong = (Song) listView.getAdapter().getItem(songIndex+1);
                if(nextSong == null) {
                    return; // better safe than sorry
                }

                Log.i("nextButton", "current song="+ listView.getCheckedItemPosition());

                currentSong.setSongPlaying(false);
                nextSong.setSongPlaying(true);
                ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();

                playSong(nextSong);
            }
        });

        ImageButton prevButton = findViewById(R.id.prev_button);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                ListView listView = findViewById(R.id.list);
                int songIndex = ((SongsAdapter)listView.getAdapter()).getCurrentSongPlaying();
                if(songIndex < 0) return; //none playing

                Log.i("PrevButton", "current song="+ songIndex);

                Song currentSong = (Song) listView.getAdapter().getItem(songIndex);
                Song prevSong = (Song) listView.getAdapter().getItem(songIndex-1);
                if(prevSong == null) {
                    return; // better safe than sorry
                }

                Log.i("nextButton", "current song="+ listView.getCheckedItemPosition());

                currentSong.setSongPlaying(false);
                prevSong.setSongPlaying(true);
                ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();

                playSong(prevSong);
            }
        });
    }

    protected void stopSong(Song song){
        TextView title = findViewById(R.id.current_song_text_view);
        title.setText("");
        TextView author = findViewById(R.id.current_author_text_view);
        author.setText("");

        ImageButton button = findViewById(R.id.stop_button);
        button.setEnabled(false);
        button = findViewById(R.id.play_button);
        button.setEnabled(true);
        button = findViewById(R.id.next_button);
        button.setEnabled(false);
        button = findViewById(R.id.prev_button);
        button.setEnabled(false);
    }

    protected void playSong(Song song) {
        TextView title = findViewById(R.id.current_song_text_view);
        title.setText(song.getSong());
        TextView author = findViewById(R.id.current_author_text_view);
        author.setText(song.getAuthor());

        Toast.makeText(getApplicationContext(),
                        "Now playing " + song.getSong() + " by " + song.getAuthor(),
                        Toast.LENGTH_LONG)
                .show();

        ImageButton button = findViewById(R.id.stop_button);
        button.setEnabled(true);

        button = findViewById(R.id.next_button);
        if(mSongs.indexOf(song) == mSongs.size() - 1) {
            button.setEnabled(false);
        }
        else {
            button.setEnabled(true);
        }
        button = findViewById(R.id.prev_button);
        if(mSongs.indexOf(song) == 0) {
            button.setEnabled(false);
        }
        else {
            button.setEnabled(true);
        }
    }
}