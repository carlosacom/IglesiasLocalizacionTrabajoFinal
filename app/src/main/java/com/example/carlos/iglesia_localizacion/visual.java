package com.example.carlos.iglesia_localizacion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class visual extends AppCompatActivity {
    handle Dbb ;
    TextView iglesia;
    ImageView foto;
    ListView datos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visual);
        Bundle bundle = getIntent().getExtras();
        int a = bundle.getInt("codigo");
        Dbb = new handle(this);
        foto=(ImageView)findViewById(R.id.foto);
        iglesia = (TextView)findViewById(R.id.iglesia);
        datos=(ListView)findViewById(R.id.datos);
        String nombre= Dbb.obtener_datos(1,a);
        iglesia.setText("Iglesia: "+nombre);
        ArrayList lista = new ArrayList();
        lista.add("Tipo de iglesia: "+Dbb.obtener_datos(2,a));
        lista.add("Direccion: "+Dbb.obtener_datos(4,a));
        lista.add("Telefono: "+Dbb.obtener_datos(3,a));
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,lista);
        datos.setAdapter(adapter);
        switch (a)
        {
            case 1:
                foto.setImageResource(R.drawable.j1);
                break;
            case 2:foto.setImageResource(R.drawable.j2);
                break;
            case 3:foto.setImageResource(R.drawable.j3);
                break;
            case 4:foto.setImageResource(R.drawable.j4);
                break;
            case 5:foto.setImageResource(R.drawable.j5);
                break;
            case 6:foto.setImageResource(R.drawable.j6);
                break;
            case 7:foto.setImageResource(R.drawable.j7);
                break;
            case 8:foto.setImageResource(R.drawable.j8);
                break;
            case 9:foto.setImageResource(R.drawable.j9);
                break;
            case 10:foto.setImageResource(R.drawable.j10);
                break;
        }
    }
    public void ubi(View view)
    {
        Bundle bundle = getIntent().getExtras();
        int a = bundle.getInt("codigo");
        Intent siguiente = new Intent(visual.this, ubicacion.class);
        siguiente.putExtra("codigo",a);
        startActivity(siguiente);
    }
    public void hora(View view)
    {
        Bundle bundle = getIntent().getExtras();
        int a = bundle.getInt("codigo");
        Intent siguiente = new Intent(visual.this, horarios.class);
        siguiente.putExtra("codigo",a);
        startActivity(siguiente);
    }
}
