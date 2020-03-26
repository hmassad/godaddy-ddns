# godaddy-ddns

DDNS service for GoDaddy, update A records with docker container public IP address

## prerequisites

* `docker-compose`
* Generate production GoDaddy API Keys from [GoDaddy API Key Management](https://developer.godaddy.com/keys)

## environment variables

<table>
<tr><th>var</th><th>example</th></tr>
<tr><td>GODADDY_API_KEY</td><td>abcdefg01234_hijklmnopq01234567890</td></tr>
<tr><td>GODADDY_API_SECRET</td><td>ABCDEFGHIJKL0123456789</td></tr>
<tr><td>GODADDY_DOMAIN</td><td>domain.com</td></tr>
<tr><td>GODADDY_HOST</td><td>somehost</td></tr>
<tr><td>SCHEDULER_INTERVAL</td><td>600000 (10 minutes)</td></tr>
</table>

to inject environment variables into docker-compose, you can, for example,
create a `.env` file

## build and run

```bash
./gradlew build docker
docker-compose up
```

gradle needs java 8, you can specify which jdk to use as

```bash
./gradlew -Dorg.gradle.java.home=/usr/lib/jvm/java-1.8.0-openjdk-amd64 build docker
docker-compose up
```

## limitations

* supports only one A record
* does not support multiple A records
* does not support AAAA records (IPv6)

## how does it work

* get current public IP address
* get GoDaddy IP address for configured host
* update IP address if needed

## Implementation

### Docker

Followed the guidelines from Pivotal [Spring Boot with Docker](https://spring.io/guides/gs/spring-boot-docker/)
