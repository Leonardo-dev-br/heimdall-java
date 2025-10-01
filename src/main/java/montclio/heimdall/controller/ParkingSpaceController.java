package montclio.heimdall.controller;

import jakarta.validation.Valid;
import montclio.heimdall.dto.ParkingSpaceDTO.GetParkingSpaceDTO;
import montclio.heimdall.dto.ParkingSpaceDTO.PostParkingSpaceDTO;
import montclio.heimdall.dto.ParkingSpaceDTO.PutParkingSpaceDTO;
import montclio.heimdall.dto.ParkingSpaceDTO.ParkingSpaceFilter;
import montclio.heimdall.model.ParkingSpace;
import montclio.heimdall.service.ParkingSpaceService;
import montclio.heimdall.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/parking")
public class ParkingSpaceController {

    @Autowired
    private ParkingSpaceService parkingSpaceService;

    @GetMapping
    public String list(ParkingSpaceFilter filter,
                       @PageableDefault(size = 9, sort = "idVaga", direction = Sort.Direction.ASC) Pageable pageable,
                       Model model) {
        Page<GetParkingSpaceDTO> page = parkingSpaceService.getAllParkingSpaces(filter, pageable);
        model.addAttribute("parkings", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("filter", filter);

        model.addAttribute("parkingList", page.getContent());
        return "parking/list";
    }


    @GetMapping("/new")
    public String newForm(Model model) {
        PostParkingSpaceDTO dto = new PostParkingSpaceDTO(null, null, false, "");
        model.addAttribute("parkingDto", dto);
        model.addAttribute("parking", dto); 
        return "parking/form";
    }


    @PostMapping
    public String create(@ModelAttribute("parkingDto") @Valid PostParkingSpaceDTO parkingDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.parkingDto", bindingResult);
            redirectAttributes.addFlashAttribute("parkingDto", parkingDto);
            return "redirect:/parking/new";
        }

        ParkingSpace saved = parkingSpaceService.postParkingSpace(parkingDto);
        redirectAttributes.addFlashAttribute("success", "Vaga criada (ID: " + saved.getIdVaga() + ")");
        return "redirect:/parking";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            GetParkingSpaceDTO dto = parkingSpaceService.getParkingSpaceById(id);
            PostParkingSpaceDTO formDto = new PostParkingSpaceDTO(dto.idVaga(), dto.idZona(), dto.preenchida(), dto.codVaga());
            model.addAttribute("parkingDto", formDto);
            model.addAttribute("parking", formDto);
            return "parking/form";
        } catch (ResourceNotFoundException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
            return "redirect:/parking";
        }
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute("parkingDto") @Valid PostParkingSpaceDTO parkingDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.parkingDto", bindingResult);
            redirectAttributes.addFlashAttribute("parkingDto", parkingDto);
            return "redirect:/parking/" + id + "/edit";
        }

        try {
            PutParkingSpaceDTO updateDto = new PutParkingSpaceDTO(parkingDto.idZona(), parkingDto.preenchida(), parkingDto.codVaga());
            parkingSpaceService.putParkingSpace(id, updateDto);
            redirectAttributes.addFlashAttribute("success", "Vaga atualizada");
        } catch (ResourceNotFoundException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/parking";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            parkingSpaceService.deleteParkingSpace(id);
            redirectAttributes.addFlashAttribute("success", "Vaga removida");
        } catch (ResourceNotFoundException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/parking";
    }

    @GetMapping("/{id}/history")
    public String redirectToHistory(@PathVariable Long id) {
        return "redirect:/histories?vehicleId=" + id;
    }

    @GetMapping("/{id}/history/new")
    public String redirectToNewHistory(@PathVariable Long id) {
        return "redirect:/histories/new?vehicleId=" + id;
    }
}
