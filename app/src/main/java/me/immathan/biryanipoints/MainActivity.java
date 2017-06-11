package me.immathan.biryanipoints;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import me.immathan.biryanipoints.presenter.MainPresenter;
import me.immathan.biryanipoints.presenter.MainPresenterImpl;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        MainPresenter mainPresenter = new MainPresenterImpl(this);
    }


}
