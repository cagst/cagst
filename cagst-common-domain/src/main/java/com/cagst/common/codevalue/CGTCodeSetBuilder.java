package com.cagst.common.codevalue;

import com.cagst.common.CGTObjectBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Represents a CodeSet within the system.
 *
 * @author Craig Gaskill
 * @version 1.0.0
 */
public final class CGTCodeSetBuilder extends CGTObjectBuilder {
  private static final long serialVersionUID = 2888223786343786452L;

  private long codeset_id;
  private String codeset_display;
  private String codeset_meaning;
  private long updt_cnt = 0;

  private final CGTCodeSet originalCodeSet;

  /**
   * Primary Constructor used to create an instance of CGTCodeSetBuilder.
   */
  public CGTCodeSetBuilder() {
    this.originalCodeSet = null;
  }

  /**
   * Primary Constructor used to create an instance of CGTCodeSetBuilder from an existing instance
   * of {@link CGTCodeSet} .
   *
   * @param obj
   *     The {@link CGTCodeSet} to base this CGTObjectBuilder off of.
   */
  public CGTCodeSetBuilder(final CGTCodeSet codeset) {
    super(codeset);

    copyFrom(codeset);

    this.originalCodeSet = codeset;
  }

  /**
   * Gets the unique identifier for the CodeSet;
   *
   * @return A {@link long} that uniquely identifies the CodeSet.
   */
  public long getCodeSetUID() {
    return codeset_id;
  }

  /**
   * Sets the unique identifier for the CodeSet.
   *
   * @param codeSetUID
   *     A {@link long} that uniquely identifies the CodeSet.
   */
  public void setCodeSetUID(final long codeSetUID) {
    this.codeset_id = codeSetUID;
  }

  /**
   * Gets the display / name of the CodeSet.
   *
   * @return The {@link String} display / name of the CodeSet.
   */
  public String getDisplay() {
    return codeset_display;
  }

  /**
   * Sets the display / name of the CodeSet.
   *
   * @param display
   *     The {@link String} display / name for the CodeSet.
   */
  public void setDisplay(final String display) {
    this.codeset_display = display;
  }

  /**
   * Gets the meaning of the CodeSet.
   *
   * @return A {@link String} that represents the meaning of the CodeSet.
   */
  public String getMeaning() {
    return codeset_meaning;
  }

  /**
   * Sets the meaning for the CodeSet.
   *
   * @param meaning
   *     A {@link String} that represents the meaning of the CodeSet.
   */
  public void setMeaning(final String meaning) {
    this.codeset_meaning = meaning;
  }

  /**
   * Gets the number of times this object has been updated.
   *
   * @return {@link long} number of times the object has been updated.
   */
  public long getCodeSetUpdateCount() {
    return updt_cnt;
  }

  /**
   * Sets the number of times this object has been updated.
   *
   * @param updateCount
   *     {@link long} the number of times the object has been updated.
   */
  public void setCodeSetUpdateCount(final long updateCount) {
    this.updt_cnt = updateCount;
  }

  @Override
  public void rejectChanges() {
    if (originalCodeSet != null) {
      copyFrom(originalCodeSet);
    } else {
      this.codeset_display = null;
      this.codeset_meaning = null;
    }

    super.rejectChanges();
  }

  @Override
  public boolean hasChanged(final boolean deepCheck) {
    if (originalCodeSet == null) {
      return true;
    }

    EqualsBuilder builder = new EqualsBuilder();
    builder.append(codeset_display, originalCodeSet.getDisplay());
    builder.append(codeset_meaning, originalCodeSet.getMeaning());
    builder.append(isActive(), originalCodeSet.isActive());

    return !builder.build();
  }

  @Override
  public boolean isValid() {
    return !StringUtils.isEmpty(codeset_display);
  }

  @Override
  public String toString() {
    ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
    builder.append("display", codeset_display);
    builder.append("meaning", codeset_meaning);
    builder.append("updateCount", updt_cnt);
    builder.appendSuper(super.toString());

    return builder.build();
  }

  /**
   * Builds a {@link CGTCodeSet} based upon this builder.
   *
   * @return An immutable {@link CGTCodeSet}.
   */
  public CGTCodeSet build() {
    return new CGTCodeSet(this);
  }

  protected void copyFrom(final CGTCodeSet codeset) {
    this.codeset_id = codeset.getCodeSetUID();
    this.codeset_display = codeset.getDisplay();
    this.codeset_meaning = codeset.getMeaning();
    this.updt_cnt = codeset.getCodeSetUpdateCount();
    this.setActive(codeset.isActive());

    super.copyFrom(codeset);
  }
}
