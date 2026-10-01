package lk.sliit.solargrid.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

public class SolarGridDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "solargrid.db";

    /** Version 1: the signed-in session . */
    public static final int DATABASE_VERSION = 1;

    /** Every "CREATE TABLE IF NOT EXISTS" script in the app. */
    private static final String[] TABLES = {
            SessionDao.CREATE_TABLE,
    };

    /** Opens (or creates) solargrid.db for this app. */
    public SolarGridDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /** Creates every table the first time the app runs. */
    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {
        createTables(db);
    }

    /** Adds tables that newer versions of the app need. */
    @Override
    public void onUpgrade(@NonNull SQLiteDatabase db, int oldVersion, int newVersion) {
        createTables(db);
    }

    /** Runs every table script; each one is safe to run again. */
    private void createTables(SQLiteDatabase db) {
        for (String script : TABLES) {
            db.execSQL(script);
        }
    }
}
