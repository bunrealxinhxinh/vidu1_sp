package com.ltdd.vidu1;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class SanPhamAdapter extends ArrayAdapter<thongTinSanPham> {
    Context context;
    List<thongTinSanPham> lsData;
    SanPhamAdapter adapter;
    public SanPhamAdapter(@NonNull Context context, List<thongTinSanPham> lsSP) {
        super(context,0 ,lsSP);
        this.context = context;
        lsData = lsSP;
        adapter= this;
    }
    @Override
    public int getCount() {
        return lsData.size();
    }

    @Nullable
    @Override
    public thongTinSanPham getItem(int position) {
        return lsData.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.activity_thong_tin_san_pham,
                    parent, false);
        }
        thongTinSanPham thongTinSanPham = lsData.get(position);
        //lay doi tuong cac view tren layout
        TextView tvTen = view.findViewById(R.id.tvTen);
        TextView tvID = view.findViewById(R.id.tvID);
        EditText eSoLuong = view.findViewById(R.id.eSoLuong);
        EditText eDonGia = view.findViewById(R.id.eDonGia);
        TextView tvNgay = view.findViewById(R.id.tvNgay);
        //data binding
        tvTen.setText(thongTinSanPham.getTen());
        tvID.setText(thongTinSanPham.getId());
        eSoLuong.setText(String.valueOf(thongTinSanPham.getsoluong()));
        eDonGia.setText(String.valueOf(thongTinSanPham.getdongia()));
        tvNgay.setText(thongTinSanPham.getngay());
        //xu ly su kien tren tung nut lenh
//        btnSua.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //day doi tuong danhba sang activity cap nhat
//                Intent data  = new Intent(context,InsUpdActivity.class);
//                data.putExtra("ins_upd","update");
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("danhba", danhBa);
//                data.putExtras(bundle);
//                callUpd.launch(data);
//
//            }
//        });
//
//        btnXoa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder dialog =
//                        new AlertDialog.Builder(context);
//                dialog.setTitle("Xoa danh ba");
//                dialog.setCancelable(true);
//                dialog.setMessage("Ban co chac chan xoa danh ba: "+ danhBa.getTen());
//                dialog.setPositiveButton("Xoa", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        DanhBaDB database = new DanhBaDB(context);
//                        database.delDanhBa(danhBa.getTen());
//                        lsData =database.getAllDanhBa();
//                        adapter.notifyDataSetChanged();
//                        dialog.dismiss();
//                    }
//                });
//                dialog.setNegativeButton("Huy",
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                //k xoa
//                                dialog.dismiss();
//                            }
//                        });
//                AlertDialog alert =dialog.create();
//                alert.show();
//
//            }
//
//        });
//
//        btnSMS.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intentSMS = new Intent(context,SendSmsActivity.class);
//                intentSMS.putExtra("ten", danhBa.getTen());
//                intentSMS.putExtra("sdt", danhBa.getSoDT());
//                context.startActivity(intentSMS);
//            }
//        });
//        btnCall.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intentCall = new Intent(Intent.ACTION_DIAL);
//                intentCall.setData(Uri.parse("tel:" + danhBa.getSoDT()));
//                context.startActivity(intentCall);
//
//            }
//        });
        return view;
    }

        ActivityResultLauncher<Intent> callUpd = ((AppCompatActivity) getContext()).registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        //update danhba
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            //lay ra doi tuong intent chua kq tra ve
                            Intent data = result.getData();
                            String action = data.getStringExtra("action");
                            if (action.equals("update")) {
                                //update du lieu
                                thongTinSanPham db = (thongTinSanPham) data.getExtras().
                                        getSerializable("updDanhBa");
                                SanPhamDB database = new SanPhamDB(context);
                                if (database.udpSanPham(db, db.getId()) > 0) {
                                    Toast.makeText(context, "update success!!",
                                            Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(context, "update failed!!",
                                            Toast.LENGTH_LONG).show();
                                }
                                //refresh adapter
                                lsData = database.getAllSanPham();
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }
                }
        );
    }
