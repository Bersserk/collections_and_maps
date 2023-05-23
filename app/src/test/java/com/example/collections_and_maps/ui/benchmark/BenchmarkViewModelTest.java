package com.example.collections_and_maps.ui.benchmark;

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
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

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
        Mockito.when(benchmark.getItemsList(false)).thenReturn(expectedItems);

        benchmarkViewModel.onCreate();

        Mockito.verify(itemsObserver).onChanged(expectedItems);
        verifyNoMore(itemsObserver);
    }

    @Test
    public void startMeasure_withValidInputValue_startsMeasurements() {
        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
        String inputtedValue = "100000";
        int parsedValue = 10;
        List<ResultItem> expectedItems = new ArrayList<>();
        Mockito.when(benchmark.getItemsList(true)).thenReturn(expectedItems);

        benchmarkViewModel.startMeasure(inputtedValue);

        Mockito.verify(liveTextTVObserver, Mockito.times(2)).onChanged(captor.capture());
        Mockito.verify(liveTextTVObserver).onChanged(ArgumentMatchers.eq(captor.getValue()));
        Mockito.verify(benchmark).getItemsList(true);
        Mockito.verify(benchmark, Mockito.times(expectedItems.size())).getMeasureTime(
                ArgumentMatchers.any(ResultItem.class),
                ArgumentMatchers.eq(parsedValue)
        );
        Mockito.verify(liveTextTVObserver).onChanged(ArgumentMatchers.eq(captor.getValue()));
        verifyNoMore(benchmark);
        verifyNoMore(liveTextTVObserver);
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
        verifyNoMore(liveShowerMessagesObserver);
        verifyNoMore(benchmark);
        verifyNoMore(liveTextTVObserver);
        verifyNoMore(itemsObserver);

    }

    @Test
    public void startMeasure_whenAlreadyRunning_disposesAndStopsMeasurements() {
        String inputtedValue = "10000";

        benchmarkViewModel.startMeasure(inputtedValue);

        Mockito.verify(liveTextTVObserver).onChanged(ArgumentMatchers.eq(R.string.calcButtonStop));
        Mockito.verify(liveTextTVObserver).onChanged(ArgumentMatchers.eq(R.string.calcButtonStart));
        Mockito.verify(itemsObserver, Mockito.times(1)).onChanged(ArgumentMatchers.any());
        verifyNoMore(liveTextTVObserver);
        verifyNoMore(itemsObserver);
    }

    @Test
    public void getSpan_returnsBenchmarkSpan() {
        int expectedSpan = 3;
        Mockito.when(benchmark.getSpan()).thenReturn(expectedSpan);

        int span = benchmarkViewModel.getSpan();

        Assert.assertEquals(expectedSpan, span);
    }

}
