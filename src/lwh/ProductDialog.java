/**
 * Okno z definicja produktu
 */
package lwh;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import kbt.I18n;
import kbt.KeyValue;

/**
 *
 * @author kabot
 */
public class ProductDialog extends javax.swing.JDialog
{
  Product prod;
  private Magazyn mag;

  /**
    * Creates new form ProductDialog
    */
  public ProductDialog(java.awt.Frame parent, int prodId)
  {
    super(parent, true);

    initComponents();

    if (prodId == 0) {
      buttonUsun.setEnabled(false);
      buttonZapiszNowy.setEnabled(false);
    }

    prodIlosc.requestFocus();

    setValues(prodId);

    getRootPane();
  }

  private void setValues(int prodId)
  {
    HashMap<Integer, KeyValue> magList;
    DecimalFormat df0 = new DecimalFormat("0.00");
    DecimalFormat df = new DecimalFormat("#.##");

    mag = new Magazyn();
    magList = mag.getList();

    for (Map.Entry<Integer, KeyValue> m : magList.entrySet()) {
      magCombo.addItem(m.getValue());
    }

    prod = new Product(prodId);

    prodName.setText(prod.name);
    prodJm.setText(prod.um);
    prodVat.setText(Integer.toString(prod.vat));
    prodCena.setText(df0.format(prod.price));
    prodCenaSprz.setText(df0.format(prod.priceSell));
    obecnaIlosc.setText(df.format(prod.quantity));

    magCombo.setSelectedItem(magList.get(prod.magId));

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    prodData.setText(dateFormat.format(new Date()));

  }

