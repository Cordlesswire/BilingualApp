package com.example.android.miwok;

import android.app.Activity;
import android.graphics.Color;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorResourceId;
    MediaPlayer mediaPlayer = new MediaPlayer();

    public WordAdapter(Activity context,ArrayList<Word> words,int colorResourceId)
    {
        super(context,0,words);
        mColorResourceId = colorResourceId;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView==null)
        {
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        Word currentWord = getItem(position);

        TextView defaultWord = (TextView) listItemView.findViewById(R.id.default_text_view);
        defaultWord.setText(currentWord.getDefaultTranslation());

        TextView miwokWord = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        miwokWord.setText(currentWord.getMiwokTranslation());
        ImageView image = (ImageView) listItemView.findViewById(R.id.image);


        if(currentWord.hasImage()) {
            image.setImageResource(currentWord.getImageResourceId());
            //this puts a white filter on the images -- 255 being the strongest i.e the image will be covered , so 100 is a bit transparent
            //image.setColorFilter(Color.argb(100, 100, 100, 100));
            image.setVisibility(View.VISIBLE);

        }
        else {
            image.setVisibility(View.GONE);
        }

        View textContainer = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(),mColorResourceId);
        textContainer.setBackgroundColor(color);

           //int audio = currentWord.getSoundResourceId();
          //mediaPlayer =  mediaPlayer.create(WordAdapter.this,audio);
          return listItemView;
    }
}
