# godaddy-ddns

DDNS service for GoDaddy. Update A records automatically, on a docker container

## prerequisites

* `docker-compose`
* Generate API Keys from [GoDaddy API Key Management](https://developer.godaddy.com/keys)

## environment variables

<table>
<tr><th>var</th><th>example</th><th>required</th></tr>
<tr><td>GODADDY_API_KEY</td><td>abcdefg01234_hijklmnopq01234567890</td></tr>
<tr><td>GODADDY_API_SECRET</td><td>ABCDEFGHIJKL0123456789</td></tr>
<tr><td>GODADDY_DOMAIN</td><td>domain.com</td></tr>
<tr><td>GODADDY_HOST</td><td>somehost</td></tr>
<tr><td>SCHEDULER_INTERVAL</td><td>60000 (10 minutes)</td></tr>
</table>

to inject environment variables into docker-compose, you can, for example,
create a `.env` file
 
## build and run
```bash
./gradlew build docker
docker-compose up
```

## limitations

* does not support multiple A records
* does not support AAAA records (IPv6)

## how does it work

* get current public ip
* get GoDaddy ip for configured host
* update ip if needed

## Implementation

### Docker

Followed the guidelines from Pivotal [Spring Boot with Docker](https://spring.io/guides/gs/spring-boot-docker/)
