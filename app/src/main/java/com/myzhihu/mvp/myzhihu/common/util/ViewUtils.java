package com.myzhihu.mvp.myzhihu.common.util;

import com.myzhihu.mvp.myzhihu.view.fragment.BaseFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by CXG on 2016/6/28.
 */
public class ViewUtils {

    private static Map<String,BaseFragment> fragmentList = new HashMap<>();



    public static BaseFragment createFragment(Class<?> clazz){

        return createFragment(clazz,true);
    }

    public static BaseFragment createFragment(Class<?> clazz, boolean b) {
        BaseFragment resultFragment = null;
        String className = clazz.getName();
        if(fragmentList.containsKey(className)){
            resultFragment = fragmentList.get(className);
        }else {
            try {
                resultFragment = (BaseFragment) Class.forName(className).newInstance();

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (b){
                fragmentList.put(className,resultFragment);
            }
        }
        return resultFragment;
    }
}
