package me.bogeun.yajalal.repository.account;

import me.bogeun.yajalal.entity.account.Role;

public interface AccountDynamicRepository {

    Role getRoleByUsername(String username);

}
