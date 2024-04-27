package pe.edu.upc.sellmaster.partner_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.sellmaster.partner_service.model.dtos.PartnerRequest;
import pe.edu.upc.sellmaster.partner_service.model.dtos.PartnerResponse;
import pe.edu.upc.sellmaster.partner_service.model.entities.Partner;
import pe.edu.upc.sellmaster.partner_service.repository.PartnerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PartnerService {
    private final PartnerRepository partnerRepository;

    @Transactional
    public void addPartner(PartnerRequest request) {
        Partner partner = Partner.builder()
                .partnerName(request.getPartnerName())
                .RUC(request.getRUC())
                .additionalInfo(request.getAdditionalInfo())
                .build();
        partnerRepository.save(partner);
        log.info("Partner added: {}", partner);
    }

    public List<PartnerResponse> getAllPartners() {
        return partnerRepository.findAll().stream()
                .map(this::mapToPartnerResponse)
                .toList();
    }

    public PartnerResponse getPartnerById(long id) {
        Partner partner = partnerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partner not found with id: " + id));
        return mapToPartnerResponse(partner);
    }

    @Transactional
    public void updatePartner(long id, PartnerRequest request) {
        Partner partner = partnerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partner not found with id: " + id));
        partner.setPartnerName(request.getPartnerName());
        partner.setRUC(request.getRUC());
        partner.setAdditionalInfo(request.getAdditionalInfo());
        partnerRepository.save(partner);
        log.info("Updated Partner: {}", partner);
    }

    public void deletePartner(long id) {
        if (!partnerRepository.existsById(id)) {
            throw new RuntimeException("Partner not found with id: " + id);
        }
        partnerRepository.deleteById(id);
        log.info("Deleted Partner with id {}", id);
    }

    public PartnerResponse findByRUC(long RUC) {
        Partner partner = partnerRepository.findByRUC(RUC);
        if (partner == null) {
            throw new RuntimeException("No partner found with RUC: " + RUC);
        }
        return mapToPartnerResponse(partner);
    }

    private PartnerResponse mapToPartnerResponse(Partner partner) {
        return PartnerResponse.builder()
                .partnerID(partner.getPartnerID())
                .partnerName(partner.getPartnerName())
                .RUC(partner.getRUC())
                .additionalInfo(partner.getAdditionalInfo())
                .build();
    }
}