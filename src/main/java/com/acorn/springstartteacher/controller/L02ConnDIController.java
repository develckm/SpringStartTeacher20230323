package com.acorn.springstartteacher.controller;

import com.acorn.springstartteacher.dto.UsersDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users") //하위의 모든 동적 페이지에 /users 붙는다
public class L02ConnDIController {
    //spring 설정으로 datasource 를 정의하면 컨테이너에 DataSource 객체가 존재한다.(java.sql.Connection 을 반환)
    //@Autowired //주입받을 객체가 컨테이너안에 부모타입의 객체가 여러개 있을때 오류가 발생할 수도 있다.
    DataSource dataSource;
    public L02ConnDIController(DataSource dataSource) {//==@Autowired 와 동일 (권장)
        this.dataSource = dataSource;
    }

    @GetMapping("/list.do")
    public String list(Model model){
        String sql="SELECT * FROM users";
        try {
            Connection conn=dataSource.getConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ResultSet rs=pstmt.executeQuery();
            List<UsersDto> users=new ArrayList<>();
            while (rs.next()){
                UsersDto user=new UsersDto();
                user.setUId(rs.getString("u_id"));
                user.setPw(rs.getString("pw"));
                user.setAddress(rs.getString("address"));
                user.setDetailAddress(rs.getString("detail_address"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setGender(rs.getString("gender"));
                user.setPermission(rs.getString("permission"));
                user.setBirth(rs.getString("birth"));
                user.setName(rs.getString("name"));
                user.setImgPath(rs.getString("img_path"));
                user.setPostTime(rs.getDate("post_time"));
                users.add(user);
            }
            System.out.println(users);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "/users/list";
    }
}
