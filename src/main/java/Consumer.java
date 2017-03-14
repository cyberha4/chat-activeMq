import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by admin on 14.03.2017.
 */
public class Consumer implements Runnable {
    public void run() {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("vm://localhost");
        Connection myconnection = null;
        try {
            myconnection = factory.createConnection();
            myconnection.start();
            Session session = myconnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("queue");
            MessageConsumer messageConsumer = session.createConsumer(destination);
            Message message = messageConsumer.receive(10000);
            System.out.println(((TextMessage) message).getText());

            session.close();
            myconnection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
