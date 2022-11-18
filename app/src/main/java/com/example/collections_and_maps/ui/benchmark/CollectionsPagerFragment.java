package com.example.collections_and_maps.ui.benchmark;


import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.Item;

import java.util.List;

public class CollectionsPagerFragment extends BaseFragment {

    @Override
    protected int getSpanCount() {
        return 3;
    }

    @Override
    protected List<Item> createTemplateList() {
        return super.createTemplateList(R.array.collections, R.array.collections_item);
    }

//    @Override
//    protected List<Item> createTemplateList() {
//        return super.createTemplateList(R.array.collections, R.array.collections_item);
//    }

//    @Override
//    protected List getResults(List<String> templateList, int sizeList) {
//
//        List <String> resultList = new ArrayList<>(templateList);
//
//        for (int i = 0; i < templateList.size(); i++) {
//            if (templateList.get(i).isEmpty()) {
//                resultList.set(i, String.valueOf(i*100));
//            }
//        }
//        return resultList;
//    }


//    public void beginNewThread(int i, MyArrayList arrayList, List <String> resultList, int y) {
////        service.submit(new Runnable() {
////            @Override
////            public void run() {
//                resultList.set(i, "");
//                resultList.set(i, arrayList.myArrayList(y));
//                Log.i("exe", "run");
////            }
////        });
//    }

//    public void beginNewThread(int i, MyLinkedList linkedList, List <String> resultList, int y) {
////        service.submit(new Runnable() {
////            public void run() {
//                resultList.set(i, "");
//                resultList.set(i, linkedList.myLinkedList(y));
////            }
////        });
//    }

//    public void beginNewThread(int i, MyCopyOnWriteArrayList copyOnWriteArrayList, List <String> resultList, int y) {
////        service.submit(new Runnable() {
////            public void run() {
//                resultList.set(i, "");
//                resultList.set(i, copyOnWriteArrayList.myCopyOnWriteArrayList(y));
////            }
////        });
//    }
}

