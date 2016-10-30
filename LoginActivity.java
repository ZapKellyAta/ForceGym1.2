package com.example.kellyjohanazapataestrada.forcegym;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    EditText User,Pass;
    Button bAceptar,bRegistro;
    String user,pass,email,user2,pass2,email2,estatura,peso;
    String idUser;
    static int bandera=0;
    SharedPreferences preferencias;
    String preferencia1,preferencia2,preferencia3,cerrar,preferencia4,preferencia5,preferencia6;
    ProgressDialog PD;

    //**********************************************
    //Creamos la base de datos
    Usuarios users;
    SQLiteDatabase dbUsers;
    ContentValues dataBD;
    private String FIREBASE_URL = "https://forcegym-b010f.firebaseio.com/";
    private Firebase firebasedatos;
    //*************************************************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        User =  (EditText)findViewById(R.id.txtLogin);
        Pass = (EditText)findViewById(R.id.txtPass);
        bAceptar=(Button)findViewById(R.id.btnIngresar);
        bRegistro=(Button)findViewById(R.id.btnRegistro);
        //****************************************
        //Base de datos
        Firebase.setAndroidContext(this);
        firebasedatos =  new Firebase (FIREBASE_URL);
        users = new Usuarios(this, "dbUsers", null, 1);
        dbUsers = users.getWritableDatabase();
        //****************************************

        if(bandera==1)
        {
            user = getIntent().getExtras().getString("user");
            pass = getIntent().getExtras().getString("pass");
            email = getIntent().getExtras().getString("email");
            estatura = getIntent().getExtras().getString("estatura");
            peso = getIntent().getExtras().getString("peso");
            idUser = getIntent().getExtras().getString("idUser");
            //Toast.makeText(LoginActivity.this, "IdUser" + " " + idUser, Toast.LENGTH_SHORT).show();
        }

        preferencias = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        preferencia1=preferencias.getString("user","");
        preferencia2=preferencias.getString("pass","");
        preferencia3=preferencias.getString("email","");
        preferencia4=preferencias.getString("estatura","");
        preferencia5=preferencias.getString("peso","");
        preferencia6=preferencias.getString("idUser","");
        cerrar=preferencias.getString("cerrar","");

        if(preferencia1.length()==0 && cerrar=="no")
        {
            bAceptar.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View view)
                {
                    String Login = User.getText().toString();
                    String Passw = Pass.getText().toString();

                    if (TextUtils.isEmpty(Login)) {
                        User.setError("Ingrese Login");
                        Toast.makeText(LoginActivity.this, "Ingrese Login", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (TextUtils.isEmpty(Passw)) {
                        Pass.setError("Ingrese Password");
                        Toast.makeText(LoginActivity.this, "Ingrese Password", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else
                    {
                        final String id = "Usuario" + idUser;
                        firebasedatos.addValueEventListener(new ValueEventListener()
                        {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot)
                            {
                                if(dataSnapshot.child(idUser).exists());
                                {
                                    final String name = User.getText().toString();
                                    final String pass = Pass.getText().toString();
                                    user2 = dataSnapshot.child("Usuario"+idUser).child(name).toString();
                                    Intent intento = new Intent(getApplicationContext(), MainActivity.class);
                                    intento.putExtra("user", user2);
                                    intento.putExtra("pass", pass);
                                    intento.putExtra("email", email);
                                    intento.putExtra("idUser", idUser);
                                    intento.putExtra("estatura", estatura);
                                    intento.putExtra("peso", peso);
                                    intento.putExtra("idUser", idUser);
                                    startActivityForResult(intento, 1234);
                                    Toast.makeText(LoginActivity.this, "Bienvenido" + " " + user2, Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }
                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });
                        /*
                        Cursor c = dbUsers.rawQuery("select * FROM USUARIOS WHERE usuario = '" + Login + "' AND contrasena ='" + Passw + "'", null);
                        if (c.getCount() == 0)
                        {
                            Toast.makeText(getApplicationContext(), "Usuario no registrado", Toast.LENGTH_LONG).show();
                        }
                        else if (c.moveToFirst())
                        {
                            idUser = c.getInt(0);
                            user2 = c.getString(1);
                            pass2 = c.getString(2);
                            email2 = c.getString(4);
                            estatura = c.getString(5);
                            peso = c.getString(6);

                            Intent intento = new Intent(getApplicationContext(), MainActivity.class);
                            intento.putExtra("user", user2);
                            intento.putExtra("pass", pass);
                            intento.putExtra("email", email);
                            intento.putExtra("idUser", idUser);
                            intento.putExtra("estatura", estatura);
                            intento.putExtra("peso", peso);
                            startActivityForResult(intento, 1234);
                            Toast.makeText(LoginActivity.this, "Bienvenido" + " " + user2, Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, "Usuario o contrasena incorrectos", Toast.LENGTH_SHORT).show();
                        }*/
                    }
                }
            });

            bRegistro.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View view)
                {
                    Intent intento = new Intent(getApplicationContext(), RegistroActivity.class);
                    startActivityForResult(intento, 1234);
                }
            });

        }
        else if(preferencia1.length()!=0 && cerrar=="no")
        {
            Intent intento2=new Intent(this,MainActivity.class);
            intento2.putExtra("user", user);
            intento2.putExtra("pass", pass);
            intento2.putExtra("email", email);
            intento2.putExtra("estatura", estatura);
            intento2.putExtra("peso", peso);
            intento2.putExtra("idUser", idUser);
            startActivity(intento2);
            finish();
        }
        else if(preferencia1.length()!=0 && cerrar=="si")
        {
            bAceptar.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View view)
                {
                    String Login = User.getText().toString();
                    String Passw = Pass.getText().toString();

                    if (TextUtils.isEmpty(Login)) {
                        User.setError("Ingrese Login");
                        Toast.makeText(LoginActivity.this, "Ingrese Login", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (TextUtils.isEmpty(Passw)) {
                        Pass.setError("Ingrese Password");
                        Toast.makeText(LoginActivity.this, "Ingrese Password", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else
                    {
                        firebasedatos.addValueEventListener(new ValueEventListener()
                        {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot)
                            {
                                final String name = User.getText().toString();
                                final String pass = Pass.getText().toString();
                                if(dataSnapshot.child("Usuario"+idUser).exists());
                                {
                                    user2 = dataSnapshot.child("Usuario"+idUser).child(name).toString();
                                    Intent intento = new Intent(getApplicationContext(), MainActivity.class);
                                    intento.putExtra("user", user2);
                                    intento.putExtra("pass", pass);
                                    intento.putExtra("email", email);
                                    intento.putExtra("idUser", idUser);
                                    intento.putExtra("estatura", estatura);
                                    intento.putExtra("peso", peso);
                                    intento.putExtra("idUser", idUser);
                                    startActivityForResult(intento, 1234);
                                    Toast.makeText(LoginActivity.this, "Bienvenido" + " " + user2, Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }
                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });
                        /*
                        Cursor c = dbUsers.rawQuery("select * FROM USUARIOS WHERE usuario = '" + Login + "' AND contrasena ='" + Passw + "'", null);
                        if (c.getCount() == 0) {
                            Toast.makeText(getApplicationContext(), "Usuario no registrado", Toast.LENGTH_LONG).show();
                        } else if (c.moveToFirst()) {
                            idUser = c.getInt(0);
                            user2 = c.getString(1);
                            pass2 = c.getString(2);
                            email2 = c.getString(4);
                            estatura = c.getString(5);
                            peso = c.getString(6);

                            Intent intento = new Intent(getApplicationContext(), MainActivity.class);
                            intento.putExtra("user", user2);
                            intento.putExtra("pass", pass);
                            intento.putExtra("email", email);
                            intento.putExtra("idUser", idUser);
                            intento.putExtra("estatura", estatura);
                            intento.putExtra("peso", peso);
                            startActivityForResult(intento, 1234);
                            Toast.makeText(LoginActivity.this, "Bienvenido" + " " + user2, Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Usuario o contrasena incorrectos", Toast.LENGTH_SHORT).show();
                        }*/
                    }
                }
            });

            bRegistro.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View view) {
                    Intent intento = new Intent(getApplicationContext(), RegistroActivity.class);
                    startActivityForResult(intento, 1234);
                }
            });
        }
        else
        {
            bAceptar.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View view)
                {
                    String Login = User.getText().toString();
                    String Passw = Pass.getText().toString();

                    if (TextUtils.isEmpty(Login)) {
                        User.setError("Ingrese Login");
                        Toast.makeText(LoginActivity.this, "Ingrese Login", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (TextUtils.isEmpty(Passw)) {
                        Pass.setError("Ingrese Password");
                        Toast.makeText(LoginActivity.this, "Ingrese Password", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else
                    {
                        firebasedatos.addValueEventListener(new ValueEventListener()
                        {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot)
                            {
                                final String name = User.getText().toString();
                                final String pass = Pass.getText().toString();

                                /*
                                if (name.equals(dataSnapshot.child("Usuario").child(name).child("nombre").getValue())
                                        && pass.equals(dataSnapshot.child("Usuario").child(name).child("pass").getValue()))
                                {
                                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(), "Incorrect Credentials", Toast.LENGTH_SHORT).show();
                                }*/
                                //PD.dismiss();
                                if(dataSnapshot.child("Usuario"+idUser).exists());
                                {
                                    user2 = dataSnapshot.child("Usuario"+idUser).child(name).toString();
                                    Intent intento = new Intent(getApplicationContext(), MainActivity.class);
                                    intento.putExtra("user", user2);
                                    intento.putExtra("pass", pass);
                                    intento.putExtra("email", email);
                                    intento.putExtra("idUser", idUser);
                                    intento.putExtra("estatura", estatura);
                                    intento.putExtra("peso", peso);
                                    intento.putExtra("idUser", idUser);
                                    startActivityForResult(intento, 1234);
                                    Toast.makeText(LoginActivity.this, "Bienvenido" + " " + user2, Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }
                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });
                        /*
                        Cursor c = dbUsers.rawQuery("select * FROM USERS WHERE usuario = '" + Login + "' AND contrasena ='" + Passw + "'", null);
                        if (c.getCount() == 0)
                        {
                            Toast.makeText(getApplicationContext(), "Usuario no registrado", Toast.LENGTH_LONG).show();
                        }
                        else if (c.moveToFirst())
                        {
                            idUser = c.getInt(0);
                            user2 = c.getString(1);
                            pass2 = c.getString(2);
                            email2 = c.getString(4);
                            estatura = c.getString(5);
                            peso = c.getString(6);

                            Intent intento = new Intent(getApplicationContext(), MainActivity.class);
                            intento.putExtra("user", user2);
                            intento.putExtra("pass", pass);
                            intento.putExtra("email", email);
                            intento.putExtra("estatura", estatura);
                            intento.putExtra("peso", peso);
                            intento.putExtra("idUser", String.valueOf(idUser));
                            startActivityForResult(intento, 1234);
                            Toast.makeText(LoginActivity.this, "Bienvenido" + " " + user2, Toast.LENGTH_SHORT).show();
                            finish();
                        } else
                        {
                            Toast.makeText(LoginActivity.this, "Usuario o contrasena incorrectos", Toast.LENGTH_SHORT).show();
                        }*/
                    }
                }
            });
            bRegistro.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View view)
                {
                    Intent intento = new Intent(getApplicationContext(), RegistroActivity.class);
                    startActivityForResult(intento, 1234);
                }
            });
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1234 && resultCode == RESULT_OK)
        {
            user = data.getExtras().getString("user");
            pass = data.getExtras().getString("pass");
            email = data.getExtras().getString("email");
            estatura = data.getExtras().getString("estatura");
            peso = data.getExtras().getString("peso");
            idUser = data.getExtras().getString("idUser");
            Log.d("user", user);
            Log.d("pass", pass);
            Log.d("email",email);
            SharedPreferences preferencias=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor= preferencias.edit();
            editor.putString("user",  user);
            editor.putString("pass", pass);
            editor.putString("email", email);
            editor.putString("estatura", estatura);
            editor.putString("peso", peso);
            editor.putString("idUser", idUser);
            editor.putString("cerrar","no");
            editor.commit();
            preferencias = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            preferencia1=preferencias.getString("user","");
            preferencia2=preferencias.getString("pass","");
            preferencia3=preferencias.getString("email","");
            preferencia4=preferencias.getString("estatura","");
            preferencia5=preferencias.getString("peso","");
            preferencia6=preferencias.getString("idUser","");
            cerrar=preferencias.getString("cerrar","");

        }
        if (requestCode == 1234 && resultCode == RESULT_CANCELED) {
            Log.d("mensaje", "no se cargaron datos");
            Toast.makeText(this, "No se cargaron los datos",Toast.LENGTH_SHORT).show();
        }
    }

}
