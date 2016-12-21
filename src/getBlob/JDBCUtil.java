package getBlob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
public class JDBCUtil {
	 /**
     * 类名称，用于打印Log日志
     */
    private static final String CLASS_NAME = "JDBCUtil";

    /**
     * 数据库类型
     */
    private String db_type;

    /**
     * 数据库实例名称
     */
    private String databaseName;

    /**
     * 数据库用户名
     */
    private String db_username;

    /**
     * 数据库密码
     */
    private String db_password;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 端口
     */
    private int port;

    /**
     * 数据库连接URl
     */
    private String db_url;

    public JDBCUtil(String dababase_name, String db_type, String db_username, String db_password, String ip, int port) {
        this.db_type = db_type;
        this.databaseName = dababase_name;
        this.db_username = db_username;
        this.db_password = db_password;
        this.ip = ip;
        this.port = port;
        this.db_url = getDBURL();
    }

    /**
     * 获得数据库连接
     * 
     * @return
     */
    public Connection getConnection() {
        // 声明Connection连接对象
        Connection conn = null;
        // 获取对应数据库的驱动
        String driver = getDBDriver();
        try {
            // 使用Class.forName()方法自动创建这个驱动程序的实例且自动调用DriverManager来注册它
            Class.forName(driver);
            // 通过DriverManager的getConnection()方法获取数据库连接
            conn = DriverManager.getConnection(db_url, db_username, db_password);
        } catch (Exception ex) {
            LogUtil.exception(CLASS_NAME, "getConnection", ex);
            return null;
        }
        return conn;
    }

    /**
     * 获取JDBC的URL，例如：jdbc:mysql://localhost:3306/test1
     * 
     * @param type
     *            数据库类型，如：mysql，oracle
     * @param ip
     *            数据库IP地址
     * @param dbId
     *            数据库实例名称
     * @param port
     *            数据库端口
     * @return url
     * @throws Exception
     */
    public String getDBURL() {
        String methodName = "getDBURL";
        if (StringUtils.isEmpty(db_type)) {
            LogUtil.error(CLASS_NAME, methodName, "type=null");
            return null;
        }
        if (StringUtils.isEmpty(ip)) {
            LogUtil.error(CLASS_NAME, methodName, "ip=null");
            return null;
        }
        if (StringUtils.isEmpty(databaseName)) {
            LogUtil.error(CLASS_NAME, methodName, "dbId=null");
            return null;
        }
        if (port == 0) {
            LogUtil.error(CLASS_NAME, methodName, "port=0");
            return null;
        }
        StringBuffer url = new StringBuffer();
        if (DbConstants.ORACLE_TYPE.equals(db_type)) {
            url.append(DbConstants.ORACLE_URL_PREFIX).append(ip).append(":").append(port).append(":")
                    .append(databaseName);
        } else if (DbConstants.MYSQL_TYPE.equals(db_type)) {
            url.append(DbConstants.MYSQL_URL_PREFIX).append(ip).append(":").append(port).append("/")
                    .append(databaseName).append("?characterEncoding=utf8");
        } 
        /*else if (DbConstants.DB2_TYPE.equals(db_type)) {
            url.append(DbConstants.DB2_URL_PREFIX).append(ip).append(":").append(port).append("/").append(databaseName);
        } else if (DbConstants.SQLSERVER_TYPE.equals(db_type)) {
            url.append(DbConstants.SQLSERVER_URL_PREFIX).append(ip).append(":").append(port).append(";DatabaseName=")
                    .append(databaseName);
        }*/
        return url.toString();
    }

    /**
     * 获取数据库驱动
     * 
     * @param type
     *            数据库类型，如：mysql，oracle
     * @return
     * @throws Exception
     */
    public String getDBDriver() {
        if (DbConstants.ORACLE_TYPE.equals(db_type)) {
            return DbConstants.ORACLE_DRIVER;
        } else if (DbConstants.MYSQL_TYPE.equals(db_type)) {
            return DbConstants.MYSQL_DRIVER;
        } 
        /*else if (DbConstants.DB2_TYPE.equals(db_type)) {
            return DbConstants.DB2_DRIVER;
        } else if (DbConstants.SQLSERVER_TYPE.equals(db_type)) {
            return DbConstants.SQLSERVER_DRIVER;
        }*/
        return null;
    }

    /**
     * 关闭数据库连接
     * 
     * @param conn
     */
    public static void close(Connection conn) {
        if (conn != null) {
            try {
                if (!conn.isClosed()) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException e) {
                LogUtil.exception(CLASS_NAME, "close", e);
            }
        }
    }

    public static void main(String[] args) {
        JDBCUtil jdbc = new JDBCUtil("idswitchtm", "Mysql", "root", "root", "127.0.0.1", 3306);
        Connection conn = jdbc.getConnection();
        List<String> dataList = new ArrayList<String>();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM business_database");
            int columns = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                for (int i = 1; i <= columns; i++) {
                    dataList.add(resultSet.getString(i));
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.print("ok");

    }
}
