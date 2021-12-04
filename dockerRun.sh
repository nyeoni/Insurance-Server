#!/bin/bash
echo "Zipkin 실행 시작"
docker run -d -p 9411:9411 --network insurance-network --name zipkin-service  openzipkin/zipkin
echo "Zipkin 실행"
sleep 3

echo "Config-Service 실행 시작"
docker run -d -p 8888:8888 --network insurance-network --name config-service choiys0212/config-service
echo "Config-Service 실행"
sleep 10

echo "Discovery-Service 실행 시작"
docker run -d -p 8761:8761 --network insurance-network --name discovery-service choiys0212/discovery-service
echo "Discovery-Service 실행"
sleep 5

echo "Gateway-Service 실행 시작"
docker run -d -p 8000:8000 --network insurance-network --name gateway-service -e "spring.config.import=optional:configserver:http://config-service:8888" -e "spring.cloud.config.profile=docker" choiys0212/gateway-service
echo "Gateway-Service 실행"
sleep 3

echo "Insurance-Service 실행 시작"
docker run -d --network insurance-network --name insurance-service -e "spring.config.import=optional:configserver:http://config-service:8888" -e "spring.cloud.config.profile=docker" choiys0212/insurance-service
echo "Insurance-Service 실행"
sleep 3

echo "User-Service 실행 시작"
docker run -d --network insurance-network --name user-service -e "spring.config.import=optional:configserver:http://config-service:8888" -e "spring.cloud.config.profile=docker" choiys0212/user-service
echo "User-Service 실행"
sleep 3

echo "Contract-Service 실행 시작"
docker run -d --network insurance-network --name contract-service -e "spring.config.import=optional:configserver:http://config-service:8888" -e "spring.cloud.config.profile=docker" choiys0212/contract-service
echo "Contract-Service 실행"
sleep 3

echo "Client-Service 실행 시작"
docker run -d --network insurance-network --name client-service -e "spring.config.import=optional:configserver:http://config-service:8888" -e "spring.cloud.config.profile=docker" choiys0212/client-service
echo "Client-Service 실행"
sleep 3

