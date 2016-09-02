package net.tfgzs.passport.user

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import net.tfgzs.common.encrypt.PasswordHash
import net.tfgzs.common.kit.CustomValidator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@CompileStatic
@Slf4j
@RestController
@RequestMapping(value = ["/user"], produces = ["application/json"])
class UserController {

    @Autowired
    UserRepository userRepository

    /**
     * 登录
     * @param principals 身份，邮箱/手机号
     * @param credentials 凭证，密码/短信验证码
     */
    @RequestMapping("/login")
    public User login(String principals, String credentials) {
        if (CustomValidator.isEmail(principals)) {
            User user = userRepository.findByEmail(principals)
            if (user && PasswordHash.validatePassword(credentials, user.password)) {
                user
            } else {
                throw new RuntimeException("用户名或密码错误")
            }
        } else if (CustomValidator.isMobile(principals)) {
            User user = userRepository.findByMobile(principals)
            String redis = ""   // 从Redis数据库里面查询验证码 TODO
            if (user && credentials.equals(redis)) {
                user
            } else {
                throw new RuntimeException("用户名或密码错误")
            }
        } else {
            throw new RuntimeException("用户名或密码错误")
        }
    }

    /**
     * 注册
     */
    @Transactional
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public User register(String nickname, String principals, String credentials) {
        if (CustomValidator.isEmail(principals)) {
            User user = new User(nickname: nickname, email: principals, password: PasswordHash.createHash(credentials))
            User u = userRepository.save(user)
            return u
        } else if (CustomValidator.isMobile(principals)) {
            User user = new User(nickname: nickname, mobile: principals, password: PasswordHash.createHash(credentials))
            return userRepository.save(user)
        } else {
            throw new RuntimeException("用户名或密码不符合规范")
        }
    }
    /**
     *  重置密码
     * @param principals 身份，邮箱/手机号/手机号
     * @param oldCredentials 凭证，旧密码/旧密码/短信验证码
     * @param newCredentials 凭证，新密码
     * @return
     */
    @Transactional(readOnly = false)
    @RequestMapping("/resetPwd")
    def resetPwd(String principals, String oldCredentials, String newCredentials) {
        if (CustomValidator.isEmail(principals)) {
        } else if (CustomValidator.isMobile(principals)) {
            if (oldCredentials && oldCredentials.length() <= 6) {//短信验证码

            } else {//旧密码

            }
        } else {
            new RuntimeException("用户名或密码错误")
        }
    }

    /**
     * 锁定用户
     */
    @Transactional(readOnly = false)
    @RequestMapping("/lock")
    def lock(String uid) {

    }
    /**
     * 逻辑删除用户
     */
    @Transactional(readOnly = false)
    @RequestMapping("/deleteLogic")
    def deleteLogic(String uid) {

    }


    @RequestMapping("/findByEmail")
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
    }

//    @RequestMapping("/list")
//    public Page<User> list(UserCriteria user) {
//        Pageable pageable = new PageRequest(0, 10);
//        Specification specification = new Specification<UserCriteria>() {
//            @Override
//            Predicate toPredicate(Root<UserCriteria> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
//                Path<String> username = root.get("username")
//                Path<String> password = root.get("password")
//
//                List<Predicate> searchList = new ArrayList<Predicate>();
//
//                if (user.username) searchList.add cb.equal(username, user.username)
//                if (user.password) searchList.add cb.equal(password, user.password)
//
//                Predicate[] pd = new Predicate[searchList.size()];
//                return cb.and(searchList.toArray(pd));
//            }
//        }
//        userRepository.findAll(specification, pageable)
//    }

}
