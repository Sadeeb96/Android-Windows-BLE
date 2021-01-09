package com.example.androidble;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    BluetoothManager bluetoothManager;
    BluetoothAdapter bluetoothAdapter;
    BluetoothLeScanner bleScanner;
    ScanCallback bleScanCallBack;
    boolean isScanning;
    Handler handler;
    int scanDuration;
    BleDeviceListAdapter devices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();

    }
    private void initialize()
    {
        if(!isBLESupported()){
            // Do Something Later
        }
        bluetoothManager = (BluetoothManager)getSystemService(Context.BLUETOOTH_SERVICE);
        bluetoothAdapter = bluetoothManager.getAdapter();
        checkAndEnable();
        bleScanner = BluetoothAdapter.getDefaultAdapter().getBluetoothLeScanner();
        bleScanCallBack = new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                super.onScanResult(callbackType, result);
            }
        };
        isScanning = false;
        handler = new Handler();
        scanDuration = 10000;
        devices = new BleDeviceListAdapter(this);

    }
    private boolean isBLESupported()
    {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE);
    }
    private void checkAndEnable()
    {
        if(bluetoothAdapter == null || !bluetoothAdapter.isEnabled())
        {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent,1);

        }
    }
    private void startScan()
    {
        if(!isScanning){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isScanning = false;
                    bleScanner.stopScan(bleScanCallBack);
                }
            },scanDuration);
            isScanning = true;
            bleScanner.startScan(bleScanCallBack);

        }else{
            isScanning = false;
            bleScanner.stopScan(bleScanCallBack);
        }

    }
}