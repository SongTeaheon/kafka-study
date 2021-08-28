# kafka-study
Kafka study (Spring, Gradle)

## 기본 설정
Spring Web
lombok

## kafka 설정
1. docker설치
2. docker 실행 `docker-compose up -d`
3. docker 확인 `docker ps`
4. kafka-cli 설치.
5. 설치한 곳의 bin directory로 접근
6. topic 목록 확인 `./kafka-topics.sh --bootstrap-server localhost:9092 --list`
7. docker 종료 `docker-compose down`

## level1
### level1 변경 사항
1. producer bean 설정
2. producer이용해서 controller로 produce
3. consumer bean 설정
4. consumer 이용해서 while문으로 consume (api 동작하지 않음)
5. consumer 이용해서 다른 스레드로 while문 consume (api 동작함)
### level1 테스트
1. consumer cli로 실행 `kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test-topic`
2. 다음 curl 실행 `curl -X POST "localhost:8080/produce?message=test"`
3. consumer에서 전달된 test가 오는지경확인

## level2
### level2 변경사항
1. level1의 VanillaKafkaConfig 삭제
2. ProducerConfig와 ConsumerConfig 설정
3. Consumer Config 설정시 @EnableKafka 주의
4. Producer Controller에서 KafkaTemplate을 바꾸도록 변경
5. ConsumerProcessor에서 Kafka Listener Annotation을 쓰도록 변경

### level2 테스트
1. 다음 curl 실행 `curl -X POST "localhost:8080/produce?message=test"`
2. consumer에서 찍는 로그 확인

### 확인사항
- Spring Kafka는 ConsumerBean이 없어도 localhost:9092를 보도록 했음! -> 이 부분이 변경가능한지 확인 필요


## Scheme Registry (using Avro) (LEVEL3)
### Scheme Registry 변경 사항
1. docker-compose에서 kafka + scheme registry를 올려봄
2. gradle에서 avro 관련 플러그인 추가
3. avro 자동 생성 및 serializer 디펜던시 추가
4. .avsc 파일로 Scheme Avro 만듦 (gradle build 또는 generateAvroJava를 통해서 /build/generated-main-avro-java에 자동생성됨)
5. Producer 및 Consumer Bean 설정 변경 (scheme registry url + serializer 변경)
6. consumer 변경 : consume 해서 사용할 수 있도록 변경.

### Scheme Registry 테스트
다음 curl로 확인 (consumer 후 로그가 찍히는지 확인!)  
`curl --header "Content-Type: application/json" -X POST   --data '{"firstName" : "song", "lastName":"taeheon"}'   http://localhost:8080/produce`
### 참고
https://medium.com/@gaemi/kafka-와-confluent-schema-registry-를-사용한-스키마-관리-1-cdf8c99d2c5c
https://medium.com/@gaemi/kafka-와-confluent-schema-registry-를-사용한-스키마-관리-2-bfa96622a974
https://medium.com/@gaemi/kafka-와-confluent-schema-registry-를-사용한-스키마-관리-3-96b0f070d0f1
