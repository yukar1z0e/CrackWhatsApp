package com.yukar1z0e.crackwhatsapp;


import android.app.Application;
import android.content.Context;
import android.os.FileObserver;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.MotionEvent;

import java.text.Format;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.Objects;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.assetAsByteArray;
import static de.robv.android.xposed.XposedHelpers.findAndHookConstructor;
import static de.robv.android.xposed.XposedHelpers.findClass;
import static de.robv.android.xposed.XposedHelpers.findField;
import static de.robv.android.xposed.XposedHelpers.callMethod;
import static de.robv.android.xposed.XposedHelpers.setObjectField;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class crackmain implements IXposedHookLoadPackage {
    private XC_LoadPackage.LoadPackageParam lpparam = null;

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if (loadPackageParam.packageName.contains("com.whatsapp")) {
            findAndHookMethod(Application.class, "attach", Context.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    lpparam = loadPackageParam;
                    crackRootAndROMCheck();
                    getInfo();
                }
            });
        }
    }

    //解决ROOT和ROM检测问题
    public void crackRootAndROMCheck() {
        final Class<?> aClass = findClass("d.g.fa.a", lpparam.classLoader);
        final Class<?> fClass = findClass("d.g.t.f", lpparam.classLoader);
        final Class<?> mClass = findClass("d.g.t.m", lpparam.classLoader);

        findAndHookMethod(aClass, "f", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                //Log.d("crackWhatsApp","ROM Value: "+param.getResult());
                param.setResult(false);

                Field fField = findField(aClass, "f");
                //Log.d("crackWhatsApp","str: "+fField.get(param.thisObject));
                fField.set(param, "miuihhhhhh");

            }
        });

        findAndHookMethod(aClass, "a", fClass, mClass, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                //Log.d("crackWhatsApp","ROOT Value: "+param.getResult());
                param.setResult(false);
            }
        });
    }

    public void getInfo() {
        final Class<?> ContactInfo$dClass = findClass("com.whatsapp.ContactInfo$d", lpparam.classLoader);
        final Class<?> CdClass = findClass("d.g.x.Cd", lpparam.classLoader);

        findAndHookMethod(ContactInfo$dClass, "doInBackground", Object[].class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                //Log.d("crackWhatsApp", "I am in ");

                Field ContactInfo$d_b_Field = findField(ContactInfo$dClass, "b");
                Object ContactInfo$d_b_Object = ContactInfo$d_b_Field.get(param.thisObject);

                Field c_Field = findField(CdClass, "c");
                Field d_Field = findField(CdClass, "d");
                Field e_Field = findField(CdClass, "e");
                Field l_Field = findField(CdClass, "l");
                Field m_Field = findField(CdClass, "m");
                Field o_Field = findField(CdClass, "o");
                Field i_Field = findField(CdClass, "i");
                Field n_Field = findField(CdClass, "n");
                Field r_Field = findField(CdClass, "r");
                Field s_Field = findField(CdClass, "s");
                Field t_Field = findField(CdClass, "t");
                Field p_Field = findField(CdClass, "p");
                Field v_Field = findField(CdClass, "v");
                Field w_Field = findField(CdClass, "w");
                Field z_Field = findField(CdClass, "z");

                Log.d("crackWhatsApp",
                        "display name: " + c_Field.get(ContactInfo$d_b_Object)
                                + " ;sort name: " + o_Field.get(ContactInfo$d_b_Object)
                                + " ;个性签名: " + p_Field.get(ContactInfo$d_b_Object)
                                + " ;wa name: " + n_Field.get(ContactInfo$d_b_Object)
                                + " ;phone type: " + d_Field.get(ContactInfo$d_b_Object)
                                + " ;phone label: " + e_Field.get(ContactInfo$d_b_Object)
                                + " ;given name: " + l_Field.get(ContactInfo$d_b_Object)
                                + " ;family name: " + m_Field.get(ContactInfo$d_b_Object)
                                + " ;photo: " + Integer.valueOf(i_Field.getInt(ContactInfo$d_b_Object))
                                + " ;nickname: " + r_Field.get(ContactInfo$d_b_Object)
                                + " ;company: " + s_Field.get(ContactInfo$d_b_Object)
                                + " ;title: " + t_Field.get(ContactInfo$d_b_Object)
                                + " ;v: " + v_Field.get(ContactInfo$d_b_Object)
                                + " ;Locale: " + w_Field.get(ContactInfo$d_b_Object)
                                + " ;z: " + z_Field.get(ContactInfo$d_b_Object)
                );
            }
        });
    }
}
