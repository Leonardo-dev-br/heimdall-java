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
    public String list(HistoryFilter filter, Pageable page, Model model) {
        Page<GetHistoryDTO> historiesPage = historyService.getAllHistoricos(filter, page);
        model.addAttribute("histories", historiesPage.getContent());
        model.addAttribute("page", historiesPage);
        model.addAttribute("filter", filter);
        return "parking/history";
    }

    @Operation(summary = "Formulário - novo histórico", description = "Exibe formulário para criar histórico")
    @GetMapping("/new")
    public String newForm(@RequestParam(value = "vehicleId", required = false) Long vehicleId, Model model) {
        PostHistoryDTO newHistory = new PostHistoryDTO(null, vehicleId, null, "");
        model.addAttribute("history", newHistory);
        model.addAttribute("vehicleId", vehicleId);
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

            model.addAttribute("history", historyUpdate);
            model.addAttribute("historyId", id);
            model.addAttribute("vehicleId", existingHistory.idMoto());
            return "parking/history_form";
        } catch (ResourceNotFoundException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
            return "redirect:/histories";
        }
    }

    @Operation(summary = "Cria um histórico", description = "Cadastra um novo histórico via formulário")
    @PostMapping
    public String createHistory(@ModelAttribute @Valid PostHistoryDTO newHistory,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.history", bindingResult);
            redirectAttributes.addFlashAttribute("history", newHistory);
            return "redirect:/histories/new" + (newHistory.idMoto() != null ? "?vehicleId=" + newHistory.idMoto() : "");
        }

        HistoryMoto saved = historyService.postHistorico(newHistory);
        redirectAttributes.addFlashAttribute("success", "Histórico criado (ID: " + saved.getIdHistorico() + ")");
        return "redirect:/histories";
    }

    @Operation(summary = "Atualiza histórico", description = "Atualiza dados do histórico via formulário")
    @PostMapping("/{id}")
    public String updateHistory(@PathVariable Long id,
                                @ModelAttribute @Valid PutHistoryDTO historyUpdate,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.history", bindingResult);
            redirectAttributes.addFlashAttribute("history", historyUpdate);
            return "redirect:/histories/" + id;
        }

        try {
            historyService.putHistorico(id, historyUpdate);
            redirectAttributes.addFlashAttribute("success", "Histórico atualizado com sucesso");
        } catch (ResourceNotFoundException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/histories";
    }

    @Operation(summary = "Remove histórico", description = "Deleta histórico por ID via formulário")
    @PostMapping("/{id}/delete")
    public String deleteHistory(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            historyService.deleteHistorico(id);
            redirectAttributes.addFlashAttribute("success", "Histórico removido");
        } catch (ResourceNotFoundException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/histories";
    }
}
