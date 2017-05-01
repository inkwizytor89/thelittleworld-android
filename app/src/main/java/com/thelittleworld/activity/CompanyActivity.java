package com.thelittleworld.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.thelittleworld.R;
import com.thelittleworld.core.AppCore;
import com.thelittleworld.entity.Companion;
import com.thelittleworld.entity.CompanionDao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CompanyActivity extends AppCompatActivity {

    public List<String> list = new ArrayList<>();
    public ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        buildArrayAdapter();
        printCompanions();
    }

    private void buildArrayAdapter() {
        arrayAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, list);
        ListView actionsListView = (ListView) findViewById(R.id.company_listView);
        actionsListView.setAdapter(arrayAdapter);
    }

    private void printCompanions() {
        List<Companion> allCompanions = getAllCompanions();
        list.clear();
        list.addAll(companionToString(allCompanions));
        arrayAdapter.notifyDataSetChanged();
    }

    private List<Companion> getAllCompanions() {
        CompanionDao companionDao = AppCore.getApplication().getDaoSession().getCompanionDao();
        return companionDao.loadAll();
    }

    private Collection<? extends String> companionToString(List<Companion> companions) {
        List<String> result = new ArrayList<>();
        for (Companion companion : companions) {
            String name = companion.name;
            String sex = companion.sex;
            result.add(sex + ": " + name);
        }
        return result;
    }
}
