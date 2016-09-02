package net.tfgzs

import net.dongliu.requests.Requests
import net.tfgzs.common.kit.JsonFormatTool
import net.tfgzs.passport.user.User
import org.junit.Ignore
import org.junit.Test
import org.nutz.json.Json
import org.nutz.json.JsonFormat

class HttpTests {
    @Test
    @Ignore
    public void helloWorld() throws Exception {
        println Requests.get("http://127.0.0.1:8080/user/helloWorld").send().readToText()
    }

    @Test
    @Ignore
    public void list() throws Exception {
        println JsonFormatTool.formatJson(
                Requests.post("http://localhost:8080/user/list")
                        .forms(["username": "李连杰", "password": "123456"])
                        .send()
                        .readToText()
        )
    }

    @Test
    @Ignore
    public void batch() throws Exception {
        println JsonFormatTool.formatJson(
                Requests.post("http://localhost:8080/facade/batch")
                        .forms(["com.tfgzs.user.addUser": "[{\"username\":\"tom\"}]", "com.tfgzs.user.addUser": "[{\"username\":\"bill\"}]", "com.tfgzs.user.editUser": "[{\"id\":\"1\",\"username\":\"jerry\"}]"])
                        .send("""params=[{"username": "李连杰", "password": "123456"}]""")
                        .readToText()
        )
    }

    @Test
    @Ignore
    public void rpcOneMethod() throws Exception {

    }



    @Test
    @Ignore
    public void json() throws Exception {
        List<Object> list = new ArrayList<>()
        Collections.addAll(list, new User(username: "tom"))

        List<Object> list2 = new ArrayList<>()
        Collections.addAll(list2, new User(username: "bill"))

        List<Object> list3 = new ArrayList<>()
        Collections.addAll(list3, new User(id: "1", username: "bill"))


        Map<String, List<Object>> multiMap = new HashMap()
        multiMap.put("com.tfgzs.user.addUser", list)
        multiMap.put("com.tfgzs.user.addUser", list2)
        multiMap.put("com.tfgzs.user.editUser", list3)
        println "=========================================="
//        println JSONObject.toJSONString(multiMap)
        println "=========================================="
        println Json.toJson(multiMap, JsonFormat.full())

    }
}
