package com.example.admin.demomanagentthuchi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.admin.demomanagentthuchi.Model.information;
import com.example.admin.demomanagentthuchi.MyAdapter.Adapter_Informatio;

import java.util.ArrayList;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class InforActivity extends SwipeBackActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    ArrayList<information> ds = new ArrayList<information>();
    SwipeBackLayout swipeBackLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor);


        swipeBackLayout = getSwipeBackLayout();
        int edgeFlag = 0;
        edgeFlag = swipeBackLayout.EDGE_LEFT;
        swipeBackLayout.setEdgeTrackingEnabled(edgeFlag);




        toolbar = (Toolbar) findViewById(R.id.allLayout);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.days_list);

        Adapter_Informatio adapter_informatio = new Adapter_Informatio(ds, InforActivity.this);
        LinearLayoutManager manager = new LinearLayoutManager(InforActivity.this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter_informatio);

        TaoDanhSach();


    }

    public void TaoDanhSach() {
        ds.add(new information("Tên phần mền", "Quản lý thu chi"));
        ds.add(new information("Giới thiệu", "Quản lý tài chính là một vấn đề được nhiều người quan tâm. Tuy nhiên, việc quản lý chi tiêu sao cho đúng và hiệu quả chưa bao giờ dễ dàng đối với nhiều người. Vì vậy, để việc này trở nên đơn giản hơn, rất nhiều ứng dụng đã được phát triển và trở thành trợ thủ đắc lực cho nhiều người trong việc ghi chú và theo dõi khoản chi tiêu, tiết kiệm và đầu tư của họ."));
        ds.add(new information("Thực hiện bởi", "72DCTT22"));
        ds.add(new information("Môn học", "Lập trình mobile"));
        ds.add(new information("Khóa", "K72"));
    }
}
