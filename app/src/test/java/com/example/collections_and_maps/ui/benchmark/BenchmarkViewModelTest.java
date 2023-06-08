package com.example.collections_and_maps.ui.benchmark;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import android.util.Log;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.example.collections_and_maps.Pair;
import com.example.collections_and_maps.R;
import com.example.collections_and_maps.RxSchedulersRule;
import com.example.collections_and_maps.models.benchmarks.Benchmark;
import com.example.collections_and_maps.models.benchmarks.ResultItem;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.TestObserver;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.schedulers.TestScheduler;


@RunWith(MockitoJUnitRunner.class)
public class BenchmarkViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Rule
    public RxSchedulersRule rxSchedulersRule = new RxSchedulersRule();
    @Mock
    public Benchmark benchmark;

    @Mock
    private Observer<List<ResultItem>> itemsObserver;

    @Mock
    private Observer<Integer> liveTextTVObserver;

    @Mock
    private Observer<Integer> liveShowerMessagesObserver;

    @Mock
    private Disposable disposable;

    @Captor
    private ArgumentCaptor<List<ResultItem>> itemsCaptor;

    @Mock
    Log log = Mockito.mock(Log.class);


    private BenchmarkViewModel benchmarkViewModel;
    private String inputtedValue = "10";

    @Before
    public void setUp() {
        benchmarkViewModel = new BenchmarkViewModel(benchmark);
        benchmarkViewModel.getItemsLiveData().observeForever(itemsObserver);
        benchmarkViewModel.getLiveTextTV().observeForever(liveTextTVObserver);
        benchmarkViewModel.getLiveShowerMessages().observeForever(liveShowerMessagesObserver);
    }

    @After
    public void cleanup() {
        // Восстановление исходных планировщиков после теста
        RxJavaPlugins.reset();
    }

    private void verifyNoMore(Object mockObject) {
        verifyNoMoreInteractions(mockObject);
    }

    @Test
    public void onCreate_setsItemsLiveData() {
        List<ResultItem> expectedItems = new ArrayList<>();
        when(benchmark.getItemsList(false)).thenReturn(expectedItems);

        benchmarkViewModel.onCreate();

        verify(itemsObserver).onChanged(expectedItems);
        verifyNoMore(itemsObserver);
    }



    @Test
    public void startMeasure_with_ValidInputValue_startsMeasurements() {

        ResultItem resultItem = Mockito.mock(ResultItem.class);
//        TestScheduler testScheduler = new TestScheduler();
//        List<ResultItem> expectedItems = new CollectionsBenchmark().getItemsList(true);
        List<ResultItem> expectedItems = new ArrayList<>();
        expectedItems.add(resultItem);

        ArgumentCaptor <Pair<Integer, ResultItem>> pairCaptor = ArgumentCaptor.forClass(Pair.class);

        when(benchmark.getItemsList(true)).thenReturn(expectedItems);

        benchmarkViewModel.startMeasure(inputtedValue);

        verify(liveTextTVObserver).onChanged(R.string.calcButtonStop);
        verify(benchmark).getItemsList(true);
        verify(itemsObserver).onChanged(new ArrayList<>(expectedItems));

//        Observable.fromIterable(expectedItems)
//                .flatMap(it -> Observable.fromCallable(() ->
//                        Pair.create(pairCaptor.capture().first, pairCaptor.capture().second)))
//                .subscribeOn(testScheduler)
//                .observeOn(testScheduler)
//                .subscribe(pair -> {
//                    expectedItems.set(pair.first, pair.second);
//                });


        verify(liveTextTVObserver).onChanged(R.string.calcButtonStart);
    }

