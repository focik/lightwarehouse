/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package magazyn;

import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteException;
import com.almworks.sqlite4java.SQLiteStatement;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author cwpika
 */
public class Magazyn {
    private SQLiteConnection db;

    public Magazyn()
    {
        File dbfile = new File("/home/kabot/Desktop/magazyn.sqlite");

        Logger.getLogger("com.almworks.sqlite4java").setLevel(Level.OFF);

        db = new SQLiteConnection(dbfile);
        try {
            db.open(true);
        }
        catch (SQLiteException e) {
            System.out.println("open: " + e);
        }
    }
    
    public HashMap<Integer, String> getList()
    {
        SQLiteStatement st;

        HashMap<Integer, String> list = new HashMap<>();

        try {
            st = db.prepare("SELECT id, name FROM store ORDER BY name");

            while (st.step()) {
                list.put(st.columnInt(0), st.columnString(1));
            }

            st.dispose();
        }
        catch (SQLiteException e) {
            System.out.println(e);
        }

        return list;
    }

    public ArrayList<Product> getProductList(int magId, String filter)
    {
        SQLiteStatement st;
        ArrayList<Product> list = new ArrayList<>();
        Product p;
        String sql;

        sql = "SELECT p.id, p.name, p.um, p.vat, o.price, o.add_date, p.quantity "
                    + "FROM product p "
                    + "LEFT JOIN operation o ON p.id=o.id_product AND o.last=1 "
                    + "WHERE p.id_store = ? AND p.name LIKE ?";

        try {
            st = db.prepare(sql);

            st.bind(1, magId);
            st.bind(2, "%" + filter + "%");
            while (st.step()) {
                p = new Product();

                p.id = st.columnInt(0);
                p.name = st.columnString(1);
                p.um = st.columnString(2);
                p.vat = st.columnInt(3);
                p.price = st.columnLong(4);
                p.date = st.columnString(5);
                
                list.add(p);
            }

            st.dispose();
        }
        catch (SQLiteException e) {
            System.out.println(e);
        }
        
        return list;
    }
}
