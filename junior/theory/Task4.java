// Ниже представлен служебный класс, организующий подключения к очереди сообщений
// Что ты можешь рассказать об этом классе? Какие механизмы он использует, в каких конструкциях может быть использован?
// Какие комментарии к этому коду ты можешь предложить?

package ru.some.project.component.wmq;

import com.ibm.mq.MQDestination;
import com.ibm.mq.MQException;
import com.ibm.mq.MQQueueManager;
import java.util.concurrent.Callable;

public class AccessQueueWorker implements Callable<MQDestination> {

    private int accessMode;
    private String queueName;
    private MQQueueManager queueManager;
    private MQException exception;

    public AccessQueueWorker(MQQueueManager manager, String queue, int mode) {
        setQueueManager(manager);
        setQueueName(queue);
        setAccessMode(mode);
        setException(null);
    }

    public void setAccessMode(int accessMode) {
        this.accessMode = accessMode;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public void setQueueManager(MQQueueManager queueManager) {
        this.queueManager = queueManager;
    }

    public MQException getException() {
        return exception;
    }

    public void setException(MQException exception) {
        this.exception = exception;
    }

    @Override
    public MQDestination call(int timeout) throws MQException {
        try{
            return queueManager.accessQueue(queueName, accessMode, mode, timeout);
        }
        catch (MQException mqe) {
            setException(mqe);
            throw mqe;
        }
    }
}