//    @Test
//    public void startMeasure_withValidInputValue_secondTimes (){
//        disposable = Mockito.mock(Disposable.class);
//
//        TestScheduler testScheduler = new TestScheduler();
////        List<ResultItem> items = Mockito.mock(List.class);
//        List<ResultItem> items = new CollectionsBenchmark().getItemsList(true);
//
//        ArgumentCaptor <Pair<Integer, ResultItem>> pairCaptor = ArgumentCaptor.forClass(Pair.class);
//
//        when(benchmark.getItemsList(true)).thenReturn(items);
//
//        when(disposable.isDisposed()).thenReturn(false);
//        when(disposable.isDisposed()).thenReturn(false);
//
//        benchmarkViewModel.startMeasure(inputtedValue);
//
//        Observable.fromIterable(items)
//                .flatMap(it -> Observable.fromCallable(() -> {
//                    // Задержка на 2 секунды
//
//                    double d = benchmark.getMeasureTime(it, 1000);
//                    int i = 0;
//                    Thread.sleep(5000);
//                    i = 1;
//                    return Pair.create(pairCaptor.capture().first, pairCaptor.capture().second.copy(it, d));
//                }))
//                .subscribeOn(testScheduler)
//                .observeOn(testScheduler)
//                .test();
//
//        benchmarkViewModel.startMeasure(inputtedValue);
//
//        verify(this.disposable).dispose();
//
//    }

    @Test
    public void startMeasure_withValidInputValue_secondTimes() throws InterruptedException {

        RxJavaPlugins.setComputationSchedulerHandler(scheduler -> Schedulers.newThread());

        List<ResultItem> expectedItems = new ArrayList<>();
        expectedItems.add(new ResultItem(R.string.ArrayList, R.string.empty, 20.0, false));
        expectedItems.add(new ResultItem(R.string.empty, R.string.empty, 20.0, false));

        disposable = Mockito.mock(Disposable.class);

        when(benchmark.getItemsList(true)).thenReturn(expectedItems);
        when(disposable.isDisposed()).thenReturn(false);

        when(benchmark.getMeasureTime(any(ResultItem.class), anyInt())).thenAnswer(invocation -> {

            try {
                String logMeasure =  "Current thread [test measure]: " + Thread.currentThread().getName();

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
            return anyDouble();
        });

        benchmarkViewModel.startMeasure(inputtedValue);

            try {
                String logText = "Current thread [delay beetwen the both calling]: " + Thread.currentThread().getName();

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        benchmarkViewModel.startMeasure(inputtedValue);
        verify(disposable).dispose();
    }



    @Test
    public void startMeasure_withInvalidInputValue_doesNotStartMeasurements() {
        String inputtedValue = "invalid";

        benchmarkViewModel.startMeasure(inputtedValue);

        verifyNoMoreInteractions(benchmark);
        verify(liveShowerMessagesObserver).onChanged(ArgumentMatchers.eq(R.string.empty_input_value));
        verify(benchmark, never()).getItemsList(true);
        verify(liveTextTVObserver, never()).onChanged(ArgumentMatchers.anyInt());
        verify(itemsObserver, never()).onChanged(any());
        verifyNoMore(liveShowerMessagesObserver);
        verifyNoMore(benchmark);
        verifyNoMore(liveTextTVObserver);
        verifyNoMore(itemsObserver);

    }

    @Test
    public void getSpan_returnsBenchmarkSpan() {
        int expectedSpan = 3;
        when(benchmark.getSpan()).thenReturn(expectedSpan);

        int span = benchmarkViewModel.getSpan();

        assertEquals(expectedSpan, span);
    }


//

    public int getIndex(ResultItem it, List<ResultItem> items) {
        int index = items.indexOf(it);
//        Log.i(TAG, "nidex item = " + index);
        return index;
    }


    public ResultItem getNewItem(ResultItem it, int value) {
//        Log.i(TAG, "OldItem = " + it);
        ResultItem newItem = it.copy(it, benchmark.getMeasureTime(it, value));
//        Log.i(TAG, "NewItem = " + newItem);
        return newItem;
    }


    public void putIndex(androidx.core.util.Pair<Integer, ResultItem> pair, List<ResultItem> items) {
//        Log.i(TAG, "index newItem = " + pair.first);
//        Log.i(TAG, "newItem = " + pair.second);
        items.set(pair.first, pair.second);
    }

//    private void putNewListItems(List <ResultItem> items) {
//        List <ResultItem> newItems = new ArrayList<>(items);
////        Log.i(TAG, "newListItems = " + newItems);
//        itemsLiveData.setValue(newItems);
//    }

}
