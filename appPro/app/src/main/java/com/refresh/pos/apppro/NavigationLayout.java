package com.refresh.pos.apppro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class NavigationLayout extends RelativeLayout
{
    Button ok;
    public NavigationLayout(Context context, RelativeLayout parent)
    {
        super(context);
        initView(context,parent);
    }

    public void initView(final Context context,RelativeLayout parent)
    {
        // надуваем любой xml файл разметки
        View view= LayoutInflater.from(context).inflate(R.layout.view_drawer_layout,parent,true);

        ok=(Button)view.findViewById(R.id.ok);

        ok.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Ok",Toast.LENGTH_SHORT).show();
            }
        });


    }
}