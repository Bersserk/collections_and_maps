package com.example.collections_and_maps.models.benchmarks.models.benchmarks;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.ResultItem;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ResultItemTest {

    @Test
    public void copy_ReturnsNewResultItemWithUpdatedTiming() {
        // Arrange
        ResultItem oldItem = new ResultItem(R.string.empty, R.string.add_begin, ResultItem.EMPTY, false);
        double newTiming = 10.0;

        // Act
        ResultItem newItem;
        try {
            Thread.sleep(3000);
            newItem = oldItem.copy(oldItem, newTiming);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Assert
        Assert.assertEquals(newTiming, newItem.timing, 0.0);
    }

    @Test
    public void isHeader_ReturnsTrueForEmptyHeaderText() {
        // Arrange
        ResultItem resultItem = new ResultItem(R.string.empty, R.string.add_begin, ResultItem.EMPTY, false);

        // Act
        boolean isHeader = resultItem.isHeader();

        // Assert
        Assert.assertTrue(isHeader);
    }

    @Test
    public void isHeader_ReturnsTrueForEmptyMethodName() {
        // Arrange
        ResultItem resultItem = new ResultItem(R.string.ArrayList, R.string.empty, ResultItem.EMPTY, false);

        // Act
        boolean isHeader = resultItem.isHeader();

        // Assert
        Assert.assertTrue(isHeader);
    }

    @Test
    public void isResult_ReturnsTrueForNonEmptyTiming() {
        // Arrange
        ResultItem resultItem = new ResultItem(R.string.ArrayList, R.string.add_begin, 10.0, false);

        // Act
        boolean isResult = resultItem.isResult();

        // Assert
        Assert.assertTrue(isResult);
    }

    @Test
    public void isResult_ReturnsFalseForEmptyTiming() {
        // Arrange
        ResultItem resultItem = new ResultItem(R.string.ArrayList, R.string.add_begin, ResultItem.EMPTY, false);

        // Act
        boolean isResult = resultItem.isResult();

        // Assert
        Assert.assertFalse(isResult);
    }
}
