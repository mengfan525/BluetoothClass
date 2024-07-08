package com.fei.bluetoothclass2;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

/**
 * 蓝牙适配器
 * Created by fei on 20240707
 */
public class BluetoothController {

    private static final int REQUEST_ENABLE = 1;
    private BluetoothAdapter mAdapter;

    public BluetoothController() {
        mAdapter = BluetoothAdapter.getDefaultAdapter();
    }

//    @Override
//    public AssetManager getAssets() {
//        return null;
//    }

    /**
     * 是否支持蓝牙
     * @return
     */

    public boolean issupportBluetooth() {
        if (mAdapter != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断当前蓝牙状态
     * @return true 打开，flase 关闭
     */
    public boolean getBluetoothStatus() {
        assert (mAdapter != null);
        return mAdapter.isEnabled();
    }

    /**
     * 打开蓝牙
     * @param activity
     * @param requestCode
     */
    public void turnOnBluetooth(Activity activity, int requestCode) {
        Intent BT_intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        activity.startActivityForResult(BT_intent, requestCode);
    }


    /**
     * 关闭蓝牙
     */
    public void turnOffBluetooth() {
        mAdapter.disable();
    }
}
