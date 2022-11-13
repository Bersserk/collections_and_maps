package com.example.collections_and_maps.ui.benchmark;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.Item;
import com.example.collections_and_maps.models.benchmarks.MyHashMap;
import com.example.collections_and_maps.models.benchmarks.MyTreeMap;
import java.util.List;

public class MapsPagerFragment extends BaseFragment {

    @Override
    protected int getSpanCount() {
        return 2;
    }

    @Override
    protected List<Item> createTemplateList() {
        return super.createTemplateList(R.array.maps, R.array.maps_item);
    }

//    @Override
//    protected List<Item> testCreateTemplateList() {
//        return super.testCreateTemplateList(R.array.maps, R.array.maps_item);
//    }

//    @Override
//    protected List getResults(List <String> templateList, int sizeList) {
//        List <String>  resultList = new ArrayList<>(templateList);
//
//        MyHashMap hashMap = new MyHashMap(sizeList);
//        MyTreeMap treeMap = new MyTreeMap(sizeList);
//
//        for (int i = 0, y = 0; i < templateList.size(); i++) {
//            if (templateList.get(i).isEmpty()) {
//                beginNewThread(i++, hashMap, resultList, y);
//                beginNewThread(i++, treeMap, resultList, y);
//                y++;
//            }
//        }
//        return resultList;
//    }

    public void beginNewThread(int i, MyHashMap hashMap, List <String> resultList, int y) {
//        service.submit(new Runnable() {
//            public void run() {
                resultList.set(i, "");
                resultList.set(i, hashMap.myHashMap(y));
//            }
//        });
    }

    public void beginNewThread(int i, MyTreeMap treeMap, List <String> resultList, int y) {
//        service.submit(new Runnable() {
//            public void run() {
                resultList.set(i, "");
                resultList.set(i, treeMap.myTreeMap(y));
//            }
//        });
    }

}
