package com.axone.vsmusic.opern;

import android.app.ListActivity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.axone.vsmusic.R;
import com.axone.vsmusic.activity.OpernChooseActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


/** @class RecentSongsActivity
 * The RecentSongsActivity class displays a list of songs
 * that were recently accessed.  The list comes from the
 * SharedPreferences ????
 */
public class RecentSongsActivity extends ListActivity {
    private ArrayList<FileUri> filelist; /* List of recent files opened */
    private IconArrayAdapter<FileUri> adapter;


    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setTitle("最近音乐");
        getListView().setBackground(getResources().getDrawable(R.mipmap.back2));
        // Load the list of songs
        loadFileList();
        adapter = new IconArrayAdapter<FileUri>(this, android.R.layout.simple_list_item_1, filelist);
        this.setListAdapter(adapter);
    }

    private void loadFileList() {
        filelist = new ArrayList<FileUri>();
        SharedPreferences settings = getSharedPreferences("midisheetmusic.recentFiles", 0);
        String recentFilesString = settings.getString("recentFiles", null);
        if (recentFilesString == null) {
            return;
        }
        try {
            JSONArray jsonArray = new JSONArray(recentFilesString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                FileUri file = FileUri.fromJson(obj, this);
                if (file != null) {
                    filelist.add(file);
                }
            }
        }
        catch (Exception e) {
        }
    }
            
    @Override
    public void onResume() {
        super.onResume();
        loadFileList();
    }

    /** When a user selects a song, open the SheetMusicActivity. */
    @Override
    protected void onListItemClick(ListView parent, View view, int position, long id) {
        super.onListItemClick(parent, view, position, id);
        FileUri file = (FileUri) this.getListAdapter().getItem(position);
        OpernChooseActivity.openFile(file);
    }  
}


