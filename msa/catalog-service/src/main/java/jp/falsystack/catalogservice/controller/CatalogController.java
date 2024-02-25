package jp.falsystack.catalogservice.controller;

import jp.falsystack.catalogservice.service.CatalogService;
import jp.falsystack.catalogservice.vo.ResponseCatalog;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/catalog-service")
public class CatalogController {

    private final CatalogService catalogService;
    private final Environment env;

    @GetMapping("/health-check")
    public String status() {
        return String.format("It's Working in Catalog Service on %s", env.getProperty("local.server.port"));
    }

    @GetMapping("/catalogs")
    public List<ResponseCatalog> getUsers() {
        return catalogService.getAllCatalogs();
    }
}
