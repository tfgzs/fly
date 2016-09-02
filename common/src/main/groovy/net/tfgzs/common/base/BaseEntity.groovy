package net.tfgzs.common.base

import groovy.transform.CompileStatic
import net.logstash.logback.encoder.org.apache.commons.lang.builder.ReflectionToStringBuilder
import org.hibernate.annotations.GenericGenerator
import org.joda.time.DateTime
import org.joda.time.DateTimeZone

import javax.persistence.*
import javax.validation.constraints.NotNull

//缺少这个注解就会报“No identifier specified for entity”异常
@MappedSuperclass
@CompileStatic
abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(generator = "system_uuid")
    @GenericGenerator(name = "system_uuid", strategy = "uuid2")
    @Column(length = 128, unique = true, nullable = false, insertable = true, updatable = false)
    String id
    /**
     * 逻辑删除  默认：false，true为删除
     */
    @Column(nullable = false)
    boolean deleted
    /**
     * 创建时间
     */
    @Column(nullable = false)
    DateTime dateCreated = DateTime.now(DateTimeZone.UTC)
    /**
     * 最后一次更新时间
     */
    @Basic(optional = false)
    @Column(nullable = false)
    DateTime dateUpdated = DateTime.now(DateTimeZone.UTC)

    @PrePersist
    protected void onCreate() {
        dateUpdated = dateCreated = DateTime.now(DateTimeZone.UTC)
        deleted = false  //默认不删除
    }

    @PreUpdate
    protected void onUpdate() {
        dateUpdated = DateTime.now(DateTimeZone.UTC)
    }


    @Override
    public boolean equals(Object obj) {

        if (null == obj) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!getClass().equals(obj.getClass())) {
            return false;
        }

        BaseEntity<?> that = (BaseEntity<?>) obj;

        return null == this.getId() ? false : this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        int hashCode = 17;
        hashCode += null == getId() ? 0 : getId().hashCode() * 31;
        return hashCode;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
