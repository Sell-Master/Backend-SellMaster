package pe.edu.upc.sellmaster.dispatch_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.sellmaster.dispatch_service.model.dtos.*;
import pe.edu.upc.sellmaster.dispatch_service.model.entities.Dispatch;
import pe.edu.upc.sellmaster.dispatch_service.repository.DispatchRepository;
import pe.edu.upc.sellmaster.dispatch_service.repository.PartnerClient;
import pe.edu.upc.sellmaster.dispatch_service.repository.ProductClient;
import pe.edu.upc.sellmaster.dispatch_service.repository.UserClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DispatchService {
    private final DispatchRepository dispatchRepository;
    private final PartnerClient partnerClient;
    private final ProductClient productClient;
    private final UserClient userClient; // Add the UserClient

    @Transactional
    public DispatchResponse addDispatch(DispatchRequest request) {
        // Validate the existence of partner and product via Feign clients
        partnerClient.getPartnerById(request.getPartnerID());
        productClient.getProductById(request.getProductID());

        Dispatch dispatch = Dispatch.builder()
                .userID(request.getUserID())
                .partnerID(request.getPartnerID())
                .productID(request.getProductID())
                .quantity(request.getQuantity())
                .date(request.getDate())
                .build();

        Dispatch savedDispatch = dispatchRepository.save(dispatch);
        log.info("Dispatch added: {}", savedDispatch);
        return mapToDispatchResponse(savedDispatch);
    }

    public List<DispatchDetailResponse> getAllDispatches() {
        return dispatchRepository.findAll().stream()
                .map(dispatch -> {
                    PartnerResponse partnerResponse = partnerClient.getPartnerById(dispatch.getPartnerID());
                    ProductResponse productResponse = productClient.getProductById(dispatch.getProductID());
                    UserResponse userResponse = userClient.getUserById(dispatch.getUserID()); // Fetch user details
                    return new DispatchDetailResponse(
                            dispatch.getDispatchID(),
                            partnerResponse,
                            productResponse,
                            userResponse, // Include in the response
                            dispatch.getQuantity(),
                            dispatch.getDate());
                })
                .collect(Collectors.toList());
    }

    public DispatchResponse getDispatchById(long id) {
        Dispatch dispatch = dispatchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dispatch not found with id: " + id));
        return mapToDispatchResponse(dispatch);
    }

    @Transactional
    public DispatchResponse updateDispatch(long id, DispatchRequest request) {
        Dispatch existingDispatch = dispatchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dispatch not found with id: " + id));

        // Assuming validation has been done via Feign clients if necessary
        existingDispatch.setPartnerID(request.getPartnerID());
        existingDispatch.setUserID(request.getUserID());
        existingDispatch.setProductID(request.getProductID());
        existingDispatch.setQuantity(request.getQuantity());
        existingDispatch.setDate(request.getDate());

        Dispatch updatedDispatch = dispatchRepository.save(existingDispatch);
        log.info("Updated Dispatch: {}", updatedDispatch);
        return mapToDispatchResponse(updatedDispatch);
    }

    public void deleteDispatch(long id) {
        if (!dispatchRepository.existsById(id)) {
            throw new RuntimeException("Dispatch not found with id: " + id);
        }
        dispatchRepository.deleteById(id);
        log.info("Deleted Dispatch with id: {}", id);
    }

    private DispatchDetailResponse mapToDispatchDetailResponse(Dispatch dispatch) {
        PartnerResponse partnerResponse = partnerClient.getPartnerById(dispatch.getPartnerID());
        ProductResponse productResponse = productClient.getProductById(dispatch.getProductID());
        UserResponse userResponse = userClient.getUserById(dispatch.getUserID());
        return DispatchDetailResponse.builder()
                .dispatchID(dispatch.getDispatchID())
                .partner(partnerResponse)
                .product(productResponse)
                .user(userResponse) // Include user information
                .quantity(dispatch.getQuantity())
                .date(dispatch.getDate())
                .build();
    }

    private DispatchResponse mapToDispatchResponse(Dispatch dispatch) {
        return DispatchResponse.builder()
                .dispatchID(dispatch.getDispatchID())
                .userID(dispatch.getUserID())
                .partnerID(dispatch.getPartnerID())
                .productID(dispatch.getProductID())
                .quantity(dispatch.getQuantity())
                .date(dispatch.getDate())
                .build();
    }
}

