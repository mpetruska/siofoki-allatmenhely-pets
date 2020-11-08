# Siofoki Állatvédő Alapítvány - Pets

## Requierements

- **Sbt**: https://www.scala-sbt.org/
- **Docker compose**: https://docs.docker.com/compose/
- Database backup file

## Notes for developers

### Local start up of project

1. Rename the database backup file to _&lt;project-dir&gt;/dev_setup/docker-entrypoint-initdb.d/00_initial_structure_and_data.sql_
2. Run "make docker-up" to start up the local database.
3. Run "make run" to start up the local web server.
4. Navigate to http://localhost:9000 to talk to the local server.
5. Run "make docker-down" to stop and clean up the local database.
