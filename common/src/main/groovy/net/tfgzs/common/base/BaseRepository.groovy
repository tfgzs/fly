package net.tfgzs.common.base
import groovy.transform.CompileStatic
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.NoRepositoryBean

@CompileStatic
@NoRepositoryBean
interface BaseRepository<M, ID extends Serializable> extends JpaSpecificationExecutor<M>, JpaRepository<M, ID> {

}