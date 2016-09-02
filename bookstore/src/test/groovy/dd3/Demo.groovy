package dd3

class Demo {
    public static void main(String[] args) {

        UserExample.Criteria criteria1 = new UserExample.Criteria().andDeletedEqualTo(0)
        UserExample.Criteria criteria2 = new UserExample.Criteria().andUsernameLike("%tom%")
        UserExample.Criteria criteria3 = new UserExample.Criteria().andStatusEqualTo(100).andRoleEqualTo(1)

        println whereSql(new UserExample().or(criteria1).or(criteria2).or(criteria3)).toString()
        println whereSql(new UserExample().or(criteria3)).toString()
    }

    private static StringBuilder whereSql(UserExample e) {
        StringBuilder whereSql = new StringBuilder()
        for (int i = 0; i < e.oredCriteria.size(); i++) {
            UserExample.Criteria it = e.oredCriteria.get(i)
            if (it.valid) {
                whereSql.append(" ( ")
                for (int j = 0; j < it.criteria.size(); j++) {
                    UserExample.Criterion criterion = it.criteria.get(j)
                    if (j != 0) {
                        whereSql.append(" and ")
                    }
                    if (criterion.noValue) {
                        whereSql.append(criterion.condition)
                    }
                    if (criterion.singleValue) {
                        whereSql.append(criterion.condition).append(" ").append(criterion.value)
                    }
                    if (criterion.betweenValue) {
                        whereSql.append(criterion.condition).append(" ").append(criterion.value).append(" ").append(criterion.secondValue)
                    }
                    if (criterion.listValue) {
                        whereSql.append(criterion.condition)
                        if (criterion.value) {
                            whereSql.append(" ( ")
                            criterion.value.each { listItem ->
                                whereSql.append(it).append(" , ")
                            }
                            whereSql.append(" ) ")
                        }
                    }
                }
                whereSql.append(" ) ")
                if (i != e.oredCriteria.size() - 1) {
                    whereSql.append(" or ")
                }
            }
        }
        return whereSql
    }
}
