package dev.nick.extendedloggerdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import dev.nick.logger.LoggerManager;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoggerManager.getLogger(getClass()).funcEnter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        test();
    }

    void test() {
        LoggerManager.getLogger(getClass()).funcEnter();
        LoggerManager.getLogger(getClass()).debug("Hi, You there.");
    }
}
