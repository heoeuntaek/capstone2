package com.example.capstone.service;

import com.example.capstone.dto.UserDto;
import com.example.capstone.entity.Group_tbl;
import com.example.capstone.entity.User;
import com.example.capstone.repository.GroupRepository;
import com.example.capstone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
//@Transactional
@RequiredArgsConstructor
public class UserService {


    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final GroupRepository groupRepository;



    public List<User> findAll() {
        return userRepository.findAll();  //리스트 형태로 반환
    }


    public User findbyId(Long id) {
        log.info("정보 {}", userRepository.findById(id));
        log.info("정보2{}", userRepository.findById(id).toString());
        return userRepository.findById(id).orElse(null);  //객체 하나 반환

    }

    public User findByUser_login_id(String user_login_id) {
       log.info("정보 {}", userRepository.findByUser_login_id(user_login_id));
        User user_found = userRepository.findByUser_login_id(user_login_id);

        //dto로 변환
        return user_found;  //객체 하나 반환
    }



    public User create(UserDto dto) {

        User created = dto.toEntity();
        if (created.getId() != null) {
            throw new IllegalArgumentException("이미 아이디가 있어서 생성할 수 없습니다.");
        }
        return userRepository.save(created);
    }


    public User update(Long id, UserDto dto) {
        //1: 수정용 엔티티 조회 dto -> entity
        User user = dto.toEntity();
        log.info("id : {}, article : {}", id, user.toString());

        // 2:대상 엔티티 조회
        User target = userRepository.findById(id).orElse(null);  //기존에 있던 것

        //3 : 잘못된 요청 처리(id 없거나, id 다른경우

        if (target == null || id != user.getId()) {
            // 400 - 잘못된 요청
            log.info("잘못된 요청! id : {}, article : {}", id, user.toString());
            return null;
        }
        // 4 : 업데이트 및 정상 응답(200)
        target.patch(user); // 기존에 있던것을 붙임
        User updated = userRepository.save(target); //target 저장, target을 업데이트
        return updated;

    }


    public User login(UserDto dto) {
        //1: 수정용 엔티티 조회 dto -> entity
        User user = dto.toEntity();

//        입력받은 id
        String user_login_id = dto.getUser_login_id();
//        입력받은 비번
        String user_pass = user.getUser_pass();

        // 2:Id를 이용해 db의 비번 조회
        User target = userRepository.findByUser_login_id(user.getUser_login_id());  //기존에 있던 것
//        User target = userService.findOneByUser_id(user.getUser_id());  //기존에 있던 것

        // db비번
        String db_pass = target.getUser_pass();

        if (user != null) {
            if (user_pass.equals(db_pass)) {
                System.out.println("비밀번호가 일치합니다.");
                return user;
            } else {
                System.out.println("비밀번호가 불일치합니다.");
                return null;
            }
        }
        System.out.println("아이디가 없습니다.");
        return null;
    }



    public User findByUser_name(String user_name) {
        return userRepository.findByuser_name(user_name);
    }

    public Group_tbl find_group_by_code(String group_code, String user_login_id) {
        Group_tbl found_group = groupRepository.findByGroup_code(group_code); // 그룹 코드로 group 조회


        return found_group;

    }

    public UserDto findByUser_login_id_dto(String user_login_id) {
        User user_found = userRepository.findByUser_login_id(user_login_id);
        UserDto userDto = UserDto.toDto(user_found);
        return userDto;
    }
}