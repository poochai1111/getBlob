package getBlob;

public interface DbConstants {
	 // The minimum number of connections we will keep open, regardless of
    // whether anyone needs them or not. Default is 2
    String minimum_connection_count = "2";

    // The maximum number of connections to the database. Default is 15.
    String maximum_connection_count = "15";

    // mysql���ݿ���������
    String MYSQL_TYPE = "Mysql";

    // mysql���ݿ�URLǰ׺������URL:jdbc:mysql://127.0.0.1:3306/idep
    String MYSQL_URL_PREFIX = "jdbc:mysql://";

    // oracle���ݿ���������
    String ORACLE_TYPE = "Oracle";

    // oracle���ݿ�URLǰ׺,����URL��jdbc:oracle:thin:@127.0.0.1:1521:idep
    String ORACLE_URL_PREFIX = "jdbc:oracle:thin:@";

    //String DB2_TYPE = "DB2";

    // DB2���ݿ�URLǰ׺������URL��jdbc:db2://127.0.0.1:50000/idep
    //String DB2_URL_PREFIX = "jdbc:db2://";

    //String SQLSERVER_TYPE = "SqlServer";

    // sqlserver���ݿ�URLǰ׺������URL��jdbc:sqlserver://127.0.0.1:1433;DatabaseName=idep
    //String SQLSERVER_URL_PREFIX = "jdbc:sqlserver://";

    // oracle���ݿ���������
    String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";

    // mysql���ݿ�����
    String MYSQL_DRIVER = "com.mysql.jdbc.Driver";

    // DB2���ݿ�����
    //String DB2_DRIVER = "com.ibm.db2.jcc.DB2Driver";

    // sqlserver���ݿ�����
    //String SQLSERVER_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
}
