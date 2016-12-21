package getBlob;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class LogUtil {
	private static Logger log = LoggerFactory.getLogger(LogUtil.class);

    /**
     * 入参日志打印，debug级别
     * 
     * @param className 类名称
     * @param methodName 方法名称
     * @param logMap 入参列表,key：参数名称，value：参数值
     */
    public static void debug(String className, String methodName, Map<String, String> logMap)
    {
        log.debug("----------log start-----------");

        // 打印类名称
        log.debug("Class Name:" + className);
        // 打印方法名称
        log.debug("Method Name:" + methodName);
        // 打印参数列表
        Set<String> keySet = logMap.keySet();
        Iterator<String> iter = keySet.iterator();

        // 参数名称作为key
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
     * Java代码块日志打印，debug级别
     * 
     * @param className 类名称
     * @param methodName 方法名称
     * @param paramMap 入参列表,key：参数名称，value：参数值
     */
    public static void debug(String className, String methodName, String... desc)
    {
        log.debug("----------log start-----------");
        // 打印类名称
        log.debug("Class Name:" + className);
        // 打印方法名称
        log.debug("Method Name:" + methodName);
        // 打印日志信息
        for (String info : desc)
        {
            log.debug("Log Info:" + info);
        }
        log.debug("----------log end-----------");
    }

    /**
     * 异常错误日志打印
     * 
     * @param className 类名称
     * @param methodName 方法名称
     * @param e 异常堆栈信息
     */
    public static void exception(String className, String methodName, Exception e)
    {
        log.error("----------log start-----------");
        // 打印类名称
        log.error("Class Name:" + className);
        // 打印方法名称
        log.error("Method Name:" + methodName);
        // 打印异常信息
        log.error("Exception Info", e);
        log.error("----------log end-----------");
    }

    /**
     * 除异常之外的其他错误日志打印
     * 
     * @param className 类名称
     * @param methodName 方法名称
     * @param logDescription 错误描述
     */
    public static void error(String className, String methodName, String logDescription)
    {
        log.error("----------log start-----------");
        // 打印类名称
        log.error("Class Name:" + className);
        // 打印方法名称
        log.error("Method Name:" + methodName);
        // 打印错误描述
        log.error("Error Info:" + logDescription);
        log.error("----------log end-----------");
    }

    /**
     * 打印警告日志
     * 
     * @param className 类名称
     * @param methodName 方法名称
     * @param logDescription 错误描述
     */
    public static void warn(String className, String methodName, String logDescription)
    {
        log.warn("----------log start-----------");
        // 打印类名称
        log.warn("Class Name:" + className);
        // 打印方法名称
        log.warn("Method Name:" + methodName);
        // 打印错误描述
        log.warn("Warn Info:" + logDescription);
        log.warn("----------log end-----------");
    }
    
    /**
     * 打印info日志
     * @param className
     * @param methodName
     * @param logDescription
     */
    public static void info(String className, String methodName, String logDescription)
    {
        log.info("----------log start-----------");
        // 打印类名称
        log.info("Class Name:" + className);
        // 打印方法名称
        log.info("Method Name:" + methodName);
        // 打印错误描述
        log.info("Info:" + logDescription);
        log.info("----------log end-----------");
    }
}
