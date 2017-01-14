/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lwh;

import kbt.Config;
import kbt.I18n;

/**
 *
 * @author kabot
 */
public class UpgradeDialog extends javax.swing.JDialog
{

  private Upgrade upg;

  /**
   * Creates new form UpgradeDialog
   */
  public UpgradeDialog(java.awt.Frame parent, boolean modal)
  {
    super(parent, modal);
    initComponents();

    int upgVer, curVer;

    upg = new Upgrade();
    upg.setUrl(Config.get("upgrade.url"));

    upgVer = upg.getVersion();
    curVer = Integer.parseInt(Config.get("version"));

    currentVersion.setText(Config.get("version"));
    upgradeVersion.setText(Integer.toString(upgVer));

    restartButton.setVisible(false);

    if (upgVer > curVer) {
      upgNeededInfo.setText(I18n.tr("msg.Aktulizacja.jest.dostepna"));
    }
    else {
      upgradeButton.setEnabled(false);
    }
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents()
  {

    jLabel1 = new javax.swing.JLabel();
    currentVersion = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    upgradeVersion = new javax.swing.JLabel();
    upgNeededInfo = new javax.swing.JLabel();
    upgradeButton = new javax.swing.JButton();
    closeButton = new javax.swing.JButton();
    restartButton = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("messages"); // NOI18N
    setTitle(bundle.getString("UpgradeDialog.title")); // NOI18N

    jLabel1.setText(bundle.getString("UpgradeDialog.jLabel1.text")); // NOI18N

    currentVersion.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
    currentVersion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    currentVersion.setText(bundle.getString("UpgradeDialog.currentVersion.text")); // NOI18N

    jLabel3.setText(bundle.getString("UpgradeDialog.jLabel3.text")); // NOI18N

    upgradeVersion.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
    upgradeVersion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    upgradeVersion.setText(bundle.getString("UpgradeDialog.upgradeVersion.text")); // NOI18N

    upgNeededInfo.setText(bundle.getString("UpgradeDialog.upgNeededInfo.text")); // NOI18N

    upgradeButton.setText(bundle.getString("UpgradeDialog.upgradeButton.text")); // NOI18N
    upgradeButton.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        upgradeButtonActionPerformed(evt);
      }
    });

    closeButton.setText(bundle.getString("UpgradeDialog.closeButton.text")); // NOI18N
    closeButton.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        closeButtonActionPerformed(evt);
      }
    });

    restartButton.setText(bundle.getString("UpgradeDialog.restartButton.text")); // NOI18N
    restartButton.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        restartButtonActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(upgNeededInfo)
          .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
              .addGap(0, 0, Short.MAX_VALUE)
              .addComponent(upgradeButton)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
              .addComponent(closeButton)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
              .addComponent(restartButton))
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel1)
                .addComponent(jLabel3))
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(upgradeVersion)
                .addComponent(currentVersion)))))
        .addContainerGap(24, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel1)
          .addComponent(currentVersion))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel3)
          .addComponent(upgradeVersion))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(upgNeededInfo)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(upgradeButton)
          .addComponent(restartButton)
          .addComponent(closeButton))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void upgradeButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_upgradeButtonActionPerformed
  {//GEN-HEADEREND:event_upgradeButtonActionPerformed
    upgNeededInfo.setText(I18n.tr("msg.Aktulizacja.w.toku"));
    upgradeButton.setEnabled(false);
    closeButton.setEnabled(false);

    if (upg.download(Config.getWorkDir())) {
      upgNeededInfo.setText(I18n.tr("msg.Aktualizacja.zakonczona.poprawnie"));

      upgradeButton.setVisible(false);
      closeButton.setVisible(false);
      restartButton.setVisible(true);
    }
    else {
      closeButton.setEnabled(true);
      upgNeededInfo.setText(I18n.tr("msg.Aktualizacja.zakonczona.bledem"));
    }
  }//GEN-LAST:event_upgradeButtonActionPerformed

  private void closeButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_closeButtonActionPerformed
  {//GEN-HEADEREND:event_closeButtonActionPerformed
    dispose();
  }//GEN-LAST:event_closeButtonActionPerformed

  private void restartButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_restartButtonActionPerformed
  {//GEN-HEADEREND:event_restartButtonActionPerformed
    upg.restart();
  }//GEN-LAST:event_restartButtonActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton closeButton;
  private javax.swing.JLabel currentVersion;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JButton restartButton;
  private javax.swing.JLabel upgNeededInfo;
  private javax.swing.JButton upgradeButton;
  private javax.swing.JLabel upgradeVersion;
  // End of variables declaration//GEN-END:variables
}