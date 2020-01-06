package com.anioncode.witcher;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class WitcherAdapterSound extends RecyclerView.Adapter<WitcherAdapterSound.RecViewHolder> {

    private ArrayList<ModelWitcher> list;
    private Context mContext;

    public WitcherAdapterSound(ArrayList<ModelWitcher> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;

    }

    @Override
    public WitcherAdapterSound.RecViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_soundboard, parent, false);


        return new WitcherAdapterSound.RecViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final RecViewHolder holder, int position) {
        ModelWitcher modelWitcher = list.get(position);
        holder.title.setText(modelWitcher.Title);
        holder.title.setSelected(true);
        holder.title.setSingleLine(true);

        final MediaPlayer mp;
        int totalTime;

        mp = TaskMedia(modelWitcher.NameFile);
        mp.setLooping(false);

        mp.seekTo(0);
        mp.setVolume(0.5f, 0.5f);
        totalTime = mp.getDuration();

        holder.playStop.setOnClickListener((View v) -> {
            if (!mp.isPlaying()) {
                // Stopping
                mp.start();
                holder.playStop.setImageResource(R.drawable.ic_stop);

            } else {
                // Playing
                mp.pause();
                holder.playStop.setImageResource(R.drawable.ic_play_arrow);
            }
        });

        holder.messageSend.setOnClickListener((View c) -> {
            //String mediaPath = copyFiletoExternalStorage(R.raw.toss_a_coin, "toss_a_coin.mp3");
            //  String mediaPath = "android.resource://com.anioncode.witcher/raw/" + R.raw.toss_a_coin;
//            String mediaPath = Environment.getExternalStorageDirectory().toString() + "/toss_a_coin.mp3";
            //Uri mediaPath = FileProvider.getUriForFile(mContext, "com.anioncode.witcher", new File("android.resource://com.anioncode.witcher/" + R.raw.toss_a_coin));

//            Intent share = new Intent(Intent.ACTION_SEND);
//            share.setType("audio/*");
//            share.putExtra(Intent.EXTRA_STREAM, mediaPath);
//            mContext.startActivity(Intent.createChooser(share, "Share Sound File"));


            /// solution
            InputStream inputStream;
            FileOutputStream fileOutputStream;
            try {
                inputStream = mContext.getResources().openRawResource(R.raw.toss_a_coin);
                fileOutputStream = new FileOutputStream(new File(Environment.getExternalStorageDirectory(), "fuck.mp3"));

                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) > 0) {
                    fileOutputStream.write(buffer, 0, length);
                }

                inputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + Environment.getExternalStorageDirectory() + "/fuck.mp3"));
            intent.setType("audio/*");
            mContext.startActivity(Intent.createChooser(intent, "Share sound"));

        });
        holder.soundProgress.setMax(totalTime);
        holder.soundProgress.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (fromUser) {
                            mp.seekTo(progress);
                            holder.soundProgress.setProgress(progress);
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mp != null) {
                    try {
                        Message msg = new Message();
                        msg.what = mp.getCurrentPosition();
                        holder.soundProgress.setProgress(msg.what);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }).start();
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class RecViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        AppCompatImageView playStop;
        AppCompatImageView messageSend;
        AppCompatSeekBar soundProgress;

        public RecViewHolder(View itemView) {
            super(itemView);
            playStop = itemView.findViewById(R.id.playStop);
            title = itemView.findViewById(R.id.titleText);
            soundProgress = itemView.findViewById(R.id.seekBar);
            messageSend = itemView.findViewById(R.id.message);


        }


    }

    MediaPlayer TaskMedia(String s) {
        MediaPlayer mp;
        switch (s) {
            case "raw1": {
                mp = MediaPlayer.create(mContext, R.raw.toss_a_coin);
                break;
            }

            case "raw2": {
                mp = MediaPlayer.create(mContext, R.raw.geraltwishes);
                break;
            }

            case "raw3": {
                mp = MediaPlayer.create(mContext, R.raw.fuck);
                break;
            }

            case "raw4": {
                mp = MediaPlayer.create(mContext, R.raw.hmm);
                break;
            }
            default: {
                mp = MediaPlayer.create(mContext, R.raw.love);
            }

        }

        return mp;
    }

}