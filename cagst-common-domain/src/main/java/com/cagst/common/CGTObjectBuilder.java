package com.cagst.common;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joda.time.DateTime;

/**
 * Abstract mutable class that provides the common functionality for all domain objects.
 *
 * @author Craig Gaskill
 * @version 1.0.0
 */
public abstract class CGTObjectBuilder implements Serializable {
  private static final long serialVersionUID = 734539234387356066L;

  private boolean active_ind = true;
  private DateTime updt_dt_tm = null;

  private boolean editing;
  private CGTObject originalCGTObject;

  /**
   * Primary Constructor used to create an instance of CGTObjectBuilder.
   */
  public CGTObjectBuilder() {
  }

  /**
   * Primary Constructor used to create an instance of CGTObjectBuilder from an existing instance of
   * {@link CGTObject}.
   *
   * @param obj
   *     The {@link CGTObject} to base this CGTObjectBuilder off of.
   */
  public CGTObjectBuilder(final CGTObject obj) {
    copyFrom(obj);

    this.originalCGTObject = obj;
  }

  /**
   * Gets the active status of the object.
   *
   * @return {@link boolean} <code>true</code> if the object is active, <code>false</code>
   * otherwise.
   */
  public boolean isActive() {
    return active_ind;
  }

  /**
   * Sets the active status of the object.
   *
   * @param active
   *     {@link boolean} <code>true</code> to make the object active, <code>false</code> to
   *     make the object inactive.
   */
  public void setActive(final boolean active) {
    this.active_ind = active;
  }

  /**
   * Gets the date this object was last updated.
   *
   * @return {@link DateTime} the object was last updated.
   */
  public DateTime getUpdateDate() {
    return updt_dt_tm;
  }

  /**
   * Sets the date that this object was last updated.
   *
   * @param updateDate
   *     {@link DateTime} the object was last updated.
   */
  public void setUpdateDate(final DateTime updateDate) {
    this.updt_dt_tm = updateDate;
  }

  /**
   * Returns whether the object has been marked for editing.
   *
   * @return <code>true</code> if the object is currently marked as being edited, <code>false</code>
   * otherwise.
   */
  public boolean isEditing() {
    return editing;
  }

  /**
   * Set the editing status of the object.
   *
   * @param editing
   *     The new editing status of the object.
   */
  public void setEditing(final boolean editing) {
    this.editing = editing;
  }

  /**
   * Accept any changes made and mark the object as no longer being edited.
   */
  public void acceptChanges() {
    this.editing = false;
  }

  /**
   * Reject any changes made, revert the changes back, and mark the object as no longer being
   * edited.
   */
  public void rejectChanges() {
    if (originalCGTObject != null) {
      copyFrom(originalCGTObject);
    } else {
      this.active_ind = true;
    }

    this.editing = false;
  }

  /**
   * Performs a deep check of the object hierarchy to see if it or any of its ancestors have
   * changed.
   *
   * @return <code>true</code> if the object has been changed since it was first created.
   */
  public boolean hasChanged() {
    return hasChanged(true);
  }

  /**
   * Performs a deep check or a shallow check of the object and its object hierarchy to see if it or
   * any of its ancestors have changed.
   *
   * @param deepCheck
   *     <code>true</code> to perform a deep check of the object hierarchy for any changes,
   *     <code>false</code> to perform a shallow check for changes to this objects properties
   *     only.
   *
   * @return <code>true</code> if the object has been changed since it was first created.
   */
  public abstract boolean hasChanged(final boolean deepCheck);

  /**
   * @return <code>true</code> if the object is valid, <code>false</code> otherwise.
   */
  public abstract boolean isValid();

  @Override
  public String toString() {
    ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);

    builder.append("active", active_ind);
    builder.append("updateDate", updt_dt_tm);

    return builder.build();
  }

  protected void copyFrom(final CGTObject obj) {
    this.active_ind = obj.isActive();
    this.updt_dt_tm = obj.getUpdateDate();
  }
}
