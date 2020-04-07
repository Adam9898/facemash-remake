#facemash-remake
The original creator of facemash was Mark Zuckerberg. The main idea behind this small web app
is that it lets you compare 2 people, based on their photo.
It was probably coded in php or something similar, but I used
Spring boot 2 for the backend. The frontend is mostly typescript with scss, and webpack is used
to compile the ts and scss files.

You can use docker to run facemash-remake:
`docker-compose up
`
will initialize the required containers.
_Note: the initializing process will take a while, especially on the first run.
The mysql database needs to be FULLY initialized before you start using the application.
You will know that mysql is ready when you see a message saying something along the lines of_
`[Server] X Plugin ready for connections. Socket: '/var/run/mysqld/mysqlx.sock' bind-address: '::' port: 33060`


Once the containers are ready, open up your favorite internet browser and head over to
`localhost:8080` where you will be able to interact with facemash. Register an account
and then you can start the voting :)

You can stop facemash-remake by using `docker-compose down`
This will remove the built containers, so next time you can start fresh ad clean.
If you want to save the containers use `docker-compose stop` instead. Use `docker-compose start` to re-run the containers.

Unit tests are included on both the fronted and backend. Backend tests were written in Junit 5 and frontend tests are
based on Jasmine. The tests are not complete, and I might continue to refine them in the future. Make sure to run the mysql
container before running the junit tests, otherwise the test responsible for testing the spring boot application context
will fail. I did NOT provide a docker environment for testing purposes, so if you want to run the tests, make sure you
have the proper tools installed on your computer for it. This mainly includes nodejs, npm, java, and gradle.

The project uses webpack, so if you want to create "production" ready code manually, make sure to run `npm install` and `npm run dev`
in order compile the typescript and scss files. For further instructions on how to build the backend, just read
the Dockerfile where you will find all the necessary information. In short, you need gradle to build the production ready
java code.

**Legal note**

I created this software for free use, and it is NOT under any licence.
The images used in the application were distributed on various websites that did NOT require
any registration or other types of authentication hence they are public data. The images are cropped, but everything else
including image metadata and additional watermarks are left as they were, and that provides a way to credit the authors/owners
of the particular image. The images are only left inside the application to help users test the software, and nothing more.
You can find the original pictures on the web, just use a tool like Google to search for them.

I don't make any money from the use of the pictures nor the software.