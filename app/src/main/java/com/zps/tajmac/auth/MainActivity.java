package com.zps.tajmac.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button_login_login;
    private EditText editText_login_username;
    private EditText editText_login_password;
    private String username;
    private String password;
    private String isValidCredentials = new String();
    static byte[] arr = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_login_username = (EditText) findViewById(R.id.editText_login_username);
        editText_login_password = (EditText) findViewById(R.id.editText_login_password);

        button_login_login = (Button) findViewById(R.id.button_login_login);

        button_login_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    username = editText_login_username.getText().toString();
                    password = editText_login_password.getText().toString();
                    Log.d(password , "UserName: ");
                    ApiAuthenticationClient apiAuthenticationClient =
                            new ApiAuthenticationClient(
                                     username
                                    , password
                            );

                    AsyncTask<Void, Void, String> execute = new ExecuteNetworkOperation(apiAuthenticationClient);
                    execute.execute();

                   /* ApiAuthenticationClient apiAuthenticationClient =
                            new ApiAuthenticationClient(
                                    username
                                    , password
                            );
                    isValidCredentials = apiAuthenticationClient.execute();*/

                } catch (Exception ex) {
                    Log.d(ex.toString(), "oException2222: ");
                }
            }
        });
    }

    /**
     * This subclass handles the network operations in a new thread.
     * It starts the progress bar, makes the API call, and ends the progress bar.
     */
    public class ExecuteNetworkOperation extends AsyncTask<Void, Void, String> {

        private ApiAuthenticationClient apiAuthenticationClient;
        private String isValidCredentials;

        /**
         * Overload the constructor to pass objects to this class.
         */
        public ExecuteNetworkOperation(ApiAuthenticationClient apiAuthenticationClient) {
            this.apiAuthenticationClient = apiAuthenticationClient;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // Display the progress bar.
            findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
        }
    /* Urcit jak se pohybuje hodnota isValidCredentials */
        @Override
        protected String doInBackground(Void... params) {
            try {
                 arr =  apiAuthenticationClient.execute();
                Log.d(String.valueOf(arr.length), "doInBackgroundlll: ");
                isValidCredentials = new String( arr);
                Log.d(isValidCredentials, " AuthKey: ");


            } catch (Exception e) {
                e.printStackTrace();
                Log.d(e.toString(), "Exception1111: ");
            }

            return isValidCredentials ;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            // Hide the progress bar.
            findViewById(R.id.loadingPanel).setVisibility(View.GONE);


            // Login Success
            //Log.d(isValidCredentials, "doInBackgroundString: ");
            if (isValidCredentials!=null) {
                goToSecondActivity();

            }
            // Login Failure
            else {
                Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * Open a new activity window.
     */
    private void goToSecondActivity() {
        Bundle bundle = new Bundle();
        bundle.putString("username", username);
        bundle.putString("password", password);

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);

        isValidCredentials = null;
        arr = null;
    }


}
