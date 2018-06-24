package cubex.mahesh.yoursaloon;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        int  cam_per_status = ContextCompat.checkSelfPermission(
                this, Manifest.permission.CAMERA);

        int  storg_per_status = ContextCompat.checkSelfPermission(
this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

    if( cam_per_status == PackageManager.PERMISSION_GRANTED
            && storg_per_status == PackageManager.PERMISSION_GRANTED)
    {
            startHandler();
    }else{
        requestPermissions( );
     }
    }


    void startHandler( )
    {
        Handler h = new Handler( );
        h.postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(MainActivity.this,
                        LoginOptionsActivity.class));

            }
        },3000);
    }


    void requestPermissions( )
    {

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA,
                     Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    123);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults[0]==PackageManager.PERMISSION_GRANTED
                && grantResults[1]==PackageManager.PERMISSION_GRANTED)
        {
                startHandler();
        }else{

            AlertDialog.Builder ad = new AlertDialog.Builder(this);
            ad.setTitle("Message");
            ad.setMessage("You can't continue  the application with out permission grant ..");
            ad.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    finish();
                }
            });
            ad.show();
        }
    }
}
