package com.example.willy.drahmi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by YOUCEF on 23/12/2017.
 */

public class Database {
    SQLiteDatabase db;
    DatabaseHelper	DBHelper;
    Context context;

    public Database(Context context)
    {	this.context = context;
        DBHelper = new DatabaseHelper(context);
    }

    public class DatabaseHelper extends SQLiteOpenHelper {
        Context context;

        //pour créer les tables
        public DatabaseHelper(Context context) {
            super(context, "drahmi", null, 1);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE IF NOT EXISTS user("
                    + " id integer primary key autoincrement ,"
                    + " nom text ,"
                    + " prenom text ,"
                    + " username text ,"
                    + "isconncted text ,"
                    + " password text );");

            db.execSQL("CREATE TABLE IF NOT EXISTS account("
                    + " id integer primary key autoincrement ,"
                    + " accountname text ,"
                    + " solde float ,"
                    + " fkuser text );");

            db.execSQL("CREATE TABLE IF NOT EXISTS input("
                    + " id integer primary key autoincrement ,"
                    + " date text ,"
                    + " time text ,"
                    + " montant float ,"
                    + " object text ,"
                    + " fkaccount text );");


            db.execSQL("CREATE TABLE IF NOT EXISTS output("
                    + " id integer primary key autoincrement ,"
                    + " date text ,"
                    + " time text ,"
                    + " montant float ,"
                    + " object text ,"
                    + " fkaccount text );");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Toast.makeText(context, "Update database old : "+oldVersion+" to new : "+newVersion, Toast.LENGTH_SHORT).show();
            onCreate(db);
        }
    }

    public Database open()
    {	db = DBHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        db.close();
    }

    public int removeAll(String table)
    {
        return db.delete(table, null, null);
    }

    public int remove(String table, String id)
    {
        return db.delete(table, "id = "+ id , null);
    }

    public int count(String tables)
    {
        Cursor c = db.rawQuery("SELECT  COUNT(*) FROM "+tables, null);
        if (c.getCount() != 0)
        {
            c.moveToPosition(0);
            return  Integer.parseInt(c.getString(0));
        }
        else
            return 0;
    }

    public Cursor getUserInfo(String username, String password)
    {
        return db.rawQuery("SELECT id FROM user WHERE username = '"+username+"' AND password = '"+password+"'", null);
    }

    public Cursor getUserInfoById(String id)
    {
        return db.rawQuery("SELECT id, nom, prenom, username, password FROM user WHERE id = "+id+"", null);
    }

    public void insertUser(String nom, String prenom, String username, String pass)
    {
        ContentValues values = new ContentValues();
        values.put("nom", nom);
        values.put("prenom", prenom);
        values.put("username", username);
        values.put("password", pass);
        db.insert("user", null, values);
    }

    public void insertAccount(String accountnom, String userid)
    {
        ContentValues values = new ContentValues();

        values.put("accountname", accountnom);
        values.put("fkuser", userid);
        values.put("solde","0");
        db.insert("account", null, values);
    }
    public void insertinput(String Date,String time,float montant,String objet,String fkaccounts)
    {

        ContentValues values = new ContentValues();

        values.put("date", Date);
        values.put("time", time);
        values.put("montant",montant);
        values.put("object", objet);
        values.put("fkaccount", fkaccounts);


        db.insert("input", null, values);
    }


    public void insertoutput(String Date,String time,float montant,String objet,String fkaccounts)
    {

        ContentValues values = new ContentValues();

        values.put("date", Date);
        values.put("time", time);
        values.put("montant",montant);
        values.put("object", objet);
        values.put("fkaccount", fkaccounts);

        db.insert("output", null, values);
    }

    public Cursor GetAllAccountByID (String id)
    {
        return db.rawQuery("SELECT id, accountname,solde FROM account WHERE fkuser = "+id+"", null);
    }
    public Cursor GetAllinputsByID (String id)
    {

        return db.rawQuery("SELECT id,date,time,montant,object  FROM input WHERE fkaccount = "+id+"", null);
    }

    public Cursor GetAlloutputsByID (String id)
    {

        return db.rawQuery("SELECT id,date,time,montant,object  FROM output WHERE fkaccount = "+id+"", null);
    }


    public String getSoldebyid(String id)
    {
        Cursor c =db.rawQuery("SELECT  solde  FROM account WHERE id LIKE '"+id+"'", null);

        if (c.getCount()>0) {
            c.moveToFirst();
            return c.getString(0);
        }
        return "null";
    }

    public void setSolde(String id, float solde){
        this.db.execSQL("UPDATE account SET solde = '" + solde + "' WHERE id="+id+"");


    }






    public void updateYourPassword(String id, String password)
    {
        this.db.execSQL("UPDATE user SET password = '" + password + "' WHERE id="+id+"");
    }
    public void updateconnexion(String id, String connexion)
    {
        Log.i("test","testofaccess " + id);
        this.db.execSQL("UPDATE user SET isconncted = '" + connexion + "' WHERE id="+id+"");
    }


    public String getstatubyid(String id)
    {
        Cursor c =db.rawQuery("SELECT  isconncted  FROM user WHERE id LIKE '"+id+"'", null);

        if (c.getCount()>0) {
            c.moveToFirst();
            return c.getString(0);
        }
        return "null";
    }


    public String getstatu()
    {
         Cursor c =db.rawQuery("SELECT id, isconncted  FROM user WHERE isconncted LIKE 'true' ", null);
            Log.i("test",""+c.getCount());
            if (c.getCount()>0) {
            c.moveToFirst();
                Log.i("test",""+c.getString(1));
            return c.getString(0);
            }
        return "null";
    }


}