package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.ImageRepository;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository2;

    public Image createAndReturn(Blog blog, String description, String dimensions){
        //create an image based on given parameters and add it to the imageList of given blog
        Image image = new Image(description,dimensions);
        image.setBlog(blog);
        return image;
    }

    public void deleteImage(Image image){
        imageRepository2.delete(image);
    }

    public Image findById(int id) {
        return imageRepository2.findById(id).get();

    }

    public int countImagesInScreen(Image image, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        //In case the image is null, return 0
        /*try{
            Image image1 = imageRepository2.findById(image.getId()).get();
            String [] arr = image1.getDimensions().split("X");
            int total = Integer.parseInt(arr[0]) * Integer.parseInt(arr[1]);
            String [] arr2 = screenDimensions.split("X");
            int fit = Integer.parseInt(arr2[0]) * Integer.parseInt(arr2[1]);
            if(total>fit){
                return total/fit;
            }
        }catch (Exception e) {
            return 0;
        }
        return 0;*/
        if(screenDimensions.split("X").length==2 || Objects.nonNull(image)){
            Integer maxLength= Integer.parseInt(screenDimensions.split("X")[0])/Integer.parseInt(image.getDimensions().split("X")[0]);
            Integer maxWidth= Integer.parseInt(screenDimensions.split("X")[1])/Integer.parseInt(image.getDimensions().split("X")[1]);

            return  maxWidth*maxLength;
        }
        return  0;

    }
}
