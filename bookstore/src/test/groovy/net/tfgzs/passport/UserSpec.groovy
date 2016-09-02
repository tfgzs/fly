package net.tfgzs.passport
import net.dongliu.requests.Requests
import net.tfgzs.common.kit.JsonFormatTool

class UserSpec {

    def setupSpec() {}

    def setup() {}

    def cleanup() {}


//    @Ignore
    def "邮箱注册"() {
        setup:
        println JsonFormatTool.formatJson(
                Requests.post("http://localhost:8080/user/register")
                        .forms(["nickname":"system","principals":"system@tfgzs.net", "credentials":"system@ltf@123"])
                        .send()
                        .readToText()
        )
    }

//    @Ignore
//    def "修改用户信息"() {
//        setup:
//    }
}
