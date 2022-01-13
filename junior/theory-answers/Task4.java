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
            return queueManager.accessQueue(queueName, accessMode, timeout);
        }
        catch (MQException mqe) {
            setException(mqe);
            throw mqe;
        }
    }
}


// Здесь используется функциональный интерфейс callable, более навороченный аналог Runnable, позволяющий получить доступ к результату
// выполнения метода единственного в интерфейсе метода call() (тогда как run() просто выполняется), либо к выкинутой в процессе
// ошибке через Future.get(). Такого рода класс описыват некоторую задачу, которую надо будет асинхронно выполнить на пуле потоков,
// а после воспользоваться результатом выполнения. В данном случае, нас скорее всего интересует объект подключения, например для индексирования
// живых коннектов или переиспользования уже созданных, либо мониторинг происходящих ошибок
//
// Из комментариев - метод call не принимает аргументов, сетить эксепшен в нулл в конструкторе не нужно, он и так будет нулл,
// сам механизм сета эксепщена под большим вопросом, доступ к нему можно получить через getCause того исключения, которое вернет Future.get()
// Вероятно, мы хотели сократить таким образом получение исключения именно нужного типа без заморочек с кастом.
// В служебной таске, управляющей подключениями отсутствует начисто логирование. Кажется, что ему тут лучше всё же быть.
// Используются сеттеры, но используются геттеры, скорее всего из лени. Наверное, лучше было бы сделать геттеры на все поля кроме исключения (если он уж так нужен),
// просто сделать их приватными