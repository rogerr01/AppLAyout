package roger.rufiandis.applayout;

import static roger.rufiandis.applayout.ControladorBD.DATABASE_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity
{

    // Camps de Text
    EditText tfCodi;
    EditText tfNom;
    EditText tfCognoms;
    EditText tfTelefon;
    EditText tfMail;

    // Botons
    RadioGroup rgGenere;
    RadioButton rbHome;
    RadioButton rbDona;
    ToggleButton tbActivar;
    Button btEsborrar;
    Button btTargeta;
    Button btRegistrar;


    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carregarElements();
    }


    public void carregarElements ()
    {
        // Camps de text
        tfCodi = findViewById(R.id.tfCodi);
        tfNom = findViewById(R.id.tfNom);
        tfCognoms = findViewById(R.id.tfCognoms);
        tfTelefon = findViewById(R.id.tfTelefon);
        tfMail = findViewById(R.id.tfMail);

        // Botons
        rgGenere = findViewById(R.id.rgGenere);
        rbHome = findViewById(R.id.rbHome);
        rbDona = findViewById(R.id.rbDona);
        tbActivar = findViewById(R.id.tbActivar);
        btEsborrar = findViewById(R.id.btEsborrar);
        btTargeta = findViewById(R.id.btEditar);
        btRegistrar = findViewById(R.id.btRegistrar);
    }

    // Accions dels botons

    public void esborrar (View view)
    {
        // Esborrar camps
        tfNom.setText("");
        tfCognoms.setText("");
        tfTelefon.setText("");
        tfMail.setText("");
        rgGenere.clearCheck();
        tbActivar.setChecked(false);
    }

    public void activar (View view)
    {
        tbActivar.setBackgroundColor(tbActivar.isChecked() ? Color.RED : Color.GREEN);
    }


    public void inici (MenuItem m)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }


    public void editar (View v)
    {


    }

    public void buscar (View v)
    {
        String id = tfCodi.getText().toString();

        SQLiteDatabase db = new ControladorBD(this).getWritableDatabase();

        Cursor c = db.rawQuery("SELECT " +
                EstructuraBD.COLUMN_NAME1 + EstructuraBD.COMMA_SEP +
                EstructuraBD.COLUMN_NAME2 + EstructuraBD.COMMA_SEP +
                EstructuraBD.COLUMN_NAME3 + EstructuraBD.COMMA_SEP +
                EstructuraBD.COLUMN_NAME4 + EstructuraBD.COMMA_SEP +
                EstructuraBD.COLUMN_NAME5 + EstructuraBD.COMMA_SEP +
                EstructuraBD.COLUMN_NAME6 + EstructuraBD.COMMA_SEP +
                EstructuraBD.COLUMN_NAME7 +
                " FROM " + EstructuraBD.TABLE_NAME +
                " WHERE " + EstructuraBD.COLUMN_NAME1 + " = " + id
                , null);

        if (c.moveToFirst()){
            do {

                tfCodi.setText(c.getString(0));
                tfNom.setText(c.getString(1));
                tfCognoms.setText(c.getString(2));
                tfTelefon.setText(c.getString(3));
                tfMail.setText(c.getString(4));
                rbHome.setSelected(c.getString(5).equals("Home"));
                tbActivar.setSelected(c.getString(6).equals("true"));

            } while(c.moveToNext());
        }

        c.close();
        db.close();

    }



    public void guardar (View v)
    {
        String codi = tfCodi.getText().toString();
        String nom = tfNom.getText().toString();
        String cognoms = tfCognoms.getText().toString();
        int telefon = Integer.parseInt(tfTelefon.getText().toString());
        String email = tfMail.getText().toString();
        String genere = rbHome.isChecked() ? "Home" : "Dona";
        boolean actiu = tbActivar.isChecked();

        SQLiteDatabase db = new ControladorBD(this).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EstructuraBD.COLUMN_NAME1, codi);
        values.put(EstructuraBD.COLUMN_NAME2, nom);
        values.put(EstructuraBD.COLUMN_NAME3, cognoms);
        values.put(EstructuraBD.COLUMN_NAME4, telefon);
        values.put(EstructuraBD.COLUMN_NAME5, email);
        values.put(EstructuraBD.COLUMN_NAME6, genere);
        values.put(EstructuraBD.COLUMN_NAME7, actiu);

        db.insert(DATABASE_NAME, null, values);

        new MessageBox().show("S'ha guardat el registre del client " + tfCodi.getText());

        esborrar(v);
    }

    public class MessageBox
    {
        void show (String message)
        {
            dialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Client guardat")
                    .setMessage(message)
                    .setPositiveButton("OK", (dialogInterface, i) -> dialog.cancel())
                    .show();
        }

        private AlertDialog dialog;
    }

    public void sortir (MenuItem v)
    {
        System.exit(0);
    }



}























