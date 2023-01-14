package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.ImageRepository;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository2;

    public Image createAndReturn(Blog blog, String description, String dimensions){
        //create an image based on given parameters and add it to the imageList of given blog
        Image image = new Image();
        image.setBlog(blog);
        image.setDescriptions(description);
        image.setDimensions(dimensions);
        imageRepository2.save(image);
        return imageRepository2.findById(image.getId()).get();
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
        String [] arr = imageRepository2.findById(image.getId()).get().getDimensions().split("X");
        int total = Integer.parseInt(arr[0]) * Integer.parseInt(arr[1]);
        String [] arr2 = screenDimensions.split("X");
        int fit = Integer.parseInt(arr2[0]) * Integer.parseInt(arr2[1]);
        if(total/fit >0){
            return total/fit;
        }
        return 0;

    }
}
