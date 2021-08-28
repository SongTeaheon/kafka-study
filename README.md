# kafka-study
Kafka study (Spring, Gradle)

## kafka 설정
1. docker설치
2. docker 실행 `docker-compose up -d`
3. docker 확인 `docker ps`
4. kafka-cli 설치. 
5. 설치한 곳의 bin directory로 접근
6. topic 목록 확인 `./kafka-topics.sh --bootstrap-server localhost:9092 --list`
7. docker 종료 `docker-compose down`

## level1 테스트

1. consumer cli로 실행 `kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test-topic`
2. 다음 curl 실행 `curl -X POST "localhost:8080/produce?message=test"`
3. consumer에서 전달된 test가 오는지 확인
