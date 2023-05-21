package com.example.collections_and_maps.ui.benchmark;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.Benchmark;
import com.example.collections_and_maps.models.benchmarks.ResultItem;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@RunWith(MockitoJUnitRunner.class)
public class BenchmarkViewModelTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    public Benchmark benchmark;

    @Mock
    private Disposable disposable = Disposable.disposed();

    @Mock
    private Observer<List<ResultItem>> itemsObserver;

    @Mock
    private Observer<Integer> liveTextTVObserver;

    @Mock
    private Observer<Integer> liveShowerMessagesObserver;


    private BenchmarkViewModel benchmarkViewModel;

    @Before
    public void setUp() {
        Scheduler immediateScheduler = Schedulers.trampoline();
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> immediateScheduler);

        benchmarkViewModel = new BenchmarkViewModel(benchmark);
        benchmarkViewModel.getItemsLiveData().observeForever(itemsObserver);
        benchmarkViewModel.getLiveTextTV().observeForever(liveTextTVObserver);
        benchmarkViewModel.getLiveShowerMessages().observeForever(liveShowerMessagesObserver);
    }

    @Test
    public void onCreate_setsItemsLiveData() {
        List<ResultItem> expectedItems = new ArrayList<>();
        Mockito.when(benchmark.getItemsList(false)).thenReturn(expectedItems);

        benchmarkViewModel.onCreate();

        Mockito.verify(itemsObserver).onChanged(expectedItems);
    }

    @Test
    public void startMeasure_withValidInputValue_startsMeasurements() {
        String inputtedValue = "100000";
        int parsedValue = 10;
        List<ResultItem> expectedItems = new ArrayList<>();
        Mockito.when(benchmark.getItemsList(true)).thenReturn(expectedItems);

        benchmarkViewModel.startMeasure(inputtedValue);

        Mockito.verify(liveTextTVObserver).onChanged(ArgumentMatchers.eq(R.string.calcButtonStop));
        Mockito.verify(benchmark).getItemsList(true);
        Mockito.verify(benchmark, Mockito.times(expectedItems.size())).getMeasureTime(
                ArgumentMatchers.any(ResultItem.class),
                ArgumentMatchers.eq(parsedValue)
        );
        Mockito.verify(liveTextTVObserver).onChanged(ArgumentMatchers.eq(R.string.calcButtonStart));
    }

    @Test
    public void startMeasure_withInvalidInputValue_doesNotStartMeasurements() {
        String inputtedValue = "invalid";

        benchmarkViewModel.startMeasure(inputtedValue);

        Mockito.verifyNoMoreInteractions(benchmark);
        Mockito.verify(liveShowerMessagesObserver).onChanged(ArgumentMatchers.eq(R.string.empty_input_value));
        Mockito.verify(benchmark, Mockito.never()).getItemsList(true);
        Mockito.verify(liveTextTVObserver, Mockito.never()).onChanged(ArgumentMatchers.anyInt());
        Mockito.verify(itemsObserver, Mockito.never()).onChanged(ArgumentMatchers.any());
    }

    @Test
    public void startMeasure_whenAlreadyRunning_disposesAndStopsMeasurements() {
        String inputtedValue = "10000";

        benchmarkViewModel.startMeasure(inputtedValue);

        Mockito.verify(liveTextTVObserver).onChanged(ArgumentMatchers.eq(R.string.calcButtonStop));
        Mockito.verify(liveTextTVObserver).onChanged(ArgumentMatchers.eq(R.string.calcButtonStart));
        Mockito.verify(itemsObserver, Mockito.times(1)).onChanged(ArgumentMatchers.any());
    }

    @Test
    public void getSpan_returnsBenchmarkSpan() {
        int expectedSpan = 3;
        Mockito.when(benchmark.getSpan()).thenReturn(expectedSpan);

        int span = benchmarkViewModel.getSpan();

        Assert.assertEquals(expectedSpan, span);
    }

}
