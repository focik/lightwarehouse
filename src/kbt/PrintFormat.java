package kbt;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class PrintFormat
{
  public static DecimalFormat getDecimal(String format)
  {
    DecimalFormat df = new DecimalFormat(format);
    DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();

    dfs.setDecimalSeparator(Config.get("decimalSeparator").charAt(0));

    df.setDecimalFormatSymbols(dfs);
    
    return df;
  }
}
