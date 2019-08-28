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
        final Class<?> aClass=findClass("d.g.fa.a",lpparam.classLoader);
        final Class<?> fClass=findClass("d.g.t.f",lpparam.classLoader);
        final Class<?> mClass=findClass("d.g.t.m",lpparam.classLoader);

        findAndHookMethod(aClass, "f",new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Log.d("crackWhatsApp","ROM Value: "+param.getResult());
                param.setResult(false);

                Field fField=findField(aClass,"f");
                Log.d("crackWhatsApp","str: "+fField.get(param.thisObject));
                fField.set(param,"miuihhhhhh");

            }
        });

        findAndHookMethod(aClass, "a", fClass, mClass, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Log.d("crackWhatsApp","ROOT Value: "+param.getResult());
                param.setResult(false);
            }
        });
    }

    public void getInfo() {
        final Class<?> ContactInfo$bClass = findClass("com.whatsapp.ContactInfo$b", lpparam.classLoader);
        final Class<?> CdClass = findClass("d.g.x.Cd", lpparam.classLoader);

        findAndHookMethod(ContactInfo$bClass, "getView", int.class, View.class, ViewGroup.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Log.d("crackWhatsApp", "I am in ");

//                Field r_Field = findField(CdClass, "r");
//                Object r_object = r_Field.get(param.thisObject);
//                Log.d("crackWhatsApp", "r_value: " + r_object.toString());
            }
        });

    }
}
