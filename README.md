# RESTfulservice-using-docker-springboot
This repository is on RESTful service using Docker and Spring Boot. For the front end I have used HTML, Jquery and Ajax to display data from Json.

## Files Included
1. RestAPI folder which has the Spring Boot application and the Docker file.
2. It also consists of HTML file (UI)
## About the application
So this is a Spring Boot application with a hardcode Json data (Customer.json) and it consists of 4 GET API menthods.

We then create a Docker file and build and run it to create a Docker Container which will fetch us the above data.

Then we use a webpage (simple front end UI) to show the endpoints in action.

## Steps to follow
1. First build the docker file by using command
### `docker build -t anyname .` (It can be anyname after -t)
2. Then run the docker file by using the command
### `docker run -p 8081:8081 anyname` ( the name that you gave after -t)

So with this the docker file is up and running

Now just open the service.html file and the code should be running as expected.


