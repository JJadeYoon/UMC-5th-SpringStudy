package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.aws.s3.AmazonS3Manager;
import umc.spring.converter.ReviewConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.*;
import umc.spring.repository.*;
import umc.spring.web.dto.StoreRequestDTO;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final AmazonS3Manager s3manager;
    private final UuidRepository uuidRepository;
    private final ReviewImageRepository reviewImageRepository;

    @Override
    @Transactional
    public Store createStore(StoreRequestDTO.StoreDTO request) {

        Region region = regionRepository.findById(request.getRegion()).get();

        Store newStore = StoreConverter.toStore(request, region);

        return storeRepository.save(newStore);
    }

    @Override
    @Transactional
    public Review createReview(Long memberId, Long storeId, StoreRequestDTO.ReviewDTO request) {

        Member member = memberRepository.findById(memberId).get();
        Store store = storeRepository.findById(storeId).get();

        Review review = StoreConverter.toReview(member, store, request);

        String uuid = UUID.randomUUID().toString();
        Uuid saveUuid = uuidRepository.save(Uuid.builder()
                .uuid(uuid).build());

        String pictureUrl = s3manager.uploadFile(s3manager.generateReviewKeyName(saveUuid), request.getReviewPicture());
        reviewImageRepository.save(ReviewConverter.toReviewImage(pictureUrl, review));

        return reviewRepository.save(review);
    }
}
