package com.kaifeng.myvedioview;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.VideoView;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class VideoActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener,SurfaceHolder.Callback{
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private Timer timer;
    private TimerTask timerTask;
    private boolean isChange = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        surfaceView = (SurfaceView) findViewById(R.id.surfaceview);
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath()+"/beyond.mp4");
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        mediaPlayer.setOnPreparedListener(this);
        seekBar = (SeekBar) findViewById(R.id.seekbar);
        seekBar.setMax(mediaPlayer.getDuration());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isChange = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
                isChange = false;
            }
        });
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                if(isChange == true){
                    return;
                }
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
            }
        };
        timer.schedule(timerTask,0,10);
    }
    public void intent(View view){
        Uri uri = Uri.parse("http://android2017.duapp.com/beyond.mp4");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri,"video/mp4");
        startActivity(intent);
    }

    public void videoview(View view){
        Uri uri = Uri.parse ( Environment.getExternalStorageDirectory ().getAbsolutePath ()
                + "/beyond.mp4");
        VideoView videoView = (VideoView) findViewById (R.id.videoview);
        videoView.setMediaController(new MediaController(this));
        videoView.setVideoURI(uri);
        videoView.start();
        videoView.requestFocus();
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mediaPlayer.start();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mediaPlayer.setDisplay(surfaceHolder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
