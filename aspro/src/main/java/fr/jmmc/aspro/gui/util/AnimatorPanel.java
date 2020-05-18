/*******************************************************************************
 * JMMC project ( http://www.jmmc.fr ) - Copyright (C) CNRS.
 ******************************************************************************/
package fr.jmmc.aspro.gui.util;

import fr.jmmc.aspro.gui.util.UserModelAnimator.UserModelAnimatorListener;
import fr.jmmc.aspro.model.oi.UserModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This simple panels interacts with the UserModelAnimator singleton to configure it at runtime
 * @author bourgesl
 */
public final class AnimatorPanel extends javax.swing.JPanel implements ActionListener, ChangeListener {

    /** default serial UID for Serializable interface */
    private static final long serialVersionUID = 1L;
    /** Class logger */
    private static final Logger _logger = LoggerFactory.getLogger(AnimatorPanel.class.getName());
    /** milliseconds per tick unit */
    private final static int TICK_UNIT_MS = 20;
    /** double formatter for auto refresh period */
    private final static NumberFormat _df1 = new DecimalFormat("0.0#");
    /** user model animator singleton */
    private final static UserModelAnimator animator = UserModelAnimator.getInstance();

    /* members */
    /** monitored user model animator listener */
    private final UserModelAnimatorListener listener;
    /** current user model to refresh images */
    private UserModel currentUserModel = null;

    /** 
     * Creates new form AnimatorPanel 
     */
    public AnimatorPanel() {
        this(null, false);
    }

    /** 
     * Creates new form AnimatorPanel 
     * @param listener monitored user model animator listener
     * @param enableAutoRefresh enable auto refresh
     */
    public AnimatorPanel(final UserModelAnimatorListener listener, final boolean enableAutoRefresh) {
        this.listener = listener;

        initComponents();

        postInit(enableAutoRefresh);
    }

    /**
     * Initialize the Swing components
     * @param enableAutoRefresh enable auto refresh
     */
    private void postInit(final boolean enableAutoRefresh) {
        jToggleButtonAutoRefresh.setSelected(enableAutoRefresh);

        // Refresh buttons listener :
        jToggleButtonAutoRefresh.addActionListener(this);
        jSliderPeriod.addChangeListener(this);

        // set initial slider value:
        jSliderPeriod.setValue(animator.getDelay() / TICK_UNIT_MS);
    }

    /** 
     * Handle the stateChanged event from the slider.
     * @param ce slider change event
     */
    @Override
    public void stateChanged(final ChangeEvent ce) {
        final int milliseconds = TICK_UNIT_MS * jSliderPeriod.getValue();

        if (_logger.isDebugEnabled()) {
            _logger.debug("slider changed to: {} ms", milliseconds);
        }

        // update text value (rounded to 0.1s):
        jTextFieldPeriod.setText(_df1.format(0.001d * milliseconds) + " s");

        // apply new delay to the timer
        animator.setDelay(milliseconds);
    }

    /**
     * Process any comboBox change event (level) to update Logger's state
     * @param ae action event
     */
    @Override
    public void actionPerformed(final ActionEvent ae) {
        if (ae.getSource() == jToggleButtonAutoRefresh) {
            final boolean autoRefresh = jToggleButtonAutoRefresh.isSelected();

            // update user model in animator panel to enable/disable animator:
            setUserModel(this.currentUserModel);

            this.jSliderPeriod.setEnabled(autoRefresh);
            this.jTextFieldPeriod.setEnabled(autoRefresh);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jSliderPeriod = new javax.swing.JSlider();
        jTextFieldPeriod = new javax.swing.JTextField();
        jToggleButtonAutoRefresh = new javax.swing.JToggleButton();

        setLayout(new java.awt.GridBagLayout());

        jSliderPeriod.setMaximum(250);
        jSliderPeriod.setMinimum(1);
        jSliderPeriod.setToolTipText("auto refresh periodicity (200ms to 5s)");
        jSliderPeriod.setValue(1);
        jSliderPeriod.setPreferredSize(new java.awt.Dimension(70, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(jSliderPeriod, gridBagConstraints);

        jTextFieldPeriod.setEditable(false);
        jTextFieldPeriod.setColumns(4);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(jTextFieldPeriod, gridBagConstraints);

        jToggleButtonAutoRefresh.setText("Animate");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(jToggleButtonAutoRefresh, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSlider jSliderPeriod;
    private javax.swing.JTextField jTextFieldPeriod;
    private javax.swing.JToggleButton jToggleButtonAutoRefresh;
    // End of variables declaration//GEN-END:variables

    /**
     * Define the current user model (may register/unregister the listener in user model animator)
     * @param userModel user model
     */
    public void setUserModel(final UserModel userModel) {
        this.currentUserModel = userModel;

        if (this.currentUserModel != null && jToggleButtonAutoRefresh.isSelected()) {
            animator.register(this.currentUserModel, this.listener);
        } else {
            animator.unregister(this.listener);
        }

        // update fields:
        // set slider value:
        jSliderPeriod.setValue(animator.getDelay() / TICK_UNIT_MS);
    }

    /**
     * Return true if the current user model is defined
     * @return true if the current user model is defined 
     */
    public boolean hasUserModel() {
        return this.currentUserModel != null;
    }
}
