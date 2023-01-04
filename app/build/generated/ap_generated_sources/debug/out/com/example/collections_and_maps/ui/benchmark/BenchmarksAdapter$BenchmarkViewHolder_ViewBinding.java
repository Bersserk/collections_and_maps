// Generated code from Butter Knife. Do not modify!
package com.example.collections_and_maps.ui.benchmark;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.collections_and_maps.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BenchmarksAdapter$BenchmarkViewHolder_ViewBinding implements Unbinder {
  private BenchmarksAdapter.BenchmarkViewHolder target;

  @UiThread
  public BenchmarksAdapter$BenchmarkViewHolder_ViewBinding(
      BenchmarksAdapter.BenchmarkViewHolder target, View source) {
    this.target = target;

    target.nameView = Utils.findOptionalViewAsType(source, R.id.nameViewList, "field 'nameView'", TextView.class);
    target.progressBar = Utils.findOptionalViewAsType(source, R.id.progressBar, "field 'progressBar'", ProgressBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BenchmarksAdapter.BenchmarkViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.nameView = null;
    target.progressBar = null;
  }
}
