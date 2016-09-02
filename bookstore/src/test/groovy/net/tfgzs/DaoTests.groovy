package net.tfgzs

import net.tfgzs.common.encrypt.PasswordHash
import net.tfgzs.passport.user.User
import net.tfgzs.passport.user.UserRepository
import net.tfgzs.passport.user.User_Gender
import net.tfgzs.passport.user.User_Status
import org.joda.time.DateTime
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner.class)
@SpringBootTest
public class DaoTests {

    @Autowired
    UserRepository userRepository

    @Test
    public void register() {
        User user = new User(nickname: "tom", email: "867805932@qq.com", password: PasswordHash.createHash("123456"))
        User u = userRepository.save(user)
        println u
    }

    @Test
    public void addUser() {
        User user = new User()
        user.username = "李连杰"
        user.nickname = "tom"
        user.password = "123456"
        user.email = "lilianjie@tfgzs.net"
        user.mobile = "17090881553"
        user.qq = "123456"
        user.birthLunar = new DateTime(1993, 4, 26, 0, 0)
        user.birthSolar = new DateTime(1993, 4, 26, 0, 0)
        user.gender = User_Gender.MALE
        user.score = new BigDecimal("100")
        user.portrait = "http://image.tfgzs.net/portrait/001.jpg"
        user.status = User_Status.NORMAL
        user.online = false
        user.addressCounty = "朝阳区"
        user.addressStreet = "四惠东"
        user.addressCountry = "中国"
        user.addressProvince = "北京"
        user.addressCity = "北京"
        user.addressZipcode = "010"

//        User result = userController.add(user)
//        println result.toString()
    }
}