package com.axone.vsmusic.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

import com.axone.vsmusic.R;
import com.axone.vsmusic.opern.AllSongsActivity;
import com.axone.vsmusic.opern.FileBrowserActivity;
import com.axone.vsmusic.opern.FileUri;
import com.axone.vsmusic.opern.MidiFile;
import com.axone.vsmusic.opern.RecentSongsActivity;

import org.json.JSONArray;
import org.json.JSONObject;

public class OpernChooseActivity extends TabActivity {

    static OpernChooseActivity globalActivity;

    @Override
    protected void onCreate(Bundle state) {

        globalActivity = this;

        super.onCreate(state);
        //setContentView(R.layout.activity_opern_choose);

        super.onCreate(state);

        Bitmap allFilesIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.allfilesicon);
        Bitmap recentFilesIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.recentfilesicon);
        Bitmap browseFilesIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.browsefilesicon);

        final TabHost tabHost = getTabHost();

        tabHost.addTab(tabHost.newTabSpec("All")
                .setIndicator("所有音乐", new BitmapDrawable(allFilesIcon))
                .setContent(new Intent(this, AllSongsActivity.class)));

        tabHost.addTab(tabHost.newTabSpec("Recent")
                .setIndicator("最近音乐", new BitmapDrawable(recentFilesIcon))
                .setContent(new Intent(this, RecentSongsActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));

        tabHost.addTab(tabHost.newTabSpec("Browse")
                .setIndicator("本地音乐", new BitmapDrawable(browseFilesIcon))
                .setContent(new Intent(this, FileBrowserActivity.class)));
    }

    public static void openFile(FileUri file) {
        globalActivity.doOpenFile(file);
    }

    public void doOpenFile(FileUri file) {
        byte[] data = file.getData(this);
        if (data == null || data.length <= 6 || !MidiFile.hasMidiHeader(data)) {
            OpernChooseActivity.showErrorDialog("Error: Unable to open song: " + file.toString(), this);
            return;
        }
        updateRecentFile(file);
        Intent intent = new Intent(Intent.ACTION_VIEW, file.getUri(), this, OpernSheetActivity.class);
        intent.putExtra(OpernSheetActivity.MidiTitleID, file.toString());
        startActivity(intent);
    }


    /** Show an error dialog with the given message */
    public static void showErrorDialog(String message, Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    /** Save the given FileUri into the "recentFiles" preferences.
     *  Save a maximum of 10 recent files.
     */
    public void updateRecentFile(FileUri recentfile) {
        try {
            SharedPreferences settings = getSharedPreferences("midisheetmusic.recentFiles", 0);
            SharedPreferences.Editor editor = settings.edit();
            JSONArray prevRecentFiles = null;
            String recentFilesString = settings.getString("recentFiles", null);
            if (recentFilesString != null) {
                prevRecentFiles = new JSONArray(recentFilesString);
            }
            else {
                prevRecentFiles = new JSONArray();
            }
            JSONArray recentFiles = new JSONArray();
            JSONObject recentFileJson = recentfile.toJson();
            recentFiles.put(recentFileJson);
            for (int i = 0; i < prevRecentFiles.length(); i++) {
                if (i >= 10) {
                    break; // only store 10 most recent files
                }
                JSONObject file = prevRecentFiles.getJSONObject(i);
                if (!FileUri.equalJson(recentFileJson, file)) {
                    recentFiles.put(file);
                }
            }
            editor.putString("recentFiles", recentFiles.toString() );
            editor.commit();
        }
        catch (Exception e) {
        }
    }
}
