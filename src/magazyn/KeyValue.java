/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package magazyn;

public class KeyValue
{  
    public int key;   
    public String value;

    public KeyValue(int nkey, String nvalue)
    {  
        key = nkey;
        value = nvalue;
    }  

    public String toString()
    {  
        return value;
    }  
}