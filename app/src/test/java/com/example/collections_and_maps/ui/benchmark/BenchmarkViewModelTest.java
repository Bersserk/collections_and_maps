package com.example.collections_and_maps.ui.benchmark;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.RxSchedulersRule;
import com.example.collections_and_maps.models.benchmarks.Benchmark;
import com.example.collections_and_maps.models.benchmarks.CollectionsBenchmark;
import com.example.collections_and_maps.models.benchmarks.ResultItem;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.schedulers.Schedulers;


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

    private BenchmarkViewModel benchmarkViewModel;
    private final String inputtedValue = "10";

    @Before
    public void setUp() {
        benchmarkViewModel = new BenchmarkViewModel(benchmark);
        benchmarkViewModel.getItemsLiveData().observeForever(itemsObserver);
        benchmarkViewModel.getLiveTextTV().observeForever(liveTextTVObserver);
        benchmarkViewModel.getLiveShowerMessages().observeForever(liveShowerMessagesObserver);
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
        List<ResultItem> expectedItems = new CollectionsBenchmark().getItemsList(false);
        when(benchmark.getItemsList(true)).thenReturn(expectedItems);

        benchmarkViewModel.startMeasure(inputtedValue);

        verify(liveTextTVObserver).onChanged(R.string.calcButtonStop);
        verify(benchmark).getItemsList(true);
        verify(itemsObserver).onChanged(new ArrayList<>(expectedItems));
    }

    @Test
    public void startMeasure_withValidInputValue_secondTimes() {
        RxJavaPlugins.setComputationSchedulerHandler(scheduler ->
                Schedulers.from(new Executor() {
                    @Override
                    public void execute(Runnable command) {
                        Schedulers.single();
                    }
                }, true));

        List<ResultItem> expectedItems = new CollectionsBenchmark().getItemsList(false);
        when(benchmark.getItemsList(true)).thenReturn(expectedItems);

        benchmarkViewModel.startMeasure(inputtedValue);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        benchmarkViewModel.startMeasure(inputtedValue);
    }

    @Test
    public void startMeasure_withInvalidInputValue_doesNotStartMeasurements() {
        benchmarkViewModel.startMeasure("invalid");

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

}
