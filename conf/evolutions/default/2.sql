-- !Ups

CREATE TABLE pets_owner(
  id      CHAR(36) NOT NULL,
  name    TEXT     NOT NULL,
  address TEXT,

  PRIMARY KEY (id)
);

CREATE TABLE pets_animal(
  id                      CHAR(36) NOT NULL,
  name                    TEXT     NOT NULL,
  sex                     TEXT,
  breed                   TEXT,
  color                   TEXT,
  hair_style              TEXT,
  is_birth_date_estimated BIT NOT NULL,
  date_of_birth           DATE,
  height_at_withers       INT,
  chip                    TEXT,
  vaccination_book_number TEXT,
  place_of_inclusion      TEXT,
  date_of_inclusion       DATE,
  context_of_inclusion    TEXT,
  `character`             TEXT,
  is_neutered             BIT NOT NULL,
  date_of_nautering       DATE,
  date_of_adoption        DATE,
  date_of_death           DATE,
  status                  TEXT,
  owner_id                CHAR(36),
  comment                 TEXT,
  
  PRIMARY KEY (id),
  FOREIGN KEY fk_pets_animal_owner_id(owner_id) REFERENCES pets_owner(id)
);

CREATE TABLE pets_image(
  id          CHAR(36) NOT NULL,
  animal_id   CHAR(36) NOT NULL,
  filename    TEXT,
  title       TEXT,
  description TEXT,
  image_data  BLOB,

  PRIMARY KEY (id),
  FOREIGN KEY fk_pets_image_animal_id(animal_id) REFERENCES pets_animal(id)
);

-- !Downs

DROP TABLE pets_image;
DROP TABLE pets_animal;
DROP TABLE pets_owner;
