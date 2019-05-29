# PaytmLabs SDE Challenge

## Coding Question

Write an interface for a data structure that can provide the moving average of the last N elements added, add elements to the structure and get access to the elements. Provide an efficient implementation of the interface for the data structure.

### Minimum Requirements

1. Provide a separate interface (IE `interface`/`trait`) with documentation for the data structure
2. Provide an implementation for the interface
3. Provide any additional explanation about the interface and implementation in a README file.

## Design Question

Design A Google Analytic like Backend System.
We need to provide Google Analytic like services to our customers. Please provide a high level solution design for the backend system. Feel free to choose any open source tools as you want.

### Requirements

1. Handle large write volume: Billions of write events per day.
2. Handle large read/query volume: Millions of merchants wish to gain insight into their business. Read/Query patterns are time-series related metrics.
3. Provide metrics to customers with at most one hour delay.
4. Run with minimum downtime.
5. Have the ability to reprocess historical data in case of bugs in the processing logic.

## Explanation for Coding Question
Maintain a slice (queue) which contains the last N elements.
1. O(1) - Assuming speed is first priority: 
When an elements is arrived: add it into the slice and remove the oldest elements from the slice if the slice has reached N size. The new average will be <b>((old_average * slice_size) - oldest_element + new_arrived_element) / slice_size</b>.
2. O(N) - If accuracy is more important, then there is another implementation which calculate the average of all the elements in the slice.

A simple testing case is under the test module.

## Explanation Design Question
The design graph PNG is under ${project}.
1. For microservices, it will use the Spring Cloud echo system which includes Eureka, Ribbon, Hystrix and Spring Cloud Config. All the logs will send to Logging system via Kafka by Spring AOP.
2. MySQL_1 is used as a buffer for business module. All the data will sync to Hadoop and SQL_2, SQL_3, SQL_4 (and so on) by Canal.
3. Incoming data will be synchronized into MySQL cluster instantly. Slice system will fetch the data from the cluster and send result into MySQL_5 cluster.
4. Incoming data will also be synchronized into Hadooop periodically. Offline system will calculate the incremental data and historical data, merge the results into MySQL_5 cluster.
5. The incoming query will be able to find the result set from MySQL_5.