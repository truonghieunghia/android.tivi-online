package groupbase.vn.thn.tivionline.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayDeque;
import java.util.ArrayList;

import groupbase.vn.thn.baselibs.common.ActivityCommon;
import groupbase.vn.thn.baselibs.common.AdapterCommon;
import groupbase.vn.thn.baselibs.listener.AdapterBaseListener;
import groupbase.vn.thn.baselibs.service.ConnectWS;
import groupbase.vn.thn.baselibs.service.callback.RequestCallBack;
import groupbase.vn.thn.baselibs.util.Param;
import groupbase.vn.thn.baselibs.view.ImageViewNetWork;
import groupbase.vn.thn.tivionline.R;
import groupbase.vn.thn.tivionline.data.entry.TiviEntry;
import groupbase.vn.thn.tivionline.data.json.TiviListJson;


public class TiviOnlineActivity extends ActivityCommon implements RequestCallBack<TiviListJson>,
        AdapterBaseListener<TiviEntry, TiviOnlineActivity.HolderView>, AdapterView.OnItemClickListener {

    private ListView listchannels;
    private ArrayList<TiviEntry> listEntry = new ArrayList<>();
    private ConnectWS connectWS;
    private AdapterCommon adapterCommon;

    @Override
    protected void init() {
        setLayout(R.layout.activity_tivi_online);
        listchannels = (ListView) findViewById(R.id.channels_list);
        connectWS = new ConnectWS("http://api.htvonline.com.vn/tv_channels", this);
        ArrayList<Param> params = new ArrayList<>();
        params.add(new Param("request", "{\"category_id\":\"-1\",\"startIndex\":\"0\",\"pageCount\":\"100\"}"));
        connectWS.setParams(params);
        connectWS.setRequestCallBack(this);
        adapterCommon = new AdapterCommon(this, R.layout.cell_tv_info, listEntry);
        adapterCommon.setAdapterBaseListener(this);
        listchannels.setAdapter(adapterCommon);
        listchannels.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        connectWS.postRequest(false);
    }

    @Override
    public void onResult(TiviListJson data) {

        if (data != null) {
            listEntry.addAll(data.listTiviEntry);
            adapterCommon.notifyDataSetChanged();
        }
    }

    @Override
    public void onResultArray(ArrayList<TiviListJson> data) {

    }

    @Override
    public HolderView setHolderView(View view) {
        return new HolderView(view);
    }

    @Override
    public void showData(TiviEntry data, HolderView holderView, int position) {
        holderView.name.setText(data.name);
        holderView.image.requestImage(data.image);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        TiviEntry obj = (TiviEntry)parent.getItemAtPosition(position);
        Intent playtivi = new Intent(this,PlayTivi.class);
        playtivi.putExtra("url",obj.linkPlays.get(0).mp3u8_link);
        startActivity(playtivi);
    }

    public class HolderView {
        TextView name;
        ImageViewNetWork image;

        public HolderView(View view) {
            image = (ImageViewNetWork) view.findViewById(R.id.icon);
            name = (TextView) view.findViewById(R.id.name);
        }
    }
}
