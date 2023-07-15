package com.ltdd.vidu1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class them extends AppCompatActivity {
    EditText eSoLuong, eDonGia;
    TextView tvID,tvTen;
    Button btnLuu, btnDong;
    String action="";
    int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.them);
        getViews();
        //lay ra doi tuong intent duoc truyen sang tu adapter
        Intent data =getIntent();
        action = data.getStringExtra("ins_upd");
        //hien thi du lieu len view
        if(action.equals("update")){
            thongTinSanPham db =(thongTinSanPham) data.getExtras()
                    .getSerializable("sanpham");
            tvID.setText(db.getId());
            tvTen.setText(db.getTen());
            eSoLuong.setText(db.getsoluong());
            eDonGia.setText(db.getdongia());

        }
        //kiem tra action la insert hay update
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(action.equals("update")){
                    //cap nhat du lieu moi va truyen ve
                    Intent updIntent= new Intent();
                    updIntent.putExtra("action", action);
                    thongTinSanPham thongTinSanPham = new thongTinSanPham();
                    thongTinSanPham.setTen(tvTen.getText().toString());
                    thongTinSanPham.setId(tvID.getText().toString());
                    thongTinSanPham.setsoluong(Integer.parseInt(eSoLuong.getText().toString()));
                    thongTinSanPham.setdongia(Integer.parseInt(eDonGia.getText().toString()));
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("updSanPham", thongTinSanPham);
                    updIntent.putExtras(bundle);
                    //set ket qua tra ve
                    setResult(RESULT_OK,updIntent);
                    finish();
                }else if(action.equals("insert")){
                    Intent insIntent = new Intent();
                    insIntent.putExtra("action", action);
                    thongTinSanPham thongTinSanPham = new thongTinSanPham();
                    thongTinSanPham.setTen(tvTen.getText().toString());
                    thongTinSanPham.setId(tvID.getText().toString());
                    thongTinSanPham.setsoluong(Integer.parseInt(eSoLuong.getText().toString()));
                    thongTinSanPham.setdongia(Integer.parseInt(eDonGia.getText().toString()));
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("ins", thongTinSanPham);
                    insIntent.putExtras(bundle);
                    setResult(RESULT_OK, insIntent);
                    finish();

                }
            }
        });
    }

    private void getViews(){
        tvID = findViewById(R.id.tvID);
        tvTen = findViewById(R.id.tvTen);
        eSoLuong = findViewById(R.id.eSoLuong);
        eDonGia = findViewById(R.id.eDonGia);
        btnLuu = findViewById(R.id.btnLuu);
        btnDong = findViewById(R.id.btnDong);
    }
}