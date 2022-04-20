package com.no.myapplication22222222;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class AddActivity  extends AppCompatActivity {
    EditText nameEdit,surnameEdit,ageEdit,loginEdit,passwordEdit;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        nameEdit=findViewById(R.id.tw_name);
        surnameEdit=findViewById(R.id.tw_surname);
        ageEdit=findViewById(R.id.tw_age);
        loginEdit=findViewById(R.id.tw_login);
        passwordEdit=findViewById(R.id.tw_password);
        button=findViewById(R.id.add_user);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEdit.getText().toString();
                String surname = surnameEdit.getText().toString();
                String login = loginEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                int age=Integer.parseInt(ageEdit.getText().toString());
                AsyncQery asyncQery=new AsyncQery();
                asyncQery.execute(new User(age, name, surname,login,password));

            }
        });
    }
    class User{
        int id,age;
        String name,surname,login,password;

        public User(int age, String name, String surname, String login, String password) {
            this.age = age;
            this.name = name;
            this.surname = surname;
            this.login = login;
            this.password = password;
        }
    }

    class  ServerAnswer{
        String status;
        User data;
    }

    interface UserService{
        @POST("/000000/v1.0/user/add")
        Call<ServerAnswer> addUser(@Body User user);
    }

    class AsyncQery extends AsyncTask<User, Integer, ServerAnswer> {

        @Override
        protected ServerAnswer doInBackground(User... users) {
            Retrofit retrofit=new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://10.148.190.161:5000/")
                    .build();
            UserService userService = retrofit.create(UserService.class);
            Call<ServerAnswer> call = userService.addUser(users[0]);
            try {
                Response<ServerAnswer> response=call.execute();
                return response.body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ServerAnswer serverAnswer) {
            if(serverAnswer!=null){
                Toast.makeText(getApplicationContext(),serverAnswer.status,Toast.LENGTH_LONG).show();
            }
        }
    }
}
