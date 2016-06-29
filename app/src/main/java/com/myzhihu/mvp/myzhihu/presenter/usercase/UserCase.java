package com.myzhihu.mvp.myzhihu.presenter.usercase;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by CXG on 2016/6/27.
 */
public abstract class UserCase<T,R> {

    private Subscription subscription= Subscriptions.empty();

    public void subscribe(Observer<T> UserCaseSubscribe,R params){
        UserCase.this.subscription = this.interactor(params)
                .onBackpressureBuffer()////当Observable发出的元素的速度比订阅者消化得要快，在这些元素被发出之前，会首先将这些元素无限制地缓存起来
                .take(1)//发出源Observable前1个元素
                .filter(new Func1<T, Boolean>() {
                    @Override
                    public Boolean call(T t) {
                        return !subscription.isUnsubscribed();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io()) //请求结束后，会close http
                .subscribe(UserCaseSubscribe);


    }

    public void unSubscribe(){
        if(subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }

    protected abstract Observable<T> interactor(R params);
}
