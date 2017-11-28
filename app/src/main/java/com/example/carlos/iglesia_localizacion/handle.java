package com.example.carlos.iglesia_localizacion;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class handle {
    public static  myDbHelper myhelper;
    public handle(Context context)
    {
        myhelper=new myDbHelper(context);
    }
    public static void  llenado_db(int tabla, String dato1,String dato2,String dato3,String dato4,String dato5,String dato6,int codigo)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        Long valido;
        ContentValues valores = new ContentValues();
        if(tabla==0)
        {
            //CREATE TABLE tipo_iglesias(
            // cod_tipo INTEGER PRIMARY KEY AUTOINCREMENT,
            // descripcion  TEXT NOT NULL);
            valores.put("cod_tipo",codigo);
            valores.put("descripcion", dato1);
            valido = dbb.insert("tipo_iglesias", null, valores);
        }
        else
        {
            if(tabla==1)
            {
                // CREATE TABLE iglesias(
                // cod_iglesia INTEGER PRIMARY KEY AUTOINCREMENT,
                // tipo_iglesia INTEGER PRIMARY KEY,
                // nombre_iglesia TEXT NOT NULL,
                // direccion TEXT NOT NULL,
                // telefono TEXT NOT NULL,
                // longitud DOUBLE NOT NULL,
                // latitud DOUBLE NOT NULL,
                // CONSTRAINT fk_tipo FOREIGN KEY (tipo_iglesia) REFERENCES tipo_iglesias(cod_tipo));
                int tip=Integer.parseInt(dato1);
                valores.put("cod_iglesia",codigo);
                valores.put("tipo_iglesia", tip);
                valores.put("nombre_iglesia", dato2);
                valores.put("direccion", dato3);
                valores.put("telefono", dato4);
                Double lon =Double.parseDouble(dato5);
                valores.put("longitud", lon);
                Double lat=Double.parseDouble(dato6);
                valores.put("latitud",lat );
                valido = dbb.insert("iglesias", null, valores);

            }
            else
            {
                if(tabla==3)
                {
                    //CREATE TABLE horario(
                    // cod_horario INTEGER PRIMARY KEY AUTOINCREMENT,
                    // cod_iglesia INTEGER PRIMARY KEY,
                    // cod_tipo INTEGER PRIMARY KEY,
                    // dia TEXT PRIMARY KEY,
                    // hora_ini TEXT,
                    // CONSTRAINT FK_cod FOREIGN KEY (cod_iglesia) REFERENCES iglesias(cod_iglesia),
                    // CONSTRAINT FK_tip FOREIGN KEY (cod_tipo) REFERENCES iglesias(tipo_iglesia));
                    valores.put("cod_horario",codigo);
                    int igl=Integer.parseInt(dato1);
                    valores.put("cod_iglesia", igl);
                    int tip=Integer.parseInt(dato2);
                    valores.put("cod_tipo",tip);
                    valores.put("dia", dato3);
                    valores.put("hora_ini", dato4);
                    valido = dbb.insert("horario", null, valores);
                }
            }
        }
    }

    public static String[] llenariglesias() {
        String []a = new String[10];
        byte i =0;
        try {
            SQLiteDatabase dbb = myhelper.getWritableDatabase();
            String qr = "SELECT nombre_iglesia FROM iglesias;";
            Cursor dat = dbb.rawQuery(qr, null);
            if (dat.moveToFirst()) {
                do {
                    a[i] = dat.getString(0);
                    i++;
                    dat.moveToNext();
                } while (i<10);
            }
            return a;
        }catch (Exception e)
        {
            a[i]="error";
            return a;
        }

    }

    public static byte llenar() {
        byte i =0;
        try {
            llenado_db(0, "parroquia", null, null, null, null, null, 1);
            llenado_db(0, "Capilla", null, null, null, null, null, 2);
            llenado_db(0, "Catedral", null, null, null, null, null, 3);

            handle.llenado_db(1, "3", "Catedral De San Jose", "Av. 5A #10-73, Cúcuta, Norte de Santander", "321 7424845", "-72.50341699865498", "7.886376717750547", 1);
            handle.llenado_db(1, "1", "Nuestra Señora De Los Desamparados", "Cl. 12 #2-89, barrio la playa, Cúcuta, Norte de Santander", "(7) 5713667", "-72.50150860694089", "7.884705561391345", 2);
            handle.llenado_db(1, "2", "Capilla Del Carmen", "Av. 2 #11-59, barrio la playa, Cúcuta, Norte de Santander", "No aplica", "-72.50008167174497", "7.884636483240888", 3);
            handle.llenado_db(1, "1", "Nuestra Señora Del Perpetuo Socorro", "Calle 15 #14-41, barrio El Contento, Cúcuta, Norte de Santander", "5821672", "-72.51223610189595", "7.880068000719348", 4);
            handle.llenado_db(1, "1", "Cristo Envangelizador", "Cll 5AN # 18 AE-35, barrio Playa Hermosa, Cúcuta, Norte de Santander", "5773737", "-72.48383687284627", "7.906295903050742", 5);
            handle.llenado_db(1, "1", "San Luis Gonzaga", "Av. 3 #13-85, barrio San Luís, Cúcuta, Norte de Santander", "5842550", "-72.48286725428738", "7.89252040494893", 6);
            handle.llenado_db(1, "1", "Espiritu Santo", "Av. 9E #2N-28, barrio Quinta Oriental, Cúcuta, Norte de Santander", "5742707", "-72.49299527552762", "7.9004373408851745", 7);
            handle.llenado_db(1, "1", "Domingo Savio", "Cll 5N #2E-35,  barrio Ceiba II, Cúcuta, Norte de Santander", "5774398", "-72.49724821338465", "7.90263201485168", 8);
            handle.llenado_db(1, "1", "Santisimo Redentor", "Av. 4 diagonal santander # 2-85 barrio Lleras Restrepo, Cúcuta, Norte de Santander", "5743276", "-72.50396178254893", "7.895028365053699", 9);
            handle.llenado_db(1, "1", "Sagrada Familia", "Cll 18 #11-51,barrio La Libertad, Cúcuta, Norte de Santander", "5760564", "-72.47712359914591", "7.887034790086583", 10);

            handle.llenado_db(3, "1", "3", "lunes", "7:00 AM", null, null, 1);
            handle.llenado_db(3, "1", "3", "lunes", "12:00 M", null, null, 2);
            handle.llenado_db(3, "1", "3", "lunes", "6:00 PM", null, null, 3);
            handle.llenado_db(3, "1", "3", "martes", "7:00 AM", null, null, 4);
            handle.llenado_db(3, "1", "3", "martes", "12:00 M", null, null, 5);
            handle.llenado_db(3, "1", "3", "martes", "6:00 PM", null, null, 6);
            handle.llenado_db(3, "1", "3", "miercoles", "7:00 AM", null, null, 7);
            handle.llenado_db(3, "1", "3", "miercoles", "12:00 M", null, null, 8);
            handle.llenado_db(3, "1", "3", "miercoles", "6:00 PM", null, null, 9);
            handle.llenado_db(3, "1", "3", "jueves", "7:00 AM", null, null, 10);
            handle.llenado_db(3, "1", "3", "jueves", "12:00 M", null, null, 12);
            handle.llenado_db(3, "1", "3", "jueves", "6:00 PM", null, null, 13);
            handle.llenado_db(3, "1", "3", "viernes", "7:00 AM", null, null, 14);
            handle.llenado_db(3, "1", "3", "viernes", "12:00 M", null, null, 15);
            handle.llenado_db(3, "1", "3", "viernes", "6:00 PM", null, null, 16);
            handle.llenado_db(3, "1", "3", "sabado", "7:00 AM", null, null, 17);
            handle.llenado_db(3, "1", "3", "sabado", "12:00 M", null, null, 18);
            handle.llenado_db(3, "1", "3", "sabado", "6:00 PM", null, null, 19);
            handle.llenado_db(3, "1", "3", "domingo", "7:00 AM", null, null, 20);
            handle.llenado_db(3, "1", "3", "domingo", "9:00 AM", null, null, 21);
            handle.llenado_db(3, "1", "3", "domingo", "11:00 AM", null, null, 22);
            handle.llenado_db(3, "1", "3", "domingo", "12:00 M", null, null, 23);
            handle.llenado_db(3, "1", "3", "domingo", "6:00 PM", null, null, 24);

            handle.llenado_db(3, "2", "1", "lunes", "7:00 AM", null, null, 25);
            handle.llenado_db(3, "2", "1", "lunes", "6:00 PM", null, null, 26);
            handle.llenado_db(3, "2", "1", "martes", "7:00 AM", null, null, 27);
            handle.llenado_db(3, "2", "1", "martes", "6:00 PM", null, null, 28);
            handle.llenado_db(3, "2", "1", "miercoles", "7:00 AM", null, null, 29);
            handle.llenado_db(3, "2", "1", "miercoles", "6:00 PM", null, null, 30);
            handle.llenado_db(3, "2", "1", "jueves", "7:00 AM", null, null, 31);
            handle.llenado_db(3, "2", "1", "jueves", "6:00 PM", null, null, 32);
            handle.llenado_db(3, "2", "1", "viernes", "7:00 AM", null, null, 33);
            handle.llenado_db(3, "2", "1", "viernes", "6:00 PM", null, null, 34);
            handle.llenado_db(3, "2", "1", "sabado", "7:00 AM", null, null, 35);
            handle.llenado_db(3, "2", "1", "sabado", "6:00 PM", null, null, 36);
            handle.llenado_db(3, "2", "1", "domingo", "7:00 AM", null, null, 37);
            handle.llenado_db(3, "2", "1", "domingo", "9:00 AM", null, null, 38);
            handle.llenado_db(3, "2", "1", "domingo", "11:00 AM", null, null, 39);
            handle.llenado_db(3, "2", "1", "domingo", "6:00 PM", null, null, 40);

            handle.llenado_db(3, "3", "2", "lunes", "7:00 AM", null, null, 41);
            handle.llenado_db(3, "3", "2", "lunes", "6:00 PM", null, null, 42);
            handle.llenado_db(3, "3", "2", "martes", "7:00 AM", null, null, 43);
            handle.llenado_db(3, "3", "2", "martes", "6:00 PM", null, null, 44);
            handle.llenado_db(3, "3", "2", "miercoles", "7:00 AM", null, null, 45);
            handle.llenado_db(3, "3", "2", "miercoles", "6:00 PM", null, null, 46);
            handle.llenado_db(3, "3", "2", "jueves", "7:00 AM", null, null, 47);
            handle.llenado_db(3, "3", "2", "jueves", "6:00 PM", null, null, 48);
            handle.llenado_db(3, "3", "2", "viernes", "7:00 AM", null, null, 49);
            handle.llenado_db(3, "3", "2", "viernes", "6:00 PM", null, null, 50);
            handle.llenado_db(3, "3", "2", "sabado", "7:00 AM", null, null, 51);
            handle.llenado_db(3, "3", "2", "sabado", "6:00 PM", null, null, 52);
            handle.llenado_db(3, "3", "2", "domingo", "7:00 AM", null, null, 53);
            handle.llenado_db(3, "3", "2", "domingo", "9:00 AM", null, null, 54);
            handle.llenado_db(3, "3", "2", "domingo", "11:00 AM", null, null, 55);
            handle.llenado_db(3, "3", "2", "domingo", "6:00 PM", null, null, 56);

            handle.llenado_db(3, "4", "1", "lunes", "6:00 PM", null, null, 57);
            handle.llenado_db(3, "4", "1", "martes", "6:00 PM", null, null, 58);
            handle.llenado_db(3, "4", "1", "miercoles", "6:00 PM", null, null, 59);
            handle.llenado_db(3, "4", "1", "jueves", "6:00 PM", null, null, 60);
            handle.llenado_db(3, "4", "1", "viernes", "6:00 PM", null, null, 61);
            handle.llenado_db(3, "4", "1", "sabado", "6:00 PM", null, null, 62);
            handle.llenado_db(3, "4", "1", "domingo", "6:00 AM", null, null, 63);
            handle.llenado_db(3, "4", "1", "domingo", "9:00 AM", null, null, 64);
            handle.llenado_db(3, "4", "1", "domingo", "5:30 PM", null, null, 65);
            handle.llenado_db(3, "4", "1", "domingo", "7:00 PM", null, null, 66);

            handle.llenado_db(3, "5", "1", "lunes", "6:00 PM", null, null, 67);
            handle.llenado_db(3, "5", "1", "martes", "6:00 PM", null, null, 68);
            handle.llenado_db(3, "5", "1", "miercoles", "6:00 PM", null, null, 69);
            handle.llenado_db(3, "5", "1", "jueves", "6:00 PM", null, null, 70);
            handle.llenado_db(3, "5", "1", "viernes", "6:00 PM", null, null, 71);
            handle.llenado_db(3, "5", "1", "sabado", "6:00 PM", null, null, 72);
            handle.llenado_db(3, "5", "1", "domingo", "6:00 AM", null, null, 73);
            handle.llenado_db(3, "5", "1", "domingo", "9:00 AM", null, null, 74);
            handle.llenado_db(3, "5", "1", "domingo", "11:00 AM", null, null, 75);
            handle.llenado_db(3, "5", "1", "domingo", "5:30 PM", null, null, 76);
            handle.llenado_db(3, "5", "1", "domingo", "7:00 PM", null, null, 77);

            handle.llenado_db(3, "6", "1", "lunes", "6:30 AM", null, null, 78);
            handle.llenado_db(3, "6", "1", "lunes", "5:00 PM", null, null, 79);
            handle.llenado_db(3, "6", "1", "lunes", "6:00 PM", null, null, 80);
            handle.llenado_db(3, "6", "1", "martes", "6:30 AM", null, null, 81);
            handle.llenado_db(3, "6", "1", "martes", "5:00 PM", null, null, 82);
            handle.llenado_db(3, "6", "1", "martes", "6:00 PM", null, null, 83);
            handle.llenado_db(3, "6", "1", "miercoles", "6:30 AM", null, null, 84);
            handle.llenado_db(3, "6", "1", "miercoles", "5:00 PM", null, null, 85);
            handle.llenado_db(3, "6", "1", "miercoles", "6:00 PM", null, null, 86);
            handle.llenado_db(3, "6", "1", "jueves", "6:30 AM", null, null, 87);
            handle.llenado_db(3, "6", "1", "jueves", "5:00 PM", null, null, 88);
            handle.llenado_db(3, "6", "1", "jueves", "6:00 PM", null, null, 89);
            handle.llenado_db(3, "6", "1", "viernes", "6:30 AM", null, null, 90);
            handle.llenado_db(3, "6", "1", "viernes", "5:00 PM", null, null, 91);
            handle.llenado_db(3, "6", "1", "viernes", "6:00 PM", null, null, 92);
            handle.llenado_db(3, "6", "1", "sabado", "6:30 AM", null, null, 93);
            handle.llenado_db(3, "6", "1", "sabado", "4:00 PM", null, null, 94);
            handle.llenado_db(3, "6", "1", "sabado", "6:00 PM", null, null, 95);
            handle.llenado_db(3, "6", "1", "domingo", "7:00 AM", null, null, 96);
            handle.llenado_db(3, "6", "1", "domingo", "9:00 AM", null, null, 97);
            handle.llenado_db(3, "6", "1", "domingo", "11:00 AM", null, null, 98);
            handle.llenado_db(3, "6", "1", "domingo", "6:00 PM", null, null, 99);

            handle.llenado_db(3, "7", "1", "lunes", "6:00 AM", null, null, 100);
            handle.llenado_db(3, "7", "1", "lunes", "5:30 PM", null, null, 101);
            handle.llenado_db(3, "7", "1", "lunes", "7:00 PM", null, null, 102);
            handle.llenado_db(3, "7", "1", "martes", "6:00 AM", null, null, 103);
            handle.llenado_db(3, "7", "1", "martes", "5:30 PM", null, null, 104);
            handle.llenado_db(3, "7", "1", "martes", "7:00 PM", null, null, 105);
            handle.llenado_db(3, "7", "1", "miercoles", "6:00 AM", null, null, 106);
            handle.llenado_db(3, "7", "1", "miercoles", "5:30 PM", null, null, 107);
            handle.llenado_db(3, "7", "1", "miercoles", "7:00 PM", null, null, 108);
            handle.llenado_db(3, "7", "1", "jueves", "6:00 AM", null, null, 109);
            handle.llenado_db(3, "7", "1", "jueves", "5:30 PM", null, null, 110);
            handle.llenado_db(3, "7", "1", "jueves", "7:00 PM", null, null, 111);
            handle.llenado_db(3, "7", "1", "viernes", "6:00 AM", null, null, 112);
            handle.llenado_db(3, "7", "1", "viernes", "5:30 PM", null, null, 113);
            handle.llenado_db(3, "7", "1", "viernes", "7:00 PM", null, null, 114);
            handle.llenado_db(3, "7", "1", "sabado", "6:00 AM", null, null, 115);
            handle.llenado_db(3, "7", "1", "sabado", "6:00 PM", null, null, 116);
            handle.llenado_db(3, "7", "1", "sabado", "7:00 PM", null, null, 117);
            handle.llenado_db(3, "7", "1", "domingo", "7:00 AM", null, null, 118);
            handle.llenado_db(3, "7", "1", "domingo", "9:00 AM", null, null, 119);
            handle.llenado_db(3, "7", "1", "domingo", "11:00 AM", null, null, 120);
            handle.llenado_db(3, "7", "1", "domingo", "6:00 PM", null, null, 121);
            handle.llenado_db(3, "7", "1", "domingo", "8:00 PM", null, null, 122);

            handle.llenado_db(3, "8", "1", "lunes", "5:00 PM", null, null, 123);
            handle.llenado_db(3, "8", "1", "lunes", "6:00 PM", null, null, 124);
            handle.llenado_db(3, "8", "1", "martes", "5:00 PM", null, null, 125);
            handle.llenado_db(3, "8", "1", "martes", "6:00 PM", null, null, 126);
            handle.llenado_db(3, "8", "1", "miercoles", "5:00 PM", null, null, 127);
            handle.llenado_db(3, "8", "1", "miercoles", "6:00 PM", null, null, 128);
            handle.llenado_db(3, "8", "1", "jueves", "5:00 PM", null, null, 129);
            handle.llenado_db(3, "8", "1", "jueves", "6:00 PM", null, null, 130);
            handle.llenado_db(3, "8", "1", "viernes", "5:00 PM", null, null, 131);
            handle.llenado_db(3, "8", "1", "viernes", "6:00 PM", null, null, 132);
            handle.llenado_db(3, "8", "1", "sabado", "5:00 PM", null, null, 133);
            handle.llenado_db(3, "8", "1", "sabado", "6:00 PM", null, null, 134);
            handle.llenado_db(3, "8", "1", "domingo", "7:00 AM", null, null, 135);
            handle.llenado_db(3, "8", "1", "domingo", "9:00 AM", null, null, 136);
            handle.llenado_db(3, "8", "1", "domingo", "11:00 AM", null, null, 137);
            handle.llenado_db(3, "8", "1", "domingo", "6:00 PM", null, null, 138);

            handle.llenado_db(3, "9", "1", "lunes", "6:00 AM", null, null, 139);
            handle.llenado_db(3, "9", "1", "lunes", "5:30 PM", null, null, 140);
            handle.llenado_db(3, "9", "1", "lunes", "6:30 PM", null, null, 141);
            handle.llenado_db(3, "9", "1", "martes", "6:00 AM", null, null, 142);
            handle.llenado_db(3, "9", "1", "martes", "5:30 PM", null, null, 143);
            handle.llenado_db(3, "9", "1", "martes", "6:30 PM", null, null, 144);
            handle.llenado_db(3, "9", "1", "miercoles", "6:00 AM", null, null, 145);
            handle.llenado_db(3, "9", "1", "miercoles", "5:30 PM", null, null, 146);
            handle.llenado_db(3, "9", "1", "miercoles", "6:30 PM", null, null, 147);
            handle.llenado_db(3, "9", "1", "jueves", "6:00 AM", null, null, 148);
            handle.llenado_db(3, "9", "1", "jueves", "5:30 PM", null, null, 149);
            handle.llenado_db(3, "9", "1", "jueves", "6:30 PM", null, null, 150);
            handle.llenado_db(3, "9", "1", "viernes", "6:00 AM", null, null, 151);
            handle.llenado_db(3, "9", "1", "viernes", "5:30 PM", null, null, 152);
            handle.llenado_db(3, "9", "1", "viernes", "6:30 PM", null, null, 153);
            handle.llenado_db(3, "9", "1", "sabado", "6:00 AM", null, null, 154);
            handle.llenado_db(3, "9", "1", "sabado", "5:30 PM", null, null, 155);
            handle.llenado_db(3, "9", "1", "sabado", "6:30 PM", null, null, 156);
            handle.llenado_db(3, "9", "1", "domingo", "6:00 AM", null, null, 157);
            handle.llenado_db(3, "9", "1", "domingo", "8:00 AM", null, null, 158);
            handle.llenado_db(3, "9", "1", "domingo", "11:00 AM", null, null, 159);
            handle.llenado_db(3, "9", "1", "domingo", "5:30 PM", null, null, 160);
            handle.llenado_db(3, "9", "1", "domingo", "7:00 PM", null, null, 161);

            handle.llenado_db(3, "10", "1", "lunes", "5:00 PM", null, null, 162);
            handle.llenado_db(3, "10", "1", "lunes", "6:00 PM", null, null, 163);
            handle.llenado_db(3, "10", "1", "martes", "5:00 PM", null, null, 164);
            handle.llenado_db(3, "10", "1", "martes", "6:00 PM", null, null, 165);
            handle.llenado_db(3, "10", "1", "miercoles", "5:00 PM", null, null, 166);
            handle.llenado_db(3, "10", "1", "miercoles", "6:00 PM", null, null, 167);
            handle.llenado_db(3, "10", "1", "jueves", "5:00 PM", null, null, 168);
            handle.llenado_db(3, "10", "1", "jueves", "6:00 PM", null, null, 169);
            handle.llenado_db(3, "10", "1", "viernes", "5:00 PM", null, null, 170);
            handle.llenado_db(3, "10", "1", "viernes", "6:00 PM", null, null, 171);
            handle.llenado_db(3, "10", "1", "sabado", "5:00 PM", null, null, 172);
            handle.llenado_db(3, "10", "1", "sabado", "6:00 PM", null, null, 173);
            handle.llenado_db(3, "10", "1", "domingo", "6:00 AM", null, null, 174);
            handle.llenado_db(3, "10", "1", "domingo", "8:00 AM", null, null, 175);
            handle.llenado_db(3, "10", "1", "domingo", "6:00 PM", null, null, 176);
            handle.llenado_db(3, "10", "1", "domingo", "7:00 PM", null, null, 177);
        }
        catch (Exception e)
        {
            i=1;
        }
        return i;
    }

    public int bucarcodigo_iglesia(String s) {
        int i=0;
        try
        {
            SQLiteDatabase dbb = myhelper.getWritableDatabase();
            String qr = "SELECT cod_iglesia FROM iglesias WHERE nombre_iglesia='"+s+"';";
            Cursor dat = dbb.rawQuery(qr, null);
            dat.moveToFirst();
            i=Integer.parseInt(dat.getString(0));
            return i;
        }
        catch (Exception e)
        {
            return 0;
        }
    }

    public String obtener_datos(int tipo_consulta,int s) {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        String a="";
        String qr;
        Cursor dat;
        switch (tipo_consulta)
        {
            case 1://nombre
                dbb = myhelper.getWritableDatabase();
                qr = "SELECT nombre_iglesia FROM iglesias WHERE cod_iglesia='"+s+"';";
                dat = dbb.rawQuery(qr, null);
                dat.moveToFirst();
                a=dat.getString(0);
                break;
            case 2:
                qr = "SELECT tipo_iglesia FROM iglesias WHERE cod_iglesia='"+s+"';";
                dat = dbb.rawQuery(qr, null);
                dat.moveToFirst();
                int x = Integer.parseInt(dat.getString(0));
                qr = "SELECT descripcion FROM tipo_iglesias WHERE cod_tipo='"+x+"';";
                dat = dbb.rawQuery(qr, null);
                dat.moveToFirst();
                a=dat.getString(0);
                break;
            case 3:
            {
                qr = "SELECT telefono FROM iglesias WHERE cod_iglesia='"+s+"';";
                dat = dbb.rawQuery(qr, null);
                dat.moveToFirst();
                a=dat.getString(0);
                break;
            }
            case 4:
            {
                qr = "SELECT direccion FROM iglesias WHERE cod_iglesia='"+s+"';";
                dat = dbb.rawQuery(qr, null);
                dat.moveToFirst();
                a=dat.getString(0);
            }

        }
        return a;
    }

    public ArrayList obtener_datos_horario(ArrayList hora,String dia,int codigo_iglesia)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        String qr = "SELECT hora_ini FROM horario WHERE dia ='"+dia+"' AND cod_iglesia ="+codigo_iglesia+";";
        Cursor dat = dbb.rawQuery(qr, null);
        if (dat.moveToFirst()) {
            do {
                hora.add(dat.getString(0));
           }while(dat.moveToNext());
        }
        return hora;
    }

    public double[] obtener_ubicacion(int codigo) {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        String qr = "SELECT latitud,longitud FROM iglesias WHERE cod_iglesia='"+codigo+"';";
         Cursor dat = dbb.rawQuery(qr, null);
        dat.moveToFirst();
        double local[]= new double [2];
        local[0]=Double.parseDouble(dat.getString(0));
        local[1]=Double.parseDouble(dat.getString(1));
        return local;
    }

    static class myDbHelper extends SQLiteOpenHelper
    {
        public myDbHelper(Context context) {
            super(context, "iglesia",null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            String query ="CREATE TABLE tipo_iglesias(cod_tipo INTEGER PRIMARY KEY AUTOINCREMENT,descripcion  TEXT NOT NULL);";
            db.execSQL(query);
            query="CREATE TABLE iglesias(cod_iglesia INTEGER PRIMARY KEY AUTOINCREMENT,tipo_iglesia INTEGER ,nombre_iglesia TEXT NOT NULL,direccion TEXT NOT NULL,telefono TEXT NOT NULL,longitud TEXT NOT NULL,latitud TEXT NOT NULL);";
            db.execSQL(query);
            query="CREATE TABLE horario(cod_horario INTEGER PRIMARY KEY AUTOINCREMENT,cod_iglesia INTEGER,cod_tipo INTEGER,dia TEXT,hora_ini TEXT);";
            db.execSQL(query);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            db.execSQL("DROP TABLE IF EXISTS tipo_iglesias");
            db.execSQL("DROP TABLE IF EXISTS iglesias");
            db.execSQL("DROP TABLE IF EXISTS horario");
            onCreate(db);
        }
    }
}
