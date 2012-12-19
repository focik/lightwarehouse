/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package magazyn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author cwpika
 */
public class Product
{
  public int id;
  public int magId;
  public String name;
  public String um;
  public int vat;
  public float quantity;
  public float price;
  public String date;

  Product(int prodId)
  {
    String sql;
    PreparedStatement st;

    sql = "SELECT p.name, p.um, p.vat, p.quantity, p.price, p.mod_date, p.id_store " +
          "FROM product p " +
          "WHERE p.id = ?";

    id = prodId;

    try {
      st = Db.getDb().prepareStatement(sql);

      st.setInt(1, prodId);
      ResultSet rs = st.executeQuery();

      if (rs.next()) {
        name = rs.getString("name");
        um = rs.getString("um");
        vat = rs.getInt("vat");
        price = rs.getFloat("price");
        date = rs.getString("mod_date");
        quantity = rs.getFloat("quantity");
        magId = rs.getInt("id_store");
      }
    }
    catch (SQLException e) {
      System.out.println(e);
    }
  }

  Product()
  {}

  public boolean save()
  {
    String sql;
    PreparedStatement st;
    Connection db = Db.getDb();

    if (id > 0) {
      sql = "UPDATE product SET " +
            "name = ?, " +
            "um = ?, " +
            "vat = ?, " +
            "quantity = ?, " +
            "price = ?, " +
            "mod_date = ?, " +
            "id_store = ? " +
            "WHERE id = ?";

      //System.out.println("aktualizacja "+ id);
    }
    else {
      sql = "INSERT INTO product " +
            "(name, um, vat, quantity, price, mod_date, id_store) VALUES " +
            "(?, ?, ?, ?, ?, ?, ?)";

      //System.out.println("nowy produkt");
    }

    try {
      st = db.prepareStatement(sql);
      st.setString(1, name);
      st.setString(2, um);
      st.setInt(3, vat);
      st.setFloat(4, quantity);
      st.setFloat(5, price);
      st.setString(6, date);
      st.setInt(7, magId);
      if (id > 0) {
        st.setInt(8, id);
      }

      st.executeUpdate();

      //bez tego dane nie trafiaja do pliku
      Db.close();
    }
    catch (SQLException e) {
      System.out.println(e);
    }

    return true;
  }

  public void delete()
  {
    PreparedStatement st;

    try {
      st = Db.getDb().prepareStatement("DELETE FROM product WHERE id = ?");
      st.setInt(1, id);

      st.executeUpdate();
      Db.close();
    }
    catch (SQLException e) {
      System.out.println(e);
    }
  }
}
