package com.example.featurehome.presentation;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavArgs;
import com.example.datasource.model.enterprise.Enterprise;
import java.io.Serializable;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class EnterpriseDetailsFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private EnterpriseDetailsFragmentArgs() {
  }

  @SuppressWarnings("unchecked")
  private EnterpriseDetailsFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static EnterpriseDetailsFragmentArgs fromBundle(@NonNull Bundle bundle) {
    EnterpriseDetailsFragmentArgs __result = new EnterpriseDetailsFragmentArgs();
    bundle.setClassLoader(EnterpriseDetailsFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("enterprise")) {
      Enterprise enterprise;
      if (Parcelable.class.isAssignableFrom(Enterprise.class) || Serializable.class.isAssignableFrom(Enterprise.class)) {
        enterprise = (Enterprise) bundle.get("enterprise");
      } else {
        throw new UnsupportedOperationException(Enterprise.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
      }
      if (enterprise == null) {
        throw new IllegalArgumentException("Argument \"enterprise\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("enterprise", enterprise);
    } else {
      throw new IllegalArgumentException("Required argument \"enterprise\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static EnterpriseDetailsFragmentArgs fromSavedStateHandle(
      @NonNull SavedStateHandle savedStateHandle) {
    EnterpriseDetailsFragmentArgs __result = new EnterpriseDetailsFragmentArgs();
    if (savedStateHandle.contains("enterprise")) {
      Enterprise enterprise;
      enterprise = savedStateHandle.get("enterprise");
      if (enterprise == null) {
        throw new IllegalArgumentException("Argument \"enterprise\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("enterprise", enterprise);
    } else {
      throw new IllegalArgumentException("Required argument \"enterprise\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Enterprise getEnterprise() {
    return (Enterprise) arguments.get("enterprise");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("enterprise")) {
      Enterprise enterprise = (Enterprise) arguments.get("enterprise");
      if (Parcelable.class.isAssignableFrom(Enterprise.class) || enterprise == null) {
        __result.putParcelable("enterprise", Parcelable.class.cast(enterprise));
      } else if (Serializable.class.isAssignableFrom(Enterprise.class)) {
        __result.putSerializable("enterprise", Serializable.class.cast(enterprise));
      } else {
        throw new UnsupportedOperationException(Enterprise.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
      }
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public SavedStateHandle toSavedStateHandle() {
    SavedStateHandle __result = new SavedStateHandle();
    if (arguments.containsKey("enterprise")) {
      Enterprise enterprise = (Enterprise) arguments.get("enterprise");
      if (Parcelable.class.isAssignableFrom(Enterprise.class) || enterprise == null) {
        __result.set("enterprise", Parcelable.class.cast(enterprise));
      } else if (Serializable.class.isAssignableFrom(Enterprise.class)) {
        __result.set("enterprise", Serializable.class.cast(enterprise));
      } else {
        throw new UnsupportedOperationException(Enterprise.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
      }
    }
    return __result;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
        return true;
    }
    if (object == null || getClass() != object.getClass()) {
        return false;
    }
    EnterpriseDetailsFragmentArgs that = (EnterpriseDetailsFragmentArgs) object;
    if (arguments.containsKey("enterprise") != that.arguments.containsKey("enterprise")) {
      return false;
    }
    if (getEnterprise() != null ? !getEnterprise().equals(that.getEnterprise()) : that.getEnterprise() != null) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + (getEnterprise() != null ? getEnterprise().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "EnterpriseDetailsFragmentArgs{"
        + "enterprise=" + getEnterprise()
        + "}";
  }

  public static final class Builder {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    public Builder(@NonNull EnterpriseDetailsFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    @SuppressWarnings("unchecked")
    public Builder(@NonNull Enterprise enterprise) {
      if (enterprise == null) {
        throw new IllegalArgumentException("Argument \"enterprise\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("enterprise", enterprise);
    }

    @NonNull
    public EnterpriseDetailsFragmentArgs build() {
      EnterpriseDetailsFragmentArgs result = new EnterpriseDetailsFragmentArgs(arguments);
      return result;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setEnterprise(@NonNull Enterprise enterprise) {
      if (enterprise == null) {
        throw new IllegalArgumentException("Argument \"enterprise\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("enterprise", enterprise);
      return this;
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    @NonNull
    public Enterprise getEnterprise() {
      return (Enterprise) arguments.get("enterprise");
    }
  }
}
