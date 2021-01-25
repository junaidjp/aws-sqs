package com.awstraining.sqs;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.CreateQueueResult;


public class CreateQueue {
	
	static String ACCESS_KEY = "AKIAJMMYMHBAO2QUZVKQ";
	static String SECRET_KEY = "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";
	
	public static void main(String args[]) {

		AWSCredentials awsCredentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
		
		AmazonSQS sqsClient = AmazonSQSClient.builder().withRegion(Regions.US_EAST_2)
					.withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();
		final CreateQueueRequest createQueueRequest = new CreateQueueRequest("awstrainingQueue");
		final CreateQueueResult queueResult = sqsClient.createQueue(createQueueRequest);

		// Print the Queue URL.
		System.out.println("QUeueURL:" + queueResult.getQueueUrl());
		    
		// Print the request ID for the CreateTopicRequest action.
		System.out.println("CreateQueueRequest: " + sqsClient.getCachedResponseMetadata(createQueueRequest));
		
		
		

	}

}
