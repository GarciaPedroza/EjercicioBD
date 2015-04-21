package com.example.kasss.basededatos;

import android.support.v7.app.ActionBarActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;




public class MainActivity extends ActionBarActivity {
    EditText et_idmateria, et_user, et_contraseña, et_nombre, et_apellidop, et_apellidom, et_No_control;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner sp = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this, R.array.versiones, android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
                                       int position, long id) {
                Toast.makeText(parentView.getContext(), "Has Seleccionado" + " " +
                        parentView.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();

            }

            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });

        et_idmateria = (EditText) findViewById(R.id.et_idmateria);
        et_user = (EditText) findViewById(R.id.et_user);
        et_contraseña = (EditText) findViewById(R.id.et_contraseña);
        et_nombre = (EditText) findViewById(R.id.et_nombre);
        et_apellidop = (EditText) findViewById(R.id.et_apellidop);
        et_apellidom = (EditText) findViewById(R.id.et_apellidom);
        et_No_control = (EditText) findViewById(R.id.et_No_control);

    }
    public void alta (View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "materia", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String idmateria = et_idmateria.getText().toString();
        String user = et_user.getText().toString();
        String contraseña = et_contraseña.getText().toString();
        String nombre = et_nombre.getText().toString();
        String apellidop = et_apellidop.getText().toString();
        String apellidom = et_apellidom.getText().toString();
        String No_control = et_No_control.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("id_materia", idmateria);
        registro.put("user", user);
        registro.put("contraseña", contraseña);
        registro.put("nombre", nombre);
        registro.put("apellido_p", apellidop);
        registro.put("apellido_m", apellidom);
        registro.put("No_control", No_control);

        bd.insert("materia", null, registro);
        bd.close();

        et_idmateria.setText("");
        et_user.setText("");
        et_contraseña.setText("");
        et_nombre.setText("");
        et_apellidop.setText("");
        et_apellidom.setText("");
        et_No_control.setText("");

        Toast.makeText(this,"Se agrego un nuevo usuario a una nueva materia",Toast.LENGTH_SHORT).show();

    }
    public void consulta(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "materia", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String idmateria = et_idmateria.getText().toString();
        Cursor fila = bd.rawQuery("select user, contraseña, nombre, apellido_p, apellido_m, No_control from materia where id_materia=" + idmateria, null);
        if (fila.moveToFirst()) {
            et_user.setText(fila.getString(0));
            et_contraseña.setText(fila.getString(1));
            et_nombre.setText(fila.getString(2));
            et_apellidop.setText(fila.getString(3));
            et_apellidom.setText(fila.getString(4));
            et_No_control.setText(fila.getString(5));
        } else {
            Toast.makeText(this,"No existe el la maeteria",Toast.LENGTH_SHORT).show();
        }
        bd.close();
    }

    public void baja(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "materia", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String idmateria = et_idmateria.getText().toString();
        int cant = bd.delete("materia","id_materia=" + idmateria, null);
        bd.close();

        et_idmateria.setText("");
        et_user.setText("");
        et_contraseña.setText("");
        et_nombre.setText("");
        et_apellidop.setText("");
        et_apellidom.setText("");
        et_No_control.setText("");

        if (cant == 1) {
            Toast.makeText(this, "Se borró la materia",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No existe la materia",Toast.LENGTH_SHORT).show();
        }
    }

    public void modificacion (View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "materia", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String idmateria = et_idmateria.getText().toString();
        String user = et_user.getText().toString();
        String contraseña = et_contraseña.getText().toString();
        String nombre = et_nombre.getText().toString();
        String apellidop = et_apellidop.getText().toString();
        String apellidom = et_apellidom.getText().toString();
        String No_control = et_No_control.getText().toString();

        ContentValues registro = new ContentValues();

        registro.put("id_materia", idmateria);
        registro.put("user", user);
        registro.put("contraseña", contraseña);
        registro.put("nombre", nombre);
        registro.put("apellido_p", apellidop);
        registro.put("apellido_m", apellidom);
        registro.put("No_control", No_control);

        int cant = bd.update("materia", registro, "id_materia=" + idmateria, null);
        bd.close();

        if (cant == 1) {
            Toast.makeText(this, "Se modificaron los datos de la materia",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No existe la materia",Toast.LENGTH_SHORT).show();
        }

    }

    public void limpia (View v){
        et_idmateria.setText("");
        et_user.setText("");
        et_contraseña.setText("");
        et_nombre.setText("");
        et_apellidop.setText("");
        et_apellidom.setText("");
        et_No_control.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
