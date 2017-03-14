import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


/**
 * Created by admin on 14.03.2017.
 */
public class Producer implements Runnable {
    public void run() {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("vm://localhost");
        Connection myconnection = null;
        try {
            myconnection = factory.createConnection();
            myconnection.start();
            Session session = myconnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("queue");
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);//отменяем кеширование
            TextMessage textMessage = session.createTextMessage("sieg heil");
            producer.send(textMessage);
            textMessage = session.createTextMessage("sieg heil");
            producer.send(textMessage);
            session.close();
            myconnection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
