package com.project.db;

import android.content.ContentValues;

/**
 * Created by 300282895 on 7/5/2018.
 */

public class DBObject {

    private ContentValues data;

    public DBObject() {
        this.data = new ContentValues();
    }

    public DBObject(ContentValues data) {
        this.data = data;
    }

    public void getColumnValue(String columnName){
        this.data.get(columnName);
    }

    public void setColumnValue(String columnName, int value){
        this.data.put(columnName, value);
    }

    public void setColumnValue(String columnName, String value){
        this.data.put(columnName, value);
    }

    public void setColumnValue(String columnName, double value){
        this.data.put(columnName, value);
    }

    public int save(){
        return 1;
    }
}
