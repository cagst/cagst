package com.cagst.common.codevalue;

import com.cagst.common.CGTObject;
import org.apache.commons.lang3.builder.*;

/**
 * Represents a CodeSet within the system.
 *
 * @author Craig Gaskill
 * @version 1.0.0
 */
public final class CGTCodeSet extends CGTObject implements Comparable<CGTCodeSet> {
  private static final long serialVersionUID = -2275415503131860687L;

  private final long codeset_id;
  private final String codeset_display;
  private final String codeset_meaning;
  private final long updt_cnt;

  /**
   * Primary Constructor used to create an instance of CGTCodeSet from an existing
   * {@link CGTCodeSetBuilder}.
   *
   * @param builder
   *     The {@link CGTCodeSetBuilder} to base this CGTCodeSet off of.
   */
  /* package */CGTCodeSet(final CGTCodeSetBuilder builder) {
    super(builder);

    this.codeset_id = builder.getCodeSetUID();
    this.codeset_display = builder.getDisplay();
    this.codeset_meaning = builder.getMeaning();
    this.updt_cnt = builder.getCodeSetUpdateCount();
  }

  /**
   * Gets the unique identifier for the CodeSet;
   *
   * @return A {@link long} that uniquely identifies the CodeSet.
   */
  public long getCodeSetUID() {
    return this.codeset_id;
  }

  /**
   * Gets the display / name of the CodeSet.
   *
   * @return The {@link String} display / name of the CodeSet.
   */
  public String getDisplay() {
    return this.codeset_display;
  }

  /**
   * Gets the meaning of the CodeSet.
   *
   * @return A {@link String} that represents the meaning of the CodeSet.
   */
  public String getMeaning() {
    return this.codeset_meaning;
  }

  /**
   * Gets the number of times this object has been updated.
   *
   * @return {@link long} number of times the object has been updated.
   */
  public final long getCodeSetUpdateCount() {
    return updt_cnt;
  }

  /*
   * (non-Javadoc)
   *
   * @see com.cagst.common.domain.CGTObject#toString()
   */
  @Override
  public String toString() {
    ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
    builder.append("display", codeset_display);
    builder.append("meaning", codeset_meaning);
    builder.append("updateCount", updt_cnt);
    builder.appendSuper(super.toString());

    return builder.build();
  }

  @Override
  public int hashCode() {
    HashCodeBuilder builder = new HashCodeBuilder();
    builder.append(codeset_display);
    builder.append(codeset_meaning);

    return builder.build();
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof CGTCodeSet)) {
      return false;
    }

    CGTCodeSet rhs = (CGTCodeSet) obj;

    EqualsBuilder builder = new EqualsBuilder();
    builder.append(codeset_display, rhs.getDisplay());
    builder.append(codeset_meaning, rhs.getMeaning());

    return builder.build();
  }

  @Override
  public int compareTo(final CGTCodeSet rhs) {
    CompareToBuilder builder = new CompareToBuilder();
    builder.append(codeset_display, rhs.getDisplay());

    return builder.build();
  }
}
