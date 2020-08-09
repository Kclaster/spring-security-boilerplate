SELECT id
    , username
    , password
    , auth_user_role_id as roleId
 FROM auth_user
WHERE username = ?