package com.example.collections_and_maps.ui.benchmark;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.RxSchedulersRule;
import com.example.collections_and_maps.models.benchmarks.Benchmark;
import com.example.collections_and_maps.models.benchmarks.ResultItem;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.hamcrest.MockitoHamcrest;
import org.mockito.internal.matchers.Matches;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.disposables.Disposable;

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

    private BenchmarkViewModel benchmarkViewModel;
    private String inputtedValue = "100000";

    @Before
    public void setUp() {
        benchmarkViewModel = new BenchmarkViewModel(benchmark);
        benchmarkViewModel.getItemsLiveData().observeForever(itemsObserver);
        benchmarkViewModel.getLiveTextTV().observeForever(liveTextTVObserver);
        benchmarkViewModel.getLiveShowerMessages().observeForever(liveShowerMessagesObserver);
    }

    private void verifyNoMore(Object mockObject) {
        Mockito.verifyNoMoreInteractions(mockObject);
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
    public void testStartMeasure_DisposableDisposed() {
        // Подготовка данных
        Disposable disposable = Mockito.mock(Disposable.class);
        lenient().when(disposable.isDisposed()).thenReturn(true);

        benchmarkViewModel.startMeasure(inputtedValue);

        verify(disposable, never()).dispose();
    }

    @Test
    public void testStartMeasure_DisposableNotDisposed() {
        // Подготовка данных
        Disposable disposable = Mockito.mock(Disposable.class);
        disposable.dispose();

        benchmarkViewModel.startMeasure(inputtedValue);

        verify(disposable).dispose();
    }

    @Test
    public void startMeasure_with_ValidInputValue_startsMeasurements() {
        ResultItem item = Mockito.mock(ResultItem.class);
        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
        int afterValidValue = 100000;
        List<ResultItem> expectedItems = new ArrayList<>();

        when(benchmark.getItemsList(true)).thenReturn(expectedItems);

        benchmarkViewModel.startMeasure(inputtedValue);

        verify(liveTextTVObserver, Mockito.times(2)).onChanged(captor.capture());
        verify(liveTextTVObserver).onChanged(ArgumentMatchers.eq(captor.getValue()));
        verify(benchmark).getItemsList(true);
        verify(benchmark, Mockito.times(expectedItems.size())).getMeasureTime(
                ArgumentMatchers.any(ResultItem.class),
                ArgumentMatchers.eq(afterValidValue)
        );
        verify(liveTextTVObserver).onChanged(ArgumentMatchers.eq(captor.getValue()));
        verifyNoMore(benchmark);
        verifyNoMore(liveTextTVObserver);
    }

    @Test
    public void startMeasure_withInvalidInputValue_doesNotStartMeasurements() {
        String inputtedValue = "invalid";

        benchmarkViewModel.startMeasure(inputtedValue);

        Mockito.verifyNoMoreInteractions(benchmark);
        verify(liveShowerMessagesObserver).onChanged(ArgumentMatchers.eq(R.string.empty_input_value));
        verify(benchmark, never()).getItemsList(true);
        verify(liveTextTVObserver, never()).onChanged(ArgumentMatchers.anyInt());
        verify(itemsObserver, never()).onChanged(ArgumentMatchers.any());
        verifyNoMore(liveShowerMessagesObserver);
        verifyNoMore(benchmark);
        verifyNoMore(liveTextTVObserver);
        verifyNoMore(itemsObserver);

    }

    @Test
    public void startMeasure_whenAlreadyRunning_disposesAndStopsMeasurements() {
        String inputtedValue = "10000";

        benchmarkViewModel.startMeasure(inputtedValue);

        verify(liveTextTVObserver).onChanged(ArgumentMatchers.eq(R.string.calcButtonStop));
        verify(liveTextTVObserver).onChanged(ArgumentMatchers.eq(R.string.calcButtonStart));
        verify(itemsObserver, Mockito.times(1)).onChanged(ArgumentMatchers.any());
        verifyNoMore(liveTextTVObserver);
        verifyNoMore(itemsObserver);
    }

    @Test
    public void getSpan_returnsBenchmarkSpan() {
        int expectedSpan = 3;
        when(benchmark.getSpan()).thenReturn(expectedSpan);

        int span = benchmarkViewModel.getSpan();

        Assert.assertEquals(expectedSpan, span);
    }

}
