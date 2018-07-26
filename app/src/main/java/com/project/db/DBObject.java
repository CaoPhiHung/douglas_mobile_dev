package com.project.db;

import android.content.ContentValues;

public abstract class DBObject {

    abstract public ContentValues toContentValues();
    abstract public DBObject get(long id);

}
