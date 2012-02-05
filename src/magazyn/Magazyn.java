/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package magazyn;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

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

        sql = "SELECT p.id, p.name, p.um, p.vat, p.price, p.mod_date, p.quantity "
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
}
