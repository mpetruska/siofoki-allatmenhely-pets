package models.json

case class Dog(
  id:    Long,
  name:  String,
  sex:   Sex.Value,
  breed: String,
  color: String,
  
)
