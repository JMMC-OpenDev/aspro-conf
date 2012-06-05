/*******************************************************************************
 * JMMC project ( http://www.jmmc.fr ) - Copyright (C) CNRS.
 ******************************************************************************/
package fr.jmmc.aspro.gui;

import fr.jmmc.aspro.AsproConstants;
import fr.jmmc.aspro.Preferences;
import fr.jmmc.aspro.model.observability.SunTimeInterval.SunType;
import fr.jmmc.jmal.image.ColorModels;
import fr.jmmc.jmal.image.ColorScale;
import fr.jmmc.jmcs.data.preference.PreferencesException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;

/**
 * Preferences GUI
 */
public final class PreferencesView extends JFrame implements Observer {

  /** default serial UID for Serializable interface */
  private static final long serialVersionUID = 1;
  /** Class logger */
  private static final Logger logger = LoggerFactory.getLogger(PreferencesView.class.getName());
  /** twilight choices */
  private final static String[] TWILIGHTS = new String[]{"Astronomical (-18°)", "Nautical (-12°)", "Civil (-6°)", "Sun (0°)"};

  /* members */
  /** preference singleton */
  private final Preferences myPreferences = Preferences.getInstance();

  /** 
   * Creates a new PreferencesView
   */
  public PreferencesView() {
    super();
    initComponents();

    postInit();
  }

  /**
   * This method is useful to set the models and specific features of initialized swing components :
   * Update the combo boxes with their models
   */
  private void postInit() {
    // define custom models :
    this.jComboBoxImageSize.setModel(new DefaultComboBoxModel(AsproConstants.IMAGE_SIZES));
    this.jComboBoxLUT.setModel(new DefaultComboBoxModel(ColorModels.getColorModelNames()));
    this.jComboBoxColorScale.setModel(new DefaultComboBoxModel(ColorScale.values()));

    // register this instance as a Preference Observer :
    this.myPreferences.addObserver(this);

    // update GUI
    update(null, null);

    this.jFieldMinElev.addPropertyChangeListener("value", new PropertyChangeListener() {

      @Override
      public void propertyChange(final PropertyChangeEvent evt) {
        final double minElevNew = ((Number) jFieldMinElev.getValue()).doubleValue();

        if (minElevNew < 0d || minElevNew >= 90d) {
          // invalid value :
          jFieldMinElev.setValue(myPreferences.getPreferenceAsDouble(Preferences.MIN_ELEVATION));
        }
        try {
          // will fire triggerObserversNotification so update() will be called
          myPreferences.setPreference(Preferences.MIN_ELEVATION, Double.valueOf(((Number) jFieldMinElev.getValue()).doubleValue()));
        } catch (PreferencesException pe) {
          logger.error("property failure : ", pe);
        }
      }
    });

    // pack and center window
    pack();
    setLocationRelativeTo(null);
  }

  /**
   * Free any ressource or reference to this instance :
   * remove this instance form Preference Observers
   */
  @Override
  public void dispose() {
    logger.debug("dispose: {}", this);

    // unregister this instance as a Preference Observer :
    this.myPreferences.deleteObserver(this);

    // dispose Frame :
    super.dispose();
  }

  /**
   * Overriden method to give object identifier
   * @return string identifier
   */
  @Override
  public String toString() {
    return "PreferencesView@" + Integer.toHexString(hashCode());
  }

