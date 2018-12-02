package com.axone.vsmusic.opern;

import android.app.ListActivity;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.axone.vsmusic.R;
import com.axone.vsmusic.activity.OpernChooseActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;


public class FileBrowserActivity extends ListActivity {
    private ArrayList<FileUri> filelist; /* List of files in the directory */
    private String directory;            /* Current directory being displayed */
    private TextView directoryView;      /* TextView showing directory name */
    private String rootdir;              /* The top level root directory */
    private IconArrayAdapter<FileUri> adapter;


    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.opern_file_browser);
        setTitle("MidiSheetMusic: Browse Files");
    }

    @Override
    public void onResume() {
        super.onResume();
        rootdir = Environment.getExternalStorageDirectory().getAbsolutePath();
        directoryView = (TextView) findViewById(R.id.directory);
        SharedPreferences settings = getPreferences(0);
        String lastBrowsedDirectory = settings.getString("lastBrowsedDirectory", null);
        if (lastBrowsedDirectory == null) {
            lastBrowsedDirectory = rootdir;
        }
        loadDirectory(lastBrowsedDirectory);
    }

    /* Scan the files in the new directory, and store them in the filelist.
     * Update the UI by refreshing the list adapter.
     */
    private void loadDirectory(String newdirectory) {
        if (newdirectory.equals("../")) {
            try {
                directory = new File(directory).getParent();
            }
            catch (Exception e) {
            }
        }
        else {
            directory = newdirectory;
        }
        SharedPreferences.Editor editor = getPreferences(0).edit();
        editor.putString("lastBrowsedDirectory", directory);
        editor.commit();
        directoryView.setText(directory);

        filelist = new ArrayList<FileUri>();
        ArrayList<FileUri> sortedDirs = new ArrayList<FileUri>();
        ArrayList<FileUri> sortedFiles = new ArrayList<FileUri>();
        if (!newdirectory.equals(rootdir)) {
            String parentDirectory = new File(directory).getParent() + "/";
            Uri uri = Uri.parse("file://" + parentDirectory);
            sortedDirs.add(new FileUri(uri, parentDirectory));
        }
        try {
            File dir = new File(directory);
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file == null) {
                        continue;
                    }
                    String filename = file.getName();
                    if (file.isDirectory()) {
                        Uri uri = Uri.parse("file://" + file.getAbsolutePath() + "/");
                        FileUri fileuri = new FileUri(uri, uri.getPath());
                        sortedDirs.add(fileuri);
                    }
                    else if (filename.endsWith(".mid") || filename.endsWith(".MID") ||
                             filename.endsWith(".midi") || filename.endsWith(".MIDI")) {
                        
                        Uri uri = Uri.parse("file://" + file.getAbsolutePath());
                        FileUri fileuri = new FileUri(uri, uri.getLastPathSegment());
                        sortedFiles.add(fileuri);
                    }
                }
            }
        }
        catch (Exception e) {
        }

        if (sortedDirs.size() > 0) {
            Collections.sort(sortedDirs, sortedDirs.get(0));
        }
        if (sortedFiles.size() > 0) {
            Collections.sort(sortedFiles, sortedFiles.get(0));
        }
        filelist.addAll(sortedDirs);
        filelist.addAll(sortedFiles);
        adapter = new IconArrayAdapter<FileUri>(this, android.R.layout.simple_list_item_1, filelist);
        this.setListAdapter(adapter);
    }
    

    /** When a user selects an item:
     * - If it's a directory, load that directory.
     * - If it's a file, open the SheetMusicActivity.
     */
    @Override
    protected void onListItemClick(ListView parent, View view, int position, long id) {
        super.onListItemClick(parent, view, position, id);
        FileUri file = (FileUri) this.getListAdapter().getItem(position);
        if (file.isDirectory()) {
            this.loadDirectory(file.getUri().getPath());
             return;
        }
        else {
            OpernChooseActivity.openFile(file);
        }
    }  
}