  @Override
  protected JRootPane createRootPane()
  {
    //http://www.javaworld.com/javaworld/javatips/jw-javatip72.html

    ActionListener actionEnter = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent evt) {
        saveProduct();
      }
    };

    ActionListener actionEsc = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent evt) {
        dispose();
      }
    };

    JRootPane rootP = new JRootPane();
    rootP.registerKeyboardAction(actionEnter,
                                 KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
                                 JComponent.WHEN_IN_FOCUSED_WINDOW);
    rootP.registerKeyboardAction(actionEsc,
                                 KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                                 JComponent.WHEN_IN_FOCUSED_WINDOW);

    return rootP;
  }

  private void saveProduct()
  {
    boolean ok = true;

    // http://www.devdaily.com/java/java-uimanager-color-keys-list
    final Color bg = UIManager.getColor("TextField.background");

    magCombo.setToolTipText(null);
    magCombo.setBackground(bg);
    prodName.setToolTipText(null);
    prodName.setBackground(bg);
    prodData.setToolTipText(null);
    prodData.setBackground(bg);
    prodIlosc.setToolTipText(null);
    prodIlosc.setBackground(bg);
    prodCena.setToolTipText(null);
    prodCena.setBackground(bg);
    prodCenaSprz.setToolTipText(null);
    prodCenaSprz.setBackground(bg);
    prodVat.setToolTipText(null);
    prodVat.setBackground(bg);

    if (magCombo.getSelectedItem() == null) {
      magCombo.setToolTipText(I18n.tr("msg.Product.Produkt_powinien_zostac_przypisany_do_magazynu"));
      magCombo.setBackground(Color.red);
      ok = false;
    }

    if (prodName.getText().isEmpty()) {
      prodName.setToolTipText(I18n.tr("msg.Product.Nie_podano_nazwy_produktu"));
      prodName.setBackground(Color.red);
      ok = false;
    }

    if (!prodData.getText().matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")) {
      prodData.setToolTipText(I18n.tr("msg.Product.Poprawny_format_daty"));
      prodData.setBackground(Color.red);
      ok = false;
    }


    if (!prodIlosc.getText().isEmpty()) {
      try {
        prod.quantity += Float.valueOf(prodIlosc.getText().replace(",", "."));
      }
      catch (NumberFormatException e) {
        prodIlosc.setToolTipText(I18n.tr("msg.Product.Poprawny_format_ilosci"));
        prodIlosc.setBackground(Color.red);
        ok = false;
      }
    }

    try {
      prod.price = Float.valueOf(prodCena.getText().replace(",", "."));
    }
    catch (NumberFormatException e) {
      prodCena.setToolTipText(I18n.tr("msg.Product.Poprawny_format_ceny"));
      prodCena.setBackground(Color.red);
      ok = false;
    }

    try {
      prod.priceSell = Float.valueOf(prodCenaSprz.getText().replace(",", "."));
    }
    catch (NumberFormatException e) {
      prodCenaSprz.setToolTipText(I18n.tr("msg.Product.Poprawny_format_ceny_sprzedazy"));
      prodCenaSprz.setBackground(Color.red);
      ok = false;
    }

    try {
      prod.vat = Integer.valueOf(prodVat.getText());
    }
    catch (NumberFormatException e) {
      prodVat.setToolTipText(I18n.tr("msg.Product.Poprawny_format_stawki_vat"));
      prodVat.setBackground(Color.red);
      ok = false;
    }

    if (!ok) {
      return;
    }

    prod.um = prodJm.getText();
    prod.name = prodName.getText();
    prod.magId = ((KeyValue)magCombo.getSelectedItem()).key;
    prod.date = prodData.getText();

    prod.save();

    dispose();
  }

  private void delProduct()
  {
    prod.delete();
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
    jLabel2 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    jLabel4 = new javax.swing.JLabel();
    jLabel5 = new javax.swing.JLabel();
    jLabel6 = new javax.swing.JLabel();
    jLabel7 = new javax.swing.JLabel();
    magCombo = new javax.swing.JComboBox<KeyValue>();
    prodName = new javax.swing.JTextField();
    prodJm = new javax.swing.JTextField();
    prodVat = new javax.swing.JTextField();
    prodIlosc = new javax.swing.JTextField();
    prodCena = new javax.swing.JTextField();
    prodData = new javax.swing.JTextField();
    jSeparator1 = new javax.swing.JSeparator();
    jButton1 = new javax.swing.JButton();
    buttonZapiszNowy = new javax.swing.JButton();
    jButton3 = new javax.swing.JButton();
    buttonUsun = new javax.swing.JButton();
    jLabel8 = new javax.swing.JLabel();
    obecnaIlosc = new javax.swing.JLabel();
    jLabel9 = new javax.swing.JLabel();
    prodCenaSprz = new javax.swing.JTextField();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("lwh/resources/messages"); // NOI18N
    setTitle(bundle.getString("ProductDialog.title")); // NOI18N

    jLabel1.setText(bundle.getString("ProductDialog.jLabel1.text")); // NOI18N

    jLabel2.setText(bundle.getString("ProductDialog.jLabel2.text")); // NOI18N

    jLabel3.setText(bundle.getString("ProductDialog.jLabel3.text")); // NOI18N

    jLabel4.setText(bundle.getString("ProductDialog.jLabel4.text")); // NOI18N

    jLabel5.setText(bundle.getString("ProductDialog.jLabel5.text")); // NOI18N

    jLabel6.setText(bundle.getString("ProductDialog.jLabel6.text")); // NOI18N

    jLabel7.setText(bundle.getString("ProductDialog.jLabel7.text")); // NOI18N

    prodName.setToolTipText(bundle.getString("ProductDialog.prodName.toolTipText")); // NOI18N

    prodData.setText(bundle.getString("ProductDialog.prodData.text")); // NOI18N

    jButton1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
    jButton1.setText(bundle.getString("ProductDialog.jButton1.text")); // NOI18N
    jButton1.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        btnZapisz(evt);
      }
    });

    buttonZapiszNowy.setText(bundle.getString("ProductDialog.buttonZapiszNowy.text")); // NOI18N
    buttonZapiszNowy.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        btnZapiszNowy(evt);
      }
    });

    jButton3.setText(bundle.getString("ProductDialog.jButton3.text")); // NOI18N
    jButton3.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        btnAnuluj(evt);
      }
    });

    buttonUsun.setText(bundle.getString("ProductDialog.buttonUsun.text")); // NOI18N
    buttonUsun.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        btnUsun(evt);
      }
    });

    jLabel8.setText(bundle.getString("ProductDialog.jLabel8.text")); // NOI18N

    obecnaIlosc.setText(bundle.getString("ProductDialog.obecnaIlosc.text")); // NOI18N

    jLabel9.setText(bundle.getString("ProductDialog.jLabel9.text")); // NOI18N

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(jButton1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(buttonZapiszNowy)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jButton3)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(buttonUsun)
            .addGap(0, 61, Short.MAX_VALUE))
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel1)
              .addComponent(jLabel2)
              .addComponent(jLabel3)
              .addComponent(jLabel4)
              .addComponent(jLabel5)
              .addComponent(jLabel6)
              .addComponent(jLabel9)
              .addComponent(jLabel7))
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(magCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                      .addComponent(prodCena, javax.swing.GroupLayout.Alignment.LEADING)
                      .addComponent(prodIlosc, javax.swing.GroupLayout.Alignment.LEADING)
                      .addComponent(prodVat, javax.swing.GroupLayout.Alignment.LEADING)
                      .addComponent(prodJm, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel8)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(obecnaIlosc))
                  .addComponent(prodCenaSprz, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(prodData, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
              .addComponent(prodName))))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel1)
          .addComponent(magCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(prodName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel2))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(prodJm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel3))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(prodVat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel4))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(prodIlosc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel5)
          .addComponent(jLabel8)
          .addComponent(obecnaIlosc))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel6)
          .addComponent(prodCena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel9)
          .addComponent(prodCenaSprz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(prodData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel7))
        .addGap(18, 18, 18)
        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jButton1)
          .addComponent(buttonZapiszNowy)
          .addComponent(jButton3)
          .addComponent(buttonUsun))
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

    private void btnZapisz(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZapisz
      saveProduct();
    }//GEN-LAST:event_btnZapisz

    private void btnZapiszNowy(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZapiszNowy
      prod.id = 0;
      saveProduct();
    }//GEN-LAST:event_btnZapiszNowy

    private void btnAnuluj(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnuluj
      dispose();
    }//GEN-LAST:event_btnAnuluj

  private void btnUsun(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnUsun
  {//GEN-HEADEREND:event_btnUsun
    int opt;
    opt = JOptionPane.showConfirmDialog(null,
                                        String.format(I18n.tr("msg.Product.Czy_na_pewno_usunąć_produkt_s"), prod.name),
                                        I18n.tr("msg.Product.Usuwanie_produktu"),
                                        JOptionPane.YES_NO_OPTION);
    if (opt == JOptionPane.YES_OPTION) {
      delProduct();
      dispose();
    }
  }//GEN-LAST:event_btnUsun

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton buttonUsun;
  private javax.swing.JButton buttonZapiszNowy;
  private javax.swing.JButton jButton1;
  private javax.swing.JButton jButton3;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JLabel jLabel8;
  private javax.swing.JLabel jLabel9;
  private javax.swing.JSeparator jSeparator1;
  private javax.swing.JComboBox<KeyValue> magCombo;
  private javax.swing.JLabel obecnaIlosc;
  private javax.swing.JTextField prodCena;
  private javax.swing.JTextField prodCenaSprz;
  private javax.swing.JTextField prodData;
  private javax.swing.JTextField prodIlosc;
  private javax.swing.JTextField prodJm;
  private javax.swing.JTextField prodName;
  private javax.swing.JTextField prodVat;
  // End of variables declaration//GEN-END:variables
}
