package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    ImageService imageService1;

    @Autowired
    UserRepository userRepository1;

    public List<Blog> showBlogs(){
        //find all blogs

        return new ArrayList<>(blogRepository1.findAll());

    }

    public void createAndReturnBlog(Integer userId, String title, String content) {
        //create a blog at the current time
        Blog blog = new Blog();

        blog.setTitle(title);
        blog.setContent(content);
        blog.setUser(userRepository1.findById(userId).get());
        User user = userRepository1.findById(userId).get();
        List<Blog> st = user.getBlogList();
        st.add(blog);
        userRepository1.save(user);
        blogRepository1.save(blog);

        //updating the blog details

        //Updating the userInformation and changing its blogs


    }

    public Blog findBlogById(int blogId){
        //find a blog
        return blogRepository1.findById(blogId).get();
    }

    public void addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog after creating it

        Blog blog = blogRepository1.findById(blogId).get();
        imageService1.createAndReturn(blog,description,dimensions);
        List<Image> st = blog.getImageList();
        Image image = new Image();
        image.setDimensions(dimensions);
        image.setDescription(description);

        st.add(image);
        blog.setImageList(st);
        blogRepository1.save(blog);

    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
        Blog blog = blogRepository1.findById(blogId).get();
        List<Image> st = blog.getImageList();
        for(Image image: st){
            imageService1.deleteImage(image);
            st.remove(image);
        }
        blogRepository1.delete(blog);
    }
}
