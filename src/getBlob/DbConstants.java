package getBlob;

public interface DbConstants {
	 // The minimum number of connections we will keep open, regardless of
    // whether anyone needs them or not. Default is 2
    String minimum_connection_count = "2";

    // The maximum number of connections to the database. Default is 15.
    String maximum_connection_count = "15";

    // mysql数据库类型名称
    String MYSQL_TYPE = "Mysql";

    // mysql数据库URL前缀，完整URL:jdbc:mysql://127.0.0.1:3306/idep
    String MYSQL_URL_PREFIX = "jdbc:mysql://";

    // oracle数据库类型名称
    String ORACLE_TYPE = "Oracle";

    // oracle数据库URL前缀,完整URL：jdbc:oracle:thin:@127.0.0.1:1521:idep
    String ORACLE_URL_PREFIX = "jdbc:oracle:thin:@";

    //String DB2_TYPE = "DB2";

    // DB2数据库URL前缀，完整URL：jdbc:db2://127.0.0.1:50000/idep
    //String DB2_URL_PREFIX = "jdbc:db2://";

    //String SQLSERVER_TYPE = "SqlServer";

    // sqlserver数据库URL前缀，完整URL：jdbc:sqlserver://127.0.0.1:1433;DatabaseName=idep
    //String SQLSERVER_URL_PREFIX = "jdbc:sqlserver://";

    // oracle数据库连接驱动
    String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";

    // mysql数据库驱动
    String MYSQL_DRIVER = "com.mysql.jdbc.Driver";

    // DB2数据库驱动
    //String DB2_DRIVER = "com.ibm.db2.jcc.DB2Driver";

    // sqlserver数据库驱动
    //String SQLSERVER_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
}
