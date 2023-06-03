package com.example.collections_and_maps.ui.benchmark;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import com.example.collections_and_maps.Pair;
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
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.TestObserver;
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

    @Captor
    private ArgumentCaptor<List<ResultItem>> itemsCaptor;


    private BenchmarkViewModel benchmarkViewModel;
    private String inputtedValue = "10";

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

        Disposable disposable = Mockito.mock(Disposable.class);
        ResultItem resultItem = Mockito.mock(ResultItem.class);
        TestScheduler testScheduler = new TestScheduler();
        List<ResultItem> expectedItems = new CollectionsBenchmark().getItemsList(true);

        Pair mockPair = Mockito.mock(Pair.class);
        ArgumentCaptor <Pair<Integer, ResultItem>> pairCaptor = ArgumentCaptor.forClass(Pair.class);

        when(benchmark.getItemsList(true)).thenReturn(expectedItems);

        benchmarkViewModel.startMeasure(inputtedValue);

        verify(liveTextTVObserver).onChanged(R.string.calcButtonStop);
        verify(benchmark).getItemsList(true);
        verify(itemsObserver).onChanged(new ArrayList<>(expectedItems));

        disposable = Observable.fromIterable(expectedItems)
                .flatMap(it -> Observable.fromCallable(() ->
                        Pair.create(pairCaptor.capture().first, pairCaptor.capture().second)))
                .subscribeOn(testScheduler)
                .observeOn(testScheduler)
                .subscribe(pair -> {
                    expectedItems.set(pair.first, pair.second);
                });

        benchmarkViewModel.startMeasure(inputtedValue);

        verify(liveTextTVObserver, times(2)).onChanged(R.string.calcButtonStart);
        verify(liveTextTVObserver, times(2)).onChanged(R.string.calcButtonStart);
    }

    @Test
    public void startMeasure_withValidInputValue_secondTimes (){
        Disposable disposable = Mockito.mock(Disposable.class);
        TestScheduler testScheduler = new TestScheduler();
        List<ResultItem> expectedItems = new CollectionsBenchmark().getItemsList(true);


        when(disposable.isDisposed()).thenReturn(false);

        benchmarkViewModel.startMeasure(inputtedValue);

        TestObserver<ResultItem> testObserver = Observable.fromIterable(expectedItems)
                .subscribeOn(testScheduler)
                .observeOn(testScheduler)
                .delay(1, TimeUnit.SECONDS)
                .test();

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

}
