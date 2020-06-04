package com.huidong.legalsys.dao;

import com.huidong.legalsys.domain.Consult;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户咨询日志的数据访问类
 */

@Repository
public interface ConsultDao {

    /**
     * newConsult 新建咨询记录
     * @param consult 咨询信息
     * @return Boolean 是否新建成功
     */
    Boolean newConsult(Consult consult);

    /**
     * getConsultsByPhone 获得用户所有咨询记录
     * @param phone 手机号
     * @return List<Consult> 用户所有咨询记录
     */
    List<Consult> getConsultsByPhone(String phone);

    /**
     * deleteConsult 删除记录
     * @param id 咨询编号
     * @return Boolean 是否成功删除
     */
    Boolean deleteConsult(Integer id);

    /**
     * getTopkConsults 获得时间最近的k条咨询记录
     * @return List<Consult> 时间最近的k条咨询记录
     */
    List<Consult> getTopkConsults();

    /**
     * getAllConsults 获得所有的咨询记录
     * @return List<Consult> 所有的咨询记录
     */
    List<Consult> getAllConsults();
}
