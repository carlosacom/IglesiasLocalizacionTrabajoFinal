package com.example.carlos.iglesia_localizacion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    Spinner igle;
    String[] iglesias;
    handle Dbb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Dbb= new handle(this);
        igle=(Spinner)findViewById(R.id.iglesia);
        Dbb.llenar();
        iglesias =Dbb.llenariglesias();
        ArrayAdapter adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,iglesias);
        igle.setAdapter(adapter);
    }
    public void salir(View view)
    {
        finish();
        Intent salir = new Intent (Intent.ACTION_MAIN);
        salir.addCategory(Intent.CATEGORY_HOME);
        salir.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(salir);
    }
    public void visualizar(View view)
    {
        Dbb= new handle(this);
        Intent a=new Intent(MainActivity.this,visual.class);
        int i=Dbb.bucarcodigo_iglesia(igle.getSelectedItem().toString());
        a.putExtra("codigo",i);
        startActivity(a);
    }
}
