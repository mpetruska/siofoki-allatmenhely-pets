.PHONY: help
help:
	@echo "Usage: make <target>"
	@echo "  where"
	@echo "-------------------------------------------------"
	@echo "| Target            | Command                   |"
	@echo "|-------------------|---------------------------|"
	@echo "| docker-up         | docker-compose up -d      |"
	@echo "| docker-down       | docker-compose down       |"
	@echo "| ----------------- | ------------------------- |"
	@echo "| sbt-clean         | sbt clean                 |"
	@echo "| sbt-compile       | sbt test:compile          |"
	@echo "| sbt-test          | sbt test                  |"
	@echo "| scala-repl        | sbt console               |"
	@echo "| run               | sbt run                   |"
	@echo "| ----------------- | ------------------------- |"
	@echo "| db-generate       | cd db && sbt run && cd .. |"
	@echo "| ----------------- | ------------------------- |"
	@echo "| bloop-install     | sbt bloopInstall          |"
	@echo "| bloop-clean       | bloop clean               |"
	@echo "| bloop-compile     | bloop compile CherriskAPI |"
	@echo "| bloop-test        | bloop test CherriskAPI    |"
	@echo "-------------------------------------------------"

.PHONY: docker-up
docker-up:
	pushd dev_setup && docker-compose --project-name pets up -d && popd

.PHONY: docker-down
docker-down:
	pushd dev_setup && docker-compose --project-name pets down && popd

.PHONY: sbt-clean
sbt-clean:
	sbt clean

.PHONY: sbt-compile
sbt-compile:
	sbt test:compile

.PHONY: sbt-test
sbt-test:
	sbt test

.PHONY: scala-repl
scala-repl:
	sbt console

.PHONY: run
run:
	sbt run

.PHONY: db-generate
db-generate:
	pushd db && sbt run && popd

.PHONY: bloop-install
bloop-install:
	sbt bloopInstall

.PHONY: bloop-clean
bloop-clean:
	bloop clean

.PHONY: bloop-compile
bloop-compile:
	bloop compile root

.PHONY: bloop-test
bloop-test:
	bloop test root
