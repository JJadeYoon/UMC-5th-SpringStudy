package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.aws.s3.AmazonS3manager;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Region;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.RegionRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.StoreRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;

    private final RegionRepository regionRepository;

    private final MemberRepository memberRepository;

    private final ReviewRepository reviewRepository;

    private final AmazonS3manager s3manager;

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

        Review newReview = StoreConverter.toReview(member, store, request);

        return reviewRepository.save(newReview);
    }
}
