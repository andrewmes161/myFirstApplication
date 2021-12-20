package com.example.module3assignment.myfiles;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UserDatabase extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "user.db";


    // Handles the user table creation //
    public static final String USER_TABLE = "USER_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_USERNAME = "USERNAME";
    public static final String COLUMN_USER_PASSWORD = "USER_PASSWORD";

    // Handles the item table creation //
    public static final String COLUMN_ITEM_NAME = "ITEM_NAME";
    public static final String COLUMN_ITEM_QUANTITY = "AMOUNT";

    // Creation of the user.db on creation //

//    private static UserDatabase userDatabase;
//
//    public static UserDatabase getInstance(Context context) {
//        if (userDatabase == null){
//            userDatabase = new UserDatabase(context);
//        }
//        return userDatabase;
//    }
//    private UserDatabase(Context context) {
//        super(context, DATABASE_NAME, null,VERSION );
//    }

    public UserDatabase(@Nullable Context context) {
        super(context, "user.db", null, 1);
    }

    // This is called the first time a database is accessed. Code to create a new database
    // should be found here.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + USER_TABLE + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_USER_PASSWORD + " TEXT);";

        db.execSQL(createTableStatement);

    }

    // This is called if the database version number changes.It prevents previous users apps from
    // breaking when you change the database design.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //                  USER CRUD METHODS                   //
    // ---------------------------------------------------- //

    public boolean addOne(User user) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USERNAME, user.getUserName()); //
        cv.put(COLUMN_USER_PASSWORD, user.getUserPassword());

        addOneUserTable(db, user.getUserName());

        long insert = db.insert(USER_TABLE, null, cv);
        if(insert == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    // Creates a table for the user's items to be stored //

    public List<User> getAllUsers() {
        // This holds the list of users...
        List<User> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + USER_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null); // returns a cursor

        // moveToFirst() brings back a boolean value...
        if (cursor.moveToFirst()) {
            // Loop through the user (result set) and create new user object. Put them into a return list
            do {
                int userID = cursor.getInt(0);
                String userName = cursor.getString(1);
                String userPassword = cursor.getString(2);
                // boolean userActive = cursor.getInt(3) == 1 ? true: false; // example of a ternary operator. SQLite
                // is unable to return true or false statements. Rather must be accepted as an int than converted.

                User newUser = new User(userID, userName, userPassword);
                returnList.add(newUser);
            } while(cursor.moveToNext()); // while their is another cursor in the list

        }
        else {
            // failure. do not add anything to the list
        }

        // If you open a door... please shut it!
        cursor.close();
        db.close();
        return returnList;
    }

    //                  ITEM CRUD METHODS                   //
    // ---------------------------------------------------- //
    public void addOneUserTable(SQLiteDatabase db ,String username) {
        String createTableStatement = "CREATE TABLE " + username + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ITEM_NAME + " TEXT, " +
                COLUMN_ITEM_QUANTITY + " INTEGER);";

        db.execSQL(createTableStatement);
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ITEM_NAME, "Oranges");
        contentValues.put(COLUMN_ITEM_QUANTITY, 1);
        db.insert(username, null, contentValues);
    }

    public void addItem(User user, String itemName, int amount, List<item> itemList) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // Check if Item already exists on the list
        for (int i = 0;  i < itemList.size(); ++i) {
            if (itemList.get(i).getItemName().equals(itemName)) {
                Log.d("Item: ", "Item already exists");
                return;
            }
            else{
                // continue...
            }
        }

        // if doesn't exist...create a new row for the
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ITEM_NAME, itemName);
        cv.put(COLUMN_ITEM_QUANTITY, amount);

        db.insert(user.getUserName(), null, cv);

        Log.d("Item: ", "Adding " + itemName + " to the table");

        // close db...
        db.close();
    }

    public List<item> readUserTable(User user) {
        List<item> itemList = new ArrayList<item>();

        SQLiteDatabase db = this.getReadableDatabase();
        String queryStr = "SELECT * FROM " + user.getUserName();
        Cursor cursor = db.rawQuery(queryStr,null);

        if(cursor.moveToFirst()) {
            do {
                //int userID = cursor.getInt(0);
                String itemName = cursor.getString(1);
                int quantity = cursor.getInt(2);

                item newItem = new item(itemName,quantity);
                itemList.add(newItem);
            }while (cursor.moveToNext());
        }
        // Close objects...
        cursor.close();
        db.close();
        return itemList;
    }

    public void updateAmount(User user, item item, String itemName, boolean operation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        if(operation == true) {
            // add 1 to quantity
            Log.d("Quantity: ", "Adding one to " + item.getItemName());
            //cv.put(COLUMN_ITEM_QUANTITY, item.getQuantity() + 1);
        }
        else{
            // subtract 1 to quantity
            if (item.getQuantity() -1 == 0) {
                Log.d("Quantity: ", "Deleting " + item.getItemName());
            }
            else{
                Log.d("Quantity: ", "Subtracting one from " + item.getItemName());
                //cv.put(COLUMN_ITEM_QUANTITY, item.getQuantity() -1);
            }
        }

        //db.update(user.getUserName(), cv, COLUMN_ITEM_QUANTITY + " = " + item.getItemName(), null);

        // close db...
        db.close();
    }

    public void deleteItem(User user, String itemName) {
        // delete item from table by the name of the item

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(user.getUserName(), COLUMN_ITEM_NAME +
                " = " + itemName, null);

        // Close db...
        db.close();
    }
}


