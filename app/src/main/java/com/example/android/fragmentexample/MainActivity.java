/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.fragmentexample;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    boolean fragmentIsUp = false;
    Button btn;
    static final String STATE_FRAGMENT = "state_of_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn_open_fragment);
        btn.setOnClickListener(view -> { if (fragmentIsUp) closeFragment(); else displayFragment(); });

        if (savedInstanceState != null) {
            fragmentIsUp = savedInstanceState.getBoolean(STATE_FRAGMENT);
            if (fragmentIsUp) {
                btn.setText("CLOSE");
            }
        }
    }

    public void displayFragment() {
        SimpleFragment sf = SimpleFragment.newInstance();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragment_container, sf).addToBackStack(null).commit();
        btn.setText("CLOSE");
        fragmentIsUp = true;
    }

    public void closeFragment() {
        FragmentManager fm = getFragmentManager();
        SimpleFragment sf = (SimpleFragment) fm.findFragmentById(R.id.fragment_container);
        if (sf != null) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.remove(sf).commit();
        }
        btn.setText("OPEN");
        fragmentIsUp = false;
    }

    public void onSaveInstanceState(Bundle savedInstance) {
        savedInstance.putBoolean(STATE_FRAGMENT, fragmentIsUp);
        super.onSaveInstanceState(savedInstance);
    }
}
