package getBlob;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class LogUtil {
	private static Logger log = LoggerFactory.getLogger(LogUtil.class);

    /**
     * �����־��ӡ��debug����
     * 
     * @param className ������
     * @param methodName ��������
     * @param logMap ����б�,key���������ƣ�value������ֵ
     */
    public static void debug(String className, String methodName, Map<String, String> logMap)
    {
        log.debug("----------log start-----------");

        // ��ӡ������
        log.debug("Class Name:" + className);
        // ��ӡ��������
        log.debug("Method Name:" + methodName);
        // ��ӡ�����б�
        Set<String> keySet = logMap.keySet();
        Iterator<String> iter = keySet.iterator();

        // ����������Ϊkey
        String paramKey = null;
        String paramValue = null;
        while (iter.hasNext())
        {
            paramKey = iter.next();
            paramValue = logMap.get(paramKey);
            StringBuffer logBuff = new StringBuffer();
            logBuff.append("Parameter Name:").append(paramKey).append(",");
            logBuff.append("Parameter Value:").append(paramValue).append(",");
            log.debug(logBuff.toString());
        }
        log.debug("----------log end-----------");
    }

    /**
     * Java�������־��ӡ��debug����
     * 
     * @param className ������
     * @param methodName ��������
     * @param paramMap ����б�,key���������ƣ�value������ֵ
     */
    public static void debug(String className, String methodName, String... desc)
    {
        log.debug("----------log start-----------");
        // ��ӡ������
        log.debug("Class Name:" + className);
        // ��ӡ��������
        log.debug("Method Name:" + methodName);
        // ��ӡ��־��Ϣ
        for (String info : desc)
        {
            log.debug("Log Info:" + info);
        }
        log.debug("----------log end-----------");
    }

    /**
     * �쳣������־��ӡ
     * 
     * @param className ������
     * @param methodName ��������
     * @param e �쳣��ջ��Ϣ
     */
    public static void exception(String className, String methodName, Exception e)
    {
        log.error("----------log start-----------");
        // ��ӡ������
        log.error("Class Name:" + className);
        // ��ӡ��������
        log.error("Method Name:" + methodName);
        // ��ӡ�쳣��Ϣ
        log.error("Exception Info", e);
        log.error("----------log end-----------");
    }

    /**
     * ���쳣֮�������������־��ӡ
     * 
     * @param className ������
     * @param methodName ��������
     * @param logDescription ��������
     */
    public static void error(String className, String methodName, String logDescription)
    {
        log.error("----------log start-----------");
        // ��ӡ������
        log.error("Class Name:" + className);
        // ��ӡ��������
        log.error("Method Name:" + methodName);
        // ��ӡ��������
        log.error("Error Info:" + logDescription);
        log.error("----------log end-----------");
    }

    /**
     * ��ӡ������־
     * 
     * @param className ������
     * @param methodName ��������
     * @param logDescription ��������
     */
    public static void warn(String className, String methodName, String logDescription)
    {
        log.warn("----------log start-----------");
        // ��ӡ������
        log.warn("Class Name:" + className);
        // ��ӡ��������
        log.warn("Method Name:" + methodName);
        // ��ӡ��������
        log.warn("Warn Info:" + logDescription);
        log.warn("----------log end-----------");
    }
    
    /**
     * ��ӡinfo��־
     * @param className
     * @param methodName
     * @param logDescription
     */
    public static void info(String className, String methodName, String logDescription)
    {
        log.info("----------log start-----------");
        // ��ӡ������
        log.info("Class Name:" + className);
        // ��ӡ��������
        log.info("Method Name:" + methodName);
        // ��ӡ��������
        log.info("Info:" + logDescription);
        log.info("----------log end-----------");
    }
}
