package com.example.kellyjohanazapataestrada.forcegym;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class NavigationDraActivity extends AppCompatActivity {

    private String[] opciones = new String[] {"Mi Perfil","Menu", "Healthy","Promociones", "Favoritos"};
    private DrawerLayout drawerLayout;
    private ListView listView;
    private ActionBarDrawerToggle drawerToggle;
    String user,pass,email;
    String promo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_dra);

        Intent intent = new Intent();
        setResult(RESULT_OK, intent);

        user = getIntent().getExtras().getString("user");
        pass = getIntent().getExtras().getString("pass");
        email = getIntent().getExtras().getString("email");
        promo = getIntent().getExtras().getString("promo");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        drawerLayout = (DrawerLayout) findViewById(R.id.contenedorPrincipal);
        listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(new ArrayAdapter<String>(getSupportActionBar().getThemedContext(),
                android.R.layout.simple_list_item_1, opciones));

        /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case(0):
                        Intent intent=new Intent(NavigationDraActivity.this,PerfilActivity.class);
                        intent.putExtra("usser", user);
                        intent.putExtra("pass", pass);
                        intent.putExtra("email", email);
                        intent.putExtra("promo", promo);
                        startActivity(intent);
                        finish();
                        break;
                    case(1):
                        Intent intento1=new Intent(NavigationDraActivity.this,MenuActivity.class);
                        intento1.putExtra("user", user);
                        intento1.putExtra("pass", pass);
                        intento1.putExtra("email", email);
                        intento1.putExtra("promo", promo);
                        startActivity(intento1);
                        finish();
                        break;
                    case(2):
                        Intent intento=new Intent(NavigationDraActivity.this,HealthyActivity.class);
                        intento.putExtra("user", user);
                        intento.putExtra("pass", pass);
                        intento.putExtra("email", email);
                        intento.putExtra("promo", promo);
                        startActivity(intento);
                        finish();
                        break;
                    case(3):
                        Intent intento4=new Intent(NavigationDraActivity.this,FavoritosActivity.class);
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
        */
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.abierto, R.string.cerrado);

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
            case R.id.mCerrar:
                SharedPreferences preferencias= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor= preferencias.edit();
                editor.putString("cerrar", "si");
                editor.commit();
                Intent intento3=new Intent(this,LoginActivity.class);
                intento3.putExtra("user", user);
                intento3.putExtra("pass", pass);
                intento3.putExtra("email", email);
                startActivity(intento3);
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}
