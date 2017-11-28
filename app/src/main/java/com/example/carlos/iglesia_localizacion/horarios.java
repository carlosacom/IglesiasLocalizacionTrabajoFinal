package com.example.carlos.iglesia_localizacion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class horarios extends AppCompatActivity {
    TextView iglesia;
    handle Dbb ;
    ListView horario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios);
        Dbb= new handle(this);
        Bundle bundle = getIntent().getExtras();
        int a = bundle.getInt("codigo");
        String nombre= Dbb.obtener_datos(1,a);
        iglesia = (TextView)findViewById(R.id.iglesia);
        iglesia.setText("Iglesia: "+nombre);
        ArrayList hora= new ArrayList();
        horario=(ListView)findViewById(R.id.horarios);
        for (byte i=1 ;i<8;i++)
        {
            switch (i)
            {
                case 1:
                    hora.add("Lunes");
                    hora=Dbb.obtener_datos_horario(hora,"lunes",a);
                    break;
                case 2:
                    hora.add("Martes");
                    hora=Dbb.obtener_datos_horario(hora,"martes",a);
                    break;
                case 3:
                    hora.add("Miercoles");
                    hora=Dbb.obtener_datos_horario(hora,"miercoles",a);
                    break;
                case 4:
                    hora.add("Jueves");
                    hora=Dbb.obtener_datos_horario(hora,"jueves",a);
                    break;
                case 5:
                    hora.add("Viernes");
                    hora=Dbb.obtener_datos_horario(hora,"viernes",a);
                    break;
                case 6:
                    hora.add("Sabado");
                    hora=Dbb.obtener_datos_horario(hora,"sabado",a);
                    break;
                case 7:
                    hora.add("Domingo");
                    hora=Dbb.obtener_datos_horario(hora,"domingo",a);
                    break;
            }
        }
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,hora);
        horario.setAdapter(adapter);
    }
}
