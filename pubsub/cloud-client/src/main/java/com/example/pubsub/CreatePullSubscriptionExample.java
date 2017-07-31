/*
  Copyright 2017, Google, Inc.

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
*/

package com.example.pubsub;

// [START pubsub_quickstart_create_subscription]
import com.google.cloud.ServiceOptions;
import com.google.cloud.pubsub.v1.SubscriptionAdminClient;
import com.google.pubsub.v1.PushConfig;
import com.google.pubsub.v1.Subscription;
import com.google.pubsub.v1.SubscriptionName;
import com.google.pubsub.v1.TopicName;

public class CreatePullSubscriptionExample {

  public static void main(String... args) throws Exception {

    // Your Google Cloud Platform project ID
    String projectId = ServiceOptions.getDefaultProjectId();

    // Your topic ID, eg. "my-topic"
    String topicId = args[0];

    // Your subscription ID eg. "my-sub"
    String subscriptionId = args[1];

    TopicName topicName = TopicName.create(projectId, topicId);

    // Create a new subscription
    SubscriptionName subscriptionName = SubscriptionName.create(projectId, subscriptionId);
    try (SubscriptionAdminClient subscriptionAdminClient = SubscriptionAdminClient.create()) {
      // create a pull subscription with default acknowledgement deadline (= 10 seconds)
      Subscription subscription =
          subscriptionAdminClient.createSubscription(
              subscriptionName, topicName, PushConfig.getDefaultInstance(), 0);
    }

    System.out.printf(
        "Subscription %s:%s created.\n",
        subscriptionName.getProject(), subscriptionName.getSubscription());
  }
}
// [END pubsub_quickstart_create_subscription]
