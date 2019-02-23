package com.marlonn.spartacontroller.spartacontroller.utils;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.marlonn.spartacontroller.spartacontroller.R;

public class FragmentoUtils {

    public static void replace(FragmentActivity activity, Fragment fragment) {
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentPrincipal, fragment).commit();
    }


}
