package ua.pt.ChronoCharm.boundary;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;

import ua.pt.ChronoCharm.service.WatchManagerService;
import ua.pt.ChronoCharm.service.CartService;

import ua.pt.ChronoCharm.data.Watch;
import ua.pt.ChronoCharm.data.Cart;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class ChronoCharmController {

    @Autowired
    private WatchManagerService watchService;

    @Autowired
    private CartService cartService;

    @GetMapping("/")
    public String main(Model model) {
        List<Watch> watches = watchService.getAllWatches();
        model.addAttribute("watches", watches);

        return "index"; //view
    }

    @GetMapping("/product/{id}")
    public String showWatchDetail(@PathVariable Long id, Model model) {
        Optional<Watch> watchOptional = watchService.getWatchDetails(id);

        if (watchOptional.isPresent()) {
            Watch watch = watchOptional.get();
            model.addAttribute("watch", watch);
            return "product-detail";
        } else {
            throw new IllegalArgumentException("Watch with ID " + id + " not found");
        }
    }

    @GetMapping("/cart")
    public String viewCart(Model model) {
        model.addAttribute("cart", cartService.getCart());
        return "cart";
    }

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam("productId") Long productId,
                            @RequestParam(value = "redirect", required = false) String redirectPage,
                            RedirectAttributes redirectAttributes) {
        cartService.addToCart(productId);
        redirectAttributes.addFlashAttribute("message", "Product added to cart successfully!");

        return (redirectPage != null && !redirectPage.isEmpty()) ? "redirect:/" + redirectPage : "redirect:/";
    }


}