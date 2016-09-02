package net.tfgzs.passport.user

import net.tfgzs.common.base.BaseRepository


interface UserRepository extends BaseRepository<User, String> {
    User findByEmail(String email)

    User findByMobile(String mobile)
}
