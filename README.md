# spring-reactive-example
Spring WebFlux example service and client

### Curl commands

Annotation based controller calls:

```sh
curl -s -uuser:password http://localhost:8080/users
curl -s -uuser:password http://localhost:8080/users/Tom
curl -s -H 'Content-type: application/json' -d '{"name":"Vince"}' -uuser:password http://localhost:8080/users
```
For functional styled defined routs calls:
```sh
curl -s -uuser:password http://localhost:8080/usrs
curl -s -uuser:password http://localhost:8080/usrs/Tom
curl -s -H 'Content-type: application/json' -d '{"name":"Vince"}' -uuser:password http://localhost:8080/usrs
```