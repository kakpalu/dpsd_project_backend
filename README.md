# dpsd_project_backend

The Backend application for the Pig Farmer project

## Pre-requisite

-Docker

## Running in development

in the root of the project run the command ```docker-compose up ```

## To view API Doc

To view swagger documentation hit the api http://localhost:8001/api/swagger/

## To View Database records 
 enter into the db container by running the command ```docker exec -it pf_db bash```
 
 then run the command ```psql -U postgres ```
 
 then run the command ```select * from farmers;```