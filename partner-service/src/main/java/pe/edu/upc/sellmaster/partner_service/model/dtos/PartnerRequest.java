package pe.edu.upc.sellmaster.partner_service.model.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PartnerRequest {
    private String partnerName;
    private long RUC;
    private String additionalInfo;
}

