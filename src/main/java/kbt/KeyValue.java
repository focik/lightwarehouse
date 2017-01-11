package kbt;

public class KeyValue
{
  public int key;
  public String value;

  public KeyValue(int nkey, String nvalue)
  {
    key = nkey;
    value = nvalue;
  }

  @Override
  public String toString()
  {
    return value;
  }
}