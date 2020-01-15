# Getting Started

University assignment project needs docker and docker-compose for starting.

After cloning the project, You must run these two commands to create docker image and running docker compose, to prepare
other containers.

1. > docker build . -t contact:1.0
2. > docker-compose up

* The project will be available on port 8085, and the base path is:
> */resources*
* All the APIs supports *Hateoas*
> http://localhost:8085/resources/contact
(open with Firefox for better formatting)
# Without Docker
You can also bootstrap project without docker.
in this situation you should change mongodb connection string. you can do that by changing configs from **application.properties**

# Test APIs
For testing apis there is swagger-ui that will be available through this address: 
> http://localhost:8085/swagger-ui.html

