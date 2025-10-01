package montclio.heimdall.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import montclio.heimdall.dto.HistoryDTO.GetHistoryDTO;
import montclio.heimdall.dto.HistoryDTO.HistoryFilter;
import montclio.heimdall.dto.HistoryDTO.PostHistoryDTO;
import montclio.heimdall.dto.HistoryDTO.PutHistoryDTO;
import montclio.heimdall.model.HistoryMoto;
import montclio.heimdall.service.HistoryService;
import montclio.heimdall.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/histories")
@Tag(name = "Historicos", description = "Gerencia histórico de atividades das motos")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @Operation(summary = "Lista históricos", description = "Retorna lista paginada de históricos")
    @GetMapping
    public String list(HistoryFilter filter, Pageable page, Model model,
                       @RequestParam(value = "vehicleId", required = false) Long vehicleId,
                       @RequestParam(value = "idMoto", required = false) Long idMotoParam) {

        // vehicleId pode vir como vehicleId ou idMoto (compatibilidade)
        Long effectiveVehicleId = vehicleId != null ? vehicleId : idMotoParam;

        Page<GetHistoryDTO> historiesPage = historyService.getAllHistoricos(filter, page);
        model.addAttribute("histories", historiesPage.getContent());
        model.addAttribute("page", historiesPage);
        model.addAttribute("filter", filter);

        // atributos compatíveis com templates
        model.addAttribute("vehicleId", effectiveVehicleId);
        model.addAttribute("idMoto", effectiveVehicleId);

        return "parking/history";
    }

    @Operation(summary = "Formulário - novo histórico", description = "Exibe formulário para criar histórico")
    @GetMapping("/new")
    public String newForm(@RequestParam(value = "vehicleId", required = false) Long vehicleId,
                          @RequestParam(value = "idMoto", required = false) Long idMotoParam,
                          Model model) {
        Long effectiveVehicleId = vehicleId != null ? vehicleId : idMotoParam;
        PostHistoryDTO newHistory = new PostHistoryDTO(null, effectiveVehicleId, null, "");
        // adiciona com o nome que o template espera
        model.addAttribute("historyDto", newHistory);
        model.addAttribute("history", newHistory); // alias para compatibilidade
        model.addAttribute("vehicleId", effectiveVehicleId);
        model.addAttribute("idMoto", effectiveVehicleId);
        return "parking/history_form";
    }

    @Operation(summary = "Formulário - editar histórico", description = "Exibe formulário para editar histórico")
    @GetMapping("/{id}")
    public String editForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            GetHistoryDTO existingHistory = historyService.getHistoricoById(id);

            PutHistoryDTO historyUpdate = new PutHistoryDTO(
                existingHistory.idMoto(),
                existingHistory.dataAtividade(),
                existingHistory.atividade()
            );

            model.addAttribute("historyDto", historyUpdate);
            model.addAttribute("history", historyUpdate); // alias
            model.addAttribute("historyId", id);
            model.addAttribute("vehicleId", existingHistory.idMoto());
            model.addAttribute("idMoto", existingHistory.idMoto());
            return "parking/history_form";
        } catch (ResourceNotFoundException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
            return "redirect:/histories";
        }
    }

    @Operation(summary = "Cria um histórico", description = "Cadastra um novo histórico via formulário")
    @PostMapping
    public String createHistory(@ModelAttribute("historyDto") @Valid PostHistoryDTO historyDto,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            // a chave do BindingResult precisa usar o mesmo name do model attribute
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.historyDto", bindingResult);
            redirectAttributes.addFlashAttribute("historyDto", historyDto);
            Long redirectVehicle = historyDto.idMoto();
            return "redirect:/histories/new" + (redirectVehicle != null ? "?vehicleId=" + redirectVehicle : "");
        }

        HistoryMoto saved = historyService.postHistorico(historyDto);
        redirectAttributes.addFlashAttribute("success", "Histórico criado (ID: " + saved.getIdHistorico() + ")");
        return "redirect:/histories" + (historyDto.idMoto() != null ? "?vehicleId=" + historyDto.idMoto() : "");
    }

    @Operation(summary = "Atualiza histórico", description = "Atualiza dados do histórico via formulário")
    @PostMapping("/{id}")
    public String updateHistory(@PathVariable Long id,
                                @ModelAttribute("historyDto") @Valid PutHistoryDTO historyDto,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.historyDto", bindingResult);
            redirectAttributes.addFlashAttribute("historyDto", historyDto);
            return "redirect:/histories/" + id;
        }

        try {
            historyService.putHistorico(id, historyDto);
            redirectAttributes.addFlashAttribute("success", "Histórico atualizado com sucesso");
        } catch (ResourceNotFoundException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/histories" + (historyDto.idMoto() != null ? "?vehicleId=" + historyDto.idMoto() : "");
    }

    @Operation(summary = "Remove histórico", description = "Deleta histórico por ID via formulário")
    @PostMapping("/{id}/delete")
    public String deleteHistory(@PathVariable Long id,
                                @RequestParam(value = "vehicleId", required = false) Long vehicleId,
                                RedirectAttributes redirectAttributes) {
        try {
            historyService.deleteHistorico(id);
            redirectAttributes.addFlashAttribute("success", "Histórico removido");
        } catch (ResourceNotFoundException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/histories" + (vehicleId != null ? "?vehicleId=" + vehicleId : "");
    }
}
