package com.example.androidble;

import android.bluetooth.BluetoothDevice;

import androidx.annotation.Nullable;

public class MyBluetoothDevice  {
    private BluetoothDevice device;
    private int rssi;
    public MyBluetoothDevice(BluetoothDevice device, int rssi){
        this.rssi = rssi;
        this.device = device;

    }
    public BluetoothDevice getDevice(){
        return this.device;
    }
    public int getRssi(){
        return this.rssi;
    }
    public void setDevice(BluetoothDevice device){
        this.device = device;
    }
    public void setRssi(int rssi)
    {
        this.rssi = rssi;

    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return this.device.getAddress().equals(((MyBluetoothDevice)obj).device.getAddress() );

    }
}
