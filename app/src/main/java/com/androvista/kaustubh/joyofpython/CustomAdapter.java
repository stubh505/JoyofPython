package com.androvista.kaustubh.joyofpython;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    private TextView headerView, descView;
    private ImageView iconView;
    private int icon;
    private ArrayList<String> list1, desc;
    private LayoutInflater inflater;
    private Context context;

    CustomAdapter(ArrayList list1, ArrayList desc, LayoutInflater inflater, int icon, Context context) {
        this.list1 = list1;
        this.desc = desc;
        this.inflater = inflater;
        this.icon = icon;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list1.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = inflater.inflate(R.layout.custom_list, null);
        headerView = view.findViewById(R.id.list_header_view);
        descView = view.findViewById(R.id.list_desc_view);
        iconView = view.findViewById(R.id.list_icon_view);
        try {
            setData(list1.get(i), desc.get(i), i);
        } catch (Exception e) {
            new AlertDialog.Builder(context)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setMessage(R.string.data_corrupted_warn)
                    .setTitle(R.string.data_corrupted_title)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    }).show();
            e.printStackTrace();
            deleteFiles(new File(LoadActivity.dir));
        }
        return view;
    }

    private void setData(String header, String desc, int i) {

        iconView.setImageResource(icon);
        headerView.setText(header);
        descView.setText(desc);

    }

    static void deleteFiles(final File folder) {
        if (folder.isDirectory()) {
            File[] list = folder.listFiles();
            if (list != null) {
                for (File temp:list) {
                    if (temp.isDirectory()) {
                        deleteFiles(temp);
                    }
                    temp.delete();
                }
            }
            if (!folder.delete()) {
                System.out.println("can't delete folder : " + folder);
            }
        }
    }
}
