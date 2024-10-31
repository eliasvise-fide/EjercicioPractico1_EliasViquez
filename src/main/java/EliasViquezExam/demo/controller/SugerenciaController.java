package EliasViquezExam.demo.controller;

import org.springframework.ui.Model;
import EliasViquezExam.demo.domain.Sugerencia;
import EliasViquezExam.service.SugerenciaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Slf4j
@RequestMapping("/sugerencia")
public class SugerenciaController {

    @Autowired
    private SugerenciaService sugerenciaService;

    @GetMapping("/listado")
    public String inicio(Model model) {
        var sugerencias = sugerenciaService.getSugerencias();
        model.addAttribute("sugerencias", sugerencias);
        model.addAttribute("totalSugerencias", sugerencias.size());
        return "/sugerencia/listado";
    }

    @GetMapping("/nuevo")
    public String sugerenciaNuevo(Sugerencia sugerencia) {
        return "/sugerencia/modifica";
    }

    @PostMapping("/guardar")
    public String sugerenciaGuardar(Sugerencia sugerencia) {
        sugerenciaService.save(sugerencia);
        return "redirect:/sugerencia/listado";
    }

    @GetMapping("/eliminar/{id}")
    public String sugerenciaEliminar(Sugerencia sugerencia) {
        sugerenciaService.delete(sugerencia);
        return "redirect:/sugerencia/listado";
    }

    @GetMapping("/modificar/{id}")
    public String sugerenciaModificar(Sugerencia sugerencia, Model model) {
        sugerencia = sugerenciaService.getSugerencia(sugerencia);
        model.addAttribute("sugerencia", sugerencia);
        return "/sugerencia/modifica";
    }
}
