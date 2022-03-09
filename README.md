# spring-kafka-binder-poc

This application is a poc to try out the new Spring Cloud Stream Kafka Binders new Functional Programming style.

Inorder to write test case and for the sake of simplicity, we will be using then Spring-kafka and Spring-kafka-test dependency 
to publish message to an embedded kafka instance(Spring kafka test provides this for us and makes our testing easy).

We will define Beans which will listen to input toppics and then publish the message after transforming it to another topic
all using the prefered functional style of programming.
