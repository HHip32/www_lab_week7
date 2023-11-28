package vn.edu.iuh.fit.frontends.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.backends.enums.ProductStatus;
import vn.edu.iuh.fit.backends.models.Product;
import vn.edu.iuh.fit.backends.models.ProductImage;
import vn.edu.iuh.fit.backends.models.ProductPrice;
import vn.edu.iuh.fit.backends.repositories.ProductRepository;
import vn.edu.iuh.fit.backends.services.ProductService;
import vn.edu.iuh.fit.frontends.dto.CartItem;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/client")
public class ShoppingController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @GetMapping("/shopping/home")
    public String homePage(Model model,
                           @RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(8);
        List<Object[]> productDetails = productRepository.findAllProductsWithDetails(ProductStatus.ACTIVE);
        Page<Product> productPage = productService.findPaginated(currentPage - 1,
                pageSize, "name", "asc");

        model.addAttribute("productPage", productPage);
        int totalPages = productPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }


        model.addAttribute("productDetails", productDetails);
        return "/client/shopping/home";
    }

    @GetMapping("/shopping/cart")
    public String checkOut() {
        return "/client/shopping/cart";
    }

    @GetMapping("/shopping/addToCart/{id}")
    public String addToCart(HttpSession session, Model model, @PathVariable("id") Long id) {
        Object object = session.getAttribute("items");
        Product product = productRepository.findById(id).get();

        HashMap<Long, CartItem> cart = null;

        if (object == null)
            cart = new HashMap<>();
        else
            cart = (HashMap<Long, CartItem>) object;

        CartItem item = new CartItem(product, 1);
        if (cart.get(product.getProduct_id()) != null)
            item.setAmount(item.getAmount() + 1);
        cart.put(product.getProduct_id(),item);

        session.setAttribute("items", cart);

        session.setAttribute("itemsOnCart", cart.size());
        return "redirect:/shopping/home";
    }
}
