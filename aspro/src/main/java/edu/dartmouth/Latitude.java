package edu.dartmouth;

/* JSkyCalc.java -- copyright 2007, John Thorstensen, Dartmouth College. */
/** TERMS OF USE -- Anyone is free to use this software for any purpose, and to
modify it for their own purposes, provided that credit is given to the author
in a prominent place.  For the present program that means that the green
title and author banner appearing on the main window must not be removed,
and may not be altered without premission of the author. */
public final class Latitude extends DEC implements Cloneable {

  /** Latitude, basically the same structure as a DEC.  */
  // overloaded constructors are simply wrappers
  public Latitude(final double inval) {
    super(inval);
  }

  public Latitude(final String s) {
    super(s);
  }

  @Override
  public Latitude clone() {
    return (Latitude) super.clone();
  }
}
