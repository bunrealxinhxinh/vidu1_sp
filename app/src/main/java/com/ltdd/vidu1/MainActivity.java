package com.ltdd.vidu1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lvSanPham;
    List<thongTinSanPham> lsData = new ArrayList<thongTinSanPham>();
    SanPhamAdapter adapter;
    SanPhamDB  SanPhamDB = new SanPhamDB(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvSanPham = findViewById(R.id.lvSanPham);
        //tao doi tuong DanhBaDB thuc hien initdata

        SanPhamDB.initData();
        //select du lieu do vao lsData de hien len listview
        lsData = SanPhamDB.getAllSanPham();
        //khoi tao adapter load view va data
        adapter = new SanPhamAdapter(MainActivity.this,lsData);
        //set adapter cho listview
        lvSanPham.setAdapter(adapter);
        EditText etSearch = findViewById(R.id.etSearch);
        Button btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchKeyword = etSearch.getText().toString().trim();
                List<thongTinSanPham> searchResults = SanPhamDB.searchSanPham(searchKeyword);
                adapter.clear();
                adapter.addAll(searchResults);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh adapter
        lsData.clear();
        lsData.addAll(SanPhamDB.getAllSanPham());
        adapter.notifyDataSetChanged();
        lvSanPham.invalidateViews();
        lvSanPham.refreshDrawableState();
    }


    public void onInsSanPhamClick(View view) {
        Intent insIntent = new Intent(this, them.class);
        insIntent.putExtra("ins_upd", "insert");
        insSanPham.launch(insIntent);
    }
    ActivityResultLauncher<Intent> insSanPham= registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    //insert du lieu
                    if(result.getResultCode()==RESULT_OK){
                        Intent data = result.getData();
                        if(data.getStringExtra("action").equals("insert")){
                            //insert du lieu vao database
                            thongTinSanPham db =(thongTinSanPham) data.getExtras().getSerializable("ins");
                            SanPhamDB.insSanPham(db);
                        }
                    }
                }
            });
}