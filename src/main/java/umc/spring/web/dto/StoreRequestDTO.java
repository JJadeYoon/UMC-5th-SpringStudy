package umc.spring.web.dto;

import lombok.Getter;
import umc.spring.domain.Region;

public class StoreRequestDTO {

    @Getter
    public static class ReviewDTO {

        private String title;

        private String body;

        private Float score;
    }

    @Getter
    public static class AddStoreDTO {
        private Long region;

        private String name;

        private String address;
    }

}
