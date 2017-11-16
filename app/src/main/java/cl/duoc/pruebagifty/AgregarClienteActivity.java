package cl.duoc.pruebagifty;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AgregarClienteActivity extends Fragment{

    EditText edtRut,edtNombre,edtApellidos,edtDireccion,edtCiudad,edtComuna,edtLatitud,edtLongitud,edtFechaNacimiento,edtListaDeRegalosDeseados;
    Button btnRegistrar;

    public AgregarClienteActivity() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        edtRut = (EditText) getView().findViewById(R.id.edRutAgregarCliente);
        edtNombre = (EditText) getView().findViewById(R.id.edNombreAgregarCliente);
        edtApellidos = (EditText) getView().findViewById(R.id.edApellidosAgregarCliente);
        edtFechaNacimiento = (EditText) getView().findViewById(R.id.dtFechaNacimientoAgregarCliente);
        edtDireccion = (EditText) getView().findViewById(R.id.edDireccionAgregarCliente);
        edtCiudad = (EditText) getView().findViewById(R.id.edCiudadAgregarCliente);
        edtComuna = (EditText) getView().findViewById(R.id.edComunaAgregarCliente);
        edtLatitud = (EditText) getView().findViewById(R.id.edLatitudAgregarCliente);
        edtLongitud = (EditText) getView().findViewById(R.id.edLongitudAgregarCliente);
        edtListaDeRegalosDeseados = (EditText) getView().findViewById(R.id.edListaDedRegalosDeseadosAgregarCliente);

        btnRegistrar = (Button) getView().findViewById(R.id.btnAgregarCliente);
        btnRegistrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                insertar();
            }
        });

        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_agregar_cliente, container, false);
    }

    public void insertar(){

        String rut = edtRut.getText().toString();
        String nombre = edtNombre.getText().toString();
        String apellidos = edtApellidos.getText().toString();
        String direccion = edtDireccion.getText().toString();
        String ciudad = edtCiudad.getText().toString();
        String comuna = edtComuna.getText().toString();
        String latitud = edtLatitud.getText().toString();
        String longitud = edtLongitud.getText().toString();
        String fechaNac = edtFechaNacimiento.getText().toString();
        String listaDeReg = edtListaDeRegalosDeseados.getText().toString();

        if (!rut.trim().equals("") && !nombre.trim().equals("") && !apellidos.trim().equals("") && !direccion.trim().equals("")&&
                !ciudad.trim().equals("")&& !comuna.trim().equals("")&& !latitud.trim().equals("")&& !longitud.trim().equals("")&& !fechaNac.trim().equals("")&& !listaDeReg.trim().equals("")) {

            BD conexion = new BD (getContext(), "BD_Gifty",null,1);

            SQLiteDatabase database = conexion.getWritableDatabase();

            if (database != null) {
                ContentValues parametros = new ContentValues();
                parametros.put("rut", rut.trim());
                parametros.put("nombre", nombre.trim());
                parametros.put("apellidos", apellidos.trim());
                parametros.put("direccion", direccion.trim());
                parametros.put("ciudad", ciudad.trim());
                parametros.put("comuna", comuna.trim());
                parametros.put("latitud", latitud.trim());
                parametros.put("longitud", longitud.trim());
                parametros.put("fecha_de_nacimiento", fechaNac.trim());
                parametros.put("lista_de_regalos_deseados", listaDeReg.trim());
                long i = database.insert("CLIENTES", null, parametros);
                if (i > 0){
                    limpiar();
                    Toast.makeText(getContext(), "Cliente Insertado", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                database.close();
            }
        }else
            Toast.makeText(getContext(),"no se permiten campos vacios",Toast.LENGTH_SHORT).show();

    }

    public void limpiar(){
        edtRut.setText("");
        edtNombre.setText("");
        edtApellidos.setText("");
        edtDireccion.setText("");
        edtCiudad.setText("");
        edtComuna.setText("");
        edtLatitud.setText("");
        edtLongitud.setText("");
        edtFechaNacimiento.setText("");
        edtListaDeRegalosDeseados.setText("");
    }
}
