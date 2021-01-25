package com.awstraining.sqs;

import java.util.concurrent.Future;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnection;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClient;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.buffered.AmazonSQSBufferedAsyncClient;
import com.amazonaws.services.sqs.buffered.QueueBufferConfig;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;

public class PostMessageJMSConnection {
	static String ACCESS_KEY = "AKIAJMMYMHBAO2QUZVKQ";
	static String SECRET_KEY = "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";
	
	public static void main(String args[]) throws JMSException {

		AWSCredentials awsCredentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
		
		final AmazonSQS sqsClient = AmazonSQSClient.builder().withRegion(Regions.US_EAST_2)
				.withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();
		final QueueBufferConfig config = new QueueBufferConfig()
		.withMaxInflightReceiveBatches(5)
		.withMaxDoneReceiveBatches(15);
		SQSConnectionFactory connectionFactory = new SQSConnectionFactory(
				new ProviderConfiguration(),
				sqsClient
				);

		// Create the connection.
		SQSConnection connection = connectionFactory.createConnection();
		
		
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Queue queue = session.createQueue("awstrainingQueue");
		// Create a producer for the 'MyQueue'
		MessageProducer producer = session.createProducer(queue);
		// Create the text message
		TextMessage message = session.createTextMessage("Test Sending a Message - again-test 5 !");
	
		// Send the message
		producer.send(message);
		System.out.println("JMS Message " + message.getJMSMessageID());
		

	}

}
