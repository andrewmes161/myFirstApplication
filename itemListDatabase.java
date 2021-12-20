package com.example.module3assignment.myfiles;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import androidx.annotation.Nullable;

public class itemListDatabase extends SQLiteOpenHelper {


    public itemListDatabase(@Nullable Context context) {super(context, "itemList.db", null, 1);}

    public static final String ITEM_TABLE = "_ITEM_TABLE"; // Scheme: USERNAME_ITEM_TABLE
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_ITEM_NAME = "ITEM_NAME";
    public static final String COLUMN_ITEM_QUANTITY = "ITEM_QUANTITY";

    @Override
    public void onCreate(SQLiteDatabase db) {
        // This would be empty, because a table is only created when a user begins to add items
        // to their list.
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
