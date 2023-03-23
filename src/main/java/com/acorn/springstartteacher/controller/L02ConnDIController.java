package com.acorn.springstartteacher.controller;

import com.acorn.springstartteacher.dto.UsersDto;
import com.acorn.springstartteacher.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    UsersMapper usersMapper; //Mybatis 의 session factory 가 생성한 객체가 주입
    public L02ConnDIController(DataSource dataSource,UsersMapper usersMapper) {//==@Autowired 와 동일 (권장)
        this.dataSource = dataSource;
        this.usersMapper = usersMapper;
    }
    @GetMapping("/update.do")
    public void updateForm(
            @RequestParam(name = "u_id")String uId,
            Model model){
        UsersDto user=usersMapper.findByUId(uId);
        model.addAttribute("user",user);
    }
    @PostMapping("/update.do")
    public String updateAction(UsersDto user){ //폼의 파라미터를 맵핑
        //객체의 필드는 자료형 null, 기본형은 0 이 기본값 => 파라미터가 없으면 0 or null
        int update=0;
        update=usersMapper.updateOne(user);
        if(update>0){
            return "redirect:/users/detail.do?u_id="+user.getUId();
        }else{
            return "redirect:/users/update.do?u_id="+user.getUId();
        }
    }



    @GetMapping("/mybatisList.do")
    public String mybatisList(Model model){
        List<UsersDto> users=usersMapper.findAll();
        model.addAttribute("users",users);
        return "/users/mybatisList";
    }
    @GetMapping("/detail.do")
    public String detail(
            @RequestParam(name = "u_id") String uId,
            Model model){ //aasdf11
        UsersDto user=usersMapper.findByUId(uId);
        model.addAttribute("user",user);
        return "/users/detail";
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
            //System.out.println(users);
            model.addAttribute("users",users);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "/users/list";
    }
}
