package com.example.faith.linkedlistdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private ListAdapter mAdapter;
    private EditText mName, mPosition;
    private LinkedList<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView listRecyclerView;

        // Set up the views
        listRecyclerView = findViewById(R.id.name_list_view);
        mName = findViewById(R.id.person_name_edit_text);
        mPosition = findViewById(R.id.position_edit_text);

        listRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mList = new LinkedList<>();
        mList.add("Obinna");
        mList.add("Kene");
        mList.add("Chinedu");
        mList.add("Mary");
        mList.add("Ada");

        mAdapter = new ListAdapter(this, mList);
        listRecyclerView.setAdapter(mAdapter);
        listRecyclerView.addItemDecoration(new DividerItemDecoration(listRecyclerView.getContext(),
                DividerItemDecoration.VERTICAL));

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int id = (int) viewHolder.itemView.getTag();

                mList.remove(id);

                mAdapter.updateList(mList);
            }
        }).attachToRecyclerView(listRecyclerView);
    }


    public void addToList(View view) {
        if(mName.getText().length() == 0 || mPosition.getText().length() == 0) {
            return;
        }

        int defaultPosition = 0;
        try {
            defaultPosition = Integer.parseInt(mPosition.getText().toString());
            if(defaultPosition > mList.size()) {
                Toast.makeText(MainActivity.this, "Position exceeds size of list",
                        Toast.LENGTH_LONG).show();
                return;
            }
        } catch (NumberFormatException ex) {
            Toast.makeText(this, "Failed to parse to number: " + ex.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }

        mList.add(defaultPosition,mName.getText().toString());
        mAdapter.updateList(mList);

        mPosition.clearFocus();
        mPosition.getText().clear();
        mName.getText().clear();
        mName.clearFocus();
    }
}
