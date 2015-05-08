package groupbase.vn.thn.tivionline.activity;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.MediaController;
import android.widget.VideoView;

import groupbase.vn.thn.baselibs.common.ActivityCommon;
import groupbase.vn.thn.tivionline.R;

public class PlayTivi extends ActivityCommon {

    VideoView video;

    String url ="";
    @Override
    protected void init() {
        setLayout(R.layout.activity_play_tivi);
        url = getIntent().getStringExtra("url");
        video = (VideoView) findViewById(R.id.videoView);
        MediaController mc = new MediaController(this);
        mc.setAnchorView(video);
        mc.setAnchorView(video);
        video.setMediaController(mc);
        video.setVideoURI(Uri.parse(url));
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setTitle("Tivi Online");
        // Set progressbar message
        pDialog.setMessage("Buffering...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        // Show progressbar
        pDialog.show();
        video.requestFocus();
        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                pDialog.dismiss();
                mp.start();
            }
        });
    }

}
