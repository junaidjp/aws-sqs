package com.awstraining.sqs;

import java.util.concurrent.Future;

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
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;

public class PostMessageOnQueue {
	static String ACCESS_KEY = "AKIAJMMYMHBAO2QUZVKQ";
	static String SECRET_KEY = "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";
	
	public static void main(String args[]) {

		AWSCredentials awsCredentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
		
		final AmazonSQSAsync sqsAsync = AmazonSQSAsyncClient.asyncBuilder().withRegion(Regions.US_EAST_2)
				.withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();
		final QueueBufferConfig config = new QueueBufferConfig()
		.withMaxInflightReceiveBatches(5)
		.withMaxDoneReceiveBatches(15);
		

		final AmazonSQSAsync bufferedSqs = new AmazonSQSBufferedAsyncClient(sqsAsync, config);
		final SendMessageRequest request = new SendMessageRequest();
		final String body = "Your message text" + System.currentTimeMillis();
		request.setMessageBody( body );
		request.setQueueUrl("https://sqs.us-east-2.amazonaws.com/264455084456/awstrainingQueue");
		final Future<SendMessageResult> sendResult = bufferedSqs.sendMessageAsync(request);
		
		

	}

}
