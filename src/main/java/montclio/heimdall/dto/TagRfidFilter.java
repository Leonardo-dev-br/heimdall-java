package montclio.heimdall.dto;

public record TagRfidFilter(
        String frequencia,
        String banda,
        String aplicacao,
        Long motorcycleId
        ) {
}
