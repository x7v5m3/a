package a.a.a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    Spinner spf;
    Spinner spt;
    EditText eta;
    EditText etr;
    Button bc;
    String[] s={"EUR", "CAD", "HKD", "ISK", "PHP", "DKK", "HUF", "CZK", "AUD", "RON", "SEK", "IDR", "INR", "BRL", "RUB", "HRK", "JPY", "THB", "CHF", "SGD", "PLN", "BGN", "TRY", "CNY", "NOK", "NZD", "ZAR", "USD", "MXN", "ILS","GBP","KRW","MYR"};
    String t="EUR";
    String f="EUR";
    public static double r;
    public static boolean a=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bc=(Button) findViewById(R.id.bc);
        spf=(Spinner) findViewById(R.id.spf);
        spt=(Spinner) findViewById(R.id.spt);
        eta=(EditText) findViewById(R.id.eta);
        etr=(EditText) findViewById(R.id.etr);
        spf.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> av, View v, int i, long l) {
                f=s[i];
            }
            @Override
            public void onNothingSelected(AdapterView<?> av) {}
        });
        spt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> av, View v, int i, long l) {
                t=s[i];
            }
            @Override
            public void onNothingSelected(AdapterView<?> av) {}
        });
        spf.setAdapter(new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item,s));
        spt.setAdapter(new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item,s));
        bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AmountGetter ag=new AmountGetter(f,t);
                ag.start();
                Waiting w=new Waiting();
                w.start();
            }
        });
    }
    class Waiting extends Thread{
        @Override
        public void run(){
            try{
                while(!a){
                    sleep(2);
                }
                a=false;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        double o=Double.parseDouble(eta.getText().toString())/r;
                        etr.setText(o+"");
                    }
                });
            }catch(Exception e){}
        }
    }
}
