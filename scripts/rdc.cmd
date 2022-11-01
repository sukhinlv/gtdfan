REM Run docker postgres test container "gtdfan_db"
REM db name = gtdfan
REM user is "user" and password is "password"
REM local port is 5433
docker run -p 5433:5432 -d --name gtdfan_db -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password -e POSTGRES_DB=gtdfan postgres
