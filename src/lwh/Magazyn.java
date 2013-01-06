package lwh;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import kbt.I18n;
import kbt.KeyValue;

/**
 * @author cwpika
 */
public class Magazyn
{
    public HashMap<Integer, KeyValue> getList()
    {
        Statement st;

        HashMap<Integer, KeyValue> list = new HashMap<>();

        try {
          st = Db.getDb().createStatement();
          ResultSet rs = st.executeQuery("SELECT id, name FROM store ORDER BY name");

          while (rs.next()) {
            list.put(rs.getInt("id"), new KeyValue(rs.getInt("id"), rs.getString("name")));
          }
        }
        catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public ArrayList<Product> getProductList(int magId, String filter)
    {
        PreparedStatement st;
        ArrayList<Product> list = new ArrayList<>();
        Product p;
        String sql;

        sql = "SELECT p.id, p.name, p.um, p.vat, p.price, p.price_sell, p.mod_date, p.quantity "
                    + "FROM product p "
                    + "WHERE p.id_store = ? AND p.name LIKE ? ORDER BY name";

        try {
          st = Db.getDb().prepareStatement(sql);

          st.setInt(1, magId);
          st.setString(2, "%" + filter + "%");

          ResultSet rs = st.executeQuery();

          while (rs.next()) {
            p = new Product();

            p.id = rs.getInt("id");
            p.name = rs.getString("name");
            p.um = rs.getString("um");
            p.vat = rs.getInt("vat");
            p.price = rs.getFloat("price");
            p.priceSell = rs.getFloat("price_sell");
            p.date = rs.getString("mod_date");
            p.quantity = rs.getFloat("quantity");

            list.add(p);
          }
        }
        catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

  /**
   * zapis do pliku csv
   *
   * @param magId
   * @param selectedFile
   */
  public boolean exportCsv(int magId, File selectedFile)
  {
    DecimalFormat df0 = new DecimalFormat("0.00");
    ArrayList<Product> list = getProductList(magId, "");
    int pos = 0;
    String line, sep = "\t";
    PrintWriter out;

    try {
      out = new PrintWriter(selectedFile, "UTF-8");
    }
    catch (IOException ex) {
      System.err.println(ex);
      return false;
    }

    out.println(
      I18n.tr("MainFrame.jTable1.columnModel.title0_1") + "\t" +
      I18n.tr("MainFrame.jTable1.columnModel.title1_1") + "\t" +
      I18n.tr("MainFrame.jTable1.columnModel.title2_1") + "\t" +
      I18n.tr("MainFrame.jTable1.columnModel.title3_1") + "\t" +
      I18n.tr("MainFrame.jTable1.columnModel.title4_1") + "\t" +
      I18n.tr("MainFrame.jTable1.columnModel.title5_1") + "\t" +
      I18n.tr("MainFrame.jTable1.columnModel.title6_1") + "\t" +
      I18n.tr("MainFrame.jTable1.columnModel.title7_1") + "\t");

    for (Product p : list) {
      pos++;

      line =
        String.valueOf(pos) + sep +
        p.name + sep +
        p.um + sep +
        p.vat + sep +
        p.quantity + sep +
        df0.format(p.price) + sep +
        df0.format(p.priceSell) + sep +
        p.date;

      out.println(line);
    }

    out.close();

    return true;
  }
}
