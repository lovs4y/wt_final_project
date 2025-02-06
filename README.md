### Setup
1. Unpack zip to some directory
2. Make sure u running postgres
3. Create new database for this project. Default name in container is wt_banana_bread so it is preferable

#### Docker (no IntelliJ)
4. Go to `setup` folder
5. Build [Dockerfile](setup/Dockerfile) with command `docker build -t wtfinal-project:latest . `
> note: just be in this directory when executing command
6. Prepare ur environment variables:

Go to [docker compose](setup/docker-compose.yml) file and check `environment` block.
Change environment variables according to ur setup

7. Execute next commands in terminal:
```
docker network create postgres_network
docker network connect postgres_network postgres
```
> Note: in the second command 'postgres' is a default name of container. Specify name of ur
> postgres container if it is not default.

8. Run the [Docker compose](setup/docker-compose.yml) file with command: `docker compose up`

#### IntelliJ
4. Go to intellij
5. Push burger menu and `Open...`
6. Select unpacked directory (just directory, no one of packages inside)
7. Choose `Maven` and push `Open`
8. IntelliJ should recognize spring project, and you will see `WtFinalProjectApplication` near the debug button
9. Push to `WtFinalProjectApplication` and choose `Edit configurations...` in list
10. If you have no `Environment Variables` field, go to `More Options` and select `Environment Variables`
11. In the environment variables you have to add key-values to override defaults.

> Note: Here is default environment variables:
        POSTGRES_ADDRESS="postgres" - with intellij config u have to use 'localhost:5432' value (if postgres container working on 5432 port)
        DB_NAME="wt_banana_bread"
        POSTGRES_USERNAME="postgres"
        POSTGRES_PASSWORD="postgres"

12. There you are. Push run/debug button and have fun! :)

### Check application runs correctly
- Fist of all check logs of docker container (if run with docker container)
or in IntelliJ console (if run with IntelliJ)
- If everything clear go to `http://localhost:8080/swagger-ui/index.html` - 
u will see Spring Doc page with project name. It means project works correctly

> note: here specified port (8011) by default, if you changed it - specify your own