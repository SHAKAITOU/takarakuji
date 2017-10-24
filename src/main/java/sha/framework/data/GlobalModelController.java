package sha.framework.data;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalModelController {
	@ModelAttribute()
    public void globalAttributes(Model model) {
		model.addAttribute("LANGUAGE", "JP");
	}
}
