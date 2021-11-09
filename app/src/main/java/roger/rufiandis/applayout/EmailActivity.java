package roger.rufiandis.applayout;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EmailActivity extends AppCompatActivity
{

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        Bundle b = getIntent().getExtras();
        TextView tvMail = findViewById(R.id.mail);
        tvMail.setText("Per a: " + b.get("email"));
    }

    public void enviar(View v)
    {
        EditText et = findViewById(R.id.contingut);
        et.setText("");
        new MessageBox().show("Correu enviat",
                "S'ha enviat el correu electronic");
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