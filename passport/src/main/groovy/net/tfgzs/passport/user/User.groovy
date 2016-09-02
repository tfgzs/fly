package net.tfgzs.passport.user

import net.tfgzs.common.base.BaseEntity
import org.joda.time.DateTime

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "passport_user")
class User extends BaseEntity {

    /**
     * 真实姓名
     */
    @Column
    String username
    /**
     * 昵称，里面可以带有表情符
     * 只用来显示，不能用来登录
     */
    @NotNull(message = "昵称不能为空")
    @Column
    String nickname
    /**
     * 密码
     */
    @NotNull(message = "密码不能为空")
    @Column
    String password
    /**
     * 邮箱
     */
    @Column(unique = true, nullable = true)
    String email
    /**
     * 手机号
     */
    @Column(unique = true, nullable = true)
    String mobile
    /**
     * QQ
     */
    @Column
    String qq
    /**
     * 性别，0女  1男
     */
    @Enumerated(EnumType.STRING)
    User_Gender gender
    /**
     * 积分
     */
    @Column
    BigDecimal score
    /**
     * 头像地址
     */
    @Column
    String portrait
    /**
     * 生日，阳历
     */
    @Column
    DateTime birthSolar

    /**
     * 生日，阴历
     */
    @Column
    DateTime birthLunar

    /**
     * 用户状态
     */
    @Column
    User_Status status
    /**
     * 在线状态
     */
    @Column
    Boolean online

    /**
     * 所在国家
     */
    @Column
    String addressCountry

    /**
     * 所在省
     */
    @Column
    String addressProvince
    /**
     * 所在市
     */
    @Column
    String addressCity
    /**
     * 县
     */
    @Column
    String addressCounty
    /**
     * 街道
     */
    @Column
    String addressStreet
    /**
     * 邮编
     */
    @Column
    String addressZipcode

    /**
     * 本次登录ip
     */
    @Column
    String thisLoginIp
    /**
     * 本次登录时间
     */
    @Column
    String thisLoginTime
    /**
     * 上次登录ip
     */
    @Column
    String lastLoginIp
    /**
     * 上次登录时间
     */
    @Column
    String lastLoginTime

}

class UserCriteria extends User {
}

enum User_Status {
    NORMAL("正常"), FORBIDDEN("禁用")

    String desc;

    User_Status(String desc) {
        this.desc = desc
    }

    public static User_Status getByName(String name) {
        for (User_Status v : User_Status.values()) {
            if (v.name().equals(name)) {
                return v;
            }
        }
        return null;
    }

    public static User_Status getByOrdinal(int ordinal) {
        for (User_Status v : User_Status.values()) {
            if (v.ordinal() == ordinal) {
                return v;
            }
        }
        return null;
    }

}
/**
 * 性别
 */
enum User_Gender {
    MALE("男"), FEMALE("女")

    String desc;

    User_Gender(String desc) {
        this.desc = desc
    }

    public static User_Gender getByName(String name) {
        for (User_Gender v : User_Gender.values()) {
            if (v.name().equals(name)) {
                return v;
            }
        }
        return null;
    }

    public static User_Gender getByOrdinal(int ordinal) {
        for (User_Gender v : User_Gender.values()) {
            if (v.ordinal() == ordinal) {
                return v;
            }
        }
        return null;
    }

//    public static void main(String[] args) {
//        println User_Gender.getByName("MALE")
//        println User_Gender.getByOrdinal(0).string
//        User_Gender g=User_Gender.valueOf("FEMALE")
//        println g
//    }
}

