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
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        return this.device.getAddress() == ((MyBluetoothDevice)obj).device.getAddress() && this.device.getName() == ((MyBluetoothDevice)obj).device.getName();

    }
}
