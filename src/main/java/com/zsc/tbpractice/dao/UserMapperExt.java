package com.zsc.tbpractice.dao;

import com.zsc.general.dao.UserMapper;
import com.zsc.general.entity.User;
import com.zsc.general.entity.UserTeacher;
import com.zsc.tbpractice.entityvo.MapVo;
import com.zsc.tbpractice.entityvo.UserTeacherVo;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * mybatis的优化操作
 * @author ZhangSuchao
 * @create 2019/3/28
 * @since 1.0.0
 */
public interface UserMapperExt extends UserMapper {
    //association  collection  查询一条user记录的时候使用，多条需要重新计算“分页”，因此多条的时候在“服务层”分步查询
    UserTeacherVo selectUserTeacherByUserId(@Param("userId") Integer userId);

    //主键:自动将主键id，set到User的属性
    Integer insertUserInfo(User user);

    //批量的insert(使用foreach)
    Integer insertTeacherList(@Param("teacherlist") List<UserTeacher> teacherlist);

    List<User> selectUserListByIdList(@Param("idList") List<Integer> idList);

    List<User> selectUserListByIdSet(@Param("idSet") Set<Integer> idSet);

    @MapKey("id")
    Map<Integer, MapVo> selectUserByUserIdList(@Param("idList") List<Integer> idList); //报错信息不用管，因为使用了mapKey

    /**
     * 参数为set，返回值为Map。主键作为key
     * <p>
     * 使用场景：根据第一次查询的结果，遍历得到Set的id集合（不用list是因为set可以自动去重，取数据根据Map的key）
     * 优点：1，遍历得到Set集合自动去重，List接收需要判断去重（不去重也不会出错）
     *      2，返回Map而不是List，可以拿出Set集合中的数据，依据key直接获取对应的value
     * 待优化：MapKey的value只能是一个对象，如果查询结果只有两列，需要将此两列封装到一个对象中作为value。。
     * 优化方案：直接将查询的两列作为key-value形式返回
     *
     * @param idSet
     * @return
     */
    @MapKey("id")
    Map<Integer, User> selectUserByIdSetResultMap(@Param("idSet") Set<Integer> idSet);


}
