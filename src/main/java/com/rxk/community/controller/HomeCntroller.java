package com.rxk.community.controller;

import com.rxk.community.entity.DiscussPost;
import com.rxk.community.entity.Page;
import com.rxk.community.entity.User;
import com.rxk.community.service.DiscussPostService;
import com.rxk.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeCntroller {
    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/index",method = RequestMethod.GET)
    public String getIndexpage(Model model ,Page page){
        //方法调用钱，SpringMVC会自动实例化Model和Page并将Page注入Model中，所以不需要再addAttribute
        page.setRows(discussPostService.findDiscussPostRows(0));
        page.setPath("/index");
        List<DiscussPost> list = discussPostService.findDiscussPosts(0, page.getOffset(), page.getLimit());
        ArrayList<Map<String,Object>> discussPosts = new ArrayList<>();
        if(list!= null){
            for (DiscussPost post:list){
                HashMap<String,Object> map = new HashMap<>();
                map.put("post",post);
                User user = userService.findUserById(post.getUserId());
                map.put("user",user);
                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts",discussPosts);
        return "index";
    }
}
