package kbt;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18n {

  private static Locale locale;
  private static ResourceBundle res;

  public static void setLocale(String lang)
  {
    locale = new Locale(lang);

    Locale.setDefault(locale);
  }

  public static String tr(String key)
  {
    if (res == null) {
      res = ResourceBundle.getBundle("lwh.resources.messages", locale);
    }

    return res.getString(key);
  }
}
