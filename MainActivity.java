package com.example.kellyjohanazapataestrada.forcegym;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentContainer;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends NavigationDraActivity
{
    /*
    //**********************************************
    //Creamos la base de datos
    ProductosP productosP;
    //MisFavoritos misFavoritos;
    SQLiteDatabase dbProductos;
    SQLiteDatabase dbMisfavoritos;
    ContentValues dataBD1;
    ContentValues dataBD2;
    //*************************************************
    String user,pass,email;
    String idUser;
    static int count =0;
    String promo;
    private String[] opciones = new String[] {"Mi Perfil","Menu", "Healthy","Promociones", "Favoritos"};
    private DrawerLayout drawerLayout;
    private ListView listView;
    private ActionBarDrawerToggle drawerToggle;
    ListView list;
    SharedPreferences preferencias;
    String preferencia1,preferencia2,preferencia3,cerrar;

    Productos[] productos= new Productos[100];

    /*
    private Productos[] productos=
            new Productos[]{
                    new Productos("Sanduche AM","Huevo frito sobre tostadas de pan rellenas " +
                            "con jamón",21300,R.drawable.desayuno),
                    new Productos("Parmessano Omega","Crema de tomate y quinoa parmessano",25900,R.drawable.omega),
                    new Productos("Dulces Pancakes!","Pancakes tradicionales servidos con miel maple y queso",14900,R.drawable.pancake),
                    new Productos("Tabla Parmessano","Queso provolone, queso camembert",17300,R.drawable.tabla),
                    new Productos("ACAI BOWL","Mezcla leche de almendras, cubierto con kiwi",13900,R.drawable.bowl),
                    new Productos("Postre de reina","Postre de Reina una receta auténtica de nuestra abuela Lía",8900,R.drawable.reina),
                    new Productos("Coctel sin alcohol","Un coctel sin licor para una tarde refrescante ",8900,R.drawable.coctel)
            };*/

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //Base de datos
        productosP = new ProductosP(this,"dbProductos",null,1);
        dbProductos = productosP.getWritableDatabase();

        //misFavoritos = new MisFavoritos(this,"dbMisfavoritos",null,1);
        //dbMisfavoritos = misFavoritos.getWritableDatabase();
        //*********************************************
        if(count==0)
        {
            //Insertar prductos
            InsertarProducto("Sanduche AM","Huevo frito sobre tostadas de pan rellenas con jamón ","21300");
            InsertarProducto("Parmessano Omega","Crema de tomate y quinoa parmessano","25900");
            InsertarProducto("Dulces Pancakes!","Pancakes tradicionales servidos con miel maple y queso","14900");
            InsertarProducto("Tabla Parmessano","Queso provolone, queso camembert","17300");
            InsertarProducto("ACAI BOWL","Mezcla leche de almendras, cubierto con kiwi","13900");
            InsertarProducto("Postre de reina","Postre de Reina una receta auténtica de nuestra abuela Lía","8900");
            InsertarProducto("Coctel sin alcohol","Un coctel sin licor para una tarde refrescante ","8900");
            count =1;
        }

        Cursor c = dbProductos.rawQuery("SELECT * FROM PRODUCTOS WHERE idProducto = '"+1+"'",null);
        Cursor c2 = dbProductos.rawQuery("SELECT * FROM PRODUCTOS WHERE idProducto = '"+2+"'",null);
        Cursor c3 = dbProductos.rawQuery("SELECT * FROM PRODUCTOS WHERE idProducto = '"+3+"'",null);
        Cursor c4 = dbProductos.rawQuery("SELECT * FROM PRODUCTOS WHERE idProducto = '"+4+"'",null);
        Cursor c5 = dbProductos.rawQuery("SELECT * FROM PRODUCTOS WHERE idProducto = '"+5+"'",null);
        Cursor c6 = dbProductos.rawQuery("SELECT * FROM PRODUCTOS WHERE idProducto = '"+6+"'",null);
        Cursor c7 = dbProductos.rawQuery("SELECT * FROM PRODUCTOS WHERE idProducto = '"+7+"'",null);

        if(c.moveToFirst() && c2.moveToFirst()&& c3.moveToFirst()&& c4.moveToFirst()&& c5.moveToFirst()&& c6.moveToFirst()&& c7.moveToFirst())
        {
            productos=
                    new Productos[]{
                            new Productos(c.getString(1),c.getString(2),Integer.parseInt(c.getString(3)),R.drawable.desayuno),
                            new Productos(c2.getString(1),c2.getString(2),Integer.parseInt(c2.getString(3)),R.drawable.omega),
                            new Productos(c3.getString(1),c3.getString(2),Integer.parseInt(c3.getString(3)),R.drawable.pancake),
                            new Productos(c4.getString(1),c4.getString(2),Integer.parseInt(c4.getString(3)),R.drawable.tabla),
                            new Productos(c5.getString(1),c5.getString(2),Integer.parseInt(c5.getString(3)),R.drawable.bowl),
                            new Productos(c6.getString(1),c6.getString(2),Integer.parseInt(c6.getString(3)),R.drawable.reina),
                            new Productos(c7.getString(1),c7.getString(2),Integer.parseInt(c7.getString(3)),R.drawable.coctel)
                    };
        }

        //***********************************************
        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.contenedorFrame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_main, contentFrameLayout);

        Intent intent = new Intent();
        setResult(RESULT_OK, intent);

        user = getIntent().getExtras().getString("user");
        pass = getIntent().getExtras().getString("pass");
        email = getIntent().getExtras().getString("idUser");
        promo = getIntent().getExtras().getString("promo");

        preferencias = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        preferencia1=preferencias.getString("user","");
        preferencia2=preferencias.getString("pass","");
        preferencia3=preferencias.getString("email","");
        cerrar=preferencias.getString("cerrar","");

        drawerLayout = (DrawerLayout) findViewById(R.id.contenedorPrincipal);

        //Para el menu de la izquierda (Navigation)
        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(new ArrayAdapter<String>(getSupportActionBar().getThemedContext(),
                android.R.layout.simple_list_item_1, opciones));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case(0):
                        Intent intent= new Intent(getApplicationContext(),PerfilActivity.class);
                        intent.putExtra("user", user);
                        intent.putExtra("pass", pass);
                        intent.putExtra("email", email);
                        intent.putExtra("promo", promo);
                        intent.putExtra("idUser", idUser);
                        startActivity(intent);
                        finish();
                        break;
                    case(1):
                        Intent intento1=new Intent(MainActivity.this,MenuActivity.class);
                        intento1.putExtra("user", user);
                        intento1.putExtra("pass", pass);
                        intento1.putExtra("email", email);
                        intento1.putExtra("promo", promo);
                        intento1.putExtra("idUser", idUser);
                        startActivity(intento1);
                        finish();
                        break;
                    case(2):
                        Intent intento=new Intent(MainActivity.this,HealthyActivity.class);
                        intento.putExtra("user", user);
                        intento.putExtra("pass", pass);
                        intento.putExtra("email", email);
                        intento.putExtra("promo", promo);
                        intento.putExtra("idUser", idUser);
                        startActivity(intento);
                        finish();
                        break;
                    case(3):
                        Intent intento4=new Intent(MainActivity.this,FavoritosActivity.class);
                        intento4.putExtra("user", user);
                        intento4.putExtra("pass", pass);
                        intento4.putExtra("email", email);
                        intento4.putExtra("promo", promo);
                        startActivity(intento4);
                        finish();
                        break;
                }
                listView.setItemChecked(i,true);
                drawerLayout.closeDrawer(listView);
            }
        });

        //Para la lista de promociones
        Adapter adaptador=new Adapter(this, productos);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adaptador);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case(0):
                        promo= "primero";
                        Intent intent=new Intent(MainActivity.this,PromocionesActivity.class);
                        intent.putExtra("user", user);
                        intent.putExtra("pass", pass);
                        intent.putExtra("email", email);
                        intent.putExtra("promo", promo);
                        intent.putExtra("idUser", idUser);
                        InsertarFavorito(user,"1");
                        startActivity(intent);
                        finish();
                        break;
                    case(1):
                        promo= "segundo";
                        Intent intento1=new Intent(MainActivity.this,PromocionesActivity.class);
                        intento1.putExtra("user", user);
                        intento1.putExtra("pass", pass);
                        intento1.putExtra("email", email);
                        intento1.putExtra("promo", promo);
                        intento1.putExtra("idUser", idUser);
                        InsertarFavorito(user,"2");
                        startActivity(intento1);
                        finish();
                        break;
                    case(2):
                        promo= "tercero";
                        Intent intento=new Intent(MainActivity.this,PromocionesActivity.class);
                        intento.putExtra("user", user);
                        intento.putExtra("pass", pass);
                        intento.putExtra("email", email);
                        intento.putExtra("promo", promo);
                        intento.putExtra("idUser", idUser);
                        InsertarFavorito(user,"3");
                        startActivity(intento);
                        finish();
                        break;
                    case(3):
                        promo= "cuarto";
                        Intent intento2=new Intent(MainActivity.this,PromocionesActivity.class);
                        intento2.putExtra("user", user);
                        intento2.putExtra("pass", pass);
                        intento2.putExtra("email", email);
                        intento2.putExtra("promo", promo);
                        intento2.putExtra("idUser", idUser);
                        InsertarFavorito(user,"4");
                        startActivity(intento2);
                        finish();
                        break;
                    case (4):
                        promo= "quinto";
                        Intent intento3=new Intent(MainActivity.this,PromocionesActivity.class);
                        intento3.putExtra("user", user);
                        intento3.putExtra("pass", pass);
                        intento3.putExtra("email", email);
                        intento3.putExtra("promo", promo);
                        intento3.putExtra("idUser", idUser);
                        InsertarFavorito(user,"5");
                        startActivity(intento3);
                        finish();
                        break;
                    case (5):
                        promo= "sexto";
                        Intent intento4=new Intent(MainActivity.this,PromocionesActivity.class);
                        intento4.putExtra("user", user);
                        intento4.putExtra("pass", pass);
                        intento4.putExtra("email", email);
                        intento4.putExtra("promo", promo);
                        intento4.putExtra("idUser", idUser);
                        InsertarFavorito(user,"6");
                        startActivity(intento4);
                        finish();
                        break;
                    case (7):
                        promo= "sexto";
                        Intent intento6=new Intent(MainActivity.this,PromocionesActivity.class);
                        intento6.putExtra("user", user);
                        intento6.putExtra("pass", pass);
                        intento6.putExtra("email", email);
                        intento6.putExtra("promo", promo);
                        intento6.putExtra("idUser", idUser);
                        InsertarFavorito(user,"7");
                        startActivity(intento6);
                        finish();
                        break;
                    case (6):
                        promo= "septimo";
                        Intent intento5=new Intent(MainActivity.this,PromocionesActivity.class);
                        intento5.putExtra("user", user);
                        intento5.putExtra("pass", pass);
                        intento5.putExtra("email", email);
                        intento5.putExtra("promo", promo);
                        intento5.putExtra("idUser", idUser);
                        InsertarFavorito(user,"8");
                        startActivity(intento5);
                        finish();
                        break;
                }
                listView.setItemChecked(i,true);
                drawerLayout.closeDrawer(Gravity.LEFT);
            }
        });

