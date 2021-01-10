package com.example.androidble;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
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
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1001);
        initialize();
        startScan();

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
                devices.addDevice(new MyBluetoothDevice(result.getDevice(),result.getRssi()));
              //  System.out.println("NEW DEVICE NAME: "+result.getDevice().getName()+" Strength: "+ result.getRssi() +"Total Devices: "+ devices.getCount());
                devices.notifyDataSetChanged();
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
                    stopScan();
                }
            },scanDuration);
            System.out.println("---------------STARTING SCAN------------------");
            isScanning = true;
            bleScanner.startScan(bleScanCallBack);

        }else{
            stopScan();
        }

    }
    private void stopScan()
    {
        System.out.println("---------------STOPPING SCAN------------------");
        isScanning = false;
        bleScanner.stopScan(bleScanCallBack);
    }
}