//package com.example.capstone.api;
//
//import com.example.capstone.repository.UserRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import java.util.List;
//
//@Controller
//@Slf4j // 로깅을 위한 골뱅이(어노테이션)
//public class UserController {
//
//    @Autowired  //스프링 부트가 미리 생성해놓은 객체를 가져다가 자동으로 연결
//    private UserRepository userRepository;
//    // new ArticleRepositoryimp(); 없앰
//
//    @GetMapping("/articles/new")
//    public String newArticleForm() {
//        return "articles/new";
//    }
//
//    @PostMapping("/articles/create")
//    public String createArticle(ArticleForm form) {
//        log.info(form.toString());
////        System.out.println(form.toString());  // 테스트 할 때 안좋음-> 로깅으로 대체
//
//        //1. dto를 변환
//        Article article = form.toEntity();
//        log.info(article.toString());
////        System.out.println(article.toString());
//
//        //2. repository에게 entity를 DB에 저장하게 함
//        Article saved = articleRepository.save(article);
//        log.info(saved.toString());
////        System.out.println(saved.toString());
//        return "redirect:/articles/"+saved.getId();
//    }
//
//
//
//
//}