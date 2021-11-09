package roger.rufiandis.applayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EmailActivity extends AppCompatActivity
{

    public void inici(MenuItem m)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    String email;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle b = getIntent().getExtras();
        TextView tvMail = findViewById(R.id.mail);
        email = b.get("email").toString();
        tvMail.setText("Per a: " + b.get("email"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public void sortir(MenuItem v)
    {
        System.exit(0);
    }

    public void enviar(View v)
    {
        EditText et = findViewById(R.id.contingut);
        et.setText("");
        new MessageBox().show("Correu enviat",
                "S'ha enviat el correu electronic a " + email);
    }


    public class MessageBox
    {
        void show(String title, String message)
        {
            dialog = new AlertDialog.Builder(EmailActivity.this) // Pass a reference to your main
                    // activity here
                    .setTitle(title)
                    .setMessage(message)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i)
                        {
                            dialog.cancel();
                        }
                    })
                    .show();
        }

        private AlertDialog dialog;
    }
}