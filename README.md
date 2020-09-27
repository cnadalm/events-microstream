# events-microstream project


## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```


## Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `events-microstream-1.0.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/events-microstream-1.0.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/events-microstream-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.


## DATA

curl http://localhost:9080/api/v1/profiles
curl http://localhost:9080/api/v1/events?postalCode=08000&postalCode=08001

### Create some profiles
curl -X POST -H "Content-Type: application/json" -d '{"name":"Javier", "avatar":"javi-avatar"}' http://localhost:9080/api/v1/profiles
curl -X POST -H "Content-Type: application/json" -d '{"name":"Juan", "avatar":"juan-avatar"}' http://localhost:9080/api/v1/profiles
curl -X POST -H "Content-Type: application/json" -d '{"name":"Jose", "avatar":"jose-avatar"}' http://localhost:9080/api/v1/profiles
curl -X POST -H "Content-Type: application/json" -d '{"name":"Julio", "avatar":"julio-avatar"}' http://localhost:9080/api/v1/profiles
curl -X POST -H "Content-Type: application/json" -d '{"name":"Juanito", "avatar":"juanito-avatar"}' http://localhost:9080/api/v1/profiles
curl -X POST -H "Content-Type: application/json" -d '{"name":"Jese", "avatar":"jese-avatar"}' http://localhost:9080/api/v1/profiles

### Create some events (provide an organizer ID)
curl -X POST -H "Content-Type: application/json" -d '{"title":"Event title", "description":"Awesome event"}' http://localhost:9080/api/v1/events\?postalCode\=08000\&organizerId\=
curl -X POST -H "Content-Type: application/json" -d '{"title":"Event title", "description":"Awesome event"}' http://localhost:9080/api/v1/events\?postalCode\=08001\&organizerId\=

### Add some participants to an event (provide an event and participant IDs)
curl http://localhost:9080/api/v1/events/62ff813a-983f-40a8-93ed-6bb166ae4bff/join\?profileId\=1a951358-e4cf-40e4-8aba-7ea071fef0df



## Microstream Rest API
java -jar storage.restclient.app-03.00.00-MS-GA.jar --port=80