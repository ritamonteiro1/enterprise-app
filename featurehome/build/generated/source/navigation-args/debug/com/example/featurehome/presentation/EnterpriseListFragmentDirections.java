package com.example.featurehome.presentation;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import com.example.datasource.model.enterprise.Enterprise;
import com.example.featurehome.R;
import java.io.Serializable;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class EnterpriseListFragmentDirections {
  private EnterpriseListFragmentDirections() {
  }

  @NonNull
  public static ActionEnterpriseListToEnterpriseDetails actionEnterpriseListToEnterpriseDetails(
      @NonNull Enterprise enterprise) {
    return new ActionEnterpriseListToEnterpriseDetails(enterprise);
  }

  public static class ActionEnterpriseListToEnterpriseDetails implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private ActionEnterpriseListToEnterpriseDetails(@NonNull Enterprise enterprise) {
      if (enterprise == null) {
        throw new IllegalArgumentException("Argument \"enterprise\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("enterprise", enterprise);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionEnterpriseListToEnterpriseDetails setEnterprise(@NonNull Enterprise enterprise) {
      if (enterprise == null) {
        throw new IllegalArgumentException("Argument \"enterprise\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("enterprise", enterprise);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
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

    @Override
    public int getActionId() {
      return R.id.action_enterpriseList_to_enterpriseDetails;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public Enterprise getEnterprise() {
      return (Enterprise) arguments.get("enterprise");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionEnterpriseListToEnterpriseDetails that = (ActionEnterpriseListToEnterpriseDetails) object;
      if (arguments.containsKey("enterprise") != that.arguments.containsKey("enterprise")) {
        return false;
      }
      if (getEnterprise() != null ? !getEnterprise().equals(that.getEnterprise()) : that.getEnterprise() != null) {
        return false;
      }
      if (getActionId() != that.getActionId()) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + (getEnterprise() != null ? getEnterprise().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionEnterpriseListToEnterpriseDetails(actionId=" + getActionId() + "){"
          + "enterprise=" + getEnterprise()
          + "}";
    }
  }
}
