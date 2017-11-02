package com.arasu.vt.backgroundsapp.base;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by kyros on 02-11-2017.
 */

public abstract class BasePresenter implements Presenter {
    private CompositeSubscription mCompositeSubscription;
    @Override
    public void onCreate() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {
    configureSubscription();
    }

    private CompositeSubscription configureSubscription() {
        if(mCompositeSubscription==null || mCompositeSubscription.isUnsubscribed()){
            mCompositeSubscription=new CompositeSubscription();
        }
        return mCompositeSubscription;
    }

    @Override
    public void onDestroy() {
        unSubscribeAll();
    }

    protected void unSubscribeAll() {
        if(mCompositeSubscription!=null){
            mCompositeSubscription.unsubscribe();
            mCompositeSubscription.clear();
            //mCompositeSubscription=null;
        }
    }
    protected  <P> void subcribe(Observable<P> observable, Observer<P>observer){
        Subscription subscription= observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.computation())
                .subscribe(observer);
        configureSubscription().add(subscription);
    }
}
