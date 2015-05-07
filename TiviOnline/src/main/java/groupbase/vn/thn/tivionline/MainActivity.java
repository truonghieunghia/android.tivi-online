package groupbase.vn.thn.tivionline;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.ArrayList;

import groupbase.vn.thn.baselibs.service.ConnectWS;
import groupbase.vn.thn.baselibs.service.callback.RequestCallBack;
import groupbase.vn.thn.baselibs.util.Param;
import groupbase.vn.thn.tivionline.data.entry.TiviEntry;
import groupbase.vn.thn.tivionline.data.json.TiviListJson;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private ArrayList<TiviEntry> listData = new ArrayList<>();
    VideoView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConnectWS connectWS = new ConnectWS("http://api.htvonline.com.vn/tv_channels", this);
        ArrayList<Param> params = new ArrayList<>();
        params.add(new Param("request", "{\"category_id\":\"-1\",\"startIndex\":\"0\",\"pageCount\":\"100\"}"));
        connectWS.setParams(params);
        connectWS.setRequestCallBack(new RequestCallBack<TiviListJson>() {
            @Override
            public void onResult(TiviListJson data) {
                listData = data.listTiviEntry;
            }

            @Override
            public void onResultArray(ArrayList<TiviListJson> data) {

            }
        });
        connectWS.postRequest(false);
        findViewById(R.id.button).setOnClickListener(this);
        video = (VideoView) findViewById(R.id.videoView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button) {
            MediaController mc = new MediaController(this);
            mc.setAnchorView(video);
            mc.setAnchorView(video);
            video.setMediaController(mc);
            video.setVideoURI(Uri.parse(listData.get(36).linkPlays.get(0).mp3u8_link));
            final ProgressDialog pDialog = new ProgressDialog(this);
            pDialog.setTitle("Android Video Streaming Tutorial");
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
}
