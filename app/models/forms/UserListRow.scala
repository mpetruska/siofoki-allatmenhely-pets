package models.forms

import java.util.UUID

case class UserListRow(
  id:                UUID,
  username:          String,
  fullName:          Option[String],
  isAdmin:           Boolean,
  isEnabled:         Boolean,
  canUpdatePassword: Boolean,
  canDelete:         Boolean,
  canUpdate:         Boolean,
)
