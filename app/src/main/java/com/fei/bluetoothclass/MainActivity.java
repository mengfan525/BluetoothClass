package com.fei.bluetoothclass;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.lang.ref.ReferenceQueue;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 0;
    private com.fei.bluetoothclass2.BluetoothController mController = new com.fei.bluetoothclass2.BluetoothController();
    private Toast mToast;
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,-1);
            switch (state){
                case BluetoothAdapter.STATE_OFF:
                    showToast("STATE_OFF");
                    break;
                case BluetoothAdapter.STATE_ON:
                    showToast("STATE_ON");
                    break;
                case BluetoothAdapter.STATE_TURNING_ON:
                    showToast("STATE_TURNING_ON");
                    break;
                case BluetoothAdapter.STATE_TURNING_OFF:
                    showToast("STATE_TURNING_OFF");
                    break;
                default:
                    showToast("Unkown STATE");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(receiver,filter);
    }

    public void isSupportBluetooth(View view){
        boolean ret = mController.issupportBluetooth();
        showToast("support Bluetooth?"+ret);
    }

    public void isBluetoothEnable(View view){
        boolean ret = mController.getBluetoothStatus();
        showToast("Bluetooth enable?"+ret);
    }

    public void TurnOnBluetooth(View view){
        mController.turnOnBluetooth(this,REQUEST_CODE);
    }

    public void turnOffBluetooth(View view){
        mController.turnOffBluetooth();
    }
    private void showToast(String text){
        if(mToast == null){
            mToast = Toast.makeText(this,text,Toast.LENGTH_SHORT);
        }
        else {
            mToast.setText(text);
        }
        mToast.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == RESULT_OK){
            showToast("打开成功");
        }
        else{
            showToast("打开失败");
        }
    }
}