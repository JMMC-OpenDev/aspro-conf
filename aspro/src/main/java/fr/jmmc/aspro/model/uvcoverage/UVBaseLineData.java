/*******************************************************************************
 * JMMC project ( http://www.jmmc.fr ) - Copyright (C) CNRS.
 ******************************************************************************/
package fr.jmmc.aspro.model.uvcoverage;

/**
 * This class contains uv coordinates related to a baseline used by the UVCoverageData class
 * @author bourgesl
 */
public class UVBaseLineData {

  /** baseline name */
  private final String name;
  /** number of uv points */
  private int nPoints = 0;
  /** u coordinates */
  private double[] u = null;
  /** v coordinates */
  private double[] v = null;

  /**
   * Constructor
   * @param name baseline name
   */
  public UVBaseLineData(final String name) {
    this.name = name;
  }

  public final String getName() {
    return name;
  }

  public final int getNPoints() {
    return nPoints;
  }

  public final void setNPoints(final int nPoints) {
    this.nPoints = nPoints;
  }

  public final double[] getU() {
    return u;
  }

  public final void setU(final double[] u) {
    this.u = u;
  }

  public final double[] getV() {
    return v;
  }

  public final void setV(final double[] v) {
    this.v = v;
  }
}