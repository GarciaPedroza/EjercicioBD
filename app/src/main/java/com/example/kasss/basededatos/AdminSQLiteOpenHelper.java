package com.example.kasss.basededatos;

/**
 * Created by USUARIO on 14/04/2015.
 */
    import android.content.Context;
    import android.database.sqlite.SQLiteDatabase;
    import android.database.sqlite.SQLiteOpenHelper;

    public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

        // Creamos el constructor
        public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        // Se crea la tabla
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table materia (id_materia integer primary key unique, user text unique, contraseña text, nombre text, apellido_p text, apellido_m text, No_control text) ");
        }

        // borrar la tabla y crear la nueva tabla
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists materia");
            db.execSQL("create table materia (id_materia integer primary key unique, user text unique, contraseña text, nombre text, apellido_p text, apellido_m text, No_control text) ");
        }
}
