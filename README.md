# TwitterDataProcessing
Kafka-Consumer which consumes data from the twitter-hashtag-count query and saves it into postgres-database.
Kafka topic should start with tweetcount.* and should contain data of the query "https://api.twitter.com/2/tweets/counts/recent?query=hashtagname". 
The last entry and its corresponding timestamp is than saved into the database as twitterlog entry together with the name of the topic recieved from and 
the tweetcount for the last 7 days.
