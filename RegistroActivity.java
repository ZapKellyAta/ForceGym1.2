package com.example.kellyjohanazapataestrada.forcegym;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class RegistroActivity extends LoginActivity
{
    //**********************************************
    //Creamos la base de datos
    Usuarios users;
    SQLiteDatabase dbUsers;
    ContentValues dataBD;

    private String FIREBASE_URL = "https://forcegym-b010f.firebaseio.com/";
    private Firebase firebasedatos;

    static int id;
    //*************************************************
    Button btnSign;
    EditText txtUser, txtPass, txtRePass, txtEmail, txtEstatura, txtPeso;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //****************************************
        //Base de datos
        Firebase.setAndroidContext(this);
        firebasedatos =  new Firebase (FIREBASE_URL);
        users = new Usuarios(this,"dbUsers",null,1);
        dbUsers = users.getWritableDatabase();
        //****************************************

        btnSign = (Button)findViewById(R.id.btnSign);
        txtUser =  (EditText)findViewById(R.id.txtUser);
        txtPass = (EditText)findViewById(R.id.txtPass);
        txtRePass = (EditText)findViewById(R.id.txtRePass);
        txtEmail = (EditText)findViewById(R.id.txtEmail);
        txtEstatura = (EditText)findViewById(R.id.txtEstatura);
        txtPeso = (EditText)findViewById(R.id.txtPeso);

        btnSign.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String strUserName = txtUser.getText().toString();
                String strPass = txtPass.getText().toString();
                String strPass2 = txtRePass.getText().toString();
                String strEmail = txtEmail.getText().toString();
                String dblEstatura = txtEstatura.getText().toString();
                String dblPeso = txtPeso.getText().toString();

                if(TextUtils.isEmpty(strUserName))
                {
                    txtUser.setError("Ingrese Login");
                    Toast.makeText(RegistroActivity.this,"Ingrese Login",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(TextUtils.isEmpty(strPass))
                {
                    txtPass.setError("Ingrese Password");
                    Toast.makeText(RegistroActivity.this,"Ingrese Password",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(TextUtils.isEmpty(strPass2))
                {
                    txtRePass.setError("Repita Password");
                    Toast.makeText(RegistroActivity.this,"Repita Password",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(TextUtils.isEmpty(strEmail))
                {
                    txtEmail.setError("Ingrese Email");
                    Toast.makeText(RegistroActivity.this,"Ingrese Email",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(TextUtils.isEmpty(dblEstatura))
                {
                    txtEstatura.setError("Ingrese Estatura");
                    Toast.makeText(RegistroActivity.this,"Ingrese Estatura",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(TextUtils.isEmpty(dblPeso))
                {
                    txtPeso.setError("Ingrese Peso");
                    Toast.makeText(RegistroActivity.this,"Ingrese Peso",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if ((txtPass.getText().toString()).equals(txtRePass.getText().toString())!=true)
                {
                    Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
                }
                else
                {
                    bandera = 1;
                    Firebase firebd = firebasedatos.child("Usuario" + id);
                    //Creamos un objeto de la clase Contacto
                    //Usuario user = new Usuario(strUserName,strPass,strPass2,strEmail,dblEstatura,dblPeso, String.valueOf(id));
                    firebd.setValue(user);
                    id=id+1;


                    Cursor c = dbUsers.rawQuery("select * FROM USERS WHERE usuario = '"+strUserName+"'",null);
                    if(c.getCount()==0)
                    {
                        bandera = 1;

                        dataBD = new ContentValues();
                        dataBD.put("usuario",strUserName);
                        dataBD.put("contrasena",strPass);
                        dataBD.put("recontrasena",strPass2);
                        dataBD.put("email",strEmail);
                        dataBD.put("estatura",dblEstatura);
                        dataBD.put("peso",dblPeso);

                        dbUsers.insert("users",null,dataBD);
                        dbUsers.execSQL("INSERT INTO USERS VALUES(null, '"+strUserName+"','"+strPass+"','"+strPass2+"','"+strEmail+"','"+dblEstatura+"','"+dblPeso+"')");

                        Toast.makeText(getApplicationContext(), "Usuario Registrado exitosamente", Toast.LENGTH_LONG).show();
                        Intent intent= new Intent(getApplicationContext(),LoginActivity.class);
                        intent.putExtra("user", strUserName);
                        intent.putExtra("pass", strPass);
                        intent.putExtra("email", strEmail);
                        intent.putExtra("estatura", dblEstatura);
                        intent.putExtra("peso", dblPeso);
                        intent.putExtra("idUser", id);
                        //Toast.makeText(RegistroActivity.this,"user " + strUserName,Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        finish();

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "El nombre de usuario ya existe", Toast.LENGTH_LONG).show();
                    }

                }

            }
        });
    }

}
