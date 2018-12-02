package com.axone.vsmusic.transmodel;

import com.axone.vsmusic.translation.Translation;

import java.io.Serializable;

/**
 * Created by 秋水 on 2017/9/3.
 */

public class FileModel extends Translation implements Serializable {

    private static final long serialVersionUID = -2236060420092380683L;
    private String filename;
    private int fileType;
    private String info;
    private int transType;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getTransType() {
        return transType;
    }

    public void setTransType(int transType) {
        this.transType = transType;
    }
}
