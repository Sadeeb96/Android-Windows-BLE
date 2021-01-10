package com.example.androidble;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class BleDeviceListAdapter extends BaseAdapter {
    private ArrayList<MyBluetoothDevice> mLeDevices;
    private LayoutInflater mInflator;

    public  BleDeviceListAdapter(Activity context){
        super();
        mLeDevices = new ArrayList<MyBluetoothDevice>();
        mInflator = context.getLayoutInflater();


    }
    public void addDevice(MyBluetoothDevice device) {
        if(!mLeDevices.contains(device)) {
            mLeDevices.add(device);
            System.out.println("Device Address: "+device.getDevice().getAddress() + "Strength: "+device.getRssi()+ "ADDED");
        }else{
            int index = mLeDevices.indexOf(device);
            if(getDevice(index).getRssi() != device.getRssi()){
                mLeDevices.get(index).setRssi(device.getRssi());
                System.out.println("Device Address: "+device.getDevice().getAddress() + "Strength: "+device.getRssi()+ "UPDATED");
            }
        }
    }

    public MyBluetoothDevice getDevice(int position) {
        return mLeDevices.get(position);
    }

    public void clear() {
        mLeDevices.clear();
    }

    @Override
    public int getCount() {
        return mLeDevices.size();
    }

    @Override
    public Object getItem(int position) {
        return mLeDevices.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
        // Implement
    }
}
