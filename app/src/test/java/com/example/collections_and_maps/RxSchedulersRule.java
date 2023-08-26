package com.example.collections_and_maps;

import org.junit.rules.ExternalResource;

import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RxSchedulersRule extends ExternalResource {
    private Scheduler immediateScheduler;

    @Override
    protected void before() throws Throwable {
        super.before();
        immediateScheduler = Schedulers.trampoline();
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> immediateScheduler);
        RxJavaPlugins.setComputationSchedulerHandler(scheduler -> immediateScheduler);
        RxJavaPlugins.setNewThreadSchedulerHandler(scheduler -> immediateScheduler);
        RxJavaPlugins.setSingleSchedulerHandler(scheduler -> immediateScheduler);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> immediateScheduler);
    }

    @Override
    protected void after() {
        super.after();
        RxJavaPlugins.reset();
        RxAndroidPlugins.reset();
    }
}
