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
     * �����ƣ����ڴ�ӡLog��־
     */
    private static final String CLASS_NAME = "JDBCUtil";

    /**
     * ���ݿ�����
     */
    private String db_type;

    /**
     * ���ݿ�ʵ������
     */
    private String databaseName;

    /**
     * ���ݿ��û���
     */
    private String db_username;

    /**
     * ���ݿ�����
     */
    private String db_password;

    /**
     * ip��ַ
     */
    private String ip;

    /**
     * �˿�
     */
    private int port;

    /**
     * ���ݿ�����URl
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
     * ������ݿ�����
     * 
     * @return
     */
    public Connection getConnection() {
        // ����Connection���Ӷ���
        Connection conn = null;
        // ��ȡ��Ӧ���ݿ������
        String driver = getDBDriver();
        try {
            // ʹ��Class.forName()�����Զ�����������������ʵ�����Զ�����DriverManager��ע����
            Class.forName(driver);
            // ͨ��DriverManager��getConnection()������ȡ���ݿ�����
            conn = DriverManager.getConnection(db_url, db_username, db_password);
        } catch (Exception ex) {
            LogUtil.exception(CLASS_NAME, "getConnection", ex);
            return null;
        }
        return conn;
    }

    /**
     * ��ȡJDBC��URL�����磺jdbc:mysql://localhost:3306/test1
     * 
     * @param type
     *            ���ݿ����ͣ��磺mysql��oracle
     * @param ip
     *            ���ݿ�IP��ַ
     * @param dbId
     *            ���ݿ�ʵ������
     * @param port
     *            ���ݿ�˿�
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
     * ��ȡ���ݿ�����
     * 
     * @param type
     *            ���ݿ����ͣ��磺mysql��oracle
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
     * �ر����ݿ�����
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
