package cl.duoc.pruebagifty;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
/**
 * Created by byron on 14-11-2017.
 */

public class BD extends SQLiteOpenHelper {
    public BD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i(this.getClass().toString(), "Creando base de datos");

        //Tablas

        db.execSQL( "CREATE TABLE CLIENTES(" +
                " rut TEXT PRIMARY KEY," +
                " nombre TEXT NOT NULL, " +
                " apellidos TEXT NOT NULL," +
                " direccion TEXT NOT NULL," +
                " ciudad TEXT NOT NULL," +
                " comuna TEXT NOT NULL," +
                " latitud TEXT," +
                " longitud TEXT,"+
                " fecha_de_nacimiento TEXT NOT NULL, " +
                " lista_de_regalos_deseados TEXT NOT NULL) " );

        Log.i(this.getClass().toString(), "Tabla CLIENTES creada");

        db.execSQL( "CREATE TABLE CATEGORIAS(" +
                " id TEXT PRIMARY KEY," +
                " categoria TEXT NOT NULL,"+
                " descripcion TEXT NOT NULL)");

        Log.i(this.getClass().toString(), "Tabla CATEGORIAS creada");

        db.execSQL("CREATE TABLE STOCKS(" +
                " id TEXT PRIMARY KEY," +
                " producto TEXT NOT NULL," +
                " descripcion TEXT NOT NULL, " +
                " precio TEXT NOT NULL," +
                " stock TEXT NOT NULL," +
                " categoria TEXT NOT NULL)" /*+*/
                /*" FOREIGN KEY(categoria) REFERENCES CATEGORIAS(id))" */
        );

        Log.i(this.getClass().toString(), "Tabla STOCKS creada");

        db.execSQL( "CREATE TABLE FECHASESPECIALES(" +
                " id TEXT PRIMARY KEY," +
                " fecha TEXT NOT NULL, " +
                " cliente TEXT NOT NULL, " +
                " nombre_evento TEXT NOT NULL," +
                " detalles_adicionales TEXT NOT NULL, " +
                " FOREIGN KEY(cliente) REFERENCES CLIENTES(rut))" );

        Log.i(this.getClass().toString(), "Tabla FECHASESPECIALES creada");

        db.execSQL("INSERT INTO CLIENTES(rut, nombre, apellidos, direccion, ciudad, comuna,latitud,longitud,fecha_de_nacimiento, lista_de_regalos_deseados) VALUES('19062217-7','Byron','Moraga','Cerro Pochoco #01634','Santiago','Puente Alto','1','2','15/04/1995','-Polera -Colonia -Computador')");

        Log.i(this.getClass().toString(), "Datos iniciales CLIENTES insertados");

        db.execSQL("INSERT INTO CATEGORIAS(id, categoria, descripcion) VALUES('1','moda','pantalones, poleras, camisas, ropa interior')");
        db.execSQL("INSERT INTO CATEGORIAS(id, categoria, descripcion) VALUES('1','alimento','lacteos, salsas, congelados, frutas')");
        db.execSQL("INSERT INTO CATEGORIAS(id, categoria, descripcion) VALUES('1','tecnologia','computacion, electrohogar, TV-AUDIO, telefonia')");

        Log.i(this.getClass().toString(), "Datos iniciales CATEGORIAS insertados");

        db.execSQL("INSERT INTO STOCKS(id, producto, descripcion, precio, stock, categoria) VALUES('1','Polera','manga corta y 100% algodon','5990','20','1')");

        Log.i(this.getClass().toString(), "Datos iniciales STOCKS insertados");

        db.execSQL("INSERT INTO FECHASESPECIALES(id, fecha, cliente, nombre_evento, detalles_adicionales) VALUES('1','15/04/2018','19062217-7','cumpleaños','fiesta sorpresa con regalos deseados')");

        Log.i(this.getClass().toString(), "Datos iniciales FECHASESPECIALES insertados");

        Log.i(this.getClass().toString(), "Base de datos creada");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
