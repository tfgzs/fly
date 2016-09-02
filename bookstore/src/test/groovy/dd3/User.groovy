package dd3

public class User implements Serializable {
    static final long serialVersionUID = 1L;
    Long id;
    String username;
    String password;
    // 总金额
    BigDecimal amountTotal;
    // 可用金额
    BigDecimal amountAvail;
    // 冻结金额
    BigDecimal amountFreeze;
    // 用户状态 -1禁止登录 0正常用户
    Integer status;
    // 用户角色 0普通用户 18管理员 99超级管理
    Integer role;
    // 删除状态 0没有删除 -1已经删除
    Integer deleted;
    // 注册时间
    Date createdate;
    Long lock;//锁定状态
}