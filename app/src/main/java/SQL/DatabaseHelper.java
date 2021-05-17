package SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import modal.User;

public class DatabaseHelper extends SQLiteOpenHelper {
//    db version
    private static final int DATABASE_VERSION = 1;
//    db name
    private static final String DATABASE_NAME = "UserManager.db";

    //user table name
    private static final String TABLE_USER = "user";

    //user table cols
    private static final String COLUMN_USER_ID ="user_id";
    private static final String COLUMN_USER_NAME ="user_name";
    private static final String COLUMN_USER_EMAIL ="user_email";
    private static final String COLUMN_USER_PASSWORD ="user_password";


    //create table sql query
    private String CREATE_USER_TABLE = " CREATE TABLE "+  TABLE_USER + "(" + COLUMN_USER_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ")";
    //drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS "+  TABLE_USER;

    public DatabaseHelper( Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
    }
    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME,user.getName());
        values.put(COLUMN_USER_EMAIL,user.getEmail());
        values.put(COLUMN_USER_PASSWORD,user.getPassword());

        //inserting the row
        db.insert(TABLE_USER,null,values);
        db.close();
    }
    public List<User> getAllUser(){
        //array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_NAME,
                COLUMN_USER_EMAIL,
                COLUMN_USER_PASSWORD,
        };
        //sorting orders
        String sortOrder = COLUMN_USER_NAME  + "ASC";
        List <User> userlist = new ArrayList<User>();
        SQLiteDatabase db = this.getReadableDatabase();

        //query the user table
        Cursor cursor = db.query(TABLE_USER,
                columns,//columns to get
                null,//columns for where clause
                null,//the values for the where clause
                null,//group the rows
                null,//filter by row groups
                sortOrder);//the sort order

        //traversing through all the rows and adding to the list
        if (cursor.moveToFirst()){
            do{
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.setName((cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.setEmail((cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.setPassword((cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));

                //now adding the user to the list
                userlist.add(user);

            }while(cursor.moveToNext());
            cursor.close();
            db.close();
        }
        return userlist;
    }
    public void updateUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USER_NAME,user.getName());
        contentValues.put(COLUMN_USER_EMAIL,user.getEmail());
        contentValues.put(COLUMN_USER_PASSWORD,user.getPassword());

        //updating row
        db.update(TABLE_USER,contentValues,COLUMN_USER_ID + "= ?",new String[]{String.valueOf(user.getId())});
        db.close();
    }
    public void deleteUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        //delete user record by id
        db.delete(TABLE_USER,COLUMN_USER_ID + "=?",new String[] {String.valueOf(user.getId())});
        db.close();
    }

    public boolean checkUser (String email){
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

//        selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";

        //selection argument
        String[] selectionArgs = {email};
//        query used to get the user
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);
        int cursorCount  = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount >0){
            return true;
        }
         return false;
    }
    public boolean checkUser(String email,String password) {
        //array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        //selection criteria
        String selection = COLUMN_USER_EMAIL + " = ? " + " AND " + COLUMN_USER_PASSWORD + " = ? ";

        //selection arguments
        String[] selectionArgs = {email, password};

        //query the table with conditions
        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0)
            return true;
        return false;
    }
}
