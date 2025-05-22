package montclio.heimdall.dto;

import montclio.heimdall.model.Motorcycle;
import montclio.heimdall.model.TagRfId;

public record GetTagRfidDTO(
        Long id,
        String frequencia,
        String banda,
        String aplicacao,
        Long motorcycleId
) {
    public GetTagRfidDTO(TagRfId tag) {
        this(
                tag.getId(),
                tag.getFrequencia(),
                tag.getBanda(),
                tag.getAplicacao(),
                tag.getMotorcycle() != null ? tag.getMotorcycle().getId() : null

        );
    }
}
