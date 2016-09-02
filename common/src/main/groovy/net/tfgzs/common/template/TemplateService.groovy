//package net.tfgzs.common.template
//
//import groovy.transform.CompileStatic
//import groovy.util.logging.Slf4j
//import net.tfgzs.common.base.BaseEntity
//import net.tfgzs.common.base.BaseRepository
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.data.domain.Page
//import org.springframework.data.domain.Pageable
//import org.springframework.data.domain.Sort
//import org.springframework.data.jpa.domain.Specification
//
///**
// * TODO 需要新增：逻辑删除
// */
//@CompileStatic
//@Slf4j
//abstract class TemplateService<T extends BaseEntity, ID extends Serializable> {
//
//    protected BaseRepository<T, ID> baseRepository
//
//    @Autowired
//    public void setBaseRepository(BaseRepository<T, ID> baseRepository) {
//        this.baseRepository = baseRepository
//    }
//
//    //================================新增==============================================
//    /**
//     * 新增一个实体
//     * @param entity 实体
//     * @return 返回保存的实体
//     */
//    public <S extends T> S save(S entity) {
//        return baseRepository.save(entity)
//    }
//    /**
//     * 批量新增
//     * @param entities
//     * @return 返回保存的实体列表
//     */
//    public <S extends T> List<S> save(List<S> entities) {
//        return baseRepository.save(entities)
//    }
//
//    //=================================删除=============================================
//
//    /**
//     * 根据主键删除相应实体
//     * @param id 主键
//     */
//    public void delete(ID id) {
//        baseRepository.delete(id)
//    }
//
//    /**
//     * 根据实体列表删除 TODO 待研究
//     * @param entities
//     */
//    public void delete(List<? extends T> entities) {
//        baseRepository.delete(entities)
//    }
//    /**
//     * 根据实体列表批量删除 TODO 待研究
//     * @param entities
//     */
//    public void deleteInBatch(List<T> entities) {
//        baseRepository.deleteInBatch(entities)
//    }
//    /**
//     * 删除全部
//     */
//    public void deleteAll() {
//        baseRepository.deleteAll();
//    }
//    //=================================修改=========================================
//    /**
//     * 更新单个实体
//     * @param entity 实体
//     * @return 返回更新的实体
//     */
//    public T update(T entity) {
//        return baseRepository.save(entity)
//    }
//    //=================================查询====================================
//    /**
//     * 按照主键查询一个实体
//     * @param id 主键
//     * @return 返回id对应的实体
//     */
//    public T findOneById(ID id) {
//        return baseRepository.findOne(id)
//    }
//    /**
//     * 根据条件查询一个实体 TODO 待研究
//     * @param spec
//     * @return
//     */
//    public T findOne(Specification<T> spec) {
//        baseRepository.findOne(spec)
//    }
//    /**
//     * 实体是否存在
//     * @param id 主键
//     * @return 存在 返回true，否则false
//     */
//    public boolean existsById(ID id) {
//        return baseRepository.exists(id)
//    }
//    /**
//     * 查询所有实体
//     *
//     * @return
//     */
//    public List<T> findAll() {
//        return baseRepository.findAll()
//    }
//    /**
//     * 按照顺序查询所有实体  TODO 待研究
//     * @param sort
//     * @return
//     */
//    public List<T> findAll(Sort sort) {
//        return baseRepository.findAll(sort)
//    }
//    /**
//     * 根据id集合查询实体
//     * @param ids
//     * @return
//     */
//    public List<T> findAll(List ids) {
//        return baseRepository.findAll(ids)
//    }
//
//    /**
//     * 分页及排序查询实体  TODO 待研究
//     * @param pageable 分页及排序数据
//     * @return
//     */
//    public Page<T> findAll(Pageable pageable) {
//        return baseRepository.findAll(pageable)
//    }
//    /**
//     * TODO 待研究
//     * @param spec
//     * @param pageable
//     * @return
//     */
//    public Page<T> findAll(Specification<T> spec, Pageable pageable) {
//        baseRepository.findAll(spec, pageable)
//    }
//    /**
//     * TODO 待研究
//     * @param spec
//     * @return
//     */
//    public List<T> findAll(Specification<T> spec) {
//        baseRepository.findAll(spec)
//    }
//    /**
//     * TODO 待研究
//     */
//    public List<T> findAll(Specification<T> spec, Sort sort) {
//        baseRepository.findAll(spec, sort)
//    }
//
//    /**
//     * 统计实体总数
//     *
//     * @return 实体总数
//     */
//    public long count() {
//        return baseRepository.count()
//    }
//    /**
//     * TODO 待研究
//     */
//    public long count(Specification<T> spec) {
//        baseRepository.count(spec)
//    }
//}
