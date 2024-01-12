package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.code.ApiResponse;
import umc.spring.web.dto.StoreResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class StoreRestController {

    @GetMapping("/{storeId}/reviews")
    public ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> getReviewList(@PathVariable(name = "storeId") Long storeId) {
        return null;
    }
}
