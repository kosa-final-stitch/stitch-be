package org.mywork.stitchbe.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.mywork.stitchbe.dto.AdminDto;

import java.util.Optional;

@Mapper
public interface AdminMapper {
    @Select("SELECT * FROM admin WHERE adminId = #{adminId}")
    Optional<AdminDto> findByAdminId(String adminId);
    @Insert("INSERT INTO admin (admin_id, password, role) VALUES (#{admin_id}, #{password}, #{role})")
    @Options(useGeneratedKeys = true, keyProperty = "adminId")
    void saveAdmin(AdminDto admin);
    // 추가적인 CRUD 메서드가 필요하면 여기에 정의
}
