package com.nickwelna.musicplayer;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    float currentVolume = 0.5f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.bensound_dubstep);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {

                Toast.makeText(MainActivity.this, "I'm Done", Toast.LENGTH_SHORT).show();

            }
        });

        Button playButton = findViewById(R.id.play_button);
        playButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                playMusic();

            }

        });

        Button pauseButton = findViewById(R.id.pause_button);
        pauseButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                pauseMusic();

            }

        });

        Button volumeUp = findViewById(R.id.volume_up);
        volumeUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                currentVolume += 0.1f;
                AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, (int) (audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC) * currentVolume), 0);

            }

        });

        Button volumeDown = findViewById(R.id.volume_down);
        volumeDown.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                currentVolume -= 0.1f;
                AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, (int) (audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC) * currentVolume), 0);

            }

        });

    }

    public void playMusic() {

        mediaPlayer.start();

    }

    public void pauseMusic() {

        mediaPlayer.pause();

    }

    @Override
    protected void onStop() {

        super.onStop();
        mediaPlayer.release();

    }

}
