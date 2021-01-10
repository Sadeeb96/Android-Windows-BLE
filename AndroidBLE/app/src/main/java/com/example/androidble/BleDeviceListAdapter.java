package com.example.androidble;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BleDeviceListAdapter extends BaseAdapter {
    private ArrayList<MyBluetoothDevice> mLeDevices;
    private LayoutInflater mInflator;

    public  BleDeviceListAdapter(Activity context){
        super();
        mLeDevices = new ArrayList<MyBluetoothDevice>();
        mInflator = context.getLayoutInflater();
        //mInflator = LayoutInflater.from(context);


    }
    public void addDevice(MyBluetoothDevice device) {
        if(!mLeDevices.contains(device)) {
            mLeDevices.add(device);
            System.out.println("Device Address: "+device.getDevice().getAddress() + " Strength: "+device.getRssi()+ " ADDED "+getCount());
        }else{
            int index = mLeDevices.indexOf(device);
            if(getDevice(index).getRssi() != device.getRssi()){
                mLeDevices.get(index).setRssi(device.getRssi());
                System.out.println("Device Address: "+device.getDevice().getAddress() + " Strength: "+device.getRssi()+ " UPDATED");
            }
        }
        /*for(int i=0;i<mLeDevices.size();i++)
        {
            if(device.equals(mLeDevices.get(i))){
                if(device.getRssi() != mLeDevices.get(i).getRssi()){
                mLeDevices.get(i).setRssi(device.getRssi());
                System.out.println("Device Address: "+device.getDevice().getAddress() + " Strength: "+device.getRssi()+ " Signal Updated "+getCount());
                }
                return;
            }
        }

            mLeDevices.add(device);
            System.out.println("Device Address: "+device.getDevice().getAddress() + " Strength: "+device.getRssi()+ " ADDED "+getCount());
            */

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
        // Implement
        RecyclerView.ViewHolder viewHolder;
        if(convertView == null)
        {
            convertView = mInflator.inflate(R.layout.device_list_item,parent,false);
        }
        MyBluetoothDevice currentDevice = (MyBluetoothDevice)getItem(position);
        TextView deviceName = (TextView) convertView.findViewById(R.id.device_name);
        TextView deviceAddress = (TextView) convertView.findViewById(R.id.device_address);
        TextView deviceRSSI = (TextView) convertView.findViewById(R.id.device_strength);
        deviceName.setText(currentDevice.getDevice().getName());
        deviceAddress.setText(currentDevice.getDevice().getAddress());
        deviceRSSI.setText(currentDevice.getRssi()+"");
        return convertView;


    }
}
