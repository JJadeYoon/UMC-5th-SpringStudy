package umc.spring.web.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

public class StoreRequestDTO {

    @Getter
    public static class ReviewDTO {

        private String title;

        private String body;

        private Float score;

        MultipartFile reviewPicture;
    }

    @Getter
    public static class StoreDTO {
        private Long region;

        private String name;

        private String address;
    }

}
