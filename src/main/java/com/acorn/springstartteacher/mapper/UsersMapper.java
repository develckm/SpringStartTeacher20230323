package com.acorn.springstartteacher.mapper;

import com.acorn.springstartteacher.dto.UsersDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper //Mybatis db 컨테이너에서 객체 구현 후 관리
public interface UsersMapper {
    List<UsersDto> findAll();
    UsersDto findByUId(String uId); //#{uId}
    int updateOne(UsersDto user);
    int insertOne(UsersDto user);
    int deleteByUIdAndPw(String uId,String pw);
}
