package a.a.a;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class AmountGetter extends Thread {
    String f="";
    String t="";
    public AmountGetter(String f,String t){
        this.f=f;
        this.t=t;
    }
    @Override
    public void run() {
        try {
            URL url = new URL("https://api.exchangeratesapi.io/latest");
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            huc.setRequestMethod("GET");
            huc.setDoInput(true);
            huc.connect();
            BufferedReader br = new BufferedReader(new InputStreamReader(huc.getInputStream()));
            sleep(10);
            String m;
            String r="";
            while((m=br.readLine())!=null){
                r+=m;
            }
            double h;
            if(!f.equals("EUR")) {
                 h=Double.parseDouble(r.split(f + "\":")[1].substring(0, r.split(f + "\":")[1].indexOf(",")));
            }else{
                h=1;
            }
            double e;
            if(!t.equals("EUR")) {
                 e=Double.parseDouble(r.split(t + "\":")[1].substring(0, r.split(t + "\":")[1].indexOf(",")));
            }else{
                e=1;
            }
            MainActivity.r=h/e;
            MainActivity.a=true;
        }catch (Exception e){
            Log.e("Exception",e+"");
        }
    }
}
