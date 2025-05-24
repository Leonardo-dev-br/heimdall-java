package montclio.heimdall.dto.TagRfidDTO;

public record TagRfidFilter(
        String frequencia,
        String banda,
        String aplicacao,
        Long motorcycleId
        ) {
}
