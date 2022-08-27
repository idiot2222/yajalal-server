package me.bogeun.yajalal.repository.account;

import me.bogeun.yajalal.entity.Role;

public interface AccountDynamicRepository {

    Role getRoleByUsername(String username);

}
