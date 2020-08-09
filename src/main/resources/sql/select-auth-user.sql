SELECT id
    , username
    , password
    , auth_user_role_id as roleId
    , is_expired as isExpired
    , is_locked as isLocked
    , is_credentials_expired as isCredentialsExpired
    , is_enabled as isEnabled
 FROM auth_users
WHERE username = ?