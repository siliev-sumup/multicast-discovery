### Intent

The intent of this project is to attempt to show a novel idea for a universal UDP based, efficient service discovery mechanism, not dependent on deployment infrastructure and usable anywhere.

### Problem Stament

On the highest level "modern" microservice architectures generally have two ways of implementing service discovery:
1. Have a key-value store keeping service_name->service_host mappings
2. Have a separate discovery service that other services in the cluster register to when joining the cluster

There is a third approach that a subset of services use however - UDP multicast discovery.
The basic idea behind it is to multicast a message to a multicast address, to which all services in a cluster are subscribed, containing all data required for other services to discover and make requests to a new joiner. 
Currently the only example I know of this approach is [Hazelcast](https://hazelcast.com/)'s [default discovery mechanism](https://docs.hazelcast.com/imdg/latest/clusters/discovering-by-multicast.html).

### Advantages

#### Members in the cluster fully own their addresses
When having a common place to store service_name->address mappings, this place becomes a friction point for all teams. Conventions/responsible people/maintainance becomes harder.

#### No additinal services to maintain
[Eureka](https://github.com/Netflix/eureka) for example, although mature and widely used, adds complexity (polling strategies to consider, scaling to consider, can be a single point of failure) and time has to be spend on upgrades and maintenance.

### Disadvantages

#### ??? opinions here greatly appreciated :)

### PoC Details
The current implemantation deployes two Spring services in a `docker-compose` network with enabled UDP. When started each service multicasts a `join` message containing the service name and address to a common multicast address. Every service subscribed to the address "hears" the message and adds the new joiner to its own in-memory storage of known cluster members.

PoC consist of two services, `one-service` and `two-service` each exposing the same endpoits (listed bellow).

### Running the PoC

#### Requirements
[JDK 13](https://jdk.java.net/13/), [Docker](https://docs.docker.com/get-docker/)

#### Build
`./mvnw clean install`

#### Run
`docker-compose up`

### Endpoints

The bellow examples assume PoC is running on localhost.

`GET http://localhost:8081/v0.1/ping` - pings the service, returns `200` if everything is running ok

`GET http://localhost:8081/v0.1/ping/all`- pings (calls the endpoint described above of) all services discovered by this service via listening for UDP `join` messages on a multicast address

### Disclaimer

I literally wrote this in 4 hours, just to test whether it will work, so I won't be taking any comments on code quality :D Anyone is free and very welcome to open a PR and fix anything. Thanks for reading up to here!