/*
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.abierto, R.string.cerrado){
            /** Called when a drawer has settled in a completely closed state. */
   /*         public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
    /*        public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

        };

        drawerLayout.setDrawerListener(drawerToggle);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.contenedorPrincipal);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case android.R.id.home:
                drawerLayout.openDrawer(Gravity.LEFT);
                return true;
            case R.id.mCerrar:
                SharedPreferences preferencias= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor= preferencias.edit();
                editor.putString("cerrar", "si");
                editor.commit();
                Intent intento2=new Intent(this,PerfilActivity.class);
                intento2.putExtra("user", user);
                intento2.putExtra("pass", pass);
                intento2.putExtra("email", email);
                intento2.putExtra("idUser", idUser);
                startActivity(intento2);
                finish();
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    class Adapter extends ArrayAdapter<Productos>
    {
        public Adapter(Context context, Productos[] productos)
        {
            super(context, R.layout.layout_item1, productos);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.layout_item1, null);

            ImageView imagen = (ImageView) item.findViewById(R.id.imagen1);
            imagen.setImageResource(productos[position].getImageId());

            TextView tPrecio = (TextView) item.findViewById(R.id.precio1);
            tPrecio.setText(String.valueOf(productos[position].getPrecioP()));

            TextView tNombre = (TextView) item.findViewById(R.id.nombre1);
            tNombre.setText(productos[position].getNombreP());

            TextView tDescripcion = (TextView) item.findViewById(R.id.descripcion1);
            tDescripcion.setText(productos[position].getDesP());

            return (item);
        }
    }

    public void InsertarProducto(String Titulo, String Descripcion, String Precio)
    {
        dataBD1 = new ContentValues();
        dataBD1.put("producto",Titulo);
        dataBD1.put("descripcion",Descripcion);
        dataBD1.put("precio",Precio);

        //dbProductos.insert("productosP",null,dataBD1);
        dbProductos.execSQL("INSERT INTO PRODUCTOS VALUES(null, '"+Titulo+"','"+Descripcion+"','"+Precio+"')");
        //****************************************
    }

    public void InsertarFavorito(String idUsuario, String idProducto)
    {
        dataBD2 = new ContentValues();
        dataBD2.put("idUsuario",idUsuario);
        dataBD2.put("idProducto",idProducto);

        //dbMisfavoritos.insert("productosP",null,dataBD1);
        dbMisfavoritos.execSQL("INSERT INTO MISFAVORITOS VALUES(null, '"+idUsuario+"','"+idProducto+"')");
        //****************************************
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1234 && resultCode == RESULT_OK)
        {
            user = data.getExtras().getString("user");
            pass = data.getExtras().getString("pass");
            email = data.getExtras().getString("email");
            Log.d("user", user);
            Log.d("pass", pass);
            Log.d("email",email);
            SharedPreferences preferencias=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor= preferencias.edit();
            editor.putString("user",  user);
            editor.putString("pass", pass);
            editor.putString("email", email);
            editor.putString("cerrar","no");
            editor.commit();
            preferencias = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            preferencia1=preferencias.getString("user","");
            preferencia2=preferencias.getString("pass","");
            preferencia3=preferencias.getString("email","");
            cerrar=preferencias.getString("cerrar","");

        }
        if (requestCode == 1234 && resultCode == RESULT_CANCELED) {
            Log.d("mensaje", "no se cargaron datos");
            Toast.makeText(this, "No se cargaron los datos",Toast.LENGTH_SHORT).show();
        }
    }
*/

}