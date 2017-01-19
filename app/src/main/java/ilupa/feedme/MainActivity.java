package ilupa.feedme;

import android.database.sqlite.SQLiteCursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button bAdd, bCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item_popup);
        bAdd = (Button)findViewById(R.id.bAdd);
        bCancel = (Button)findViewById(R.id.bCancel);

        final DataBaseHelper db = new DataBaseHelper(this);



        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean czysieudalo;
                czysieudalo = db.wstawDane("Bot","100ml","1","40","39","test");
                if(czysieudalo) {
                    Toast.makeText(MainActivity.this,"Udało się",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this,"Nie udało się",Toast.LENGTH_LONG).show();
                }
            }
        });

        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteCursor kursor = db.pobierzDane();
                if(kursor.getCount()>0) {
                    StringBuffer buff  = new StringBuffer();
                    while (kursor.moveToNext()) {
                        buff.append("ID "+kursor.getString(0)+ "\n");
                        buff.append("Type "+kursor.getString(1)+ "\n");
                        buff.append("Volume "+kursor.getString(2)+ "\n");
                        buff.append("Start Time "+kursor.getString(3)+ "\n");
                        buff.append("Stop Time "+kursor.getString(4)+ "\n");
                        buff.append("Duration "+kursor.getString(5)+ "\n");
                        buff.append("Comment "+kursor.getString(6)+ "\n");
                        buff.append(" - - - - - - - - - - - - - - - - -\n");

                    }
                    PokazWiadomosc("rekord",buff.toString());
                } else {
                    PokazWiadomosc("Brak","Niestety brak rekordów");
                }

            }
        });


    }
    public void PokazWiadomosc(String tytul, String wiadomosc) {
        AlertDialog.Builder budowniczy = new AlertDialog.Builder(this);
        budowniczy.setCancelable(true);
        budowniczy.setMessage(wiadomosc);
        budowniczy.setTitle(tytul);
        budowniczy.show();
    }
}
