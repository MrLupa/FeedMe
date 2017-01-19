package ilupa.feedme;

/**
 * Created by Marcin on 19/01/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Marcin on 18/01/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String database_name = "Studenty";
    public static final String database_table = "Studenci";


    public DataBaseHelper(Context context) {
        super(context, database_name, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /// Creating new table
        db.execSQL("create table "+ database_table+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, FTYPE TEXT, FVOL, FSTART TEXT, FSTOP TEXT, FDURA TEXT, FCOMM TEXT)");
        /// FTYPE - Feed Type
        /// FVOL  - Feed Amount
        /// FSTART- Feed Start Time
        /// FSTOP - Feed Stop Time
        /// FDURA - Feed Duration Time
        /// FCOMM - Feed Comment

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+database_table);
        onCreate(db);
    }

    public boolean wstawDane(String fType, String fVol, String fStart, String fStop, String fDura, String fComm ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("FTYPE", fType);
        cv.put("FVOL", fVol);
        cv.put("FSTART", fStart);
        cv.put("FSTOP", fStop);
        cv.put("FDURA", fDura);
        cv.put("FCOMM", fComm);

        //// Return true if everything is ok, else put false
        if (db.insert(database_table,null,cv)==-1){
            return false;

        } else {
            return true;
        }
    }

    public SQLiteCursor pobierzDane() {
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteCursor fCursor = (SQLiteCursor)db.rawQuery("SELECT * FROM "+database_table, null);
        return fCursor;
    }
}
