/*******************************************************************************
 * JMMC project ( http://www.jmmc.fr ) - Copyright (C) CNRS.
 ******************************************************************************/
package fr.jmmc.aspro;

import fr.jmmc.aspro.model.TimeRef;
import fr.jmmc.jmal.image.ColorModels;
import fr.jmmc.jmcs.util.NumberUtils;
import java.util.Arrays;
import java.util.Vector;

/**
 * This class gathers main constant values
 * @author bourgesl
 */
public interface AsproConstants {

    // TODO: reuse OIFitsExplorer-Core or JMCS Constants
    /** Warning Logger */
    /** jmmc status log */
    public final static String ASPRO_WARNING_LOG = "fr.jmmc.aspro.warning";
    /* JMMC applications */
    /** SearchCal samp.name keyword */
    public final static String SEARCHCAL_SAMP_NAME = "SearchCal";

    /* ASTRO constants */
    /** EPOCH J2000 */
    public final static float EPOCH_J2000 = 2000.f;
    /** fixed earth radius constant from ASPRO Fortran code */
    public final static double EARTH_RADIUS = 6367435d;
    /** micrometres to meter */
    public final static double MICRO_METER = 1e-6;

    /* Hour angle ranges */
    /** minimum decimal hour angle = -12h */
    public final static double HA_MIN = -12D;
    /** maximum decimal hour angle = +12h */
    public final static double HA_MAX = 12D;

    /* UI Defaults */
    /** default minimum elevation = 45 degrees */
    public static final double DEFAULT_MIN_ELEVATION = 45d;
    /** default maximum elevation = 85 degrees */
    public static final double DEFAULT_MAX_ELEVATION = 85d;
    /** default observation duration per calibrated point = 300 seconds i.e. 5 minutes */
    public static final double DEFAULT_OBSERVATION_DURATION = 300d;
    /** default value for checkbox Night Limit = true */
    public static final boolean DEFAULT_USE_NIGHT_LIMITS = true;
    /** default image size */
    public final static Integer DEFAULT_IMAGE_SIZE = NumberUtils.valueOf(512);
    /** image size choices */
    public final static Vector<Integer> IMAGE_SIZES = new Vector<Integer>(Arrays.asList(
            new Integer[]{
                NumberUtils.valueOf(256),
                NumberUtils.valueOf(384),
                DEFAULT_IMAGE_SIZE,
                NumberUtils.valueOf(768),
                NumberUtils.valueOf(1024),
                NumberUtils.valueOf(1536),
                NumberUtils.valueOf(2048)}));
    /** default super sampling = 3 */
    public final static Integer DEFAULT_SUPER_SAMPLING = NumberUtils.valueOf(3);
    /** supersampling choices */
    public final static Vector<Integer> SUPER_SAMPLING = new Vector<Integer>(Arrays.asList(
            new Integer[]{
                NumberUtils.valueOf(1),
                DEFAULT_SUPER_SAMPLING,
                NumberUtils.valueOf(5),
                NumberUtils.valueOf(9),
                NumberUtils.valueOf(15),
                NumberUtils.valueOf(25)}));
    /** no value for combo boxes */
    public static final String NONE = "None";

    /** HA time reference */
    public static final String TIME_HA = "H.A.";
    /** list of choosable time references */
    public static final Vector<String> TIME_CHOICES = new Vector<String>(TimeRef.getDisplayNames());

    /* instrument names for specific features */
    /** VLTI AMBER */
    public static final String INS_AMBER = "AMBER";
    /** VLTI MIDI */
    public static final String INS_MIDI = "MIDI";
    /** VLTI PIONIER */
    public static final String INS_PIONIER = "PIONIER";
    /** VLTI GRAVITY */
    public static final String INS_GRAVITY = "GRAVITY";
    /** CHARA VEGA (2T/3T) */
    public static final String INS_VEGA = "VEGA_";
    /** JMMC legal notice on plots */
    public static final String JMMC_ANNOTATION = "Made by ASPRO 2/JMMC ";
    /** JMMC legal notice on plots */
    public static final String JMMC_WARNING_ANNOTATION = JMMC_ANNOTATION + " - Modified configuration: USE AT YOUR OWN RISKS ";
    /** suffix for calibrator names */
    public static final String CAL_SUFFIX = " (cal)";
    /** default value for undefined magnitude = -99 */
    public final static double UNDEFINED_MAGNITUDE = -99d;
    /** label to display when multiple configurations are in use (file names, chart titles ...) */
    public static final String MULTI_CONF = "MULTI CONFIGURATION";

    /* Warning matchers */    
    /** Prefix for the warning message 'Missing photometry ...' */
    public final static String WARN_MISSING_MAGS = "Missing photometry";
}