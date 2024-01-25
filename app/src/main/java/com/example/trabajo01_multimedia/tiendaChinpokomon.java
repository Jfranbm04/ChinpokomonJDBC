package com.example.trabajo01_multimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.trabajo01_multimedia.R;
import com.example.trabajo01_multimedia.MainActivity;

import java.util.Calendar;

public class tiendaChinpokomon extends AppCompatActivity {

    private TextView textoFecha;
    private Button botonFecha;
    private TextView textoHora;
    private Button botonHora;

    private double precioTotal = 0.0; // Precio total de la compra
    private TextView PrecioTotal; // TextView para mostrar el precio total



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda_chinpokomon);

        textoFecha = findViewById(R.id.txtFecha);
        botonFecha = findViewById(R.id.btDatePicker);

        textoHora = findViewById(R.id.txtHora);
        botonHora = findViewById(R.id.btTimePicker);

        // Referencia precioTotal
        PrecioTotal = findViewById(R.id.precioTotal);

        // Referencias a los CheckBox
        CheckBox checkBoxProducto1 = findViewById(R.id.compra1);
        CheckBox checkBoxProducto2 = findViewById(R.id.compra);
        CheckBox checkBoxProducto3 = findViewById(R.id.compra3);
        CheckBox checkBoxProducto4 = findViewById(R.id.compra2);

        // Listener para cuando se pulsa un checkbox
        checkBoxProducto1.setOnCheckedChangeListener(new CheckBoxListener(0.10)); // Precio de los productos
        checkBoxProducto2.setOnCheckedChangeListener(new CheckBoxListener(1.00));
        checkBoxProducto3.setOnCheckedChangeListener(new CheckBoxListener(5.00));
        checkBoxProducto4.setOnCheckedChangeListener(new CheckBoxListener(1000.00));


    }

    // Clase listener para actualizar precio segun check de los productos
    private class CheckBoxListener implements CheckBox.OnCheckedChangeListener {

        private double precioProducto;

        public CheckBoxListener(double precioProducto) {
            this.precioProducto = precioProducto;
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            // Actualiza el precio total según el estado del CheckBox
            if (isChecked) {
                precioTotal += precioProducto;
            } else {
                precioTotal -= precioProducto;
            }
            PrecioTotal.setText("Precio Total:" + String.format("%.2f", precioTotal)+ " €");


        }
    }




    // Método para el datepicker

    // Método llamado al hacer clic en el botón
    public void mostrarDatePicker(View view) {

        // Obtener fecha
        Calendar calendar = Calendar.getInstance();
        int año = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int día = calendar.get(Calendar.DAY_OF_MONTH);

        // Crear DatePickerDialog y mostrar el cuadro de diálogo
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Actualizar el TextView con la fecha seleccionada
                        textoFecha.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                },
                año, mes, día);

        // Mostrar el DatePickerDialog
        datePickerDialog.show();
    }


    // Método llamado al hacer clic en el botón
    public void mostrarTimePicker(View view) {
        // Obtén la hora actual
        Calendar calendar = Calendar.getInstance();
        int hora = calendar.get(Calendar.HOUR_OF_DAY);
        int minutos = calendar.get(Calendar.MINUTE);

        // Crear TimePickerDialog y mostrar el cuadro de diálogo
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // Actualizar el TextView con la hora seleccionada
                        textoHora.setText(hourOfDay + ":" + minute);
                    }
                },
                hora, minutos, false);

        // Mostrar el TimePickerDialog
        timePickerDialog.show();
    }


    // Toast para la compra
    // Método para enviarResultados
    public void comprar(View view){
        Toast.makeText(getApplicationContext(), "Comprados 10 billetes de avión a Turquía.", Toast.LENGTH_SHORT).show();
    }


    // Método para volver al main
    public void volver (View view){
        Intent anterior = new Intent(this, MainActivity.class);
        startActivity(anterior);

    }



}