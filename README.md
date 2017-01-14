## Build & run
<code>gradle jar</gradle>

<code>java -jar build/libs/lightwarehouse.jar [-v]</code>

<code>java -DpropertiesFile=src/main/resources/application.properties -jar build/libs/lightwarehouse.jar</code>

## Tools
in.csv - file exported by the application

<code>python tools/csv2sql.py 1 < in.csv > out.sql</code>
