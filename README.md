# E-commerce Project (Backend-structure)
### _Simple e-commerce project with microservice architecture_ 

---

This was build to reinforce my knowledge in Backend Web Architecture, focusing in RESTful API for data transfer and a microservice architecture for possible scalability, the folowing techologies were used:

- Java
- Spring Boot (Web, Lombok, Jpa, OpenFeign, Eureka, Config, Gateway, etc...)
- Kafka & Zookeeper (Message Broker)
- Postgresql | MongoDB | Redis
- Docker (docker-compose.yml)
- JS | CSS | HTML ***[FUTURE ADDITION]***

## Structure

The following is the structure of the services withing the project:

| Gateway | Discovery-Server | Config_Server | Product | Payment | Customer | Order | Cart | Notification | Discount |
| ------ | ------ | ------ | ------ | ------ | ------ | ------ | ------ | ------ | ------ |
| Reactive Gateway & _Keyclock_ | Netflix Eureka Server | /settings [repository] | add/purchase _endpoints_ | add _endpoint_ | add/findById/ findByCpfCnpj _endpoints_ | add _enpoint_ & OpenFeign | Redis | Kafka & Mail notification | add/ getDiscountByProductId _endpoints_|

## Docker - SETUP
##### Use docker to start daemon & configure all necessaries technologies:
- Kafka & Zookeeper
- PostgreSQL | MongoDB | Redis
- ***[FUTURE ADDITION]***

```sh
cd <Xalv>
docker compose up -d