  /**
   * This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroupPositionStyle = new javax.swing.ButtonGroup();
        buttonGroupTimeRef = new javax.swing.ButtonGroup();
        buttonGroupTimeAxis = new javax.swing.ButtonGroup();
        buttonGroupFastUserModel = new javax.swing.ButtonGroup();
        jPanelObservability = new javax.swing.JPanel();
        jLabelTimeRef = new javax.swing.JLabel();
        jRadioButtonTimeLST = new javax.swing.JRadioButton();
        jRadioButtonTimeUTC = new javax.swing.JRadioButton();
        jLabelCenterNight = new javax.swing.JLabel();
        jRadioButtonCenterNightYes = new javax.swing.JRadioButton();
        jRadioButtonCenterNightNo = new javax.swing.JRadioButton();
        jLabelMinElev = new javax.swing.JLabel();
        jFieldMinElev = new javax.swing.JFormattedTextField();
        jLabelTwilight = new javax.swing.JLabel();
        jComboBoxTwilight = new javax.swing.JComboBox();
        jPanelModelEditor = new javax.swing.JPanel();
        jLabelPositionStyle = new javax.swing.JLabel();
        jRadioButtonXY = new javax.swing.JRadioButton();
        jRadioButtonRhoTheta = new javax.swing.JRadioButton();
        jPanelModelImage = new javax.swing.JPanel();
        jLabelLutTable = new javax.swing.JLabel();
        jComboBoxLUT = new javax.swing.JComboBox();
        jLabelImageSize = new javax.swing.JLabel();
        jComboBoxImageSize = new javax.swing.JComboBox();
        jLabelColorScale = new javax.swing.JLabel();
        jComboBoxColorScale = new javax.swing.JComboBox();
        jPanelUserModel = new javax.swing.JPanel();
        jLabelFastUserModel = new javax.swing.JLabel();
        jRadioButtonFastUserModelYes = new javax.swing.JRadioButton();
        jRadioButtonFastUserModelNo = new javax.swing.JRadioButton();
        jPanelButtons = new javax.swing.JPanel();
        jButtonDefault = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Preferences");
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.PAGE_AXIS));

        jPanelObservability.setBorder(javax.swing.BorderFactory.createTitledBorder("Observability"));
        jPanelObservability.setLayout(new java.awt.GridBagLayout());

        jLabelTimeRef.setText("Time reference");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 6);
        jPanelObservability.add(jLabelTimeRef, gridBagConstraints);

        buttonGroupTimeRef.add(jRadioButtonTimeLST);
        jRadioButtonTimeLST.setSelected(true);
        jRadioButtonTimeLST.setText(AsproConstants.TIME_LST);
        jRadioButtonTimeLST.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonTimeRefActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanelObservability.add(jRadioButtonTimeLST, gridBagConstraints);

        buttonGroupTimeRef.add(jRadioButtonTimeUTC);
        jRadioButtonTimeUTC.setText(AsproConstants.TIME_UTC);
        jRadioButtonTimeUTC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonTimeRefActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanelObservability.add(jRadioButtonTimeUTC, gridBagConstraints);

        jLabelCenterNight.setText("Center plot around night");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 6);
        jPanelObservability.add(jLabelCenterNight, gridBagConstraints);

        buttonGroupTimeAxis.add(jRadioButtonCenterNightYes);
        jRadioButtonCenterNightYes.setSelected(true);
        jRadioButtonCenterNightYes.setText("yes");
        jRadioButtonCenterNightYes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonCenterNightActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanelObservability.add(jRadioButtonCenterNightYes, gridBagConstraints);

        buttonGroupTimeAxis.add(jRadioButtonCenterNightNo);
        jRadioButtonCenterNightNo.setText("no");
        jRadioButtonCenterNightNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonCenterNightActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanelObservability.add(jRadioButtonCenterNightNo, gridBagConstraints);

        jLabelMinElev.setText("Default min. Elevation");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 6);
        jPanelObservability.add(jLabelMinElev, gridBagConstraints);

        jFieldMinElev.setColumns(2);
        jFieldMinElev.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jFieldMinElev.setName("jFieldMinElev"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 2);
        jPanelObservability.add(jFieldMinElev, gridBagConstraints);

        jLabelTwilight.setText("Twilight used as Night limit");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanelObservability.add(jLabelTwilight, gridBagConstraints);

        jComboBoxTwilight.setModel(new DefaultComboBoxModel(TWILIGHTS));
        jComboBoxTwilight.setSelectedItem(TWILIGHTS[0]);
        jComboBoxTwilight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTwilightActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanelObservability.add(jComboBoxTwilight, gridBagConstraints);

        getContentPane().add(jPanelObservability);

        jPanelModelEditor.setBorder(javax.swing.BorderFactory.createTitledBorder("Model Editor"));
        jPanelModelEditor.setLayout(new java.awt.GridBagLayout());

        jLabelPositionStyle.setText("<html>Default style to <br/>edit model positions</htmL>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        jPanelModelEditor.add(jLabelPositionStyle, gridBagConstraints);

        buttonGroupPositionStyle.add(jRadioButtonXY);
        jRadioButtonXY.setText("x / y (mas)");
        jRadioButtonXY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonPositionStyleActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanelModelEditor.add(jRadioButtonXY, gridBagConstraints);

        buttonGroupPositionStyle.add(jRadioButtonRhoTheta);
        jRadioButtonRhoTheta.setText("rho (mas) / theta");
        jRadioButtonRhoTheta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonPositionStyleActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanelModelEditor.add(jRadioButtonRhoTheta, gridBagConstraints);

        getContentPane().add(jPanelModelEditor);

        jPanelModelImage.setBorder(javax.swing.BorderFactory.createTitledBorder("Model Image"));
        jPanelModelImage.setLayout(new java.awt.GridBagLayout());

        jLabelLutTable.setText("LUT table");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 6);
        jPanelModelImage.add(jLabelLutTable, gridBagConstraints);

        jComboBoxLUT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxLUTActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanelModelImage.add(jComboBoxLUT, gridBagConstraints);

        jLabelImageSize.setText("Image size");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 6);
        jPanelModelImage.add(jLabelImageSize, gridBagConstraints);

        jComboBoxImageSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxImageSizeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanelModelImage.add(jComboBoxImageSize, gridBagConstraints);

        jLabelColorScale.setText("Color scale");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 6);
        jPanelModelImage.add(jLabelColorScale, gridBagConstraints);

        jComboBoxColorScale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxColorScaleActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanelModelImage.add(jComboBoxColorScale, gridBagConstraints);

        getContentPane().add(jPanelModelImage);

        jPanelUserModel.setBorder(javax.swing.BorderFactory.createTitledBorder("User Model"));
        jPanelUserModel.setLayout(new java.awt.GridBagLayout());

        jLabelFastUserModel.setText("Fast mode (optimize image)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 6);
        jPanelUserModel.add(jLabelFastUserModel, gridBagConstraints);

        buttonGroupFastUserModel.add(jRadioButtonFastUserModelYes);
        jRadioButtonFastUserModelYes.setText("yes");
        jRadioButtonFastUserModelYes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonFastUserModelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanelUserModel.add(jRadioButtonFastUserModelYes, gridBagConstraints);

        buttonGroupFastUserModel.add(jRadioButtonFastUserModelNo);
        jRadioButtonFastUserModelNo.setText("no");
        jRadioButtonFastUserModelNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonFastUserModelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanelUserModel.add(jRadioButtonFastUserModelNo, gridBagConstraints);

        getContentPane().add(jPanelUserModel);

        jButtonDefault.setText("Restore Default Settings");
        jButtonDefault.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDefaultActionPerformed(evt);
            }
        });
        jPanelButtons.add(jButtonDefault);

        jButtonSave.setText("Save Modifications");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });
        jPanelButtons.add(jButtonSave);

        getContentPane().add(jPanelButtons);
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButtonPositionStyleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonPositionStyleActionPerformed
      try {
        // will fire triggerObserversNotification so update() will be called
        this.myPreferences.setPreference(Preferences.MODELEDITOR_PREFERXY, Boolean.valueOf(this.jRadioButtonXY.isSelected()));
      } catch (PreferencesException pe) {
        logger.error("property failure : ", pe);
      }
    }//GEN-LAST:event_jRadioButtonPositionStyleActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
      try {
        this.myPreferences.saveToFile();
      } catch (PreferencesException pe) {
        // this try catch should not be solved here
        // one feedback report could be thrown on error into Preference code
        logger.error("property failure : ", pe);
      }
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonDefaultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDefaultActionPerformed
      this.myPreferences.resetToDefaultPreferences();
    }//GEN-LAST:event_jButtonDefaultActionPerformed

    private void jComboBoxImageSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxImageSizeActionPerformed
      try {
        // will fire triggerObserversNotification so update() will be called
        this.myPreferences.setPreference(Preferences.MODEL_IMAGE_SIZE, this.jComboBoxImageSize.getSelectedItem());
      } catch (PreferencesException pe) {
        logger.error("property failure : ", pe);
      }
    }//GEN-LAST:event_jComboBoxImageSizeActionPerformed

    private void jComboBoxLUTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxLUTActionPerformed
      try {
        // will fire triggerObserversNotification so update() will be called
        this.myPreferences.setPreference(Preferences.MODEL_IMAGE_LUT, this.jComboBoxLUT.getSelectedItem());
      } catch (PreferencesException pe) {
        logger.error("property failure : ", pe);
      }
    }//GEN-LAST:event_jComboBoxLUTActionPerformed

    private void jRadioButtonTimeRefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonTimeRefActionPerformed
      try {
        final String value;
        if (this.jRadioButtonTimeUTC.isSelected()) {
          value = AsproConstants.TIME_UTC;
        } else {
          value = AsproConstants.TIME_LST;
        }

        // will fire triggerObserversNotification so update() will be called
        this.myPreferences.setPreference(Preferences.TIME_REFERENCE, value);
      } catch (PreferencesException pe) {
        logger.error("property failure : ", pe);
      }
    }//GEN-LAST:event_jRadioButtonTimeRefActionPerformed

    private void jRadioButtonCenterNightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonCenterNightActionPerformed
      try {
        // will fire triggerObserversNotification so update() will be called
        this.myPreferences.setPreference(Preferences.CENTER_NIGHT, Boolean.valueOf(this.jRadioButtonCenterNightYes.isSelected()));
      } catch (PreferencesException pe) {
        logger.error("property failure : ", pe);
      }
    }//GEN-LAST:event_jRadioButtonCenterNightActionPerformed

    private void jComboBoxTwilightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTwilightActionPerformed
      try {
        // will fire triggerObserversNotification so update() will be called
        this.myPreferences.setPreference(Preferences.TWILIGHT_NIGHT, getTwilight((String) this.jComboBoxTwilight.getSelectedItem()).toString());
      } catch (PreferencesException pe) {
        logger.error("property failure : ", pe);
      }
    }//GEN-LAST:event_jComboBoxTwilightActionPerformed

  private void jComboBoxColorScaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxColorScaleActionPerformed
    try {
      // will fire triggerObserversNotification so update() will be called
      this.myPreferences.setPreference(Preferences.MODEL_IMAGE_SCALE, this.jComboBoxColorScale.getSelectedItem().toString());
    } catch (PreferencesException pe) {
      logger.error("property failure : ", pe);
    }
  }//GEN-LAST:event_jComboBoxColorScaleActionPerformed

  private void jRadioButtonFastUserModelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonFastUserModelActionPerformed
    try {
      // will fire triggerObserversNotification so update() will be called
      this.myPreferences.setPreference(Preferences.MODEL_USER_FAST, Boolean.valueOf(this.jRadioButtonFastUserModelYes.isSelected()));
    } catch (PreferencesException pe) {
      logger.error("property failure : ", pe);
    }
  }//GEN-LAST:event_jRadioButtonFastUserModelActionPerformed

  /**
   * Listen to preferences changes
   * @param o Preferences
   * @param arg unused
   */
  @Override
  public void update(final Observable o, final Object arg) {
    logger.debug("Preferences updated on : {}", this);

    // read prefs to set states of GUI elements
    final boolean preferXyMode = this.myPreferences.getPreferenceAsBoolean(Preferences.MODELEDITOR_PREFERXY);
    this.jRadioButtonXY.setSelected(preferXyMode);
    this.jRadioButtonRhoTheta.setSelected(!preferXyMode);

    this.jComboBoxImageSize.setSelectedItem(this.myPreferences.getPreferenceAsInt(Preferences.MODEL_IMAGE_SIZE));
    this.jComboBoxLUT.setSelectedItem(this.myPreferences.getPreference(Preferences.MODEL_IMAGE_LUT));
    this.jComboBoxColorScale.setSelectedItem(this.myPreferences.getImageColorScale());

    final boolean preferTimeLst = AsproConstants.TIME_LST.equals(this.myPreferences.getPreference(Preferences.TIME_REFERENCE));
    this.jRadioButtonTimeLST.setSelected(preferTimeLst);
    this.jRadioButtonTimeUTC.setSelected(!preferTimeLst);

    final boolean preferCenterNight = this.myPreferences.getPreferenceAsBoolean(Preferences.CENTER_NIGHT);
    this.jRadioButtonCenterNightYes.setSelected(preferCenterNight);
    this.jRadioButtonCenterNightNo.setSelected(!preferCenterNight);

    this.jFieldMinElev.setValue(this.myPreferences.getPreferenceAsDouble(Preferences.MIN_ELEVATION));

    this.jComboBoxTwilight.setSelectedItem(getTwilight(this.myPreferences.getTwilightAsNightLimit()));

    final boolean useFastUserModel = this.myPreferences.isFastUserModel();
    this.jRadioButtonFastUserModelYes.setSelected(useFastUserModel);
    this.jRadioButtonFastUserModelNo.setSelected(!useFastUserModel);
  }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupFastUserModel;
    private javax.swing.ButtonGroup buttonGroupPositionStyle;
    private javax.swing.ButtonGroup buttonGroupTimeAxis;
    private javax.swing.ButtonGroup buttonGroupTimeRef;
    private javax.swing.JButton jButtonDefault;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JComboBox jComboBoxColorScale;
    private javax.swing.JComboBox jComboBoxImageSize;
    private javax.swing.JComboBox jComboBoxLUT;
    private javax.swing.JComboBox jComboBoxTwilight;
    private javax.swing.JFormattedTextField jFieldMinElev;
    private javax.swing.JLabel jLabelCenterNight;
    private javax.swing.JLabel jLabelColorScale;
    private javax.swing.JLabel jLabelFastUserModel;
    private javax.swing.JLabel jLabelImageSize;
    private javax.swing.JLabel jLabelLutTable;
    private javax.swing.JLabel jLabelMinElev;
    private javax.swing.JLabel jLabelPositionStyle;
    private javax.swing.JLabel jLabelTimeRef;
    private javax.swing.JLabel jLabelTwilight;
    private javax.swing.JPanel jPanelButtons;
    private javax.swing.JPanel jPanelModelEditor;
    private javax.swing.JPanel jPanelModelImage;
    private javax.swing.JPanel jPanelObservability;
    private javax.swing.JPanel jPanelUserModel;
    private javax.swing.JRadioButton jRadioButtonCenterNightNo;
    private javax.swing.JRadioButton jRadioButtonCenterNightYes;
    private javax.swing.JRadioButton jRadioButtonFastUserModelNo;
    private javax.swing.JRadioButton jRadioButtonFastUserModelYes;
    private javax.swing.JRadioButton jRadioButtonRhoTheta;
    private javax.swing.JRadioButton jRadioButtonTimeLST;
    private javax.swing.JRadioButton jRadioButtonTimeUTC;
    private javax.swing.JRadioButton jRadioButtonXY;
    // End of variables declaration//GEN-END:variables

  /**
   * Return the string choice corresponding to the given SunType instance
   * @param type SunType instance
   * @return string choice
   */
  private String getTwilight(final SunType type) {
    switch (type) {
      default:
      case Night:
        return TWILIGHTS[0];
      case AstronomicalTwilight:
        return TWILIGHTS[1];
      case NauticalTwilight:
        return TWILIGHTS[2];
      case CivilTwilight:
        return TWILIGHTS[3];
    }
  }

  /**
   * Return the SunType instance corresponding to the given string choice
   * @param choice string choice
   * @return SunType instance
   */
  private SunType getTwilight(final String choice) {
    int pos = -1;
    for (int i = 0; i < TWILIGHTS.length; i++) {
      if (TWILIGHTS[i].equals(choice)) {
        pos = i;
        break;
      }
    }
    if (pos == -1) {
      logger.warn("choice[{}] not found in {}", choice, Arrays.toString(TWILIGHTS));
    }
    switch (pos) {
      default:
      case 0:
        return SunType.Night;
      case 1:
        return SunType.AstronomicalTwilight;
      case 2:
        return SunType.NauticalTwilight;
      case 3:
        return SunType.CivilTwilight;
    }
  }
}
