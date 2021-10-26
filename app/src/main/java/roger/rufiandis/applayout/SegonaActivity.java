package roger.rufiandis.applayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SegonaActivity extends AppCompatActivity
{

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segona);

        Bundle b =  getIntent().getExtras();

        TextView tv = findViewById(R.id.tvCodiSegona);

        tv.setText( b.get("codi").toString());


    }
}