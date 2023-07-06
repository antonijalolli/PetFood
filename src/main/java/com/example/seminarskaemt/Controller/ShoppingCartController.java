package com.example.seminarskaemt.Controller;

import com.example.seminarskaemt.Service.AuthService;
import com.example.seminarskaemt.Service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/shopping-carts")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final AuthService authService;

    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  AuthService authService) {
        this.shoppingCartService = shoppingCartService;
        this.authService = authService;
    }

    @GetMapping("/list")
    public String listAllPetFoodsInShoppingCart(Model model) {
        var userId = this.authService.getCurrentUserId();
        var shoppingCart = this.shoppingCartService.getShoppingCartForUserId(userId);
        var petFoodsInShoppingCart = shoppingCart.getPetFoods();
        model.addAttribute("petFoodsInShoppingCart", petFoodsInShoppingCart);
        return "/shopping-cart";
    }

    @PostMapping("/add/{petFoodId}")
    public String addPetFoodToShoppingCart(@PathVariable Long petFoodId, RedirectAttributes redirectAttributes) {
        var userId = this.authService.getCurrentUserId();
        var shoppingCart = this.shoppingCartService.getShoppingCartForUserId(userId);
        for (var pf : shoppingCart.getPetFoods()) {
            if (pf.getId().equals(petFoodId)) {
                redirectAttributes.addAttribute("error", "PetFoodIsAlreadyInShoppingCart");
                return "redirect:/petFoods";
            }
        }
        this.shoppingCartService.addPetFoodToShoppingCart(shoppingCart.getId(), petFoodId);
            return "redirect:/petFoods";
    }

    @PostMapping("/remove/{petFoodId}")
    public String removePetFoodToShoppingCart(@PathVariable Long petFoodId) {
        var userId = this.authService.getCurrentUserId();
        var shoppingCart = this.shoppingCartService.getShoppingCartForUserId(userId);

        this.shoppingCartService.removePetFoodFromShoppingCart(shoppingCart.getId(), petFoodId);

        return "redirect:/shopping-carts/list";
    }
}


