// Generated code from Butter Knife. Do not modify!
package com.example.collections_and_maps.ui.benchmark;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.collections_and_maps.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BaseFragment_ViewBinding implements Unbinder {
  private BaseFragment target;

  private View view7f080064;

  @UiThread
  public BaseFragment_ViewBinding(final BaseFragment target, View source) {
    this.target = target;

    View view;
    target.inputFiled = Utils.findRequiredViewAsType(source, R.id.inputField, "field 'inputFiled'", EditText.class);
    view = Utils.findRequiredView(source, R.id.calcButton, "method 'onClick'");
    view7f080064 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(Utils.castParam(p0, "doClick", 0, "onClick", 0, Button.class));
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    BaseFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.inputFiled = null;

    view7f080064.setOnClickListener(null);
    view7f080064 = null;
  }
}
