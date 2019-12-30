# kafka 知识点 #
## 概述 ##
    Kafka 是一个分布式的基于发布/订阅模式的消息队列（Message Queue） ， 主要应用于
    大数据实时处理领域。
## 快速入门 ##
### 安装部署 ###
- 安装集群
- 启动集群 , 先启动 zk 集群 , 再启动 kafka 。 nohup  /opt/module/kafka/bin/kafka-server-start.sh config/server.properties  & (踩坑 : 由于之前安装过 kafka 了 , 会导致 kafka.common.InconsistentClusterIdException)
#### 命令行操作 ####
    topic 增删改查 , 发送消息 , 消费消息
- ./bin/kafka-topics.sh --zookeeper gh103:2181 --describe  --topic t01
- ./bin/kafka-topics.sh  --zookeeper  gh102:2181  --delete   --topic  topic01
- ./bin/kafka-topics.sh --zookeeper  gh103:2181 --list
- ./bin/kafka-topics.sh --zookeeper gh104:2181 --create --replication-factor 3 --partitions 1 --topic topic01
- ./bin/kafka-console-producer.sh  --broker-list gh102:9092 --topic t01
- ./bin/kafka-console-consumer.sh --topic t01 --bootstrap-server gh102:9092,gh103:9092,gh104:9092 --from-beginning