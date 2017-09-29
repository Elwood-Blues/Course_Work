package sql;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * The SQL class provides a single method getSQL() for retrieving SQL statements
 * from .sql files in this package.
 * @Author Peter Braband, Michael Ellison, Alexa Chiu, Grant Page, Abigal Reed
 */
public class SQL {

  private static SQL sqlSingleton;

  private final Map<String, String> statements;

  private SQL() {
    statements = new HashMap<>();
  }

  private synchronized String doGetSQL(String nm)
      throws IOException {
    if (statements.containsKey(nm)) {
      return statements.get(nm);
    }
    InputStream stream = SQL.class.getResourceAsStream(nm + ".sql");
    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
    StringBuilder builder = new StringBuilder();
    String line = reader.readLine();
    while (line != null) {
      builder.append(line);
      builder.append("\n");
      line = reader.readLine();
    }
    String statement = builder.toString();
    statements.put(nm, statement);
    return statement;
  }

  /**
   * SQL.getSQL(baseName) returns the SQL statement from the file basename.sql
   * in the sql package. The statement may contain '?' characters for
   * parameterized statements. It should only contain one statement. It must not
   * be terminated by a semicolon.
   *
   * @param baseName the base name of the .sql file
   * @return the SQL statement
   * @throws IOException when the file cannot be read
   */
  public static String getSQL(String baseName)
      throws IOException {
    if (sqlSingleton == null) {
      sqlSingleton = new SQL();
    }
    return sqlSingleton.doGetSQL(baseName);
  }

}
