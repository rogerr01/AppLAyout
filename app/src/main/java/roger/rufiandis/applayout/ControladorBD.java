package roger.rufiandis.applayout;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ControladorBD extends SQLiteOpenHelper
{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Contactes2.db";

    public ControladorBD (Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate (SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE CLIENT (" +
                "CODI INTEGER PRIMARY KEY, " +
                "NOM TEXT," +
                "COGNOMS TEXT," +
                "TELEFON TEXT," +
                "EMAIL TEXT," +
                "GENERE TEXT," +
                "ACTIU INTEGER" +
                ")");
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion)
    {
        onCreate(db);
    }

    @Override
    public void onDowngrade (SQLiteDatabase db, int oldVersion, int newVersion)
    {
        onUpgrade(db, oldVersion, newVersion);
    }
}
