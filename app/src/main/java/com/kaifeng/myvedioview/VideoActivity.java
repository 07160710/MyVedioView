package com.kaifeng.myvedioview;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
    }
    public void intent(View view){
        Uri uri = Uri.parse ( "http://android2017.duapp.com/beyond.mp4" );
        Intent intent = new Intent ( Intent.ACTION_VIEW );
        intent.setDataAndType ( uri,"video/mp4" );
        startActivity ( intent );
    }

    public void videoview(View view){
        Uri uri = Uri.parse ( Environment.getExternalStorageDirectory ().getAbsolutePath ()
                + "/beyond.mp4");
        VideoView videoView = (VideoView) findViewById ( R.id.videoview );
        videoView.setMediaController ( new MediaController( this ) );
        videoView.setVideoURI ( uri );
        videoView.start ();
        videoView.requestFocus ();
    }
}
