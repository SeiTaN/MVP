package com.juanjo.mvp.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.inject.Inject;
import com.juanjo.mvp.R;
import com.juanjo.mvp.interfaces.IMainView;
import com.juanjo.mvp.models.ImageDto;
import com.juanjo.mvp.presenters.MainViewPresenter;
import com.juanjo.mvp.views.adapters.ImageListAdapter;

import org.parceler.Parcels;

import java.util.List;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;


public class MainActivity extends RoboActivity implements IMainView, AdapterView.OnItemClickListener {


    @InjectView(R.id.list)
    ListView list;
    @InjectView(R.id.progress)
    ProgressBar progressBar;
    @InjectView(R.id.retry)
    Button retryButton;

    @Inject
    MainViewPresenter presenter;

    ImageListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter.onCreate(this);
    }

    @Override
    public void createList(List<ImageDto> images) {
        adapter = new ImageListAdapter(this, images);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showRetryButton() {
        retryButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetryButton() {
        retryButton.setVisibility(View.GONE);
    }

    @Override
    public void showList() {
        list.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideList() {
        list.setVisibility(View.GONE);
    }


    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("IMAGE", Parcels.wrap(presenter.getImage(position)));
        startActivity(intent);
    }
}
