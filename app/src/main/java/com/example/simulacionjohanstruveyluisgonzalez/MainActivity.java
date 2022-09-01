package com.example.simulacionjohanstruveyluisgonzalez;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    Button btn_fecha_inicio, btn_hora_inicio,
            btn_fecha_fin,  btn_hora_fin, btn_iniciarr;


    EditText fecha_inicio, hora_inicio,
             fecha_fin,  hora_fin;

    private int dia_inicio,mes_inicio,ano_inicio,horas_inicio,minutos_inicio,
                dia_fin,mes_fin,ano_fin,horas_fin,minutos_fin;

    private ListView mListView;
    private List<String> mlista = new ArrayList<>();
    private ArrayAdapter<String> mAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_fecha_inicio = (Button) findViewById(R.id.btn_fecha_inicio);
        btn_fecha_fin= (Button) findViewById(R.id.btn_fecha_fin);

        btn_hora_inicio = (Button) findViewById(R.id.btn_hora_inicio);
        btn_hora_fin = (Button) findViewById(R.id.btn_hora_fin);

        fecha_inicio = (EditText) findViewById(R.id.fecha_inicio);
        fecha_fin= (EditText) findViewById(R.id.fecha_fin);

        hora_inicio = (EditText) findViewById(R.id.hora_inicio);
        hora_fin = (EditText) findViewById(R.id.hora_fin);


        btn_fecha_inicio.setOnClickListener(this);
        btn_fecha_fin.setOnClickListener(this);

        btn_hora_inicio.setOnClickListener(this);
        btn_hora_fin.setOnClickListener(this);

        btn_iniciarr = findViewById(R.id.btn_iniciar);
        btn_iniciarr.setOnClickListener(this);
        mListView = findViewById(R.id.listView_j);
        mListView.setOnItemClickListener(this);


    }


    @Override
    public void onClick(View v) {

        if(v==btn_fecha_inicio){

            final Calendar c = Calendar.getInstance();
            dia_inicio = c.get(Calendar.DAY_OF_MONTH);
            mes_inicio = c.get(Calendar.MONTH);
            ano_inicio = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    fecha_inicio.setText(dayOfMonth+"/"+ (month+1)+"/"+year);

                }
            },dia_inicio,mes_inicio,ano_inicio);
            datePickerDialog.show();


        }

        if(v==btn_fecha_fin){

            final Calendar c = Calendar.getInstance();
            dia_fin = c.get(Calendar.DAY_OF_MONTH);
            mes_fin = c.get(Calendar.MONTH);
            ano_fin = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    fecha_fin.setText(dayOfMonth+"/"+ (month+1)+"/"+year);

                }
            },dia_fin,mes_fin,ano_fin);
            datePickerDialog.show();
        }

        if(v==btn_hora_inicio){

            final Calendar c = Calendar.getInstance();
            horas_inicio = c.get(Calendar.HOUR_OF_DAY);
            minutos_inicio = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    hora_inicio.setText(hourOfDay+":"+minute);

                }
            },horas_inicio,minutos_inicio,false);
            timePickerDialog.show();

        }
        if(v==btn_hora_fin){


            final Calendar c = Calendar.getInstance();
            horas_fin = c.get(Calendar.HOUR_OF_DAY);
            minutos_fin = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    hora_fin.setText(hourOfDay+":"+minute);

                }
            },horas_fin,minutos_fin,false);
            timePickerDialog.show();
        }


        switch (v.getId()){

            case R.id.btn_iniciar:
                //aqui sucede la magia
                //mlista.add("bebe");
                if(fecha_inicio.getText().toString().isEmpty()|| fecha_fin.getText().toString().isEmpty()
                        || hora_inicio.getText().toString().isEmpty() || hora_fin.getText().toString().isEmpty() ){

                    Toast.makeText(this,"Ingresa los datos ", Toast.LENGTH_SHORT).show();
                }

                else{


                    //SimulacionEmpieza(dia_inicio,mes_inicio,ano_inicio,horas_inicio,minutos_inicio,
                   // dia_fin,mes_fin,ano_fin,horas_fin,horas_inicio);


                    //SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat formateador2 = new SimpleDateFormat("HH:mm");
                    SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");

                    try
                    {

                        Date fechai = formateador.parse(String.valueOf(fecha_inicio.getText()));
                        Date fechaf = formateador.parse(String.valueOf(fecha_fin.getText()));

                        Date horai = formateador2.parse(String.valueOf(hora_inicio.getText()));
                        Date horaf = formateador2.parse(String.valueOf(hora_fin.getText()));
                        Date comparar = formateador2.parse("24:00");
                        Date cero = formateador2.parse("00:00");

                        mlista.clear();

                        SimulacionEmpieza(fechai,fechaf,horai,horaf,comparar,cero);



                    }
                    catch (ParseException e)
                    {
                        mlista.add("error");
                        // Error, la cadena de texto no se puede convertir en fecha.
                    }





            mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,mlista);
            mListView.setAdapter(mAdapter);

                     }
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        Toast.makeText(this,"tocaste: "+ position, Toast.LENGTH_SHORT).show();
    }


    public void SimulacionEmpieza(Date fechai,Date fechaf, Date horai, Date horaf,Date compara , Date cero){


        Date fromDate = fechai,
                toDate = fechaf, hora;



        Calendar calendar = Calendar.getInstance();
        Calendar hora_calendar = Calendar.getInstance();

        calendar.setTime(fromDate);
        hora_calendar.setTime(horai);


        while (!fromDate.after(toDate)){




            hora_calendar.get(Calendar.HOUR_OF_DAY);
            hora = hora_calendar.getTime();

            mlista.add("");
            mlista.add("----Dia: " + calendar.get(Calendar.DAY_OF_MONTH) + "/"+
                    (calendar.get(Calendar.MONTH) + 1 )+ "/"+ calendar.get(Calendar.YEAR) + "----");

            while (!hora.after(compara)){


                Double norteSur = obtenerDesidadYTiempo(
                        calendar.get(Calendar.DAY_OF_WEEK),
                        hora_calendar.get(Calendar.HOUR_OF_DAY),
                        "norte-sur");




                //obtenemos variables del canal sur-norte

                Double surNorte  = obtenerDesidadYTiempo(
                        calendar.get(Calendar.DAY_OF_WEEK),
                        hora_calendar.get(Calendar.HOUR_OF_DAY),
                        "sur-norte");







                if(fromDate.equals(toDate)){if(hora.after(horaf)){

                    hora_calendar.setTime(horaf);
                    mlista.add("----Hora: " + hora_calendar.get(Calendar.HOUR_OF_DAY) + ":" +
                            hora_calendar.get(Calendar.MINUTE) + " ----");

                    mlista.add(  "Flujo Norte-Sur: " + norteSur );
                    mlista.add(  "Flujo Sur-Norte: " + surNorte);

                    break;}}


                    mlista.add("----Hora: " + hora_calendar.get(Calendar.HOUR_OF_DAY) + ":" +
                            hora_calendar.get(Calendar.MINUTE) + " ----");

                          mlista.add(  "Flujo Norte-Sur: " + norteSur );
                          mlista.add(  "Flujo Sur-Norte: " + surNorte);

                    hora_calendar.add(Calendar.HOUR_OF_DAY, 1);
                    hora = hora_calendar.getTime();


           }



            hora_calendar.setTime(cero);
             // Configuramos la fecha que se recibe
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            fromDate = calendar.getTime();



    }


        return;
    }


   public Double obtenerDesidadYTiempo(int day, int hour, String via ){

        Double lonigtudDelTramo = 12.00;

       if (via == "norte-sur") {
           switch (day) {
               case 1: // lunes
               case 2: // martes
               case 3: //miercoles
               case 4: // jueves
               case 5: // viernes
                   if ((6 <= hour) && (hour <= 9)) {

                       Double velocidad = lonigtudDelTramo / 18;
                       Double flujonorte =velocidad * 119;

                       return flujonorte;


                   } else if ((11 <= hour) && (hour <= 13)) {

                       Double velocidad = lonigtudDelTramo / 18;
                       Double flujonorte =velocidad * 105;

                       return flujonorte;

                   } else if ((17 <= hour) && (hour <= 19)) {

                       Double velocidad = lonigtudDelTramo / 18;
                       Double flujonorte =velocidad * 120;

                       return flujonorte;

                   } else {
                       Double velocidad = lonigtudDelTramo / 18;
                       Double flujonorte =velocidad * 0;

                       return flujonorte;

                   }

               case 6: // sabado
               case 7: // domingo
                   if ((13 <= hour) && (hour <= 15)) {

                       Double velocidad = lonigtudDelTramo / 8;
                       Double flujonorte =velocidad * 107;

                       return flujonorte;

                   } else if ((18 <= hour) && (hour <= 20)) {

                       Double velocidad = lonigtudDelTramo / 8;
                       Double flujonorte =velocidad * 80;

                       return flujonorte;

                   } else  {

                       Double velocidad = lonigtudDelTramo / 8;
                       Double flujonorte =velocidad * 0;

                       return flujonorte;

                   }

               default:
                   break;
           }
       } else {
           switch (day) {
               case 1: // lunes
               case 2: // martes
               case 3: //miercoles
               case 4: // jueves
               case 5: // viernes
                   if ((6 <= hour) && (hour <= 9)) {

                       Double velocidad = lonigtudDelTramo / 6;
                       Double flujonorte =velocidad * 117;

                       return flujonorte;

                   } else if ((11 <= hour) && (hour <= 13)) {

                       Double velocidad = lonigtudDelTramo / 6;
                       Double flujonorte =velocidad * 98;

                       return flujonorte;

                   } else if ((17 <= hour) && (hour <= 21)) {

                       Double velocidad = lonigtudDelTramo / 6;
                       Double flujonorte =velocidad * 76;

                       return flujonorte;

                   } else {

                       Double velocidad = lonigtudDelTramo / 6;
                       Double flujonorte =velocidad * 0;

                       return flujonorte;

                   }


               case 6: // sabado
               case 7: // domingo
                   if ((7 <= hour) && (hour <= 10)) {
                       // const velocidad = lonigtudDelTramo / 0
                       return 0.0;
                   } else if ((17 <= hour) && (hour <= 22)) {
                       // const velocidad = lonigtudDelTramo / 0
                       return 0.0;
                   } else  {
                       // const velocidad = lonigtudDelTramo / 0
                       return 0.0;
                   }

               default:
                   break;
           }
       }





       return 0.00;

    }



}