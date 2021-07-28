package sg.edu.rp.id20006707.songapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Editing extends AppCompatActivity {
    EditText etID, etTitle, etSingers, etYear;
    RadioGroup rg;
    Button btnUpdate, btnDelete;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing);

        etID = findViewById(R.id.editID);
        etTitle = findViewById(R.id.etTitle);
        etSingers = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);
        rg = findViewById(R.id.rg);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        Intent intent = getIntent();
        data = (Song) intent.getSerializableExtra("data");


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(Editing.this);
                int selected = rg.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selected);
                int stars = Integer.parseInt(radioButton.getText().toString());

                data.setTitle(etTitle.getText().toString());
                data.setSingers(etSingers.getText().toString());
                data.setYear(Integer.parseInt(etYear.getText().toString()));
                data.setStars(stars);

                dbh.updateSong(data);
                dbh.close();
                Toast.makeText(Editing.this,"Song inserted", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(Editing.this);
                dbh.deleteSong(data.getId());
                finish();
            }
        });
    }
}