package com.example.android.coolMusicPlayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SongsAdapter extends ArrayAdapter<Song> {

    public SongsAdapter(@NonNull Context context, ArrayList<Song> songs) {
        super(context, 0, songs);

    }
    @Override
    public int getViewTypeCount() {

        return getCount();
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }
    public int getCurrentSongPlaying(){
        for (int index=0; index < getCount(); index++){
            if(getItem(index).isPlaying())
                return index;
        }

        return -1; // none playing
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Song currentSong = getItem(position);
        TextView songName = (TextView) listItemView.findViewById(R.id.song_text_view);
        songName.setText(currentSong.getSong());

        TextView songAuthor = (TextView) listItemView.findViewById(R.id.author_text_view);
        songAuthor.setText(currentSong.getAuthor());

       if(currentSong.isPlaying()) {
            TextView nowPlayingText = listItemView.findViewById(R.id.nowPlayingText);
            nowPlayingText.setText(R.string.now_playing);
        }
        else {
            TextView nowPlayingText = listItemView.findViewById(R.id.nowPlayingText);
            nowPlayingText.setText("");
        }

        return listItemView;
    }
}
